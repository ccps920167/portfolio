package com.admin_list.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.java.javaURLContextFactory;

import com.admin_auth.model.Admin_authService;
import com.admin_auth.model.Admin_authVO;
import com.admin_list.model.Admin_listJDBCDAO;
import com.admin_list.model.Admin_listService;
import com.admin_list.model.Admin_listVO;


@WebServlet("/admin_list/admin_listServlet")
public class Admin_listServlet extends HttpServlet {
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_list/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_list/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Admin_listService admin_listSvc = new Admin_listService();
				Admin_listVO admin_listVO = admin_listSvc.getOneAdmin_list(admin_id);
				if (admin_listVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_list/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("admin_listVO", admin_listVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/admin_list/listOneAdmin_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String admin_id = new String(req.getParameter("admin_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Admin_listService admin_listSvc = new Admin_listService();
				Admin_listVO admin_listVO = admin_listSvc.getOneAdmin_list(admin_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("admin_listVO", admin_listVO);         
				String url = "/back-end/admin_list/update_admin_list_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin_list/listAllAdmin_list.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String admin_id = new String(req.getParameter("admin_id").trim());
				
				String admin_name = req.getParameter("admin_name");
				String admin_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (admin_name == null || admin_name.trim().length() == 0) {
					errorMsgs.add("�޲z���W��: �ФŪť�");
				} else if(!admin_name.trim().matches(admin_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�޲z���W��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				
				String admin_pwd = req.getParameter("admin_pwd");
				String admin_pwdReg = "^[(a-zA-Z0-9_)]{6,12}$";
				if (admin_pwd == null || admin_pwd.trim().length() == 0) {
					errorMsgs.add("�޲z���K�X: �ФŪť�");
				} else if(!admin_pwd.trim().matches(admin_pwdReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�޲z���K�X:�u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb6��12����");
	            }
				
				String admin_status = req.getParameter("admin_status");
				if (admin_status == null
						|| admin_status.trim().length() == 0) {
					admin_status="0";
					errorMsgs.add("�޲z�����A: �ФŪť�");
				}
				
				Admin_listService admin_listSvc = new Admin_listService();
				Admin_listVO admin_listVO = new Admin_listVO();
				admin_listVO.setAdmin_id(admin_id);
				admin_listVO.setAdmin_name(admin_name);
				admin_listVO.setAdmin_pwd(admin_pwd);
				admin_listVO.setAdmin_status(Integer.valueOf(admin_status));


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admin_listVO", admin_listVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/admin_list/update_admin_list_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				
				admin_listVO = admin_listSvc.updateAdmin_list(admin_id, admin_name, admin_pwd, Integer.valueOf(admin_status));
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("admin_listVO", admin_listVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/admin_list/listOneAdmin_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin_list/update_admin_list_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("insert".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				String admin_name = req.getParameter("admin_name");
				String admin_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (admin_name == null || admin_name.trim().length() == 0) {
					errorMsgs.add("�޲z���W��: �ФŪť�");
				} else if(!admin_name.trim().matches(admin_nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�޲z���W��: �u��O���B�^��r���M_ , �B���ץ��ݦb2��10����");
	            }
				
				
				String admin_pwd = req.getParameter("admin_pwd");
				String admin_pwdReg = "^[(a-zA-Z0-9_)]{6,12}$";
				if (admin_pwd == null || admin_pwd.trim().length() == 0) {
					errorMsgs.add("�޲z���K�X: �ФŪť�");
				} else if(!admin_pwd.trim().matches(admin_pwdReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�޲z���K�X:�u��O�^��r���B�Ʀr�M_ , �B���ץ��ݦb6��12����");
	            }
				
				Integer admin_status = Integer.valueOf(req.getParameter("admin_status"));
				if (req.getParameter("admin_status") == null
						|| (req.getParameter("admin_status")).trim().length() == 0) {
					errorMsgs.add("�޲z�����A: �ФŪť�");
				}
				
				List<Admin_authVO> admin_lstList = new ArrayList<Admin_authVO>();
				String[] S = {"A","B","C","D","E"};
				
				int i = 0;
				for(String item:S) {
					Admin_authVO vo = new Admin_authVO();
					vo.setAuth_status(Integer.parseInt("1"));
					vo.setAuth_update(new java.sql.Timestamp(System.currentTimeMillis()));
					vo.setAuth_id(S[i]);
					admin_lstList.add(vo);
					i++;
				}
				
				
				String admin_id=req.getParameter("admin_id");
				Admin_listVO admin_listVO = new Admin_listVO();
				admin_listVO.setAdmin_name(admin_name);
				admin_listVO.setAdmin_pwd(admin_pwd);
				admin_listVO.setAdmin_status(admin_status);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admin_listVO", admin_listVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/admin_list/addAdmin_list.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Admin_listService dao = new Admin_listService();
				dao.addWithAdmin_list(admin_listVO, admin_lstList);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("admin_listVO", admin_listVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/admin_list/listAllAdmin_list.jsp";
							  
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin_list/addAdmin_list.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String admin_id = new String(req.getParameter("admin_id"));
				
				/***************************2.�}�l�R�����***************************************/
				Admin_listService admin_listSvc = new Admin_listService();
				admin_listSvc.deleteAdmin_list(admin_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/admin_list/listAllAdmin_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin_list/listAllAdmin_list.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
