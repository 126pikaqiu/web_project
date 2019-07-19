package dao;

import java.sql.Connection;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/18 13:55
 */
public abstract class Dao {
    Connection connection;

    public void init(){
        connection = JDBCUtil.getConnection();
    }

    public void destroy() {
        if (connection != null) {
            connection = null;
        }
    }
}
