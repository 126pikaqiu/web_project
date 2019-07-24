package dao;
import java.sql.Connection;


/**
 * 连接数据库的JDBC工具类，用连接池管理连接
 */
class JDBCUtil {
    private static JDBCPool pool = new JDBCPool();
    static Connection getConnection(){
        return pool.getConnection();
    }
    static void release(Connection conn) {
        if(conn!=null){
            try{
                //关闭Connection数据库连接对象
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
