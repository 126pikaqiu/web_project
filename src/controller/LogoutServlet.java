package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/17 20:35
 */
public class LogoutServlet extends HttpServlet {
    private ArrayList<String> bannedUrls = new ArrayList<>();
    public void init(){
        bannedUrls.add("collections.jsp");
        bannedUrls.add("dashboard.jsp");
        //TODO:add something else
    }
    /**
     * to handle logout
     * @param req request
     * @param resp response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getParameter("current_url");
        HttpSession session = req.getSession();
        session.setAttribute("user",null);
        session.setAttribute("permission",0);
        if (url == null || bannedUrls.contains(url)) {
            resp.sendRedirect("index.jsp");//重定向到首页
        } else {
            resp.sendRedirect(url); //通行
        }
    }
}
