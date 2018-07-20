package com.dognessnetwork.customer.controller.json;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dognessnetwork.customer.util.Robot;

import cn.hutool.core.lang.Console;

@RestController
@RequestMapping(value = "/js/robot",name = "RobotMessageJson")
public class RobotMessageJson {
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
}
