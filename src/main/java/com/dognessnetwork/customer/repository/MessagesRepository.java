package com.dognessnetwork.customer.repository;
import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.Messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;

/**
 * = MessagesRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Messages.class)
public interface MessagesRepository extends JpaRepository<Messages, Long> {
	Messages	findBySendTime(Long	sendTime);
}
