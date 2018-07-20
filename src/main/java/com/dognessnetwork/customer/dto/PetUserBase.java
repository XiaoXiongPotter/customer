package com.dognessnetwork.customer.dto;
import java.io.Serializable;

import org.springframework.roo.addon.dto.annotations.RooDTO;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;

/**
 * = PetUserBase
 TODO Auto-generated class documentation
 *
 */
@RooDTO
@RooJavaBean
public class PetUserBase implements Serializable{
	private static final long serialVersionUId = 1L;
	private	Long	id;
	private	String	username;
	private	String	status;
	private	String	sign;
	private	String	avatar;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Override
	public String toString() {
		return "PetUserBase [id=" + id + ", username=" + username + ", status=" + status + ", sign=" + sign
				+ ", avatar=" + avatar + "]";
	}
	
	
}
