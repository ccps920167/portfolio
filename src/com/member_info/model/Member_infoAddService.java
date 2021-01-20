package com.member_info.model;

import java.util.List;

public class Member_infoAddService {

	private Member_infoDAO_interface dao;

	public Member_infoAddService() {

		dao = new Member_infoJDBCDAO();
	}
	

	public Member_infoVO add(Member_infoVO member_infoVO) {
		dao.insert(member_infoVO);
		return member_infoVO;
	}



}
