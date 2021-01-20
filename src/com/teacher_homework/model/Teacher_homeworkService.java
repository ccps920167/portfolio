package com.teacher_homework.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class Teacher_homeworkService {
	private Teacher_homeworkDAO_interface dao;
	public Teacher_homeworkService() {
		dao = new Teacher_homeworkJDBCDAO();
	}
	
	public List<StuTr_homeworkVO> getHwContent(String member_id){
		return dao.getHwContent(member_id);
	}
	
	
	public Teacher_homeworkVO insert(String unit_id,String hw_name,String hw_content,byte[] file_data) {
		Teacher_homeworkVO vo = new Teacher_homeworkVO();
		Timestamp hw_uploadtime = new Timestamp(System.currentTimeMillis());
		Timestamp hw_updatetime = new Timestamp(System.currentTimeMillis());
		vo.setUnit_id(unit_id);
		vo.setHw_name(hw_name);
		vo.setHw_content(hw_content);
		vo.setFile_data(file_data);
		vo.setHw_uploadtime(hw_uploadtime);
		vo.setHw_updatetime(hw_updatetime);
		dao.insert(vo);
		return vo;
	}
	
	public Teacher_homeworkVO update(String teacherhw_id,String unit_id,String hw_name,String hw_content,byte[] file_data,Timestamp hw_uploadtime) {
		Teacher_homeworkVO vo = new Teacher_homeworkVO();
		Timestamp hw_updatetime = new Timestamp(System.currentTimeMillis());
		vo.setTeacherhw_id(teacherhw_id);
		vo.setUnit_id(unit_id);
		vo.setHw_name(hw_name);
		vo.setHw_content(hw_content);
		vo.setFile_data(file_data);
		vo.setHw_uploadtime(hw_uploadtime);
		vo.setHw_updatetime(hw_updatetime);
		dao.update(vo);
		return vo;
	}
	
	public void delete(String teacherhw_id) {
		dao.delete(teacherhw_id);
	}
	
	public Teacher_homeworkVO select(String teacherhw_id) {
		return dao.select(teacherhw_id);
	}
	
	public List<Teacher_homeworkVO> getAll(){
		return dao.getAll();
	}
	
	public List<Map> selectByClassId(String class_id){
		return dao.selectByClassId(class_id);
	}
}
