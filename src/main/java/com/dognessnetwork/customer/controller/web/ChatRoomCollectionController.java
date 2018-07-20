package com.dognessnetwork.customer.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatRoomCollectionController {
	@GetMapping("/room")
	public	String	index(){
		
		return	"chatroom";
	}
}
