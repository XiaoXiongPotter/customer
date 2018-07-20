package com.dognessnetwork.customer.service.impl;
import com.dognessnetwork.customer.domain.MessageStatus;
import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.dto.Msg;
import com.dognessnetwork.customer.service.api.MessagesService;
import com.dognessnetwork.customer.util.WebSocketClient;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * = MessagesServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = MessagesService.class)
public class MessagesServiceImpl implements MessagesService {
		@Autowired
		WebSocketClient	webSocketClient;
		@Autowired
		HttpSession	session;
		/**
		 * 发送消息若信息实体的id为空返回0表示失败
		 */
		public	String customerAccess(Msg	msg){
			//通过房间号ID查询将要接收的人的ID
			
			if(msg.getToUser()==null){
				return	"0";
			}else{
				webSocketClient.customerSend("/"+msg.getToUser(), msg);
				return	"1";
			}
			
		};
		/**
		 * 用户请求人工服务
		 */
		/*public	String requestService(Long	optimalCustomerId,MessagePerson	petUser){
					//请求接入服务
					Msg	chatMessage	=	new Msg();
					//chatMessage.setUserName(petUser.getUserName());
					
					
					chatMessage.setMsgType("text");
					chatMessage.setContent("reqSeat");
					
					Console.log("请求的客服》》"+optimalCustomerId);
					//Long	room	=	customerCareRepository.findOne(optimalCustomerId).getCustomer().getId();
					//Console.log("请求的聊天室》》"+room);
					chatMessage.setFromUser(petUser.getId());
					//chatMessage.setToUser(room);
					if(chatMessage.getToUser()==null){
						return	"0";
					}else{
						//webSocketClient.send("/"+room, chatMessage);
						//webSocketClient.customerSend("/"+room, chatMessage);
						return	"1";
					}
		}*/

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Messages
     */
    @Transactional
    public Messages save(Messages entity) {
    	Messages	newMessages	=	getMessagesRepository().save(entity);
    	Msg	msg	=	new	Msg();
		msg.setContent(newMessages.getPostMessages());
		msg.setFromUser(newMessages.getFormUser());
		msg.setToUser(newMessages.getToUser());
		msg.setUserName(newMessages.getFormUser().substring(3));
		msg.setMsgType("text");
		msg.setMsgId(newMessages.getId());
		msg.setSendTime(newMessages.getSendTime());
		webSocketClient.customerSend("/"+msg.getToUser(), msg);
        return newMessages;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Messages
     */
    @Transactional
    public Messages updateStatus(Long	sendTime) {
    	Messages	msg	=	getMessagesRepository().findBySendTime(sendTime);
    	msg.setMessageStatus(MessageStatus.success);
    	return	getMessagesRepository().save(msg);
    }
}
