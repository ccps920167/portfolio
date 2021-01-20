package com.coupon.model;

import java.util.List;

public interface CouponDAO_interface {
	public void insert(CouponVO couponVO);
	public void update(CouponVO couponVO);
	public void delete(String coupon_id);
	public CouponVO findByPrimaryKey(String coupon_id);
	public List<CouponVO> getAll();
	
	
	

}
