package com.post_message.model;

import java.util.List;

import com.post_message.model.Post_messageVO;

public interface Post_messageDAO_interface {
	public void insert(Post_messageVO Post_messageVO);
    public void update(Post_messageVO Post_messageVO);
    public void delete(String postmsg_id);
    public Post_messageVO findByPrimaryKey(String postmsg_id);
    public List<Post_messageVO> getAll();
	

}
