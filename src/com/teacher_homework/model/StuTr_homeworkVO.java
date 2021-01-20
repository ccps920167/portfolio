package com.teacher_homework.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class StuTr_homeworkVO implements java.io.Serializable{
	private String studenthw_id;
	private String teacherhw_id;
	private String member_id;
	private byte[] file_data;
	private Timestamp hw_uploadtime;
	private Timestamp hw_updatetime;
	private String hw_name;
	private String hw_content;
	@Override
	public String toString() {
		return "StuTr_homeworkVO [studenthw_id=" + studenthw_id + ", teacherhw_id=" + teacherhw_id + ", member_id="
				+ member_id + ", file_data=" + Arrays.toString(file_data) + ", hw_uploadtime=" + hw_uploadtime
				+ ", hw_updatetime=" + hw_updatetime + ", hw_name=" + hw_name + ", hw_content=" + hw_content + "]";
	}
	public String getStudenthw_id() {
		return studenthw_id;
	}
	public void setStudenthw_id(String studenthw_id) {
		this.studenthw_id = studenthw_id;
	}
	public String getTeacherhw_id() {
		return teacherhw_id;
	}
	public void setTeacherhw_id(String teacherhw_id) {
		this.teacherhw_id = teacherhw_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public byte[] getFile_data() {
		return file_data;
	}
	public void setFile_data(byte[] file_data) {
		this.file_data = file_data;
	}
	public Timestamp getHw_uploadtime() {
		return hw_uploadtime;
	}
	public void setHw_uploadtime(Timestamp hw_uploadtime) {
		this.hw_uploadtime = hw_uploadtime;
	}
	public Timestamp getHw_updatetime() {
		return hw_updatetime;
	}
	public void setHw_updatetime(Timestamp hw_updatetime) {
		this.hw_updatetime = hw_updatetime;
	}
	public String getHw_name() {
		return hw_name;
	}
	public void setHw_name(String hw_name) {
		this.hw_name = hw_name;
	}
	public String getHw_content() {
		return hw_content;
	}
	public void setHw_content(String hw_content) {
		this.hw_content = hw_content;
	}
}
