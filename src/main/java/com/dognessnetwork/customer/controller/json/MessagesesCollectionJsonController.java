package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.MessageStatus;
import com.dognessnetwork.customer.domain.MessageType;
import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.dto.Msg;
import com.dognessnetwork.customer.service.api.MessagesService;
import com.dognessnetwork.customer.util.SendImg;
import com.dognessnetwork.customer.util.StringToNumber;
import com.dognessnetwork.customer.util.WebSocketClient;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Autowired
    WebSocketClient webSocketClient;
    @Value("${document.image}")
    private String uploadImagePath;
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
		Long  time    =   new   Date().getTime();
		Messages	messages	=	new	Messages();
		messages.setFormUser(formUser);
		messages.setPostMessages(postMessages);
		messages.setToUser(toUser);
		messages.setMessageType(MessageType.Text);
		messages.setSendTime(time);
		
		if(postMessages.equals("人工")){
		    Msg   msg =   new Msg();
	        msg.setContent(postMessages);
	        msg.setFromUser(formUser);
	        msg.setToUser(toUser);
	        msg.setUserName(formUser.substring(3));
	        msg.setMsgType(MessageType.Text.toString());
	        
	        msg.setMsgId(Long.parseLong(StringToNumber.letterToNumber(postMessages)+""));
	        msg.setSendTime(time);
		    webSocketClient.customerSend("/"+msg.getToUser(), msg);
		    JSONObject  js  =   new JSONObject();
		    js.put("res", "0");
		    return  js+"";
        }else{
            Messages    newMessages =   getMessagesService().save(messages);
            Console.log(newMessages);
            JSONObject  js  =   new JSONObject();
                js.put("res", "1");
            if(newMessages!=null){
                js.put("res", "0");
            }
            return  js+"";
        }
		
	}
	@PostMapping("/sendImg")
    @ResponseBody
    public  String  sendImg(
            HttpServletRequest  request,
            @RequestParam("formUser")String formUser,
            @RequestParam("toUser")String   toUser,
            @RequestParam("postMessages")String postMessages){
        Console.log("formUserId"+formUser+">>>"+"toUserId"+toUser+"postMessages"+postMessages);
        //PetUser   formUser    =   petUserService.findOne(formUserId);
        //SendImg.get_Image_base(request, postMessages);
        Messages    messages    =   new Messages();
        messages.setFormUser(formUser);
        messages.setPostMessages(SendImg.get_Image_base(request, postMessages,uploadImagePath));
        messages.setToUser(toUser);
        messages.setMessageType(MessageType.Picture);
        messages.setSendTime(new    Date().getTime());
        Messages    newMessages =   getMessagesService().save(messages);
        Console.log(newMessages);
        JSONObject  js  =   new JSONObject();
            js.put("res", "1");
        if(newMessages!=null){
            js.put("res", "0");
        }
        return  js+"";
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
