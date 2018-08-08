package com.dognessnetwork.customer.util;



import com.dognessnetwork.customer.domain.Messages;
import com.dognessnetwork.customer.dto.Msg;

import cn.hutool.core.lang.Console;

public class MsgFactory {
    public  static  Msg  getMsg(Messages    messages){
        Msg msg =   new Msg();
        msg.setContent(messages.getPostMessages());
        msg.setFromUser(messages.getFormUser());
        msg.setToUser(messages.getToUser());
        msg.setUserName(messages.getFormUser().substring(3));
        if(messages!=null){
            msg.setMsgType(messages.getMessageType().toString());
        }
        
        msg.setMsgId(messages.getId());
        msg.setSendTime(messages.getSendTime());
        return  msg;
        /*if(type.equals("qrcode")){
            Device  device  =   new Device();
            device.setDevName("协寻二维码");
            device.setBind(false);
            device.setOnline(false);
            device.setIsWechat(false);
            device.setBatchCode(batchCode);
            device.setDeviceCode(deviceCode);
            device.setProducter(producter);
            device.setQrUrl(qrUrl);
            device.setType("QRCODE");
            Console.log(device);
            return  device;
        }else   if(type.equals(EnumDeviceType.bluetooth)){
            Device  device  =   new Device();
            device.setBind(false);
            device.setOnline(false);
            device.setIsWechat(true);
            device.setType("BLUETOOTH");
            return  device;
        }else{
            return  null;
        }*/
        
    }
}
