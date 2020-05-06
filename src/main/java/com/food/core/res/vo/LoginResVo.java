package com.food.core.res.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResVo implements Serializable {
    private static final long serialVersionUID = -7616532749935612723L;

    private String name;

    private String token;

    private String hand;
}
