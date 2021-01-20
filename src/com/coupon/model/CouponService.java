package com.coupon.model;

import java.sql.Timestamp;
import java.util.List;

public class CouponService {
	private CouponDAO_interface Cou;

	public CouponService() {
		Cou = new CouponJDBCDAO();
	}

	public CouponVO addCoupon(String coupon_code, Integer coupon_amount, Timestamp coupon_time,
			Timestamp coupon_expiry) {
		CouponVO couponVO = new CouponVO();

		couponVO.setCoupon_code(coupon_code);
		couponVO.setCoupon_amount(coupon_amount);
		couponVO.setCoupon_time(coupon_time);
		couponVO.setCoupon_expiry(coupon_expiry);
		Cou.insert(couponVO);
		return couponVO;
	}
	
	public CouponVO updateCoupon(String coupon_id,String coupon_code, Integer coupon_amount, Timestamp coupon_time,
			Timestamp coupon_expiry) {
		CouponVO couponVO = new CouponVO();
		couponVO.setCoupon_code(coupon_code);
		couponVO.setCoupon_amount(coupon_amount);
		couponVO.setCoupon_time(coupon_time);
		couponVO.setCoupon_expiry(coupon_expiry);
		couponVO.setCoupon_id(coupon_id);
		Cou.update(couponVO);
		return couponVO;
	}
	
	public void deleteEmp(String coupon_id) {
		Cou.delete(coupon_id);
	}
	
	public CouponVO getOneCoupon(String coupon_id) {
		return Cou.findByPrimaryKey(coupon_id);
	}
	
	public List<CouponVO> getAll(){
		return Cou.getAll();
	}
}
