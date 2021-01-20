package com.admin_list.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin_auth.model.Admin_authService;
import com.admin_auth.model.Admin_authVO;
import com.admin_list.model.Admin_listService;
import com.admin_list.model.Admin_listVO;
import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.login_history.model.Login_historyService;
import com.login_history.model.Login_historyVO;
import com.member_info.model.Member_infoService;
import com.member_info.model.Member_infoVO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;
import com.video_record.model.Video_recordService;
import com.video_record.model.Video_recordVO;

@WebServlet("/admin_list/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String checkadmin;
	String checkadminpwd;
	String checkadminname;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	protected boolean allowUser(String account, String password) {
//		System.out.println("捕捉使用者帳密");
//		System.out.println(account);
//		System.out.println(password);

		if (account.equals(checkadmin) && password.equals(checkadminpwd))
			return true;
		else
			return false;
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		// action=login登入
		if ("adminlogin".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
//System.out.println("3");
				/*************************** 1.接收請求參數 ****************************************/
				String account = new String(req.getParameter("adminAccount"));
				String password = new String(req.getParameter("adminPassword"));
				if (account == null || (account.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}

				if (password == null || (password.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				if (!errorMsgs.isEmpty()) {
//System.out.println("1");
					RequestDispatcher failureView = req.getRequestDispatcher("/index-back-login.jsp");
					failureView.forward(req, res);
					return;
				} // 程式中斷

				/*************************** 2.開始查詢資料 ****************************************/
                Admin_listService admin_listSvc = new Admin_listService();
                Admin_listVO admin_listVO = admin_listSvc.getOneAdmin_list(account);
                checkadmin = admin_listVO.getAdmin_id();
                checkadminpwd = admin_listVO.getAdmin_pwd();
                checkadminname = admin_listVO.getAdmin_name();
				// 【檢查該帳號 , 密碼是否有效】
                
                if (!allowUser(account, password)) { // 【帳號 , 密碼無效時】
					out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
					out.println("<BODY>你的帳號 , 密碼無效!<BR>");
					out.println("請按此重新登入 <A HREF=" + req.getContextPath() + "/index-back-login.jsp>重新登入</A>");
					out.println("</BODY></HTML>");
				} else { // 【帳號 , 密碼有效時, 才做以下工作】
					HttpSession session = req.getSession();
					session.setAttribute("administrator",1);
					session.setAttribute("admin_id",account);
					session.setAttribute("adminAccount",checkadmin);// for聊天室用帳號
					session.setAttribute("adminName",checkadminname);// for聊天室用名稱
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/index-back.jsp");
					failureView.forward(req, res);
	                List<Admin_authVO> admin_authMapList = new Admin_authService().getOneAdmin_auth(admin_listVO.getAdmin_id());
	                session.setAttribute("admin_auth",admin_authMapList );
				}
                

			} catch (Exception e) {
//System.out.println("2");
				errorMsgs.add("無此帳號，請先註冊" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/index-back-login.jsp");
				failureView.forward(req, res);
			}
		}
		// action=logout登出
		if ("adminLoginOut".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("administrator");
			session.removeAttribute("admin_auth");
			session.removeAttribute("admin_id");
			session.invalidate();
			res.sendRedirect(req.getContextPath() + "/index-back-login.jsp");
		}
	}


}