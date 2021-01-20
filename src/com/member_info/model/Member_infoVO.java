package com.member_info.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;

public class Member_infoVO implements java.io.Serializable  {
	
	private static final long serialVersionUID = 1L;
	private String member_id;
	private String member_name;
	private String member_email;
	private String member_password;
	private Integer member_role;
	private Integer member_gender;
	private Date member_birthday;
	private String member_occupation;
	private String member_address;
	private String member_invoice;
	private byte[] member_pic;
	private Integer teachclass_on;
	private Integer learnclass_on;
	private Integer member_homework;
	private String member_about;
	private String member_good_for;
	private Timestamp register_time;
	private Timestamp member_update;
	private String traccount;
	private String bank_code;
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public Integer getMember_role() {
		return member_role;
	}
	public void setMember_role(Integer member_role) {
		this.member_role = member_role;
	}
	public Integer getMember_gender() {
		return member_gender;
	}
	public void setMember_gender(Integer member_gender) {
		this.member_gender = member_gender;
	}
	public Date getMember_birthday() {
		return member_birthday;
	}
	public void setMember_birthday(Date member_birthday) {
		this.member_birthday = member_birthday;
	}
	public String getMember_occupation() {
		return member_occupation;
	}
	public void setMember_occupation(String member_occupation) {
		this.member_occupation = member_occupation;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getMember_invoice() {
		return member_invoice;
	}
	public void setMember_invoice(String member_invoice) {
		this.member_invoice = member_invoice;
	}
	
	public byte[] getMember_pic() {
		return member_pic ;
	}
	
	public void setMember_pic(byte[] member_pic) {
		this.member_pic = member_pic;
	}
	public Integer getTeachclass_on() {
		return teachclass_on;
	}
	public void setTeachclass_on(Integer teachclass_on) {
		this.teachclass_on = teachclass_on;
	}
	public Integer getLearnclass_on() {
		return learnclass_on;
	}
	public void setLearnclass_on(Integer learnclass_on) {
		this.learnclass_on = learnclass_on;
	}
	public Integer getMember_homework() {
		return member_homework;
	}
	public void setMember_homework(Integer member_homework) {
		this.member_homework = member_homework;
	}
	public String getMember_about() {
		return member_about;
	}
	public void setMember_about(String member_about) {
		this.member_about = member_about;
	}
	public String getMember_good_for() {
		return member_good_for;
	}
	public void setMember_good_for(String member_good_for) {
		this.member_good_for = member_good_for;
	}
	public Timestamp getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Timestamp register_time) {
		this.register_time = register_time;
	}
	public Timestamp getMember_update() {
		return member_update;
	}
	public void setMember_update(Timestamp member_update) {
		this.member_update = member_update;
	}
	public String getTraccount() {
		return traccount;
	}
	public void setTraccount(String traccount) {
		this.traccount = traccount;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	@Override
	public String toString() {
		return "Member_infoVO [member_id=" + member_id + ", member_name=" + member_name + ", member_email="
				+ member_email + ", member_password=" + member_password + ", member_role=" + member_role
				+ ", member_gender=" + member_gender + ", member_birthday=" + member_birthday + ", member_occupation="
				+ member_occupation + ", member_address=" + member_address + ", member_invoice=" + member_invoice
				+ ", member_pic=" + Arrays.toString(member_pic) + ", teachclass_on=" + teachclass_on
				+ ", learnclass_on=" + learnclass_on + ", member_homework=" + member_homework
				+ ", member_about=" + member_about + ", member_good_for=" + member_good_for + ", register_time="
				+ register_time + ", member_update=" + member_update + ", traccount="
				+ traccount + ", bank_code=" + bank_code + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
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
		Member_infoVO other = (Member_infoVO) obj;
		if (member_id == null) {
			if (other.member_id != null)
				return false;
		} else if (!member_id.equals(other.member_id))
			return false;
		return true;
	}
	
	
	
	
}
