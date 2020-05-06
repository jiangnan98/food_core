package com.food.core.contests;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * 系统默认属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SystemDefaultConfig {

	private String heading; // 默认用户头像

	private String namePrefix;// 默认用户名前缀

	private String callBackUrl;// 回调地址系统前缀

	private String customerPhone;// 客服电话

	private String tokenTime;//登录过期时间

	private String sign;

	private String appId;

	private String zfb;

	private Boolean isProd;//是否正式环境

}
