package com.login_history.model;

import java.sql.Timestamp;

public class Login_historyVO implements java.io.Serializable  {
	private String login_id;
	private Timestamp login_time; 
	private String login_arrange ; 
	private String login_ip;
	private String member_id;
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public Timestamp getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
	}
	public String getLogin_arrange() {
		return login_arrange;
	}
	public void setLogin_arrange(String login_arrange) {
		this.login_arrange = login_arrange;
	}
	public String getLogin_ip() {
		return login_ip;
	}
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	@Override
	public String toString() {
		return "Login_historyVO [login_id=" + login_id + ", login_time=" + login_time + ", login_arrange="
				+ login_arrange + ", login_IP=" + login_ip + ", member_id=" + member_id + "]";
	}
	

}
