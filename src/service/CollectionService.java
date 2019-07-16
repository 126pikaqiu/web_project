package service;

import dao.CollectionDao;

import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/16 23:42
 */
public class CollectionService {
    private CollectionDao collectionDao;
    public void init() {
        collectionDao = new CollectionDao();
        collectionDao.init();
    }
    public ArrayList<Item>  getItems(int userID) {
        return collectionDao.getItems(userID);
    }

    public Item getItem(int itemID) {
        return collectionDao.getItem(itemID);
    }
}
