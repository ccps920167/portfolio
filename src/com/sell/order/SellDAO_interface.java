package com.sell.order;

import java.util.List;
import java.util.Map;

import com.order_info.model.Order_infoVO;

public interface SellDAO_interface {
	
	public SellVO getAll(java.sql.Timestamp start_dateTime , java.sql.Timestamp end_dateTime);
	
}
