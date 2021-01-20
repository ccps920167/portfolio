package com.admin_auth.model;

import java.sql.Timestamp;

public class Admin_authVO implements java.io.Serializable{
	
	private String admin_id;
	private String auth_id;
	private Integer auth_status;
	private Timestamp auth_update;
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}
	public Integer getAuth_status() {
		return auth_status;
	}
	public void setAuth_status(Integer auth_status) {
		this.auth_status = auth_status;
	}
	public Timestamp getAuth_update() {
		return auth_update;
	}
	public void setAuth_update(Timestamp auth_update) {
		this.auth_update = auth_update;
	}
	
	
	

}
