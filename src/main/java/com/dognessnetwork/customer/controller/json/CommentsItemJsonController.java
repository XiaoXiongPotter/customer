package com.dognessnetwork.customer.controller.json;
import com.dognessnetwork.customer.domain.Comment;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;

/**
 * = CommentsItemJsonController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = Comment.class, pathPrefix = "js", type = ControllerType.ITEM)
@RooJSON
public class CommentsItemJsonController {
}
