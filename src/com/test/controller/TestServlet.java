package com.test.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.model.TestService;
import com.test.model.TestVO;

@WebServlet("/test/testServlet")
public class TestServlet extends HttpServlet {
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
				String str = req.getParameter("test_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�Ҹսs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/test/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String test_id = null;
				try {
					test_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�Ҹսs���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/test/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				TestService testSvc = new TestService();
				TestVO testVO = testSvc.getOneTest(test_id);
				if (testVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/test/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("testVO", testVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/test/listOneTest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/test/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String test_id = new String(req.getParameter("test_id"));
				
				/***************************2.�}�l�d�߸��****************************************/
				TestService testSvc = new TestService();
				TestVO testVO = testSvc.getOneTest(test_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("testVO", testVO);         
				String url = "/back-end/test/update_test_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/test/listAllTest.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String test_id = new String(req.getParameter("test_id").trim());
				

				
				
				
				String unit_id = new String (req.getParameter("unit_id").trim());
				
				String test_answer = req.getParameter("test_answer");
				String test_answerReg = "^[(a-dA-D)]{1}$";
				if (test_answer == null || test_answer.trim().length() == 0) {
					errorMsgs.add("�D�ظѵ�: �ФŪť�");
				} else if(!test_answer.trim().matches(test_answerReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�D�ظѵ�: �u��OA~D�䤤�@��");
	            }
				
				String test_content = req.getParameter("test_content");
				if (test_content == null || test_content.trim().length() == 0) {
					errorMsgs.add("�Ҹդ��e: �ФŪť�");
				} 
				
				
				String opta = req.getParameter("opta");
				if (opta == null || opta.trim().length() == 0) {
					errorMsgs.add("����ﶵA: �ФŪť�");
				} 
				String optb = req.getParameter("optb");
				if (optb == null || optb.trim().length() == 0) {
					errorMsgs.add("����ﶵB: �ФŪť�");
				} 
				String optc = req.getParameter("optc");
				if (optc == null || optc.trim().length() == 0) {
					errorMsgs.add("����ﶵC: �ФŪť�");
				} 
				String optd = req.getParameter("optd");
				if (optd == null || optd.trim().length() == 0) {
					errorMsgs.add("����ﶵD: �ФŪť�");
				} 

				TestVO testVO = new TestVO();
				testVO.setTest_id(test_id);
				testVO.setUnit_id(unit_id);
				testVO.setTest_answer(test_answer);
				testVO.setTest_content(test_content);
				testVO.setOpta(opta);
				testVO.setOptb(optb);
				testVO.setOptc(optc);
				testVO.setOptd(optd);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("testVO", testVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/test/update_test_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				TestService testSvc = new TestService();
				testVO = testSvc.update(test_id,unit_id, test_answer, test_content, opta, optb, optc, optd);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("testVO", testVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/test/listOneTest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/test/update_test_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("insert".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String unit_id = new String (req.getParameter("unit_id").trim());
				
				String test_answer = req.getParameter("test_answer");
				String test_answerReg = "^[(a-dA-D)]{1}$";
				if (test_answer == null || test_answer.trim().length() == 0) {
					errorMsgs.add("�D�ظѵ�: �ФŪť�");
				} else if(!test_answer.trim().matches(test_answerReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�D�ظѵ�: �u��OA~D�䤤�@��");
	            }
				
				String test_content = req.getParameter("test_content");
				if (test_content == null || test_content.trim().length() == 0) {
					errorMsgs.add("�Ҹդ��e: �ФŪť�");
				} 
				
				
				String opta = req.getParameter("opta");
				if (opta == null || opta.trim().length() == 0) {
					errorMsgs.add("����ﶵA: �ФŪť�");
				} 
				String optb = req.getParameter("optb");
				if (optb == null || optb.trim().length() == 0) {
					errorMsgs.add("����ﶵB: �ФŪť�");
				} 
				String optc = req.getParameter("optc");
				if (optc == null || optc.trim().length() == 0) {
					errorMsgs.add("����ﶵC: �ФŪť�");
				} 
				String optd = req.getParameter("optd");
				if (optd == null || optd.trim().length() == 0) {
					errorMsgs.add("����ﶵD: �ФŪť�");
				} 
				
				TestVO testVO = new TestVO();
				testVO.setUnit_id(unit_id);
				testVO.setTest_answer(test_answer);
				testVO.setTest_content(test_content);
				testVO.setOpta(opta);
				testVO.setOptb(optb);
				testVO.setOptc(optc);
				testVO.setOptd(optd);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("testVO", testVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/test/addTest.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				TestService testSvc = new TestService();
				testVO = testSvc.add(unit_id, test_answer, test_content, opta, optb, optc, optd);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("testVO", testVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/test/listAllTest.jsp";
							  
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/test/addTest.jsp");
				failureView.forward(req, res);
			}
		}
//
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String test_id = new String(req.getParameter("test_id"));
				
				/***************************2.�}�l�R�����***************************************/
				TestService testSvc = new TestService();
				testSvc.delete(test_id);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/test/listAllTest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/test/listAllTest.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	
}
