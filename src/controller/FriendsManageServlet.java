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
 * 用来处理好友请求的servlet类
 * doGet返回好友列表，或消息列表，或请求列表，或好友主页信息，或好友推荐列表的json字符串
 * doPost处理增删好友，发送添加好友请求，处理好友添加请求，发送消息的操作
 */
public class FriendsManageServlet extends HttpServlet {
    private FriendService friendService;

    public void init() {
        friendService = new FriendService();
        friendService.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的数据内容
        String want = req.getParameter("want");
        OutputStream outputStream = resp.getOutputStream();
        //通过session获取操作者
        User user = (User) req.getSession().getAttribute("user");
        //获取好友主页，获取两个人之间的消息需要好友的id
        String userID = req.getParameter("userID");
        if (user == null || userID == null) {
            resp.setStatus(401);
            return;
        }
        resp.setStatus(200);
        switch (want) {
            case "friends"://获取好友列表
                outputStream.write(JSON.toJSONString(friendService.getAllFriends(user.getUserID())).getBytes());
                break;
            case "messages"://获取与某个好友的聊天记录
                outputStream.write(JSON.toJSONString(friendService.getAllFriendsMessage(user.getUserID(), Integer.parseInt(userID))).getBytes());
                break;
            case "request"://获取好友添加请求的列表
                outputStream.write(JSON.toJSONString(friendService.getAllFriendsRequest(user.getUserID())).getBytes());
                break;
            case "info"://获取某个好友的主页
                outputStream.write(JSON.toJSONString(friendService.getHomePage(Integer.parseInt(userID))).getBytes());
                break;
            case "recommends"://获取好友推荐列表
                outputStream.write(JSON.toJSONString(friendService.getRecommends(user.getUserID())).getBytes());
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取操作命令
        String want = req.getParameter("order");
        //获取操作的好友的id
        int userID2 = Integer.parseInt(req.getParameter("userID"));
        //通过session获取操作者
        User user = (User) req.getSession().getAttribute("user");
        boolean result = true;
        if (user == null) {
            resp.setStatus(401);
            return;
        }
        int userID1 = user.getUserID();
        switch (want) {
            case "add_friend"://添加好友
                result = friendService.addFriend(userID1, userID2);
                break;
            case "delete_friend"://删除好友
                result = friendService.deleteFriend(userID1, userID2);
                break;
            case "add_request"://发送添加请求
                result = friendService.saveFriendsAddRequest(userID1, userID2);
                break;
            case "update_request"://同意或拒绝好友添加请求
                int requestResult = Integer.parseInt(req.getParameter("result"));
                if (requestResult == 1) {
                    friendService.addFriend(userID1, userID2);
                    System.out.println("add friends");
                }
                result = friendService.updateFriendsRequest(userID1, userID2, requestResult);
                System.out.println(result);
                break;
            case "send_message"://发送消息
                String message = req.getParameter("message");
                result = friendService.saveMessage(userID1, userID2, message);
                break;
        }
        if (result) {
            resp.setStatus(200);
        } else {
            resp.setStatus(401);
        }
    }


}
