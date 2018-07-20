package com.dognessnetwork.customer.service.impl;
import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.service.api.ChatRoomService;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;

/**
 * = ChatRoomServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = ChatRoomService.class)
public class ChatRoomServiceImpl implements ChatRoomService {
	@Override
	public	ChatRoom	findBySeat(String	seat){
		ChatRoom	chatRoom	=	getChatRoomRepository().findBySeat(seat);
		return	chatRoom;
	}
	@Override
	public	ChatRoom	findByPetUser(String	petUserName){
		ChatRoom	chatRoom	=	getChatRoomRepository().findByPetUser(petUserName);
		return	chatRoom;
	}
}
