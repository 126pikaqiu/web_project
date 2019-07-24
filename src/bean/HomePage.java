package bean;

import java.util.List;

/**
 * 个人主页的JavaBean类
 * user：用户
 * publicCollection：公开的收藏品
 *
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
