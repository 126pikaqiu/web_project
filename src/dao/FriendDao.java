package dao;

import bean.Collection;
import bean.Friend;
import bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/20 18:58
 */
public class FriendDao extends Dao {
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

    public boolean saveFriends(Friend friend){
        String sql2 = "select * from friends where userID1=" + friend.getId1() + " or userID2=" + friend.getId1();
        if (getFriends(sql2,0).contains(friend)) {
            return true;
        }
        String sql1 = "insert into friends (userID1,userID2) values ("
                + friend.getId1() + ", " + friend.getId2() + ")";
        return doUpdateSql(sql1);
    }

    public boolean saveFriendsAddRequest(Friend friend){
        String sql2 = "select * from friends_request where userID1=" + friend.getId1() + " and userID2=" + friend.getId2();
        if (getFriends(sql2,0).size()>0) {
            return true;
        }
        String sql1 = "insert into friends_request (userID1,userID2,result) values ("
                + friend.getId1() + ", " + friend.getId2() + ")";
        return doUpdateSql(sql1);
    }

    public boolean saveFriendsMessage(Friend friend){
        String sql = "insert into friends_chat (userID1,userID2,message) values ("
                + friend.getId1() + ", " + friend.getId2() + ", '" + friend.getMessage() + "')";
        return doUpdateSql(sql);
    }


    public boolean delete(Friend friend){
        boolean result;
        String sql = "delete from friends where (userID1="
                + friend.getId1() + " and userID2=" + friend.getId2() + ") or (userID2="
                + friend.getId1() + " and userID1=" + friend.getId2() + ")";
        result = doUpdateSql(sql);

        return result;
    }

    public boolean updateFriendRequest(Friend friend){
        String sql = "update friends_request set result=" + friend.getResult() + " where (userID1="
                    + friend.getId1() + " and userID2=" + friend.getId2() + ") or (userID2="
                    + friend.getId1() + " and userID1=" + friend.getId2() + ")";
        return doUpdateSql(sql);
    }


    ArrayList<Friend> getFriends(String sql, int type) {
        ArrayList<Friend> friends = new ArrayList<>();
        ResultSet rs=null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()){
                if(type == 0)
                    //获得好友
                    friends.add(new Friend(rs.getInt("userID1"),rs.getInt("userID2")));
                else if(type==1){
                    //获得好友添加记录
                    int userID1 = rs.getInt("userID1");
                    int userID2 = rs.getInt("userID2");
                    friends.add(new Friend(userID1,userID2,rs.getInt("result"),
                            getUserName(userID1),getUserName(userID2),rs.getString("tiemReleased")));
                }
                else
                    //获得好友聊天记录
                    friends.add(new Friend(rs.getInt("userID1"),rs.getInt("userID2"),rs.getString("message")));
            }
            statement.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return friends;
    }

    public ArrayList<User> getAllFriends(int userID) {
        String sql2 = "select * from friends where userID1=" + userID + " or userID2=" + userID;
        ArrayList<User> users = new ArrayList<>();
        getFriends(sql2,0).forEach(friend -> {
            users.add(getUser(friend.getId1() == userID?friend.getId2():friend.getId1()));
        });
        return users;
    }
    public ArrayList<Friend> getAllFriendsRequest(int userID) {
        String sql2 = "select * from friends_request where userID1=" + userID + " or userID2=" + userID;
        return getFriends(sql2,1);
    }
    public ArrayList<Friend> getAllFriendsMessage(Friend friend) {
        String sql = "select * from friends_chat where (userID1="
                + friend.getId1() + " and userID2=" + friend.getId2() + ") or (userID2="
                + friend.getId1() + " and userID1=" + friend.getId2() + ")";
        return getFriends(sql,2);
    }


    public String getUserName(int id) {
        return getUser(id).getName();
    }

    public User getUser(int id) {
        ResultSet rs=null;
        String sql = "select username from users where id=" + id;
        User user = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            if (rs.next()){
                int userID = rs.getInt("id");
                String email = rs.getString("email");
                String signature = rs.getString("signature");
                String username = rs.getString("username");
                user = new User(userID,username,"",email,signature,-100);
            }
            statement.close();
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
