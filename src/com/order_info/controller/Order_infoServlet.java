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

import com.order_info.model.Order_infoService;
import com.order_info.model.Order_infoVO;


@WebServlet("/order_info/Order_infoServlet")
public class Order_infoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) {
			List<String> error = new ArrayList<String>();
			req.setAttribute("error", error);
			try {
				String orderinfoVO1 = req.getParameter("orderinfoVO1");
				String orderinfoVO2 = "[O][I][D][0-9]{5}";
				if (orderinfoVO1 == null || orderinfoVO1.trim().length() == 0) {
					error.add("�Фſ�J�ť�");
				} else if (!orderinfoVO1.matches(orderinfoVO2)) {
					error.add("�榡���~");
				}
				if (!error.isEmpty()) {
					RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_info/select-page.jsp");
					diss.forward(req, res);
					return;
				}
				Order_infoService oder_infoService = new Order_infoService();
				Order_infoVO order_infoVO = oder_infoService.getOneOrder_infoVO(orderinfoVO1);
				if (order_infoVO == null) {
					error.add("�d�L���");
				}
				if (!error.isEmpty()) {
					RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_info/select-page.jsp");
					diss.forward(req, res);
					return;
				}
				req.setAttribute("order_infoVO", order_infoVO);
				RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_info/listOneOrder_info.jsp");
				diss.forward(req, res);
			} catch (Exception e) {
				error.add("�Э��s��J");
				RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_info/select-page.jsp");
				diss.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> error = new ArrayList<String>();
			req.setAttribute("error", error);
			try {
				String member_id = req.getParameter("member_id");
				String member_id1 = "[M][E][M][0-9]{5}";
				if (!member_id.matches(member_id1)) {
					error.add("�|���s����O���~");
				} else if (member_id == null || member_id.trim().length() == 0) {
					error.add("�|���s���Фſ�J�ť�");
				}
				java.sql.Timestamp order_time = null;
				try {
					order_time = java.sql.Timestamp.valueOf(req.getParameter("Order_time"));
				} catch (Exception e) {
					order_time = new java.sql.Timestamp(System.currentTimeMillis());
					error.add("�q��ɶ��榡����");
				}
				java.sql.Timestamp payment_time = null;
				try {
					payment_time = java.sql.Timestamp.valueOf(req.getParameter("payment_time"));
				} catch (Exception e) {
					payment_time = new java.sql.Timestamp(System.currentTimeMillis());
					error.add("�I�ڮɶ��榡����");
				}
				String pay_way = null;
				try {
					pay_way = req.getParameter("pay_way");
				} catch (Exception e) {
					error.add("�Э��s��J�I�ڤ覡");
				}
				Integer order_status = null;
				try {
					order_status = new Integer(req.getParameter("order_status"));
				} catch (NumberFormatException e) {
					order_status = 1;
					error.add("�q�檬�A�ж�g1��2");
				}

				String coupon_ID = req.getParameter("coupon_ID");
				String coupon_ID2 = "[C][O][U][0-9]{5}";
				if (!coupon_ID.matches(coupon_ID2)) {
					coupon_ID = "COU00001";
					error.add("�u�f��s���榡���~");
				} else if (coupon_ID == null || coupon_ID.trim().length() == 0) {
					coupon_ID = "COU00001";
					error.add("�u�f��s���Фſ�J�ť�");
				}

				Order_infoVO order_infoVO = new Order_infoVO();
				order_infoVO.setMember_id(member_id);
				order_infoVO.setOrder_time(order_time);
				order_infoVO.setPayment_time(payment_time);
				order_infoVO.setPay_way(pay_way);
				order_infoVO.setOrder_status(order_status);
				order_infoVO.setCoupon_ID(coupon_ID);
				if (!error.isEmpty()) {
					req.setAttribute("order_infoVO", order_infoVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_info/addorder_info.jsp");
					failureView.forward(req, res);
					return;
				}
				Order_infoService order_infosvc = new Order_infoService();
				order_infoVO = order_infosvc.addOrder_info(member_id, order_time, payment_time, pay_way, order_status,
						coupon_ID);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_info/listAllorder_info.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				error.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_info/addorder_info.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {
			List<String> error = new ArrayList<String>();
			req.setAttribute("error", error);

			try {
				String Order = req.getParameter("order");
				Order_infoService Order_infosvc = new Order_infoService();
				Order_infosvc.deleorder_info(Order);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_info/listAllorder_info.jsp");

				successView.forward(req, res);
			} catch (Exception e) {

				error.add("�R������");
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_info/listAllorder_info.jsp");
				successView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) {
			List<String> error = new LinkedList<String>();
			req.setAttribute("error", error);
			try {
				String order_ID = req.getParameter("order_ID");
				Order_infoService order_infosvc = new Order_infoService();
				Order_infoVO order_infoVO = order_infosvc.getOneOrder_infoVO(order_ID);
				req.setAttribute("order_infoVO", order_infoVO);
				String url = "/back-end/order_info/updateorder_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				error.add("�L�k���o�n�ק諸���:");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_info/listAllorder_info.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {
			
			List<String> error = new ArrayList<String>();
			req.setAttribute("error", error);
			try {
				String order_ID = req.getParameter("order_ID");
				String member_id = req.getParameter("member_id");
				String member_id1 = "[M][E][M][0-9]{5}";
				if (!member_id.matches(member_id1)) {
					error.add("�|���s����O���~");
				} else if (member_id == null || member_id.trim().length() == 0) {
					error.add("�|���s���Фſ�J�ť�");
				}
				java.sql.Timestamp order_time = null;
				try {
					order_time = java.sql.Timestamp.valueOf(req.getParameter("rder_time"));
				} catch (Exception e) {
					order_time = new java.sql.Timestamp(System.currentTimeMillis());
					error.add("�q��ɶ��榡����");
				}
				java.sql.Timestamp payment_time = null;
				try {
					payment_time = java.sql.Timestamp.valueOf(req.getParameter("payment_time"));
				} catch (Exception e) {
					payment_time = new java.sql.Timestamp(System.currentTimeMillis());
					error.add("�I�ڮɶ��榡����");
				}
				String pay_way = null;
				try {
					pay_way = req.getParameter("pay_way");
				} catch (Exception e) {
					error.add("�Э��s��J�I�ڤ覡");
				}
				Integer order_status = null;
				try {
					order_status = new Integer(req.getParameter("order_status"));
				} catch (NumberFormatException e) {
					order_status = 1;
					error.add("�q�檬�A�ж�g1��2");
				}
				String coupon_ID = req.getParameter("coupon_ID");
				String coupon_ID2 = "[C][O][U][0-9]{5}";
				if (!coupon_ID.matches(coupon_ID2)) {
					coupon_ID = "COU00001";
					error.add("�u�f��s���榡���~");
				} else if (coupon_ID == null || coupon_ID.trim().length() == 0) {
					coupon_ID = "COU00001";
					error.add("�u�f��s���Фſ�J�ť�");
				}
				Order_infoVO order_infoVO = new Order_infoVO();
				order_infoVO.setOrder_ID(order_ID);
				order_infoVO.setMember_id(member_id);
				order_infoVO.setOrder_time(order_time);
				order_infoVO.setPayment_time(payment_time);
				order_infoVO.setPay_way(pay_way);
				order_infoVO.setOrder_status(order_status);
				order_infoVO.setCoupon_ID(coupon_ID);
				if (!error.isEmpty()) {
					req.setAttribute("order_infoVO", order_infoVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_info/updateorder_info.jsp");
					failureView.forward(req, res);
					return;
				}
				Order_infoService order_infosvc = new Order_infoService();
				order_infoVO = order_infosvc.addOrder_info(member_id, order_time, payment_time, pay_way, order_status,
						coupon_ID);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_info/listAllorder_info.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				error.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_info/updateorder_info.jsp");
				failureView.forward(req, res);
			}
		}

	}
}
