package bean;

import java.util.List;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/23 8:32
 */
public class HomePage {
    public HomePage(User user, List<Item> public_collection) {
        this.user = user;
        this.publicCollection = public_collection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Item> getPublicCollection() {
        return publicCollection;
    }

    public void setPublicCollection(List<Item> publicCollection) {
        this.publicCollection = publicCollection;
    }

    private User user;
    private List<Item> publicCollection;
}
