package bean;

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

    public User(String name, String pwd, String email, int permission) {
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.permission = permission;
    }

    private String name;
    private String pwd;
    private String email;
    private int permission;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public User(String name, String pwd, String email,String signature, int permission) {
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.signature = signature;
        this.permission = permission;
    }

    public User(int userID, String name, String pwd, String email, String signature) {
        this.userID = userID;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.signature = signature;
    }
    public User(int userID, String name, String pwd, String email, String signature,int permission) {
        this.userID = userID;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.signature = signature;
        this.permission = permission;
    }

    private String signature;
    public User(){}
    public User(User user){
        this.userID = user.userID;
        this.name = user.name;
        this.pwd = user.pwd;
        this.email = user.email;
        this.signature = user.signature;
        this.permission = user.permission;
    }
}
