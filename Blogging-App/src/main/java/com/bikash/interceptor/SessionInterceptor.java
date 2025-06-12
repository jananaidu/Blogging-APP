package com.bikash.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bikash.constant.AppConst;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SessionInterceptor implements HandlerInterceptor {
	
	@Autowired
	private HttpSession session;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(session.getAttribute(AppConst.STR_USER_ID)==null)
		{
			//if session is then redirect the control to home page
			response.sendRedirect(AppConst.STR_REDIRECT_HOME);
			return false;
		}
		return true;
	}
}
