package com.food.core.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.food.core.adapter.JwtPayLoad;
import com.food.core.anno.Auth;
import com.food.core.contests.SystemDefaultConfig;
import com.food.core.entity.BaseFood;
import com.food.core.entity.BaseOrder;
import com.food.core.entity.BaseUser;
import com.food.core.entity.FoodType;
import com.food.core.entity.OrderProduct;
import com.food.core.mapper.*;
import com.food.core.pack.PageInfo;
import com.food.core.pack.ResponseCollection;
import com.food.core.pack.ResponseResult;
import com.food.core.req.vo.LoginReqVo;
import com.food.core.req.vo.OrderInReqVo;
import com.food.core.req.vo.OrderReqVo;
import com.food.core.req.vo.RegisterReqVo;
import com.food.core.res.vo.*;
import com.food.core.util.JwtUtil;
import com.food.core.util.MoneyUtils;
import com.food.core.util.PassWordUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {

    @Autowired
    BaseUserMapper baseUserMapper;

    @Autowired
    SystemDefaultConfig systemDefaultConfig;

    @Autowired
    BaseOrderMapper baseOrderMapper;

    @Autowired
    BaseFoodMapper foodMapper;

    @Autowired
    OrderProductMapper orderProductMapper;

    @Autowired
    FoodTypeMapper foodTypeMapper;

    @Autowired
    BaseFoodMapper baseFoodMapper;

    public ResponseResult<LoginResVo> login(LoginReqVo vo){
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("code",vo.getCode());
        List<BaseUser> users = baseUserMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(users)){
            return ResponseResult.failNotice("用户不存在");
        }
        BaseUser user = users.get(0);
        if(!PassWordUtils.validatePassword(vo.getPwd(),user.getPwd())){
            return ResponseResult.failNotice("密码不正确");
        }
        JwtPayLoad payLoad =  new JwtPayLoad();
        payLoad.setUserId(user.getId());
        String currentToken = JwtUtil.createJWT(IdUtil.objectId(), BeanUtil.beanToMap(payLoad), Long.parseLong(systemDefaultConfig.getTokenTime()));
        LoginResVo loginResVo = new LoginResVo();
        loginResVo.setName(user.getName());
        loginResVo.setHand(systemDefaultConfig.getHeading());
        loginResVo.setToken(currentToken);
        return ResponseResult.success(loginResVo);
    }

    public ResponseResult<LoginResVo> register(RegisterReqVo vo){
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("code",vo);
        List<BaseUser> baseUser = baseUserMapper.selectList(wrapper);
        if(!CollectionUtils.isEmpty(baseUser)){
            return ResponseResult.failNotice("账号已存在");
        }
        BaseUser baseUser1 = new BaseUser();
        baseUser1.setCode(vo.getCode());
        baseUser1.setName(vo.getName());
        baseUser1.setStatus(1);
        baseUser1.setPwd(PassWordUtils.entryptPassword(vo.getPwd()));
        baseUserMapper.insert(baseUser1);
        JwtPayLoad payLoad =  new JwtPayLoad();
        payLoad.setUserId(baseUser1.getId());
        String currentToken = JwtUtil.createJWT(IdUtil.objectId(), BeanUtil.beanToMap(payLoad), Long.parseLong(systemDefaultConfig.getTokenTime()));
        LoginResVo vo1 = new LoginResVo();
        vo1.setHand(systemDefaultConfig.getHeading());
        vo1.setName(baseUser1.getName());
        vo1.setToken(currentToken);
        return ResponseResult.success(vo1);
    }

    @Transactional
    public ResponseResult<OrderResVo> zfb(OrderInReqVo vo){
        BaseOrder baseOrder = new BaseOrder();
        baseOrder.setOrderNo(IdUtil.createSnowflake(1,1).nextIdStr());
        baseOrder.setPrice(MoneyUtils.yuanTofen(vo.getPrice()).intValue());
        baseOrder.setUserId(vo.getId());
        baseOrder.setStatus(1);
        baseOrder.setCreateTime(new Date());
        baseOrderMapper.insert(baseOrder);
        for (OrderReqVo o : vo.getVos()) {
            BaseFood food = foodMapper.selectById(o.getId());
            if(food == null){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponseResult.failNotice("商品异常 无法进行支付");
            }
            OrderProduct p = new OrderProduct();
            p.setOrderNo(baseOrder.getOrderNo());
            p.setCreateTime(new Date());
            p.setFoodNo(o.getId());
            p.setNum(o.getNum());
            p.setPrice(food.getPrice());
            orderProductMapper.insert(p);
        }
        OrderResVo resVo = new OrderResVo();
        resVo.setId(baseOrder.getOrderNo());
        resVo.setImg(systemDefaultConfig.getZfb());
        return ResponseResult.success(resVo);
    }

    public ResponseResult<OrderResVo> yes(String id){
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("order_no",id);
        List<BaseOrder> orders = baseOrderMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(orders)){
            return ResponseResult.failNotice("订单不存在");
        }
        BaseOrder order = orders.get(0);
        order.setStatus(2);
        order.setUpdateTime(new Date());
        baseOrderMapper.updateById(order);
        return ResponseResult.successNotice("支付回执成功");
    }

    public ResponseCollection<OrderListResVo> orderList(String id){
        List<OrderListResVo> vos = baseOrderMapper.findOrder(id);
        if(CollectionUtils.isEmpty(vos)){
            return ResponseCollection.success(new ArrayList<>());
        }
        return ResponseCollection.success(vos);
    }

    public ResponseCollection<TypeResVo> type(){
        EntityWrapper wrapper = new EntityWrapper();
        List<FoodType> types = foodTypeMapper.selectList(wrapper);
        List<TypeResVo> vos = new ArrayList<>();
        if(CollectionUtils.isEmpty(types)){
            return ResponseCollection.success(vos);
        }
        for (FoodType type : types) {
            TypeResVo vo = new TypeResVo();
            vo.setId(type.getId());
            vo.setName(type.getName());
            vos.add(vo);
        }
        return ResponseCollection.success(vos);
    }

    public ResponseCollection<FoodListResVo> food(String type, PageInfo page){
        EntityWrapper wrapper = new EntityWrapper();
        if(!StringUtils.isEmpty(type)){
            wrapper.eq("type",type);
        }
        Page<BaseFood> resVos = PageHelper.startPage(page.getPageNo(), page.getPageSize())
                .doSelectPage(() -> baseFoodMapper.selectList(wrapper));
        List<BaseFood> shopResVos = resVos.getResult();
        page.setTotal(resVos.getTotal());
        page.setPages(resVos.getPages());
        List<FoodListResVo> resIsNull = new ArrayList<>();
        if (CollectionUtil.isEmpty(shopResVos)) {
            return ResponseCollection.success(resIsNull, page);
        }
        List<FoodListResVo> vos = new ArrayList<>();
        for (BaseFood resVo : resVos) {
            FoodListResVo vo = new FoodListResVo();
            vo.setId(resVo.getId());
            vo.setImg(resVo.getImg());
            vo.setName(resVo.getName());
            vo.setPrice(MoneyUtils.fenToyuan(Long.valueOf(resVo.getPrice())));
            vos.add(vo);
        }
        return ResponseCollection.success(vos, page);
    }

}
