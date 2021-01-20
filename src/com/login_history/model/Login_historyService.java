package com.login_history.model;

import java.util.List;
//import java.util.Set;

public class Login_historyService {
	private Login_historyDAO_interface dao;

	public Login_historyService() {
		dao = new Login_historyJDBCDAO();
	}
	
	public Login_historyVO update(String login_id,java.sql.Timestamp login_time,String login_arrange,String login_ip,String member_id) {
		Login_historyVO login_historyVO =new Login_historyVO();
		
		login_historyVO.setLogin_id(login_id);
		login_historyVO.setLogin_time(login_time);
		login_historyVO.setLogin_arrange(login_arrange);
		login_historyVO.setLogin_ip(login_ip);
		login_historyVO.setMember_id(member_id);
		
		dao.update(login_historyVO);
		return login_historyVO;
	}
	
	
	public Login_historyVO add(java.sql.Timestamp login_time,String login_arrange,String login_ip,String member_id) {
		
		Login_historyVO login_historyVO =new Login_historyVO();
		login_historyVO.setLogin_time(login_time);
		login_historyVO.setLogin_arrange(login_arrange);
		login_historyVO.setLogin_ip(login_ip);
		login_historyVO.setMember_id(member_id);
		dao.insert(login_historyVO);
		
		return login_historyVO;
	}
	
	public void delete(String login_id) {
		dao.delete(login_id);
	}

	public List<Login_historyVO> getAll() {
		return dao.getAll();
	}

	public Login_historyVO getOneLogin_history(String login_id) {
		return dao.findByPrimaryKey(login_id);
	}

//	public Set<Sub_classVO> getSub_classByMain_class_id(String main_class_id) {
//		return dao.getSub_classByMain_class_ID(main_class_id);
//	}
//
//	public void deleteMain_class(String login_id) {
//		dao.delete(login_id);
//	}
	
	
	
	

}
