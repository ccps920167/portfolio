package com.view_class_income.model;

import java.util.*;
public interface View_class_incomeDAO_interface {
	//後臺用
	public List<View_class_incomeVO> getAll();
	//老師用
	public List<View_class_incomeVO> getMemberAll(String member_id);

}
