// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dognessnetwork.customer.domain;

import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.Comment;

privileged aspect Comment_Roo_JavaBean {
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    public Long Comment.getId() {
        return this.id;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param id
     */
    public void Comment.setId(Long id) {
        this.id = id;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return Integer
     */
    public Integer Comment.getVersion() {
        return this.version;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param version
     */
    public void Comment.setVersion(Integer version) {
        this.version = version;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return String
     */
    public String Comment.getContent() {
        return this.content;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param content
     */
    public void Comment.setContent(String content) {
        this.content = content;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return String
     */
    public String Comment.getFromPetUser() {
        return this.fromPetUser;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param fromPetUser
     */
    public void Comment.setFromPetUser(String fromPetUser) {
        this.fromPetUser = fromPetUser;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return ChatRoom
     */
    public ChatRoom Comment.getChatRoom() {
        return this.chatRoom;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRoom
     */
    public void Comment.setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    public Long Comment.getCommentTime() {
        return this.commentTime;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param commentTime
     */
    public void Comment.setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return Integer
     */
    public Integer Comment.getStar() {
        return this.star;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param star
     */
    public void Comment.setStar(Integer star) {
        this.star = star;
    }
    
}
