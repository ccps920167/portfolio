package com.main_class.model;

public class Main_classVO  implements java.io.Serializable{
	private String mainClass_id	;
	private String mainClass_name	;
	private Integer mainClass_status;
	
	@Override
	public String toString() {
		return "mainClassVO [mainClass_id=" + mainClass_id + ", mainClass_name=" + mainClass_name
				+ ", mainClass_status=" + mainClass_status + "]";
	}
	
	public String getMainClass_id() {
		return mainClass_id;
	}
	public void setMainClass_id(String mainClass_id) {
		this.mainClass_id = mainClass_id;
	}
	public String getMainClass_name() {
		return mainClass_name;
	}
	public void setMainClass_name(String mainClass_name) {
		this.mainClass_name = mainClass_name;
	}
	public Integer getMainClass_status() {
		return mainClass_status;
	}
	public void setMainClass_status(Integer mainClass_status) {
		this.mainClass_status = mainClass_status;
	}
	

}
