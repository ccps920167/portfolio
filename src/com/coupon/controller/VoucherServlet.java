package com.coupon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/coupon/VoucherServlet")
public class VoucherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		List<String>amounttest=new ArrayList<String>();
		try {
			if ("VoucherServlet".equals(action)) {
				List<String> coupon = new ArrayList<String>();
				coupon.add("使用優惠卷");
				req.setAttribute("coupon", coupon);
				RequestDispatcher rd = req.getRequestDispatcher("/front-end/member_info/coupon.jsp");
				rd.forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if ("coupon".equals(action)) {
				Integer coupon_amount = new Integer(req.getParameter("coupon_amount"));
				String amount1 = (String) session.getAttribute("amount");
				Integer amount2 = Integer.parseInt(amount1);
				Integer amount3 = amount2 -= coupon_amount;
				amounttest.add("存入優惠價錢");
				session.setAttribute("amounttest", amounttest);
				session.setAttribute("amount3", amount3);
				System.out.println(amount3);
				RequestDispatcher rd = req.getRequestDispatcher("/front-end/order_info/Checkout.jsp");
				rd.forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}
