package com.sub_class.model;

import java.util.*;

public interface Sub_classDAO_interface {
	public void insert(Sub_classVO sub_classVO);

	public void update(Sub_classVO sub_classVO);

	public void delete(String sub_class_ID);

	public Sub_classVO findByPrimaryKey(String sub_class_ID);

	public List<Sub_classVO> getAll();

//	public Set<Main_classVO> getSub_classByMain_class_ID(String main_class_ID);
	

}
