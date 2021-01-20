package com.view_class_income.model;

import java.sql.Timestamp;

public class View_class_incomeVO implements java.io.Serializable {
	private String class_id;
	private String member_id;
	private String class_name;
	private Timestamp startfund_date;
	private Integer price;
	private Integer class_status;
	public Integer getClass_status() {
		return class_status;
	}
	public void setClass_status(Integer class_status) {
		this.class_status = class_status;
	}
	@Override
	public String toString() {
		return "View_class_incomeVO [class_id=" + class_id + ", member_id=" + member_id + ", class_name=" + class_name
				+ ", startfund_date=" + startfund_date + ", price=" + price + "]";
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
		View_class_incomeVO other = (View_class_incomeVO) obj;
		if (class_id == null) {
			if (other.class_id != null)
				return false;
		} else if (!class_id.equals(other.class_id))
			return false;
		return true;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public Timestamp getStartfund_date() {
		return startfund_date;
	}
	public void setStartfund_date(Timestamp startfund_date) {
		this.startfund_date = startfund_date;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	
}
