package com.post_message.model;

import java.sql.Timestamp;
import java.util.List;

public class Post_messageService {
	
	private Post_messageDAO_interface dao;
	
	public Post_messageService() {
		dao = new Post_messageJDBCDAO();
	}

	//insert
	public Post_messageVO aaddPost_message(String post_content, java.sql.Timestamp send_time,
			String admin_id, Integer target_type) {
		Post_messageVO post_messageVO = new Post_messageVO();
		
		post_messageVO.setPost_content(post_content);
		post_messageVO.setSend_time(send_time);
		post_messageVO.setAdmin_id(admin_id);
		post_messageVO.setTarget_type(target_type);
		
		dao.insert(post_messageVO);
		
		return post_messageVO;
		
	}
	
   //update
	public Post_messageVO updatePost_message(String post_content,java.sql.Timestamp send_time,
			String admin_id, Integer target_type, String post_id) {
		
		Post_messageVO post_messageVO = new Post_messageVO();
		post_messageVO.setPost_content(post_content);
		post_messageVO.setSend_time(send_time);
		post_messageVO.setTarget_type(target_type);
		post_messageVO.setPost_id(post_id);
		
		dao.update(post_messageVO);
		
		return post_messageVO;
	}
	
	
   //delete
	public void deletepost_message(String post_id) {
		dao.delete(post_id);
	}
	
	public Post_messageVO getOnePost_message(String post_id) {
		return dao.findByPrimaryKey(post_id);
	}
	
	public List<Post_messageVO> getAll(){
		return dao.getAll();
	}

	public Post_messageVO updatePost_message(String post_id, String post_content, Timestamp send_time, String admin_id,
			Integer target_type) {
		
		return null;
	}
}
