package dao;

import service.User;

import java.sql.*;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/15 16:06
 */
public class AccountDao {
    private Connection connection;

    public void init(){
        connection = JDBCUtil.getConnection();
    }

    public void destroy() {
        if (connection != null) {
            connection = null;
        }
    }

    public boolean save(String name, String pwd){
        return true;
    }

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
}
