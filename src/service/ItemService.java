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

    public void destroy() {
        itemDao.destroy();
    }

    public ArrayList<Item> getItems(int userID, int permission) {
        return itemDao.getItems(userID, permission);
    }

    public ArrayList<Item> getItems(int userID) {
        return itemDao.getItems(userID);
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

    public boolean addHot(int itemID) {
        return itemDao.addHot(itemID);
    }

    public SearchResult getItemsByOrder(String searchKey, String order, int page) {
        return getItemsByOrder(searchKey, order, page, false);
    }

    public SearchResult getItemsByOrder(String searchKey, String order, int page, boolean allpage) {
        return itemDao.getItemsByOrder(searchKey, order, page, allpage);
    }

    public boolean updateItem(Item item) {
        return itemDao.updateItem(item);
    }

    public boolean deleteItem(int id) {
        return itemDao.deleteItem(id);
    }

    public boolean save(Item item) {
        return itemDao.save(item);
    }

    public ArrayList<Item> getSimilar(Item item, int count) {
        return itemDao.getSimilar(item, count);
    }

    public ArrayList<Item> getRecommends(Item item, int userID, int count) {
        return itemDao.getRecommends(item, userID, count);
    }
}
