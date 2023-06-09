package com.example.e_market.controller;

import com.example.e_market.dto.input.*;
import com.example.e_market.dto.output.*;
import com.example.e_market.entity.*;
import com.example.e_market.service.*;
import com.example.e_market.util.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("normal_user")
public class NormalUserController extends BaseController{
    @Resource
    private UserService userService;

    @Resource
    private StoreService storeService;

    @Resource
    ExpressCompanyService expressCompanyService;

    @Resource
    GoodService goodService;

    @Resource
    OrderService orderService;

    @Resource
    UserLogService userLogService;

    @GetMapping("get_orders")
    public JsonResult<UserOrdersOut> getOrders(int id) {
        List<Order> orders = userService.getOrders(id);
        return new JsonResult<UserOrdersOut>(new UserOrdersOut(orders, "user"));
    }

    @PostMapping("add_to_shoppingcart")
    public JsonResult<Void> addCart(@RequestBody addCartInput input) {
        userService.addCart(input.buildCart());
        return new JsonResult<Void>();
    }

    @GetMapping("get_shopping_cart")
    public JsonResult<getCartsOut> getAllCart(int id) {
        List<Good> goods = userService.getAllCartGood(id);
        return new JsonResult<>(new getCartsOut(goods));
    }

    @GetMapping("goodsList")
    public JsonResult<GoodListOut> getGoodsList(String order) {
        List<Good> goods = goodService.getSortedGoods(order);
        return new JsonResult<>(new GoodListOut(goods));
    }

    @GetMapping("search_goods")
    public JsonResult<GoodListOut> searchGoods(String keyword) {
        List<Good> goods = goodService.searchGoods(keyword);
        return new JsonResult<>(new GoodListOut(goods));
    }

    @GetMapping("get_collections")
    public JsonResult<GoodListOut> getCollections(int id) {
        List<Good> goods = goodService.getCollections(id);
        return new JsonResult<>(new GoodListOut(goods));
    }

    @PostMapping("receiver_info")
    public JsonResult<Void> updateReceiverInfo(@RequestBody ReceiveInput input) {
        Receive receive = new Receive();
        receive.setId(input.getId());
        receive.setNote(input.getNote());
        receive.setRAddr(input.getRecv_addr());
        receive.setRName(input.getRecv_name());
        receive.setRProvince(input.getRecv_province());
        receive.setRTel(input.getRecv_tel());
        userService.updateReceive(receive);
        return new JsonResult<>();
    }

    @GetMapping("get_recv_info")
    public JsonResult<ReceiveOut> getRecvInfo(int id) {
        Receive receive = userService.getReceive(id);
        return new JsonResult<>(new ReceiveOut(receive));
    }

    @PostMapping("clear_shopping_cart")
    public JsonResult<Void> buyShoppingCart(@RequestBody IdInput input) {
        int uId = input.getId();
        List<Cart> carts = userService.getAllCart(uId);
        for (Cart cart : carts) {
            int gid = cart.getGId();
            int amount = cart.getAmount();
            Good good = goodService.getGood(gid);
            int price = good.getGPrice();
            int money = price * amount;
            // 创建订单
            orderService.buildOrder(money, cart);
            // 移除购物车
            userService.dropCart(uId, gid);
        }
        userLogService.buildUserLog(uId, "normal_user", "下单", "成功下单！");
        return new JsonResult<>();
    }

    @PostMapping("confirm_order")
    public JsonResult<Void> confirmOrder(@RequestBody confirmOrderInput input) {
        int oId = input.getId();
        String password = input.getPassword();
        userService.checkPassword(oId, password);
        orderService.confirmOrder(oId);
        return new JsonResult<>();
    }

    @PostMapping("add_to_collection")
    public JsonResult<Void> addCollect(@RequestBody CollectInput input) {
        int uId = input.getU_id();
        int gId = input.getG_id();
        userService.addCollect(uId, gId);
        return new JsonResult<>();
    }

    @PostMapping("remove_from_cart")
    public JsonResult<Void> removeFromCart(@RequestBody CollectInput input) {
        int uId = input.getU_id();
        int gId = input.getG_id();
        userService.removeFromCart(uId, gId);
        return new JsonResult<>();
    }

    @GetMapping("getUser")
    public User getUser(int id) {
        return userService.getUser(id);
    }
}
