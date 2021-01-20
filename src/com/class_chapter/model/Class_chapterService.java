package com.class_chapter.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.class_unit.model.Class_unitVO;

public class Class_chapterService {
	private Class_chapterDAO_interface dao;
	public Class_chapterService() {
		dao = new Class_chapterJDBCDAO();
	}
	
	public Class_chapterVO addClass_chapterVO(String class_id,String chapter_name,String unit_name,byte[] video,String video_long,Timestamp video_updatetime,Integer video_status) {
		Class_chapterVO class_chapterVO=new Class_chapterVO();
		Class_unitVO unit=new Class_unitVO();
		List<Class_unitVO> list=new ArrayList<Class_unitVO>();
		class_chapterVO.setChapter_name(chapter_name);
		class_chapterVO.setClass_id(class_id);
		unit.setUnit_name(unit_name);
		unit.setVideo(video);
		unit.setVideo_long(video_long);
		unit.setVideo_status(video_status);
		unit.setVideo_updatetime(video_updatetime);
		list.add(unit);
		dao.inserwithunit(class_chapterVO, list);
		return class_chapterVO;
	}
	public void inserwithunit(Class_chapterVO class_chapterVO, List<Class_unitVO> list) {
		dao.inserwithunit(class_chapterVO, list);
	}
	
	 public List<Class_chapterVO2> getChapterUnit(String class_id){
		  return dao.getChapterUnit(class_id);
		 }
	 public Map<Class_chapterVO, List<Class_unitVO>> getClassChapter(String class_id){
		 return dao.getClassChapter(class_id);
	 }
	
	public Class_chapterVO insert(String class_id,String chapter_name) {
		Class_chapterVO vo = new Class_chapterVO();
		vo.setClass_id(class_id);
		vo.setChapter_name(chapter_name);
		dao.insert(vo);
		return vo;
	}
	public Class_chapterVO update(String chapter_id,String class_id,String chapter_name) {
		Class_chapterVO vo = new Class_chapterVO();
		vo.setChapter_id(chapter_id);
		vo.setClass_id(class_id);
		vo.setChapter_name(chapter_name);
		dao.update(vo);
		return vo;
	}
	public void delete(String chapter_id) {
		dao.delete(chapter_id);
	}
	public Class_chapterVO select(String chapter_id) {
		return dao.select(chapter_id);
	}
	public List<Class_chapterVO> getAll(){
		return dao.getAll();
	}
	public List<Class_chapterVO> getAll(Map<String,String[]> map){
		return dao.getAll(map);
	}
	
	public List<Class_chapterVO> getClassAll(String class_id){
		return dao.getClassAll(class_id);
	}
	
//	public static void main(String[] args) {
//		Class_chapterService service = new Class_chapterService();
//		service.insert("CLA00010","service„insert•¦");
//		System.out.println(service.select("CLC00117").getClass_id());
//		System.out.println(service.select("CLC00117").getChapter_name());
//		System.out.println("==============");
//		service.update("CLC00117","CLA00011","service„upadte•¦");
//		service.select("CLC00117");
//		System.out.println(service.select("CLC00117").getClass_id());
//		System.out.println(service.select("CLC00117").getChapter_name());
//		System.out.println("==============");
//		service.delete("CLC00117");
//		System.out.println("==============");
//		List<Class_chapterVO> test_list = service.getAll();
//		for(Class_chapterVO test_vo: test_list) {
//			System.out.println("class_id:"+test_vo.getChapter_id());
//			System.out.println("class_id:"+test_vo.getClass_id());
//			System.out.println("chapter:"+test_vo.getChapter_name());
//		}
//	}
}
