package service;

import bean.User;
import dao.AccountDao;

import java.util.ArrayList;

/**
 * @author: jiaxing liu
 * @Date: 2019/7/15 16:05
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
        return accountDao.updateUserPermission(id,newPermission);
    }
    public AccountService(){
        accountDao = new AccountDao();
    }
    public void init() {
        accountDao.init();
    }
    public boolean login(String name, String pwd){
        return accountDao.login(name,pwd);
    }
    public boolean register(User user) {
        return accountDao.saveUser(user);
    }
    public User getUser(String name) {
        return accountDao.getUserLiu(name);
    }
    public void destroy(){
        accountDao.destroy();
    }
    public boolean updateUser(User user){
        return accountDao.updateUser(user);
    }
    public ArrayList<User> searchUsers(String name, int id) {
        return accountDao.searchUsers(name,id);
    }
}
