package service;

import bean.Item;
import dao.ItemDao;

import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/16 23:42
 */
public class ItemService {
    private ItemDao itemDao;
    public void init() {
        itemDao = new ItemDao();
        itemDao.init();
    }
    public ArrayList<Item>  getItems(int userID) {
        return itemDao.getItems(userID);
    }

    public Item getItem(int itemID) {
        return itemDao.getItem(itemID);
    }
}
