package com.dognessnetwork.customer.repository;

import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.dognessnetwork.customer.domain.ChatRoom;

/**
 * = ChatRoomRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */ 
@RooJpaRepositoryCustomImpl(repository = ChatRoomRepositoryCustom.class)
public class ChatRoomRepositoryImpl extends QueryDslRepositorySupportExt<ChatRoom> {

    /**
     * TODO Auto-generated constructor documentation
     */
    ChatRoomRepositoryImpl() {
        super(ChatRoom.class);
    }
}