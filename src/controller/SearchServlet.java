package controller;

import bean.SearchResult;
import com.alibaba.fastjson.JSON;
import service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

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
        SearchResult searchResult = itemService.getItemsByOrder(searchKey, order, intPage);
        if (searchResult.getItems() != null) {
            response.setStatus(200);
            OutputStream out = response.getOutputStream();
            System.out.println(JSON.toJSONString(searchResult));
            out.write(JSON.toJSONString(searchResult).getBytes(StandardCharsets.UTF_8));
        } else {
            response.setStatus(400);
        }
    }
}
