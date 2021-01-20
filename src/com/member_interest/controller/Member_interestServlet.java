package com.member_interest.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member_interest.model.Member_interestService;
import com.member_interest.model.Member_interestVO;

@WebServlet("/member_interest/Member_interestServlet")
public class Member_interestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String member_id = new String(req.getParameter("member_id"));
				String subclass_id = new String(req.getParameter("subclass_id"));
				/***************************2.�}�l�R�����***************************************/
				Member_interestService member_interestSvc = new Member_interestService();
				member_interestSvc.deletebySubclass(member_id, subclass_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
	}
		
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("interest_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J����s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String interest_id = null;
				try {
					interest_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("����s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Member_interestService member_interestSvc = new Member_interestService();
				Member_interestVO member_interestVO = member_interestSvc.findByPrimaryKey(interest_id);
				if (member_interestVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("member_interestVO", member_interestVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/member_interest/listOneMember_interest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/select_page.jsp");
				failureView.forward(req, res);
			}
		}

	if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.�����ШD�Ѽ�****************************************/
			String interest_id = req.getParameter("interest_id").trim();
			
			/***************************2.�}�l�d�߸��****************************************/
			Member_interestService member_interestSvc = new Member_interestService();
			Member_interestVO member_interestVO = member_interestSvc.findByPrimaryKey(interest_id);
							
			/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
			req.setAttribute("member_interestVO", member_interestVO);         // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/back-end/member_interest/update_Member_interest.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);

			/***************************��L�i�઺���~�B�z**********************************/
		} catch (Exception e) {
			errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/member_interest/listAllMember_interest.jsp");
			failureView.forward(req, res);
		}
	}

	if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
	
		try {
			/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			String interest_id = req.getParameter("interest_id").trim();
			
			String member_id = req.getParameter("member_id");
			String member_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (member_id == null || member_id.trim().length() == 0) {
				errorMsgs.add("�|���s��: �ФŪť�");
			} else if(!member_id.trim().matches(member_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("�|���s��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
            }
			
			String subclass_id = req.getParameter("subclass_id");
			if (subclass_id == null || subclass_id.trim().length() == 0) {
				errorMsgs.add("����ФŪť�");
			}	
			

			Integer interest_status = new Integer (req.getParameter("interest_status"));
			if(interest_status>1||interest_status<0) {	
				errorMsgs.add("�Ʀr�п�J0��1");
			}
			try {
				interest_status = new Integer(req.getParameter("interest_status").trim());
			} catch (NumberFormatException e) {
				interest_status = (int) 0.0;
							
				errorMsgs.add("���A�ж�Ʀr�A���i�ť�");
			}
	
			Member_interestVO member_interestVO = new Member_interestVO();
			member_interestVO.setInterest_id(interest_id);
			member_interestVO.setMember_id(member_id);
			member_interestVO.setSubclass_id(subclass_id);
			member_interestVO.setInterest_status(interest_status);
			

//		 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("member_interestVO", member_interestVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member_interest/update_Member_interest.jsp");
				failureView.forward(req, res);
				return; //�{�����_
	}
			
			/***************************2.�}�l�ק���*****************************************/
			Member_interestService member_interestSvc = new Member_interestService();
			member_interestVO = member_interestSvc.updateInterest_form(interest_id, member_id, subclass_id, interest_status);
			
			/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
			req.setAttribute("member_interestVO", member_interestVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			String url = "/back-end/member_interest/listOneMember_interest.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);

			/***************************��L�i�઺���~�B�z*************************************/
		} catch (Exception e) {
			errorMsgs.add("�ק��ƥ���:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/member_interest/update_Member_interest.jsp");
			failureView.forward(req, res);
		}
	}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
				String member_id = req.getParameter("member_id");
				String member_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if (!member_id.trim().matches(member_idReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				String subclass_id = req.getParameter("subclass_id").trim();
				if (subclass_id == null || subclass_id.trim().length() == 0) {
					errorMsgs.add("���쵥��ƽҵ{���O�A�ФŪť�");
				}
				Integer interest_status = null;
				try {
					if (req.getParameter("interest_status") != null || req.getParameter("interest_status").length() != 0) {
						interest_status = new Integer(req.getParameter("interest_status").trim());
					}
				} catch (Exception e) {
					errorMsgs.add("���A��0��1�A�ФŪť�");
				}


				Member_interestVO member_interestVO = new Member_interestVO();
				member_interestVO.setMember_id(member_id);
				member_interestVO.setSubclass_id(subclass_id);
				member_interestVO.setInterest_status(interest_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("member_interestVO", member_interestVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/addMember_interest.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				Member_interestService member_interestSvc = new Member_interestService();
				member_interestVO = member_interestSvc.addInterest_form(member_id, subclass_id, interest_status);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/member_interest/listAllMember_interest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMember_interest.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/addMember_interest.jsp");
				failureView.forward(req, res);
			}
		}

//	if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
//
//		List<String> errorMsgs = new LinkedList<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
//		req.setAttribute("errorMsgs", errorMsgs);
//
//		try {
//			/***************************1.�����ШD�Ѽ�***************************************/
//			Integer empno = new Integer(req.getParameter("empno"));
//			
//			/***************************2.�}�l�R�����***************************************/
//			EmpService empSvc = new EmpService();
//			empSvc.deleteEmp(empno);
//			
//			/***************************3.�R������,�ǳ����(Send the Success view)***********/								
//			String url = "/emp/listAllEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//			successView.forward(req, res);
//			
//			/***************************��L�i�઺���~�B�z**********************************/
//		} catch (Exception e) {
//			errorMsgs.add("�R����ƥ���:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/emp/listAllEmp.jsp");
//			failureView.forward(req, res);
//		}
//}

	}
}
