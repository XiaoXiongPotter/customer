package com.dognessnetwork.customer.repository;
import com.dognessnetwork.customer.domain.Comment;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepository;
import org.springframework.roo.addon.layers.repository.jpa.annotations.finder.RooFinder;

/**
 * = CommentRepository
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepository(entity = Comment.class, finders = { @RooFinder(value = "findByChatRoomAndStar", returnType = Comment.class) })
public interface CommentRepository {
}
