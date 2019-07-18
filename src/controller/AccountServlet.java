package controller;

import javax.servlet.http.HttpServlet;


/**
 * @author: jiaxing liu
 * @Date: 2019/7/15 16:06
 */
public class AccountServlet extends HttpServlet {
//    private AccountService accoutService;
//    @Override
//    public void init(){
//        accoutService = new AccountService();
//        accoutService.init();
//    }
//
//    /**
//     * to handle login
//     * @param req request
//     * @param resp response
//     * @throws ServletException
//     * @throws IOException
//     */
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("username");
//        String pwd = req.getParameter("pwd");
//        HttpSession session = req.getSession();
//        if(name != null && pwd != null && accoutService.login(name,pwd)) {
//            resp.setStatus(200);
//            User user = accoutService.getUser(name);
//            session.setAttribute("user",user);
//            session.setAttribute("permission",user.getPermission());
//        } else {
//            session.setAttribute("permission",0);
//            session.setAttribute("user",null);
//            resp.setStatus(401);
//        }
//    }
}
