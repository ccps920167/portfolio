package com.sell.order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.class_info.model.Class_infoVO;
import com.member_info.model.Member_infoVO;
import com.order_info.model.Order_infoVO;
import com.order_list.model.Order_listVO;

public class SellVO implements Serializable {
	//取得訂單資訊和明細
	private Map <Order_infoVO,List<Order_listVO>> Order_infoVOList;
	//訂單內的課程
	private Set <Class_infoVO> OrderClass_infoVO;
	//會員明細
	private List<Member_infoVO> Member_infoVOList; 
	//課程明細
	private List<Class_infoVO> Class_infoVOList;
	
	public Set<Class_infoVO> getOrderClass_infoVO() {
		return OrderClass_infoVO;
	}
	public void setOrderClass_infoVO(Set<Class_infoVO> orderClass_infoVOList) {
		OrderClass_infoVO = orderClass_infoVOList;
	}
	
	public Map<Order_infoVO, List<Order_listVO>> getOrder_infoVOList() {
		return Order_infoVOList;
	}
	public void setOrder_infoVOList(Map<Order_infoVO, List<Order_listVO>> order_infoVOList) {
		Order_infoVOList = order_infoVOList;
	}
	public List<Member_infoVO> getMember_infoVOList() {
		return Member_infoVOList;
	}
	public void setMember_infoVOList(List<Member_infoVO> member_infoVOList) {
		Member_infoVOList = member_infoVOList;
	}
	public List<Class_infoVO> getClass_infoVOList() {
		return Class_infoVOList;
	}
	public void setClass_infoVOList(List<Class_infoVO> class_infoVOList) {
		Class_infoVOList = class_infoVOList;
	} 
}
