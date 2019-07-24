package dao;

import bean.Collection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 和数据库中的收藏品table打交道，完成service的请求
 */
public class CollectionDao extends Dao {
    /**
     * 新加入收藏品
     *
     * @param collection 收藏品的信息
     * @return 收藏是否成功
     */
    public boolean save(Collection collection) {
        boolean result = true;
        if (getCollections(collection.getUserID()).contains(collection)) {
            return result;
        } else {
            String sql = "insert into collections (userID,itemID) values ("
                    + collection.getUserID() + ", " + collection.getItemID() + ")";
            result = doUpdateSql(sql);
        }
        return result;
    }

    /**
     * 删除收藏品
     *
     * @param collection 收藏品的信息
     * @return 删除是否成功
     */
    public boolean delete(Collection collection) {
        String sql = "delete from collections where userID="
                + collection.getUserID() + " and itemID=" + collection.getItemID() + "";
        return doUpdateSql(sql);
    }

    /**
     * 获取收藏品列表
     *
     * @param userID 查询对象的id
     * @return 收藏品列表
     */
    ArrayList<Collection> getCollections(int userID) {
        String sql = "select * from collections where userID=" + userID;
        return doQeurySql(sql);
    }

    /**
     * 获取某个用户的公开收藏品列表
     *
     * @param userID     用户的id
     * @param permission 某种权限（公开or私有）的收藏品
     * @return
     */
    ArrayList<Collection> getCollections(int userID, int permission) {
        String sql = "select * from collections where userID=" + userID + " and permission=" + permission;
        return doQeurySql(sql);
    }

    /**
     * 执行查询的sql语句，是公共部分的提取
     *
     * @param sql 执行的语句
     * @return 查询的收藏品列表
     */
    private ArrayList<Collection> doQeurySql(String sql) {
        ArrayList<Collection> collections = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                collections.add(new Collection(rs.getInt("userID"), rs.getInt("itemID")));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collections;
    }

    /**
     * 执行更新的sql语句，公共部分的提取
     *
     * @param sql 执行的语句
     * @return 更新是否成功
     */
    public boolean doUpdateSql(String sql) {
        boolean result = true;
        try {
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate(sql);
            statement.close();
            result = count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改收藏品公开权限
     *
     * @param collection 操作的收藏品
     * @return 更新是否成功
     */
    public boolean update(Collection collection) {
        String sql = "update collections set `permission`=1-`permission` where userID=" + collection.getUserID()
                + " and itemID=" + collection.getItemID();
        return doUpdateSql(sql);
    }

    /**
     * 查询某人某件收藏品的权限
     *
     * @param collection 目标收藏品
     * @return 改用户的改藏品是否公开
     */
    public boolean getPermission(Collection collection) {
        String sql = "select * from  collections where permission=1 && userID=" + collection.getUserID()
                + " and itemID=" + collection.getItemID();
        return doQeurySql(sql).size() > 0;
    }

}
