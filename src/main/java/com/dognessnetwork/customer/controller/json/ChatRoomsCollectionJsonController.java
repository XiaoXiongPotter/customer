package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.MessageType;
import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.dto.Msg;
import com.dognessnetwork.customer.service.api.ChatRoomService;
import com.dognessnetwork.customer.service.api.MessagesService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/js/chatrooms",name = "ChatRoomsCollectionJsonController",produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = ChatRoomsCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = ChatRoom.class, pathPrefix = "js", type = ControllerType.COLLECTION)
@RooJSON
public class ChatRoomsCollectionJsonController {
	@Autowired
	ChatRoomService	chatroomService;
	@Autowired
	MessagesService	messagesService;
	/**
	 * /js/chatrooms/get_petUser_is_accessing
	 * @param req
	 * @return
	 */
	@ResponseBody
	@PostMapping(value="/get_petUser_is_accessing",produces = "text/plain;charset=UTF-8")
	public	String	get_petUser_is_accessing(/*@RequestBody	String	req*/
			@RequestParam("array[]")	String[]	array){
		Console.log("获取到的在线用户列表");
		List<String>	list	=	CollUtil.newArrayList(array);
		JSONArray	accessing	=	new	JSONArray();
		for (String petUserName : list) {
			ChatRoom	chatRoom	=	chatroomService.findByPetUser(petUserName);
			if(chatRoom==null){
				accessing.put(petUserName.substring(3));
			}
		}
		/*List<String>	listaccess	=	new	ArrayList<>();
		list.forEach(petUserName->{listaccess.add(chatroomService.findByPetUser(petUserName).getPetUser());});
		
		if(listaccess.size()>0){
			listaccess.forEach(action);
		}*/
		
		Console.log(accessing);
		//返回正在请求的
		return	accessing+"";
	}
	/**
	 * 客服接入用户时向被接入的用户发送消息提醒
	 * @param petUser
	 * @param seat
	 * @return
	 */
	@ResponseBody
	@PostMapping("/customer_access")
	public	JSONObject	customer_access(@RequestParam("petUser")	String	petUser,
			@RequestParam("seat")	String	seat){
		Console.log(seat+"to"+petUser);
		Msg	msg=	new	Msg();
		msg.setContent(seat.substring(3)+"为您服务");
		msg.setFromUser(seat);
		if(petUser.startsWith("PFU")){
		    msg.setToUser(petUser);
		}else{
		    msg.setToUser("PFU"+petUser);
		}
		
		msg.setMsgType("text");
		msg.setMsgId(1L);
		msg.setUserName(seat.substring(3));
		Messages	messages	=	new	Messages();
		messages.setFormUser(seat);
		messages.setPostMessages(seat.substring(3)+"为您服务");
		if(petUser.startsWith("PFU")){
		    messages.setToUser(petUser);
        }else{
            messages.setToUser("PFU"+petUser);
        }
		
		messages.setMessageType(MessageType.Text);
		messages.setSendTime(new	Date().getTime());
		Messages  resMessage  =   messagesService.save(messages);
		JSONObject    res  =   new JSONObject();
		JSONObject    js  =   new JSONObject();
        js.put("id", 1);
        js.put("username",resMessage.getToUser().substring(3));
        js.put("status", true);
        js.put("sign", seat);
        js.put("avatar", "/layui/images/img/tu2.png");
        JSONObject    faile  =   new JSONObject();
        faile.put("info", "save data faile");
		if(resMessage!=null){
		    res.put("code", 0);
		    res.put("res", js);
		    return    res;
		}else{
		    res.put("code", 1);
		    res.put("res", faile);
		    return    res;
		}
		
	}
}
