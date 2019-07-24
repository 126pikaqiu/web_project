package bean;

/**
 * 收藏品的JavaBean类
 * userID:用户的id
 * itemID:收藏品的id
 * permission:收藏品权限
 *
 */
public class Collection {
    private int userID;
    private int itemID;
    private int permission;

    public Collection(int userID, int itemID) {
        this.userID = userID;
        this.itemID = itemID;
    }


    public Collection(int userID, int itemID, int permission) {
        this.userID = userID;
        this.itemID = itemID;
        this.permission = permission;
    }

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


    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Collection)) {
            return false;
        }
        return itemID == ((Collection) obj).getItemID() && userID == ((Collection) obj).getUserID();
    }
}
