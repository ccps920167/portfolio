package com.main_message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main_message.model.Main_messageService;
import com.main_message.model.Main_messageVO;



@WebServlet("/Main_message/MainMsgServlet")
public class Main_message_front_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
//doGet	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
//doPost
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// 取得來源參數
		System.out.println(req.getQueryString());

		String action = req.getParameter("action");
		System.out.println(action);
		String class_id = req.getParameter("class_id");
		String member_id = req.getParameter("member_id");
		String mainmsg_text = req.getParameter("mainmsg_text");
		String msg_source = req.getParameter("msg_source");
// 來自addMainMessage.jsp的請求 (insert)
		if ("insert".equals(action)) { // 來自addMainMessage.jsp的請求
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				//參數
				System.out.println(class_id);
				System.out.println(member_id);
				System.out.println(mainmsg_text);
				System.out.println(msg_source);
				/*************************** 2.開始新增資料 ***************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				long data = new Date().getTime();
				java.sql.Timestamp mainmsg_time = new Timestamp(data);
				main_messageSvc.addMain_message(class_id, member_id,mainmsg_time, mainmsg_text, msg_source,1);
				
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}


	}

}