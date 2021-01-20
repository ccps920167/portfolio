package com.allClass_info.model;

public class testJDBC {
	
	public static void main(String[] args) {
		AllClass_info_interface dao = new AllClass_infoJDBCDAO();
		AllClass_infoVO AllClass_infoVO = dao.getInfo("CLA00018");
//		System.out.println(AllClass_infoVO.getChapterAndUnit());
//		System.out.println(AllClass_infoVO.getClass_infoVO());
//		System.out.println(AllClass_infoVO.getHomework());
//		System.out.println(AllClass_infoVO.getMain_class());
//		System.out.println(AllClass_infoVO.getMessageClass());
//		System.out.println(AllClass_infoVO.getMessageLearn());
		System.out.println(AllClass_infoVO.getOrder());
//		System.out.println(AllClass_infoVO.getStudent());
//		System.out.println(AllClass_infoVO.getSub_class());
//		System.out.println(AllClass_infoVO.getTeacher());
//		System.out.println(AllClass_infoVO.getTest());
	}
}
