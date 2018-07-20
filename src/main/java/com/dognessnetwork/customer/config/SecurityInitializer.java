package com.dognessnetwork.customer.config;

import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.querydsl.core.annotations.Config;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	public SecurityInitializer() {
        super(SecurityConfig.class, Config.class);
}

}
