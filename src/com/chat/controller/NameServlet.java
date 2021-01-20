package com.chat.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.member_info.model.Member_infoService;
import com.member_info.model.Member_infoVO;

@WebServlet("/chat/NameServlet")
public class NameServlet extends HttpServlet {

	String loginAdminID; // 管理者登入帳號
	String loginUser; // 會員登入帳號
	String loginAdminName;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("進nameservlet");
		HttpSession session = req.getSession();
//		session.setAttribute("memberlist", list);
		// 管理者登入的聊天帳號=管理者登入帳號
		loginAdminID = (String) session.getAttribute("adminAccount");
		loginAdminName = (String) session.getAttribute("adminName");

		// 一般會員登入的聊天帳號=會員登入帳號
		loginUser = (String) session.getAttribute("account");
		String action = req.getParameter("action");

		if ("member-chat".equals(action) && loginUser!=null) {
			System.out.println("會員聊天室啟動");

			req.setAttribute("userName", loginUser);
			req.setAttribute("loginAdminID", loginAdminID);
			req.setAttribute("loginAdminName", loginAdminName);

			System.out.println("會員" + loginUser + "上線聊天");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/member_chat.jsp");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
			dispatcher.forward(req, res);
			return;
		}
//		if ("back-chat".equals(action)) {
//			System.out.println("客服聊天室啟動");
//			req.setAttribute("userName", loginAdminID);
//
//			System.out.println("客服" + loginAdminName + "上線聊天");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/back-end/chat/adminchat.jsp");
//			dispatcher.forward(req, res);
//			return;
//		} 
		else {
			System.out.println("loginUser==null");
			res.sendRedirect(req.getContextPath() + "/front-end/sign_in/sign_in.jsp");
			return;
		}

	}
}
