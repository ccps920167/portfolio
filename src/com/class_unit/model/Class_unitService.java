package com.class_unit.model;

import java.sql.Timestamp;
import java.util.List;

public class Class_unitService {

	private Class_unitDAO_interface dao;

	public Class_unitService(){
		dao = new Class_unitJDBCDAO();
	}
	
	public Class_unitVO insert(String chapter_id,String unit_name,byte[] video,String video_long,Timestamp video_updatetime,Integer video_status) {
		Class_unitVO vo = new Class_unitVO();
		vo.setChapter_id(chapter_id);
		vo.setUnit_name(unit_name);
		vo.setVideo(video);
		vo.setVideo_long(video_long);
		vo.setVideo_updatetime(video_updatetime);
		vo.setVideo_status(video_status);
		dao.insert(vo);
		return vo;
	}

	public Class_unitVO update(String unit_id,String chapter_id,String unit_name,byte[] video,String video_long,Timestamp video_updatetime,Integer video_status) {
		Class_unitVO vo = new Class_unitVO();
		vo.setUnit_id(unit_id);
		vo.setChapter_id(chapter_id);
		vo.setUnit_name(unit_name);
		vo.setVideo(video);
		vo.setVideo_long(video_long);
		vo.setVideo_updatetime(video_updatetime);
		vo.setVideo_status(video_status);
		dao.update(vo);
		return vo;
	}

	public void delete(String unit_id) {
		dao.delete(unit_id);
	}

	public Class_unitVO select(String unit_id) {
		return dao.findByPrimaryKey(unit_id);
	}

	public List<Class_unitVO> getAll(){
		return dao.getAll();
	}

	public List<Class_unitVO> getChapter_id(String chapter_id) {
		return dao.getChapter_id(chapter_id);
	}

	public List<Class_unitVO> getAllunit_id(String class_id) {
		return dao.getAllunit_id(class_id);
	}
	
	public static void main(String[] args) {
		Class_unitService service = new Class_unitService();
		service.delete("UNI00014");
	}
	
	public void updateVideo(String unit_id, byte[] video,Timestamp video_updatetime) {
		Class_unitVO vo = new Class_unitVO();
		vo.setUnit_id(unit_id);
		vo.setVideo(video);
		vo.setVideo_updatetime(video_updatetime);
		dao.updateVideo(vo);
	}

}
