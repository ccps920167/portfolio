package com.main_class.model;

import java.util.List;
import java.util.Set;

import com.sub_class.model.Sub_classVO;

public class Main_classService {

	private Main_classDAO_interface dao;

	public Main_classService() {
		dao = new Main_classDAO();
	}
	
	
	public void updateMain_class(String mainClass_name,Integer mainClass_status,String mainClass_id) {
		Main_classVO main_classVo  = new Main_classVO();
		main_classVo.setMainClass_id(mainClass_id);
		main_classVo.setMainClass_name(mainClass_name);
		main_classVo.setMainClass_status(mainClass_status);
		dao.update(main_classVo);
	}
	
	
	public void addMain_class(String mainClass_name,Integer mainClass_status) {
		Main_classVO main_classVo  = new Main_classVO();
		main_classVo.setMainClass_name(mainClass_name);
		main_classVo.setMainClass_status(mainClass_status);
		dao.insert(main_classVo);
	}

	public List<Main_classVO> getMain_classAll() {
		return dao.getAll();
	}

	public Main_classVO getOneMain_class(String  mainClass_id) {
		return dao.findByPrimaryKey(mainClass_id);
	}

	public Set<Sub_classVO> getSub_classByMain_class_id(String mainClass_id) {
		return dao.getSub_classByMainClass_ID(mainClass_id);
	}

	public void deleteMain_class(String mainClass_id) {
		dao.delete(mainClass_id);
	}
}
