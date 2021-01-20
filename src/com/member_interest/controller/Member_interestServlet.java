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
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數***************************************/
				String member_id = new String(req.getParameter("member_id"));
				String subclass_id = new String(req.getParameter("subclass_id"));
				/***************************2.開始刪除資料***************************************/
				Member_interestService member_interestSvc = new Member_interestService();
				member_interestSvc.deletebySubclass(member_id, subclass_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
	}
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("interest_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入興趣編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String interest_id = null;
				try {
					interest_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("興趣編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Member_interestService member_interestSvc = new Member_interestService();
				Member_interestVO member_interestVO = member_interestSvc.findByPrimaryKey(interest_id);
				if (member_interestVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("member_interestVO", member_interestVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member_interest/listOneMember_interest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/select_page.jsp");
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
			String interest_id = req.getParameter("interest_id").trim();
			
			/***************************2.開始查詢資料****************************************/
			Member_interestService member_interestSvc = new Member_interestService();
			Member_interestVO member_interestVO = member_interestSvc.findByPrimaryKey(interest_id);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("member_interestVO", member_interestVO);         // 資料庫取出的empVO物件,存入req
			String url = "/back-end/member_interest/update_Member_interest.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/member_interest/listAllMember_interest.jsp");
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
			String interest_id = req.getParameter("interest_id").trim();
			
			String member_id = req.getParameter("member_id");
			String member_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (member_id == null || member_id.trim().length() == 0) {
				errorMsgs.add("會員編號: 請勿空白");
			} else if(!member_id.trim().matches(member_idReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			String subclass_id = req.getParameter("subclass_id");
			if (subclass_id == null || subclass_id.trim().length() == 0) {
				errorMsgs.add("興趣請勿空白");
			}	
			

			Integer interest_status = new Integer (req.getParameter("interest_status"));
			if(interest_status>1||interest_status<0) {	
				errorMsgs.add("數字請輸入0跟1");
			}
			try {
				interest_status = new Integer(req.getParameter("interest_status").trim());
			} catch (NumberFormatException e) {
				interest_status = (int) 0.0;
							
				errorMsgs.add("狀態請填數字，不可空白");
			}
	
			Member_interestVO member_interestVO = new Member_interestVO();
			member_interestVO.setInterest_id(interest_id);
			member_interestVO.setMember_id(member_id);
			member_interestVO.setSubclass_id(subclass_id);
			member_interestVO.setInterest_status(interest_status);
			

//		 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("member_interestVO", member_interestVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member_interest/update_Member_interest.jsp");
				failureView.forward(req, res);
				return; //程式中斷
	}
			
			/***************************2.開始修改資料*****************************************/
			Member_interestService member_interestSvc = new Member_interestService();
			member_interestVO = member_interestSvc.updateInterest_form(interest_id, member_id, subclass_id, interest_status);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("member_interestVO", member_interestVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/member_interest/listOneMember_interest.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/member_interest/update_Member_interest.jsp");
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
				String member_id = req.getParameter("member_id");
				String member_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if (!member_id.trim().matches(member_idReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String subclass_id = req.getParameter("subclass_id").trim();
				if (subclass_id == null || subclass_id.trim().length() == 0) {
					errorMsgs.add("興趣等於副課程類別，請勿空白");
				}
				Integer interest_status = null;
				try {
					if (req.getParameter("interest_status") != null || req.getParameter("interest_status").length() != 0) {
						interest_status = new Integer(req.getParameter("interest_status").trim());
					}
				} catch (Exception e) {
					errorMsgs.add("狀態為0或1，請勿空白");
				}


				Member_interestVO member_interestVO = new Member_interestVO();
				member_interestVO.setMember_id(member_id);
				member_interestVO.setSubclass_id(subclass_id);
				member_interestVO.setInterest_status(interest_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("member_interestVO", member_interestVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/addMember_interest.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Member_interestService member_interestSvc = new Member_interestService();
				member_interestVO = member_interestSvc.addInterest_form(member_id, subclass_id, interest_status);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/member_interest/listAllMember_interest.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember_interest.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_interest/addMember_interest.jsp");
				failureView.forward(req, res);
			}
		}

//	if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//		List<String> errorMsgs = new LinkedList<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
//		req.setAttribute("errorMsgs", errorMsgs);
//
//		try {
//			/***************************1.接收請求參數***************************************/
//			Integer empno = new Integer(req.getParameter("empno"));
//			
//			/***************************2.開始刪除資料***************************************/
//			EmpService empSvc = new EmpService();
//			empSvc.deleteEmp(empno);
//			
//			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//			String url = "/emp/listAllEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//			successView.forward(req, res);
//			
//			/***************************其他可能的錯誤處理**********************************/
//		} catch (Exception e) {
//			errorMsgs.add("刪除資料失敗:"+e.getMessage());
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/emp/listAllEmp.jsp");
//			failureView.forward(req, res);
//		}
//}

	}
}
