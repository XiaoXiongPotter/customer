package com.dognessnetwork.customer.util;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.dognessnetwork.customer.dto.ChatMessage;
import com.dognessnetwork.customer.dto.Msg;

import cn.hutool.json.JSONObject;

@Component
public class WebSocketClient {

    @Value("${dgs.ws.app-destination-prefix}")
    String destination;

    @Value("${dgs.ws.mapping-customer}")
    String customerMapping;

    @Value("${customer.wesocket}")
    private String wesocket;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCustomerMapping() {
        return customerMapping;
    }

    public void setCustomerMapping(String customerMapping) {
        this.customerMapping = customerMapping;
    }

    public JSONObject sendMsg(Msg msg) {
        Object Msg = (Object) msg;
        String room = "/" + msg.getToUser();
        String empty = "";
        JSONObject js = new JSONObject();
        List<Transport> transports = Arrays.asList(new WebSocketTransport(new StandardWebSocketClient()),
                new RestTemplateXhrTransport(new RestTemplate()));
        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        StompSessionHandler handler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            }
        };
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        ListenableFuture<StompSession> future = stompClient.connect(wesocket, handler, empty);
        try {
            future.get().send(getDestination() + getCustomerMapping() + room, Msg);

            future.addCallback(new SuccessCallback<StompSession>() {
                public void onSuccess(StompSession stompSession) {
                    js.put("res", "0");
                }
            }, new FailureCallback() {
                public void onFailure(Throwable throwable) {
                    js.put("res", "1");
                    throwable.printStackTrace();
                }
            });

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return js;
    }
}
