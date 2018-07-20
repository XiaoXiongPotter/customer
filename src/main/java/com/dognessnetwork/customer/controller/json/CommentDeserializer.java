package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.Comment;
import com.dognessnetwork.customer.service.api.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;

/**
 * = CommentDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Comment.class)
public class CommentDeserializer extends JsonObjectDeserializer<Comment> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private CommentService commentService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param commentService
     * @param conversionService
     */
    @Autowired
    public CommentDeserializer(@Lazy CommentService commentService, ConversionService conversionService) {
        this.commentService = commentService;
        this.conversionService = conversionService;
    }
}
