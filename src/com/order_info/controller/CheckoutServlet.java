package com.order_info.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.class_info.model.Class_infoVO;
import com.member_info.model.Member_infoVO;
import com.order_info.model.Order_infoService;
import com.order_info.model.Order_infoVO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;

@WebServlet("/order_info/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		Member_infoVO member_infoVO = (Member_infoVO) session.getAttribute("member_infoVO");
		LinkedList<Class_infoVO> shoppingcart = (LinkedList<Class_infoVO>) session.getAttribute("shoppingcart");
		List<String>listtest=new ArrayList<String>(); //測試彈跳視窗
		List<String>amounttest=(List<String>) session.getAttribute("amounttest");
		if ("ADD".equals(action)) {
			List<String> error = new ArrayList<String>();
			try {
				String pan_no1 = req.getParameter("pan_no1");
				String pan_no2 = req.getParameter("pan_no2");
				String pan_no3 = req.getParameter("pan_no3");
				String pan_no4 = req.getParameter("pan_no4");
				String pan_no = "[0-9]{4}";
				if (pan_no1.length() == 0 || pan_no2.length() == 0 || pan_no3.length() == 0 || pan_no4.length() == 0) {
					error.add("請輸入卡號");
					pan_no1 = "000";
					pan_no2 = "123";
					pan_no3 = "456";
					pan_no4 = "789";
				} else if (!pan_no1.matches(pan_no) || !pan_no2.matches(pan_no) || !pan_no3.matches(pan_no)
						|| !pan_no4.matches(pan_no)) {
					error.add("卡號請輸入數字");
				}
				String period = req.getParameter("period");
				String period1 = req.getParameter("period1");
				String period2 = "[0-9]{2}";
				String period3 = "[0-9]{4}";
				if (period.length() == 0 || period1.length() == 0) {
					error.add("請輸入卡片有效期限");
				} else if (!period.matches(period2) || !period1.matches(period3)) {
					error.add("卡片有效期限輸入錯誤");
				}
				String Securityc = req.getParameter("Securityc");
				String Security1 = "[0-9]{3}";
				if (Securityc.length() == 0) {
					error.add("請輸入安全碼");
				} else if (!Securityc.matches(Security1)) {
					error.add("安全碼格式錯誤");
				}
//				String account1 = req.getParameter("account1");
//				if (account1.length() == 0) {
//					error.add("請輸入匯款帳號");
//				}
				String name1 = req.getParameter("name1");
				if (name1.length() == 0) {
					error.add("請輸入姓名");
				}
				if (!error.isEmpty()) {
					req.setAttribute("error", error);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order_info/Checkout.jsp");
					failureView.forward(req, res);
					return;
				}
				Integer amount=null;
				System.out.println(amounttest==null);
				if(!(amounttest==null)) {
					amount=new Integer((int) session.getAttribute("amount3"));
					System.out.println(amount);
				}
				else {
					String amount1=(String)session.getAttribute("amount");
					amount=Integer.parseInt(amount1);
				}
				System.out.println(amount);

				Order_infoVO order_infoVO = new Order_infoVO();			
				Order_infoService ordersiv = new Order_infoService();
				String member_id = member_infoVO.getMember_id();
				java.sql.Timestamp order_time = new java.sql.Timestamp(System.currentTimeMillis());
				java.sql.Timestamp payment_time = new java.sql.Timestamp(System.currentTimeMillis());
				String pay_way = req.getParameter("pay_way");
				Integer order_status = 1;
				String coupon_ID = null;
				String purchase_plan = "早鳥";
				listtest.add("購買完成"); //測試彈跳視窗
				order_infoVO = ordersiv.addOrder_infoVO_V2(member_id, order_time, payment_time, pay_way, order_status,
						coupon_ID,amount, shoppingcart, purchase_plan);
				List<Order_listVO> get = new  Order_listService().getMemberClass(member_id);
				session.setAttribute("member_class",get);
				List<Order_listVO> member_class = (List<Order_listVO>)session.getAttribute("member_class");
				System.out.println(member_class);
				shoppingcart.clear();
				req.setAttribute("listtest", listtest);
				RequestDispatcher failureView = req.getRequestDispatcher("/index.jsp");
				failureView.forward(req, res);
			} catch (Exception e) {
				req.setAttribute("error", error);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order_info/Checkout.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		if("oneadd".equals(action)) {
			List<String> errory = new ArrayList<String>();
			try {
				String email=req.getParameter("email");
				email="一件新增";
				errory.add(email);
				session.setAttribute("errory", errory);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order_info/Checkout.jsp");
				failureView.forward(req, res);
			}catch(Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order_info/Checkout.jsp");
				failureView.forward(req, res);
			}
		}
	}

}