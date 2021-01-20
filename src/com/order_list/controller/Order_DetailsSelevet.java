package com.order_list.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.order_info.model.Order_infoService;
import com.order_info.model.Order_infoVO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;


@WebServlet("/Order_info/backOrder_DetailsSelevet")
public class Order_DetailsSelevet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		

		if ("getOne_For_Display".equals(action)) {
			try {
				String member_id = req.getParameter("member_id");
				Order_listService order_list = new Order_listService();
				Map<Order_infoVO, List<Order_listVO>> order_infoVO1 = order_list.getMemberOrder(member_id);
				req.setAttribute("member_id", member_id);
				req.setAttribute("order_infoVO1", order_infoVO1);
				RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_list/information.jsp");
				diss.forward(req, res);
			} catch (Exception e) {
				RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_list/information.jsp");
				diss.forward(req, res);
			}
		}
		if ("getOne_For_order".equals(action)) {
			try {
				List<Class_infoVO> list=new ArrayList<Class_infoVO>();
				String member_id = req.getParameter("member_id");
				String order_ID = req.getParameter("order_ID");
				Order_infoService order_Sev = new Order_infoService();
				Order_listService order_listsev = new Order_listService();
				Class_infoService Class_infosev = new Class_infoService();
				Order_infoVO order_infoVO = order_Sev.getOneOrder_infoVO(order_ID);
				List<Order_listVO> list1=order_listsev.getOrderInfo(order_ID);
				String purchase_plan=null;
				String Class_id=null;
				String Class_name=null;
				Integer original_price=null;
				Integer startfund_price=null;
				for(Order_listVO list2:list1) {
					purchase_plan=list2.getPurchase_plan();
					Class_id=list2.getClass_id();
					Class_infoVO Class_infosev1=Class_infosev.getOneClass_info(Class_id);
					Class_name=Class_infosev1.getClass_name();
					original_price=Class_infosev1.getOriginal_price();
					startfund_price=Class_infosev1.getStartfund_price();
					Class_infosev1.setStartfund_price(startfund_price);
					Class_infosev1.setClass_name(Class_name);
					Class_infosev1.setOriginal_price(original_price);
					list.add(Class_infosev1);
				}
				
				
				Map<Order_infoVO, List<Order_listVO>> order_infoVO1 = order_listsev.getMemberOrder(member_id);
				Collection<List<Order_listVO>> coll = (Collection<List<Order_listVO>>) order_infoVO1.values();
				
				
		

				Integer Amount = null;
				Integer total = 0;
				Set<Order_infoVO> key = order_infoVO1.keySet();	//≠p∫‚¡`√B
				for (Order_infoVO item : key) {
					Amount = (Integer) item.getAmount();
					total+=Amount;
				}
				session.setAttribute("purchase_plan", purchase_plan);
				
				req.setAttribute("order_infoVO", order_infoVO);
				req.setAttribute("member_id", member_id);
				req.setAttribute("list", list);
				RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_list/Order_Details.jsp");
				diss.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_info/information.jsp");
				diss.forward(req, res);
			}
		}
	}
}
