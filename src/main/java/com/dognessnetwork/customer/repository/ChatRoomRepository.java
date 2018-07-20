package com.dognessnetwork.customer.repository;
import com.dognessnetwork.customer.domain.ChatRoom;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional(readOnly = true)
/**
 * = ChatRoomRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = ChatRoom.class)
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	ChatRoom	findBySeat(String	seat);
	ChatRoom	findByPetUser(String	petUserName);
}
