package com.main_class.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main_class.model.Main_classJDBCDAO;
import com.main_class.model.Main_classService;
import com.main_class.model.Main_classVO;
import com.sub_class.model.Sub_classVO;

@WebServlet("/Main_class/Main_classServlet")
public class Main_classServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// ���o�ӷ��Ѽ�
		String action = req.getParameter("action");
		
		
		if ("listEmps_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.�N��J����ରMap**********************************/ 
				//�ĥ�Map<String,String[]> getParameterMap()����k 
				//�`�N:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.�}�l�ƦX�d��***************************************/
				Main_classService service = new Main_classService();
				List<Main_classVO>list = service.getMain_classAll();
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listEmps_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/main_class/listEmps_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		/******************************** ��}�d���� ********************************/
		if("getALL".equals(action)) {
			/*************************** ��� ***********************/
			String url = "/back-end/main_class/listMain_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	
		/******************************** ��}�s�W��� ********************************/
		if("addnew".equals(action)) {
			/*************************** ��� ***********************/
			String url = "/back-end/main_class/addMain_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/******************************** �d�ߤ@����� ********************************/
		if ("listOneMain_class".equals(action)) { // �Ӧ�listOneMain_class.jsp���ШD

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			// �إ߮e���˿��~���+��J�ݩʤ�
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// �榡�P�_

			String mainClass_name = req.getParameter("mainClass_name");
			String mainClass_status = req.getParameter("mainClass_status");
			String mainClass_id = req.getParameter("mainClass_id");

			if (mainClass_id == null || mainClass_id.length() == 0) {
				if (mainClass_name == null || mainClass_name.length() == 0) {
					if (mainClass_status == null || mainClass_status.length() == 0) {
						errorMsgs.add("�i�D�ҵ{���O�s���j�B�i�D���O�ҵ{�W�١j�Ρi�D���O���A�j�����ܤ@��g");
					}
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/main_class/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.�}�l�d�߸�� ***************************************/
			Main_classService main_classSrv = new Main_classService();
			// ��Ʈw���X�����,�s�J����
			Main_classVO main_classVO = main_classSrv.getOneMain_class(mainClass_id);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			// ����s�J�ݩ�
			req.setAttribute("main_classVO", main_classVO);
			// ���
			String url = "/back-end/main_class/listOneMain_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z **********************************/

		}

		/******************************** �s�W�@����� ********************************/
		if ("insert".equals(action)) { // �Ӧ�addMain_class.jsp���ШD
			// �˿��~���
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			String mainClass_name = req.getParameter("mainClass_name");
			// mainClass_name�P�_�O�_����
			if (mainClass_name == null || mainClass_name.trim().isEmpty()) {
				errorMsgs.add("�D���O���i�H�ť�");
			}

			String[] subClass_name = req.getParameterValues("subClass_name");

			System.out.println(mainClass_name);
			System.out.println(subClass_name);
			// �w�g�s�b����Ʀs�J����
			Main_classVO main_classVo = new Main_classVO();
			main_classVo.setMainClass_name(mainClass_name);
			main_classVo.setMainClass_status(0);

			// �����~�T�����^�h
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Main_classVO", main_classVo); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/main_class/addMain_class.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.�}�l�s�W��� ***************************************/
			Main_classService service = new Main_classService();
			service.addMain_class(mainClass_name, 1);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			// ���
			String url = "/back-end/main_class/listMain_class.jsp";
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
				String mainClass_id = req.getParameter("mainClass_id");
				/*************************** 2.�}�l�s�W��� ***************************************/
				Main_classService main_classSrv = new Main_classService();
				main_classSrv.deleteMain_class(mainClass_id);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				// ���
				String url = "/back-end/main_class/listMain_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {

				/*************************** ��L�i�઺���~�B�z **********************************/
				errorMsgs.add("�ӵ���Ƥw�g���p�A�ФŧR��");
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				// ���
				String url = "/back-end/main_class/listMain_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}
		}
		if ("update_mainClass_id".equals(action)) {
			/*********************** 0.�]�w���~�T���e�� ***********************/
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
			String mainClass_id = req.getParameter("mainClass_id");
			/*********************** 2.�}�l�s�W��� ***********************/
			Main_classService Srv = new Main_classService();
			Main_classVO main_classVO = Srv.getOneMain_class(mainClass_id);
			req.setAttribute("main_classVO", main_classVO);
			/*********************** 3.�s�W����,�ǳ���� ***********************/
			// ���
			String url = "/back-end/main_class/update_Main_class_input.jsp";
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
			String mainClass_id = req.getParameter("mainClass_id");
			String mainClass_name = req.getParameter("mainClass_name");
			String mainClass_status = req.getParameter("mainClass_status");

			if (mainClass_name == null || mainClass_name.length() == 0) {
				errorMsgs.add("�п�J�D�ҵ{���O�W��");
			}

			if (mainClass_status == null || mainClass_status.length() == 0) {
				errorMsgs.add("�п�J�D�ҵ{���O�W��");
			}

			if (!errorMsgs.isEmpty()) {
				Main_classVO main_classVO = new Main_classVO();
				main_classVO.setMainClass_id(mainClass_id);
				main_classVO.setMainClass_name(mainClass_name);
				main_classVO.setMainClass_status(Integer.parseInt(mainClass_status));
				req.setAttribute("main_classVO", main_classVO);
				String url = "/back-end/main_class/update_Main_class_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			/*********************** 2.�}�l�ק��� ***********************/
			Main_classService Srv = new Main_classService();
			Srv.updateMain_class(mainClass_name, Integer.parseInt(mainClass_status), mainClass_id);
			/*********************** 3.�s�W����,�ǳ���� ***********************/
			// ���
			String url = "/back-end/main_class/listMain_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** ��L�i�઺���~�B�z ***********************/
			/*************************** ��� ***********************/
		}
		if ("mainclass_id_sub_class".equals(action)) {
			
			/***********************0.�]�w���~�T���e��***********************/
			//���ο��~�B�z
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z***********************/
			String mainClass_id = req.getParameter("mainClass_id");
			String mainClass_name = req.getParameter("mainClass_name");
			/*********************** 2.�}�l�s�W���***********************/
			Main_classService Srv = new Main_classService();
			Set<Sub_classVO> Sub_classVO_list = Srv.getSub_classByMain_class_id(mainClass_id);
			Main_classVO main_classVO = new Main_classVO();
			main_classVO.setMainClass_id(mainClass_id);
			main_classVO.setMainClass_name(mainClass_name);
			req.setAttribute("main_classVO", main_classVO);

			req.setAttribute("Sub_classVO_list", Sub_classVO_list);
			/*********************** 3.�s�W����,�ǳ����***********************/
			String url = "/back-end/sub_class/mainclass_id_sub_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** ��L�i�઺���~�B�z***********************/
			/*************************** ��� ***********************/
			
		}
		

	}
}
