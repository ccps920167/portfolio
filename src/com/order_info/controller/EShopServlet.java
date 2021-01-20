package com.order_info.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;

@WebServlet("/Order_info/EShopServlet")
public class EShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		LinkedList<Class_infoVO> shoppingcart = (LinkedList<Class_infoVO>) session.getAttribute("shoppingcart");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 進入購物車
		if (action.equals("goToCar")) {
			// 如果未有購物車內容
			if (shoppingcart == null || shoppingcart.isEmpty()||shoppingcart.size()==0) {
				String amount = "0";
				session.setAttribute("amount", amount);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/order_info/CartNoitem.jsp");
				failureView.forward(req, res);
				return;
			} else {
				// 計算價格
				int total = 0;
				// 如果未有購物車內容
				if (shoppingcart.size() == 0) {
					String amount = "0";
					session.setAttribute("amount", amount);
				}
				for (int i = 0; i < shoppingcart.size(); i++) {
					int price =0;
					Class_infoVO inf = shoppingcart.get(i);
					if(inf.getClass_status()==1) 
						price = inf.getStartfund_price();
					else if (inf.getClass_status()==4)
						price = inf.getOriginal_price();
					total += price;
					String amount = String.valueOf(total);
					session.setAttribute("amount", amount);
				}
				// 已經有購物車內容
				session.setAttribute("shoppingcart", shoppingcart);
				RequestDispatcher rd = req.getRequestDispatcher("/front-end/order_info/Cart1.jsp");
				rd.forward(req, res);
				return;
			}
		}

		// 馬上購買
		if (action.equals("carbutNow")) {
			// 直接加入購物車
			boolean match = false;
			String class_id = req.getParameter("class_id");
			Class_infoVO class_info = new Class_infoService().getOneClass_info(class_id);
			if (shoppingcart == null) {
				shoppingcart = new LinkedList<Class_infoVO>();
				shoppingcart.add(class_info);
			} else {
				for (int i = 0; i < shoppingcart.size(); i++) {
					Class_infoVO class_infoVO1 = shoppingcart.get(i);
					// 如果有相等的課程
					if (class_infoVO1.getClass_id().equals(class_info.getClass_id())) {
						shoppingcart.set(i, class_infoVO1);
						match = true;
					}
				}
				if (!match) {
					shoppingcart.add(class_info);
				}
			}
			// 計算價格
			int total = 0;
			// 如果未有購物車內容
			if (shoppingcart.size() == 0) {
				String amount = "0";
				session.setAttribute("amount", amount);
			}
			for (int i = 0; i < shoppingcart.size(); i++) {
				int price =0;
				Class_infoVO inf = shoppingcart.get(i);
				if(inf.getClass_status()==1) 
					price = inf.getStartfund_price();
				else if (inf.getClass_status()==4)
					price = inf.getOriginal_price();
				total += price;
				String amount = String.valueOf(total);
				session.setAttribute("amount", amount);
			}
			session.setAttribute("shoppingcart", shoppingcart);
			RequestDispatcher rd = req.getRequestDispatcher("/front-end/order_info/Cart1.jsp");
			rd.forward(req, res);
			return;
		}

		// 刪除項目
		if (action.equals("DELETE")) {

			String del = req.getParameter("del");
			for (Class_infoVO item : shoppingcart) {
				if (del.equals(item.getClass_name())) {
					shoppingcart.remove(item);
					break;
				}
			}
			int total = 0;
			if (shoppingcart.size() == 0) {
				String amount = "0";
				session.setAttribute("amount", amount);
			}
			for (int i = 0; i < shoppingcart.size(); i++) {
				int price =0;
				Class_infoVO inf = shoppingcart.get(i);
				if(inf.getClass_status()==1) 
					price = inf.getStartfund_price();
				else if (inf.getClass_status()==4)
					price = inf.getOriginal_price();
				total += price;
				String amount = String.valueOf(total);
				session.setAttribute("amount", amount);
			}

			session.setAttribute("shoppingcart", shoppingcart);
			RequestDispatcher rd = req.getRequestDispatcher("/front-end/order_info/Cart1.jsp");
			rd.forward(req, res);
			return;
		}
		// 加入購物車
		if (action.equals("ADD")) {
			boolean match = false;
			String class_id = req.getParameter("class_id");
			Class_infoVO class_info = new Class_infoService().getOneClass_info(class_id);
			if (shoppingcart == null) {
				shoppingcart = new LinkedList<Class_infoVO>();
				shoppingcart.add(class_info);
				session.setAttribute("shoppingcart", shoppingcart);
			} else {
				for (int i = 0; i < shoppingcart.size(); i++) {
					Class_infoVO class_infoVO1 = shoppingcart.get(i);
					// 如果有相等的課程
					if (class_infoVO1.getClass_id().equals(class_info.getClass_id())) {
						shoppingcart.set(i, class_infoVO1);
						match = true;
						return;
					}
				}
				if (!match) {
					shoppingcart.add(class_info);
					return;
				}
			}

		}
	}
}
