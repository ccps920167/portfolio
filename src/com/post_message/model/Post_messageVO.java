package com.post_message.model;

import java.sql.Timestamp;

public class Post_messageVO implements java.io.Serializable{
	
	
	private String post_id;      //公告編號  PI12345
	private String post_content; //公告內容
	private Timestamp send_time; //發送時間
	private String admin_id;     //公告人(管理員編號) AI12345
	private Integer target_type;  //公告對象身份 預設1 ,0老師 1一般會員
	
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
	
//將狀態改為文字顯示method
	public String getTarget_typeword() {
		if (this.target_type==1) {
			return "一般會員";
		} else {
			return "老師";
		}
	}
}
