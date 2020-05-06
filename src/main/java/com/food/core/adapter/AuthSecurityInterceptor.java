package com.food.core.adapter;


import com.food.core.anno.Auth;
import com.food.core.pack.ResponseResult;
import com.food.core.pack.SystemCode;
import com.food.core.util.LogUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Desc 权限拦截器
 * @Author Wisty
 * @Date 2018年10月24日
 */
@Component
public class AuthSecurityInterceptor implements HandlerInterceptor {
	Logger log = LogUtils.getPlatformLogger();
	@Override
	public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
		boolean result = true;
		if(handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			Auth auth = method.getMethod().getAnnotation(Auth.class);
			if (auth != null) {
				String userId = request.getHeader("userId");// 用户登录id
				String token = request.getHeader("token");
				String type = request.getHeader("type"); // 客户端类型1---安卓 2---IOS 3---PC
				if(StringUtils.isBlank(userId) || StringUtils.isBlank(token) || StringUtils.isBlank(type)){
					result = false;
				}
			} else {
				result = true;
			}
			if (!result) {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().print(new Gson().toJson(new ResponseResult<>(SystemCode.NOT_LOGIN)));
			}
		}
		return result;
	}


	@Override
	public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object arg2, ModelAndView arg3) throws Exception {
	}
}
