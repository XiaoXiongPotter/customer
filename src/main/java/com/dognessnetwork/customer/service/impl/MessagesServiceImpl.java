package com.dognessnetwork.customer.service.impl;

import com.dognessnetwork.customer.domain.ChatRoom;
import com.dognessnetwork.customer.domain.MessageStatus;
import com.dognessnetwork.customer.domain.MessageType;
import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.domain.RoomStatus;
import com.dognessnetwork.customer.dto.Msg;
import com.dognessnetwork.customer.repository.ChatRoomRepository;
import com.dognessnetwork.customer.service.api.MessagesService;
import com.dognessnetwork.customer.util.MessagesFactory;
import com.dognessnetwork.customer.util.MsgFactory;
import com.dognessnetwork.customer.util.Robot;
import com.dognessnetwork.customer.util.StringToNumber;
import com.dognessnetwork.customer.util.WebSocketClient;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONObject;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.layers.service.annotations.RooServiceImpl;
import org.springframework.transaction.annotation.Transactional;

/**
 * = MessagesServiceImpl TODO Auto-generated class documentation
 *
 */
@RooServiceImpl(service = MessagesService.class)
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    WebSocketClient webSocketClient;

    @Autowired
    HttpSession session;

    /**
     * 发送消息若信息实体的id为空返回0表示失败
     */
    public String customerAccess(Msg msg) {
        return webSocketClient.sendMsg(msg) + "";
    };

    /**
     * 用户请求人工服务
     */
    @Transactional
    public String requestCustomerService(String formUser, String toUser, String postMessages) {
        String petUserName = formUser.substring(3);
        Long time = System.currentTimeMillis();
        Msg msg = new Msg();
        msg.setContent(postMessages);
        msg.setFromUser(formUser);
        msg.setToUser(toUser);
        msg.setUserName(petUserName);
        msg.setMsgType(MessageType.Text.toString());

        msg.setMsgId(Long.parseLong(StringToNumber.letterToNumber(postMessages) + ""));
        msg.setSendTime(time);
        String seat = toUser.replaceAll("CSD", "");
        ChatRoom chatRoom = chatRoomRepository.findBySeat(seat);
        // 先创建一个聊天对象为dogness房间用于保存用户请求人工客服的状态
        ChatRoom newChatRoom = chatRoomRepository.findBySeatAndPetUser("dogness", petUserName);
        if (newChatRoom != null) {
            newChatRoom.setSeat("dogness");
            newChatRoom.setSeatAt(System.currentTimeMillis());
            newChatRoom.setStatus(RoomStatus.在线);
            newChatRoom.setPetUser(petUserName);
            chatRoomRepository.save(newChatRoom);
        } else {
            newChatRoom = new ChatRoom();
            newChatRoom.setSeat("dogness");
            newChatRoom.setSeatAt(System.currentTimeMillis());
            newChatRoom.setStatus(RoomStatus.在线);
            newChatRoom.setPetUser(petUserName);
            chatRoomRepository.save(newChatRoom);
        }

        // 查询当前请求的客服是否在线、是否繁忙
        // ChatRoom chatRoom = chatroomService.findBySeat(seat);
        // Console.log(chatRoom);
        JSONObject js = new JSONObject();
        js.put("res", "1");
        if (chatRoom.getStatus().name().equals("在线") && chatRoom.getPetUser() == null) {
            String res = webSocketClient.sendMsg(msg).getStr("res");
            js.put("res", res);
        }

        return js + "";
    }

    /**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Messages
     */
    @Transactional
    public Messages save(String seat, String petUser) {
        Messages messages = MessagesFactory.getMessages("Text", seat, petUser, seat.substring(3) + "为您服务");

        //Messages newMessages = getMessagesRepository().save(messages);
        Msg msg = new Msg();
        msg.setContent(messages.getPostMessages());
        msg.setFromUser(messages.getFormUser());
        msg.setToUser(messages.getToUser());
        msg.setUserName(messages.getFormUser().substring(3));
        if (messages != null) {
            msg.setMsgType(messages.getMessageType().toString());
        }

        msg.setMsgId(-100L);
        msg.setSendTime(messages.getSendTime());

        webSocketClient.sendMsg(msg);
        return messages;
    }

    /**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Messages
     */
    @Transactional
    public Messages updateStatus(Long sendTime) {
        Messages msg = getMessagesRepository().findBySendTime(sendTime);
        msg.setMessageStatus(MessageStatus.success);
        return getMessagesRepository().save(msg);
    }

    @Transactional
    public String sendMsg(Messages messages) {
        Messages newMessages = null;
        JSONObject js = new JSONObject();
        js.put("res", "1");
        // 如果是用户发送的消息先看聊天对象是否在线
        if (messages.getFormUser().startsWith("PFU")) {
            String seat = messages.getToUser().replaceAll("CSD", "");
            ChatRoom chatRoom = chatRoomRepository.findBySeat(seat);
            if (chatRoom.getStatus().name().equals("在线")) {
                newMessages = getMessagesRepository().save(messages);
                if (newMessages != null) {
                    String res = webSocketClient.sendMsg(MsgFactory.getMsg(newMessages)).getStr("res");
                    js.put("res", res);
                }
            } else {
                // messages.setToUser("dogness");
                // newMessages = getMessagesRepository().save(messages);
                Robot robot = new Robot();
                String value = robot.getMessage("客服意外掉线");
                js.put("res", "2");
                js.put("data", value);
                Console.log(value);
            }
        }
        // 客服发消息
        else {
            newMessages = getMessagesRepository().save(messages);
            if (newMessages != null) {
                String res = webSocketClient.sendMsg(MsgFactory.getMsg(newMessages)).getStr("res");
                js.put("res", res);
            }
        }
        ;
        Console.log(newMessages);
        return js + "";
    }
}
