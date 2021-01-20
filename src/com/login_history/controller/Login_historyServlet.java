package com.login_history.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login_history.model.Login_historyService;
import com.login_history.model.Login_historyVO;

@WebServlet("/login_history/Login_historyServlet")
public class Login_historyServlet extends HttpServlet {
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
				String str = req.getParameter("login_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�n�J���v�����s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login_history/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String login_id = null;
				try {
					login_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�n�J���v�����s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login_history/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Login_historyService login_historySvc = new Login_historyService();
				Login_historyVO login_historyVO = login_historySvc.getOneLogin_history(login_id);
				if (login_historyVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login_history/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("login_historyVO", login_historyVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/login_history/listOneLogin_history.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login_history/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String login_id = new String(req.getParameter("login_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				Login_historyService login_historySvc = new Login_historyService();
				Login_historyVO login_historyVO = login_historySvc.getOneLogin_history(login_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("login_historyVO", login_historyVO);         
				String url = "/back-end/login_history/update_login_history_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/login_history/listAllLogin_history.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String login_id = new String(req.getParameter("login_id").trim());
				
				java.sql.Timestamp login_time = null;
				try {
					login_time = java.sql.Timestamp.valueOf(req.getParameter("login_time").trim());
				} catch (IllegalArgumentException e) {
					login_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String login_arrange = req.getParameter("login_arrange");
				String login_arrangeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (login_arrange == null || login_arrange.trim().length() == 0) {
					errorMsgs.add("�n�J���v����: �ФŪť�");
				} else if(!login_arrange.trim().matches(login_arrangeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�n�J���v����: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String login_ip = req.getParameter("login_ip");
				String login_ipReg = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
				if (login_ip == null || login_ip.trim().length() == 0) {
					errorMsgs.add("�n�JIP: �ФŪť�");
				} else if(!login_ip.trim().matches(login_ipReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�n�JIP: �榡��XXX.XXX.XXX.XXX�B�u���Ʀr");
	            }
				
				
				String member_id = req.getParameter("member_id");
				String member_idReg = "[M][E][M]\\d{5}";
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if(!member_id.trim().matches(member_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �榡��MEMXXXXX,�B�᤭�쬰�Ʀr");
	            }

				
				

				Login_historyVO login_historyVO = new Login_historyVO();
				login_historyVO.setLogin_id(login_id);
				login_historyVO.setLogin_time(login_time);;
				login_historyVO.setLogin_arrange(login_arrange);
				login_historyVO.setLogin_ip(login_ip);
				login_historyVO.setMember_id(member_id);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("login_historyVO", login_historyVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/login_history/update_login_history_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Login_historyService login_historySvc = new Login_historyService();
				login_historyVO = login_historySvc.update(login_id, login_time, login_arrange, login_ip, member_id);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("login_historyVO", login_historyVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/login_history/listOneLogin_history.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/login_history/update_login_history_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("insert".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				
				java.sql.Timestamp login_time = null;
				try {
					login_time = java.sql.Timestamp.valueOf(req.getParameter("login_time").trim());
				} catch (IllegalArgumentException e) {
					login_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}
				
				String login_arrange = req.getParameter("login_arrange");
				String login_arrangeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (login_arrange == null || login_arrange.trim().length() == 0) {
					errorMsgs.add("�n�J���v�����˸m: �ФŪť�");
				} else if(!login_arrange.trim().matches(login_arrangeReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�n�J���v�����˸m: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
	            }
				
				String login_ip = req.getParameter("login_ip");
				String login_ipReg = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
				if (login_ip == null || login_ip.trim().length() == 0) {
					errorMsgs.add("�n�JIP: �ФŪť�");
				} else if(!login_ip.trim().matches(login_ipReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�n�JIP: �榡��XXX.XXX.XXX.XXX�B�u���Ʀr");
	            }
				
				
				String member_id = new String (req.getParameter("member_id").trim());

				Login_historyVO login_historyVO = new Login_historyVO();
				login_historyVO.setLogin_time(login_time);
				login_historyVO.setLogin_arrange(login_arrange);
				login_historyVO.setLogin_ip(login_ip);
				login_historyVO.setMember_id(member_id);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("login_historyVO", login_historyVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/login_history/addLogin_history.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				Login_historyService login_historySvc = new Login_historyService();
				login_historyVO = login_historySvc.add(login_time, login_arrange, login_ip, member_id);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("login_historyVO", login_historyVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/login_history/listAllLogin_history.jsp";
							  
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/login_history/addLogin_history.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String login_id = new String(req.getParameter("login_id"));
				
				/***************************2.�}�l�R�����***************************************/
				Login_historyService login_historySvc = new Login_historyService();
				login_historySvc.delete(login_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/login_history/listAllLogin_history.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/login_history/listAllLogin_history.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
