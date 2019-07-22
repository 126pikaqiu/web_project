package bean;

import java.util.ArrayList;

/**
 * @ Author     ：Wang Shang.
 * @ Date       ：Created in 12:53 2019/7/20
 * @ Description：
 * @ Modified By：
 * @Version: $
 */
public class SearchResult {
    private ArrayList<Item> items;
    private int pageNum;
    private int currentPageNum;
    public SearchResult(){

    }

    public SearchResult(ArrayList<Item> items, int pageNum, int currentPageNum){
        this.items = items;
        this.pageNum = pageNum;
        this.currentPageNum = currentPageNum;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }
}
