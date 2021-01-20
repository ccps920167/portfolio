package com.admin_auth.model;

import java.sql.Timestamp;
import java.util.List;



public class Admin_authService {
private Admin_auth_interface dao;
    
    public Admin_authService() {
    	dao = new Admin_authJDBCDAO();
    }
    
    public Admin_authVO addAdmin_auth(
    		String admin_id,
    		String auth_id,
    		Integer auth_status,
    		Timestamp auth_update) {
    	
    	Admin_authVO aaVO =new Admin_authVO();

    	aaVO.setAdmin_id(admin_id);
    	aaVO.setAuth_id(auth_id);
    	aaVO.setAuth_status(auth_status);
    	aaVO.setAuth_update(auth_update);
    	dao.insert(aaVO);
    	
    	return aaVO;	
    }
    
    public Admin_authVO updateAdmin_auth(
    		String admin_id,
    		String auth_id,
    		Integer auth_status,
    		Timestamp auth_update) {
    
    	Admin_authVO aaVO =new Admin_authVO();
    	
    	aaVO.setAdmin_id(admin_id);
    	aaVO.setAuth_id(auth_id);
    	aaVO.setAuth_status(auth_status);
    	aaVO.setAuth_update(auth_update);
    	dao.update(aaVO);
    	
    	return aaVO;
    }
    
    public void deleteAdmin_auth(String admin_id) {
    	dao.delete(admin_id);
    }
    
    public List<Admin_authVO> getOneAdmin_auth(String admin_id) {
    	return dao.findByPrimaryKey(admin_id);
    }
    public List<Admin_authVO> getAll(){
    	return dao.getAll();
    }

}
