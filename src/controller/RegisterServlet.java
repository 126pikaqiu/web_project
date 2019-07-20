package controller;

import bean.User;
import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author: jiaxing liu
 * @Date: 2019/7/15 16:06
 */
public class RegisterServlet extends HttpServlet {
    private AccountService accountService;
    @Override
    public void init(){
        accountService = new AccountService();
        accountService.init();
    }

    /**
     * to handle login
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
        HttpSession session = req.getSession();
        if(name != null && pwd != null && accountService.register(new User(name,pwd,email,1))) {
            resp.setStatus(200);
            User user = accountService.getUser(name);
            session.setAttribute("user",user);
            session.setAttribute("permission",1);
        } else {
            session.setAttribute("permission",0);
            session.setAttribute("user",null);
            resp.setStatus(401);
        }
    }
}
