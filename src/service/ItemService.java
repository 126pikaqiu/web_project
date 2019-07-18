package service;

import dao.itemDao;

import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/16 23:42
 */
public class ItemService {
    private itemDao itemDao;
    public void init() {
        itemDao = new itemDao();
        itemDao.init();
    }
    public ArrayList<Item>  getItems(int userID) {
        return itemDao.getItems(userID);
    }

    public Item getItem(int itemID) {
        return itemDao.getItem(itemID);
    }
}
