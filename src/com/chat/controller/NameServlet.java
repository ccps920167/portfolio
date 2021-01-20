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

	String loginAdminID; // �޲z�̵n�J�b��
	String loginUser; // �|���n�J�b��
	String loginAdminName;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		System.out.println("�inameservlet");
		HttpSession session = req.getSession();
//		session.setAttribute("memberlist", list);
		// �޲z�̵n�J����ѱb��=�޲z�̵n�J�b��
		loginAdminID = (String) session.getAttribute("adminAccount");
		loginAdminName = (String) session.getAttribute("adminName");

		// �@��|���n�J����ѱb��=�|���n�J�b��
		loginUser = (String) session.getAttribute("account");
		String action = req.getParameter("action");

		if ("member-chat".equals(action) && loginUser!=null) {
			System.out.println("�|����ѫǱҰ�");

			req.setAttribute("userName", loginUser);
			req.setAttribute("loginAdminID", loginAdminID);
			req.setAttribute("loginAdminName", loginAdminName);

			System.out.println("�|��" + loginUser + "�W�u���");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/member_chat.jsp");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
			dispatcher.forward(req, res);
			return;
		}
//		if ("back-chat".equals(action)) {
//			System.out.println("�ȪA��ѫǱҰ�");
//			req.setAttribute("userName", loginAdminID);
//
//			System.out.println("�ȪA" + loginAdminName + "�W�u���");
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
