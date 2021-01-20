package com.evaluation.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.evaluation.model.EvaluationService;
import com.evaluation.model.EvaluationVO;

@WebServlet("/evaluation/EvaluationServlet")
public class EvaluationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("------------------------");
		System.out.println("EvaluationController");
		System.out.println("action = " + action);
		System.out.println("------------------------");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("evaluation_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�����s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String evaluation_id = null;
				try {
					evaluation_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�����s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				EvaluationService evaluationSvc = new EvaluationService();
				EvaluationVO evaluationVO = evaluationSvc.findByPrimaryKey(evaluation_id);
				if (evaluationVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("evaluationVO", evaluationVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/evaluation/listOneEvaluation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/select_page.jsp");
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
			String evaluation_id = req.getParameter("evaluation_id").trim();
			
			/***************************2.�}�l�d�߸��****************************************/
			EvaluationService evaluationSvc = new EvaluationService();
			EvaluationVO evaluationVO = evaluationSvc.findByPrimaryKey(evaluation_id);
							
			/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
			req.setAttribute("evaluationVO", evaluationVO);         // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/back-end/evaluation/update_Evaluation.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);

			/***************************��L�i�઺���~�B�z**********************************/
		} catch (Exception e) {
			errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/evaluation/listAllEvaluation.jsp");
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
			String evaluation_id = req.getParameter("evaluation_id").trim();
			System.out.println("1");
			
			String class_id = req.getParameter("class_id");
			System.out.println("2");
			
			String class_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (class_id == null || class_id.trim().length() == 0) {
				errorMsgs.add("�����s��: �ФŪť�");
			} else if(!class_id.trim().matches(class_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("�����s��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
            }
			System.out.println("3");
			
			
			String member_id = req.getParameter("member_id");
			String member_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (member_id == null || member_id.trim().length() == 0) {
				errorMsgs.add("�|���s��: �ФŪť�");
			} else if(!member_id.trim().matches(member_idReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("�|���s��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
			}
			System.out.println("4");
			
			String evaluation_class = req.getParameter("evaluation_class");
			if (evaluation_class == null || evaluation_class.trim().length() == 0) {
				errorMsgs.add("�п�J�������e");
			}	
			System.out.println("5");
			
			
			Integer evaluation_score = new Integer (req.getParameter("evaluation_score"));
			if(evaluation_score>10 || evaluation_score< 0) {	
				errorMsgs.add("�����п�J1��10");
			}
			System.out.println("6");
			try {
				evaluation_score = new Integer(req.getParameter("evaluation_score").trim());
			} catch (NumberFormatException e) {
				evaluation_score = (int) 0.0;
				
				errorMsgs.add("���i�ť�");
			}
			System.out.println("7");
			
			java.sql.Timestamp evaluation_time = null;
			try {
				evaluation_time = java.sql.Timestamp.valueOf(req.getParameter("evaluation_time").trim());
			} catch (IllegalArgumentException e) {
				evaluation_time=new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("�п�J���!");
			}
			System.out.println("8");
			
			Integer evaluation_status = new Integer (req.getParameter("evaluation_status"));
			if(evaluation_status>1 || evaluation_status <0 ) {	
				errorMsgs.add("�Ʀr�п�J0��1");
			}
			try {
				evaluation_status = new Integer(req.getParameter("evaluation_status").trim());
			} catch (NumberFormatException e) {
				evaluation_status = (int) 0.0;
							
				errorMsgs.add("���A�ж�Ʀr�A���i�ť�");
			}
			System.out.println("9");
	
			EvaluationVO evaluationVO = new EvaluationVO();
			
			evaluationVO.setEvaluation_id(evaluation_id);
			evaluationVO.setClass_id(class_id);
			evaluationVO.setMember_id(member_id);
			evaluationVO.setEvaluation_class(evaluation_class);
			evaluationVO.setEvaluation_score(evaluation_score);
			evaluationVO.setEvaluation_time(evaluation_time);
			evaluationVO.setEvaluation_status(evaluation_status);
			System.out.println("��˧���");
			System.out.println("10");

//		 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("evaluationVO", evaluationVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/evaluation/update_Evaluation.jsp");
				failureView.forward(req, res);
				return; //�{�����_
	}
			
			/***************************2.�}�l�ק���*****************************************/
			EvaluationService evaluationSvc = new EvaluationService();
			evaluationVO = evaluationSvc.updateEvaluation(evaluation_id, class_id,member_id, evaluation_class, evaluation_score,evaluation_time,evaluation_status);
			
			/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
			req.setAttribute("evaluationVO", evaluationVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			String url = "/back-end/evaluation/listOneEvaluation.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);

			/***************************��L�i�઺���~�B�z*************************************/
		} catch (Exception e) {
			errorMsgs.add("�ק��ƥ���:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/evaluation/update_Evaluation.jsp");
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
				String class_id = req.getParameter("class_id");
				String class_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (class_id == null || class_id.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if (!class_id.trim().matches(class_idReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				String member_id = req.getParameter("member_id").trim();
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("���쵥��ƽҵ{���O�A�ФŪť�");
				}
				String evaluation_class = req.getParameter("evaluation_class");
				String evaluation_classReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
				if (evaluation_class == null || evaluation_class.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if (!evaluation_class.trim().matches(evaluation_classReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �u��O���B�^��r��, �B���ץ��ݦb2��20����");
				}
				
				Integer evaluation_score = null;
				try {
					if (req.getParameter("evaluation_score") != null) {
						evaluation_score = new Integer(req.getParameter("evaluation_score").trim());
					}
					
					System.out.println(evaluation_score > 10);
					if(evaluation_score < 1 || evaluation_score > 10) {
						throw new RuntimeException("�Ʀr�W�L�d��");
					}
								
				} catch (NumberFormatException e) {
					errorMsgs.add(e.getMessage());
				}catch(RuntimeException e) {
					errorMsgs.add(e.getMessage());
				}
				
				java.sql.Timestamp evaluation_time = null;
				try {
					evaluation_time = java.sql.Timestamp.valueOf(req.getParameter("evaluation_time").trim());
				} catch (IllegalArgumentException e) {
					evaluation_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("�п�ܤ��!");
				}
				
				Integer evaluation_status = null;
				try {
					if (req.getParameter("evaluation_status") != null
							) {	evaluation_status = new Integer(req.getParameter("evaluation_status").trim());
					}if(evaluation_status > 1 || evaluation_status < 0) {
						throw new RuntimeException("�Ʀr�W�L�d��");
					}
				} catch (Exception e) {
					evaluation_status = 0;
					errorMsgs.add("0�����/1���");
				}

				EvaluationVO evaluationVO = new EvaluationVO();
				evaluationVO.setClass_id(class_id);
				evaluationVO.setMember_id(member_id);
				evaluationVO.setEvaluation_class(evaluation_class);
				evaluationVO.setEvaluation_score(evaluation_score);
				evaluationVO.setEvaluation_time(evaluation_time);
				evaluationVO.setEvaluation_status(evaluation_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("evaluationVO", evaluationVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/addEvaluation.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				EvaluationService evaluationSvc = new EvaluationService();
				evaluationVO = evaluationSvc.addEvaluation(class_id,member_id,evaluation_class,evaluation_score,evaluation_time,evaluation_status);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/evaluation/listAllEvaluation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEvaluation.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/addEvaluation.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
