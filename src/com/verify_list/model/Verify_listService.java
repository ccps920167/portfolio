package com.verify_list.model;

import java.sql.Timestamp;
import java.util.List;

public class Verify_listService {
    private Verify_list_interface dao;
    
    public Verify_listService() {
    	dao = new Verify_listJDBCDAO();
    }
    
    public Verify_listVO addVerify_list(
	 String class_id,
	 String admin_id,
	 String class_check,
	 Timestamp upload_time) {
    	
    	Verify_listVO vlVO =new Verify_listVO();

    	vlVO.setClass_id(class_id);
    	vlVO.setAdmin_id(admin_id);
    	vlVO.setClass_check(class_check);
    	vlVO.setUpload_time(upload_time);
    	dao.insert(vlVO);
    	
    	return vlVO;	
    }
    
    public Verify_listVO updateVerify_list(String class_id, 
    		String admin_id, 
    		String class_check, 
    		Timestamp upload_time ,
    		String verify_id) {
    
    	Verify_listVO vlVO =new Verify_listVO();
    	
    	vlVO.setClass_id(class_id);
    	vlVO.setAdmin_id(admin_id);
    	vlVO.setClass_check(class_check);
    	vlVO.setUpload_time(upload_time);
    	vlVO.setVerify_id(verify_id);
    	dao.update(vlVO);
    	
    	return vlVO;
    }
    
    public void deleteVerify_list(String verify_id) {
    	dao.delete(verify_id);
    }
    
    public Verify_listVO getOneVerify_list(String verify_id) {
    	return dao.findByPrimaryKey(verify_id);
    }
    public List<Verify_listVO> getAll(){
    	return dao.getAll();
    }
    
    public Verify_listVO getClassCheck(String class_id) {
    	return dao.getClassCheck(class_id);
    }
    
}
