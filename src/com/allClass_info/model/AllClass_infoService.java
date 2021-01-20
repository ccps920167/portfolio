package com.allClass_info.model;


public class AllClass_infoService {
	private AllClass_info_interface dao;
	
	public AllClass_infoService() {
		dao = new AllClass_infoJDBCDAO();
	}
	public AllClass_infoVO getAll(String class_id) {
		return dao.getInfo(class_id);
	}
	

}
