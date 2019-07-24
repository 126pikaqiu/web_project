package bean;

/**
 * 用户的JavaBean类
 * userID：用户的id
 * name：用户名
 * pwd：密码
 * email：用户邮箱
 * permission：用户的权限
 * signature：个性签名
 * lastLogin：用户最近一次登录时间
 *
 */
public class User {
    private int userID;
    private String name;
    private String pwd;
    private String email;
    private int permission;
    private String signature;
    private String lastLogin;

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


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public User(int userID, String name, String pwd, String email, String signature, int permission, String lastLogin) {
        this.userID = userID;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.permission = permission;
        this.signature = signature;
        this.lastLogin = lastLogin;
    }

    public User(int userID, String name, String pwd, String email, String signature, int permission) {
        this.userID = userID;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.signature = signature;
        this.permission = permission;
    }

    public User(String name, String pwd, String email, String signature, int permission) {
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

    public User(int userID, String name, String pwd, String email, int permission) {
        this.userID = userID;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.permission = permission;
    }


    public User(String name, String pwd, String email, int permission) {
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.permission = permission;
    }

    public User(User user) {
        this.userID = user.userID;
        this.name = user.name;
        this.pwd = user.pwd;
        this.email = user.email;
        this.signature = user.signature;
        this.permission = user.permission;
    }


    public User() {
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User && userID == ((User) obj).userID) {
            return true;
        }
        return false;
    }
}
