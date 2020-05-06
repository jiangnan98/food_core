package com.food.core.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.food.core.entity.BaseOrder;
import com.food.core.res.vo.OrderListResVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2020-05-06
 */
@Repository
public interface BaseOrderMapper extends BaseMapper<BaseOrder> {
    List<OrderListResVo> findOrder(String userId);
}
