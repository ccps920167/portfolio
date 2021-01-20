package com.test.model;

public class TestVO implements java.io.Serializable{
	private String test_id;
	private String unit_id;
	private String test_answer;
	private String test_content;
	private String opta;
	private String optb;
	private String optc;
	private String optd;
	public String getTest_id() {
		return test_id;
	}
	public void setTest_id(String test_id) {
		this.test_id = test_id;
	}
	public String getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}
	public String getTest_answer() {
		return test_answer;
	}
	public void setTest_answer(String test_answer) {
		this.test_answer = test_answer;
	}
	public String getTest_content() {
		return test_content;
	}
	public void setTest_content(String test_content) {
		this.test_content = test_content;
	}
	public String getOpta() {
		return opta;
	}
	public void setOpta(String opta) {
		this.opta = opta;
	}
	public String getOptb() {
		return optb;
	}
	public void setOptb(String optb) {
		this.optb = optb;
	}
	public String getOptc() {
		return optc;
	}
	public void setOptc(String optc) {
		this.optc = optc;
	}
	public String getOptd() {
		return optd;
	}
	public void setOptd(String optd) {
		this.optd = optd;
	}
	@Override
	public String toString() {
		return "TestVO [test_id=" + test_id + ", unit_id=" + unit_id + ", test_answer=" + test_answer
				+ ", test_content=" + test_content + ", opta=" + opta + ", optb="
				+ optb + ", optc=" + optc + ", optd=" + optd + "]";
	}
	
	

}
