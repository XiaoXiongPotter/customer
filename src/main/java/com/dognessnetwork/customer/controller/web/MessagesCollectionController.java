package com.dognessnetwork.customer.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MessagesCollectionController {
	@PostMapping("/send")
	public	String	send(){
		
		return	"redirect:http://weixin.qq.com/r/QyrH3_jE0LWvray8939R";
	}
}
