package com.class_info.model;

import java.util.*;


public interface Class_info_interface {

	public void insert(Class_infoVO class_infoVO);
    public void update(Class_infoVO class_infoVO);
    public void delete(String class_id);
    public Class_infoVO findByPrimaryKey(String class_id);
    public List<Class_infoVO> getAll();
    public List<Class_infoVO> getTeachAll(String member_id);
	public List<Class_infoVO> get_ROWNUM_8();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<Class_infoVO> getAll(Map<String, String[]> map);
	public byte[] findByPrimaryKey_video(String class_infoVO);
	public byte[] findByPrimaryKey_pic(String class_infoVO);
	public List<Class_infoVO> getTop8();
	public List<Class_infoVO> getFund();
	public List<Class_infoVO> get_ROWNUM_8_Bysub_class(String subclass_id);
	public void updateStatus(Class_infoVO class_infoVO);
	public void updateFund(Class_infoVO class_infoVO);
	public Class_infoVO findClass_id(Class_infoVO class_infoVO);
	//上傳課程封面和募資影片
	public void updateVerify1(Class_infoVO class_infoVO);
	//更改課程狀態至募資中
	
	
}
