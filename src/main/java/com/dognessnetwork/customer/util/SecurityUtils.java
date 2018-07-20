package com.dognessnetwork.customer.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;

import com.dognessnetwork.customer.dto.User;




public class SecurityUtils {
	public static User getUser(HttpSession session){
		SecurityContext a = (SecurityContext) session.getAttribute(session.getId());
		if(session.getAttribute(session.getId())==null){
			return null;
		}
		return (User) a.getAuthentication().getPrincipal();
	}
	/**
     * 根据名字获取cookie（接口方法）
     * 
     * @author 
     * @param request
     * @param name
     *            cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }
	/**
	 * 把redis中的sessionMap复制到appSession中
	 * @param request
	 * @param appSession
	 *//*
	public static void initSession(HttpServletRequest request, AppSession appSession) {
	    // 获取redis的session
	    Map<String, String> sessionMap = SessionUtils.getSessionMap(request);
	    // 如果redis没有找到session,表明已是登出状态,将老系统的appSession置为空
	    if (MapUtils.isEmpty(sessionMap)) {
	        appSession.set(PageParamConstant.LOGIN_ID, "");
	    } else {
	        // 避免重复赋值
	        if (!sessionMap.get(PageParamConstant.LOGIN_ID).equals(appSession.getStringValue(PageParamConstant.LOGIN_ID))) {
	            // 将sessionMap复制到appSession
	            appSession.set(PageParamConstant.LOGIN_ID, sessionMap.get(PageParamConstant.LOGIN_ID));// 登录用户
	            appSession.set(PageParamConstant.PASSWORD,DesCodeUtil.encrypt(sessionMap.get(PageParamConstant.PASSWORD)));//登录密码
	        }
	    }
	}*/


	/**
	 * 从redis中获取session
	 * @param request
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	public static Map<String, String> getSessionMap(HttpServletRequest request) {
	    String token = getToken(request);
	    Map<String, String> sessionMap = RedisUtil.getMap("session_"+token);

	    return sessionMap;
	}*/



	/**
	 * 获取cookie中的token
	 * @param request
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
	    String token = "";
	    Cookie c = getCookieByName(request, "wx_token");
	    if (c != null) {
	        token = c.getValue();
	    }

	    return token;
	}


	

	/**
	 * 将cookie封装到Map里面（非接口方法）
	 * 
	 * @author 
	 * @param request
	 * @return
	 */
	public static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
	    Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if (null != cookies) {
	        for (Cookie cookie : cookies) {
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
}
