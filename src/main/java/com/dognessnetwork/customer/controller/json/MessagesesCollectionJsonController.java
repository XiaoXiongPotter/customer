package com.dognessnetwork.customer.controller.json;

import com.dognessnetwork.customer.domain.MessageStatus;
import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.service.api.ChatRoomService;
import com.dognessnetwork.customer.util.DateUtils;
import com.dognessnetwork.customer.util.MessagesFactory;
import com.dognessnetwork.customer.util.SendImg;
import com.dognessnetwork.customer.util.WebSocketClient;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/js/messageses", name = "MessagesesCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = MessagesesCollectionJsonController TODO Auto-generated class documentation
 *
 */
@RooController(entity = Messages.class, pathPrefix = "js", type = ControllerType.COLLECTION)
@RooJSON
public class MessagesesCollectionJsonController {

    @Autowired
    WebSocketClient webSocketClient;

    @Autowired
    ChatRoomService chatroomService;

    @Value("${document.image}")
    private String uploadImagePath;

    /**
     * 发送消息
     * 
     * @param formUser
     * @param toUser
     * @param postMessages
     * @return
     */
    @PostMapping("/sendMsg")
    @ResponseBody
    public String sendMsg(@RequestParam("formUser") String formUser, @RequestParam("toUser") String toUser,
            @RequestParam("postMessages") String postMessages) {
        Console.log("formUserId" + formUser + ">>>" + "toUserId" + toUser + "postMessages" + postMessages);
        // PetUser formUser = petUserService.findOne(formUserId);
        Messages messages = MessagesFactory.getMessages("Text", formUser, toUser, postMessages);
        /*
         * Long time = System.currentTimeMillis(); Messages messages = new
         * Messages(); messages.setFormUser(formUser);
         * messages.setPostMessages(postMessages); messages.setToUser(toUser);
         * messages.setMessageType(MessageType.Text);
         * messages.setSendTime(time);
         */
        if (formUser.startsWith("PFU") && postMessages.equals("人工")) {
            return getMessagesService().requestCustomerService(formUser, toUser, postMessages);
        } else {
            return getMessagesService().sendMsg(messages);

        }

    }

    @PostMapping("/sendImg")
    @ResponseBody
    public String sendImg(HttpServletRequest request, @RequestParam("formUser") String formUser,
            @RequestParam("toUser") String toUser, @RequestParam("postMessages") String postMessages) {
        // Console.log("formUserId"+formUser+">>>"+"toUserId"+toUser+"postMessages"+postMessages);
        // PetUser formUser = petUserService.findOne(formUserId);
        // SendImg.get_Image_base(request, postMessages);
        /*
         * Messages messages = new Messages(); messages.setFormUser(formUser);
         * messages.setPostMessages(SendImg.get_Image_base(request,
         * postMessages,uploadImagePath)); messages.setToUser(toUser);
         * messages.setMessageType(MessageType.Picture);
         * messages.setSendTime(new Date().getTime());
         */
        /* Messages messages = ; */
        return getMessagesService().sendMsg(MessagesFactory.getMessages("Picture", formUser, toUser,
                SendImg.get_Image_base(request, postMessages, uploadImagePath)));
    }

    /**
     * 更新消息状态 js/messageses/updateStatus
     * 
     * @param sendTime
     * @return
     */
    @PostMapping("/updateStatus")
    @ResponseBody
    public JSONObject updateStatus(@RequestParam("sendTime") Long sendTime) {
        Messages msg = getMessagesService().updateStatus(sendTime);
        JSONObject js = new JSONObject();
        if (msg != null) {

            js.put("res", msg.getMessageStatus());
            return js;
        } else {
            js.put("res", MessageStatus.failure);
            return js;
        }

    }

    /**
     * js/messageses/messageHistory 查询历史消息
     * 
     * @param sendTime
     * @param thatDay
     * @return
     */
    @PostMapping("/messageHistory")
    @ResponseBody
    public JSONObject messageHistory(@RequestParam("userName") String userName, @RequestParam("thatDay") Long thatDay) {
        Date date = new Date(thatDay);
        Long beginDay = DateUtils.getDayStartTime(date).getTime();
        Long endDay = DateUtils.getDayEndTime(date).getTime();
        List<Messages> listMessages = null;
        if (userName.startsWith("PFU")) {
            listMessages = getMessagesService().findByPetUserNameAndSendTimeBetween(userName, beginDay, endDay, null)
                    .getContent();
        }
        if (userName.startsWith("CSD")) {
            listMessages = getMessagesService().findBySeatAndSendTimeBetween(userName, beginDay, endDay, null)
                    .getContent();
        }
        Console.log("时间" + thatDay);
        Console.log(listMessages);
        JSONObject res = new JSONObject();
        JSONObject header = new JSONObject();
        JSONArray list = new JSONArray();
        header.put("status", 2000);
        header.put("message", "fail");
        res.put("header", header);
        res.put("data", list);
        if (listMessages.size() > 0)
            for (Messages messages : listMessages) {
                list.put(messages);
            }
        if (list.size() > 0) {
            header.put("status", 1000);
            header.put("message", "success");
            res.put("header", header);
            res.put("data", list);
        }
        return res;
    }
}
