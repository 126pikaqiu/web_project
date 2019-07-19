package dao;

import bean.Item;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/16 23:42
 */
public class ItemDao extends Dao {

    public boolean save(Item item) {
        return true;
    }

    public ArrayList<Item> getItems(int userID) {
        ArrayList<Item> items = new ArrayList<>();
        CollectionDao collectionDao = new CollectionDao();
        collectionDao.init();
        collectionDao.getCollections(userID).forEach(e -> {
            items.add(getItem(e.getItemID()));
        });
        collectionDao.destroy();
        return items;
    }

    public Item getItem(int itemID) {
        String sql = "select * from artworks where id=";
        ArrayList<Item> items = getItems(sql + itemID);
        if (items.size() > 0)
            return items.get(0);
        else
            return null;
    }

    public ArrayList<Item> getLatest() {
        String sql = "SELECT * FROM artworks Order By `timeReleased` desc limit 3";
        return getItems(sql);
    }

    public ArrayList<Item> getHottest() {
        String sql = "SELECT * FROM artworks Order By `like` desc limit 3";
        return getItems(sql);
    }

    private ArrayList<Item> getItems(String sql) {
        ArrayList<Item> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String img = rs.getString("img");
                String description = rs.getString("description");
                String video = rs.getString("video");
                int hot = rs.getInt("like");
                String time = rs.getString("yearOfWork");
                String location = rs.getString("location");
                String genre = rs.getString("genre");
                items.add(new Item(id, name, img, description, video, hot, time, location, genre));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public ArrayList<Item> getItemsByOrder(String searchKey, String order, int page) {
        if (order.equals("hot"))
            order = " Order By `like` desc";
        else if (order.equals("name")) {
            order = " Order By `name`";
        }
        String sql = "SELECT * FROM artworks" + searchKey + order;
        System.out.println(sql);
        ArrayList<Item> totalItems = getItems(sql);
        int start = (page - 1) * 9;
        ArrayList<Item> returnItems = new ArrayList<>();
        for (int i = start; i < start + 9; i++) {
            if (i >= totalItems.size())
                break;
            returnItems.add(totalItems.get(i));
        }
        return returnItems;
    }


}
