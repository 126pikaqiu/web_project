package controller;


import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/21 23:43
 */
public class ItemServlet extends HttpServlet {
    private ItemService itemService;
    public void init(){
        itemService= new ItemService();
        itemService.init();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemID = req.getParameter("itemID");

        if(itemID!= null && itemService.addHot(Integer.parseInt(itemID))) {
            resp.setStatus(200);
        } else {
            resp.setStatus(400);
        }
    }
}
