package com.dognessnetwork.customer.controller.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.RoomStatus;
import com.dognessnetwork.customer.service.api.ChatRoomService;
import com.dognessnetwork.customer.service.api.RedisService;
import com.dognessnetwork.customer.util.SecurityUtils;
//import com.dognessnetwork.customer.service.api.RedisService;
import com.dognessnetwork.customer.util.StringToNumber;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;

/**
 * 
 * @author Dogness
 *
 */
@Controller
public class HandlerController {
	/*@Autowired
    RedisService redisService;*/
	@Autowired
	HttpSession	session;
	@Autowired
	ChatRoomService	chatroomService;
	@Autowired
    RedisService    redisService;
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/uid")
    public String uid(HttpSession session) {
    	UUID uid = (UUID) session.getAttribute("uid");
    	if (uid == null) {
    		uid = UUID.randomUUID();
    	}
    	session.setAttribute("uid", uid);
    	return session.getId();
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest  request) {
        return /*"redirect:http://192.168.0.197:8088/users/index"*/"index";
    }
    
    @RequestMapping("/index")
    public String index1(String	username,HttpServletRequest	request,HttpServletResponse	response) {
    	Console.log(username);
    	//String	res	=	HttpUtil.post("http://192.168.0.197:8088/users/test","");
    	/*CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 创建HttpPost对象
        HttpPost post = new HttpPost(
                "http://192.168.0.197:8088/users/query_by_username;");
        post.setHeader("Cookie","JSESSIONID=" + redisService.getStr("sessionId"));
        // 3. 设置POST请求传递参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "lisisi"));
        //params.add(new BasicNameValuePair("password", "12356"));
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
            post.setEntity(entity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 4. 执行请求并处理响应
        try {
            CloseableHttpResponse response1 = httpClient.execute(post);
            HttpEntity entity = response1.getEntity();
            if (entity != null){
                System.out.println("响应内容：");
                System.out.println(EntityUtils.toString(entity));
            }
            response1.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    	Console.log("getId");
    	//Console.log(res);
    	
    	// String sessionId = request.getSession().getId();
    	 
    	 Cookie cookie = new Cookie("JSESSIONID", redisService.getStr("sessionId"));
    	 cookie.setPath(request.getContextPath());
    	 response.addCookie(cookie);
    	//SecurityUtils.
        /*Console.log(cookieMap);*/
    	SecurityUtils.getUser(session);
    	Console.log("用户信息：");
    	
    	Console.log(SecurityUtils.getCookieByName(request, "sessionId"));
    	//查看客服是否第一次登录，如果不是则创建客服的坐席，如果是则更新状态为在线
    	ChatRoom	chatRoom	=	chatroomService.findBySeat(username);
    	
    	if(chatRoom==null){
    		ChatRoom	newChatRoom	=	new	ChatRoom();
    		newChatRoom.setSeat(username);
    		newChatRoom.setSeatAt(System.currentTimeMillis());
    		newChatRoom.setStatus(RoomStatus.在线);
    		chatroomService.save(newChatRoom);
    	}else{
    	    Console.log("上线");
    	    chatRoom.setSeatAt(System.currentTimeMillis());
    		chatRoom.setStatus(RoomStatus.在线);
    		chatroomService.save(chatRoom);
    	}
    	String	key	=	username;
    	session.setAttribute("user", key);
    	JSONObject	mine	=	new	JSONObject();
		mine.put("username", username);//我的昵称
		mine.put("id", StringToNumber.letterToNumber(username));//我的ID
		mine.put("status", "online");//在线状态 online：在线、hide：隐身
		mine.put("sign", "CSD");//我的签名
		mine.put("avatar", "/layui/images/img/tu2.png");//我的头像
    	//redisService.setStr(key, mine+"");
    	Console.log("登录");
        return "index";
    }
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    /**
     * 用户请求客服入口
     * @param username
     * @return
     */
    @RequestMapping("/requestCustomerService")
    public String message(String	username) {
    	if(username==null)username="hello";
    	String	key	=	username;
		
		JSONObject	mine	=	new	JSONObject();
		mine.put("username", username);//我的昵称
		mine.put("id", StringToNumber.letterToNumber(username));//我的ID
		mine.put("status", "online");//在线状态 online：在线、hide：隐身
		mine.put("sign", "PFU");//我的签名
		mine.put("avatar", "/layui/images/img/tu2.png");//我的头像
		//redisService.setStr(key, mine+"");
		session.setAttribute("user", mine);
		Console.log("登录");
        return /*"customer_service"*/"userchat";
    }
    @RequestMapping(value = "/chatRoom", method = RequestMethod.GET)
    public String chatRoom(String	username) {
    	
    	String	key	=	username;
		session.setAttribute("user", key);
		JSONObject	mine	=	new	JSONObject();
		mine.put("username", username);//我的昵称
		mine.put("id", StringToNumber.letterToNumber(username));//我的ID
		mine.put("status", "online");//在线状态 online：在线、hide：隐身
		mine.put("sign", "PFU");//我的签名
		mine.put("avatar", "/layui/images/img/tu2.png");//我的头像
		//redisService.setStr(key, mine+"");
		Console.log("登录");
        return "chatroom";
    }
}