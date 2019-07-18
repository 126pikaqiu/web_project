package bean;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/17 23:49
 */
public class Collection {
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public Collection(int userID, int itemID) {
        this.userID = userID;
        this.itemID = itemID;
    }

    private int userID;
    private int itemID;

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Collection)) {
            return false;
        }
        return itemID==((Collection) obj).getItemID() && userID == ((Collection) obj).getUserID();
    }
}
