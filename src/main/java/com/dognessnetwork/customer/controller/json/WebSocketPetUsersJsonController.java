package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.RoomStatus;
import com.dognessnetwork.customer.dto.ChatMessage;
import com.dognessnetwork.customer.dto.Msg;
import com.dognessnetwork.customer.service.api.ChatRoomService;
import com.dognessnetwork.customer.service.api.MessagesService;
import com.dognessnetwork.customer.util.WebSocketClient;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/js/status",name = "WebSocketPetUsersJsonController",produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = PetUsersCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = ChatRoom.class, pathPrefix = "js", type = ControllerType.COLLECTION)
@RooJSON
public class WebSocketPetUsersJsonController {
	@Autowired
	HttpSession	session;
	@Autowired
	MessagesService	messagesService;
	@Autowired
	ChatRoomService	chatroomService;
	
	/*@Autowired
	com.dognessnetwork.customer.util.BalancedDistribution	BalancedDistribution;*/
	/**
	 * /js/status/offline
	 * 聊天室下线状态更新
	 * @return
	 */
	@PostMapping("/offline")
	@ResponseBody
	public	String	update(@RequestBody	String	req){
		Console.log(req);
		ChatRoom	res	=	null;
		String	username	=	req.substring(req.lastIndexOf("=")+1, req.length());
		if(username.startsWith("CSD")){
			String	seat	=	username.replaceAll("CSD", "");
			ChatRoom	chatRoom	=	chatroomService.findBySeat(seat);
			chatRoom.setStatus(RoomStatus.下线);
			res	=	chatroomService.save(chatRoom);
        }
		JSONObject re = JSONUtil.parseObj(res);
		return	re+"";
	}
	
	/**//**
	 * 接入时获取用户详细信息
	 * @param id
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	@GetMapping("/getSnsapiUserInfo")
	public	JSONObject	getSnsapiUserInfo(@RequestParam("id")	Long	id,@RequestParam("seat")	Long	seat_id){
		//坐席接入用户
		PetUser	seat	=	petUserServiceImpl.findOne(seat_id);
		CustomerCare	customerCare	=	customerCareService.findByCustomerId(seat.getId(), null).getContent().get(0);
		Chatroom	chatRoome	=	chatroomService.findBySeat(customerCare, null).getContent().get(0);
		PetUser	petUser	=	petUserServiceImpl.findOne(id);
		chatRoome.setCustomerService(petUser);
		Chatroom	chatRoom	=	chatroomService.save(chatRoome);
		//接入成功后清除排队列表中的用户，向用户通知谁在为他服务
		ServletContext application = session.getServletContext();
		Set<Long> queueList = new HashSet<Long>();
    	session.setAttribute("queue", petUser);
    	queueList = (Set<Long>)application.getAttribute("queueList");
    	queueList.remove(id);
	    application.setAttribute("queueList", queueList);
	    
	    Msg	msg	=	new	Msg();
		msg.setContent(seat.getUserName()+"为您服务");
		msg.setFromUser(seat.getId());
		msg.setToUser(id);
		msg.setUserName(seat.getUserName());
		msg.setMsgType("jr");
		msg.setMsgId(1L);
		String	res	=	messagesService.customerAccess(msg);
		
		JSONObject	js	=	new	JSONObject();
		js.put("id", petUser.getId());
		js.put("username", petUser.getUserName());
		js.put("status", petUser.getEnable());
		js.put("sign", chatRoom.getId());
		js.put("avatar", "/layui/images/img/tu2.png");
		return	js;
	}*/
}
