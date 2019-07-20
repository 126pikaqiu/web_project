package dao;

import bean.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/15 16:06
 */
public class AccountDao extends Dao {
    private boolean manageUser(User user, String sql) {
        boolean result = true;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getSignature());
            preparedStatement.setInt(4, user.getUserID());
            preparedStatement.setInt(5,user.getPermission());
            int rows = preparedStatement.executeUpdate();
            result = rows > 0;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveUser(User user) {
        if (getUser(user.getName()) != null) {
            return false;
        }
        String sql = "insert into users (username,email,pwd,signature,permission) values(?,?,?,?,?)";
        return manageUser(user, sql);
    }

    public boolean updateUser(User user) {
        String sql = "update users  set username=?,email=?,signature=?,permission=? where id=?";
        return manageUser(user, sql);
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

    private ArrayList<User> getUsers(String sql) {
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String pwd = rs.getString("pwd");
                int userID = rs.getInt("id");
                String email = rs.getString("email");
                int permission = rs.getInt("permission");
                users.add(new User(userID, name, pwd, email, permission));
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
        if (userType.equals("normal"))
            sql += "1";
        else if (userType.equals("admin"))
            sql += "2";
        return getUsers(sql);
    }
}
