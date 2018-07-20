// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dognessnetwork.customer.controller.json;

import com.dognessnetwork.customer.controller.json.CommentDeserializer;
import com.dognessnetwork.customer.domain.Comment;
import com.dognessnetwork.customer.service.api.CommentService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.core.convert.ConversionService;

privileged aspect CommentDeserializer_Roo_EntityDeserializer {
    
    declare @type: CommentDeserializer: @JsonComponent;
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return CommentService
     */
    public CommentService CommentDeserializer.getCommentService() {
        return commentService;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param commentService
     */
    public void CommentDeserializer.setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return ConversionService
     */
    public ConversionService CommentDeserializer.getConversionService() {
        return conversionService;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param conversionService
     */
    public void CommentDeserializer.setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param jsonParser
     * @param context
     * @param codec
     * @param tree
     * @return Comment
     * @throws IOException
     */
    public Comment CommentDeserializer.deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Comment comment = commentService.findOne(id);
        if (comment == null) {
            throw new NotFoundException("Comment not found");
        }
        return comment;
    }
    
}
