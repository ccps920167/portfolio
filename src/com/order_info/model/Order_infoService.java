package com.order_info.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.class_info.model.Class_infoVO;
import com.order_list.model.Order_listVO;

public class Order_infoService {
	private Order_infoDAO_interface Ord;

	public Order_infoService() {
		Ord = new Order_infoJDBCDAO();
	}

	public Order_infoVO addOrder_info(String member_id, Timestamp order_time, Timestamp payment_time, String pay_way,
			Integer order_status, String coupon_ID) {
		Order_infoVO order_infoVO = new Order_infoVO();
		order_infoVO.setMember_id(member_id);
		order_infoVO.setOrder_time(order_time);
		order_infoVO.setPayment_time(payment_time);
		order_infoVO.setPay_way(pay_way);
		order_infoVO.setOrder_status(order_status);
		order_infoVO.setCoupon_ID(coupon_ID);
		Ord.insert(order_infoVO);
		return order_infoVO;
	}
	
		public Order_infoVO addOrder_infoVO1(String member_id, Timestamp order_time, Timestamp payment_time, String pay_way,
		Integer order_status, String coupon_ID, Integer amount, String class_id, String purchase_plan) {
		Order_infoVO order = new Order_infoVO();
		Order_listVO inf = new Order_listVO();
		List<Order_listVO> li = new ArrayList<Order_listVO>();
		order.setMember_id(member_id);
		order.setOrder_time(order_time);
		order.setPayment_time(payment_time);
		order.setPay_way(pay_way);
		order.setOrder_status(order_status);
		order.setCoupon_ID(coupon_ID);
		order.setAmount(amount);
		inf.setClass_id(class_id);
		inf.setPurchase_plan(purchase_plan);
		li.add(inf);
		Ord.insertwithinfo(order, li);
		return order;
	}
	
	public Order_infoVO addOrder_infoVO_V2(String member_id, Timestamp order_time, Timestamp payment_time, String pay_way,
			Integer order_status, String coupon_ID, Integer amount, List<Class_infoVO> shoppingCart, String purchase_plan) {
			Order_infoVO order = new Order_infoVO();
			List<Order_listVO> li = new ArrayList<Order_listVO>();
			order.setMember_id(member_id);
			order.setOrder_time(order_time);
			order.setPayment_time(payment_time);
			order.setPay_way(pay_way);
			order.setOrder_status(order_status);
			order.setCoupon_ID(coupon_ID);
			order.setAmount(amount);
			for(Class_infoVO class_infoVO:shoppingCart) {
				Order_listVO inf = new Order_listVO();
				inf.setClass_id(class_infoVO.getClass_id());
				inf.setPurchase_plan(purchase_plan);
				li.add(inf);
			}
			Ord.insertwithinfo(order, li);
			return order;
		}
	

	public Order_infoVO updateOrder_infoVO(String order_ID, String member_id, Timestamp order_time,
			Timestamp payment_time, String pay_way, Integer order_status, String coupon_ID) {
		Order_infoVO order_infoVO = new Order_infoVO();
		order_infoVO.setMember_id(member_id);
		order_infoVO.setOrder_time(order_time);
		order_infoVO.setPayment_time(payment_time);
		order_infoVO.setPay_way(pay_way);
		order_infoVO.setOrder_status(order_status);
		order_infoVO.setCoupon_ID(coupon_ID);
		order_infoVO.setOrder_ID(order_ID);
		Ord.update(order_infoVO);
		return order_infoVO;

	}

	public void deleorder_info(String order_ID) {
		Ord.delete(order_ID);
	}

	public Order_infoVO getOneOrder_infoVO(String order_ID) {
		return Ord.findByPrimaryKey(order_ID);
	}
	
	public Order_infoVO getOneOrder_infoVO1(String member_id) {
		return Ord.findmember_id(member_id);
	}

	public List<Order_infoVO> getAll() {
		return Ord.getAll();
	}
	
	public List<MemberOrder_infoVO> getMemberOrderList(){
		return Ord.getMemberOrderList();
	}
}
