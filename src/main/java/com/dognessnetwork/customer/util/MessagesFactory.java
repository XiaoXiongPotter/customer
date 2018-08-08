package com.dognessnetwork.customer.util;


import com.dognessnetwork.customer.domain.MessageType;
import com.dognessnetwork.customer.domain.Messages;

public class MessagesFactory {
    public  static  Messages  getMessages(String    type,String    formUser,
                                        String    toUser,String    postMessages){
        Long  time    =   System.currentTimeMillis();
        if(type.equals("Text")){
            Messages    messages    =   new Messages();
            messages.setFormUser(formUser);
            messages.setPostMessages(postMessages);
            messages.setToUser(toUser);
            messages.setMessageType(MessageType.Text);
            messages.setSendTime(time);
            if(formUser.startsWith("PFU")){
                messages.setPetUserName(formUser);
                messages.setSeat(toUser);
            }else{
                messages.setPetUserName(toUser);
                messages.setSeat(formUser);
            }
            
            return  messages;
        }else   if(type.equals("Picture")){
            Messages    messages    =   new Messages();
            messages.setFormUser(formUser);
            messages.setPostMessages(postMessages);
            messages.setToUser(toUser);
            messages.setMessageType(MessageType.Picture);
            messages.setSendTime(time);
            if(formUser.startsWith("PFU")){
                messages.setPetUserName(formUser);
                messages.setSeat(toUser);
            }else{
                messages.setPetUserName(toUser);
                messages.setSeat(formUser);
            }
            
            return  messages;
        }else{
            return  null;
        }
        
    }
}
