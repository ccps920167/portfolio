package com.video_record.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.video_record.model.Video_recordService;
import com.video_record.model.Video_recordVO;

@WebServlet("/Video_record/Video_recordServlet")
public class Video_recordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// ���o�ӷ��Ѽ�
		String action = req.getParameter("action");
		if("getNewTime".equals(action)) {
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z***********************/
			String record_id = req.getParameter("record_id");
			String member_id =req.getParameter("member_id");
			String unit_id = req.getParameter("unit_id");
			String class_last = req.getParameter("class_last");
			//�p�G���s�b��� ��s 
			if(record_id.length()==0) {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z***********************/
				Video_recordVO video_recordVO = new Video_recordVO();
				video_recordVO.setMember_id(member_id);
				video_recordVO.setunit_id(unit_id);
				video_recordVO.setClass_last(class_last);
				video_recordVO.setVideo_updateTime(new java.sql.Timestamp(new java.util.Date().getTime()));
				/*********************** 2.�}�l�s�W���***********************/
				Video_recordService Srv = new Video_recordService();
				Srv.add(video_recordVO);
			}else {//�p�G�w�s�b��� ��s
				Video_recordVO video_recordVO = new Video_recordVO();
				video_recordVO.setRecord_id(record_id);
				video_recordVO.setMember_id(member_id);
				video_recordVO.setunit_id(unit_id);
				video_recordVO.setClass_last(class_last);
			/*********************** 2.�}�l�s�W���***********************/
			video_recordVO.setVideo_updateTime(new java.sql.Timestamp(new java.util.Date().getTime()));
			Video_recordService Srv = new Video_recordService();
			Srv.update(video_recordVO);
			}
		}
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// ���o�ӷ��Ѽ�
		String action = req.getParameter("action");

		/*�s���d�ߥ���*/
		if("getALL".equals(action)) {
			/*********************** 3.�s�W����,�ǳ����***********************/
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/listVideo_record.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
		}
		/*�s���s�W���*/
		if("addnew".equals(action)) {
			/*********************** 3.�s�W����,�ǳ����***********************/
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/addVideo_record.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
		}
		
		
		
		/*��@�d��*/
		if("listOneVideo_record".equals(action)) {
			/***********************0.�]�w���~�T���e��***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z***********************/
			Map<String,String[]> map = req.getParameterMap();
			/*********************** 2.�}�l�s�W���***********************/
			Video_recordService Srv = new Video_recordService();
			List <Video_recordVO> video_recordVO_list = (List<Video_recordVO>)Srv.getAll(map);
			/*********************** 3.�s�W����,�ǳ����***********************/
			req.setAttribute("video_recordVO_list", video_recordVO_list);
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/listOneVideo_record.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			/*********************** ��L�i�઺���~�B�z***********************/
			/*************************** ��� ***********************/
			
		}
		
		
		/*�s���ק�*/
		if("update_record_id".equals(action)) {
			/***********************0.�]�w���~�T���e��***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z***********************/
			String record_id = req.getParameter("record_id");
			/*********************** 2.�}�l�s�W���***********************/
			Video_recordService Srv = new Video_recordService();
			Video_recordVO video_recordVO = Srv.getOneMain_class(record_id);
			/*********************** 3.�s�W����,�ǳ����***********************/
			req.setAttribute("video_recordVO", video_recordVO);
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/update_Video_record_input.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			/*********************** ��L�i�઺���~�B�z***********************/
			/*************************** ��� ***********************/
			
		}
		
		/*�ק�*/
		if("update".equals(action)) {
			/***********************0.�]�w���~�T���e��***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z***********************/
			String record_id = req.getParameter("record_id");
			String member_id = req.getParameter("member_id");
			String unit_id = req.getParameter("unit_id");
			String class_last = req.getParameter("class_last");
			if(class_last==null || class_last.trim().length()==0) {
				errorMsgs.add("�ФŪť�");
			}
			Video_recordVO video_recordVO = new Video_recordVO();
			video_recordVO.setRecord_id(record_id);
			video_recordVO.setMember_id(member_id);
			video_recordVO.setunit_id(unit_id);
			video_recordVO.setClass_last(class_last);
			if(!errorMsgs.isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					long date_long = ((java.util.Date) dateFormat.parse(req.getParameter("video_updateTime"))).getTime();
					video_recordVO.setVideo_updateTime(new java.sql.Timestamp(date_long));
					req.setAttribute("video_recordVO", video_recordVO);
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/update_Video_record_input.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			/*********************** 2.�}�l�s�W���***********************/
			video_recordVO.setVideo_updateTime(new java.sql.Timestamp(new java.util.Date().getTime()));
			Video_recordService Srv = new Video_recordService();
			Srv.update(video_recordVO);
			/*********************** 3.�s�W����,�ǳ����***********************/
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/listVideo_record.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			/*********************** ��L�i�઺���~�B�z***********************/
			/*************************** ��� ***********************/
			
		}
		
		
		/*�s�W*/
		if("insert".equals(action)) {
			/***********************0.�]�w���~�T���e��***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z***********************/
			String member_id =req.getParameter("member_id");
//			String member_regex =  "^[M]{1}[E]{1}[M]{1}(0-9){5}$";
			if(member_id == null || member_id.trim().length()==0) {
				errorMsgs.add("�|���s���ФŪť�");
			}
//			else if(!member_id.matches(member_regex)) {
//				errorMsgs.add("�|���s���榡���~");
//			}
			String unit_id = req.getParameter("unit_id");
//			String unit_regex = "^[U]{1}[N]{1}[I]{1}(0-9){5}$";
			if(unit_id == null || unit_id.trim().length()==0) {
				errorMsgs.add("�ҵ{�椸�ФŪť�");
			}
//			else if(!unit_id.matches(unit_regex)) {
//				errorMsgs.add("�ҵ{�椸�榡���~");
//			}
			String class_last = req.getParameter("class_last");
			
			Video_recordVO video_recordVO = new Video_recordVO();
			video_recordVO.setMember_id(member_id);
			video_recordVO.setunit_id(unit_id);
			video_recordVO.setClass_last(class_last);
			video_recordVO.setVideo_updateTime(new java.sql.Timestamp(new java.util.Date().getTime()));
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("video_recordVO", video_recordVO);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/addVideo_record.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				return;
			}
			
			/*********************** 2.�}�l�s�W���***********************/
			Video_recordService Srv = new Video_recordService();
			Srv.add(video_recordVO);
			/*********************** 3.�s�W����,�ǳ����***********************/
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/listVideo_record.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			/*********************** ��L�i�઺���~�B�z***********************/
			/*************************** ��� ***********************/
			
		}
		
		
		
		
		
		/*�R��*/
		if("delete".equals(action)) {
			/***********************0.�]�w���~�T���e��***********************/
			//�p�G�����p
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z***********************/
			String record_id = req.getParameter("record_id");
			//���|���ݭn���~�B�z
			/*********************** 2.�}�l�s�W���***********************/
			try{
			Video_recordService video_recordService = new Video_recordService();
			video_recordService.deleteMain_class(record_id);
			/*********************** 3.�s�W����,�ǳ����***********************/
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/listVideo_record.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
			
			}catch (RuntimeException e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/listVideo_record.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
			}catch(Exception ex){
				errorMsgs.add(ex.getMessage());
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/video_record/listVideo_record.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
			}
			/*********************** ��L�i�઺���~�B�z***********************/
			/*************************** ��� ***********************/
			
		}
		
		
	}

}
