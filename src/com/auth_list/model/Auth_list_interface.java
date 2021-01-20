package com.auth_list.model;

import java.util.List;


public interface Auth_list_interface {
	public void insert(Auth_listVO auth_listVO);
    public void update(Auth_listVO auth_listVO);
    public void delete(String auth_listVO);
    public Auth_listVO findByPrimaryKey(String auth_listVO);
    public List<Auth_listVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
