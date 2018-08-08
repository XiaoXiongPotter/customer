package com.dognessnetwork.customer.repository;
import com.dognessnetwork.customer.domain.ChatRoom;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.finder.RooFinder;

/**
 * = ChatRoomRepository
 TODO Auto-generated class documentation
 *
 */
@Transactional(readOnly = true)
@RooJpaRepository(entity = ChatRoom.class, finders = { @RooFinder(value = "findBySeat", returnType = ChatRoom.class), @RooFinder(value = "findBySeatAndPetUserAndStatus", returnType = ChatRoom.class) })
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    ChatRoom findBySeat(String seat);

    ChatRoom findByPetUser(String petUserName);

    ChatRoom findBySeatAndPetUser(String seat, String petUserName);
}
