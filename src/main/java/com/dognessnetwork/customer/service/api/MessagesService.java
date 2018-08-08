package com.dognessnetwork.customer.service.api;
import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.dto.Msg;

import org.springframework.roo.addon.layers.service.annotations.RooService;

/**
 * = MessagesService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = Messages.class)
public interface MessagesService {
	/**
	 * 客服接入用户
	 * @param msg
	 * @return
	 */
	public abstract String customerAccess(Msg msg);
	/**
	 * 更新消息状态
	 * @param entity
	 * @return
	 */
	public	abstract	Messages	updateStatus(Long	sendTime);

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Messages
     */
    public abstract Messages save(String    seat,String petUserName);
    /**
     * 请求人工客服
     * @param formUser
     * @param toUser
     * @param postMessages
     * @return
     */
    public  abstract    String  requestCustomerService(String   formUser,String    toUser,String    postMessages);
    
    public abstract String sendMsg(Messages messages);
}
