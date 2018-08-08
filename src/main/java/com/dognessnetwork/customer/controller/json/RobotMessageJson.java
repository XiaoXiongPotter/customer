package com.dognessnetwork.customer.controller.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dognessnetwork.customer.util.Robot;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ImageUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

@RestController
@RequestMapping(value = "/js/robot",name = "RobotMessageJson")
public class RobotMessageJson {
    @Value("${document.image}")
    private String sendImage;
	/**
	 * /js/robot/get_message_robot
	 * @param content
	 * @return
	 */
	@PostMapping("/get_message_robot")
	@ResponseBody
	public	String	get_message_robot(String	content){
		Console.log(content);
		Robot	robot	=	new	Robot();
		String	value	=	robot.getMessage(content);
		Console.log(value);
		
		return	value;
	}
	/**
	 * 向机器人发送图片聊天
	 * @param mrequest
	 * @param data
	 * @return
	 */
	@SuppressWarnings("static-access")
    @PostMapping("/get_Img_robot")
    @ResponseBody
	public String  get_Img_robot(HttpServletRequest mrequest,String    data){
	    Console.log(data);
	    if (data == null)
	        return "f";
	       String path = mrequest.getSession().getServletContext().getRealPath("/");
	       File filePath = new File(path+System.currentTimeMillis()+".png");
	       String value=null;
	       try {
                ImageIO.write(ImageUtil.toImage(data),"png",filePath);
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("file", FileUtil.file(filePath.getPath()));
                String  result= HttpUtil.post(sendImage, paramMap);
                Console.log(result);
                JSONObject  js  =   JSONUtil.parseObj(result);
                JSONObject  header  =   JSONUtil.parseObj(js.get("header"));
                if(header.get("code").equals(1000)){
                    Robot   robot   =   new Robot();
                    value   =   robot.getImage(js.get("data")+"");
                    Console.log(value);
                }
            } catch (IORuntimeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	       return  value;
	}
}
