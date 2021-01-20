package com.post_message.model;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.post_message.controller.WebSocketServlet;

public class WebSocketMapUtil {
	public static ConcurrentMap<String, WebSocketServlet> webSocketMap = new ConcurrentHashMap<>();
    public static void put(String key, WebSocketServlet WebSocketServlet){
    	webSocketMap.put(key, WebSocketServlet);
    }
    	 
    public static WebSocketServlet get(String key){
    	 return webSocketMap.get(key);
    }
    	 
	public static void remove(String key){
		 webSocketMap.remove(key);
	}
	
	public static Collection<WebSocketServlet> getValues(){
		return webSocketMap.values();
	}
}
