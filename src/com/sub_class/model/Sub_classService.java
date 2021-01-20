package com.sub_class.model;

import java.util.List;
import java.util.Set;

import com.sub_class.model.Sub_classVO;

public class Sub_classService {

	private Sub_classDAO_interface dao;

	public Sub_classService() {
		dao = new Sub_classJDBCDAO();
	}
	
	public void update(Sub_classVO sub_classVO) {
		dao.update(sub_classVO);
	}
	
	
	public void add(String subClass_name,String subClass_status,String mainClass_id) {
		Sub_classVO sub_classVO = new Sub_classVO();
		sub_classVO.setSubClass_name(subClass_name);
		sub_classVO.setSubClass_status(Integer.parseInt(subClass_status));
		sub_classVO.setMainClass_id(mainClass_id);
		dao.insert(sub_classVO);
	}

	public List<Sub_classVO> getAll() {
		return dao.getAll();
	}

	public Sub_classVO getOneMain_class(String  subClass_id) {
		return dao.findByPrimaryKey(subClass_id);
	}


	public void deleteSub_class(String subClass_id) {
		dao.delete(subClass_id);
	}
}
