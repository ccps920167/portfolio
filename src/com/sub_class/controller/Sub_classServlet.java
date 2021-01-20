package com.sub_class.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main_class.model.Main_classJDBCDAO;
import com.main_class.model.Main_classService;
import com.main_class.model.Main_classVO;
import com.sub_class.model.Sub_classService;
import com.sub_class.model.Sub_classVO;

@WebServlet("/Sub_class/Sub_classServlet")
public class Sub_classServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// ���o�ӷ��Ѽ�
		String action = req.getParameter("action");
		/******************************** ��}�d���� ********************************/
		if ("getALL".equals(action)) {
			/*************************** ��� ***********************/
			String url = "/back-end/sub_class/listSub_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/******************************** ��}�s�W��� ********************************/
		if ("addnew".equals(action)) {
			/*************************** ��� ***********************/
			String url = "/back-end/sub_class/addSub_class_all.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("add_SubClass".equals(action)) {

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
			String mainClass_id = req.getParameter("mainClass_id");
			String mainClass_name = req.getParameter("mainClass_name");
			/*********************** 3.�s�W����,�ǳ���� ***********************/
			Main_classVO main_classVO = new Main_classVO();
			main_classVO.setMainClass_id(mainClass_id);
			main_classVO.setMainClass_name(mainClass_name);
			req.setAttribute("main_classVO", main_classVO);

			/*************************** ��� ***********************/
			String url = "/back-end/sub_class/addSub_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // �Ӧ�addSub_class.jsp���ШD
			/*********************** 0.�]�w���~�T���e�� ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
			// �^�Ǹ�ƪ��e��

			String mainClass_id = req.getParameter("mainClass_id");
			String mainClass_name = req.getParameter("mainClass_name");
			String subClass_name = req.getParameter("subClass_name");
			String subClass_status = req.getParameter("subClass_status");

			Main_classVO main_classVO = new Main_classVO();
			main_classVO.setMainClass_id(mainClass_id);
			main_classVO.setMainClass_name(mainClass_name);
			req.setAttribute("main_classVO", main_classVO);
			try {
				String regex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (subClass_name == null || subClass_name.trim().length() == 0) {
					errorMsgs.add("�п�J�����O�W��");
				} else if (!subClass_name.matches(regex)) {
					subClass_name = null;
					errorMsgs.add("�п�J�����O�W�ٶȯ�O����B�^��μƦr");
				}
				Sub_classVO sub_classVO = new Sub_classVO();
				sub_classVO.setSubClass_name(subClass_name);
				sub_classVO.setSubClass_status(Integer.parseInt(subClass_status));

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sub_classVO", sub_classVO);
					String url = "/back-end/sub_class/addSub_class.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}

				/*********************** 2.�}�l�s�W��� ***********************/
				Sub_classService Srv = new Sub_classService();
				Srv.add(subClass_name, subClass_status, mainClass_id);
				Main_classService Main_classSrv = new Main_classService();
				Set<Sub_classVO> Sub_classVO_list = Main_classSrv.getSub_classByMain_class_id(mainClass_id);

				req.setAttribute("Sub_classVO_list", Sub_classVO_list);

				/*********************** 3.�s�W����,�ǳ���� ***********************/
				String url = "/back-end/sub_class/mainclass_id_sub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				/*********************** ��L�i�઺���~�B�z ***********************/
				errorMsgs.add("�Х[�J���s�W�����O");
				/*************************** ��� ***********************/
				if (!errorMsgs.isEmpty()) {
					String url = "/back-end/sub_class/addSub_class.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}

			}
		}

		if ("insert_all".equals(action)) { // �Ӧ�addSub_class.jsp���ШD
			/*********************** 0.�]�w���~�T���e�� ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
			// �^�Ǹ�ƪ��e��
			String subClass_name = req.getParameter("subClass_name");
			String mainClass_id = req.getParameter("mainClass_id");
			String subClass_status = req.getParameter("subClass_status");
			String regex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (subClass_name == null || subClass_name.trim().length() == 0) {
				errorMsgs.add("�п�J�����O�W��");
			} else if (!subClass_name.matches(regex)) {
				errorMsgs.add("�п�J�����O�W�ٶȯ�O����B�^��μƦr");
			}

			if (!errorMsgs.isEmpty()) {
				String url = "/back-end/sub_class/addSub_class_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

			/*********************** 2.�}�l�s�W��� ***********************/
			Sub_classService Srv = new Sub_classService();
			Srv.add(subClass_name, subClass_status, mainClass_id);

			/*********************** 3.�s�W����,�ǳ���� ***********************/
			String url = "/back-end/sub_class/listSub_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*********************** ��L�i�઺���~�B�z ***********************/
			/*************************** ��� ***********************/
		}

		/******************************** �R���@����� ********************************/
		if ("delete".equals(action)) {
			/*********************** 0.�]�w���~�T���e�� ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/

				String subClass_id = req.getParameter("subClass_id");
				/*********************** 2.�}�l�s�W��� ***********************/
				Main_classVO main_classVO = new Main_classVO();
				main_classVO.setMainClass_id(req.getParameter("mainClass_id"));
				main_classVO.setMainClass_name(req.getParameter("mainClass_name"));
				req.setAttribute("main_classVO", main_classVO);

				Sub_classService Srv = new Sub_classService();
				Srv.deleteSub_class(subClass_id);
				Main_classService Main_classSrv = new Main_classService();
				Set<Sub_classVO> Sub_classVO_list = Main_classSrv
						.getSub_classByMain_class_id(req.getParameter("mainClass_id"));
				req.setAttribute("Sub_classVO_list", Sub_classVO_list);
				/*********************** 3.�s�W����,�ǳ���� ***********************/
				String url = "/back-end/sub_class/mainclass_id_sub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				/*************************** ��L�i�઺���~�B�z **********************************/
				errorMsgs.add("�ӵ���Ƥw�g���p�A�ФŧR��");
				Main_classService Main_classSrv = new Main_classService();
				Set<Sub_classVO> Sub_classVO_list = Main_classSrv
						.getSub_classByMain_class_id(req.getParameter("mainClass_id"));
				req.setAttribute("Sub_classVO_list", Sub_classVO_list);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				// ���
				String url = "/back-end/sub_class/mainclass_id_sub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** ��� ***********************/
			}
		}

		if ("update_subClass_id".equals(action)) {
			/*********************** 0.�]�w���~�T���e�� ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
			String subClass_id = req.getParameter("subClass_id");
			/*********************** 2.�}�l�s�W��� ***********************/
			Sub_classService srv = new Sub_classService();
			Sub_classVO sub_classVO = srv.getOneMain_class(subClass_id);
			req.setAttribute("sub_classVO", sub_classVO);
			/*********************** 3.�s�W����,�ǳ���� ***********************/
			String url = "/back-end/sub_class/update_Sub_class_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("update".equals(action)) {
			/*********************** 0.�]�w���~�T���e�� ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
			String subClass_id = req.getParameter("subClass_id");
			String subClass_name = req.getParameter("subClass_name");
			String subClass_status = req.getParameter("subClass_status");
			String mainClass_id = req.getParameter("mainClass_id");

			Sub_classVO sub_classVO = new Sub_classVO();
			sub_classVO.setMainClass_id(mainClass_id);
			sub_classVO.setSubClass_id(subClass_id);
			sub_classVO.setSubClass_name(subClass_name);
			sub_classVO.setSubClass_status(Integer.parseInt(subClass_status));

//			subClass_name���~�B�z
			if (subClass_name == null || subClass_name.trim().length() == 0) {
				errorMsgs.add("�ƽҵ{���O�W�ٽФŪť�");
			}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("sub_classVO", sub_classVO);
				String url = "/back-end/sub_class/update_Sub_class_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

			/*********************** 2.�}�l�s�W��� ***********************/
			Sub_classService Srv = new Sub_classService();
			Srv.update(sub_classVO);
			/*********************** 3.�s�W����,�ǳ���� ***********************/
			Main_classService Main_classSrv = new Main_classService();
			Main_classVO main_classVO = Main_classSrv.getOneMain_class(mainClass_id);
			req.setAttribute("main_classVO", main_classVO);

			Set<Sub_classVO> Sub_classVO_list = Main_classSrv
					.getSub_classByMain_class_id(req.getParameter("mainClass_id"));

			req.setAttribute("Sub_classVO_list", Sub_classVO_list);
			String url = "/back-end/sub_class/mainclass_id_sub_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** ��L�i�઺���~�B�z ***********************/
			/*************************** ��� ***********************/
		}
		if ("listOneSub_class".equals(action)) {
			/*********************** 0.�]�w���~�T���e�� ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
			try {
				String subClass_id = req.getParameter("subClass_id");
				String subClass_name = req.getParameter("subClass_name");
				String regex = "^[S][C][I][0-9]{8}$";
				if (subClass_id == null || subClass_id.trim().length() == 0) {
					if (subClass_name == null || subClass_name.trim().length() == 0) {
						errorMsgs.add("�п�J�i�ƽҵ{���O�s���j�Ρi�ƽҵ{���O�W�١j");
					}
				}
				if (subClass_id.matches(regex)) {
					errorMsgs.add("�п�J���T�榡");
				}

				if (!errorMsgs.isEmpty()) {
					String url = "/back-end/sub_class/select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				/*********************** 2.�}�l�s�W��� ***********************/
				Sub_classService Srv = new Sub_classService();
				Sub_classVO sub_classVO = Srv.getOneMain_class(subClass_id);
				req.setAttribute("sub_classVO", sub_classVO);
				/*********************** 3.�s�W����,�ǳ���� ***********************/
				String url = "/back-end/sub_class/listOneSub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*********************** ��L�i�઺���~�B�z ***********************/
			} catch (Exception e) {
				errorMsgs.add("��Ƥ��s�b");
				/*************************** ��� ***********************/
				String url = "/back-end/sub_class/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

		}
		if ("delete_all".equals(action)) {
			/*********************** 0.�]�w���~�T���e�� ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
				String subClass_id = req.getParameter("subClass_id");
				/*********************** 2.�}�l�s�W��� ***********************/
				Main_classVO main_classVO = new Main_classVO();
				main_classVO.setMainClass_id(req.getParameter("mainClass_id"));
				main_classVO.setMainClass_name(req.getParameter("mainClass_name"));
				req.setAttribute("main_classVO", main_classVO);

				Sub_classService Srv = new Sub_classService();
				Srv.deleteSub_class(subClass_id);

				Main_classService Main_classSrv = new Main_classService();
				Set<Sub_classVO> Sub_classVO_list = Main_classSrv
						.getSub_classByMain_class_id(req.getParameter("mainClass_id"));
				req.setAttribute("Sub_classVO_list", Sub_classVO_list);
				/*********************** 3.�s�W����,�ǳ���� ***********************/
				String url = "/back-end/sub_class/listSub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				/*************************** ��L�i�઺���~�B�z **********************************/
				errorMsgs.add("�ӵ���Ƥw�g���p�A�ФŧR��");
				Main_classService Main_classSrv = new Main_classService();
				Set<Sub_classVO> Sub_classVO_list = Main_classSrv
						.getSub_classByMain_class_id(req.getParameter("mainClass_id"));
				req.setAttribute("Sub_classVO_list", Sub_classVO_list);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				// ���
				String url = "/back-end/sub_class/listSub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** ��� ***********************/
			}
		}

	}
}
