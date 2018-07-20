package com.dognessnetwork.customer.dto;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.roo.addon.dto.annotations.RooDTO;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;

/**
 * = ChatMessage
 TODO Auto-generated class documentation
 *
 */
@RooDTO
@RooJavaBean
public class ChatMessage {
	private	Long	id;
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
	/**
	 * 用户头像
	 */
	private	String	avatar;
	/**
	 * 是否是我发的
	 */
	private	boolean	mine;
	
	public ChatMessage() {
		this.sendTime = new Date().getTime();
	}

	public ChatMessage(String content) {
		this("anonymous", content);
	}

	public ChatMessage(String userName, String content) {
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", userName=" + userName + ", content=" + content + ", sendTime=" + sendTime
				+ ", avatar=" + avatar + ", mine=" + mine + "]";
	}

	
}
