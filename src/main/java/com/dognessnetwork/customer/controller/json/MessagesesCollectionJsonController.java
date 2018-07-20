package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.MessageStatus;
import com.dognessnetwork.customer.domain.MessageType;
import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.dto.Msg;
import com.dognessnetwork.customer.service.api.MessagesService;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/js/messageses",name = "MessagesesCollectionJsonController",produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = MessagesesCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = Messages.class, pathPrefix = "js", type = ControllerType.COLLECTION)
@RooJSON
public class MessagesesCollectionJsonController {
	/**
	 * 发送消息
	 * @param formUser
	 * @param toUser
	 * @param postMessages
	 * @return
	 */
	@PostMapping("/sendMsg")
	@ResponseBody
	public	String	sendMsg(
			@RequestParam("formUser")String	formUser,
			@RequestParam("toUser")String	toUser,
			@RequestParam("postMessages")String	postMessages){
		Console.log("formUserId"+formUser+">>>"+"toUserId"+toUser+"postMessages"+postMessages);
		//PetUser	formUser	=	petUserService.findOne(formUserId);
		Messages	messages	=	new	Messages();
		messages.setFormUser(formUser);
		messages.setPostMessages(postMessages);
		messages.setToUser(toUser);
		messages.setMessageType(MessageType.Text);
		messages.setSendTime(new	Date().getTime());
		Messages	newMessages	=	getMessagesService().save(messages);
		Console.log(newMessages);
		JSONObject	js	=	new	JSONObject();
			js.put("res", "1");
		if(newMessages!=null){
			js.put("res", "0");
		}
		return	js+"";
	}
	/**
	 * 更新消息状态
	 * js/messageses/updateStatus
	 * @param sendTime
	 * @return
	 */
	@PostMapping("/updateStatus")
	@ResponseBody
	public	JSONObject	updateStatus(@RequestParam("sendTime")Long	sendTime){
		Console.log("时间");
		Console.log(sendTime);
		Messages	msg	=	getMessagesService().updateStatus(sendTime);
		JSONObject	js	=	new	JSONObject();
		if(msg!=null){
			
			js.put("res", msg.getMessageStatus());
			return	js;
		}else{
			js.put("res", MessageStatus.failure);
			return	js;
		}
		
	}
}
