package dao;

import bean.Item;
import bean.SearchResult;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/16 23:42
 */
public class ItemDao extends Dao{

    public boolean save(Item item) {
        String sql = "insert into artworks (name,img,description,video,`like`,yearOfWork,location,genre) values(?,?,?,?,?,?,?,?)";
        return manageItem(item, sql);
    }

    public ArrayList<Item> getItems(int userID) {
        ArrayList<Item> items = new ArrayList<>();
        CollectionDao collectionDao = new CollectionDao();
        collectionDao.init();
        collectionDao.getCollections(userID).forEach(e->{
            items.add(getItem(e.getItemID()));
        });
        collectionDao.destroy();
        return items;
    }

    public Item getItem(int itemID) {
        String sql = "select * from artworks where id=";
        ArrayList<Item> items = getItems(sql + itemID);
        if(items.size() > 0)
            return items.get(0);
        else
            return null;
    }

    public ArrayList<Item> getLatest(){
        String sql = "SELECT * FROM artworks Order By `timeReleased` desc limit 3";
        return getItems(sql);
    }

    public ArrayList<Item> getHottest(){
        String sql = "SELECT * FROM artworks Order By `like` desc limit 3";
        return getItems(sql);
    }

    public boolean addHot(int itemID) {
        String sql = "update artworks set `like`=`like` + 1 where id=" + itemID;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate(sql);
            System.out.println(count);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private ArrayList<Item> getItems(String sql) {
        ArrayList<Item> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String name =  rs.getString("name");
                String img = rs.getString("img");
                String description = rs.getString("description");
                String video = rs.getString("video");
                int hot = rs.getInt("like");
                int time = rs.getInt("yearOfWork");
                String location = rs.getString("location");
                String genre = rs.getString("genre");
                String timeReleased = rs.getString("timeReleased");
                items.add(new Item(id,name,img,description,video,hot,time,location,genre,timeReleased));
            }
            statement.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
    }
    public SearchResult getItemsByOrder(String searchKey, String order, int page, boolean allPage) {
        if (order.equals("hot"))
            order = " Order By `like` desc";
        else if (order.equals("name")) {
            order = " Order By `name`";
        }
        String sql = "SELECT * FROM artworks" + searchKey + order;
        System.out.println(sql);
        ArrayList<Item> totalItems = getItems(sql);
        if (allPage)
            return new SearchResult(totalItems, totalItems.size(), page);
        int start = (page - 1) * 9;
        ArrayList<Item> returnItems = new ArrayList<>();
        for (int i = start; i < start + 9; i++) {
            if (i >= totalItems.size())
                break;
            returnItems.add(totalItems.get(i));
        }
        return new SearchResult(returnItems, totalItems.size(), page);
    }


    public boolean updateItem(Item item) {
        String sql = "update artworks  set name=?,img=?,description=?,video=?,`like`=?,yearOfWork=?,location=?,genre=? where id=" + item.getId();
        return manageItem(item, sql);
    }

    public boolean deleteItem(int id) {
        boolean result = true;
        PreparedStatement preparedStatement = null;
        String sql = "delete from artworks where id = ? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            result = rows > 0;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private boolean manageItem(Item item, String sql) {
        boolean result = true;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getImg().substring(item.getImg().lastIndexOf("/")+1));
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setString(4, item.getVideo().substring(item.getVideo().lastIndexOf("/")+1));
            preparedStatement.setInt(5, item.getHot());
            preparedStatement.setInt(6, item.getTime());
            preparedStatement.setString(7, item.getLocation());
            preparedStatement.setString(8, item.getGenre());
            int rows = preparedStatement.executeUpdate();
            result = rows > 0;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
