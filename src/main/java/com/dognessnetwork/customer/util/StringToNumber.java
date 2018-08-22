package com.dognessnetwork.customer.util;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;

public class StringToNumber {
	public	static int letterToNumber(String letter) {
	    int length = letter.length();
	    int num = 0;
	    int number = 0;
	    for(int i = 0; i < length; i++) {
	        char ch = letter.charAt(length - i - 1);
	        num = (int)(ch - 'A' + 1) ;
	        num *= Math.pow(26, i);
	        number += num;
	    }
	    if(number<0){
	    	return number*-1;
	    }else{
	    	return number;
	    }
	    
	}
	public static String stringToAscii(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    char[] chars = value.toCharArray();   
	    for (int i = 0; i < chars.length; i++) {  
	        if(i != chars.length - 1)  
	        {  
	            sbu.append((int)chars[i]).append(",");  
	        }  
	        else {  
	            sbu.append((int)chars[i]);  
	        }  
	    }  
	    return sbu.toString().replace(",", "");  
	}  
	/*public static void main(String[] args) {
		Console.log("123123");
		Console.log(HttpUtil.get("http://192.168.0.197:8087/users/hello"));
		//Console.log(letterToNumber("CSD707kfCSD"));;
	}*/
}
