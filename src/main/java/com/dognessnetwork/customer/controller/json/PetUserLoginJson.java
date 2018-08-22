package com.dognessnetwork.customer.controller.json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dognessnetwork.customer.service.api.RedisService;
import com.dognessnetwork.customer.util.StringToNumber;

//import com.dognessnetwork.customer.service.api.RedisService;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@RestController
@RequestMapping(value = "/js/petUser",name = "PetUserLoginJson")
public class PetUserLoginJson {
	@Autowired
    RedisService redisService;
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
	 * /js/petUser/requestCustomerService
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/requestCustomerService")
    public JSONObject message(
            String    username,
            String token,
            HttpServletRequest    request,
            String  platForm,
            String  area) {
	    Console.log(username);
	    redisService.setStr(username, token);
	    //request.getSession().setAttribute(username, token);
        if(username==null)username="hello";
        String  key =   username;
        
        String  petUser    =   redisService.getStr(username);
        JSONObject  js  =   JSONUtil.parseObj(petUser);
        js.getStr("");
        js.getStr("token");
        js.getStr("platForm");
        js.getStr("area");
        js.getStr("");
        js.getStr("");
        JSONObject  mine    =   new JSONObject();
        mine.put("username", username);//我的昵称
        mine.put("id", StringToNumber.letterToNumber(username));//我的ID
        mine.put("status", "online");//在线状态 online：在线、hide：隐身
        mine.put("sign", "PFU");//我的签名
        mine.put("avatar", "/userchat/img/tu2.png");//我的头像
        mine.put("platForm", platForm);
        //redisService.setStr(key, mine+"");
        Console.log("登录");
        Console.log(mine);
        return /*"customer_service"*/mine;
    }
	/**
     * /js/petUser/get_x_auth_token
     * @param username
     * @return
     */
    @ResponseBody
    @RequestMapping("/get_x_auth_token")
    public JSONObject get_x_auth_token(String    username,HttpServletRequest    request) {
        Console.log(redisService.getStr(username));;
        //Console.log(request.getSession().getAttribute(username).toString());;
        JSONObject  res    =   new JSONObject();
        res.put("x-auth-token", redisService.getStr(username));//我的昵称
        return res;
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
