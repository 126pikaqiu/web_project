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

/**
 * @ Author     ：Wang Shang.
 * @ Date       ：Created in 13:39 2019/7/20
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public class ManageItemsServlet extends HttpServlet {
    private ItemService itemService;

    public void init() {
        itemService = new ItemService();
        itemService.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchResult searchResult = itemService.getItemsByOrder("", "", 1, true);
        if (searchResult.getItems() != null) {
            resp.setStatus(200);
            OutputStream out = resp.getOutputStream();
            System.out.println(JSON.toJSONString(searchResult));
            out.write(JSON.toJSONString(searchResult).getBytes(StandardCharsets.UTF_8));
        } else {
            resp.setStatus(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
