package dao;

import bean.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 和数据库中的好友table打交道，完成service的请求
 */
public class FriendDao extends Dao {
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
     * 添加好友
     *
     * @param friend 添加好友的两个人的信息
     * @return 添加是否成功
     */
    public boolean saveFriends(Friend friend) {
        String sql2 = "select * from friends where userID1=" + friend.getId1() + " or userID2=" + friend.getId1();
        if (getFriends(sql2, 0).contains(friend)) {
            return true;
        }
        String sql1 = "insert into friends (userID1,userID2) values ("
                + friend.getId1() + ", " + friend.getId2() + ")";
        return doUpdateSql(sql1);
    }

    /**
     * 保存添加好友的信息
     *
     * @param friend 添加好友的两个人的信息
     * @return 保存是否成功
     */
    public boolean saveFriendsAddRequest(Friend friend) {
        String sql1 = "insert into friends_request (userID1,userID2,result) values ("
                + friend.getId1() + ", " + friend.getId2() + "," + friend.getResult() + ")";
        return doUpdateSql(sql1);
    }

    /**
     * 保存聊天的信息
     *
     * @param friend 互为好友的两个人的信息
     * @return 保存是否成功
     */
    public boolean saveFriendsMessage(Friend friend) {
        String sql = "insert into friends_chat (userID1,userID2,message) values ("
                + friend.getId1() + ", " + friend.getId2() + ", '" + friend.getMessage() + "')";
        return doUpdateSql(sql);
    }

    /**
     * 删除好友
     *
     * @param friend 互为好友的两个人的信息
     * @return 删除是否成功
     */
    public boolean delete(Friend friend) {
        boolean result;
        String sql = "delete from friends where (userID1="
                + friend.getId1() + " and userID2=" + friend.getId2() + ") or (userID2="
                + friend.getId1() + " and userID1=" + friend.getId2() + ")";
        result = doUpdateSql(sql);

        return result;
    }

    /**
     * 更新好友添加请求状态（同意或拒绝）
     *
     * @param friend 添加请求中两个人的信息
     * @return 更新是否成功
     */
    public boolean updateFriendRequest(Friend friend) {
        String sql = "update friends_request set result=" + friend.getResult() + " where ((userID1="
                + friend.getId1() + " and userID2=" + friend.getId2() + ") or (userID2="
                + friend.getId1() + " and userID1=" + friend.getId2() + ")) and result=0 ";
        System.out.println(sql);
        return doUpdateSql(sql);
    }

    /**
     * 根据type的类型执行sql的查询功能，公共部分的提取
     *
     * @param sql  查询的语句
     * @param type 插叙的内容
     * @return
     */
    ArrayList<Friend> getFriends(String sql, int type) {
        ArrayList<Friend> friends = new ArrayList<>();
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                if (type == 0)
                    //获得好友
                    friends.add(new Friend(rs.getInt("userID1"), rs.getInt("userID2")));
                else if (type == 1) {
                    //获得好友添加记录
                    int userID1 = rs.getInt("userID1");
                    int userID2 = rs.getInt("userID2");
                    friends.add(new Friend(userID1, userID2, rs.getInt("result"),
                            getUserName(userID1), getUserName(userID2), rs.getString("timeReleased")));
                } else
                    //获得好友聊天记录
                    friends.add(new Friend(rs.getInt("userID1"), rs.getInt("userID2"), rs.getString("message")));
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends;
    }

    /**
     * 获得所有好友的信息
     *
     * @param userID 查询该id下的所有好友
     * @return 好友列表
     */
    public ArrayList<User> getAllFriends(int userID) {
        String sql2 = "select * from friends where userID1=" + userID + " or userID2=" + userID;
        ArrayList<User> users = new ArrayList<>();
        getFriends(sql2, 0).forEach(friend -> {
            users.add(getUser(friend.getId1() == userID ? friend.getId2() : friend.getId1()));
        });
        return users;
    }

    /**
     * 查询所有好友添加请求
     *
     * @param userID 查询该id下的所有好友添加请求
     * @return 查询结果
     */
    public ArrayList<Object> getAllFriendsRequest(int userID) {
        String sql2 = "select * from friends_request where userID1=" + userID + " or userID2=" + userID;
        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<Friend> friends = getFriends(sql2, 1);
        friends.forEach(friend -> {
            objects.add(friend);
            objects.add(getUser(friend.getId1()));
            objects.add(getUser(friend.getId2()));
        });
        return objects;
    }

