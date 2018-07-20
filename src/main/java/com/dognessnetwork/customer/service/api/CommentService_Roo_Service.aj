// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dognessnetwork.customer.service.api;

import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.Comment;
import com.dognessnetwork.customer.service.api.CommentService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

privileged aspect CommentService_Roo_Service {
    
    declare parents: CommentService extends EntityResolver<Comment, Long>;
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Comment
     */
    public abstract Comment CommentService.findOne(Long id);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param comment
     */
    public abstract void CommentService.delete(Comment comment);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param entities
     * @return List
     */
    public abstract List<Comment> CommentService.save(Iterable<Comment> entities);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     */
    public abstract void CommentService.delete(Iterable<Long> ids);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Comment
     */
    public abstract Comment CommentService.save(Comment entity);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Comment
     */
    public abstract Comment CommentService.findOneForUpdate(Long id);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return List
     */
    public abstract List<Comment> CommentService.findAll(Iterable<Long> ids);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    public abstract List<Comment> CommentService.findAll();
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    public abstract long CommentService.count();
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Comment> CommentService.findAll(GlobalSearch globalSearch, Pageable pageable);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Comment> CommentService.findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRoom
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Comment> CommentService.findByChatRoom(ChatRoom chatRoom, GlobalSearch globalSearch, Pageable pageable);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRoom
     * @return Long
     */
    public abstract long CommentService.countByChatRoom(ChatRoom chatRoom);
    
}
