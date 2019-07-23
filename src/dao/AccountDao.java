package dao;

import bean.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/15 16:06
 */
public class AccountDao extends Dao {

    public User getUserLiu(String name) {
        String sql = "select * from users where username='" + name + "'";
        ArrayList<User> users = doQuerySQL(sql, 0);
        return users.size() > 0 ? users.get(0) : null;
    }

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

    public boolean updateUser(User user) {
        User user1 = getUser(user.getName());
        if (user1 != null && user1.getUserID() != user.getUserID()) {
            return false;
        }
        String sql = "update users  set username=?,email=?,signature=?,permission=? where id=?";
        return manageUser(user, sql);
    }

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

    public boolean saveUser(User user) {
        if (getUserLiu(user.getName()) != null) {
            return false;
        }
        String sql = "insert into users (username,email,signature,permission,pwd) values(?,?,?,?,?)";
        return manageUser(user, sql);
    }

    public ArrayList<User> searchUsers(String name, int id) {
        String sql = "select * from users where username like '%" + name + "%'";
        return doQuerySQL(sql, id);
    }

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
                users.add(new User(userID, name, pwd, email, signature, permission,lastLogin));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

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

    public boolean login(String name, String pwd) {
        User user = getUserLiu(name);
        if (pwd.equals(user.getPwd())){
            String sql = "update users set lastLogin=CURRENT_TIMESTAMP where id="+user.getUserID();
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
