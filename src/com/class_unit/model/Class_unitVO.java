package com.class_unit.model;

import java.util.Arrays;
import java.io.Serializable;
import java.sql.Timestamp;

public class Class_unitVO implements Serializable{
	private String unit_id;
	private String chapter_id;
	private String unit_name;
	private byte[] video;
	private String video_long;
	private Timestamp video_updatetime;
	private Integer video_status;
	
	
	@Override
	public String toString() {
		return "Class_unitVO [unit_id=" + unit_id + ", chapter_id=" + chapter_id + ", unit_name=" + unit_name
				+ ", video=" + Arrays.toString(video) + ", video_long=" + video_long + ", video_updatetime="
				+ video_updatetime + ", video_status=" + video_status + "]";
	}


	public String getUnit_id() {
		return unit_id;
	}


	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}


	public String getChapter_id() {
		return chapter_id;
	}


	public void setChapter_id(String chapter_id) {
		this.chapter_id = chapter_id;
	}


	public String getUnit_name() {
		return unit_name;
	}


	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}


	public byte[] getVideo() {
		return video;
	}


	public void setVideo(byte[] video) {
		this.video = video;
	}


	public String getVideo_long() {
		return video_long;
	}


	public void setVideo_long(String video_long) {
		this.video_long = video_long;
	}


	public Timestamp getVideo_updatetime() {
		return video_updatetime;
	}


	public void setVideo_updatetime(Timestamp video_updatetime) {
		this.video_updatetime = video_updatetime;
	}


	public Integer getVideo_status() {
		return video_status;
	}


	public void setVideo_status(Integer video_status) {
		this.video_status = video_status;
	}
}
