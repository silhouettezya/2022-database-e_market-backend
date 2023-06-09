package com.example.e_market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.e_market.entity.Good;
import com.example.e_market.entity.Store;
import com.example.e_market.entity.StoreGood;
import com.example.e_market.entity.User;
import com.example.e_market.mapper.GoodMapper;
import com.example.e_market.mapper.StoreGoodMapper;
import com.example.e_market.mapper.StoreMapper;
import com.example.e_market.service.StoreService;
import com.example.e_market.service.UserLogService;
import com.example.e_market.service.ex.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Resource
    private StoreMapper storeMapper;

    @Resource
    private GoodMapper goodMapper;

    @Resource
    private StoreGoodMapper storeGoodMapper;

    @Resource
    UserLogService userLogService;

    @Override
    public List<Good> getAllGoods(int sId) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("s_id", sId);
        List<StoreGood> storeGoods = storeGoodMapper.selectList(qw);
        ArrayList<Good> Goods = new ArrayList<>();
        for (StoreGood storeGood : storeGoods) {
            qw = new QueryWrapper();
            qw.eq("g_id", storeGood.getGId());
            Good good = goodMapper.selectOne(qw);
            good.setAmount(storeGood.getAmount());
            Goods.add(good);
        }
        return Goods;
    }

    @Override
    public void updateGood(Good good) {
        int gId = good.getGId();
        goodMapper.updateById(good);

        UpdateWrapper<StoreGood> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("g_id", gId).set("Amount", good.getAmount());
        storeGoodMapper.update(null, updateWrapper);

    }

    @Override
    public int regin(Store store) {
        // 查询是否有重名
        String sAccount = store.getSAccount();
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Store::getSAccount, sAccount);
        Store result = storeMapper.selectOne(queryWrapper);
        if (result != null) {
            throw new ServiceException("该用户名已被使用！");
        }
        // 将密码进行md5加密
        String pwd = store.getSPwd();
        store.setSPwd(getMd5Password(pwd));
        // 存入数据库
        storeMapper.insert(store);
        userLogService.buildUserLog(store.getSId(), "seller", "注册", "注册成功！");
        return store.getSId();
    }

    @Override
    public Store login(String account, String pwd) {
        // 判断是否存在该用户
        LambdaQueryWrapper<Store> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Store::getSAccount, account);
        Store result = storeMapper.selectOne(queryWrapper);
        if (result == null) {
            throw new ServiceException("该用户不存在！");
        }
        pwd = getMd5Password(pwd);
        if (!pwd.equals(result.getSPwd())) {
            throw new ServiceException("密码错误！");
        }
        userLogService.buildUserLog(result.getSId(), "seller", "登录", "登陆成功！");
        return result;
    }

    @Override
    public void addGood(int sId, Good good) {
        goodMapper.insert(good);
        int gId = good.getGId();
        StoreGood storeGood = new StoreGood();
        storeGood.setSId(sId);
        storeGood.setGId(gId);
        storeGood.setAmount(good.getAmount());
        storeGoodMapper.insert(storeGood);
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
