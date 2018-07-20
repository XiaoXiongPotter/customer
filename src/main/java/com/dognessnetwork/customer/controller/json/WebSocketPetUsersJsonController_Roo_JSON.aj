// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dognessnetwork.customer.controller.json;

import com.dognessnetwork.customer.controller.json.ChatRoomsItemJsonController;
import com.dognessnetwork.customer.controller.json.WebSocketPetUsersJsonController;
import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.service.api.ChatRoomService;
import io.springlets.data.domain.GlobalSearch;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

privileged aspect WebSocketPetUsersJsonController_Roo_JSON {
    
    /**
     * TODO Auto-generated constructor documentation
     * 
     * @param chatRoomService
     */
    @Autowired
    public WebSocketPetUsersJsonController.new(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    /**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<ChatRoom>> WebSocketPetUsersJsonController.list(GlobalSearch globalSearch, Pageable pageable) {
        
        Page<ChatRoom> chatRooms = getChatRoomService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(chatRooms);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @return UriComponents
     */
    public static UriComponents WebSocketPetUsersJsonController.listURI() {
        return MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder.on(WebSocketPetUsersJsonController.class).list(null, null))
            .build().encode();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRoom
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> WebSocketPetUsersJsonController.create(@Valid @RequestBody ChatRoom chatRoom, BindingResult result) {
        
        if (chatRoom.getId() != null || chatRoom.getVersion() != null) {        
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        ChatRoom newChatRoom = getChatRoomService().save(chatRoom);
        UriComponents showURI = ChatRoomsItemJsonController.showURI(newChatRoom);
        
        return ResponseEntity.created(showURI.toUri()).build();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRooms
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> WebSocketPetUsersJsonController.createBatch(@Valid @RequestBody Collection<ChatRoom> chatRooms, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        getChatRoomService().save(chatRooms);
        
        return ResponseEntity.created(listURI().toUri()).build();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param chatRooms
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> WebSocketPetUsersJsonController.updateBatch(@Valid @RequestBody Collection<ChatRoom> chatRooms, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        getChatRoomService().save(chatRooms);
        
        return ResponseEntity.ok().build();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> WebSocketPetUsersJsonController.deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        
        getChatRoomService().delete(ids);
        
        return ResponseEntity.ok().build();
    }
    
}
