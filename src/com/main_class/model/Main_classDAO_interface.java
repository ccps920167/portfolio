package com.main_class.model;

import java.util.*;

import com.sub_class.model.Sub_classVO;

public interface Main_classDAO_interface {
	public void insert(Main_classVO Main_classVO);

	public void update(Main_classVO Main_classVO);

	public void delete(String main_class_id);

	public Main_classVO findByPrimaryKey(String main_class_ID);

	public List<Main_classVO> getAll();

	public Set<Sub_classVO> getSub_classByMainClass_ID(String main_class_ID);
	
	public List<Main_classVO> getAll(Map<String, String[]> map);
}
