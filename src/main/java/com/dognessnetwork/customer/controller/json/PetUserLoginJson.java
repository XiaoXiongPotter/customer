package com.dognessnetwork.customer.controller.json;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.dognessnetwork.customer.service.api.RedisService;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;

@RestController
@RequestMapping(value = "/js/petUser",name = "PetUserLoginJson")
public class PetUserLoginJson {
	/*@Autowired
    RedisService redisService;*/
	@Autowired
	HttpSession	session;
	/**
	 * /js/petUser/get_login_petUser
	 * 用给定的kEY从redis中获取value
	 * @return
	 */
	@GetMapping("/get_login_petUser")
	@ResponseBody
	public	JSONObject	get_login_petUser(){
		
		Console.log("获取用户信息");
		JSONObject	customer	=	(JSONObject)session.getAttribute("user");
		//customer.put("res", value);
		Console.log(customer);
		return	customer;
	}
	/**
	 * 获取客服信息
	 * @return
	 */
	@GetMapping("/get_customer")
	@ResponseBody
	public	String	get_customer(){
		Console.log("获取用户信息");
		String	value/*key*/	=	(String)session.getAttribute("user");
		//String	value	=	redisService.getStr(key);
		Console.log(value);
		
		return	value;
	}
}
