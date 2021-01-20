package com.member_interest.model;

public class Member_interestVO implements java.io.Serializable {
	private String interest_id;
	private String member_id;
	private String subclass_id;
	private Integer interest_status;
	
	public String getInterest_id() {
		return interest_id;
	}
	public void setInterest_id(String interest_id) {
		this.interest_id = interest_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getSubclass_id() {
		return subclass_id;
	}
	public void setSubclass_id(String subclass_id) {
		this.subclass_id = subclass_id;
	}
	public Integer getInterest_status() {
		return interest_status;
	}
	public void setInterest_status(Integer interest_status) {
		this.interest_status = interest_status;
	}
	@Override
	public String toString() {
		return "Member_interestVO [interest_id=" + interest_id + ", member_id=" + member_id + ", subclass_id="
				+ subclass_id + ", interest_status=" + interest_status + ", getInterest_id()=" + getInterest_id()
				+ ", getMember_id()=" + getMember_id() + ", getSubclass_id()=" + getSubclass_id()
				+ ", getInterest_status()=" + getInterest_status() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
	
	