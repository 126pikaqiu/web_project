package controller;

import bean.User;
import com.alibaba.fastjson.JSON;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 管理员管理用户的servlet类
 * doGet获取所有用户的信息
 * doPost对用户进行增删改（权限）的操作
 *
 */
public class ManageUsersServlet extends HttpServlet {
    private AccountService accountService;

    public void init() {
        accountService = new AccountService();
        accountService.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer user_permission = (Integer) req.getSession().getAttribute("permission");
        if(user_permission == null || user_permission != 2){
            resp.setStatus(401);
            return;
        }
        String userType = req.getParameter("userType");
        ArrayList<User> users = accountService.getAllUsers(userType);
        if (users != null) {
            resp.setStatus(200);
            OutputStream out = resp.getOutputStream();
            out.write(JSON.toJSONString(users).getBytes(StandardCharsets.UTF_8));
        } else {
            resp.setStatus(400);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer user_permission = (Integer) req.getSession().getAttribute("permission");
        if(user_permission == null || user_permission != 2){
            resp.setStatus(401);
            return;
        }
        String operation = req.getParameter("operation");
        User user;
        switch (operation) {
            case "add"://增
                String name = req.getParameter("username");
                String pwd = req.getParameter("pwd");
                String email = req.getParameter("email");
                String signature = req.getParameter("signature");
                int permission = Integer.parseInt(req.getParameter("permission"));
                if (name != null && pwd != null && accountService.register(new User(name, pwd, email, signature, permission))) {
                    resp.setStatus(200);
                } else {
                    resp.setStatus(401);
                }
                break;
            case "change"://修改权限
                int permission2 = Integer.parseInt(req.getParameter("permission"));
                int newPermission = 3 - permission2;//转换权限
                int id = Integer.parseInt(req.getParameter("id"));
                //不能修改自己的权限
                user = (User) req.getSession().getAttribute("user");
                if (user.getUserID() == id) {
                    resp.setStatus(402);
                } else if (accountService.updateUserPermission(id, newPermission)) {
                    resp.setStatus(200);
                } else {
                    resp.setStatus(401);
                }
                break;
            case "delete"://删
                int id2 = Integer.parseInt(req.getParameter("id"));
                //不能删除自己
                user = (User) req.getSession().getAttribute("user");
                if (user.getUserID() == id2) {
                    resp.setStatus(402);
                } else if (accountService.deleteUser(id2)) {
                    resp.setStatus(200);
                } else {
                    resp.setStatus(401);
                }
                break;
        }
    }
}
