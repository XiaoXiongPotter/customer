package com.dognessnetwork.customer.controller.json;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dognessnetwork.customer.service.api.RedisService;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@RestController
@RequestMapping(value = "/js/userChat", name = "UserChatJson")
public class UserChatJson {

    @Value("${userServer}")
    private String user;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @PostMapping("/get_user")
    public String get_user(String room) {
        String mine = redisService.getStr(room);
        // JSONObject js = JSONUtil.parseObj(mine);
        return mine;
    }

    @ResponseBody
    @PostMapping("/get_user_info")
    public String get_user_info(String token) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("x-auth-token", token);
        String result = HttpUtil.post(user + "/getLoginUser", paramMap);
        return result;
    }

    /**
     * /js/userChat/get_login_user 获取登陆用户信息
     * 
     * @param username
     * @return
     */
    @ResponseBody
    @PostMapping("/get_login_user")
    public String getLoginUser(String username, HttpServletRequest request) {
        Console.log(username);
        String mine = redisService.getStr(username);
        JSONObject js = JSONUtil.parseObj(mine);
        ;
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("x-auth-token", js.getStr("token"));
        String result = HttpUtil.post(user + "/getLoginUser", paramMap);

        return result;
    }
}
