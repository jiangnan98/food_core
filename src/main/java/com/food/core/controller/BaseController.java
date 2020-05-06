package com.food.core.controller;

import cn.hutool.core.bean.BeanUtil;
import com.food.core.adapter.JwtPayLoad;
import com.food.core.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 基本属性处理
 */
public class BaseController {

	@Autowired
	private HttpServletRequest request;

	String getSysUserId(){
		String token = request.getHeader("token");
		Claims claims = JwtUtil.parseJWT(token);
		JwtPayLoad payLoad = BeanUtil.mapToBean(claims, JwtPayLoad.class, true);
		return payLoad.getUserId();
	}

}
