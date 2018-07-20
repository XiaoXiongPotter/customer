// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dognessnetwork.customer.service.api;

import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.service.api.MessagesService;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

privileged aspect MessagesService_Roo_Service {
    
    declare parents: MessagesService extends EntityResolver<Messages, Long>;
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Messages
     */
    public abstract Messages MessagesService.findOne(Long id);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param messages
     */
    public abstract void MessagesService.delete(Messages messages);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param entities
     * @return List
     */
    public abstract List<Messages> MessagesService.save(Iterable<Messages> entities);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     */
    public abstract void MessagesService.delete(Iterable<Long> ids);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Messages
     */
    public abstract Messages MessagesService.save(Messages entity);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Messages
     */
    public abstract Messages MessagesService.findOneForUpdate(Long id);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return List
     */
    public abstract List<Messages> MessagesService.findAll(Iterable<Long> ids);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    public abstract List<Messages> MessagesService.findAll();
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    public abstract long MessagesService.count();
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Messages> MessagesService.findAll(GlobalSearch globalSearch, Pageable pageable);
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Messages> MessagesService.findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);
    
}