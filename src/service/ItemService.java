package service;

import bean.Item;
import bean.SearchResult;
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

    public ArrayList<Item> getItems(int userID) {
        return itemDao.getItems(userID);
    }

    public SearchResult getItemsByOrder(String searchKey, String order, int page) {
        return itemDao.getItemsByOrder(searchKey, order, page);
    }

    public Item getItem(int itemID) {
        return itemDao.getItem(itemID);
    }

    public ArrayList<Item> getLatest() {
        return itemDao.getLatest();
    }

    public ArrayList<Item> getHottest() {
        return itemDao.getHottest();
    }
}
