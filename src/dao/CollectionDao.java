package dao;

import bean.Collection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/17 23:50
 */
public class CollectionDao extends Dao{

    public boolean save(Collection collection){
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

    public boolean delete(Collection collection){
        String sql = "delete from collections where userID="
                + collection.getUserID() + " and itemID=" + collection.getItemID() + "";
        return doUpdateSql(sql);
    }

    ArrayList<Collection> getCollections(int userID) {
        String sql = "select * from collections where userID=" + userID;
        return doQeurySql(sql);
    }
    ArrayList<Collection> getCollections(int userID,int permission) {
        String sql = "select * from collections where userID=" + userID + " and permission=" + permission;
        return doQeurySql(sql);
    }
    private ArrayList<Collection> doQeurySql(String sql) {
        ArrayList<Collection> collections = new ArrayList<>();
        ResultSet rs=null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()){
                collections.add(new Collection(rs.getInt("userID"), rs.getInt("itemID")));
            }
            statement.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return collections;
    }
    public boolean doUpdateSql(String sql){
        boolean result = true;
        try {
            Statement statement = connection.createStatement();
            int count = statement.executeUpdate(sql);
            statement.close();
            result = count > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean update(Collection collection){
        String sql = "update collections set `permission`=1-`permission` where userID=" + collection.getUserID()
                + " and itemID=" + collection.getItemID();
        return doUpdateSql(sql);
    }

    public boolean getPermission(Collection collection) {
        String sql = "select * from  collections where permission=1 && userID=" + collection.getUserID()
                + " and itemID=" + collection.getItemID();
        return doQeurySql(sql).size()>0;
    }

}
