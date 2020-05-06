package com.food.core.res.vo;

import com.food.core.util.MoneyUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderListResVo implements Serializable {
    private static final long serialVersionUID = 6476298337362465802L;

    private String orderNo;

    private Long price;

    private String status;

    private String createTime;

    private String updateTime;

    private List<ProductListResVo> resVos;

    public String getPrice() {
        return MoneyUtils.fenToyuan(price);
    }
}
