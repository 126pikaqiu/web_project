package controller;

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
public class AccountServlet extends HttpServlet {
    private AccountService accoutService;
    @Override
    public void init(){
        accoutService = new AccountService();
        accoutService.init();
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
        HttpSession session = req.getSession();
        if(name != null && pwd != null && accoutService.login(name,pwd)) {
            resp.setStatus(200);
            session.setAttribute("authorization",accoutService.getPermission(name));
            session.setAttribute("username",name);
            session.setAttribute("pwd",pwd);
        } else {
            session.removeAttribute("authorization");
            resp.setStatus(401);
        }
    }
}
