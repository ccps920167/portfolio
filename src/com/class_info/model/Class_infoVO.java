package com.class_info.model;
import java.sql.*;
import java.util.*;

public class Class_infoVO implements java.io.Serializable{
	private String class_id;
	private String class_name;
	private String member_id;
	private Integer class_status;
	private String subclass_id;
	private Timestamp startfund_date;
	private Timestamp startclass_time;
	private String class_description;
	private byte[] class_picture;
	private Integer startfund_price;
	private Integer original_price;
	private Integer people_threshold;
	private String class_length;
	private byte[] video_fundraising;
	private Timestamp update_time;
	private String admin_id;
	
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Integer getClass_status() {
		return class_status;
	}
	public void setClass_status(Integer class_status) {
		this.class_status = class_status;
	}
	public String getSubclass_id() {
		return subclass_id;
	}
	public void setSubclass_id(String subclass_id) {
		this.subclass_id = subclass_id;
	}
	public Timestamp getStartfund_date() {
		return startfund_date;
	}
	public void setStartfund_date(Timestamp startfund_date) {
		this.startfund_date = startfund_date;
	}
	public Timestamp getStartclass_time() {
		return startclass_time;
	}
	public void setStartclass_time(Timestamp startclass_time) {
		this.startclass_time = startclass_time;
	}
	public String getClass_description() {
		return class_description;
	}
	public void setClass_description(String class_description) {
		this.class_description = class_description;
	}
	public byte[] getClass_picture() {
		return class_picture;
	}
	
	public String getClass_picture2() {
		Base64.Encoder encoder =  Base64.getEncoder();
		String base64 = encoder.encodeToString(this.class_picture);
		
		return base64;
	}
	
	public void setClass_picture(byte[] class_picture) {
		this.class_picture = class_picture;
	}
	public Integer getStartfund_price() {
		return startfund_price;
	}
	public void setStartfund_price(Integer startfund_price) {
		this.startfund_price = startfund_price;
	}
	public Integer getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(Integer original_price) {
		this.original_price = original_price;
	}
	public Integer getPeople_threshold() {
		return people_threshold;
	}
	public void setPeople_threshold(Integer people_threshold) {
		this.people_threshold = people_threshold;
	}
	public String getClass_length() {
		return class_length;
	}
	public void setClass_length(String class_length) {
		this.class_length = class_length;
	}
	public byte[] getVideo_fundraising() {
		return video_fundraising;
	}
	public String getVideo_fundraising2() {
		Base64.Encoder encoder =  Base64.getEncoder();
		String base64 = encoder.encodeToString(this.class_picture);
		
		return base64;
	}
	public void setVideo_fundraising(byte[] video_fundraising) {
		this.video_fundraising = video_fundraising;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((class_id == null) ? 0 : class_id.hashCode());
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
		Class_infoVO other = (Class_infoVO) obj;
		if (class_id == null) {
			if (other.class_id != null)
				return false;
		} else if (!class_id.equals(other.class_id))
			return false;
		return true;
	}
	
	
	
	

}
