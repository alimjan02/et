package com.et.pro.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.et.pro.user.entity.User;

public class PublicUtils {

	public static User getCurrentUserInfo() {
		return (User) getRequest().getSession().getAttribute("USER");
	}
	
	public static int getCurrentUserId() {
		if (getCurrentUserInfo()==null) {
			return 0;
		}
		return getCurrentUserInfo().getId();
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static String getServerPath() {
		String contextPath = PublicUtils.getRequest().getContextPath();
		String basePath = PublicUtils.getRequest().getScheme() + "://" + PublicUtils.getRequest().getServerName() + ":"
				+ PublicUtils.getRequest().getServerPort() + contextPath + "/";
		return basePath;
	}

	public static String getClientIp() {
		try {
			String ip = PublicUtils.getRequest().getHeader("x-forwarded-for");
			if (!checkIP(ip)) {
				ip = PublicUtils.getRequest().getHeader("Proxy-Client-IP");
			}
			if (!checkIP(ip)) {
				ip = PublicUtils.getRequest().getHeader("WL-Proxy-Client-IP");
			}
			if (!checkIP(ip)) {
				ip = PublicUtils.getRequest().getRemoteAddr();
			}
			return ip;
		} catch (Exception e) {
			return "";
		}

	}

	private static boolean checkIP(String ip) {
		if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip) || ip.split(".").length != 4) {
			return false;
		}
		return true;
	}

	public static String getClientWebType() {
		return PublicUtils.getRequest().getHeader("user-agent");
	}
}
