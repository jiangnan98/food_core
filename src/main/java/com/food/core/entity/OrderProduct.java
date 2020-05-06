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
@TableName("order_product")
public class OrderProduct extends Model<OrderProduct> {

    private static final long serialVersionUID = 1L;


    private String id;

    @TableField("order_no")
    private String orderNo;

    @TableField("food_no")
    private String foodNo;

    private Integer num;

    private Integer price;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
