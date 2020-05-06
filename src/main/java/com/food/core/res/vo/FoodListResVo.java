package com.food.core.res.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FoodListResVo implements Serializable {
    private static final long serialVersionUID = 4152127233552627768L;

    private String id;

    private String price;

    private String img;

    private String name;

}
