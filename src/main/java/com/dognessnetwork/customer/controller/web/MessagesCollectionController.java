package com.dognessnetwork.customer.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MessagesCollectionController {
	@PostMapping("/send")
	public	String	send(){
		
		return	"redirect:http://weixin.qq.com/r/QyrH3_jE0LWvray8939R";
	}
}
