package com.order_info.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MemberOrder_infoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String order_list_id; //Pk
	private String order_id;//FK
	private Timestamp order_time;
	private Timestamp payment_time;
	private String pay_way;
	private String class_id;//FK
	private String purchase_plan;
	private Integer amount;
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getOrder_list_id() {
		return order_list_id;
	}
	public void setOrder_list_id(String order_list_id) {
		this.order_list_id = order_list_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getPurchase_plan() {
		return purchase_plan;
	}
	public void setPurchase_plan(String purchase_plan) {
		this.purchase_plan = purchase_plan;
	}

	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public Timestamp getPayment_time() {
		return payment_time;
	}
	public void setPayment_time(Timestamp payment_time) {
		this.payment_time = payment_time;
	}
	public String getPay_way() {
		return pay_way;
	}
	public void setPay_way(String pay_way) {
		this.pay_way = pay_way;
	}
	@Override
	public String toString() {
		return "MemberOrder_infoVO [order_list_id=" + order_list_id + ", order_id=" + order_id + ", order_time="
				+ order_time + ", payment_time=" + payment_time + ", pay_way=" + pay_way + ", class_id=" + class_id
				+ ", purchase_plan=" + purchase_plan + "]";
	}


	

 
}
