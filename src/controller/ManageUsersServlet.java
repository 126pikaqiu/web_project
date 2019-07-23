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
 * @ Author     ：Wang Shang.
 * @ Date       ：Created in 13:39 2019/7/20
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public class ManageUsersServlet extends HttpServlet {
    private AccountService accountService;

    public void init() {
        accountService = new AccountService();
        accountService.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        String operation = req.getParameter("operation");
        switch (operation) {
            case "add":
                String name = req.getParameter("username");
                String pwd = req.getParameter("pwd");
                String email = req.getParameter("email");
                String signature = req.getParameter("signature");
                int permission = Integer.parseInt(req.getParameter("permission"));
                if (name != null && pwd != null && accountService.register(new User(name, pwd, email,signature, permission))) {
                    resp.setStatus(200);
                } else {
                    resp.setStatus(401);
                }
                break;
            case "change":
                int permission2 = Integer.parseInt(req.getParameter("permission"));
                int newPermission = 3 - permission2;
                int id = Integer.parseInt(req.getParameter("id"));
                if (accountService.updateUserPermission(id,newPermission)) {
                    resp.setStatus(200);
                } else {
                    resp.setStatus(401);
                }
                break;
            case "delete":
                int id2 = Integer.parseInt(req.getParameter("id"));
                if (accountService.deleteUser(id2)) {
                    resp.setStatus(200);
                } else {
                    resp.setStatus(401);
                }
                break;
        }
    }
}
