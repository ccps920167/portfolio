package com.member_interest.model;

import java.util.List;
import java.util.Set;


public interface Member_interestDAO_interface {
	
	 public void insert(Member_interestVO member_interestVO);
	 public void update(Member_interestVO member_interestVO);
	 public void delete(String interest_id);	 
     public Member_interestVO findByPrimaryKey(String insterest_id);
     public List<Member_interestVO> getAll();   
     public List<Member_interestVO> findBymember_id(String member_id);  
     public void deletebySubclass(String member_id,String subclass_id);
  	
}
