package com.class_chapter.model;

import java.io.Serializable;

public class Class_chapterVO implements Comparable<Class_chapterVO> ,Serializable {
	
	private String chapter_id;
	private String class_id;
	private String chapter_name;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chapter_id == null) ? 0 : chapter_id.hashCode());
		result = prime * result + ((chapter_name == null) ? 0 : chapter_name.hashCode());
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
		Class_chapterVO other = (Class_chapterVO) obj;
		if (chapter_id == null) {
			if (other.chapter_id != null)
				return false;
		} else if (!chapter_id.equals(other.chapter_id))
			return false;
		if (chapter_name == null) {
			if (other.chapter_name != null)
				return false;
		} else if (!chapter_name.equals(other.chapter_name))
			return false;
		if (class_id == null) {
			if (other.class_id != null)
				return false;
		} else if (!class_id.equals(other.class_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Class_chapterVO [chapter_id=" + chapter_id + ", class_id=" + class_id + ", chapter_name=" + chapter_name
				+ "]";
	}
	public String getChapter_id() {
		return chapter_id;
	}
	public void setChapter_id(String chapter_id) {
		this.chapter_id = chapter_id;
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
	@Override
	public int compareTo(Class_chapterVO o) {
		
		if(this.getChapter_id().compareTo(o.getChapter_id())>0) {
			return 1;
		}else if(this.getChapter_id().compareTo(o.getChapter_id())==0){
			return 0;
		}
		return -1;
	}

}
