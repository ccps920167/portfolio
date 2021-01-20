package com.verify_list.model;

import java.sql.*;


public class Verify_listVO implements java.io.Serializable{
	private String verify_id;
	private String class_id;
	private String admin_id;
	private String class_check;
	private Timestamp upload_time;
	
	public String getVerify_id() {
		return verify_id;
	}
	public void setVerify_id(String verify_id) {
		this.verify_id = verify_id;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getClass_check() {
		return class_check;
	}
	public void setClass_check(String class_check) {
		this.class_check = class_check;
	}
	public Timestamp getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Timestamp upload_time) {
		this.upload_time = upload_time;
	}
	

}
