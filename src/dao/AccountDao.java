package dao;

import bean.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/15 16:06
 */
public class AccountDao extends Dao {

    public User getUser(String name){
        String sql = "select * from users where username='" + name + "'";
        ArrayList<User> users = doQuerySQL(sql,0);
        return users.size() > 0?users.get(0):null;
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

    public ArrayList<User> getUsers(String name,int id) {
        String sql = "select * from users where username like%'" + name + "'%";
        return doQuerySQL(sql,id);
    }

    public ArrayList<User> doQuerySQL(String sql,int id) {
        ResultSet rs=null;
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()){
                String pwd=rs.getString("pwd");
                int userID = rs.getInt("id");
                if(id < 0) {
                    pwd = "";
                }
                else if(id != 0) {
                    pwd = isFriends(userID,id)?"1":"0";
                }
                String email = rs.getString("email");
                int permission = rs.getInt("permission");
                String signature = rs.getString("signature");
                String username = rs.getString("username");
                users.add(new User(userID,username,pwd,email,signature,permission));
            }
            statement.close();
            rs.close();
        }catch (SQLException e){
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
            isFriends = statement.executeQuery(sql).getFetchSize() > 0;
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isFriends;

    }


}
