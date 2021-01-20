package com.post_message.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.post_message.model.Post_messageService;
import com.post_message.model.Post_messageVO;
import com.post_message.model.State;
import com.post_message.model.WebsocketVO;
import com.post_messageWebsocketchat.jedis.JedisHandleMessage;

@ServerEndpoint("/Notification/{post_content}")
public class Notification {
	private static final Timestamp Timestamp = null;
//	private static List<Session> sessions = new LinkedList<>();
	private static Map<String,Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();

	@OnOpen
//	public void onOpen(Session userSession) throws IOException {
//		sessions.add(userSession);
	
	public void onOpen(@PathParam("post_content") String admin_id,Session userSession) throws IOException {
	System.out.println("onOpen");
    sessionsMap.put(admin_id,userSession);
		

	}

	public void sendNotify(Integer type, String post_content, Timestamp send_time, String admin_id,
			String target_type) {
		switch (type) {
		case 1:
			Post_messageService post_messageService = new Post_messageService();
			Post_messageVO websocketVO = post_messageService.getOnePost_message(post_content);
			
			WebsocketVO websocketVO1 = new WebsocketVO(type, post_content, send_time, admin_id, target_type);
			String WebsocketVOJson = gson.toJson(websocketVO);
			System.out.println("sendNotify" + WebsocketVOJson);
			onMessage(WebsocketVOJson);

			break;
		default:
			break;
		}
	}

	@OnMessage
	public void onMessage(String WebsocketVOJson) {

//		for (Session session : sessions) {
//			System.out.println("Notification message" + WebsocketVOJson);
//			if (session.isOpen())
//				session.getAsyncRemote().sendText(WebsocketVOJson);
//		}

			WebsocketVO websocketVO = gson.fromJson(WebsocketVOJson, WebsocketVO.class);
			String message = websocketVO.getPost_content();
			
			Post_messageService Srv = new Post_messageService();
			Timestamp getSend_time = new java.sql.Timestamp(new Date().getTime());
			Srv.aaddPost_message(websocketVO.getPost_content(), getSend_time, websocketVO.getAdmin_id(), websocketVO.getType());
			

			sessionsMap.forEach((key, wsSession) -> {
				// 不要發送給後台
				if (!"post_content".equals(key)) {
					if (wsSession != null && wsSession.isOpen()) {
						wsSession.getAsyncRemote().sendText(WebsocketVOJson);
					}
				}
			});

        System.out.println("Message received: " + WebsocketVOJson);
	}

	@OnError
	public void onError(Session post_contentSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}


	@OnClose
	public void onClose(Session post_contentSession, CloseReason reason) {

			String post_contentClose = null;
			Set<String> userNames = sessionsMap.keySet();
			for (String post_content : userNames) {
				if (sessionsMap.get(post_content).equals(post_contentSession)) {
					post_contentClose = post_content;
					sessionsMap.remove(post_content);
					break;
				}
			}
		


			if (post_contentClose != null) {
				State stateMessage = new State ("close", post_contentClose, userNames);
				String stateMessageJson = gson.toJson(stateMessage);
				Collection<Session> sessions = sessionsMap.values();
				for (Session session : sessions) {
					session.getAsyncRemote().sendText(stateMessageJson);
				}
			}

			String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", post_contentSession.getId(),
					reason.getCloseCode().getCode(), userNames);
			System.out.println(text);
	}
}
