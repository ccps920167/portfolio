package com.class_chapter.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class Class_chapterVO2 {
	
	private String unit_id;
	private String chapter_id;
	private String unit_name;
	private byte[] video;
	private String video_long;
	private Timestamp video_updatetime;
	private Integer video_status;
	private String class_id;
	private String chapter_name;
	
	@Override
	public String toString() {
		return "Class_chapterVO2 [unit_id=" + unit_id + ", chapter_id=" + chapter_id + ", unit_name=" + unit_name
				+ ", video=" + Arrays.toString(video) + ", video_long=" + video_long + ", video_updatetime="
				+ video_updatetime + ", video_status=" + video_status + ", class_id=" + class_id + ", chapter_name="
				+ chapter_name + "]";
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
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}

}
