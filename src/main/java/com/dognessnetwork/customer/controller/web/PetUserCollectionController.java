package com.dognessnetwork.customer.controller.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/customer")
public class PetUserCollectionController {
	
	/**
	 * /customer/login
	 * @return
	 */
	@GetMapping("/login")
	public	String	login(){
		
		return	"register";
	}
	
}
