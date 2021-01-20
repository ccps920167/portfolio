package com.post_message.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class WebsocketVO implements Serializable{
	private Integer type;//�T����ܪ��A
	private String post_content;// �o�e���e
	private Timestamp send_time;//�o�e�ɶ�
	private String admin_id;//�o�e��
	private String target_type;//�o�e��H
	


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
