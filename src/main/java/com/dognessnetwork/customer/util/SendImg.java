package com.dognessnetwork.customer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ImageUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class SendImg {

    /**
     * 聊天发送图片工具
     * @param mrequest
     * @param data
     * @param uploadImagePath
     * @return
     */
    public  static String get_Image_base(HttpServletRequest mrequest,String data,String uploadImagePath){
        Console.log(data);
        if (data == null)
            return "f";
           String path = mrequest.getSession().getServletContext().getRealPath("/");
           File filePath = new File(path+System.currentTimeMillis()+".png");
           String result=null;
           try {
                ImageIO.write(ImageUtil.toImage(data),"png",filePath);
                HashMap<String, Object> paramMap = new HashMap<>();
                paramMap.put("file", FileUtil.file(filePath.getPath()));
                result= HttpUtil.post(uploadImagePath, paramMap);
                
            } catch (IORuntimeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           Console.log(result);
           JSONObject  js  =   JSONUtil.parseObj(result);
           return  js.get("data")+"";
    }
}
