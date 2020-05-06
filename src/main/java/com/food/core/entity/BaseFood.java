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
@TableName("base_food")
public class BaseFood extends Model<BaseFood> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private Integer price;

    private String img;

    private String type;

    private Integer status;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
