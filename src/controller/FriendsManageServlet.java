package controller;
import bean.User;
import com.alibaba.fastjson.JSON;
import service.FriendService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/20 18:40
 */
public class FriendsManageServlet extends HttpServlet {
    private FriendService friendService;
    public void init(){
        friendService = new FriendService();
        friendService.init();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String want = req.getParameter("want");
        OutputStream outputStream = resp.getOutputStream();
        User user = (User) req.getSession().getAttribute("user");
        String userID = req.getParameter("userID");
        if(user == null || userID==null) {
            resp.setStatus(401);
            return;
        }
        resp.setStatus(200);
        switch(want){
            case "friends":
                outputStream.write(JSON.toJSONString(friendService.getAllFriends(user.getUserID())).getBytes());
                break;
            case "messages":
                outputStream.write(JSON.toJSONString(friendService.getAllFriendsMessage(user.getUserID(),Integer.parseInt(userID))).getBytes());
                break;
            case "request":
                outputStream.write(JSON.toJSONString(friendService.getAllFriendsRequest(user.getUserID())).getBytes());
                break;
            case "info":
                outputStream.write(JSON.toJSONString(friendService.getUser(Integer.parseInt(userID))).getBytes());
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String want = req.getParameter("order");
        int userID2 = Integer.parseInt(req.getParameter("userID"));
        User user = (User) req.getSession().getAttribute("user");
        boolean result=true;
        if(user == null) {
            resp.setStatus(401);
            return;
        }
        int userID1 = user.getUserID();

        switch(want){
            case "add_friend":
                result=friendService.addFriend(userID1,userID2);
                break;
            case "delete_friend":
                result=friendService.deleteFriend(userID1,userID2);
                break;
            case "add_request":
                result=friendService.saveFriendsAddRequest(userID1,userID2);
                break;
            case "update_request":
                int requestResult =Integer.parseInt(req.getParameter("result"));
                if(requestResult == 1) {
                    friendService.addFriend(userID1,userID2);
                    System.out.println("add friends");
                }
                result=friendService.updateFriendsRequest(userID1,userID2,requestResult);
                System.out.println(result);
                break;
            case "send_message":
                String message=req.getParameter("message");
                result=friendService.saveMessage(userID1,userID2,message);
                break;
        }
        if(result){
            resp.setStatus(200);
        } else {
            resp.setStatus(401);
        }
    }


}
