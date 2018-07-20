package com.dognessnetwork.customer.repository;

import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.dognessnetwork.customer.domain.Comment;

/**
 * = CommentRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */ 
@RooJpaRepositoryCustomImpl(repository = CommentRepositoryCustom.class)
public class CommentRepositoryImpl extends QueryDslRepositorySupportExt<Comment> {

    /**
     * TODO Auto-generated constructor documentation
     */
    CommentRepositoryImpl() {
        super(Comment.class);
    }
}