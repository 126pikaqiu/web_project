package dao;

import service.Collection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/17 23:50
 */
public class CollectionDao {
    private Connection connection;
    public void init(){
        connection = JDBCUtil.getConnection();
    }

    void destroy() {
        if (connection!= null) {
            connection= null;
        }
    }

    public boolean save(Collection collection){
        boolean result = true;
        if (getCollections(collection.getUserID()).contains(collection)) {
            return result;
        } else {
            String sql = "insert into collections (userID,itemID) values ("
                    + collection.getUserID() + ", " + collection.getItemID() + ")";
            try {
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(sql);
                statement.close();
                result = count > 0;
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean delete(Collection collection){
        boolean result = true;
        if (!getCollections(collection.getUserID()).contains(collection)) {
            return result;
        } else {
            String sql = "delete from collections where userID="
                    + collection.getUserID() + " and itemID=" + collection.getItemID() + "";
            try {
                Statement statement = connection.createStatement();
                int count = statement.executeUpdate(sql);
                result = count > 0;
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    ArrayList<Collection> getCollections(int userID) {
        ArrayList<Collection> collections = new ArrayList<>();
        String sql = "select * from collections where userID=" + userID;
        ResultSet rs=null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()){
                collections.add(new Collection(userID, rs.getInt("itemID")));
            }
            statement.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return collections;
    }

}
