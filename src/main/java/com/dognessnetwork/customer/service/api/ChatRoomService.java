package com.dognessnetwork.customer.service.api;
import com.dognessnetwork.customer.domain.ChatRoom;
import org.springframework.roo.addon.layers.service.annotations.RooService;

/**
 * = ChatRoomService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = ChatRoom.class)
public interface ChatRoomService {
	ChatRoom	findBySeat(String	seat);
	ChatRoom	findByPetUser(String	petUserName);
}
