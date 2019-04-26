package com.et.pro.framework;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.et.pro.user.entity.User;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			if (request.getRequestURI().contains("/login")||request.getRequestURI().contains("/addUser")) {
				return true;
			}
			User u = (User) request.getSession().getAttribute("USER");
			if (u == null) {
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<script>");
				out.println("window.open ('login','_top');");
				out.println("</script>");
				out.println("</html>");
				return false;
			}
			// 判断后执行操作...
		}
		return true;
	}
	
}
