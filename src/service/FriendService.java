package service;

import bean.Friend;
import bean.User;
import dao.FriendDao;

import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/20 18:43
 */
public class FriendService {
    private FriendDao friendDao;
    public void init(){
        friendDao = new FriendDao();
        friendDao.init();
    }
    public ArrayList<User> getAllFriends(int userID) {
        return friendDao.getAllFriends(userID);
    }
    public ArrayList<Object> getAllFriendsRequest(int userID) {
        return friendDao.getAllFriendsRequest(userID);
    }
    public ArrayList<Friend> getAllFriendsMessage(int userID1,int userID2) {
        return friendDao.getAllFriendsMessage(new Friend(userID1,userID2));
    }
    public boolean deleteFriend(int userID1, int userID2){
        return friendDao.delete(new Friend(userID1,userID2));
    }
    public boolean addFriend(int userID1, int userID2){
        return friendDao.saveFriends(new Friend(userID1,userID2));
    }
    public boolean updateFriendsRequest(int userID1, int userID2,int result){
        return friendDao.updateFriendRequest(new Friend(userID1,userID2,result));
    }
    public boolean saveMessage(int userID1, int userID2,String message) {
        return friendDao.saveFriendsMessage(new Friend(userID1,userID2,message));
    }
    public boolean saveFriendsAddRequest(int userID1, int userID2){
        return friendDao.saveFriendsAddRequest((new Friend(userID1,userID2)));
    }
    public User getUser(int userID) {
        return friendDao.getUser(userID);
    }

}
