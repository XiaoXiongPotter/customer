package com.dognessnetwork.customer.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.service.api.CommentService;

import cn.hutool.core.lang.Console;
@Component
public class Avgrate {
    @Autowired
    CommentService  commentService;
    public  float get_avgrate(ChatRoom   chatRoom){
        
        int vote_1=0,vote_2=0,vote_3=0,vote_4=0,vote_5=0;
        
        for(int i=1;i<=5;i++){
            
            int num =   commentService.findByChatRoomAndStar(chatRoom, i, null).getContent().size();
            
            switch(i){
            case 1:
                vote_1  =   num;
            case 2:
                vote_2  =   num;
            case 3:
                vote_3  =   num;
            case 4:
                vote_4  =   num;
            case 5:
                vote_5  =   num;
            }
            
        }
        
        int NUM =   vote_1+vote_2+vote_3+vote_4+vote_5;
        if(NUM==0)return    0F;
        
        int sum =   vote_1*1+vote_2*2+vote_3*3+vote_4*4+vote_5*5;
        
        float  avg =   div((float)sum,(float)NUM,2);
        
        Console.log("平均分:"+avg*2);
        return  avg*2;
    }
    /**  
    * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。  
    *  
    * @param v1  
    *            被除数  
    * @param v2  
    *            除数  
    * @param scale  
    *            表示需要精确到小数点以后几位。  
    * @return 两个参数的商  
    */ 
   public float div(float v1, float v2, int scale) {   
       if (scale < 0) {   
           throw new IllegalArgumentException(   
                   "The scale must be a positive integer or zero");   
       }   
       BigDecimal b1 = new BigDecimal(Float.toString(v1));   
       BigDecimal b2 = new BigDecimal(Float.toString(v2));   
       return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue();   
   }
}
