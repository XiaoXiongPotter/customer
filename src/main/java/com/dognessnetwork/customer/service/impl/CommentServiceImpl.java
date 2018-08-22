package com.dognessnetwork.customer.service.impl;
import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.Comment;
import com.dognessnetwork.customer.repository.ChatRoomRepository;
import com.dognessnetwork.customer.service.api.ChatRoomService;
import com.dognessnetwork.customer.service.api.CommentService;
import com.dognessnetwork.customer.util.Avgrate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * = CommentServiceImpl
 TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = CommentService.class)
public class CommentServiceImpl implements CommentService {
    @Autowired
    Avgrate avgrate;
    
    @Autowired
    ChatRoomRepository chatroomRepository;
	/**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Comment
     */
    @Transactional
    public Comment save(Comment entity) {
        return getCommentRepository().save(entity);
    }
    
    @Transactional
    public  void    scoring (Comment    comment){
        if(getCommentRepository().save(comment)!=null){
            ChatRoom    chatRoom    =   comment.getChatRoom();
            ChatRoom    oldChatRoom =   chatroomRepository.findOne(chatRoom.getId());
            oldChatRoom.setAvgrate(avgrate.get_avgrate(chatRoom));
            chatroomRepository.save(oldChatRoom);
        }
    };
}
