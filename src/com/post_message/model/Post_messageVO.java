package com.post_message.model;

import java.sql.Timestamp;

public class Post_messageVO implements java.io.Serializable{
	
	
	private String post_id;      //���i�s��  PI12345
	private String post_content; //���i���e
	private Timestamp send_time; //�o�e�ɶ�
	private String admin_id;     //���i�H(�޲z���s��) AI12345
	private Integer target_type;  //���i��H���� �w�]1 ,0�Ѯv 1�@��|��
	
	public String getPost_id() {
		return post_id;
	}
	public void setPost_id(String post_id) {
		this.post_id = post_id;
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
	public Integer getTarget_type() {
		return target_type;
	}
	public void setTarget_type(Integer target_type) {
		this.target_type = target_type;
	}
	
//�N���A�אּ��r���method
	public String getTarget_typeword() {
		if (this.target_type==1) {
			return "�@��|��";
		} else {
			return "�Ѯv";
		}
	}
}
