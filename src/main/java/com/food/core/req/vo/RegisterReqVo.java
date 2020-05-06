package com.food.core.req.vo;

import com.food.core.anno.Verify;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterReqVo implements Serializable {
    private static final long serialVersionUID = -2818053927205694632L;

    @Verify
    private String name;

    @Verify
    private String code;

    @Verify
    private String pwd;

}
