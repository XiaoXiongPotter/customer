package com.dognessnetwork.customer.dto;

import java.util.Collection;
import java.util.Set;

import org.springframework.roo.addon.dto.annotations.RooDTO;
import org.springframework.roo.addon.javabean.annotations.RooJavaBean;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
@RooDTO
@RooJavaBean
public class User extends org.springframework.security.core.userdetails.User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 500L;
	
	public User(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, true, true, true, true, authorities);
	}

	
	public User(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	@JsonIgnore
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return super.getAuthorities();
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return super.isAccountNonExpired();
	}
	
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return super.isAccountNonLocked();
	}
	
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return super.isCredentialsNonExpired();
	}
	
	
	
	
	private long id;
	private String name;
	
	private String mobile;
	
	private String email;
	
	private Integer status;
	
	private String creatTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	

	
	
}
