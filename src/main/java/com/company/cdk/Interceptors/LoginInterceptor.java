package com.company.cdk.Interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import com.company.cdk.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		String requestURI = request.getRequestURI();
////		if ("/".equals(requestURI) || "/error".equals(requestURI) || ("/login".equals(requestURI)) || ("/userlogin".equals(requestURI))) {
////            return true;
////        }
		
		User user = (User) request.getSession().getAttribute("loginuser");
		if (user == null || user.getEmail() == null) {
			response.sendRedirect("/login");
			return false;
		}

		return true;
	}	
}
