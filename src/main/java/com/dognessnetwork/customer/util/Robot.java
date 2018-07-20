package com.dognessnetwork.customer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class Robot {
	//存储APIkey
    public static final String API_KEY = "8db7a483e91642268d33c3a00487ab87";
    //存储接口请求地址
    public static final String API_URL = "http://openapi.tuling123.com/openapi/api/v2";
    /**
     * 拼接出我们的接口请求地址
     *
     * @param msg 需要发送的消息
     * @return
     */
    private String setParameter(String msg) {
        //在接口请求中 中文要用URLEncoder encode成UTF-8
        try {
            return API_URL + "?key=" + API_KEY + "&info=" + URLEncoder.encode(msg, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拿到消息回复的内容的方法
     * @param json 请求接口得到的JSON
     * @return text的部分
     */
    private String getString(String json){
        
        return json;
    }

    /**
     * 提供对外公开的方法用于最终拿到机器人回复的消息{
	"reqType":0,
    "perception": {
        "inputText": {
            "text": "附近的酒店"
        },
        "inputImage": {
            "url": "imageUrl"
        },
        "selfInfo": {
            "location": {
                "city": "北京",
                "province": "北京",
                "street": "信息路"
            }
        }
    },
    "userInfo": {
        "apiKey": "",
        "userId": ""
    }
}
     * @param msg 传入你需要发送的信息
     * @return 机器人对你的回复
     */
    public String getMessage(String msg){
    	JSONObject	js	=	new	JSONObject();
    	JSONObject	perception	=	new	JSONObject();
    	JSONObject	inputText	=	new	JSONObject();
    	JSONObject	userInfo	=	new	JSONObject();
    	js.put("reqType", 0);
    	inputText.put("text", msg);
    	perception.put("inputText", inputText);
    	js.put("perception", perception);
    	userInfo.put("apiKey", API_KEY);
    	userInfo.put("userId", "007");
    	js.put("userInfo",userInfo);
    	
    	String	str	=	HttpUtil.post(API_URL, js.toString());
    	JSONObject	res	=	JSONUtil.parseObj(str);
        return getString(res.getJSONArray("results").toString());
    }


    private String getHTML(String url) {
        StringBuffer buffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            //创建URL对象
            URL u = new URL(url);
            //打开连接
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            //从连接中拿到InputStream并由BufferedReader进行读取
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            //循环每次加入一行HTML内容 直到最后一行
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //结束时候关闭释放资源
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
