package com.food.core.req.vo;

import com.food.core.anno.Verify;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderInReqVo implements Serializable {
    private static final long serialVersionUID = -1157043604146326012L;

    @Verify
    private String price;

    private String id;

    @Verify
    private List<OrderReqVo> vos;
}
