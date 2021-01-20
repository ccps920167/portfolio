package com.admin_auth.controller;

import java.awt.peer.SystemTrayPeer;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin_auth.model.Admin_authService;
import com.admin_auth.model.Admin_authVO;


@WebServlet("/admin_auth/admin_authServlet")
public class Admin_authServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("admin_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�޲z���s���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_auth/select_pageAdmin_auth.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String admin_id = null;
				try {
					admin_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�޲z���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_auth/select_pageAdmin_auth.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Admin_authService admin_authSvc = new Admin_authService();
				List<Admin_authVO> admin_authlist = admin_authSvc.getOneAdmin_auth(admin_id);
				
				if (admin_authlist == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_auth/select_pageAdmin_auth.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("admin_authlist", admin_authlist); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/admin_auth/listOneAdmin_auth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_auth/select_pageAdmin_auth.jsp");
				failureView.forward(req, res);
			}
		}

//		if ("getOne_For_Update".equals(action)) { 
//
//			List<String> errorMsgs = new LinkedList<String>();
//			
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.�����ШD�Ѽ�****************************************/
//				String admin_id = new String(req.getParameter("admin_id"));
//				
//				/***************************2.�}�l�d�߸��****************************************/
//				admin_authService admin_authSvc = new admin_authService();
//				admin_authVO admin_authVO = admin_authSvc.getOneadmin_auth(admin_id);
//								
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("admin_authVO", admin_authVO);         
//				String url = "/back-end/admin_auth/update_admin_auth_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/admin_auth/listAlladmin_auth.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String admin_id = req.getParameter("admin_id");
//System.out.println("1");
//System.out.println(admin_id);
//System.out.println("1");
				String statusA = req.getParameter("auth_status_A");
				String statusB = req.getParameter("auth_status_B");
				String statusC = req.getParameter("auth_status_C");
				String statusD = req.getParameter("auth_status_D");
				String statusE = req.getParameter("auth_status_E");
//System.out.println(statusA);				
				Timestamp auth_update = new java.sql.Timestamp(System.currentTimeMillis());

				/***************************2.�}�l��s���****************************************/
				Admin_authService admin_authSvc = new Admin_authService();
				admin_authSvc.updateAdmin_auth(admin_id, "A", Integer.valueOf(statusA), auth_update);
				admin_authSvc.updateAdmin_auth(admin_id, "B", Integer.valueOf(statusB), auth_update);
				admin_authSvc.updateAdmin_auth(admin_id, "C", Integer.valueOf(statusC), auth_update);
				admin_authSvc.updateAdmin_auth(admin_id, "D", Integer.valueOf(statusD), auth_update);
				admin_authSvc.updateAdmin_auth(admin_id, "E", Integer.valueOf(statusE), auth_update);
//				/***************************1.�����ШD�Ѽ�****************************************/
//				String admin_id = new String(req.getParameter("admin_id"));
//System.out.println("1");
//				/***************************2.�}�l�d�߸��****************************************/
//				Admin_authService admin_authSvc = new Admin_authService();
//				Admin_authVO admin_authVO = (Admin_authVO) admin_authSvc.getOneAdmin_auth(admin_id);
//System.out.println("2");								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("admin_authVO", admin_authVO);         
				String url = "/back-end/admin_auth/listOneAdmin_auth.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
//System.out.println("3");
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin_auth/listOneAdmin_auth.jsp");
				failureView.forward(req, res);
			}
		}

		
//		if ("insert".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
//				
//				String admin_name = req.getParameter("admin_name");
//				String admin_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
//				if (admin_name == null || admin_name.trim().length() == 0) {
//					errorMsgs.add("�޲z���W��: �ФŪť�");
//				} else if(!admin_name.trim().matches(admin_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�޲z���W��: �u��O���B�^��r���M_ , �B���ץ��ݦb2��10����");
//	            }
//				
//				
//				String admin_pwd = req.getParameter("admin_pwd");
//				String admin_pwdReg = "^[(a-zA-Z0-9_)]{6,12}$";
//				if (admin_pwd == null || admin_pwd.trim().length() == 0) {
//					errorMsgs.add("�޲z���K�X: �ФŪť�");
//				} else if(!admin_pwd.trim().matches(admin_pwdReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�޲z���K�X:�u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb6��12����");
//	            }
//				
//				Integer admin_status = Integer.valueOf(req.getParameter("admin_status"));
//				if (req.getParameter("admin_status") == null
//						|| (req.getParameter("admin_status")).trim().length() == 0) {
//					errorMsgs.add("�޲z�����A: �ФŪť�");
//				}
//				
//				
//				String admin_id=req.getParameter("admin_id");
//				admin_authVO admin_authVO = new admin_authVO();
//				admin_authVO.setAdmin_name(admin_name);
//				admin_authVO.setAdmin_pwd(admin_pwd);
//				admin_authVO.setAdmin_status(admin_status);
//
//
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("admin_authVO", admin_authVO); // �t����J�榡���~��empVO����,�]�s�Jreq
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/admin_auth/addadmin_auth.jsp");
//					failureView.forward(req, res);
//					return; //�{�����_
//				}
//				
//				/***************************2.�}�l�ק���*****************************************/
//				admin_authService admin_authSvc = new admin_authService();
//				admin_authVO = admin_authSvc.addadmin_auth(admin_id,admin_name, admin_pwd,admin_status);
//				
//				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
//				req.setAttribute("admin_authVO", admin_authVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
//				String url = "/back-end/admin_auth/listAlladmin_auth.jsp";
//							  
//				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************��L�i�઺���~�B�z*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/admin_auth/addadmin_auth.jsp");
//				failureView.forward(req, res);
//			}
//		}
//
//		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.�����ШD�Ѽ�***************************************/
//				String admin_id = new String(req.getParameter("admin_id"));
//				
//				/***************************2.�}�l�R�����***************************************/
//				admin_authService admin_authSvc = new admin_authService();
//				admin_authSvc.deleteadmin_auth(admin_id);
//				
//				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//				String url = "/back-end/admin_auth/listAlladmin_auth.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//				successView.forward(req, res);
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("�R����ƥ���:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/admin_auth/listAlladmin_auth.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}

}
