package com.post_message.model;

import java.sql.Timestamp;
import java.util.Set;


 
public class WebsocketPost_message {
	private String post_content;
	// the user changing the post_content
	private Timestamp send_time;
	//µo°e®É¶¡
	private String admin_id;
	// total users
	private Set<String> target_type;

	public WebsocketPost_message(String post_content,Timestamp send_time, String admin_id, Set<String> target_type) {
		super();
		this.post_content = post_content;
		this.send_time = send_time ;
		this.admin_id = admin_id;
		this.target_type = target_type;
	}

	public String getPost_content() {
		return post_content;
	}

	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}

	public Timestamp getSend_time() {
		return send_time;
	}

	public void setSend_time(Timestamp send_time) {
		this.send_time = send_time;
	}

	public Set<String> getTarget_type() {
		return target_type;
	}

	public void setTarget_type(Set<String> target_type) {
		this.target_type = target_type;
	}

}
