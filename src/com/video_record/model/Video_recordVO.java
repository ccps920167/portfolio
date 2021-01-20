package com.video_record.model;

import java.sql.Timestamp;

public class Video_recordVO implements java.io.Serializable {
	
	private String record_id;
	private String member_id;
	private String unit_id 	;
	private String class_last	;
	private Timestamp video_updateTime;
	

	@Override
	public String toString() {
		return "Video_recordVO [record_id=" + record_id + ", member_id=" + member_id + ", unit_id=" + unit_id
				+ ", class_last=" + class_last + ", video_updateTime=" + video_updateTime + "]";
	}
	
	
	public String getRecord_id() {
		return record_id;
	}
	public void setRecord_id(String record_id) {
		this.record_id = record_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getunit_id() {
		return unit_id;
	}
	public void setunit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public String getClass_last() {
		return class_last;
	}
	public void setClass_last(String class_last) {
		this.class_last = class_last;
	}
	public Timestamp getVideo_updateTime() {
		return video_updateTime;
	}
	public void setVideo_updateTime(Timestamp video_updateTime) {
		this.video_updateTime = video_updateTime;
	}
	
}
