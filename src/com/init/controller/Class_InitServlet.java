package com.init.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import com.main_class.model.Main_classService;
import com.main_class.model.Main_classVO;
import com.sub_class.model.Sub_classService;
import com.sub_class.model.Sub_classVO;

/**
 * Servlet implementation class Class_infoServlet
 */
@WebServlet(
		urlPatterns = { "/Class_info/Class_InitServlet" }, 
		loadOnStartup = 2)
public class Class_InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Class_InitServlet() {
	}

	@Override
	public void init() throws ServletException {
		// get all data
		List<Main_classVO> Main_class_list = new Main_classService().getMain_classAll();
		List<Sub_classVO> Sub_class_list = new Sub_classService().getAll();
		
		
		// Map1 = String class_id : list Class_chapte
		Map<String, List<Sub_classVO>> MainSubClass = new HashMap<String, List<Sub_classVO>>();
//		MainSubClass = Sub_class_list.stream().collect(Collectors.groupingBy(Sub_classVO::getMainClass_id));
		for (Main_classVO Main_class_item : Main_class_list) { // °j°é¨ú¥XClass_infoVO
			List<Sub_classVO> Sub_class_list_put = new LinkedList<Sub_classVO>();
			for (Sub_classVO Sub_class_item : Sub_class_list) {
				if (Main_class_item.getMainClass_id().equals(Sub_class_item.getMainClass_id())) {
					Sub_class_list_put.add(Sub_class_item);
				}
			}
			MainSubClass.put(Main_class_item.getMainClass_name(),Sub_class_list_put);
		}
		
		ServletContext ServletContext = getServletContext();
		ServletContext.setAttribute("MainSubClass", MainSubClass);
	
	}

	public void destroy() {

	}

}
