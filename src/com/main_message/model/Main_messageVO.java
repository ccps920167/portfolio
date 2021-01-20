package com.main_message.model;

import java.sql.Timestamp;

import com.sun.xml.internal.ws.api.message.Message;

public class Main_messageVO implements java.io.Serializable , Comparable<Main_messageVO>{
	
	private String mainmsg_id;     //�j�d��id MM12345
	private String class_id;       //�ҵ{�s��    CLA12345
	private String member_id;      //�|���s��    MEM12345
	private Timestamp mainmsg_time;//�ɶ�
	private String mainmsg_text;   //�d����r
	private String msg_source;     //�d���ӷ�
	private Integer msg_status;    //�T����ܪ��A �w�]1 , 0���� 1���
	
	public int compareTo(Main_messageVO o) {
		
		if(this.getMainmsg_id().compareTo(o.getMainmsg_id())>0) {
			return 1;
		}else if(this.getMainmsg_id().compareTo(o.getMainmsg_id())==0){
			return 0;
		}
		return -1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mainmsg_id == null) ? 0 : mainmsg_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Main_messageVO other = (Main_messageVO) obj;
		if (mainmsg_id == null) {
			if (other.mainmsg_id != null)
				return false;
		} else if (!mainmsg_id.equals(other.mainmsg_id))
			return false;
		return true;
	}
	public String getMainmsg_id() {
		return mainmsg_id;
	}
	public void setMainmsg_id(String mainmsg_id) {
		this.mainmsg_id = mainmsg_id;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Timestamp getMainmsg_time() {
		return mainmsg_time;
	}
	public void setMainmsg_time(Timestamp mainmsg_time) {
		this.mainmsg_time = mainmsg_time;
	}
	public String getMainmsg_text() {
		return mainmsg_text;
	}
	public void setMainmsg_text(String mainmsg_text) {
		this.mainmsg_text = mainmsg_text;
	}
	public String getMsg_source() {
		return msg_source;
	}
	public void setMsg_source(String msg_source) {
		this.msg_source = msg_source;
	}
	public Integer getMsg_status() {
		return msg_status;
	}
	public void setMsg_status(Integer msg_status) {
		this.msg_status = msg_status;
	}
	
	//�N���A�אּ��r���method
	public String getMsg_statusword() {
		if(msg_status==1) {
			return "����";
		}else {
			return "���";
		}
	}

	
	
}
