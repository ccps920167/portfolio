package com.coupon.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import oracle.sql.TIMESTAMP;

public class CouponVO implements Serializable {
	private String coupon_id; //PK
	private String coupon_code;
	private Integer coupon_amount;
	private Timestamp coupon_time;
	private Timestamp coupon_expiry;
	
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getCoupon_code() {
		return coupon_code;
	}
	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}
	public Integer getCoupon_amount() {
		return coupon_amount;
	}
	public void setCoupon_amount(Integer coupon_amount) {
		this.coupon_amount = coupon_amount;
	}
	public Timestamp getCoupon_time() {
		return coupon_time;
	}
	public void setCoupon_time(Timestamp coupon_time) {
		this.coupon_time = coupon_time;
	}
	public Timestamp getCoupon_expiry() {
		return coupon_expiry;
	}
	public void setCoupon_expiry(Timestamp coupon_expiry) {
		this.coupon_expiry = coupon_expiry;
	}
	@Override
	public String toString() {
		return "CouponVO [coupon_id=" + coupon_id + ", coupon_code=" + coupon_code + ", coupon_amount=" + coupon_amount
				+ ", coupon_time=" + coupon_time + ", coupon_expiry=" + coupon_expiry + "]";
	}
	
	
	
	
}

	