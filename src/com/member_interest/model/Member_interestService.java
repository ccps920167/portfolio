package com.member_interest.model;

import java.util.List;

import com.member_interest.model.Member_interestDAOJDBC;
import com.member_interest.model.Member_interestVO;

public class Member_interestService {
	private Member_interestDAO_interface dao;

	public Member_interestService() {
		dao = new Member_interestDAOJDBC();
	}
	
	public void deletebySubclass(String member_id,String subclass_id) {
		dao.deletebySubclass(member_id,subclass_id);
	}

	
	
	public Member_interestVO addInterest_form(String member_id, String subclass_id, Integer interest_status) {

		Member_interestVO member_interestVO = new Member_interestVO();
		member_interestVO.setMember_id(member_id);
		member_interestVO.setSubclass_id(subclass_id);
		member_interestVO.setInterest_status(interest_status);
		dao.insert(member_interestVO);

		return member_interestVO;
	}

//預留給 Struts 2 用的
	public void addInterest_form(Member_interestVO member_interestVO) {
		dao.insert(member_interestVO);
	}

	public Member_interestVO updateInterest_form(String interest_id, String member_id, String subclass_id,
			Integer interest_status) {

		Member_interestVO member_interestVO = new Member_interestVO();

		member_interestVO.setInterest_id(interest_id);
		member_interestVO.setMember_id(member_id);
		member_interestVO.setSubclass_id(subclass_id);
		member_interestVO.setInterest_status(interest_status);
		dao.update(member_interestVO);

		return dao.findByPrimaryKey(interest_id);
	}

//預留給 Struts 2 用的
	public void updateInterest_form(Member_interestVO member_interestVO) {
		dao.update(member_interestVO);
	}

	public void deleteInterest_form(String interest_id) {
		dao.delete(interest_id);
	}

	public Member_interestVO findByPrimaryKey(String interest_id) {
		return dao.findByPrimaryKey(interest_id);
	}

	public List<Member_interestVO> getAll() {
		return dao.getAll();
	}
	public List<Member_interestVO> findBymember_id(String member_id) {	
		return dao.findBymember_id(member_id);
	}

}
