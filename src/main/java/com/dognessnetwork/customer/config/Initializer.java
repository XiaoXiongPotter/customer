package com.dognessnetwork.customer.config;

import org.apache.catalina.util.SessionConfig;
import org.springframework.security.access.SecurityConfig;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import com.querydsl.core.annotations.Config;

public class Initializer extends AbstractHttpSessionApplicationInitializer{
	/*public Initializer() {
		super(SecurityConfig.class, Config.class); 
	}*/
}
