package com.dognessnetwork.customer.repository;

import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.dognessnetwork.customer.domain.Messages;

/**
 * = MessagesRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */ 
@RooJpaRepositoryCustomImpl(repository = MessagesRepositoryCustom.class)
public class MessagesRepositoryImpl extends QueryDslRepositorySupportExt<Messages> {

    /**
     * TODO Auto-generated constructor documentation
     */
    MessagesRepositoryImpl() {
        super(Messages.class);
    }
}