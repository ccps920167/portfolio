package com.init.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.class_chapter.model.Class_chapterService;
import com.class_chapter.model.Class_chapterVO;
import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.class_unit.model.Class_unitService;
import com.class_unit.model.Class_unitVO;

/**
 * Servlet implementation class Class_infoServlet
 */
@WebServlet(
		urlPatterns = { "/Class_info/Class_InfoInitServlet" }, 
		loadOnStartup = 1)
public class Class_InfoInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Class_InfoInitServlet() {
	}

	@Override
	public void init() throws ServletException {
		// get all data
		List<Class_chapterVO> Class_chapter_list = new Class_chapterService().getAll();
		List<Class_unitVO> Class_unit_list = new Class_unitService().getAll();
		
		// Map1 = String class_id : list Class_chapte
		Map<String, List<Class_chapterVO>> initClass = new HashMap<String, List<Class_chapterVO>>();
		initClass =Class_chapter_list.stream().collect(Collectors.groupingBy(Class_chapterVO::getClass_id));
							//		for (Class_infoVO Class_info_item : Class_info_list) { // 迴圈取出Class_infoVO
							//			List<Class_chapterVO> Class_chapter_list_put = new LinkedList<Class_chapterVO>();
							//			for (Class_chapterVO Class_chapter_item : Class_chapter_list) {
							//				if (Class_info_item.getClass_id().equals(Class_chapter_item.getClass_id())) {
							//					Class_chapter_list_put.add(Class_chapter_item);
							//				}
							//			}
							//			initClass.put(Class_info_item.getClass_id(),Class_chapter_list_put);
							//		}
		// Map2 = String Class_chapte : list Class_unit
		Map<String, List<Class_unitVO>> initChapter = new HashMap<String, List<Class_unitVO>>();
		initChapter = Class_unit_list.stream().collect(Collectors.groupingBy(Class_unitVO::getChapter_id));
		
							//		for (Class_chapterVO Class_chapter_item : Class_chapter_list) { // 迴圈取出Class_infoVO
							//			List<Class_unitVO> Class_unit_list_put = new LinkedList<Class_unitVO>();
							//			for (Class_unitVO Class_unit_item : Class_unit_list) {
							//				if (Class_unit_item.getChapter_id().equals(Class_chapter_item.getChapter_id())) {
							//					Class_unit_list_put.add(Class_unit_item);
							//				}
							//			}
							//			initChapter.put(Class_chapter_item.getChapter_id(),Class_unit_list_put);
							//		}
		ServletContext ServletContext = getServletContext();
		ServletContext.setAttribute("initClass", initClass);
		ServletContext.setAttribute("initChapter", initChapter);		
	}

	public void destroy() {

	}

}
