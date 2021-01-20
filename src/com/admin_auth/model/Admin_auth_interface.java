package com.admin_auth.model;

import java.sql.Connection;
import java.util.List;

public interface Admin_auth_interface {
	
	public void insert(Admin_authVO admin_authVO);
	public void update(Admin_authVO admin_authVO);
	public void delete(String admin_id);
	public List<Admin_authVO> findByPrimaryKey(String admin_id);
	public List<Admin_authVO> getAll();
	public Admin_authVO findByPK(String admin_id);
	public void addWithAdmin_auth(Admin_authVO admin_authVO, Connection con);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);

}
