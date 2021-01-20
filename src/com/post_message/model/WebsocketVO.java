package com.post_message.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class WebsocketVO implements Serializable{
	private Integer type;//訊息顯示狀態
	private String post_content;// 發送內容
	private Timestamp send_time;//發送時間
	private String admin_id;//發送者
	private String target_type;//發送對象
	


	public WebsocketVO(Integer type,String post_content,Timestamp send_time, String admin_id, String target_type) {
		this.type = type;
		this.post_content = post_content;
		this.send_time = send_time ;
		this.admin_id = admin_id;
		this.target_type = target_type;
	}

	
	
	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
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
	
	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getTarget_type() {
		return target_type;
	}

	public void setTarget_type(String target_type) {
		this.target_type = target_type;
	}
	
	

}
