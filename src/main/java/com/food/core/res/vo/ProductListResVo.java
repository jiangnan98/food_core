package com.food.core.res.vo;

import com.food.core.util.MoneyUtils;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductListResVo implements Serializable {
    private static final long serialVersionUID = -4656813051695421177L;

    private String id;

    private String img;

    private String name;

    private String num;

    private Long price;

    public String getPrice() {
        return MoneyUtils.fenToyuan(price);
    }
}
