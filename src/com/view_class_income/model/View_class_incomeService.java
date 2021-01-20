package com.view_class_income.model;

import java.util.List;
public class View_class_incomeService {

	private View_class_incomeDAO_interface dao;

	public View_class_incomeService() {
		dao = new View_class_incomeJDBCDAO();
	}

	public List<View_class_incomeVO> getMemberAll(String member_id) {
		return dao.getMemberAll(member_id);
	}

	public List<View_class_incomeVO> getAll() {
		return dao.getAll();
	}

}
