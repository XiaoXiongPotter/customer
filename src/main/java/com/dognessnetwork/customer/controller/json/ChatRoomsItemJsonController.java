package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.ChatRoom;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;

/**
 * = ChatRoomsItemJsonController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = ChatRoom.class, pathPrefix = "js", type = ControllerType.ITEM)
@RooJSON
public class ChatRoomsItemJsonController {
}
