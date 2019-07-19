package controller;

import bean.Item;
import com.alibaba.fastjson.JSON;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SearchServlet extends HttpServlet {
    private ItemService itemService;

    public void init() {
        itemService = new ItemService();
        itemService.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKey = request.getParameter("key");
        String order = request.getParameter("order");
        String page = request.getParameter("page");
        int intPage = page == null ? 1 : Integer.parseInt(page);
        ArrayList<Item> items = itemService.getItemsByOrder(searchKey, order, intPage);
        if (items != null) {
            response.setStatus(200);
            PrintWriter out = response.getWriter();
            System.out.println(JSON.toJSONString(items));
            System.out.println("arrive here!");
            out.write(JSON.toJSONString(items));
        } else {
            response.setStatus(400);
        }
    }
}
