package com.chat.controller;

import java.io.IOException;
import java.util.Collection;
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

import com.chat.jedis.JedisHandleMessage;
import com.chat.model.ChatMessage;
import com.chat.model.State;

@ServerEndpoint("/FriendWS/{userName}")
public class FriendWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	private static Map<String, Integer> unReadMessageCount = new ConcurrentHashMap<>();
    
	
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		if(userName.equals("AI00010")) {
			userSession.getAsyncRemote().sendText(gson.toJson(unReadMessageCount));
			System.out.println(gson.toJson(unReadMessageCount));
		}
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			if (session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		
        
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		if(!sessionsMap.containsKey("AI00010")) {
			if(!unReadMessageCount.containsKey(sender)) {
				System.out.println("have sender");
				Integer unReadCount = new Integer(1);
				unReadMessageCount.put(sender, unReadCount);
			}else{
				Integer unReadCount = unReadMessageCount.get(sender);
				unReadMessageCount.replace(sender, unReadCount);
			}
			
		}
		if ("history".equals(chatMessage.getType())) {
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			String historyMsg = gson.toJson(historyData);
			
			ChatMessage cmHistory = new ChatMessage("history", sender, receiver, historyMsg);
			if (userSession != null && userSession.isOpen()) {  //使用者有連線且開通
				System.out.println("history = " + gson.toJson(cmHistory));
				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
				return;
			}
		}
		
		
		Session receiverSession = sessionsMap.get(receiver);
		if (receiverSession != null && receiverSession.isOpen()) {//接收者有連線且開通
			receiverSession.getAsyncRemote().sendText(message);
//		}else {
//			userSession.getAsyncRemote().sendText(message);
			
		}
			userSession.getAsyncRemote().sendText(message);
		JedisHandleMessage.saveChatMessage(sender, receiver, message);
		System.out.println("Message received: " + message);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
		e.printStackTrace();
		
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
