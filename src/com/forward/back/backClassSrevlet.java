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

import com.class_chapter.model.Class_chapterService;
import com.class_chapter.model.Class_chapterVO;
import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.class_unit.model.Class_unitService;
import com.class_unit.model.Class_unitVO;
import com.member_info.model.Member_infoVO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;
import com.view_class_income.model.View_class_incomeService;
import com.view_class_income.model.View_class_incomeVO;

/**
 * Servlet implementation class Class_infoServlet
 */
@WebServlet(
		urlPatterns = { "/forward/backClassSrevlet" })
public class backClassSrevlet extends HttpServlet {
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

//		章節單元
		if("chapterAndUnit".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/back-listClass_unit.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
//		作業考試
		if("homework".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/back-Homwork.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
		
//		作業考試
		if("Test".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/back-Test.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
		
//		ClassMsg
		if("ClassMsg".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/back-Class_Msg.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
		
//		LearnMsg
		if("LearnMsg".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/back-Learn_Msg.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}		
		
//		老師學生
		if("studentsAndTeacher".equals(action)){
    	   	RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_info/back-AllMember_info.jsp");
    	   	failureView.forward(req, res);
			return;// 程式中斷
		}
	}
}