    /**
     * 查询两个人所有的聊天记录
     *
     * @param friend 互为好友的两个人的信息
     * @return 所有聊天记录
     */
    public ArrayList<Friend> getAllFriendsMessage(Friend friend) {
        String sql = "select * from friends_chat where (userID1="
                + friend.getId1() + " and userID2=" + friend.getId2() + ") or (userID2="
                + friend.getId1() + " and userID1=" + friend.getId2() + ")";
        System.out.println(sql);
        return getFriends(sql, 2);
    }

    /**
     * 获取某个用户的名字，private方法
     *
     * @param id 查询的id
     * @return 用户名字
     */
    private String getUserName(int id) {
        return getUser(id).getName();
    }

    /**
     * 这里不得不再夸一夸liu姓同学，这个方法应该是直接从AccoutDao抄过来的，够懒！
     *
     * @param id
     * @return
     */
    private User getUser(int id) {
        ResultSet rs = null;
        String sql = "select * from users where id=" + id;
        User user = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                int userID = rs.getInt("id");
                String email = rs.getString("email");
                String signature = rs.getString("signature");
                String username = rs.getString("username");
                user = new User(userID, username, "", email, signature, -100);
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 获得用户的个人主页
     *
     * @param id 用户的id
     * @return 个人主页
     */
    public HomePage getHomePage(int id) {
        User user = getUser(id);
        ItemDao itemDao = new ItemDao();
        itemDao.init();
        List<Item> items = itemDao.getItems(id, 1);
        return new HomePage(user, items);
    }

    /**
     * 获得好友推荐
     *
     * @param userID 用户的id
     * @return 推荐的用户列表
     */
    public ArrayList<User> getRecommends(int userID) {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<User> friends = getAllFriends(userID);
        User user = getUser(userID);
        if (friends.size() > 0) {
            if (friends.size() == 1) {
                User commonFriend = commonFriend(friends.get(0), user, null);
                if (commonFriend != null) {
                    users.add(commonFriend);
                }
            } else {
                User pre = friends.get(0);
                for (int index = 1; index < friends.size() && users.size() < 4; index++) {
                    User commonFriend = commonFriend(pre, friends.get(index), user);
                    if (commonFriend != null) {
                        users.add(commonFriend);
                    }
                    pre = friends.get(index);
                }
            }
        }
        int size = users.size();
        if (size < 4) {
            users.addAll(commonCollection(user, 4 - size));
        }
        return users;
    }

    /**
     * 获取一个共同好友
     *
     * @param user1 用户A
     * @param user2 用户B
     * @param user  某个用户C
     * @return 如果A和B有公共好友C，把跟C满足equal关系的用户被返回
     */
    private User commonFriend(User user1, User user2, User user) { //共同好友
        ArrayList<User> friends1 = getAllFriends(user1.getUserID());
        ArrayList<User> friends2 = getAllFriends(user2.getUserID());
        for (User user3 : friends1) {
            if (!user3.equals(user) && friends2.contains(user3)) {
                return user3;
            }
        }
        return null;
    }

    /**
     * 获取公共收藏品的列表，和上面的逻辑很类似
     *
     * @param user  目标用户
     * @param count 限制数量
     * @return 最多count数量的公共收藏品列表
     */
    private ArrayList<User> commonCollection(User user, int count) { //有共同的收藏
        CollectionDao collectionDao = new CollectionDao();
        collectionDao.init();
        ArrayList<Collection> collections = collectionDao.getCollections(user.getUserID());
        collectionDao.destroy();
        ArrayList<User> users = new ArrayList<>();
        if (collections.size() > 0) {
            String sql = "select * from collections where itemID=";
            for (int i = 0; i < collections.size() && i < count; i++) {
                doQeurySql(sql + collections.get(i).getItemID()).forEach(collection -> {
                    User user1 = getUser(collection.getUserID());
                    if (!user1.equals(user)) {
                        users.add(user1);
                    }
                });
            }
        }
        return users;
    }

    /**
     * 执行查询公共收藏的sql语句
     *
     * @param sql 执行的语句
     * @return 收藏品列表
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
}
