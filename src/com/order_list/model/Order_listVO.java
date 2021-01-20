package com.order_list.model;

import java.io.Serializable;

public class Order_listVO implements Serializable {
	private String order_list_id; //Pk
	private String order_id;//FK
	private String class_id;//FK
	private String purchase_plan;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_list_id == null) ? 0 : order_list_id.hashCode());
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
		Order_listVO other = (Order_listVO) obj;
		if (order_list_id == null) {
			if (other.order_list_id != null)
				return false;
		} else if (!order_list_id.equals(other.order_list_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Order_listVO [order_list_id=" + order_list_id + ", order_id=" + order_id + ", class_id=" + class_id
				+ ", purchase_plan=" + purchase_plan + "]";
	}
	public String getOrder_list_id() {
		return order_list_id;
	}
	public void setOrder_list_id(String order_list_id) {
		this.order_list_id = order_list_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getClass_id() {
		return class_id;
	}
	public void setClass_id(String class_id) {
		this.class_id = class_id;
	}
	public String getPurchase_plan() {
		return purchase_plan;
	}
	public void setPurchase_plan(String purchase_plan) {
		this.purchase_plan = purchase_plan;
	}
 
}
