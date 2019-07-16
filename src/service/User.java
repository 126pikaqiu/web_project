package service;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/16 23:51
 */
public class User {
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public User(int userID, String name, String pwd, String email, int permission) {
        this.userID = userID;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.permission = permission;
    }

    private int userID;
    private String name;
    private String pwd;
    private String email;
    private int permission;
    public User(){}

}
