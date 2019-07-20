package dao;

import bean.User;

import java.sql.*;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/15 16:06
 */
public class AccountDao extends Dao {

    public User getUser(String name){
        String sql = "select * from users where username=?";
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                String pwd = rs.getString("pwd");
                int userID = rs.getInt("id");
                String email = rs.getString("email");
                int permission = rs.getInt("permission");
                user = new User(userID,name,pwd,email,permission);
            }
            preparedStatement.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public boolean updateUser(User user) {
        boolean result = true;
        String sql = "update users  set username=?,email=?,signature=? where id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getSignature());
            preparedStatement.setInt(3,user.getUserID());
            int rows = preparedStatement.executeUpdate();
            result = rows > 0;
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean saveUser(User user) {
        if(getUser(user.getName()) != null) {
            return false;
        }
        boolean result = true;
        String sql = "insert into users (username,email,pwd,signature,permission) values(?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPwd());
            preparedStatement.setString(4,user.getSignature());
            preparedStatement.setInt(5,user.getPermission());
            int rows = preparedStatement.executeUpdate();
            result = rows > 0;
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

}
