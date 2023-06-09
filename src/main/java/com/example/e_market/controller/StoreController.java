package com.example.e_market.controller;

import com.example.e_market.dto.input.GoodInput;
import com.example.e_market.dto.input.GoodList;
import com.example.e_market.dto.input.IdInput;
import com.example.e_market.dto.output.GoodListOut;
import com.example.e_market.dto.output.UserOrdersOut;
import com.example.e_market.entity.Good;
import com.example.e_market.entity.Store;
import com.example.e_market.entity.StoreOrder;
import com.example.e_market.service.GoodService;
import com.example.e_market.service.StoreOrderService;
import com.example.e_market.service.StoreService;
import com.example.e_market.service.UserLogService;
import com.example.e_market.service.impl.UserLogServiceImpl;
import com.example.e_market.util.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("seller")
public class StoreController extends BaseController {
    @Resource
    private StoreOrderService storeOrderService;

    @Resource
    private StoreService storeService;

    @Resource
    private GoodService goodService;

    @Resource
    UserLogService userLogService;

    @GetMapping("get_orders")
    public JsonResult<UserOrdersOut> getAllOrder(int id) {
        List<StoreOrder> storeOrders = storeOrderService.getAllOrder(id);
        System.out.println(storeOrders);
        return new JsonResult<>(new UserOrdersOut(storeOrders));
    }

    @PostMapping("modify_goods")
    public JsonResult<Void> updateGood(@RequestBody GoodInput input) {
        Good good = new Good();
        good.setAmount(input.getInventory());
        good.setGId(input.getId());
        good.setGPrice(input.getPrice());
        good.setGDes(input.getComDescription());
        good.setGName(input.getComName());
        good.setGPic(input.getPic_src());
        storeService.updateGood(good);
        return new JsonResult<>();
    }

    @PostMapping("delete_goods")
    public JsonResult<Void> deleteGood(@RequestBody IdInput input) {
        int gId = input.getId();
        goodService.dropGood(gId);
        return new JsonResult<>();
    }

    @PostMapping("deliver")
    public JsonResult<Void> setOrderState(@RequestBody IdInput input) {
        int oId = input.getId();
        String oState = "已发货";
        storeOrderService.setState(oId, oState);
        return new JsonResult<>();
    }


    @GetMapping("goodsList")
    public JsonResult<GoodListOut> getAllGood(int id) {
        return new JsonResult<>(new GoodListOut(storeService.getAllGoods(id)));
    }

    @PostMapping("add_goods_list")
    public JsonResult<Void> addGoodsList(@RequestBody GoodList input) {
        List<Good> goods = input.buildGoods();
        int sId = input.getId();
        for (Good good : goods) {
            storeService.addGood(sId, good);
        }
        userLogService.buildUserLog(sId, "seller", "添加商品", "从excel中添加商品成功！");
        return new JsonResult<>();
    }

    @PostMapping("add_new_goods")
    public JsonResult<Void> addGood(@RequestBody GoodInput input) {
        int sId = input.getId();
        storeService.addGood(sId, input.buildGood());
        userLogService.buildUserLog(sId, "seller", "添加商品", "添加单个商品成功！");
        return new JsonResult<>();
    }



    /*@GetMapping("getOrder")
    public StoreOrder getOrder(int oId) {
        return storeOrderService.getOrder(oId);
    }

    @PostMapping("addGood")
    public void addGood(int sId, Good good, int Amount) {
        storeService.addGood(sId, good, Amount);
    }

    @PostMapping("addAllGoods")
    public void addAllGoods(int sId, List<Good> goods) {
        for (Good good : goods) {
            storeService.addGood(sId, good, 100); // TODO 调整传参时处理，Amount先预设占位
        }
    }*/
}
