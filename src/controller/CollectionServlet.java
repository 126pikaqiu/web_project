package controller;
import service.CollectionService;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/17 23:31
 */
public class CollectionServlet extends HttpServlet {
    private CollectionService collectionService;
    public void init(){
        collectionService = new CollectionService();
        collectionService.init();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemID = req.getParameter("itemID");
        String method = req.getParameter("method");
        User user = (User)req.getSession().getAttribute("user");
        boolean success = false;
        if (user != null && "put".equalsIgnoreCase(method)) {
            success = collectionService.save(user.getUserID(),Integer.parseInt(itemID));
        } else if(user != null && "delete".equalsIgnoreCase(method)) {
            success = collectionService.delete(user.getUserID(),Integer.parseInt(itemID));
        } else if(user != null && "update".equalsIgnoreCase(method)) {
            success = collectionService.update(user.getUserID(),Integer.parseInt(itemID));
        }
        if(success) {
            resp.setStatus(200);
        } else {
            resp.setStatus(400);
        }
    }

}
