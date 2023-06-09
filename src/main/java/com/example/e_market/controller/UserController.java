package com.example.e_market.controller;

import com.example.e_market.dto.input.IdentityInput;
import com.example.e_market.dto.input.LoginInput;
import com.example.e_market.dto.input.ReginInput;
import com.example.e_market.dto.output.IdentityOut;
import com.example.e_market.dto.output.LogOut;
import com.example.e_market.entity.*;
import com.example.e_market.service.*;
import com.example.e_market.dto.output.LoginOut;
import com.example.e_market.service.ex.ServiceException;
import com.example.e_market.util.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("user")
public class UserController extends BaseController{
    @Resource
    private UserService userService;

    @Resource
    private StoreService storeService;

    @Resource
    ExpressCompanyService expressCompanyService;

    @Resource
    AdminService adminService;

    @Resource
    GoodService goodService;

    @Resource
    OrderService orderService;

    @PostMapping("regin")
    public JsonResult<LoginOut> regin(@RequestBody ReginInput input) {
        String identity = input.getIdentity();
        LoginOut result = new LoginOut();
        // 调用业务对象执行注册
        if (identity.equals("normal_user")) {
            User user = new User(input);
            int id = userService.regin(user);
            result.setId(id);
            result.setToken("user-token");
        } else if (identity.equals("seller")) {
            Store store = new Store(input);
            int id = storeService.regin(store);
            result.setId(id);
            result.setToken("seller-token");
        } else {
            throw new ServiceException("此类用户不允许注册!");
        }
        return new JsonResult<LoginOut>(result);
        // 返回
    }

    @PostMapping("login")
    public JsonResult<LoginOut> login(@RequestBody LoginInput input) {
        String username = input.getUsername();
        String password = input.getPassword();
        String identity = input.getIdentity();
        LoginOut result = new LoginOut();
        // 调用业务对象的方法执行登录，并获取返回值
        if (identity.equals("normal_user")) {
            User data = userService.login(username, password);
            // 将以上返回值和状态码OK封装到响应结果中并返回
            result.setId(data.getUId());
            result.setToken("user-token");
        } else if (identity.equals("seller")) {
            Store data = storeService.login(username, password);
            result.setId(data.getSId());
            result.setToken("seller-token");
        } else if (identity.equals("deliver")) {
            ExpressCompany data = expressCompanyService.login(username, password);
            result.setId(data.getEId());
            result.setToken("delivery-token");
        } else if (identity.equals("admin")) {
            Admin data = adminService.login(username, password);
            result.setId(data.getId());
            result.setToken("admin-token");
        }

        return new JsonResult<LoginOut>(result);
    }

    @PostMapping("info")
    public JsonResult<IdentityOut> info(@RequestBody IdentityInput input) {
        IdentityOut result = new IdentityOut(input.getToken());
        return new JsonResult<IdentityOut>(result);
    }

    @PostMapping("logout")
    public JsonResult<LogOut> logOut() {
        return new JsonResult<LogOut>(new LogOut());
    }

    /*@PostMapping("addCart")
    public void addCart(Cart cart) {
        userService.addCart(cart);
    }

    @GetMapping("getAllCart")
    public List<Cart> getAllCart(int uId) {
        return userService.getAllCart(uId);
    }

    @PostMapping("ShoppingCart")
    public void buyShoppingCart(int uId, String addr) {
        List<Cart> carts = userService.getAllCart(uId);
        for (Cart cart : carts) {
            int gid = cart.getGId();
            int amount = cart.getAmount();
            Good good = goodService.getGood(gid);
            int price = good.getGPrice();
            int money = price * amount;
            // 创建订单
            orderService.buildOrder(addr, money, cart);
            // 移除购物车
            userService.dropCart(uId, gid);
        }
    }

    @GetMapping("getUser")
    public User getUser(int uId) {
        return userService.getUser(uId);
    }*/
}
