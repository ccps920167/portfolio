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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("evaluation_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入評價編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String evaluation_id = null;
				try {
					evaluation_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("評價編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				EvaluationService evaluationSvc = new EvaluationService();
				EvaluationVO evaluationVO = evaluationSvc.findByPrimaryKey(evaluation_id);
				if (evaluationVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("evaluationVO", evaluationVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/evaluation/listOneEvaluation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/select_page.jsp");
				failureView.forward(req, res);
			}
		}

	if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.接收請求參數****************************************/
			String evaluation_id = req.getParameter("evaluation_id").trim();
			
			/***************************2.開始查詢資料****************************************/
			EvaluationService evaluationSvc = new EvaluationService();
			EvaluationVO evaluationVO = evaluationSvc.findByPrimaryKey(evaluation_id);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("evaluationVO", evaluationVO);         // 資料庫取出的empVO物件,存入req
			String url = "/back-end/evaluation/update_Evaluation.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/evaluation/listAllEvaluation.jsp");
			failureView.forward(req, res);
		}
	}

	if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
	
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String evaluation_id = req.getParameter("evaluation_id").trim();
			System.out.println("1");
			
			String class_id = req.getParameter("class_id");
			System.out.println("2");
			
			String class_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (class_id == null || class_id.trim().length() == 0) {
				errorMsgs.add("評價編號: 請勿空白");
			} else if(!class_id.trim().matches(class_idReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("評價編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			System.out.println("3");
			
			
			String member_id = req.getParameter("member_id");
			String member_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (member_id == null || member_id.trim().length() == 0) {
				errorMsgs.add("會員編號: 請勿空白");
			} else if(!member_id.trim().matches(member_idReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}
			System.out.println("4");
			
			String evaluation_class = req.getParameter("evaluation_class");
			if (evaluation_class == null || evaluation_class.trim().length() == 0) {
				errorMsgs.add("請輸入評價內容");
			}	
			System.out.println("5");
			
			
			Integer evaluation_score = new Integer (req.getParameter("evaluation_score"));
			if(evaluation_score>10 || evaluation_score< 0) {	
				errorMsgs.add("評分請輸入1到10");
			}
			System.out.println("6");
			try {
				evaluation_score = new Integer(req.getParameter("evaluation_score").trim());
			} catch (NumberFormatException e) {
				evaluation_score = (int) 0.0;
				
				errorMsgs.add("不可空白");
			}
			System.out.println("7");
			
			java.sql.Timestamp evaluation_time = null;
			try {
				evaluation_time = java.sql.Timestamp.valueOf(req.getParameter("evaluation_time").trim());
			} catch (IllegalArgumentException e) {
				evaluation_time=new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			System.out.println("8");
			
			Integer evaluation_status = new Integer (req.getParameter("evaluation_status"));
			if(evaluation_status>1 || evaluation_status <0 ) {	
				errorMsgs.add("數字請輸入0跟1");
			}
			try {
				evaluation_status = new Integer(req.getParameter("evaluation_status").trim());
			} catch (NumberFormatException e) {
				evaluation_status = (int) 0.0;
							
				errorMsgs.add("狀態請填數字，不可空白");
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
			System.out.println("填裝完成");
			System.out.println("10");

//		 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("evaluationVO", evaluationVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/evaluation/update_Evaluation.jsp");
				failureView.forward(req, res);
				return; //程式中斷
	}
			
			/***************************2.開始修改資料*****************************************/
			EvaluationService evaluationSvc = new EvaluationService();
			evaluationVO = evaluationSvc.updateEvaluation(evaluation_id, class_id,member_id, evaluation_class, evaluation_score,evaluation_time,evaluation_status);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("evaluationVO", evaluationVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/evaluation/listOneEvaluation.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/evaluation/update_Evaluation.jsp");
			failureView.forward(req, res);
		}
	}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String class_id = req.getParameter("class_id");
				String class_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (class_id == null || class_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if (!class_id.trim().matches(class_idReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String member_id = req.getParameter("member_id").trim();
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("興趣等於副課程類別，請勿空白");
				}
				String evaluation_class = req.getParameter("evaluation_class");
				String evaluation_classReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
				if (evaluation_class == null || evaluation_class.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if (!evaluation_class.trim().matches(evaluation_classReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是中、英文字母, 且長度必需在2到20之間");
				}
				
				Integer evaluation_score = null;
				try {
					if (req.getParameter("evaluation_score") != null) {
						evaluation_score = new Integer(req.getParameter("evaluation_score").trim());
					}
					
					System.out.println(evaluation_score > 10);
					if(evaluation_score < 1 || evaluation_score > 10) {
						throw new RuntimeException("數字超過範圍");
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
					errorMsgs.add("請選擇日期!");
				}
				
				Integer evaluation_status = null;
				try {
					if (req.getParameter("evaluation_status") != null
							) {	evaluation_status = new Integer(req.getParameter("evaluation_status").trim());
					}if(evaluation_status > 1 || evaluation_status < 0) {
						throw new RuntimeException("數字超過範圍");
					}
				} catch (Exception e) {
					evaluation_status = 0;
					errorMsgs.add("0不顯示/1顯示");
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
					req.setAttribute("evaluationVO", evaluationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/addEvaluation.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				EvaluationService evaluationSvc = new EvaluationService();
				evaluationVO = evaluationSvc.addEvaluation(class_id,member_id,evaluation_class,evaluation_score,evaluation_time,evaluation_status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/evaluation/listAllEvaluation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEvaluation.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/evaluation/addEvaluation.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
