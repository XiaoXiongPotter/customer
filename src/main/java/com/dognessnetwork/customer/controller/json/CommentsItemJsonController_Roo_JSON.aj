// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dognessnetwork.customer.controller.json;

import com.dognessnetwork.customer.controller.json.CommentsItemJsonController;
import com.dognessnetwork.customer.domain.Comment;
import com.dognessnetwork.customer.service.api.CommentService;
import io.springlets.web.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

privileged aspect CommentsItemJsonController_Roo_JSON {
    
    declare @type: CommentsItemJsonController: @RestController;
    
    declare @type: CommentsItemJsonController: @RequestMapping(value = "/js/comments/{comment}", name = "CommentsItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE);
    
    /**
     * TODO Auto-generated constructor documentation
     * 
     * @param commentService
     */
    @Autowired
    public CommentsItemJsonController.new(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Comment
     */
    @ModelAttribute
    public Comment CommentsItemJsonController.getComment(@PathVariable("comment") Long id) {
        Comment comment = commentService.findOne(id);
        if (comment == null) {
            throw new NotFoundException(String.format("Comment with identifier '%s' not found",id));
        }
        return comment;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param comment
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> CommentsItemJsonController.show(@ModelAttribute Comment comment) {
        return ResponseEntity.ok(comment);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param comment
     * @return UriComponents
     */
    public static UriComponents CommentsItemJsonController.showURI(Comment comment) {
        return MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder.on(CommentsItemJsonController.class).show(comment))
            .buildAndExpand(comment.getId()).encode();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param storedComment
     * @param comment
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> CommentsItemJsonController.update(@ModelAttribute Comment storedComment, @Valid @RequestBody Comment comment, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        comment.setId(storedComment.getId());
        getCommentService().save(comment);
        return ResponseEntity.ok().build();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param comment
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> CommentsItemJsonController.delete(@ModelAttribute Comment comment) {
        getCommentService().delete(comment);
        return ResponseEntity.ok().build();
    }
    
}
