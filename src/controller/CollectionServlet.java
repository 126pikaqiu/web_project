package controller;

import service.CollectionService;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来处理收藏品请求的servlet类
 * doGet的逻辑由jsp直接完成
 * doPost用来处理对收藏品的添加删除和修改操作
 *
 */
public class CollectionServlet extends HttpServlet {
    private CollectionService collectionService;

    public void init() {
        collectionService = new CollectionService();
        collectionService.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取操作的展品的id
        String itemID = req.getParameter("itemID");
        //获取操作类型
        String method = req.getParameter("method");
        //通过session获取操作者
        User user = (User) req.getSession().getAttribute("user");
        //操作结果（操作是否成功）
        boolean success = false;
        if (user != null && "put".equalsIgnoreCase(method)) {//增加收藏品
            success = collectionService.save(user.getUserID(), Integer.parseInt(itemID));
        } else if (user != null && "delete".equalsIgnoreCase(method)) {//删除收藏品
            success = collectionService.delete(user.getUserID(), Integer.parseInt(itemID));
        } else if (user != null && "update".equalsIgnoreCase(method)) {//修改收藏品公开权限
            success = collectionService.update(user.getUserID(), Integer.parseInt(itemID));
        }
        if (success) {
            resp.setStatus(200);
        } else {
            resp.setStatus(400);
        }
    }
}
