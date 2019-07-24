package service;

import bean.User;
import dao.AccountDao;

import java.util.ArrayList;

/**
 * 首先明确，service是在servlet(jsp)和dao中间，起到桥梁和筛选等作用的存在，因此此处的各个方法均为响应前台，从后台获取数据后返回给前台，没有特殊说明没有特殊功能
 * 请根据函数名自行yy
 */
public class AccountService {
    private AccountDao accountDao;

    public ArrayList<User> getAllUsers(String userType) {
        return accountDao.getAllUser(userType);
    }

    public boolean deleteUser(int id) {
        return accountDao.deleteUser(id);
    }

    public boolean updateUserPermission(int id, int newPermission) {
        return accountDao.updateUserPermission(id, newPermission);
    }

    public AccountService() {
        accountDao = new AccountDao();
    }

    public void init() {
        accountDao.init();
    }

    public boolean login(String name, String pwd) {
        return accountDao.login(name, pwd);
    }

    public boolean register(User user) {
        return accountDao.saveUser(user);
    }

    public User getUser(String name) {
        return accountDao.getUserLiu(name);
    }

    public void destroy() {
        accountDao.destroy();
    }

    public boolean updateUser(User user) {
        return accountDao.updateUser(user);
    }

    public ArrayList<User> searchUsers(String name, int id) {
        return accountDao.searchUsers(name, id);
    }
}
