package com.video_record.model;

import java.util.*;

import com.main_class.model.Main_classVO;

public interface Video_recordDAO_interface {
	public void insert(Video_recordVO sub_classVO);

	public void update(Video_recordVO sub_classVO);

	public void delete(String sub_class_ID);

	public Video_recordVO findByPrimaryKey(String sub_class_ID);

	public List<Video_recordVO> getAll();
	
	public List<Video_recordVO> getMemberAll(String member_id);

	public List<Video_recordVO> getAll(Map<String, String[]> map);
}
