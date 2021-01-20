package com.guest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.class_chapter.model.Class_chapterVO;
import com.class_info.model.Class_infoService;
import com.member_info.model.Member_infoService;
import com.member_info.model.Member_infoVO;
import com.order_info.model.Order_infoVO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;
import com.view_class_income.model.View_class_incomeService;
import com.view_class_income.model.View_class_incomeVO;

@WebServlet("/guest/guestMember_Servlet")
@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024, maxFileSize = 30 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024
		* 1024)
public class Member_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
//		Member_infoVO Member_infoVO = (Member_infoVO)session.getAttribute("member_infoVO");
//		
//		String member_id = req.getParameter("member_id");
		
		if("member_infoPage".equals(action)){
			System.out.println("action=member_infoPage");
//			req.setAttribute("member_id", member_id); // 資料庫取出的empVO物件,存入req
//			System.out.println(member_id);
//			Member_infoService member_infoSvc = new Member_infoService();
//			Member_infoVO member_infoVO = member_infoSvc.getOneMember_info(member_id);
		
//			req.setAttribute("member_infoVO", member_infoVO);
//			String url = "/front-end/member_info/listOneMember_info.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);
			
			//===========
			String member_id=req.getParameter("member_id");
			req.setAttribute("member_id", member_id);
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/front-end/guest/memberHomepage.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
		
		if("member_getinfoPage".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_info/listOneMember_info.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
		
		if("member_coupon".equals(action)){
			List<String> coupon = new ArrayList<String>();
			coupon.add("使用優惠卷");
			req.setAttribute("coupon", coupon);
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_info/coupon.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
		
//		if("member_income".equals(action)){
//			//有收益的課程
//			List<View_class_incomeVO> income_list = (List<View_class_incomeVO>) new View_class_incomeService().getMemberAll(Member_infoVO.getMember_id());
//			List<Map<String,String>> getOrder_list = null;
////			//傳送List
//			Map<View_class_incomeVO , List<Map<String,String>>> income = new HashMap<View_class_incomeVO,List<Map<String,String>>>();
//			for(View_class_incomeVO item :income_list) {
//				getOrder_list = new Order_listService().getClassID(item.getClass_id());
//				income.put(item,getOrder_list);
//			}
//			req.setAttribute("income", income);
//    	   	RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_info/member_income.jsp");
//    	   	failureView.forward(req, res);
//			return;// 程式中斷
//		}
		
	}

}
