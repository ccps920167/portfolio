package com.student_homework.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.class_chapter.model.Class_chapterService;
import com.class_chapter.model.Class_chapterVO;

public class Student_homeworkService {
	private Student_homeworkDAO_interface dao;
	public Student_homeworkService() {
		dao = new Student_homeworkJDBCDAO();
	}
	
	public Student_homeworkVO insert(String teacherhw_id,String member_id,String hw_name,String hw_content,byte[] file_data) {
		Student_homeworkVO vo = new Student_homeworkVO();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		vo.setTeacherhw_id(teacherhw_id);
		vo.setMember_id(member_id);
		vo.setHw_name(hw_name);
		vo.setHw_content(hw_content);
		vo.setFile_data(file_data);
		vo.setHw_uploadtime(Timestamp.valueOf(dateFormat.format(System.currentTimeMillis())));
		vo.setHw_updatetime(new Timestamp(System.currentTimeMillis()));
		dao.insert(vo);
		return vo;
	}
	public Student_homeworkVO update(String studenthw_id,String teacherhw_id,String member_id,String hw_name,String hw_content,byte[] file_data,Timestamp hw_uploadtime) {
		Student_homeworkVO vo = new Student_homeworkVO();
		vo.setStudenthw_id(studenthw_id);
		vo.setTeacherhw_id(teacherhw_id);
		vo.setMember_id(member_id);
		vo.setHw_name(hw_name);
		vo.setHw_content(hw_content);
		vo.setFile_data(file_data);
		vo.setHw_uploadtime(hw_uploadtime);
		vo.setHw_updatetime(new Timestamp(System.currentTimeMillis()));
		dao.update(vo);
		return vo;
	}
	public void delete(String studenthw_id) {
		dao.delete(studenthw_id);
	}
	public Student_homeworkVO select(String studenthw_id) {
		return dao.select(studenthw_id);
	}
	public List<Student_homeworkVO> selectByTrhwId(String teacherhw_id){
		return dao.selectByTrhwId(teacherhw_id);
	}
	public List<Map<String,Object>> worklist(String class_id){
		return dao.worklist(class_id);
	}
	public List<Student_homeworkVO> getAll(){
		return dao.getAll();
	}
	public List<Student_homeworkVO> getAll_orderByUpdate(){
		return dao.getAll_orderByUpdate();
	}
	public static void main(String[] args) {
		Student_homeworkService service = new Student_homeworkService();
		System.out.println(service.getAll_orderByUpdate());
		service.select("SH00003");
	}
}
