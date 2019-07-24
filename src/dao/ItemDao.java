package dao;

import bean.Collection;
import bean.Item;
import bean.SearchResult;
import bean.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * 和数据库中的展品table打交道，完成service的请求
 */
public class ItemDao extends Dao {
    /**
     * 新添展品
     *
     * @param item 添加的展品
     * @return 添加是否成功
     */
    public boolean save(Item item) {
        String sql = "insert into artworks (name,img,description,video,`like`,yearOfWork,location,genre) values(?,?,?,?,?,?,?,?)";
        return manageItem(item, sql);
    }

    /**
     * 获取收藏品列表（我也不知道为啥在这个dao里面）
     *
     * @param userID     用户的id
     * @param permission 展品的权限
     * @return
     */
    public ArrayList<Item> getItems(int userID, int permission) {
        ArrayList<Item> items = new ArrayList<>();
        CollectionDao collectionDao = new CollectionDao();
        collectionDao.init();
        collectionDao.getCollections(userID, permission).forEach(e -> {
            items.add(getItem(e.getItemID()));
        });
        collectionDao.destroy();
        return items;
    }

    /**
     * 获取收藏品列表
     *
     * @param userID 用户的id
     * @return
     */
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

    /**
     * 获取某件展品
     *
     * @param itemID 展品的id
     * @return
     */
    public Item getItem(int itemID) {
        String sql = "select * from artworks where id=";
        ArrayList<Item> items = getItems(sql + itemID);
        if (items.size() > 0)
            return items.get(0);
        else {
            sql = "select * from artworks where id=248";
            items = getItems(sql);
            return items.get(0);
        }
    }

    /**
     * 获取最多三个最新的展品
     *
     * @return 展品列表
     */
    public ArrayList<Item> getLatest() {
        String sql = "SELECT * FROM artworks Order By `timeReleased` desc limit 3";
        return getItems(sql);
    }

    /**
     * 获取最多三个最热的展品
     *
     * @return 展品列表
     */
    public ArrayList<Item> getHottest() {
        String sql = "SELECT * FROM artworks Order By `like` desc limit 3";
        return getItems(sql);
    }

    /**
     * 点赞，增加展品热度
     *
     * @param itemID 展品的id
     * @return
     */
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

    /**
     * 执行查询展品的sql语句
     *
     * @param sql 查询的语句
     * @return 展品列表
     */
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
                int time = rs.getInt("yearOfWork");
                String location = rs.getString("location");
                String genre = rs.getString("genre");
                String timeReleased = rs.getString("timeReleased");
                items.add(new Item(id, name, img, description, video, hot, time, location, genre, timeReleased));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * 根据某种排序获得搜索的展品
     *
     * @param searchKey 查询条件的sql语句
     * @param order     查询结果的顺序
     * @param page      根据page判断返回的展品数量
     * @param allPage   是否要求获取全部数据
     * @return 展品列表
     */
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

    /**
     * 更新展品的信息
     *
     * @param item 更新的展品
     * @return 更新是否成功
     */
    public boolean updateItem(Item item) {
        String sql = "update artworks  set name=?,img=?,description=?,video=?,`like`=?,yearOfWork=?,location=?,genre=? where id=" + item.getId();
        return manageItem(item, sql);
    }

    /**
     * 删除展品
     *
     * @param id 展品的id
     * @return 删除是否成功
     */
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

    /**
     * 管理展品，添加和更新展品提取的公共方法
     *
     * @param item 管理的展品
     * @param sql  执行的语句
     * @return 操作的结果
     */
    private boolean manageItem(Item item, String sql) {
        boolean result = true;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getImg().substring(item.getImg().lastIndexOf("/") + 1));
            preparedStatement.setString(3, item.getDescription());
            preparedStatement.setString(4, item.getVideo().substring(item.getVideo().lastIndexOf("/") + 1));
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

    /**
     * 获取推荐的展品
     *
     * @param item   我也不知道干啥的参数
     * @param userID 用户的id
     * @param count  限制的数量
     * @return
     */
    private ArrayList<Item> getRecommend(Item item, int userID, int count) {
        ArrayList<Item> items = getItems(userID);//自己的收藏夹内容
        ArrayList<Item> result = new ArrayList<>();
        int size = items.size();
        if (size > 0) {
            result.addAll(getSimilar(items.get(size - 1), count));
        }
        return result;
    }

    /**
     * 获取相似的展品
     *
     * @param item  目标展品
     * @param count 限制的数量
     * @return 展品列表
     */
    public ArrayList<Item> getSimilar(Item item, int count) {
        String name = item.getName();
        StringBuilder sql = new StringBuilder("select * from artworks where (1=2 ");
        //模糊匹配
        for (int i = 0; i < name.length(); i++) {
            sql.append(" or name like '%").append(name.charAt(i)).append("%' or description like '%").append(name.charAt(i)).append("%'");
        }
        sql.append(" or genre='").append(item.getGenre()).append("') and id!= ").append(item.getId());//排除自己
        sql.append(" Order By `timeReleased` desc limit ").append(count);
        return getItems(sql.toString());
    }

    /**
     * 获得推荐的展品
     *
     * @param item   推荐的标准
     * @param userID 用户的id
     * @param count  限制的数量
     * @return 展品列表
     */
    public ArrayList<Item> getRecommends(Item item, int userID, int count) {
        FriendDao friendDao = new FriendDao();
        friendDao.init();
        ArrayList<User> friends = friendDao.getAllFriends(userID);//好友
        friendDao.destroy();
        ArrayList<Item> result = new ArrayList<>(getRecommend(item, userID, count / 3));//三分之一根据自己最近收藏进行推荐
        if (friends.size() > 0) {
            User user = friends.get((int) (Math.random() * friends.size()));//随机好友
            result.addAll(getRecommend(item, user.getUserID(), count / 6));
        }
        int left = count - result.size();//剩下的根据好友的收藏夹推荐
        result.addAll(getSimilar(item, left));//一半的相似产品
        return result;
    }

}
