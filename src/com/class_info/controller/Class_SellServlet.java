package com.class_info.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.class_chapter.model.Class_chapterService;
import com.class_chapter.model.Class_chapterVO;
import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.class_unit.model.Class_unitService;
import com.class_unit.model.Class_unitVO;


/**
 * Servlet implementation class Class_infoServlet
 */
@WebServlet("/Class_info/Class_SellServlet")
public class Class_SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Class_SellServlet() {
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//銷售頁面 募資倒數/已開課
		if ("sell".equals(action)) {
			Class_infoVO class_infoVO = (Class_infoVO)req.getAttribute("class_infoVO");
			if (class_infoVO != null) { // 判斷檔案是否存在
				int date =class_infoVO.getStartfund_date().compareTo(new java.sql.Timestamp( new java.util.Date().getTime()+30*24*60*60*1000));
				if(date == -1 || date == 0) {
					//
					req.setAttribute("class_infoVO", class_infoVO);
					System.out.println(class_infoVO.getClass_id());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_info/classOnly_time.jsp");  //classOnly.jsp classlearn.jsp
					failureView.forward(req, res);
					return;
				}else {
					req.setAttribute("class_infoVO", class_infoVO);
					System.out.println(class_infoVO.getClass_id());
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_info/classOnly.jsp");  //classOnly.jsp classlearn.jsp
					failureView.forward(req, res);
					return;
				}
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

}
