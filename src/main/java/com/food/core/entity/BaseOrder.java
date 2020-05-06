package com.food.core.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiang
 * @since 2020-05-06
 */
@Data
@Accessors(chain = true)
@TableName("base_order")
public class BaseOrder extends Model<BaseOrder> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 订单号
     */
    @TableField("order_no")
    private String orderNo;

    /**
     * 总价格
     */
    private Integer price;

    /**
     * 1：未支付 2：已支付 3：已完成
     */
    private Integer status;


    @TableField("user_id")
    private String userId;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
