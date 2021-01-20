package com.sub_message.model;


import java.util.List;

public interface Sub_messageDAO_interface {
		public void insert(Sub_messageVO sub_messageVO) ;
	    public void update(Sub_messageVO sub_messageVO);
	    public void delete(String sub_messageVO);
	    public Sub_messageVO findByPrimaryKey(String submsg_id) ;
	    public List<Sub_messageVO> getAll();

	}

