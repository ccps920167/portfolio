package com.main_class.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main_class.model.Main_classJDBCDAO;
import com.main_class.model.Main_classService;
import com.main_class.model.Main_classVO;
import com.sub_class.model.Sub_classVO;

@WebServlet("/Main_class/Main_classServlet")
public class Main_classServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// 取得來源參數
		String action = req.getParameter("action");
		
		
		if ("listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				Main_classService service = new Main_classService();
				List<Main_classVO>list = service.getMain_classAll();
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/main_class/listEmps_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		/******************************** 轉址查全部 ********************************/
		if("getALL".equals(action)) {
			/*************************** 轉交 ***********************/
			String url = "/back-end/main_class/listMain_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	
		/******************************** 轉址新增資料 ********************************/
		if("addnew".equals(action)) {
			/*************************** 轉交 ***********************/
			String url = "/back-end/main_class/addMain_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/******************************** 查詢一筆資料 ********************************/
		if ("listOneMain_class".equals(action)) { // 來自listOneMain_class.jsp的請求

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			// 建立容器裝錯誤資料+放入屬性中
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 格式判斷

			String mainClass_name = req.getParameter("mainClass_name");
			String mainClass_status = req.getParameter("mainClass_status");
			String mainClass_id = req.getParameter("mainClass_id");

			if (mainClass_id == null || mainClass_id.length() == 0) {
				if (mainClass_name == null || mainClass_name.length() == 0) {
					if (mainClass_status == null || mainClass_status.length() == 0) {
						errorMsgs.add("【主課程類別編號】、【主類別課程名稱】或【主類別狀態】必須擇一填寫");
					}
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/main_class/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始查詢資料 ***************************************/
			Main_classService main_classSrv = new Main_classService();
			// 資料庫取出的資料,存入物件
			Main_classVO main_classVO = main_classSrv.getOneMain_class(mainClass_id);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// 物件存入屬性
			req.setAttribute("main_classVO", main_classVO);
			// 轉交
			String url = "/back-end/main_class/listOneMain_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		}

		/******************************** 新增一筆資料 ********************************/
		if ("insert".equals(action)) { // 來自addMain_class.jsp的請求
			// 裝錯誤資料
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String mainClass_name = req.getParameter("mainClass_name");
			// mainClass_name判斷是否有值
			if (mainClass_name == null || mainClass_name.trim().isEmpty()) {
				errorMsgs.add("主類別不可以空白");
			}

			String[] subClass_name = req.getParameterValues("subClass_name");

			System.out.println(mainClass_name);
			System.out.println(subClass_name);
			// 已經存在的資料存入物件
			Main_classVO main_classVo = new Main_classVO();
			main_classVo.setMainClass_name(mainClass_name);
			main_classVo.setMainClass_status(0);

			// 有錯誤訊息轉交回去
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Main_classVO", main_classVo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/main_class/addMain_class.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Main_classService service = new Main_classService();
			service.addMain_class(mainClass_name, 1);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// 轉交
			String url = "/back-end/main_class/listMain_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		}

		/******************************** 新增一筆資料 ********************************/
		if ("delete".equals(action)) {
			// 裝錯誤資料
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			接收一筆參數 刪除的序號
				String mainClass_id = req.getParameter("mainClass_id");
				/*************************** 2.開始新增資料 ***************************************/
				Main_classService main_classSrv = new Main_classService();
				main_classSrv.deleteMain_class(mainClass_id);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 轉交
				String url = "/back-end/main_class/listMain_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {

				/*************************** 其他可能的錯誤處理 **********************************/
				errorMsgs.add("該筆資料已經關聯，請勿刪除");
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 轉交
				String url = "/back-end/main_class/listMain_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}
		}
		if ("update_mainClass_id".equals(action)) {
			/*********************** 0.設定錯誤訊息容器 ***********************/
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
			String mainClass_id = req.getParameter("mainClass_id");
			/*********************** 2.開始新增資料 ***********************/
			Main_classService Srv = new Main_classService();
			Main_classVO main_classVO = Srv.getOneMain_class(mainClass_id);
			req.setAttribute("main_classVO", main_classVO);
			/*********************** 3.新增完成,準備轉交 ***********************/
			// 轉交
			String url = "/back-end/main_class/update_Main_class_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** 其他可能的錯誤處理 ***********************/
			/*************************** 轉交 ***********************/

		}

		if ("update".equals(action)) {
			/*********************** 0.設定錯誤訊息容器 ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
			String mainClass_id = req.getParameter("mainClass_id");
			String mainClass_name = req.getParameter("mainClass_name");
			String mainClass_status = req.getParameter("mainClass_status");

			if (mainClass_name == null || mainClass_name.length() == 0) {
				errorMsgs.add("請輸入主課程類別名稱");
			}

			if (mainClass_status == null || mainClass_status.length() == 0) {
				errorMsgs.add("請輸入主課程類別名稱");
			}

			if (!errorMsgs.isEmpty()) {
				Main_classVO main_classVO = new Main_classVO();
				main_classVO.setMainClass_id(mainClass_id);
				main_classVO.setMainClass_name(mainClass_name);
				main_classVO.setMainClass_status(Integer.parseInt(mainClass_status));
				req.setAttribute("main_classVO", main_classVO);
				String url = "/back-end/main_class/update_Main_class_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			/*********************** 2.開始修改資料 ***********************/
			Main_classService Srv = new Main_classService();
			Srv.updateMain_class(mainClass_name, Integer.parseInt(mainClass_status), mainClass_id);
			/*********************** 3.新增完成,準備轉交 ***********************/
			// 轉交
			String url = "/back-end/main_class/listMain_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** 其他可能的錯誤處理 ***********************/
			/*************************** 轉交 ***********************/
		}
		if ("mainclass_id_sub_class".equals(action)) {
			
			/***********************0.設定錯誤訊息容器***********************/
			//不用錯誤處理
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理***********************/
			String mainClass_id = req.getParameter("mainClass_id");
			String mainClass_name = req.getParameter("mainClass_name");
			/*********************** 2.開始新增資料***********************/
			Main_classService Srv = new Main_classService();
			Set<Sub_classVO> Sub_classVO_list = Srv.getSub_classByMain_class_id(mainClass_id);
			Main_classVO main_classVO = new Main_classVO();
			main_classVO.setMainClass_id(mainClass_id);
			main_classVO.setMainClass_name(mainClass_name);
			req.setAttribute("main_classVO", main_classVO);

			req.setAttribute("Sub_classVO_list", Sub_classVO_list);
			/*********************** 3.新增完成,準備轉交***********************/
			String url = "/back-end/sub_class/mainclass_id_sub_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** 其他可能的錯誤處理***********************/
			/*************************** 轉交 ***********************/
			
		}
		

	}
}
