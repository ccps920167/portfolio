package com.class_chapter.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.class_chapter.model.Class_chapterService;
import com.class_chapter.model.Class_chapterVO;
import com.sub_class.model.Sub_classVO;

@WebServlet("/Class_chapter/Class_chapterServlet")
public class Class_chapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// ���o�ӷ��Ѽ�
		String action = req.getParameter("action");
		
		
		/******************************** ��}�d���� ********************************/
		if("getALL".equals(action)) {
			/*************************** ��� ***********************/
			String url = "/back-end/class_chapter/listClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	
		/******************************** ��}�s�W��� ********************************/
		if("addnew".equals(action)) {
			/*************************** ��� ***********************/
			String url = "/back-end/class_chapter/addClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/******************************** �d�ߤ@����� ********************************/
		if ("listOneClass_chapter".equals(action)) { // �Ӧ�listOneClass_chapter.jsp���ШD

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			// �إ߮e���˿��~���+��J�ݩʤ�
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// �榡�P�_

			String chapter_name = req.getParameter("chapter_name");
			String class_id = req.getParameter("class_id");
			String chapter_id = req.getParameter("chapter_id");

			if (chapter_id == null || chapter_id.length() == 0) {
				if (chapter_name == null || chapter_name.length() == 0) {
					if (class_id == null || class_id.length() == 0) {
						errorMsgs.add("�i�D�ҵ{���O�s���j�B�i�D���O�ҵ{�W�١j�Ρi�D���O���A�j�����ܤ@��g");
					}
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Class_chapter/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.�}�l�d�߸�� ***************************************/
			Class_chapterService Class_chapterSrv = new Class_chapterService();
			// ��Ʈw���X�����,�s�J����
			Class_chapterVO class_chapterVO = Class_chapterSrv.select(chapter_id);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			// ����s�J�ݩ�
			req.setAttribute("class_chapterVO", class_chapterVO);
			// ���
			String url = "/back-end/class_chapter/listOneClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z **********************************/

		}
		/******************************** �s�W�@����� ********************************/
		if ("insert_all".equals(action)) { // �Ӧ�addClass_chapter.jsp���ШD
			// �˿��~���
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			String chapter_name = req.getParameter("chapter_name");
			String class_id = req.getParameter("class_id");
			// chapter_name�P�_�O�_����
			String chapter_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
			if (chapter_name == null || chapter_name.trim().isEmpty()) {
				errorMsgs.add("���`�W�٤��i�H�ť�");
			}else if(!chapter_name.matches(chapter_nameReg)) {
				errorMsgs.add("���`�W�٥����O����έ^��A�å�������2��30�r����");
			}
			
			if (class_id == null || class_id.trim().isEmpty()) {
				errorMsgs.add("�ҵ{���i�H�ť�");
			}

			// �w�g�s�b����Ʀs�J����
			Class_chapterVO class_chapterVO = new Class_chapterVO();
			class_chapterVO.setChapter_name(chapter_name);
			class_chapterVO.setClass_id(class_id);

			// �����~�T�����^�h
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("class_chapterVO", class_chapterVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_chapter/addClass_chapter.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�s�W��� ***************************************/
			Class_chapterService service = new Class_chapterService();
			service.insert(class_id, chapter_name);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			// ���
			String url = "/back-end/class_chapter/listClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z **********************************/

		}
		/******************************** �s�W�@����� ********************************/
		if ("insert".equals(action)) { // �Ӧ�addClass_chapter.jsp���ШD
			// �˿��~���
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			String chapter_name = req.getParameter("chapter_name");
			String class_id = req.getParameter("class_id");
			// chapter_name�P�_�O�_����
			if (chapter_name == null || chapter_name.trim().isEmpty()) {
				errorMsgs.add("�D���O���i�H�ť�");
			}

			// �w�g�s�b����Ʀs�J����
			Class_chapterVO class_chapterVO = new Class_chapterVO();
			class_chapterVO.setChapter_name(chapter_name);
			class_chapterVO.setClass_id(class_id);

			// �����~�T�����^�h
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("class_chapterVO", class_chapterVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Class_chapter/addClass_chapter.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�s�W��� ***************************************/
			Class_chapterService service = new Class_chapterService();
			service.insert(class_id, chapter_name);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			// ���
			String url = "/back-end/class_chapter/listClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z **********************************/

		}

		/******************************** �s�W�@����� ********************************/
		if ("delete".equals(action)) {
			// �˿��~���
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
//			�����@���Ѽ� �R�����Ǹ�
				String chapter_id = req.getParameter("chapter_id");
				/*************************** 2.�}�l�s�W��� ***************************************/
				Class_chapterService Class_chapterSrv = new Class_chapterService();
				Class_chapterSrv.delete(chapter_id);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				// ���
				String url = "/back-end/class_chapter/listClass_chapter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {

				/*************************** ��L�i�઺���~�B�z **********************************/
				errorMsgs.add("�ӵ���Ƥw�g���p�A�ФŧR��");
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				// ���
				String url = "/back-end/Class_chapter/listClass_chapter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}
		}
		if ("update_chapter_id".equals(action)) {
			/*********************** 0.�]�w���~�T���e�� ***********************/
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
			String chapter_id = req.getParameter("chapter_id");
			/*********************** 2.�}�l�s�W��� ***********************/
			Class_chapterService Srv = new Class_chapterService();
			Class_chapterVO class_chapterVO = Srv.select(chapter_id);
			req.setAttribute("class_chapterVO", class_chapterVO);
			/*********************** 3.�s�W����,�ǳ���� ***********************/
			// ���
			String url = "/back-end/class_chapter/update_Class_chapter_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** ��L�i�઺���~�B�z ***********************/
			/*************************** ��� ***********************/

		}

		if ("update".equals(action)) {
			/*********************** 0.�]�w���~�T���e�� ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
			String chapter_id = req.getParameter("chapter_id");
			String chapter_name = req.getParameter("chapter_name");
			String class_id = req.getParameter("class_id");

			String chapter_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
			if (chapter_name == null || chapter_name.trim().isEmpty()) {
				errorMsgs.add("���`�W�٤��i�H�ť�");
			}else if(!chapter_name.matches(chapter_nameReg)) {
				errorMsgs.add("���`�W�٥����O����έ^��A�å�������2��30�r����");
			}
			
			if (class_id == null || class_id.trim().isEmpty()) {
				errorMsgs.add("�ҵ{���i�H�ť�");
			}

			if (!errorMsgs.isEmpty()) {
				Class_chapterVO class_chapterVO = new Class_chapterVO();
				class_chapterVO.setChapter_id(chapter_id);
				class_chapterVO.setChapter_name(chapter_name);
				class_chapterVO.setClass_id(class_id);
				req.setAttribute("class_chapterVO", class_chapterVO);
				String url = "/back-end/class_chapter/update_Class_chapter_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			/*********************** 2.�}�l�ק��� ***********************/
			Class_chapterService Srv = new Class_chapterService();
			Srv.update(chapter_id, class_id, chapter_name);
			/*********************** 3.�s�W����,�ǳ���� ***********************/
			// ���
			String url = "/back-end/class_chapter/listClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** ��L�i�઺���~�B�z ***********************/
			/*************************** ��� ***********************/
		}
//		if ("chapter_id_sub_class".equals(action)) {
//			
//			/***********************0.�]�w���~�T���e��***********************/
//			//���ο��~�B�z
//			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z***********************/
//			String chapter_id = req.getParameter("chapter_id");
//			String chapter_name = req.getParameter("chapter_name");
//			/*********************** 2.�}�l�s�W���***********************/
//			Class_chapterService Srv = new Class_chapterService();
//			Set<Sub_classVO> Sub_classVO_list = Srv.getSub_classByClass_chapter_id(chapter_id);
//			class_chapterVO class_chapterVO = new class_chapterVO();
//			class_chapterVO.setchapter_id(chapter_id);
//			class_chapterVO.setchapter_name(chapter_name);
//			req.setAttribute("class_chapterVO", class_chapterVO);
//
//			req.setAttribute("Sub_classVO_list", Sub_classVO_list);
//			/*********************** 3.�s�W����,�ǳ����***********************/
//			String url = "/back-end/sub_class/chapter_id_sub_class.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//			/*********************** ��L�i�઺���~�B�z***********************/
//			/*************************** ��� ***********************/
//			
//		}
		

	}
}
