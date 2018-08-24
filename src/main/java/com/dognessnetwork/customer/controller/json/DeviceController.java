package com.dognessnetwork.customer.controller.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dognessnetwork.customer.service.api.RedisService;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;

@RestController
@RequestMapping(value = "/js/device", name = "UserDevice")
public class DeviceController {

    @Value("${device}")
    private String device;

    @Value("${clientServer}")
    private String clientServer;

    @Autowired
    private RedisService redisService;

    /**
     * /js/device/get_all_device 获取用户的所有设备
     * 
     * @param username
     * @return
     */
    @ResponseBody
    @PostMapping("/get_all_device")
    public String get_all_device(String username) {
        Console.log("get_all_device", username);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("x-auth-token", redisService.getStr(username));
        String result = HttpUtil.post(device + "/devices/listByLoginUser", paramMap);
        Console.log(result);

        return result;
    }

    /**
     * /js/device/get_all_pet 获取用户有的宠物
     * 
     * @param username
     * @return
     */
    @ResponseBody
    @PostMapping("/get_all_pet")
    public String get_all_pet(String username) {
        Console.log("get_all_pet", username);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("x-auth-token", redisService.getStr(username));
        String result = HttpUtil.post(clientServer + "/pets/info/listPetByOwner", paramMap);
        Console.log(result);

        return result;
    }
}
