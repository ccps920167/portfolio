package com.order_list.controller;

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
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;

@WebServlet("/Order_list/Order_listServlet")
public class Order_listServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) {
			List<String> error = new ArrayList<String>();
			req.setAttribute("error", error);
			try {
				String order_list = req.getParameter("order_list");
				String order_list1 = "[O][L][0-9]{5}";
				if (order_list == null || order_list.trim().length() == 0) {
					error.add("�Фſ�J�ť�");
				} else if (!order_list.matches(order_list1)) {
					error.add("�q����Ӯ榡���~");
				}
				if (!error.isEmpty()) {
					RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_list/select-page.jsp");
					diss.forward(req, res);
					return;
				}
				Order_listService oder_listService = new Order_listService();
				Order_listVO Order_listVO = oder_listService.getOneorder_list(order_list);
				if (Order_listVO == null) {
					error.add("�d�L���");
				}
				if (!error.isEmpty()) {
					RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_list/select-page.jsp");
					diss.forward(req, res);
					return;
				}
				req.setAttribute("Order_listVO", Order_listVO);
				RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_list/listOneOrder_list.jsp");
				diss.forward(req, res);
			} catch (Exception e) {
				error.add("�Э��s��J");
				RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_list/select-page.jsp");
				diss.forward(req, res);
			}
		}
		if ("insert".equals(action)) {
			List<String> error = new LinkedList<String>();
			req.setAttribute("error", error);
			try {
				String order_id = req.getParameter("order_id");
				String order_id1 = "[O][I][D][0-9]{5}";
				if (order_id == null || order_id.trim().length() == 0) {
					order_id = "OID00001";
					error.add("�п�J�q��s��");
				} else if (!order_id.matches(order_id1)) {
					error.add("�q��s���榡��J���~");
				}

				String class_id = req.getParameter("class_id");
				String class_id1 = "[C][L][A][0-9]{5}";
				if (class_id == null || class_id.trim().length() == 0) {
					class_id = "CLA00001";
					error.add("�п�J�ҵ{�s��");
				} else if (!class_id.matches(class_id1)) {
					error.add("�ҵ{�s���榡��J���~");
				}
				String purchase_plan = null;
				try {
					purchase_plan = req.getParameter("purchase_plan");
				} catch (Exception e) {
					error.add("�Э��s���");
				}
				Order_listVO order_listVO = new Order_listVO();
				order_listVO.setOrder_id(order_id);
				order_listVO.setClass_id(class_id);
				order_listVO.setPurchase_plan(purchase_plan);

				if (!error.isEmpty()) {
					req.setAttribute("order_listVO", order_listVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_list/addorder_list.jsp");
					failureView.forward(req, res);
					return;
				}
				Order_listService order_listsvc = new Order_listService();
				order_listVO = order_listsvc.addOrder_list(order_id, class_id, purchase_plan);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_list/listAllorder_list.jsp"); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				error.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_list/addorder_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) {
			List<String> error = new ArrayList<String>();
			req.setAttribute("error", error);

			try {
				String Order = req.getParameter("order");
				Order_listService Order_listsvc = new Order_listService();
				Order_listsvc.deleteOrder_list(Order);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_list/listAllorder_list.jsp");

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
				String order_list_id = req.getParameter("order_list_id");
				Order_listService order_listsvc = new Order_listService();
				Order_listVO order_listVO = order_listsvc.getOneorder_list(order_list_id);
				req.setAttribute("order_listVO", order_listVO);
				String url = "/back-end/order_list/updateorder_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				error.add("�L�k���o�n�ק諸���:");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_list/listAllorder_list.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {

			List<String> error = new ArrayList<String>();
			req.setAttribute("error", error);
			try {
				String order_list_id = req.getParameter("order_list_id");
				String order_id = req.getParameter("order_id");
				String order_id1 = "[O][I][D][0-9]{5}";
				if (order_id == null || order_id.trim().length() == 0) {
					order_id = "OID00001";
					error.add("�п�J�q��s��");
				} else if (!order_id.matches(order_id1)) {
					error.add("�q��s���榡��J���~");
				}

				String class_id = req.getParameter("class_id");
				String class_id1 = "[C][L][A][0-9]{5}";
				if (class_id == null || class_id.trim().length() == 0) {
					class_id = "CLA00001";
					error.add("�п�J�ҵ{�s��");
				} else if (!class_id.matches(class_id1)) {
					error.add("�ҵ{�s���榡��J���~");
				}
				String purchase_plan = null;
				try {
					purchase_plan = req.getParameter("purchase_plan");
				} catch (Exception e) {
					error.add("�Э��s���");
				}
				Order_listVO order_listVO = new Order_listVO();
				order_listVO.setOrder_id(order_id);
				order_listVO.setClass_id(class_id);
				order_listVO.setPurchase_plan(purchase_plan);

				if (!error.isEmpty()) {
					req.setAttribute("order_listVO", order_listVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_list/updateorder_list.jsp");
					failureView.forward(req, res);
					return;
				}
				Order_listService order_listsvc = new Order_listService();
				order_listVO = order_listsvc.updateOrder_listVO(order_list_id, order_id, class_id, purchase_plan);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/order_list/listAllorder_list.jsp"); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				error.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_list/updateorder_list.jsp");
				failureView.forward(req, res);
			}
		}

	}
}