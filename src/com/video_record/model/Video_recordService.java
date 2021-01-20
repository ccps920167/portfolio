package com.video_record.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sub_class.model.Sub_classVO;

public class Video_recordService {

	private Video_recordDAO_interface dao;

	public Video_recordService() {
		dao = new Video_recordJDBCDAO();
	}
	
	public void update(Video_recordVO video_recordVO) {
		dao.update(video_recordVO);
	}
	
	public void add(Video_recordVO video_recordVO) {
		dao.insert(video_recordVO);
	}

	public List<Video_recordVO> getAll() {
		return dao.getAll();
	}
	
	public List<Video_recordVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

	public Video_recordVO getOneMain_class(String  record_id) {
		return dao.findByPrimaryKey(record_id);
	}

	public void deleteMain_class(String record_id) {
		dao.delete(record_id);
	}
	
	public List<Video_recordVO> getMemberAll(String member_id) {
		return dao.getMemberAll(member_id);
	}
}
