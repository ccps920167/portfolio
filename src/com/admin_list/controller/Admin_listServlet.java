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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("admin_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_list/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String admin_id = null;
				try {
					admin_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_list/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Admin_listService admin_listSvc = new Admin_listService();
				Admin_listVO admin_listVO = admin_listSvc.getOneAdmin_list(admin_id);
				if (admin_listVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_list/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("admin_listVO", admin_listVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/admin_list/listOneAdmin_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin_list/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String admin_id = new String(req.getParameter("admin_id"));
				
				/***************************2.開始查詢資料****************************************/
				Admin_listService admin_listSvc = new Admin_listService();
				Admin_listVO admin_listVO = admin_listSvc.getOneAdmin_list(admin_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("admin_listVO", admin_listVO);         
				String url = "/back-end/admin_list/update_admin_list_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin_list/listAllAdmin_list.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String admin_id = new String(req.getParameter("admin_id").trim());
				
				String admin_name = req.getParameter("admin_name");
				String admin_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (admin_name == null || admin_name.trim().length() == 0) {
					errorMsgs.add("管理員名稱: 請勿空白");
				} else if(!admin_name.trim().matches(admin_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				String admin_pwd = req.getParameter("admin_pwd");
				String admin_pwdReg = "^[(a-zA-Z0-9_)]{6,12}$";
				if (admin_pwd == null || admin_pwd.trim().length() == 0) {
					errorMsgs.add("管理員密碼: 請勿空白");
				} else if(!admin_pwd.trim().matches(admin_pwdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員密碼:只能是英文字母、數字和_ , 且長度必需在6到12之間");
	            }
				
				String admin_status = req.getParameter("admin_status");
				if (admin_status == null
						|| admin_status.trim().length() == 0) {
					admin_status="0";
					errorMsgs.add("管理員狀態: 請勿空白");
				}
				
				Admin_listService admin_listSvc = new Admin_listService();
				Admin_listVO admin_listVO = new Admin_listVO();
				admin_listVO.setAdmin_id(admin_id);
				admin_listVO.setAdmin_name(admin_name);
				admin_listVO.setAdmin_pwd(admin_pwd);
				admin_listVO.setAdmin_status(Integer.valueOf(admin_status));


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("admin_listVO", admin_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/admin_list/update_admin_list_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				
				admin_listVO = admin_listSvc.updateAdmin_list(admin_id, admin_name, admin_pwd, Integer.valueOf(admin_status));
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admin_listVO", admin_listVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/admin_list/listOneAdmin_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin_list/update_admin_list_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		if ("insert".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String admin_name = req.getParameter("admin_name");
				String admin_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
				if (admin_name == null || admin_name.trim().length() == 0) {
					errorMsgs.add("管理員名稱: 請勿空白");
				} else if(!admin_name.trim().matches(admin_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員名稱: 只能是中、英文字母和_ , 且長度必需在2到10之間");
	            }
				
				
				String admin_pwd = req.getParameter("admin_pwd");
				String admin_pwdReg = "^[(a-zA-Z0-9_)]{6,12}$";
				if (admin_pwd == null || admin_pwd.trim().length() == 0) {
					errorMsgs.add("管理員密碼: 請勿空白");
				} else if(!admin_pwd.trim().matches(admin_pwdReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("管理員密碼:只能是英文字母、數字和_ , 且長度必需在6到12之間");
	            }
				
				Integer admin_status = Integer.valueOf(req.getParameter("admin_status"));
				if (req.getParameter("admin_status") == null
						|| (req.getParameter("admin_status")).trim().length() == 0) {
					errorMsgs.add("管理員狀態: 請勿空白");
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
					req.setAttribute("admin_listVO", admin_listVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/admin_list/addAdmin_list.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Admin_listService dao = new Admin_listService();
				dao.addWithAdmin_list(admin_listVO, admin_lstList);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("admin_listVO", admin_listVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/admin_list/listAllAdmin_list.jsp";
							  
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin_list/addAdmin_list.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String admin_id = new String(req.getParameter("admin_id"));
				
				/***************************2.開始刪除資料***************************************/
				Admin_listService admin_listSvc = new Admin_listService();
				admin_listSvc.deleteAdmin_list(admin_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/admin_list/listAllAdmin_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/admin_list/listAllAdmin_list.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
