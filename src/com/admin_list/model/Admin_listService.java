package com.admin_list.model;

import java.util.List;

import com.admin_auth.model.Admin_authVO;

public class Admin_listService {
private Admin_list_interface dao;
    
    public Admin_listService() {
    	dao = new Admin_listJDBCDAO();
    }
    
    public Admin_listVO addAdmin_list(
    		String admin_id,
    		String admin_name,
    		String admin_pwd,
    		Integer admin_status) {
    	
    	Admin_listVO alVO =new Admin_listVO();

    	alVO.setAdmin_id(admin_id);
    	alVO.setAdmin_name(admin_name);
    	alVO.setAdmin_pwd(admin_pwd);
    	alVO.setAdmin_status(admin_status);
    	dao.insert(alVO);
    	
    	return alVO;	
    }
    
    public Admin_listVO updateAdmin_list(
    		String admin_id,
    		String admin_name,
    		String admin_pwd,
    		Integer admin_status) {
    
    	Admin_listVO alVO =new Admin_listVO();
    	
    	alVO.setAdmin_id(admin_id);
    	alVO.setAdmin_name(admin_name);
    	alVO.setAdmin_pwd(admin_pwd);
    	alVO.setAdmin_status(admin_status);
    	dao.update(alVO);
    	
    	return alVO;
    }
    
    public void deleteAdmin_list(String admin_listVO) {
    	dao.delete(admin_listVO);
    }
    
    public Admin_listVO getOneAdmin_list(String admin_listVO) {
    	return dao.findByPrimaryKey(admin_listVO);
    }
    public List<Admin_listVO> getAll(){
    	return dao.getAll();
    }

	public String addWithAdmin_list(Admin_listVO admin_listVO, List<Admin_authVO> Admin_authVO) {
		return dao.addWithAdmin_list(admin_listVO, Admin_authVO);
	}
}
