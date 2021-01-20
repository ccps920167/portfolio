package com.sell.order;

import java.util.List;
import java.util.Map;

import com.order_list.model.Order_listVO;

public class SellService {
	
	private SellDAO_interface dao;
	
	public SellService() {
		dao=new SellJDBCDAO();
	}
	
	public SellVO getAll(java.sql.Timestamp start_dateTime , java.sql.Timestamp end_dateTime) {
		return dao.getAll(start_dateTime, end_dateTime);
	}
	


}
