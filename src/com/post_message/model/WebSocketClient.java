package com.post_message.model;



	import java.io.IOException;
	import java.net.URI;

	import javax.websocket.ClientEndpoint;
	import javax.websocket.ContainerProvider;
	import javax.websocket.OnClose;
	import javax.websocket.OnError;
	import javax.websocket.OnMessage;
	import javax.websocket.OnOpen;
	import javax.websocket.Session;
	import javax.websocket.WebSocketContainer;

	@ClientEndpoint
	public class WebSocketClient {
		
		private Session session;
		@OnOpen
		public void onOpen(Session session) throws IOException {
			this.session = session;
		}

		@OnMessage
		public void onMessage(String message) {
		}

		@OnError
		public void onError(Throwable t) {
			t.printStackTrace();
		}
		/**
	     * 
	     * @throws Exception 
	     */
	    @OnClose
	    public void onClose() throws Exception{
	    }
	    
	    /**
	     * @param message
	     * @throws IOException
	     */
	    public void closeSocket() throws IOException{
	        this.session.close();
	    }
	    
	    /**
	     * @param message
	     * @throws IOException
	     */
	    public void sendMessage(String message) throws IOException{
	        this.session.getBasicRemote().sendText(message);
	    }
	    
	    public void start(String uri) {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			try {
				this.session = container.connectToServer(WebSocketClient.class, URI.create(uri));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
