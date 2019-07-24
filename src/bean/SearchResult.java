package bean;

import java.util.ArrayList;

/**
 * 搜索结果的JavaBean类
 * items：搜索的结果
 * itemNum：查询到的展品总数
 * currentPageNum：当前返回结果的页面数
 *
 */
public class SearchResult {
    private ArrayList<Item> items;
    private int itemNum;
    private int currentPageNum;

    public SearchResult() {

    }

    public SearchResult(ArrayList<Item> items, int itemNum, int currentPageNum) {
        this.items = items;
        this.itemNum = itemNum;
        this.currentPageNum = currentPageNum;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }
}
