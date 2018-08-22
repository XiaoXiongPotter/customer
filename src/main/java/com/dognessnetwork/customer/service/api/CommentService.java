package com.dognessnetwork.customer.service.api;
import com.dognessnetwork.customer.domain.Comment;
import org.springframework.roo.addon.layers.service.annotations.RooService;

/**
 * = CommentService
 TODO Auto-generated class documentation
 *
 */
@RooService(entity = Comment.class)
public interface CommentService {
    void    scoring (Comment    comment);
}
