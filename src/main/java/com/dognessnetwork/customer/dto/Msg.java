package com.dognessnetwork.customer.dto;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.roo.addon.dto.annotations.RooDTO;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;

import com.dognessnetwork.customer.domain.MessageType;

/**
 * = ChatMessage
 TODO Auto-generated class documentation
 *
 */
@RooDTO
@RooJavaBean
public class Msg {
	/**
	 * 接收者
	 */
	private	String	toUser;
	/**
	 * 发送者
	 */
	private	String	fromUser;
	/**
	 * 消息类型
	 */
	private	String	msgType;
	/**
	 * 消息ID
	 */
	private	Long	msgId;
	/**
	 * 发送者昵称
	 */
	private String userName;
	/**
	 * 消息内容
	 */
	private String content;
	/**
	 * 发送的时间毫秒值
	 */
	private Long sendTime;
	
	public Msg() {
		this.sendTime = new Date().getTime();
	}

	public Msg(String content) {
		this("anonymous", content);
	}

	public Msg(String userName, String content) {
		this.userName = userName;
		this.content = content;
		this.sendTime = new Date().getTime();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSendTime() {
		return sendTime;
	}

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}


	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		return "Msg [toUser=" + toUser + ", fromUser=" + fromUser + ", msgType=" + msgType + ", msgId=" + msgId
				+ ", userName=" + userName + ", content=" + content + ", sendTime=" + sendTime + "]";
	}

	


}
