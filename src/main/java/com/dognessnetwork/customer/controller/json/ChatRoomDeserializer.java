package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.service.api.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;

/**
 * = ChatRoomDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = ChatRoom.class)
public class ChatRoomDeserializer extends JsonObjectDeserializer<ChatRoom> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ChatRoomService chatRoomService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param chatRoomService
     * @param conversionService
     */
    @Autowired
    public ChatRoomDeserializer(@Lazy ChatRoomService chatRoomService, ConversionService conversionService) {
        this.chatRoomService = chatRoomService;
        this.conversionService = conversionService;
    }
}
