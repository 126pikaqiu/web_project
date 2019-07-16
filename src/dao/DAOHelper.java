package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/17 0:34
 */
public class DAOHelper {
    public static Connection getConnection(){
        String jdbcUrl = "jdbc:mysql://111.231.218.101:3306/items";
        String user = "abc";
        String pwd = "I6sKajCUQAmsg1Q4";
        String diverClass = "com.mysql.jdbc.Driver";
        Connection connection = null;
        try {
            Class.forName(diverClass);
            connection = DriverManager.getConnection(jdbcUrl, user, pwd);
        } catch (SQLException | ClassNotFoundException  e){
            e.printStackTrace();
        }
        return connection;
    }
}
