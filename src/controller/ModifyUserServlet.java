package controller;

import bean.User;
import com.alibaba.fastjson.JSON;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 修改自己个人信息的servlet类
 * doGet获得自己的所有信息，但是不能获取密码
 * doPost修改个人信息
 *
 */
public class ModifyUserServlet extends HttpServlet {
    private AccountService accountService;
    @Override
    public void init(){
        accountService = new AccountService();
        accountService.init();
    }

    /**
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        String email = req.getParameter("email");
        String signature = req.getParameter("signature");
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute("user");
        //可以不修改用户名
        if (name.equals(""))
            name = user.getName();
        if(name != null && user!=null && pwd != null && pwd.equals(user.getPwd()) && accountService.updateUser(new User(user.getUserID(),name,pwd,email,signature,user.getPermission()))) {
            resp.setStatus(200);
            session.setAttribute("user",new User(user.getUserID(),name,pwd,email,signature,user.getPermission()));
        } else {
            resp.setStatus(401);
        }
    }

    /**
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user != null){
            user = new User((User) req.getSession().getAttribute("user"));
            resp.setStatus(200);
            user.setPwd(null);
            OutputStream outputStream = resp.getOutputStream();
            outputStream.write(JSON.toJSONString(user).getBytes(StandardCharsets.UTF_8));
        } else {
            resp.setStatus(401);
        }
    }

}
