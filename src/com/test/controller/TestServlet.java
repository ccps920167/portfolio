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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("test_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入考試編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/test/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String test_id = null;
				try {
					test_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("考試編號編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/test/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				TestService testSvc = new TestService();
				TestVO testVO = testSvc.getOneTest(test_id);
				if (testVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/test/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("testVO", testVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/test/listOneTest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/test/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String test_id = new String(req.getParameter("test_id"));
				
				/***************************2.開始查詢資料****************************************/
				TestService testSvc = new TestService();
				TestVO testVO = testSvc.getOneTest(test_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("testVO", testVO);         
				String url = "/back-end/test/update_test_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/test/listAllTest.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String test_id = new String(req.getParameter("test_id").trim());
				

				
				
				
				String unit_id = new String (req.getParameter("unit_id").trim());
				
				String test_answer = req.getParameter("test_answer");
				String test_answerReg = "^[(a-dA-D)]{1}$";
				if (test_answer == null || test_answer.trim().length() == 0) {
					errorMsgs.add("題目解答: 請勿空白");
				} else if(!test_answer.trim().matches(test_answerReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("題目解答: 只能是A~D其中一個");
	            }
				
				String test_content = req.getParameter("test_content");
				if (test_content == null || test_content.trim().length() == 0) {
					errorMsgs.add("考試內容: 請勿空白");
				} 
				
				
				String opta = req.getParameter("opta");
				if (opta == null || opta.trim().length() == 0) {
					errorMsgs.add("測驗選項A: 請勿空白");
				} 
				String optb = req.getParameter("optb");
				if (optb == null || optb.trim().length() == 0) {
					errorMsgs.add("測驗選項B: 請勿空白");
				} 
				String optc = req.getParameter("optc");
				if (optc == null || optc.trim().length() == 0) {
					errorMsgs.add("測驗選項C: 請勿空白");
				} 
				String optd = req.getParameter("optd");
				if (optd == null || optd.trim().length() == 0) {
					errorMsgs.add("測驗選項D: 請勿空白");
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
					req.setAttribute("testVO", testVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/test/update_test_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TestService testSvc = new TestService();
				testVO = testSvc.update(test_id,unit_id, test_answer, test_content, opta, optb, optc, optd);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("testVO", testVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/test/listOneTest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/test/update_test_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("insert".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String unit_id = new String (req.getParameter("unit_id").trim());
				
				String test_answer = req.getParameter("test_answer");
				String test_answerReg = "^[(a-dA-D)]{1}$";
				if (test_answer == null || test_answer.trim().length() == 0) {
					errorMsgs.add("題目解答: 請勿空白");
				} else if(!test_answer.trim().matches(test_answerReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("題目解答: 只能是A~D其中一個");
	            }
				
				String test_content = req.getParameter("test_content");
				if (test_content == null || test_content.trim().length() == 0) {
					errorMsgs.add("考試內容: 請勿空白");
				} 
				
				
				String opta = req.getParameter("opta");
				if (opta == null || opta.trim().length() == 0) {
					errorMsgs.add("測驗選項A: 請勿空白");
				} 
				String optb = req.getParameter("optb");
				if (optb == null || optb.trim().length() == 0) {
					errorMsgs.add("測驗選項B: 請勿空白");
				} 
				String optc = req.getParameter("optc");
				if (optc == null || optc.trim().length() == 0) {
					errorMsgs.add("測驗選項C: 請勿空白");
				} 
				String optd = req.getParameter("optd");
				if (optd == null || optd.trim().length() == 0) {
					errorMsgs.add("測驗選項D: 請勿空白");
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
					req.setAttribute("testVO", testVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/test/addTest.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				TestService testSvc = new TestService();
				testVO = testSvc.add(unit_id, test_answer, test_content, opta, optb, optc, optd);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("testVO", testVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/test/listAllTest.jsp";
							  
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/test/addTest.jsp");
				failureView.forward(req, res);
			}
		}
//
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String test_id = new String(req.getParameter("test_id"));
				
				/***************************2.開始刪除資料***************************************/
				TestService testSvc = new TestService();
				testSvc.delete(test_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/test/listAllTest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/test/listAllTest.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	
}
