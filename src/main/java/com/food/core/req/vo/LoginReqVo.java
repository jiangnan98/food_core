package com.food.core.req.vo;

import com.food.core.anno.Verify;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginReqVo implements Serializable {
    private static final long serialVersionUID = 914822236100397063L;

    @Verify
    private String code;

    @Verify
    private String pwd;
}
