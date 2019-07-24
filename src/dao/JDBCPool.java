package dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Vector;
import java.util.Properties;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 * 连接池，管理连接，预建立10个连接，超过了的请求无法连接，某种程度上加快了速度，不用现查现连
 */
public class JDBCPool implements DataSource {

    /**
     * @Field: Connections
     * 使用Vector来存放数据库链接，
     * Vector具备线程安全性
     */
    private static Vector Connections = new Vector();

    static {
        //在静态代码块中加载db.properties数据库配置文件
        InputStream in = JDBCPool.class.getClassLoader().getResourceAsStream("db.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            //数据库连接池的初始化连接数大小
            int jdbcPoolInitSize = Integer.parseInt(prop.getProperty("jdbcPoolInitSize"));
            //加载数据库驱动
            Class.forName(driver);
            for (int i = 0; i < jdbcPoolInitSize; i++) {
                Connection conn = DriverManager.getConnection(url, username, password);
                //将获取到的数据库连接加入到Connections集合中，Connections此时就是一个存放了数据库连接的连接池
                Connections.addElement(conn);
            }

        } catch (SQLException e) {
            try {
                throw new SQLException();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    /* 获取数据库连接
     * @see javax.sql.DataSource#getConnection()
     */
    @Override
    public Connection getConnection() {
        //如果数据库连接池中的连接对象的个数大于0
        if (Connections.size() > 0) {
            //从Connections集合中获取一个数据库连接
            final Connection conn = (Connection) Connections.remove(0);
            //返回Connection对象的代理对象
            return (Connection) Proxy.newProxyInstance(JDBCPool.class.getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args)
                        throws Throwable {
                    if (!method.getName().equals("close")) {
                        return method.invoke(conn, args);
                    } else {
                        //如果调用的是Connection对象的close方法，就把conn还给数据库连接池
                        Connections.addElement(conn);
                        return null;
                    }
                }
            });
        } else {
            throw new RuntimeException("对不起，数据库忙");
        }
    }

    @Override
    public Connection getConnection(String username, String password)
            throws SQLException {
        return null;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
