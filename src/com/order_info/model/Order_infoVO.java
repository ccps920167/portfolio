package com.order_info.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Order_infoVO implements Serializable ,Comparable<Order_infoVO> {
	private String order_ID;// PK
	private String member_id;// FK
	private Timestamp order_time;
	private Timestamp payment_time;
	private String pay_way;
	private Integer order_status;
	private String coupon_ID;
	private Integer amount;

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_ID == null) ? 0 : order_ID.hashCode());
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
		Order_infoVO other = (Order_infoVO) obj;
		if (order_ID == null) {
			if (other.order_ID != null)
				return false;
		} else if (!order_ID.equals(other.order_ID))
			return false;
		return true;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Order_infoVO [order_ID=" + order_ID + ", member_id=" + member_id + ", order_time=" + order_time
				+ ", payment_time=" + payment_time + ", pay_way=" + pay_way + ", order_status=" + order_status
				+ ", coupon_ID=" + coupon_ID + ", amount=" + amount + "]";
	}

	public String getOrder_ID() {
		return order_ID;
	}

	public void setOrder_ID(String order_ID) {
		this.order_ID = order_ID;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
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

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}

	public String getCoupon_ID() {
		return coupon_ID;
	}

	public void setCoupon_ID(String coupon_ID) {
		this.coupon_ID = coupon_ID;
	}
	
	public int compareTo(Order_infoVO o) {
		
		if(this.getOrder_ID().compareTo(o.getOrder_ID())>0) {
			return 1;
		}else if(this.getOrder_ID().compareTo(o.getOrder_ID())==0){
			return 0;
		}
		return -1;
	}

}
