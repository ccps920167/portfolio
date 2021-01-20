package com.post_message.controller;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.post_message.model.WebSocketMapUtil;

@ServerEndpoint(value = "/websocket")  
public class WebSocketServlet{
   
    private Session session;
   
    
    
    
    // 連接打開
    
    @OnOpen
    public void onOpen(Session session) throws Exception{
        this.session = session;
        WebSocketMapUtil.put(session.getQueryString(),this);
    }
     
  
    // 連接關閉 
   
    @OnClose
    public void onClose() throws Exception{
    	//從map中删除
    	WebSocketMapUtil.remove(session.getQueryString());
    }
     
    /**
     * 收到客户端消息
     * @param message 客户端發送過來的消息
     * @param session 可選的参数
     * @throws IOException 
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        try {
        	WebSocketServlet WebSocketServlet= ((WebSocketServlet) WebSocketMapUtil.get(session.getQueryString().replace("service", "client")));
        	if(WebSocketServlet != null){
        		WebSocketServlet.sendMessage(message);
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    /**
     * 發生錯誤
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }
     
    
    /**
     * 發送消息
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
    }
    
    /**
     * 群發消息
     * @param message
     * @throws IOException
     */
    public void sendMessageAll(String message) throws IOException{
    	for(WebSocketServlet WebSocketServlet : WebSocketMapUtil.getValues()){
    		WebSocketServlet.sendMessage(message);
    	}
    }
}