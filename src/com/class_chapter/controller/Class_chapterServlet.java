package com.class_chapter.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.class_chapter.model.Class_chapterService;
import com.class_chapter.model.Class_chapterVO;
import com.sub_class.model.Sub_classVO;

@WebServlet("/Class_chapter/Class_chapterServlet")
public class Class_chapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// 取得來源參數
		String action = req.getParameter("action");
		
		
		/******************************** 轉址查全部 ********************************/
		if("getALL".equals(action)) {
			/*************************** 轉交 ***********************/
			String url = "/back-end/class_chapter/listClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	
		/******************************** 轉址新增資料 ********************************/
		if("addnew".equals(action)) {
			/*************************** 轉交 ***********************/
			String url = "/back-end/class_chapter/addClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/******************************** 查詢一筆資料 ********************************/
		if ("listOneClass_chapter".equals(action)) { // 來自listOneClass_chapter.jsp的請求

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			// 建立容器裝錯誤資料+放入屬性中
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 格式判斷

			String chapter_name = req.getParameter("chapter_name");
			String class_id = req.getParameter("class_id");
			String chapter_id = req.getParameter("chapter_id");

			if (chapter_id == null || chapter_id.length() == 0) {
				if (chapter_name == null || chapter_name.length() == 0) {
					if (class_id == null || class_id.length() == 0) {
						errorMsgs.add("【主課程類別編號】、【主類別課程名稱】或【主類別狀態】必須擇一填寫");
					}
				}
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Class_chapter/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始查詢資料 ***************************************/
			Class_chapterService Class_chapterSrv = new Class_chapterService();
			// 資料庫取出的資料,存入物件
			Class_chapterVO class_chapterVO = Class_chapterSrv.select(chapter_id);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// 物件存入屬性
			req.setAttribute("class_chapterVO", class_chapterVO);
			// 轉交
			String url = "/back-end/class_chapter/listOneClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		}
		/******************************** 新增一筆資料 ********************************/
		if ("insert_all".equals(action)) { // 來自addClass_chapter.jsp的請求
			// 裝錯誤資料
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String chapter_name = req.getParameter("chapter_name");
			String class_id = req.getParameter("class_id");
			// chapter_name判斷是否有值
			String chapter_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
			if (chapter_name == null || chapter_name.trim().isEmpty()) {
				errorMsgs.add("章節名稱不可以空白");
			}else if(!chapter_name.matches(chapter_nameReg)) {
				errorMsgs.add("章節名稱必須是中文或英文，並必須介於2到30字之間");
			}
			
			if (class_id == null || class_id.trim().isEmpty()) {
				errorMsgs.add("課程不可以空白");
			}

			// 已經存在的資料存入物件
			Class_chapterVO class_chapterVO = new Class_chapterVO();
			class_chapterVO.setChapter_name(chapter_name);
			class_chapterVO.setClass_id(class_id);

			// 有錯誤訊息轉交回去
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("class_chapterVO", class_chapterVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_chapter/addClass_chapter.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Class_chapterService service = new Class_chapterService();
			service.insert(class_id, chapter_name);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// 轉交
			String url = "/back-end/class_chapter/listClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		}
		/******************************** 新增一筆資料 ********************************/
		if ("insert".equals(action)) { // 來自addClass_chapter.jsp的請求
			// 裝錯誤資料
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String chapter_name = req.getParameter("chapter_name");
			String class_id = req.getParameter("class_id");
			// chapter_name判斷是否有值
			if (chapter_name == null || chapter_name.trim().isEmpty()) {
				errorMsgs.add("主類別不可以空白");
			}

			// 已經存在的資料存入物件
			Class_chapterVO class_chapterVO = new Class_chapterVO();
			class_chapterVO.setChapter_name(chapter_name);
			class_chapterVO.setClass_id(class_id);

			// 有錯誤訊息轉交回去
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("class_chapterVO", class_chapterVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/Class_chapter/addClass_chapter.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			Class_chapterService service = new Class_chapterService();
			service.insert(class_id, chapter_name);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// 轉交
			String url = "/back-end/class_chapter/listClass_chapter.jsp";
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
				String chapter_id = req.getParameter("chapter_id");
				/*************************** 2.開始新增資料 ***************************************/
				Class_chapterService Class_chapterSrv = new Class_chapterService();
				Class_chapterSrv.delete(chapter_id);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 轉交
				String url = "/back-end/class_chapter/listClass_chapter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {

				/*************************** 其他可能的錯誤處理 **********************************/
				errorMsgs.add("該筆資料已經關聯，請勿刪除");
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 轉交
				String url = "/back-end/Class_chapter/listClass_chapter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}
		}
		if ("update_chapter_id".equals(action)) {
			/*********************** 0.設定錯誤訊息容器 ***********************/
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
			String chapter_id = req.getParameter("chapter_id");
			/*********************** 2.開始新增資料 ***********************/
			Class_chapterService Srv = new Class_chapterService();
			Class_chapterVO class_chapterVO = Srv.select(chapter_id);
			req.setAttribute("class_chapterVO", class_chapterVO);
			/*********************** 3.新增完成,準備轉交 ***********************/
			// 轉交
			String url = "/back-end/class_chapter/update_Class_chapter_input.jsp";
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
			String chapter_id = req.getParameter("chapter_id");
			String chapter_name = req.getParameter("chapter_name");
			String class_id = req.getParameter("class_id");

			String chapter_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
			if (chapter_name == null || chapter_name.trim().isEmpty()) {
				errorMsgs.add("章節名稱不可以空白");
			}else if(!chapter_name.matches(chapter_nameReg)) {
				errorMsgs.add("章節名稱必須是中文或英文，並必須介於2到30字之間");
			}
			
			if (class_id == null || class_id.trim().isEmpty()) {
				errorMsgs.add("課程不可以空白");
			}

			if (!errorMsgs.isEmpty()) {
				Class_chapterVO class_chapterVO = new Class_chapterVO();
				class_chapterVO.setChapter_id(chapter_id);
				class_chapterVO.setChapter_name(chapter_name);
				class_chapterVO.setClass_id(class_id);
				req.setAttribute("class_chapterVO", class_chapterVO);
				String url = "/back-end/class_chapter/update_Class_chapter_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}
			/*********************** 2.開始修改資料 ***********************/
			Class_chapterService Srv = new Class_chapterService();
			Srv.update(chapter_id, class_id, chapter_name);
			/*********************** 3.新增完成,準備轉交 ***********************/
			// 轉交
			String url = "/back-end/class_chapter/listClass_chapter.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** 其他可能的錯誤處理 ***********************/
			/*************************** 轉交 ***********************/
		}
//		if ("chapter_id_sub_class".equals(action)) {
//			
//			/***********************0.設定錯誤訊息容器***********************/
//			//不用錯誤處理
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理***********************/
//			String chapter_id = req.getParameter("chapter_id");
//			String chapter_name = req.getParameter("chapter_name");
//			/*********************** 2.開始新增資料***********************/
//			Class_chapterService Srv = new Class_chapterService();
//			Set<Sub_classVO> Sub_classVO_list = Srv.getSub_classByClass_chapter_id(chapter_id);
//			class_chapterVO class_chapterVO = new class_chapterVO();
//			class_chapterVO.setchapter_id(chapter_id);
//			class_chapterVO.setchapter_name(chapter_name);
//			req.setAttribute("class_chapterVO", class_chapterVO);
//
//			req.setAttribute("Sub_classVO_list", Sub_classVO_list);
//			/*********************** 3.新增完成,準備轉交***********************/
//			String url = "/back-end/sub_class/chapter_id_sub_class.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//			/*********************** 其他可能的錯誤處理***********************/
//			/*************************** 轉交 ***********************/
//			
//		}
		

	}
}
