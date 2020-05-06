package com.food.core.req.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderReqVo implements Serializable {
    private static final long serialVersionUID = -5481529174138817057L;

    private String id;

    private Integer num;


}
