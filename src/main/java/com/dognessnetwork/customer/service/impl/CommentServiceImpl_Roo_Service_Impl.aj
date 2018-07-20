// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dognessnetwork.customer.service.impl;

import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.Comment;
import com.dognessnetwork.customer.repository.CommentRepository;
import com.dognessnetwork.customer.service.impl.CommentServiceImpl;
import io.springlets.data.domain.GlobalSearch;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect CommentServiceImpl_Roo_Service_Impl {
    
    declare @type: CommentServiceImpl: @Service;
    
    declare @type: CommentServiceImpl: @Transactional(readOnly = true);
    
    /**
     * TODO Auto-generated attribute documentation
     * 
     */
    private CommentRepository CommentServiceImpl.commentRepository;
    
    /**
     * TODO Auto-generated constructor documentation
     * 
     * @param commentRepository
     */
    @Autowired
    public CommentServiceImpl.new(CommentRepository commentRepository) {
        setCommentRepository(commentRepository);
    }

    /**
     * TODO Auto-generated method documentation
     * 
     * @return CommentRepository
     */
    public CommentRepository CommentServiceImpl.getCommentRepository() {
        return commentRepository;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param commentRepository
     */
    public void CommentServiceImpl.setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param comment
     */
    @Transactional
    public void CommentServiceImpl.delete(Comment comment) {
        // Clear bidirectional many-to-one child relationship with ChatRoom
        if (comment.getChatRoom() != null) {
            comment.getChatRoom().getComment().remove(comment);
        }
        
        getCommentRepository().delete(comment);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param entities
     * @return List
     */
    @Transactional
    public List<Comment> CommentServiceImpl.save(Iterable<Comment> entities) {
        return getCommentRepository().save(entities);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     */
    @Transactional
    public void CommentServiceImpl.delete(Iterable<Long> ids) {
        List<Comment> toDelete = getCommentRepository().findAll(ids);
        getCommentRepository().deleteInBatch(toDelete);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Comment
     */
    @Transactional
    public Comment CommentServiceImpl.save(Comment entity) {
        return getCommentRepository().save(entity);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Comment
     */
    public Comment CommentServiceImpl.findOne(Long id) {
        return getCommentRepository().findOne(id);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Comment
     */
    public Comment CommentServiceImpl.findOneForUpdate(Long id) {
        return getCommentRepository().findOneDetached(id);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return List
     */
    public List<Comment> CommentServiceImpl.findAll(Iterable<Long> ids) {
        return getCommentRepository().findAll(ids);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    public List<Comment> CommentServiceImpl.findAll() {
        return getCommentRepository().findAll();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    public long CommentServiceImpl.count() {
        return getCommentRepository().count();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Comment> CommentServiceImpl.findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getCommentRepository().findAll(globalSearch, pageable);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Comment> CommentServiceImpl.findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        return getCommentRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRoom
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<Comment> CommentServiceImpl.findByChatRoom(ChatRoom chatRoom, GlobalSearch globalSearch, Pageable pageable) {
        return getCommentRepository().findByChatRoom(chatRoom, globalSearch, pageable);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRoom
     * @return Long
     */
    public long CommentServiceImpl.countByChatRoom(ChatRoom chatRoom) {
        return getCommentRepository().countByChatRoom(chatRoom);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return Class
     */
    public Class<Comment> CommentServiceImpl.getEntityType() {
        return Comment.class;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return Class
     */
    public Class<Long> CommentServiceImpl.getIdType() {
        return Long.class;
    }
    
}
