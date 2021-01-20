package com.sub_message.model;

import java.sql.Timestamp;

public class Sub_messageVO implements java.io.Serializable{
	
	
	private String submsg_id;   //小留言id  SM12345 學習頁面編號
	private String mainmsg_id;  //大留言id  MM12345 課程編號
	private String member_id;   //會員編號      MEM12345
	private Timestamp submsg_time; //小留言時間
	private String submsg_text;    //留言文字 學習頁面留言內容
	private Integer submsg_status; //訊息顯示狀態 預設1 (0隱藏 1顯示)
	
	public String getSubmsg_id() {
		return submsg_id;
	}
	public void setSubmsg_id(String submsg_id) {
		this.submsg_id = submsg_id;
	}
	public String getMainmsg_id() {
		return mainmsg_id;
	}
	public void setMainmsg_id(String mainmsg_id) {
		this.mainmsg_id = mainmsg_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Timestamp getSubmsg_time() {
		return submsg_time;
	}
	public void setSubmsg_time(Timestamp submsg_time) {
		this.submsg_time = submsg_time;
	}
	public String getSubmsg_text() {
		return submsg_text;
	}
	public void setSubmsg_text(String submsg_text) {
		this.submsg_text = submsg_text;
	}
	public Integer getSubmsg_status() {
		return submsg_status;
	}
	public void setSubmsg_status(Integer submsg_status) {
		this.submsg_status = submsg_status;
	}
	//將狀態改為文字顯示method
		public String getSubmsg_statusword() {
			if(submsg_status==1) {
				return "隱藏";
			}else {
				return "顯示";
			}
		}

}
