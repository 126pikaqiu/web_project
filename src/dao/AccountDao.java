package dao;

import bean.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * 和数据库中的用户table打交道，完成service的请求
 */
public class AccountDao extends Dao {

    /**
     * 根据用户名获取用户的所有数据
     *
     * @param name 用户名
     * @return 用户的实例
     */
    public User getUserLiu(String name) {
        String sql = "select * from users where username='" + name + "'";
        ArrayList<User> users = doQuerySQL(sql, 0);
        return users.size() > 0 ? users.get(0) : null;
    }

    /**
     * 功能同上，不同人定义的接口冲突而已，合并的人很懒，就在自己的方法名字后面加上自己的姓
     *
     * @param name 用户名
     * @return 用户的实例
     */
    public User getUser(String name) {
        String sql = "select * from users where username=?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String pwd = rs.getString("pwd");
                int userID = rs.getInt("id");
                String email = rs.getString("email");
                int permission = rs.getInt("permission");
                user = new User(userID, name, pwd, email, permission);
            }
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 更新用户信息，对应前台修改个人信息
     *
     * @param user 修改的用户
     * @return 修改是否成功
     */
    public boolean updateUser(User user) {
        User user1 = getUser(user.getName());
        if (user1 != null && user1.getUserID() != user.getUserID()) {
            return false;
        }
        String sql = "update users  set username=?,email=?,signature=?,permission=? where id=?";
        return manageUser(user, sql);
    }

    /**
     * 从插入用户和更新用户中提取出的公共部分，类型是private
     *
     * @param user 便于不同语句获取参数
     * @param sql  执行的语句
     * @return 操作是否成功
     */
    private boolean manageUser(User user, String sql) {
        boolean result = true;
        PreparedStatement preparedStatement = null;
        boolean update = sql.contains("update");
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getSignature());
            preparedStatement.setInt(4, user.getPermission());
            if (update)
                preparedStatement.setInt(5, user.getUserID());
            else preparedStatement.setString(5, user.getPwd());
            int rows = preparedStatement.executeUpdate();
            result = rows > 0;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 添加用户，对应前台管理员添加用户和普通用户注册
     *
     * @param user 插入的用户信息
     * @return 添加是否成功
     */
    public boolean saveUser(User user) {
        if (getUserLiu(user.getName()) != null) {
            return false;
        }
        String sql = "insert into users (username,email,signature,permission,pwd) values(?,?,?,?,?)";
        return manageUser(user, sql);
    }

    /**
     * 查询用户，用以好友添加搜索
     *
     * @param name 用户名
     * @param id   查询者的id
     * @return 查询结果列表
     */
    public ArrayList<User> searchUsers(String name, int id) {
        String sql = "select * from users where username like '%" + name + "%'";
        return doQuerySQL(sql, id);
    }

    /**
     * liu(niu)姓同学提取出的公共部分
     *
     * @param sql 执行的语句
     * @param id  功能强大，0代表普通的执行语句获取信息，负数代表获取除了密码以外的信息，整数代表某个人查询好友想要知道是不是已经是好友并对应有木有添加ta的权限
     * @return
     */
    public ArrayList<User> doQuerySQL(String sql, int id) {
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                String pwd = rs.getString("pwd");
                int userID = rs.getInt("id");
                String email = rs.getString("email");
                int permission = rs.getInt("permission");
                String signature = rs.getString("signature");
                String username = rs.getString("username");
                if (id < 0) {
                    pwd = "";
                } else if (id != 0) {
                    pwd = isFriends(userID, id) ? "1" : "0";
                    permission = -100;
                }
                users.add(new User(userID, username, pwd, email, signature, permission));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 是否好友判断，本该放在friendDao,奈何liu姓同学桀骜不驯，放荡不羁，就不要追究了
     *
     * @param userID1 他的id
     * @param userID2 她的id
     * @return 配对是否成功
     */
    private boolean isFriends(int userID1, int userID2) {
        String sql = "select * from friends where (userID1=" + userID1 + " and userID2=" + userID2
                + ") or (userID2=" + userID1 + " and userID1=" + userID2 + ")";
        Statement statement = null;
        boolean isFriends = false;
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                isFriends = true;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isFriends;

    }

    /**
     * 获取符合sql语句的所有用户，功能和上面略有重复
     *
     * @param sql 查询的语句
     * @return 用户列表
     */
    private ArrayList<User> getUsers(String sql) {
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("username");
                String pwd = rs.getString("pwd");
                int userID = rs.getInt("id");
                String email = rs.getString("email");
                String signature = rs.getString("signature");
                int permission = rs.getInt("permission");
                String lastLogin = rs.getString("lastLogin");
                users.add(new User(userID, name, pwd, email, signature, permission, lastLogin));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 获取数据库某种类型所有用户，用于管理员获取用户信息
     *
     * @param userType 查询的用户类型
     * @return 用户列表
     */
    public ArrayList<User> getAllUser(String userType) {
        String sql = "select * from users where permission=";
        switch (userType) {
            case "normal":
                sql += "1";
                break;
            case "admin":
                sql += "2";
                break;
            case "all":
                sql = "select * from users";
                break;
            default:
                sql += "-1";
                break;
        }
        return getUsers(sql);
    }

    /**
     * 删除用户
     *
     * @param id 删除的用户的id
     * @return 删除是否成功
     */
    public boolean deleteUser(int id) {
        boolean result = true;
        PreparedStatement preparedStatement = null;
        String sql = "delete from users where id = ? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            result = rows > 0;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改用户权限
     *
     * @param id            修改的用户的id
     * @param newPermission 新的权限
     * @return 修改是否成功
     */
    public boolean updateUserPermission(int id, int newPermission) {
        boolean result = true;
        PreparedStatement preparedStatement = null;
        String sql = "update users  set permission=? where id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newPermission);
            preparedStatement.setInt(2, id);
            int rows = preparedStatement.executeUpdate();
            result = rows > 0;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 封装的登录方法，在这里直接验证而不是把数据传回去，验证成功直接刷新用户最近登录时间
     *
     * @param name 用户名
     * @param pwd  密码
     * @return
     */
    public boolean login(String name, String pwd) {
        User user = getUserLiu(name);
        if (user != null && pwd.equals(user.getPwd())) {
            String sql = "update users set lastLogin=CURRENT_TIMESTAMP where id=" + user.getUserID();
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
