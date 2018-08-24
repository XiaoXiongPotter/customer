package com.dognessnetwork.customer.controller.json;

import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.MessageType;
import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.domain.RoomStatus;
import com.dognessnetwork.customer.service.api.ChatRoomService;
import com.dognessnetwork.customer.service.api.MessagesService;
import com.dognessnetwork.customer.util.DateUtils;
import com.dognessnetwork.customer.util.MsgFactory;
import com.dognessnetwork.customer.util.WebSocketClient;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/js/chatrooms", name = "ChatRoomsCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = ChatRoomsCollectionJsonController TODO Auto-generated class documentation
 *
 */
@RooController(entity = ChatRoom.class, pathPrefix = "js", type = ControllerType.COLLECTION)
@RooJSON
public class ChatRoomsCollectionJsonController {

    @Autowired
    ChatRoomService chatroomService;

    @Autowired
    MessagesService messagesService;

    @Autowired
    WebSocketClient webSocketClient;

    /**
     * /js/chatrooms/get_talking 用户聊天框刷新获取正在和用户聊天的客服
     * 
     * @param petUserName
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/get_talking", produces = "text/plain;charset=UTF-8")
    public String get_talking(String petUserName) {
        ChatRoom chatRoom = chatroomService.findByPetUser(petUserName);
        if (chatRoom == null) {
            return null;
        } else if (chatRoom.getPetUser().equals(petUserName)) {
            return chatRoom.getSeat();
        }
        return null;
    }

    /**
     * /js/chatrooms/get_petUser_is_accessing
     * 
     * @param req
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/get_petUser_is_accessing", produces = "text/plain;charset=UTF-8")
    public String get_petUser_is_accessing(/* @RequestBody String req */
            @RequestParam("array[]") String[] array) {
        Console.log("获取到的在线用户列表");
        List<String> list = CollUtil.newArrayList(array);
        JSONArray accessing = new JSONArray();
        for (String petUserName : list) {
            ChatRoom chatRoom = chatroomService.findByPetUser(petUserName);
            if (chatRoom == null) {
                accessing.put(petUserName.substring(3));
            }
        }

        Console.log(accessing);
        // 返回正在请求的
        return accessing + "";
    }

    /**
     * 客服接入用户时向被接入的用户发送消息提醒
     * 
     * @param petUser
     * @param seat
     * @return
     */
    @ResponseBody
    @PostMapping("/customer_access")
    public JSONObject customer_access(@RequestParam("petUser") String petUser, @RequestParam("seat") String seat) {
        Console.log(seat + "to" + petUser);
        String customer = seat.substring(3);
        String petUserName = petUser.substring(3);
        ChatRoom petUserChatRoom = chatroomService.findBySeatAndPetUser("dogness", petUserName);
        ChatRoom chatRoom = null;
        chatRoom = chatroomService.findBySeat(customer);
        chatRoom.setPetUser(petUserName);
        chatRoom.setPetUserAt(System.currentTimeMillis());

        ChatRoom customerChatRoom = null;
        Messages resMessage = null;

        if (petUserChatRoom != null) {
            if (petUserChatRoom.getSeat().equals("dogness")) {
                chatroomService.delete(petUserChatRoom);
                customerChatRoom = chatroomService.save(chatRoom);
            }
            if (customerChatRoom.getPetUser().equals(petUserName)) {
                resMessage = messagesService.save(seat, petUser);
            }
        } else {
            customerChatRoom = chatroomService.save(chatRoom);
            resMessage = messagesService.save(seat, petUser);
        }

        JSONObject res = new JSONObject();
        JSONObject js = new JSONObject();
        JSONObject faile = new JSONObject();
        faile.put("info", "save data faile");
        if (resMessage != null) {
            js.put("id", resMessage.getId());
            js.put("toUser", resMessage.getToUser().substring(3));
            js.put("status", true);
            js.put("content", resMessage.getPostMessages());
            js.put("sendTime", resMessage.getSendTime());
            js.put("formUser", resMessage.getFormUser().substring(3));
            js.put("avatar", "/layui/images/img/tu2.png");
            res.put("code", 0);
            res.put("res", js);
            return res;
        }
        res.put("code", 1);
        res.put("res", faile);
        return res;
    }

    /**
     * /js/chatrooms/query_pending_access 查询待接入
     * 
     * @return
     */
    @ResponseBody
    @PostMapping("/query_pending_access")
    public JSONObject queryPendingAccess() {
        List<ChatRoom> listChatRoom = chatroomService.findBySeat("dogness", null).getContent();

        JSONArray array = new JSONArray();
        for (ChatRoom chatRoom : listChatRoom) {
            array.put(chatRoom.getPetUser());
        }
        JSONObject res = new JSONObject();
        res.put("res", array);
        return res;
    }

    /**
     * /js/chatrooms/get_can_get_time 获取可转接的客服
     * 
     * @param seat
     * @return
     */
    @ResponseBody
    @PostMapping("/get_can_get_time")
    public JSONObject getCanGetTime(@RequestParam("seat") String seat) {
        List<ChatRoom> listChatRoom = chatroomService.findBySeatAndPetUserAndStatus(seat, null, RoomStatus.在线, null)
                .getContent();
        JSONArray array = new JSONArray();
        for (ChatRoom chatRoom : listChatRoom) {
            array.put(chatRoom.getSeat());
        }
        JSONObject res = new JSONObject();
        res.put("res", array);
        return res;
    }

    /**
     * /js/chatrooms/can_transfer 向其他客服发送转接客服消息
     * 
     * @param seat
     * @param customer
     * @param petUser
     * @return
     */
    @ResponseBody
    @PostMapping("/can_transfer")
    public JSONObject transfer(@RequestParam("seat") String seat, @RequestParam("customer") String customer,
            @RequestParam("petUser") String petUser) {
        JSONObject str = new JSONObject();
        str.put("seat", seat);
        str.put("customer", customer);
        str.put("petUser", petUser);
        Messages messages = new Messages();
        messages.setFormUser(seat);
        messages.setToUser(customer);
        messages.setId(2L);
        messages.setPostMessages("Transfer:" + str);
        messages.setMessageType(MessageType.Text);
        webSocketClient.sendMsg(MsgFactory.getMsg(messages));

        JSONObject res = new JSONObject();
        // res.put("res", array);
        return res;
    }

    /**
     * js/chatrooms/trans_ferAccess
     * 
     * @param json
     * @return
     */
    @ResponseBody
    @PostMapping("/trans_ferAccess")
    public JSONObject transferAccess(@RequestParam("json") String json) {
        Console.log(json);
        JSONObject ob = JSONUtil.parseObj(json);
        String petUserName = ob.getStr("petUser");
        List<Messages> listMessages = messagesService.findBySeatAndPetUserNameAndSendTimeBetween(ob.getStr("seat"),
                "PFU" + petUserName, DateUtils.getDayBegin().getTime(), DateUtils.getDayEnd().getTime(), null)
                .getContent();
        ChatRoom chatRoom = chatroomService.findBySeat(ob.getStr("customer").substring(3));
        chatRoom.setPetUser(petUserName);
        ChatRoom newChatRoom = chatroomService.save(chatRoom);
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
        if (list.size() > 0 && newChatRoom.getPetUser().equals(petUserName)) {
            header.put("status", 1000);
            header.put("message", "success");
            res.put("header", header);
            res.put("data", list);
        }
        return res;
    }

    /**
     * js/chatrooms/deny_Access 客服拒绝接入用户
     * 
     * @param json
     * @return
     */
    @ResponseBody
    @PostMapping("/deny_Access")
    public JSONObject denyAccess(@RequestParam("json") String json) {
        JSONObject ob = JSONUtil.parseObj(json);
        String petUserName = ob.getStr("petUser");
        String customer = ob.getStr("customer");
        String seat = ob.getStr("seat");
        JSONObject res = new JSONObject();
        JSONObject header = new JSONObject();
        JSONArray list = new JSONArray();
        header.put("status", 2000);
        header.put("message", "fail");
        res.put("header", header);
        res.put("data", list);
        ChatRoom chatRoom = chatroomService.findBySeat(ob.getStr("seat").substring(3));
        chatRoom.setPetUser(petUserName);
        ChatRoom newChatRoom = chatroomService.save(chatRoom);

        JSONObject str = new JSONObject();
        str.put("seat", seat);
        str.put("customer", customer);
        str.put("petUser", petUserName);
        Messages messages = new Messages();
        messages.setFormUser(customer);
        messages.setToUser(seat);
        messages.setId(3L);
        messages.setPostMessages("DenyAccess:" + str);
        messages.setMessageType(MessageType.Text);
        webSocketClient.sendMsg(MsgFactory.getMsg(messages));
        if (newChatRoom.getPetUser().equals(petUserName)) {
            header.put("status", 1000);
            header.put("message", "success");
            res.put("header", header);
            res.put("data", list);
        }
        return res;
    }

    /**
     * 获取客服正在接待的用户
     * 
     * @param seat
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/receiving_user", produces = "text/plain;charset=UTF-8")
    public String receiving_user(String seat) {
        ChatRoom chatRoom = chatroomService.findBySeat(seat);
        if (chatRoom == null) {
            return null;
        } else if (chatRoom.getSeat().equals(seat)) {
            return chatRoom.getPetUser();
        }
        return null;
    }
}
