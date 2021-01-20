package com.sub_class.model;

public class Sub_classVO implements java.io.Serializable {
	private String subClass_id;
	private String subClass_name;
	private Integer subClass_status;
	private String mainClass_id;
	
	@Override
	public String toString() {
		return "SubClassVO [subClass_id=" + subClass_id + ", subClass_name=" + subClass_name
				+ ", subClass_status=" + subClass_status + ", mainClass_id=" + mainClass_id + "]";
	}
	

	public String getSubClass_id() {
		return subClass_id;
	}

	public void setSubClass_id(String subClass_id) {
		this.subClass_id = subClass_id;
	}


	public String getSubClass_name() {
		return subClass_name;
	}

	public void setSubClass_name(String subClass_name) {
		this.subClass_name = subClass_name;
	}

	public Integer getSubClass_status() {
		return subClass_status;
	}

	public void setSubClass_status(Integer subClass_status) {
		this.subClass_status = subClass_status;
	}

	public String getMainClass_id() {
		return mainClass_id;
	}

	public void setMainClass_id(String mainClass_id) {
		this.mainClass_id = mainClass_id;
	}

}
