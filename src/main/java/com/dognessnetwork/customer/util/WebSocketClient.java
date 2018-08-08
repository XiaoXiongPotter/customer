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
	@Value("${dgs.ws.endpoint}")
	String endPoint;
	
	@Value("${dgs.ws.app-destination-prefix}")
	String destination;
	
	@Value("${dgs.ws.mapping-chat}")
	String chatMapping;
	
	@Value("${dgs.ws.mapping-customer}")
	String customerMapping;
	
	@Value("${dgs.ws.mapping-indent}")
	String indentMapping;
	
	@Value("${dgs.ws.server.url}")
	String url;
	
	@Value("${customer.wesocket}")
    private String wesocket;

	public String getEndPoint() {
		return endPoint;
	}


	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getChatMapping() {
		return chatMapping;
	}


	public void setChatMapping(String chatMapping) {
		this.chatMapping = chatMapping;
	}

	public String getCustomerMapping() {
		return customerMapping;
	}


	public void setCustomerMapping(String customerMapping) {
		this.customerMapping = customerMapping;
	}
	public String getIndentMapping() {
		return indentMapping;
	}


	public void setIndentMapping(String indentMapping) {
		this.indentMapping = indentMapping;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	// String topic = "/topic";
	// String reply = topic+mapping;
	public void send(String room,Object message) {
		System.out.println("url:"+"ws://"+getUrl()+getEndPoint());
		String empty = "";
		List<Transport> transports = Arrays.asList(new WebSocketTransport(new StandardWebSocketClient()),
				new RestTemplateXhrTransport(new RestTemplate()));
		SockJsClient sockJsClient = new SockJsClient(transports);
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		StompSessionHandler handler = new StompSessionHandlerAdapter() {
			@Override
			public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
				System.out.println("#### >>> ");

			}
		};

		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		ListenableFuture<StompSession> future = stompClient.connect("ws://"+getUrl()+getEndPoint(), handler, empty);
		StompSession session;
		try {
			session = future.get();
			if(room!=null){
				if(message instanceof Msg){
					//dgs/chat/room+message
					session.send(getDestination() + getChatMapping()+room, message);
				}
			    /*if(message instanceof IndentHint){
			    	session.send(getDestination() + getIndentMapping()+room, message);
			    }*/
			} 
			else{
				if(message instanceof Msg){
					session.send(getDestination() + getChatMapping(), message);
				}
			    /*if(message instanceof IndentHint){
			    	session.send(getDestination() + getIndentMapping(), message);
			    }*/
			}

			future.addCallback(new SuccessCallback<StompSession>() {
				public void onSuccess(StompSession stompSession) {
					System.out.println(">>> on Success!");
				}
			}, new FailureCallback() {
				public void onFailure(Throwable throwable) {
					System.out.println(">>> on Failure!");
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
	}
	
	public void customerSend(String room,Object	msg) {
		System.out.println("url:"+wesocket);
		String empty = "";
		List<Transport> transports = Arrays.asList(new WebSocketTransport(new StandardWebSocketClient()),
				new RestTemplateXhrTransport(new RestTemplate()));
		SockJsClient sockJsClient = new SockJsClient(transports);
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		StompSessionHandler handler = new StompSessionHandlerAdapter() {
			@Override
			public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
				System.out.println("#### >>> ");

			}
		};
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		ListenableFuture<StompSession> future = stompClient.connect(wesocket, handler, empty);
		StompSession session;
		try {
			session = future.get();
			if(room!=null){
					//dgs/customer/room+message
					session.send(getDestination() + getCustomerMapping()+room, msg);
			} 
			else{
					session.send(getDestination() + getCustomerMapping(), msg);
			}

			future.addCallback(new SuccessCallback<StompSession>() {
				public void onSuccess(StompSession stompSession) {
					System.out.println(">>> on Success!");
				}
			}, new FailureCallback() {
				public void onFailure(Throwable throwable) {
					System.out.println(">>> on Failure!");
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
	}
	
	public String sendMsg(Msg    msg) {
	    Object Msg =   (Object)msg;
	    String room    =   "/"+msg.getToUser();
	    String empty = "";
	    JSONObject  js  =   new JSONObject();
        
        List<Transport> transports = Arrays.asList(new WebSocketTransport(new StandardWebSocketClient()),
                new RestTemplateXhrTransport(new RestTemplate()));
        SockJsClient sockJsClient = new SockJsClient(transports);
        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        StompSessionHandler handler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                System.out.println("#### >>> ");
            }
        };
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        ListenableFuture<StompSession> future = stompClient.connect(wesocket, handler, empty);
        try {
            future.get().send(getDestination() + getCustomerMapping()+room, Msg);

            future.addCallback(new SuccessCallback<StompSession>() {
                public void onSuccess(StompSession stompSession) {
                    System.out.println(">>> on Success!");
                    js.put("res", "0");
                }
            }, new FailureCallback() {
                public void onFailure(Throwable throwable) {
                    System.out.println(">>> on Failure!");
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
	    return js+"";
	}
}
