package com.order_info.model;

import java.util.List;

import com.coupon.model.CouponVO;
import com.order_list.model.Order_listVO;

public interface Order_infoDAO_interface {
	public void insert(Order_infoVO order_infoVO);
	public void update(Order_infoVO order_infoVO);
	public void delete(String order_infoVO);
	public Order_infoVO findByPrimaryKey(String order_ID);
	public Order_infoVO findmember_id(String member_id);
	public List<Order_infoVO> getAll();
	public void insertwithinfo(Order_infoVO order_infoVO,List<Order_listVO> list);
	public List<MemberOrder_infoVO> getMemberOrderList();
}
