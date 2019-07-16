package dao;

import java.sql.*;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/15 16:06
 */
public class AccountDao {
    private Connection connection;

    public void init(){
        connection = getConnection();
    }

    private Connection getConnection(){
        String jdbcUrl = "jdbc:mysql://111.231.218.101:3306/users";
        String user = "abc";
        String pwd = "I6sKajCUQAmsg1Q4";
        String diverClass = "com.mysql.jdbc.Driver";
        Connection  connection = null;
        try {
            Class.forName(diverClass);
            connection = DriverManager.getConnection(jdbcUrl, user, pwd);
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        } finally {
            connection = null;
        }
        return connection;

    }

    public void destroy() {
        if (connection != null) {
            connection = null;
        }
    }

    public boolean save(String name, String pwd){
        return true;
    }

    public String getPwd(String name){
        String sql = "select * from users where username=?";
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        String pwd = "";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                pwd = rs.getString("pwd");
            }
            preparedStatement.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pwd;
    }

    public int getPermission(String name) {
        ResultSet rs=null;
        PreparedStatement preparedStatement = null;
        String sql = "select * from users where username=?";
        int permission = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                permission = rs.getInt("permission");
            }
            preparedStatement.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return permission;
    }
}
