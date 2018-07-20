// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dognessnetwork.customer.controller.json;

import com.dognessnetwork.customer.controller.json.ChatRoomsItemJsonController;
import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.service.api.ChatRoomService;
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

privileged aspect ChatRoomsItemJsonController_Roo_JSON {
    
    declare @type: ChatRoomsItemJsonController: @RestController;
    
    declare @type: ChatRoomsItemJsonController: @RequestMapping(value = "/js/chatrooms/{chatRoom}", name = "ChatRoomsItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE);
    
    /**
     * TODO Auto-generated constructor documentation
     * 
     * @param chatRoomService
     */
    @Autowired
    public ChatRoomsItemJsonController.new(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    /**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return ChatRoom
     */
    @ModelAttribute
    public ChatRoom ChatRoomsItemJsonController.getChatRoom(@PathVariable("chatRoom") Long id) {
        ChatRoom chatRoom = chatRoomService.findOne(id);
        if (chatRoom == null) {
            throw new NotFoundException(String.format("ChatRoom with identifier '%s' not found",id));
        }
        return chatRoom;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRoom
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> ChatRoomsItemJsonController.show(@ModelAttribute ChatRoom chatRoom) {
        return ResponseEntity.ok(chatRoom);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRoom
     * @return UriComponents
     */
    public static UriComponents ChatRoomsItemJsonController.showURI(ChatRoom chatRoom) {
        return MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder.on(ChatRoomsItemJsonController.class).show(chatRoom))
            .buildAndExpand(chatRoom.getId()).encode();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param storedChatRoom
     * @param chatRoom
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> ChatRoomsItemJsonController.update(@ModelAttribute ChatRoom storedChatRoom, @Valid @RequestBody ChatRoom chatRoom, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        chatRoom.setId(storedChatRoom.getId());
        getChatRoomService().save(chatRoom);
        return ResponseEntity.ok().build();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRoom
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> ChatRoomsItemJsonController.delete(@ModelAttribute ChatRoom chatRoom) {
        getChatRoomService().delete(chatRoom);
        return ResponseEntity.ok().build();
    }
    
}