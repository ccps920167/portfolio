package com.class_info.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Class_infoService {
	private Class_info_interface dao;
	
	public Class_infoService() {
		dao = new Class_infoJDBCDAO();
	}
	
	
	
	
	
	public Class_infoVO addClass_info(
	 String class_name,
	 String member_id,
	 Integer class_status,
	 String subclass_id,
	 Timestamp startfund_date,
	 Timestamp startclass_time,
	 String class_description,
	 byte[] class_picture,
	 Integer startfund_price,
	 Integer original_price,
	 Integer people_threshold,
	 String class_length,
	 byte[] video_fundraising,
	 Timestamp update_time,
	 String admin_id) {
		
		Class_infoVO ciVO = new Class_infoVO();
		ciVO.setClass_name(class_name);
		ciVO.setMember_id(member_id);
		ciVO.setClass_status(class_status);
		ciVO.setSubclass_id(subclass_id);
		ciVO.setStartfund_date(startfund_date);
		ciVO.setStartclass_time(startclass_time);
		ciVO.setClass_description(class_description);
		ciVO.setClass_picture(class_picture);
		ciVO.setStartfund_price(startfund_price);
		ciVO.setOriginal_price(original_price);
		ciVO.setPeople_threshold(people_threshold);
		ciVO.setClass_length(class_length);
		ciVO.setVideo_fundraising(video_fundraising);
		ciVO.setUpdate_time(update_time);
		ciVO.setAdmin_id(admin_id);
		dao.insert(ciVO);
		return ciVO;
		
	}
	
    public List<Class_infoVO> getTop8(){
    	return dao.getTop8();
    }
    
    public List<Class_infoVO> getFund(){
    	return dao.getFund();
    }
	
	
	public Class_infoVO updateClass_info(
			 String class_id,
			 String class_name,
			 String member_id,
			 Integer class_status,
			 String subclass_id,
			 Timestamp startfund_date,
			 Timestamp startclass_time,
			 String class_description,
			 byte[] class_picture,
			 Integer startfund_price,
			 Integer original_price,
			 Integer people_threshold,
			 String class_length,
			 byte[] video_fundraising,
			 Timestamp update_time,
			 String admin_id) {
				
				Class_infoVO ciVO = new Class_infoVO();
				
				ciVO.setClass_id(class_id);
				ciVO.setClass_name(class_name);
				ciVO.setMember_id(member_id);
				ciVO.setClass_status(class_status);
				ciVO.setSubclass_id(subclass_id);
				ciVO.setStartfund_date(startfund_date);
				ciVO.setStartclass_time(startclass_time);
				ciVO.setClass_description(class_description);
				ciVO.setClass_picture(class_picture);
				ciVO.setStartfund_price(startfund_price);
				ciVO.setOriginal_price(original_price);
				ciVO.setPeople_threshold(people_threshold);
				ciVO.setClass_length(class_length);
				ciVO.setVideo_fundraising(video_fundraising);
				ciVO.setUpdate_time(update_time);
				ciVO.setAdmin_id(admin_id);
				
				
				dao.update(ciVO);
				return ciVO;
				
			}	
	
	public void deleteClass_info(String class_id) {
    	dao.delete(class_id);
    }
    
    public Class_infoVO getOneClass_info(String class_id) {
    	return dao.findByPrimaryKey(class_id);
    }
    public List<Class_infoVO> getAll(){
    	return dao.getAll();
    }
	public List<Class_infoVO> get_ROWNUM_8(){
    	return dao.get_ROWNUM_8();
    }
	
	public byte[] getClassPic(String class_id){
    	return dao.findByPrimaryKey_pic(class_id);
    }
	
	public byte[] getClassVideo(String class_id){
    	return dao.findByPrimaryKey_video(class_id);
    }
	
	public List<Class_infoVO> getAll(Map<String,String[]> map){
    	return dao.getAll(map);
    }
	
    public List<Class_infoVO> getTeachAll(String member_id){
    	return dao.getTeachAll(member_id);
    }
    
	public List<Class_infoVO> get_ROWNUM_8_Bysub_class(String subclass_id){
		return dao.get_ROWNUM_8_Bysub_class(subclass_id);
	}
	    
    public void updateStatus(Integer class_status,String admin_id,String class_id) {
    	Class_infoVO ciVO = new Class_infoVO();
    	ciVO.setClass_id(class_id);
    	ciVO.setClass_status(class_status);
    	ciVO.setAdmin_id(admin_id);
    	dao.updateStatus(ciVO);
    }
    
    public void updateFund(
			 byte[] class_picture,
			 byte[] video_fundraising,
			 Timestamp update_time,
			 String class_id
    		) {
    	Class_infoVO ciVO = new Class_infoVO();
    	ciVO.setClass_id(class_id);
		ciVO.setClass_picture(class_picture);
		ciVO.setVideo_fundraising(video_fundraising);
		ciVO.setUpdate_time(update_time);
    	dao.updateFund(ciVO);
    }
    
    public Class_infoVO findClass_id(
    		 String class_name,
    		 String member_id,
    		 String subclass_id) {
    	Class_infoVO ciVO = new Class_infoVO();
    	ciVO.setClass_name(class_name);
		ciVO.setMember_id(member_id);
		ciVO.setSubclass_id(subclass_id);
    	Class_infoVO class_id = dao.findClass_id(ciVO);
    	return class_id;
    }
    
    
    public void updateVerify1(Integer class_status,String admin_id,String class_id
    		,Timestamp startfund_date,Timestamp startclass_time,Timestamp update_time) {
    	Class_infoVO ciVO = new Class_infoVO();
    	ciVO.setClass_id(class_id);
    	ciVO.setClass_status(class_status);
    	ciVO.setAdmin_id(admin_id);
		ciVO.setStartfund_date(startfund_date);
		ciVO.setStartclass_time(startclass_time);
		ciVO.setUpdate_time(update_time);
    	dao.updateVerify1(ciVO);
    	
    }
    
    //抓待審核課程的方法
    public List<Class_infoVO> getAllVerify(){
    	List<Class_infoVO> verifyClass_info = new ArrayList<Class_infoVO>();
    	List<Class_infoVO> verify = (List<Class_infoVO>) dao.getAll();
    	for(Class_infoVO class_info:verify) {
    		if(class_info.getClass_status() == 0) {
    			verifyClass_info.add(class_info);
    		}
    	}
    	return verifyClass_info;
    }
    
  //抓募資中課程的方法
    public List<Class_infoVO> getAllVerifyStatus1(){
    	List<Class_infoVO> verifyClass_info = new ArrayList<Class_infoVO>();
    	List<Class_infoVO> verify = (List<Class_infoVO>) dao.getAll();
    	for(Class_infoVO class_info:verify) {
    		if(class_info.getClass_status() == 1) {
    			verifyClass_info.add(class_info);
    		}
    	}
    	return verifyClass_info;
    }
    
    //抓開課中課程的方法
    public List<Class_infoVO> getAllVerifyStatus4(){
    	List<Class_infoVO> verifyClass_info = new ArrayList<Class_infoVO>();
    	List<Class_infoVO> verify = (List<Class_infoVO>) dao.getAll();
    	for(Class_infoVO class_info:verify) {
    		if(class_info.getClass_status() == 4) {
    			verifyClass_info.add(class_info);
    		}
    	}
    	return verifyClass_info;
    }
    
    //抓下架中課程的方法
    public List<Class_infoVO> getAllVerifyStatus5(){
    	List<Class_infoVO> verifyClass_info = new ArrayList<Class_infoVO>();
    	List<Class_infoVO> verify = (List<Class_infoVO>) dao.getAll();
    	for(Class_infoVO class_info:verify) {
    		if(class_info.getClass_status() == 5) {
    			verifyClass_info.add(class_info);
    		}
    	}
    	return verifyClass_info;
    }
    
    //抓已退件課程的方法
    public List<Class_infoVO> getAllVerifyStatus6(){
    	List<Class_infoVO> verifyClass_info = new ArrayList<Class_infoVO>();
    	List<Class_infoVO> verify = (List<Class_infoVO>) dao.getAll();
    	for(Class_infoVO class_info:verify) {
    		if(class_info.getClass_status() == 6) {
    			verifyClass_info.add(class_info);
    		}
    	}
    	return verifyClass_info;
    }
    
    //抓尚未送出課程的方法
    public List<Class_infoVO> getAllVerifyStatus7(){
    	List<Class_infoVO> verifyClass_info = new ArrayList<Class_infoVO>();
    	List<Class_infoVO> verify = (List<Class_infoVO>) dao.getAll();
    	for(Class_infoVO class_info:verify) {
    		if(class_info.getClass_status() == 7) {
    			verifyClass_info.add(class_info);
    		}
    	}
    	return verifyClass_info;
    }


}
