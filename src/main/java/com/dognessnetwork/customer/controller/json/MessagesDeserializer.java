package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.service.api.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;

/**
 * = MessagesDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Messages.class)
public class MessagesDeserializer extends JsonObjectDeserializer<Messages> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private MessagesService messagesService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param messagesService
     * @param conversionService
     */
    @Autowired
    public MessagesDeserializer(@Lazy MessagesService messagesService, ConversionService conversionService) {
        this.messagesService = messagesService;
        this.conversionService = conversionService;
    }
}
