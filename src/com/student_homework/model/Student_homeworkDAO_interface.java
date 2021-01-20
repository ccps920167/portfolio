package com.student_homework.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface Student_homeworkDAO_interface {
	public void insert(Student_homeworkVO student_homeworkVO);
	public void update(Student_homeworkVO student_homeworkVO);
	public void delete(String studenthw_id);
	public Student_homeworkVO select(String studenthw_id);
	public List<Student_homeworkVO> selectByTrhwId(String teacherhw_id);
	public List<Map<String,Object>> worklist(String class_id);
	public List<Student_homeworkVO> getAll();
	public List<Student_homeworkVO> getAll_orderByUpdate();
	
}
