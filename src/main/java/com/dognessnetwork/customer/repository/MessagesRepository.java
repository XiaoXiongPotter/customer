package com.dognessnetwork.customer.repository;
import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.finder.RooFinder;

/**
 * = MessagesRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Messages.class, finders = { @RooFinder(value = "findByFormUserOrToUserAndSendTimeGreaterThanEqual", returnType = Messages.class), @RooFinder(value = "findByFormUserOrToUserAndSendTimeGreaterThanEqualAndSendTimeLessThanEqual", returnType = Messages.class), @RooFinder(value = "findByFormUserOrToUserAndSendTimeGreaterThanAndSendTimeLessThanEqual", returnType = Messages.class), @RooFinder(value = "findByFormUserOrToUserAndSendTimeBetween", returnType = Messages.class), @RooFinder(value = "findBySendTimeBetweenAndFormUserAndToUser", returnType = Messages.class), @RooFinder(value = "findBySendTimeBetweenOrFormUserOrToUser", returnType = Messages.class), @RooFinder(value = "findByPetUserNameAndSendTimeBetween", returnType = Messages.class), @RooFinder(value = "findBySeatAndSendTimeBetween", returnType = Messages.class), @RooFinder(value = "findBySeatAndPetUserNameAndSendTimeBetween", returnType = Messages.class) })
public interface MessagesRepository extends JpaRepository<Messages, Long> {

    Messages findBySendTime(Long sendTime);
}
