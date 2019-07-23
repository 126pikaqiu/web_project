package dao;

import bean.Collection;
import bean.Item;
import bean.SearchResult;
import bean.User;

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

    public ArrayList<Item> getItems(int userID,int permission) {
        ArrayList<Item> items = new ArrayList<>();
        CollectionDao collectionDao = new CollectionDao();
        collectionDao.init();
        collectionDao.getCollections(userID,permission).forEach(e->{
            items.add(getItem(e.getItemID()));
        });
        collectionDao.destroy();
        return items;
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
        else{
            sql = "select * from artworks where id=248";
            items = getItems(sql);
            return items.get(0);
        }
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

    private ArrayList<Item> getRecommend(Item item, int userID, int count) {
        ArrayList<Item> items = getItems(userID);//自己的收藏夹内容
        ArrayList<Item> result = new ArrayList<>();
        int size = items.size();
        if(size > 0) {
            result.addAll(getSimilar(items.get(size - 1),count));
        }
        return result;
    }
    public ArrayList<Item> getSimilar(Item item,int count) {
        String name = item.getName();
        StringBuilder sql = new StringBuilder("select * from artworks where (1=2 ");
        for(int i = 0; i < name.length(); i++) {
            sql.append(" or name like '%").append(name.charAt(i)).append("%' or description like '%").append(name.charAt(i)).append("%'");
        }
        sql.append(" or genre='").append(item.getGenre()).append("') and id!= ").append(item.getId());
        sql.append(" Order By `timeReleased` desc limit ").append(count);
        return getItems(sql.toString());
    }


    public ArrayList<Item> getRecommends(Item item,int userID, int count) {
        FriendDao friendDao = new FriendDao();
        friendDao.init();
        ArrayList<User> friends = friendDao.getAllFriends(userID);//好友
        friendDao.destroy();
        ArrayList<Item> result = new ArrayList<>(getRecommend(item, userID, count / 3));//三分之一根据自己最近收藏进行推荐
        if( friends.size() > 0){
            User user = friends.get((int)(Math.random() * friends.size()));//随机好友
            result.addAll(getRecommend(item,user.getUserID(),count / 6));
        }
        int left = count - result.size();//剩下的根据好友的收藏夹推荐
        result.addAll(getSimilar(item, left));//一半的相似产品
        return result;
    }

}
