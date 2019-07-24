package dao;

import java.sql.Connection;

/**
 * 所有Dao的父类，与JDBC的连接在这里实现
 *
 */
public abstract class Dao {
    Connection connection;

    /**
     * 初始化，进行连接
     */
    public void init() {
        connection = JDBCUtil.getConnection();
    }

    /**
     * 断开连接
     */
    public void destroy() {
        JDBCUtil.release(connection);
    }
}
