package com.sub_message.model;

import java.sql.Timestamp;

public class Sub_messageVO implements java.io.Serializable{
	
	
	private String submsg_id;   //�p�d��id  SM12345 �ǲ߭����s��
	private String mainmsg_id;  //�j�d��id  MM12345 �ҵ{�s��
	private String member_id;   //�|���s��      MEM12345
	private Timestamp submsg_time; //�p�d���ɶ�
	private String submsg_text;    //�d����r �ǲ߭����d�����e
	private Integer submsg_status; //�T����ܪ��A �w�]1 (0���� 1���)
	
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
	//�N���A�אּ��r���method
		public String getSubmsg_statusword() {
			if(submsg_status==1) {
				return "����";
			}else {
				return "���";
			}
		}

}
