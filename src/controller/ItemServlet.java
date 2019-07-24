package controller;


import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 普通用户(全部用户)对展品操作的servlet类
 * 目前只有增加热度（点赞）的请求
 */
public class ItemServlet extends HttpServlet {
    private ItemService itemService;

    public void init() {
        itemService = new ItemService();
        itemService.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取操作的展品id
        String itemID = req.getParameter("itemID");

        if (itemID != null && itemService.addHot(Integer.parseInt(itemID))) {
            resp.setStatus(200);
        } else {
            resp.setStatus(400);
        }
    }
}
