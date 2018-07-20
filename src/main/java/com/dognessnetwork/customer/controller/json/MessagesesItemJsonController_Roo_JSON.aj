// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.dognessnetwork.customer.controller.json;

import com.dognessnetwork.customer.controller.json.MessagesesItemJsonController;
import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.service.api.MessagesService;
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

privileged aspect MessagesesItemJsonController_Roo_JSON {
    
    declare @type: MessagesesItemJsonController: @RestController;
    
    declare @type: MessagesesItemJsonController: @RequestMapping(value = "/js/messageses/{messages}", name = "MessagesesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE);
    
    /**
     * TODO Auto-generated constructor documentation
     * 
     * @param messagesService
     */
    @Autowired
    public MessagesesItemJsonController.new(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    /**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Messages
     */
    @ModelAttribute
    public Messages MessagesesItemJsonController.getMessages(@PathVariable("messages") Long id) {
        Messages messages = messagesService.findOne(id);
        if (messages == null) {
            throw new NotFoundException(String.format("Messages with identifier '%s' not found",id));
        }
        return messages;
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param messages
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> MessagesesItemJsonController.show(@ModelAttribute Messages messages) {
        return ResponseEntity.ok(messages);
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param messages
     * @return UriComponents
     */
    public static UriComponents MessagesesItemJsonController.showURI(Messages messages) {
        return MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder.on(MessagesesItemJsonController.class).show(messages))
            .buildAndExpand(messages.getId()).encode();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param storedMessages
     * @param messages
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> MessagesesItemJsonController.update(@ModelAttribute Messages storedMessages, @Valid @RequestBody Messages messages, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        messages.setId(storedMessages.getId());
        getMessagesService().save(messages);
        return ResponseEntity.ok().build();
    }
    
    /**
     * TODO Auto-generated method documentation
     * 
     * @param messages
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> MessagesesItemJsonController.delete(@ModelAttribute Messages messages) {
        getMessagesService().delete(messages);
        return ResponseEntity.ok().build();
    }
    
}
