package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.Messages;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;

/**
 * = MessagesesItemJsonController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = Messages.class, pathPrefix = "js", type = ControllerType.ITEM)
@RooJSON
public class MessagesesItemJsonController {
}
