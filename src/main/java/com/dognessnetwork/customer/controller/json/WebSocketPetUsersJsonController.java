package com.dognessnetwork.customer.controller.json;

import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.RoomStatus;
import com.dognessnetwork.customer.service.api.ChatRoomService;
import com.dognessnetwork.customer.service.api.MessagesService;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;


import javax.servlet.http.HttpSession;

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
@RequestMapping(value = "/js/status", name = "WebSocketPetUsersJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = PetUsersCollectionJsonController TODO Auto-generated class documentation
 *
 */
@RooController(entity = ChatRoom.class, pathPrefix = "js", type = ControllerType.COLLECTION)
@RooJSON
public class WebSocketPetUsersJsonController {

    @Autowired
    HttpSession session;

    @Autowired
    MessagesService messagesService;

    @Autowired
    ChatRoomService chatroomService;

    /*
     * @Autowired com.dognessnetwork.customer.util.BalancedDistribution
     * BalancedDistribution;
     */
    /**
     * /js/status/offline 聊天室下线状态更新
     * 
     * @return
     */
    @PostMapping("/offline")
    @ResponseBody
    public JSONObject update(@RequestParam("userName") String userName) {
        Console.log(userName);
        ChatRoom res = null;
        JSONObject result = new JSONObject();
        JSONObject header = new JSONObject();
        if (userName.startsWith("CSD")) {
            String seat = userName.replaceAll("CSD", "");
            ChatRoom chatRoom = chatroomService.findBySeat(seat);
            chatRoom.setPetUser(null);
            chatRoom.setStatus(RoomStatus.下线);
            res = chatroomService.save(chatRoom);
        }
        if (userName.startsWith("PFU")) {
            String petUserName = userName.replaceAll("PFU", "");
            ChatRoom chatRoom = chatroomService.findBySeatAndPetUser("dogness", petUserName);
            if (chatRoom != null) {
                chatRoom.setStatus(RoomStatus.下线);
                res = chatroomService.save(chatRoom);
            }
        }
        if (res != null && res.getStatus().name().equals("下线")) {
            header.put("code", 1000);
            header.put("message", "offline");
            result.put("header", header);
            result.put("data", userName);
            return result;
        } else {
            header.put("code", 2000);
            header.put("message", "fail");
            result.put("header", header);
            result.put("data", userName);
            return result;
        }
    }

    /**
     * js/status/online 上线
     * 
     * @param userName
     * @return
     */
    @PostMapping("/online")
    @ResponseBody
    public JSONObject online(@RequestParam("userName") String userName) {
        Console.log(userName);
        ChatRoom res = null;
        JSONObject result = new JSONObject();
        JSONObject header = new JSONObject();
        if (userName.startsWith("CSD")) {
            String seat = userName.replaceAll("CSD", "");
            ChatRoom chatRoom = chatroomService.findBySeat(seat);
            chatRoom.setStatus(RoomStatus.在线);
            res = chatroomService.save(chatRoom);
        }

        if (res != null && res.getStatus().name().equals("在线")) {
            header.put("code", 1000);
            header.put("message", "online");
            result.put("header", header);
            result.put("data", userName);
            return result;
        } else {
            header.put("code", 2000);
            header.put("message", "fail");
            result.put("header", header);
            result.put("data", userName);
            return result;
        }
    }

    /**//**
         * 接入时获取用户详细信息
         * 
         * @param id
         * @return
         *//*
           * @SuppressWarnings("unchecked")
           * 
           * @GetMapping("/getSnsapiUserInfo") public JSONObject
           * getSnsapiUserInfo(@RequestParam("id") Long id,@RequestParam("seat")
           * Long seat_id){ //坐席接入用户 PetUser seat =
           * petUserServiceImpl.findOne(seat_id); CustomerCare customerCare =
           * customerCareService.findByCustomerId(seat.getId(),
           * null).getContent().get(0); Chatroom chatRoome =
           * chatroomService.findBySeat(customerCare, null).getContent().get(0);
           * PetUser petUser = petUserServiceImpl.findOne(id);
           * chatRoome.setCustomerService(petUser); Chatroom chatRoom =
           * chatroomService.save(chatRoome); //接入成功后清除排队列表中的用户，向用户通知谁在为他服务
           * ServletContext application = session.getServletContext(); Set<Long>
           * queueList = new HashSet<Long>(); session.setAttribute("queue",
           * petUser); queueList =
           * (Set<Long>)application.getAttribute("queueList");
           * queueList.remove(id); application.setAttribute("queueList",
           * queueList);
           * 
           * Msg msg = new Msg(); msg.setContent(seat.getUserName()+"为您服务");
           * msg.setFromUser(seat.getId()); msg.setToUser(id);
           * msg.setUserName(seat.getUserName()); msg.setMsgType("jr");
           * msg.setMsgId(1L); String res = messagesService.customerAccess(msg);
           * 
           * JSONObject js = new JSONObject(); js.put("id", petUser.getId());
           * js.put("username", petUser.getUserName()); js.put("status",
           * petUser.getEnable()); js.put("sign", chatRoom.getId());
           * js.put("avatar", "/layui/images/img/tu2.png"); return js; }
           */
}
