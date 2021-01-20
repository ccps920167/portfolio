package com.evaluation.model;

import java.sql.Timestamp ;

public class EvaluationVO implements java.io.Serializable{
	private String evaluation_id;
	private String class_id;
	private String member_id;
	private String evaluation_class;
	private Integer evaluation_score;
	private Timestamp evaluation_time;
	private Integer evaluation_status;
	
	public String getEvaluation_id() {
		return evaluation_id;
	}
	public EvaluationVO() {
	
	}
	public void setEvaluation_id(String evaluation_id) {
		this.evaluation_id = evaluation_id;
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
	public String getEvaluation_class() {
		return evaluation_class;
	}
	public void setEvaluation_class(String evaluation_class) {
		this.evaluation_class = evaluation_class;
	}
	public Integer getEvaluation_score() {
		return evaluation_score;
	}
	public void setEvaluation_score(Integer evaluation_score) {
		this.evaluation_score = evaluation_score;
	}
	public Timestamp getEvaluation_time() {
		return evaluation_time;
	}
	public void setEvaluation_time(Timestamp evaluation_time) {
		this.evaluation_time = evaluation_time;
	}
	public Integer getEvaluation_status() {
		return evaluation_status;
	}
	public void setEvaluation_status(Integer evaluation_status) {
		this.evaluation_status = evaluation_status;
	}
	@Override
	public String toString() {
		return "evaluationVO [evaluation_id=" + evaluation_id + ", class_id=" + class_id + ", member_id=" + member_id
				+ ", evaluation_class=" + evaluation_class + ", evaluation_score=" + evaluation_score
				+ ", evaluation_time=" + evaluation_time + ", evaluation_status=" + evaluation_status
				+ ", getEvaluation_id()=" + getEvaluation_id() + ", getClass_id()=" + getClass_id()
				+ ", getMember_id()=" + getMember_id() + ", getEvaluation_class()=" + getEvaluation_class()
				+ ", getEvaluation_score()=" + getEvaluation_score() + ", getEvaluation_time()=" + getEvaluation_time()
				+ ", getEvaluation_status()=" + getEvaluation_status() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	



}
