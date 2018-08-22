package com.dognessnetwork.customer.config;
import com.dognessnetwork.customer.CustomerApplication;
import io.springlets.data.jpa.repository.support.DetachableJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryConfiguration;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = DetachableJpaRepositoryImpl.class,basePackageClasses = CustomerApplication.class)
/**
 * = SpringDataJpaDetachableRepositoryConfiguration
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryConfiguration
public class SpringDataJpaDetachableRepositoryConfiguration {
}
