package com.example.e_market.service;

import com.example.e_market.entity.*;

import java.util.List;

public interface UserService {
    /**
     * 用户注册
     * @param user 用户数据
     */
    int regin(User user);

    /**
     * 用户登录
     * @param account 用户名
     * @param pwd 密码
     * @return 登录成功的用户数据
     */
    User login(String account, String pwd);

    void addCart(Cart cart);

    List<Cart> getAllCart(int uId);

    List<Good> getAllCartGood(int uId);

    void dropCart(int uId, int gId);

    User getUser(int uId);

    List<Order> getOrders(int uId);

    void updateReceive(Receive receive);

    Receive getReceive(int id);

    void checkPassword(int oId, String password);

    void addCollect(int uId, int gId);

    void removeFromCart(int uId, int gId);
}
