package com.food.core.controller;

import com.food.core.anno.Auth;
import com.food.core.pack.*;
import com.food.core.pack.RequestParam;
import com.food.core.req.vo.LoginReqVo;
import com.food.core.req.vo.OrderInReqVo;
import com.food.core.req.vo.OrderReqVo;
import com.food.core.req.vo.RegisterReqVo;
import com.food.core.res.vo.*;
import com.food.core.service.UserService;
import com.food.core.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController  extends BaseController  {

    @Autowired
    UserService userService;

    /**
     * 登录
     * @param param
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseResult<LoginResVo> getAssembly(
            @RequestBody RequestParam<LoginReqVo> param) {
        LoginReqVo vo = param.getParams();
        if(!VerifyUtil.verifyParams(vo)){
            return ResponseResult.fail(SystemCode.PARAMS_ERROR);
        }
        return userService.login(vo);
    }


    /**
     * 注册
     * @param param
     * @return
     */
    @PostMapping(value = "/register")
    public ResponseResult<LoginResVo> register(
            @RequestBody RequestParam<RegisterReqVo> param) {
        RegisterReqVo vo = param.getParams();
        if(!VerifyUtil.verifyParams(vo)){
            return ResponseResult.fail(SystemCode.PARAMS_ERROR);
        }
        return userService.register(vo);
    }

    /**
     * 获取店铺支付信息
     * @return
     */
    @Auth
    @PostMapping(value = "/zfb")
    public ResponseResult<OrderResVo> zfb(
            @RequestBody RequestParam<OrderInReqVo> param) {
        OrderInReqVo vo = param.getParams();
        if(!VerifyUtil.verifyParams(vo)){
            return ResponseResult.fail(SystemCode.PARAMS_ERROR);
        }
        String userId = super.getSysUserId();
        vo.setId(userId);
        return userService.zfb(vo);
    }

    /**
     * 确认支付
     * @return
     */
    @Auth
    @PostMapping(value = "/yes")
    public ResponseResult<OrderResVo> yes(
            @RequestBody RequestParam<String> param) {
        String vo = param.getParams();
        if(StringUtils.isEmpty(vo)){
            return ResponseResult.fail(SystemCode.PARAMS_ERROR);
        }
        return userService.yes(vo);
    }

    /**
     * 查询我的订单列表
     * @return
     */
    @Auth
    @GetMapping(value = "/order/list")
    public ResponseCollection<OrderListResVo> orderList() {
        String userId = super.getSysUserId();
        return userService.orderList(userId);
    }

    /**
     * 获取商品分类
     * @return
     */
    @Auth
    @GetMapping(value = "/type")
    public ResponseCollection<TypeResVo> type() {
        return userService.type();
    }


    /**
     * 获取店铺支付信息
     * @return
     */
    @Auth
    @PostMapping(value = "/food")
    public ResponseCollection<FoodListResVo> food(
            @RequestBody RequestPageParam<String> param) {
        String vo = param.getParams();
        PageInfo page = param.getPage();
        return userService.food(vo,page);
    }


}
