package com.forward.back;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.allClass_info.model.AllClass_infoService;
import com.allClass_info.model.AllClass_infoVO;
import com.class_chapter.model.Class_chapterService;
import com.class_chapter.model.Class_chapterVO;
import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.class_unit.model.Class_unitService;
import com.class_unit.model.Class_unitVO;
import com.member_info.model.Member_infoVO;
import com.order_info.model.Order_infoService;
import com.order_info.model.Order_infoVO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;
import com.view_class_income.model.View_class_incomeService;
import com.view_class_income.model.View_class_incomeVO;

/**
 * Servlet implementation class Class_infoServlet
 */
@WebServlet(
		urlPatterns = { "/forward/backSrevlet" })
public class backSrevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		Member_infoVO Member_infoVO = (Member_infoVO)session.getAttribute("member_infoVO");

/*********************審核課程**********************/

		/*********************訊息管理**********************/
//		公告管理
		if("listAllPostMessage".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/post_message/BEpostContext.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
		
//		聊天室
		if("back-chat".equals(action)){
			
			// 管理者登入的聊天帳號=管理者登入帳號
			String loginAdminID = (String) session.getAttribute("adminAccount");
			String loginAdminName = (String) session.getAttribute("adminName");


			System.out.println("客服聊天室啟動");
			req.setAttribute("userName", loginAdminID);

			System.out.println("客服" + loginAdminName + "上線聊天");
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chat/adminchat.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		} 
			
		
		/*********************首頁內容**********************/
//		廣告與關鍵字
		if("back-Keyword_formAll".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
		/*********************課程管理**********************/
//		新增課程
		if("back-addClass_info".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/back-addClass_info.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
//		課程列表
		if("back-listAllClass_info".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/verify_list/back-verify_listAll.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
		
		if("back-listAllClass_info-Details".equals(action)){
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				String class_id = req.getParameter("class_id");
				
				/***************************2.開始查詢資料****************************************/
				AllClass_infoService aciSrv = new AllClass_infoService(); 
				AllClass_infoVO AllClass_infoVO = aciSrv.getAll(class_id);
							
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				session.setAttribute("AllClass_infoVO", AllClass_infoVO);         // 資料庫取出的Class_infoVO物件,存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/back-listAllClass_info-Details.jsp");
	    	   	failureView.forward(req, res);
				return;// 程式中斷

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				erMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/class_info/listAllClass_info.jsp");
//				failureView.forward(req, res);
//			}
		}
//		課程查詢
		if("select_pageClass_info".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/select_pageClass_info.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
//		類別列表
		if("listMain_class".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/main_class/listMain_class.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
		/*********************會員資料**********************/
		
//		會員列表
		if("listAllMember_info".equals(action)){
	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_info/listAllMember_info.jsp");
	   	failureView.forward(req, res);
		return;// 程式中斷
	}
	
//		會員登入歷程
	if("listAllLogin_history".equals(action)){
	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login_history/listAllLogin_history.jsp");
	   	failureView.forward(req, res);
		return;// 程式中斷
	}

		/*********************費用管理**********************/
//		優惠券
//		講師費用
	if("listAllTeacher_info".equals(action)){
		List<Class_infoVO> Srv = new Class_infoService().getAll();
	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sell/listAllTeacher_info.jsp");
	   	failureView.forward(req, res);
		return;// 程式中斷
	}
//		訂單管理
	if("listAllorder_info".equals(action)){
	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_info/listAllorder_info.jsp");
	   	failureView.forward(req, res);
		return;// 程式中斷
	}
	
	if ("listOneOrder_list".equals(action)) {
		try {
			String order_id = req.getParameter("order_id");
			Order_infoVO Order_info = new Order_infoService().getOneOrder_infoVO(order_id);
			List<Order_listVO> Order_listVO =new Order_listService ().getOrderInfo(order_id);
			req.setAttribute("Order_info", Order_info);
			req.setAttribute("Order_listVO", Order_listVO);
			RequestDispatcher diss = req.getRequestDispatcher("/back-end/order_list/listAllorder_info_list.jsp");
			diss.forward(req, res);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	
		/*********************數據管理**********************/
//		銷售分析
	if ("listSell_info".equals(action)) {
		try {
			String order_id = req.getParameter("order_id");
			List<Order_listVO> Order_listVO =new Order_listService ().getOrderInfo(order_id);
			req.setAttribute("Order_listVO", Order_listVO);
			RequestDispatcher diss = req.getRequestDispatcher("/back-end/sell/listSell_info.jsp");
			diss.forward(req, res);
		} catch (Exception e) {
			e.getMessage();
		}
	}
//		關鍵字分析
		/*********************權限管理**********************/
//		管理員列表
		
	
	}

}