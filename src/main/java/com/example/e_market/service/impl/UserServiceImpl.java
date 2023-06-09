package com.example.e_market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.e_market.entity.*;
import com.example.e_market.mapper.*;
import com.example.e_market.service.UserLogService;
import com.example.e_market.service.UserService;
import com.example.e_market.service.ex.ServiceException;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private CartMapper cartMapper;

    @Resource
    private UserOrderMapper userOrderMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private GoodMapper goodMapper;

    @Resource
    private ReceiveMapper receiveMapper;

    @Resource
    UserLogService userLogService;

    @Resource
    CollectMapper collectMapper;

    @Override
    public int regin(User user) {
        // 查询是否有重名
        String U_name = user.getUName();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUName, U_name);
        User result = userMapper.selectOne(queryWrapper);
        if (result != null) {
            throw new ServiceException("该用户名已被使用！");
        }
        // 将密码进行md5加密
        String pwd = user.getUPwd();
        user.setUPwd(getMd5Password(pwd));
        // 存入数据库
        userMapper.insert(user);
        userLogService.buildUserLog(user.getUId(), "normal_user", "注册", "注册成功！");
        return user.getUId();
    }

    @Override
    public User login(String account, String pwd) {
        // 判断是否存在该用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUAccount, account);
        User result = userMapper.selectOne(queryWrapper);
        if (result == null) {
            throw new ServiceException("该用户不存在！");
        }
        pwd = getMd5Password(pwd);
        if (!pwd.equals(result.getUPwd())) {
            throw new ServiceException("密码错误！");
        }
        userLogService.buildUserLog(result.getUId(), "normal_user", "登录", "登陆成功！");
        return result;
    }

    @Override
    public void addCart(Cart cart) {
        if (cart.getGId() == -1) {
            throw new ServiceException("该商品已被删除，不允许添加！");
        }
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("u_id", cart.getUId());
        qw.eq("g_id", cart.getGId());
        Cart cart1 = cartMapper.selectOne(qw);
        if (cart1 != null) {
            cart1.setAmount(cart1.getAmount() + cart.getAmount());
            cartMapper.update(cart1, qw);
        } else {
            cartMapper.insert(cart);
        }
    }

    @Override
    public List<Cart> getAllCart(int uId) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("u_id", uId);
        List<Cart> carts = cartMapper.selectList(qw);
        return carts;
    }

    @Override
    public List<Good> getAllCartGood(int uId) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("u_id", uId);
        List<Cart> carts = cartMapper.selectList(qw);
        List<Good> goods = new ArrayList<>();
        for (Cart cart : carts) {
            Good good = goodMapper.selectById(cart.getGId());
            good.setAmount(cart.getAmount()); // 将购物车的购买数量加入good属性中
            goods.add(good);
        }
        return goods;
    }

    @Override
    public void dropCart(int uId, int gId) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("u_id", uId);
        qw.eq("g_id", gId);
        cartMapper.delete(qw);
    }

    @Override
    public User getUser(int uId) {
        return userMapper.selectById(uId);
    }

    @Override
    public List<Order> getOrders(int uId) {
        List<Order> orders = new ArrayList<>();
        QueryWrapper<UserOrder> qw = new QueryWrapper<>();
        qw.eq("u_id", uId);
        List<UserOrder> userOrders = userOrderMapper.selectList(qw);
        for (UserOrder userOrder : userOrders) {
            Order order = orderMapper.selectById(userOrder.getOId());
            orders.add(order);
        }
        return orders;
    }

    @Override
    public void updateReceive(Receive receive) {
        Receive result = receiveMapper.selectById(receive.getId());
        try {
            if (result == null) {
                receiveMapper.insert(receive);
            } else {
                receiveMapper.updateById(receive);
            }
        } catch (Exception e) {
            throw new ServiceException("请提供完整信息！");
        }
    }

    @Override
    public Receive getReceive(int id) {
        Receive receive = receiveMapper.selectById(id);
        if (receive == null) {
            receive = new Receive();
            receive.setId(id);
            return receive;
        }
        return receive;
    }

    @Override
    public void checkPassword(int oId, String password) {
        QueryWrapper<UserOrder> qw = new QueryWrapper<>();
        qw.eq("o_id", oId);
        int uId = userOrderMapper.selectOne(qw).getUId();
        User user = userMapper.selectById(uId);
        if (!user.getUPwd().equals(getMd5Password(password))) {
            throw new ServiceException("密码错误！");
        }
    }

    @Override
    public void addCollect(int uId, int gId) {
        if (gId == -1) {
            throw new ServiceException("该商品已被删除，不允许添加！");
        }
        QueryWrapper<Collect> qw = new QueryWrapper<>();
        qw.eq("u_id", uId);
        qw.eq("g_id", gId);
        Collect collect = collectMapper.selectOne(qw);
        if (collect != null) {
            throw new ServiceException("该商品已在收藏夹中！");
        }
        Collect collect1 = new Collect();
        collect1.setUId(uId);
        collect1.setGId(gId);
        collectMapper.insert(collect1);
    }

    @Override
    public void removeFromCart(int uId, int gId) {
        QueryWrapper<Cart> qw = new QueryWrapper<>();
        qw.eq("u_id", uId);
        qw.eq("g_id", gId);
        Cart cart = cartMapper.selectOne(qw);
        if (cart == null) {
            throw new ServiceException("该商品已从购物车中移除！");
        }
        cartMapper.delete(qw);
    }


    /**
     * 执行密码加密
     * @param password 原始密码
     * @return 加密后的密文
     */
    private String getMd5Password(String password) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        String salt = "2006DB";
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }


}
