package com.sub_message.model;

import java.sql.Timestamp;
import java.util.List;

public class Sub_messageService {

	private Sub_messageDAO_interface dao;
	
	public Sub_messageService() {
		dao = new Sub_messageJDBCDAO();
	}
	
	
	//insert 新增
	public Sub_messageVO addSub_message(String mainmsg_id, String member_id,
			Timestamp submsg_time, String submsg_text, Integer submsg_status) {
		
		Sub_messageVO sub_messageVO = new Sub_messageVO();
		
		sub_messageVO.setMainmsg_id(mainmsg_id);
		sub_messageVO.setMember_id(member_id);
		sub_messageVO.setSubmsg_time(submsg_time);
		sub_messageVO.setSubmsg_text(submsg_text);
		sub_messageVO.setSubmsg_status(submsg_status);
		dao.insert(sub_messageVO);
		
		return sub_messageVO;
		
	}
	
	//update 更新
	public void updateSub_message(String mainmsg_id, String member_id, java.sql.Timestamp submsg_time,
			String submsg_text, Integer submsg_status, String submsg_id) {

		Sub_messageVO sub_messageVO = new Sub_messageVO();
		
		sub_messageVO.setMainmsg_id(mainmsg_id);
		sub_messageVO.setMember_id(member_id);
		sub_messageVO.setSubmsg_time(submsg_time);
		sub_messageVO.setSubmsg_text(submsg_text);
		sub_messageVO.setSubmsg_status(submsg_status);
		sub_messageVO.setSubmsg_id(submsg_id);

		dao.update(sub_messageVO);
	}
	
	public void deleteSub_message(String submsg_id) {
		dao.delete(submsg_id);
	}
	
	public Sub_messageVO getOneSub_message(String submsg_id) {
		return dao.findByPrimaryKey(submsg_id);
	}
	
	public List<Sub_messageVO> getAll(){
		return dao.getAll();
	}


	public Sub_messageVO addSub_message(String submsg_id, String mainmsg_id, String member_id, String submsg_text,
			Integer submsg_status) {
		
		return null;
	}
}
