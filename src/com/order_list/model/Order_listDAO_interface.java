package com.order_list.model;

import java.util.List;
import java.util.Map;

import com.order_info.model.Order_infoVO;

public interface Order_listDAO_interface {
	public void insert(Order_listVO Order_listVO);
	public void update(Order_listVO Order_listVO);
	public void delete(String Order_list);
	public Order_listVO findByPrimaryKey(String order_list_id);
	public Order_listVO findByOrder_ID(String Order_ID);
	public List<Order_listVO> getAll();
	public Map <Order_infoVO,List<Order_listVO>> getMemberOrder(String member_id);
	public List<Order_listVO> getMemberClass(String member_id);
	public List<Order_listVO> getOrderInfo(String order_id);
	public List<Map<String,String>> getClassID(String class_id);
	public void inser2(Order_listVO Order_listVO,java.sql.Connection con);
	public List<Order_listVO> getNumClass(String class_id);//多少人上課
	
}
