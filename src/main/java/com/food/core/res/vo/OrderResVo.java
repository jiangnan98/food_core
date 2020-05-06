package com.food.core.res.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderResVo implements Serializable {
    private static final long serialVersionUID = 5208125962283060508L;

    private String id;

    private String img;
}
