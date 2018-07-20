package com.dognessnetwork.customer.domain;
/**
 * 消息类型
 * @author Dogness
 *
 */
public enum MessageType {
	Text, Picture ,Video, Voice;
    //文本 图片 视频 语音
    public int getId(){
    	return this.ordinal();
    }
}
