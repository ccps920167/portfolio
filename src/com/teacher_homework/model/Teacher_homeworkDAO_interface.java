package com.teacher_homework.model;

import java.util.List;
import java.util.Map;

public interface Teacher_homeworkDAO_interface {
	public void insert(Teacher_homeworkVO teacher_homeworkVO);
	public void update(Teacher_homeworkVO teacher_homeworkVO);
	public void delete(String teacherhw_id);
	public Teacher_homeworkVO select(String teacherhw_id);
	public List<Teacher_homeworkVO> getAll();
	public List<StuTr_homeworkVO>getHwContent(String member_id);
	public List<Map> selectByClassId(String class_id);
	
}
