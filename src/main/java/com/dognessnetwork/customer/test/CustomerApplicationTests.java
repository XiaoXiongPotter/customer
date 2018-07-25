package com.dognessnetwork.customer.test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.dognessnetwork.customer.CustomerApplication;
import com.dognessnetwork.customer.dto.Msg;
import com.dognessnetwork.customer.service.api.RedisService;
//import com.dognessnetwork.customer.service.api.RedisService;
import com.dognessnetwork.customer.util.Robot;
import com.dognessnetwork.customer.util.SecurityUtils;
import com.dognessnetwork.customer.util.WebSocketClient;

import ch.qos.logback.core.joran.action.AppenderAction;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;

import org.apache.http.Header;
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

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(CustomerApplication.class)
@SpringBootTest
public class CustomerApplicationTests {
	@Autowired
	HttpServletRequest mrequest;
	@Autowired
	WebSocketClient	client;
	@Autowired
	HttpSession	session;
	@Test
	public void contextLoads() {
		/*String path = mrequest.getSession().getServletContext().getRealPath("/");
		String	name	=	System.getProperty("os.name");
		System.out.print(name);
		System.out.println("++++++++++++");
		File	file	=	new	File(path+"/123.txt");
		file.getParentFile();
		System.out.println(file.getParentFile().getParent());
		File	file1	=	new	File(file.getParentFile().getParent()+"/img/123.txt");
		System.out.println(file1.getParentFile().getPath());*/
		/**************测试图片上传接口*****************/
		//HashMap<String, Object> paramMap = new HashMap<>();
	
		///文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
		//paramMap.put("file", FileUtil.file("E:/Pictures/tupian/dgmyq.jpg"));
		//paramMap.put("fileName", "A");
		//paramMap.put("type", "A");
		//paramMap.put("size",400);
		//paramMap.put("content", "https://www.dognessnetwork.com/qr=123");
		//String result= HttpUtil.post("https://www.dognessnetwork.com/document/qrCode_image", paramMap);
		//String result= HttpUtil.post("http://localhost/document/image_animal", paramMap);
		//Console.log(result);
		/***************测试消息服务器接口****************/
		Msg	msg=	new	Msg();
		msg.setContent("707为您服务");
		msg.setFromUser("707");
		msg.setToUser("PFUtml");
		msg.setMsgType("text");
		msg.setMsgId(1L);
		msg.setUserName("707");
		client.customerSend("/PFUtml", msg);
		/*Robot	robot	=	new	Robot();
		Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            //直接输出机器人的回复
            System.err.println("Ta 对你说 -> " + robot.getMessage(scanner.nextLine()));
        }*/
		/*String	res	=	HttpUtil.post("http://192.168.0.197:8088/users/test", "");
    	Console.log("用户信息：");
    	Console.log(res);*/
    	//Console.log(redisService.getStr("SPRING_SECURITY_CONTEXT"));
		/*Console.log("getId：");
		Console.log(session.getId());
		Console.log("用户信息：");
    	Console.log(SecurityUtils.getUser(session));
		;*/
	}
	
	/*@Autowired
    private StringRedisTemplate stringRedisTemplate; // 处理字符串
    @Autowired
    RedisService    redisService;
    @Autowired
    Header  header; // 处理对象
    @Test
    public void test() throws Exception {
        String  sessionId   =   redisService.getStr("sessionId");
        //Cookie cookie = new Cookie("JSESSIONID", redisService.getStr("sessionId"));
        //cookie.setPath(request.getContextPath());
        //HttpUtil.createPost("").body("username=lisisi").cookie(cookie.toString());
        stringRedisTemplate.opsForValue().set("yoodb", "123");
        //Assert.assertEquals("123", stringRedisTemplate.opsForValue().get("yoodb"));
        Console.log(stringRedisTemplate.opsForValue().get("sessionId"));
        Console.log(sessionId);
        // 1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 2. 创建HttpPost对象
        HttpPost post = new HttpPost(
                "http://192.168.0.197:8088/users/query_by_username");
        post.setHeader("Cookie","JSESSIONID=" + sessionId);
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
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null){
                System.out.println("响应内容：");
                System.out.println(EntityUtils.toString(entity));
            }
            response.close();
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
        }
    
    }*/
}
