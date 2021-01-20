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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("login_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入登入歷史紀錄編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login_history/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String login_id = null;
				try {
					login_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("登入歷史紀錄編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login_history/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Login_historyService login_historySvc = new Login_historyService();
				Login_historyVO login_historyVO = login_historySvc.getOneLogin_history(login_id);
				if (login_historyVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login_history/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("login_historyVO", login_historyVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/login_history/listOneLogin_history.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login_history/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String login_id = new String(req.getParameter("login_id"));
				
				/***************************2.開始查詢資料****************************************/
				Login_historyService login_historySvc = new Login_historyService();
				Login_historyVO login_historyVO = login_historySvc.getOneLogin_history(login_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("login_historyVO", login_historyVO);         
				String url = "/back-end/login_history/update_login_history_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/login_history/listAllLogin_history.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String login_id = new String(req.getParameter("login_id").trim());
				
				java.sql.Timestamp login_time = null;
				try {
					login_time = java.sql.Timestamp.valueOf(req.getParameter("login_time").trim());
				} catch (IllegalArgumentException e) {
					login_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String login_arrange = req.getParameter("login_arrange");
				String login_arrangeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (login_arrange == null || login_arrange.trim().length() == 0) {
					errorMsgs.add("登入歷史紀錄: 請勿空白");
				} else if(!login_arrange.trim().matches(login_arrangeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("登入歷史紀錄: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String login_ip = req.getParameter("login_ip");
				String login_ipReg = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
				if (login_ip == null || login_ip.trim().length() == 0) {
					errorMsgs.add("登入IP: 請勿空白");
				} else if(!login_ip.trim().matches(login_ipReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("登入IP: 格式為XXX.XXX.XXX.XXX且只能填數字");
	            }
				
				
				String member_id = req.getParameter("member_id");
				String member_idReg = "[M][E][M]\\d{5}";
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if(!member_id.trim().matches(member_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 格式為MEMXXXXX,且後五位為數字");
	            }

				
				

				Login_historyVO login_historyVO = new Login_historyVO();
				login_historyVO.setLogin_id(login_id);
				login_historyVO.setLogin_time(login_time);;
				login_historyVO.setLogin_arrange(login_arrange);
				login_historyVO.setLogin_ip(login_ip);
				login_historyVO.setMember_id(member_id);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("login_historyVO", login_historyVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/login_history/update_login_history_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Login_historyService login_historySvc = new Login_historyService();
				login_historyVO = login_historySvc.update(login_id, login_time, login_arrange, login_ip, member_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("login_historyVO", login_historyVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/login_history/listOneLogin_history.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/login_history/update_login_history_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("insert".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				java.sql.Timestamp login_time = null;
				try {
					login_time = java.sql.Timestamp.valueOf(req.getParameter("login_time").trim());
				} catch (IllegalArgumentException e) {
					login_time=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String login_arrange = req.getParameter("login_arrange");
				String login_arrangeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (login_arrange == null || login_arrange.trim().length() == 0) {
					errorMsgs.add("登入歷史紀錄裝置: 請勿空白");
				} else if(!login_arrange.trim().matches(login_arrangeReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("登入歷史紀錄裝置: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String login_ip = req.getParameter("login_ip");
				String login_ipReg = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
				if (login_ip == null || login_ip.trim().length() == 0) {
					errorMsgs.add("登入IP: 請勿空白");
				} else if(!login_ip.trim().matches(login_ipReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("登入IP: 格式為XXX.XXX.XXX.XXX且只能填數字");
	            }
				
				
				String member_id = new String (req.getParameter("member_id").trim());

				Login_historyVO login_historyVO = new Login_historyVO();
				login_historyVO.setLogin_time(login_time);
				login_historyVO.setLogin_arrange(login_arrange);
				login_historyVO.setLogin_ip(login_ip);
				login_historyVO.setMember_id(member_id);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("login_historyVO", login_historyVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/login_history/addLogin_history.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Login_historyService login_historySvc = new Login_historyService();
				login_historyVO = login_historySvc.add(login_time, login_arrange, login_ip, member_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("login_historyVO", login_historyVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/login_history/listAllLogin_history.jsp";
							  
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/login_history/addLogin_history.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String login_id = new String(req.getParameter("login_id"));
				
				/***************************2.開始刪除資料***************************************/
				Login_historyService login_historySvc = new Login_historyService();
				login_historySvc.delete(login_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/login_history/listAllLogin_history.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/login_history/listAllLogin_history.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
