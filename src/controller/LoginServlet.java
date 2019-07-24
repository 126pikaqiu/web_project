package controller;

import com.alibaba.fastjson.JSON;
import service.AccountService;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 处理登录请求的servlet类
 * doGet获取用户信息
 * doPost处理登录操作
 */
public class LoginServlet extends HttpServlet {
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
        //获取用户名
        String name = req.getParameter("username");
        //获取密码
        String pwd = req.getParameter("pwd");
        //获取session
        HttpSession session = req.getSession();
        if(name != null && pwd != null && accoutService.login(name,pwd)) {
            resp.setStatus(200);
            User user = accoutService.getUser(name);
            session.setAttribute("user",user);
            session.setAttribute("permission",user.getPermission());
        } else {
            session.setAttribute("permission",0);
            session.setAttribute("user",null);
            resp.setStatus(401);
        }
    }

    /*%u597D%u53CB%u63A8%u8350%u529F%u80FD%uFF0C%u6211%u4E5F%u4E0D%u77E5%u9053%u4E3A%u5565%u8FD9%u91CC%u6709%u4E2A%u8FD9%u513F%uFF0C%u95EE%uFF0C%u5C31%u53BB%u95EE126pikaqiu*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名
        String name = req.getParameter("username");
        //获取操作用户
        User user = (User) req.getSession().getAttribute("user");
        if(name == null || user==null){
            resp.setStatus(400);
            return;
        }
        OutputStream outputStream = resp.getOutputStream();
        resp.setStatus(200);
        //好友推荐
        outputStream.write(JSON.toJSONString(accoutService.searchUsers(name,user.getUserID())).getBytes());
    }
}
