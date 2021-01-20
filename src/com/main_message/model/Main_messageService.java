package com.main_message.model;

import java.sql.Timestamp;
import java.util.List;

public class Main_messageService {
	
	private Main_messageDAO_interface dao ;
	
	
	public Main_messageService(){
		dao = new Main_messageJDBCDAO();
	}
	
//insert
	
	public Main_messageVO addMain_message(Main_messageVO main_messagevo) {
		dao.insert(main_messagevo);
		return main_messagevo;
	}
	
	
	public Main_messageVO addMain_message(String class_id, String member_id, Timestamp mainmsg_time,String mainmsg_text, String msg_source, Integer msg_status) {
		Main_messageVO main_messagevo = new Main_messageVO();
		
		main_messagevo.setClass_id(class_id);
		main_messagevo.setMember_id(member_id);
		main_messagevo.setMainmsg_time(mainmsg_time);
		main_messagevo.setMainmsg_text(mainmsg_text);
		main_messagevo.setMsg_source(msg_source);
		main_messagevo.setMsg_status(msg_status);
		dao.insert(main_messagevo);
		
		return main_messagevo;
	}
	
	
//update

	public Main_messageVO updateMain_message(String class_id,String member_id,java.sql.Timestamp mainmsg_time,
			String mainmsg_text,String msg_source,Integer msg_status,String mainmsg_id) {
		
		Main_messageVO main_messageVO = new Main_messageVO();
		main_messageVO.setClass_id(class_id);
		main_messageVO.setMember_id(member_id);
		main_messageVO.setMainmsg_time(mainmsg_time);
		main_messageVO.setMainmsg_text(mainmsg_text);
		main_messageVO.setMsg_source(msg_source);
		main_messageVO.setMsg_status(msg_status);
		main_messageVO.setMainmsg_id(mainmsg_id);
		dao.update(main_messageVO);
		return main_messageVO;
		
	}
	
	
//delete
	
	public void deletemain_message(String mainmsg_id) {
		dao.delete(mainmsg_id);
	}
	
	public Main_messageVO getOnemain_message(String mainmsg_id) {
		return dao.findByPrimaryKey(mainmsg_id);
	}
	
	public List<Main_messageVO> getAll() {
		return dao.getAll();
	}




}
