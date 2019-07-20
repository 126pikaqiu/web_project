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

    private static class Cryption{
        static String encrypt(String text){
            return convertMD5(text);
        }
        static String decrypt(String ciphertext){
            return convertMD5(convertMD5(ciphertext));
        }
        static String decryptMD5(String ciphertext){
            return convertMD5(convertMD5(ciphertext));
        }
        private static String convertMD5(String inStr){

            char[] a = inStr.toCharArray();
            for (int i = 0; i < a.length; i++){
                a[i] = (char) (a[i] ^ 't');
            }
            return new String(a);

        }

    }
    public AccountService(){
        accountDao = new AccountDao();
    }
    public void init() {
        accountDao.init();
    }
    public boolean login(String name, String pwd){
//        String passwordFromUser = Cryption.convertMD5(pwd);
//        String passwordFromDatabase = Cryption.decrypt(accountDao.getUser(name).getPwd());
//        return passwordFromDatabase.equals(passwordFromUser);
        return accountDao.getUser(name).getPwd().equals(pwd);
    }
    public boolean register(User user) {
        return accountDao.saveUser(user);
    }
    public User getUser(String name) {
        return accountDao.getUser(name);
    }
    public void destroy(){
        accountDao.destroy();
    }
    public boolean updateUser(User user){
        return accountDao.updateUser(user);
    }
}
