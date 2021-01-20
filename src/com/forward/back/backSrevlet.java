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

/*********************�f�ֽҵ{**********************/

		/*********************�T���޲z**********************/
//		���i�޲z
		if("listAllPostMessage".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/post_message/BEpostContext.jsp");
    	   	failureView.forward(req, res);
			return;// �{�����_
		}
		
//		��ѫ�
		if("back-chat".equals(action)){
			
			// �޲z�̵n�J����ѱb��=�޲z�̵n�J�b��
			String loginAdminID = (String) session.getAttribute("adminAccount");
			String loginAdminName = (String) session.getAttribute("adminName");


			System.out.println("�ȪA��ѫǱҰ�");
			req.setAttribute("userName", loginAdminID);

			System.out.println("�ȪA" + loginAdminName + "�W�u���");
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chat/adminchat.jsp");
			failureView.forward(req, res);
			return;// �{�����_
		} 
			
		
		/*********************�������e**********************/
//		�s�i�P����r
		if("back-Keyword_formAll".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
    	   	failureView.forward(req, res);
			return;// �{�����_
		}
		/*********************�ҵ{�޲z**********************/
//		�s�W�ҵ{
		if("back-addClass_info".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/back-addClass_info.jsp");
    	   	failureView.forward(req, res);
			return;// �{�����_
		}
//		�ҵ{�C��
		if("back-listAllClass_info".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/verify_list/back-verify_listAll.jsp");
    	   	failureView.forward(req, res);
			return;// �{�����_
		}
		
		if("back-listAllClass_info-Details".equals(action)){
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);
			
//			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String class_id = req.getParameter("class_id");
				
				/***************************2.�}�l�d�߸��****************************************/
				AllClass_infoService aciSrv = new AllClass_infoService(); 
				AllClass_infoVO AllClass_infoVO = aciSrv.getAll(class_id);
							
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				session.setAttribute("AllClass_infoVO", AllClass_infoVO);         // ��Ʈw���X��Class_infoVO����,�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/back-listAllClass_info-Details.jsp");
	    	   	failureView.forward(req, res);
				return;// �{�����_

				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				erMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/class_info/listAllClass_info.jsp");
//				failureView.forward(req, res);
//			}
		}
//		�ҵ{�d��
		if("select_pageClass_info".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/select_pageClass_info.jsp");
    	   	failureView.forward(req, res);
			return;// �{�����_
		}
//		���O�C��
		if("listMain_class".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/main_class/listMain_class.jsp");
    	   	failureView.forward(req, res);
			return;// �{�����_
		}
		/*********************�|�����**********************/
		
//		�|���C��
		if("listAllMember_info".equals(action)){
	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member_info/listAllMember_info.jsp");
	   	failureView.forward(req, res);
		return;// �{�����_
	}
	
//		�|���n�J���{
	if("listAllLogin_history".equals(action)){
	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/login_history/listAllLogin_history.jsp");
	   	failureView.forward(req, res);
		return;// �{�����_
	}

		/*********************�O�κ޲z**********************/
//		�u�f��
//		���v�O��
	if("listAllTeacher_info".equals(action)){
		List<Class_infoVO> Srv = new Class_infoService().getAll();
	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sell/listAllTeacher_info.jsp");
	   	failureView.forward(req, res);
		return;// �{�����_
	}
//		�q��޲z
	if("listAllorder_info".equals(action)){
	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/order_info/listAllorder_info.jsp");
	   	failureView.forward(req, res);
		return;// �{�����_
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
	
	
		/*********************�ƾں޲z**********************/
//		�P����R
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
//		����r���R
		/*********************�v���޲z**********************/
//		�޲z���C��
		
	
	}

}