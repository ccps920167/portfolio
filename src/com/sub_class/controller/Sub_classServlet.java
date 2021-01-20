package com.sub_class.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main_class.model.Main_classJDBCDAO;
import com.main_class.model.Main_classService;
import com.main_class.model.Main_classVO;
import com.sub_class.model.Sub_classService;
import com.sub_class.model.Sub_classVO;

@WebServlet("/Sub_class/Sub_classServlet")
public class Sub_classServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// 取得來源參數
		String action = req.getParameter("action");
		/******************************** 轉址查全部 ********************************/
		if ("getALL".equals(action)) {
			/*************************** 轉交 ***********************/
			String url = "/back-end/sub_class/listSub_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/******************************** 轉址新增資料 ********************************/
		if ("addnew".equals(action)) {
			/*************************** 轉交 ***********************/
			String url = "/back-end/sub_class/addSub_class_all.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("add_SubClass".equals(action)) {

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
			String mainClass_id = req.getParameter("mainClass_id");
			String mainClass_name = req.getParameter("mainClass_name");
			/*********************** 3.新增完成,準備轉交 ***********************/
			Main_classVO main_classVO = new Main_classVO();
			main_classVO.setMainClass_id(mainClass_id);
			main_classVO.setMainClass_name(mainClass_name);
			req.setAttribute("main_classVO", main_classVO);

			/*************************** 轉交 ***********************/
			String url = "/back-end/sub_class/addSub_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addSub_class.jsp的請求
			/*********************** 0.設定錯誤訊息容器 ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
			// 回傳資料的容器

			String mainClass_id = req.getParameter("mainClass_id");
			String mainClass_name = req.getParameter("mainClass_name");
			String subClass_name = req.getParameter("subClass_name");
			String subClass_status = req.getParameter("subClass_status");

			Main_classVO main_classVO = new Main_classVO();
			main_classVO.setMainClass_id(mainClass_id);
			main_classVO.setMainClass_name(mainClass_name);
			req.setAttribute("main_classVO", main_classVO);
			try {
				String regex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (subClass_name == null || subClass_name.trim().length() == 0) {
					errorMsgs.add("請輸入副類別名稱");
				} else if (!subClass_name.matches(regex)) {
					subClass_name = null;
					errorMsgs.add("請輸入副類別名稱僅能是中文、英文或數字");
				}
				Sub_classVO sub_classVO = new Sub_classVO();
				sub_classVO.setSubClass_name(subClass_name);
				sub_classVO.setSubClass_status(Integer.parseInt(subClass_status));

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sub_classVO", sub_classVO);
					String url = "/back-end/sub_class/addSub_class.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}

				/*********************** 2.開始新增資料 ***********************/
				Sub_classService Srv = new Sub_classService();
				Srv.add(subClass_name, subClass_status, mainClass_id);
				Main_classService Main_classSrv = new Main_classService();
				Set<Sub_classVO> Sub_classVO_list = Main_classSrv.getSub_classByMain_class_id(mainClass_id);

				req.setAttribute("Sub_classVO_list", Sub_classVO_list);

				/*********************** 3.新增完成,準備轉交 ***********************/
				String url = "/back-end/sub_class/mainclass_id_sub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				/*********************** 其他可能的錯誤處理 ***********************/
				errorMsgs.add("請加入欲新增副類別");
				/*************************** 轉交 ***********************/
				if (!errorMsgs.isEmpty()) {
					String url = "/back-end/sub_class/addSub_class.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}

			}
		}

		if ("insert_all".equals(action)) { // 來自addSub_class.jsp的請求
			/*********************** 0.設定錯誤訊息容器 ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
			// 回傳資料的容器
			String subClass_name = req.getParameter("subClass_name");
			String mainClass_id = req.getParameter("mainClass_id");
			String subClass_status = req.getParameter("subClass_status");
			String regex = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (subClass_name == null || subClass_name.trim().length() == 0) {
				errorMsgs.add("請輸入副類別名稱");
			} else if (!subClass_name.matches(regex)) {
				errorMsgs.add("請輸入副類別名稱僅能是中文、英文或數字");
			}

			if (!errorMsgs.isEmpty()) {
				String url = "/back-end/sub_class/addSub_class_all.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

			/*********************** 2.開始新增資料 ***********************/
			Sub_classService Srv = new Sub_classService();
			Srv.add(subClass_name, subClass_status, mainClass_id);

			/*********************** 3.新增完成,準備轉交 ***********************/
			String url = "/back-end/sub_class/listSub_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*********************** 其他可能的錯誤處理 ***********************/
			/*************************** 轉交 ***********************/
		}

		/******************************** 刪除一筆資料 ********************************/
		if ("delete".equals(action)) {
			/*********************** 0.設定錯誤訊息容器 ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/

				String subClass_id = req.getParameter("subClass_id");
				/*********************** 2.開始新增資料 ***********************/
				Main_classVO main_classVO = new Main_classVO();
				main_classVO.setMainClass_id(req.getParameter("mainClass_id"));
				main_classVO.setMainClass_name(req.getParameter("mainClass_name"));
				req.setAttribute("main_classVO", main_classVO);

				Sub_classService Srv = new Sub_classService();
				Srv.deleteSub_class(subClass_id);
				Main_classService Main_classSrv = new Main_classService();
				Set<Sub_classVO> Sub_classVO_list = Main_classSrv
						.getSub_classByMain_class_id(req.getParameter("mainClass_id"));
				req.setAttribute("Sub_classVO_list", Sub_classVO_list);
				/*********************** 3.新增完成,準備轉交 ***********************/
				String url = "/back-end/sub_class/mainclass_id_sub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				/*************************** 其他可能的錯誤處理 **********************************/
				errorMsgs.add("該筆資料已經關聯，請勿刪除");
				Main_classService Main_classSrv = new Main_classService();
				Set<Sub_classVO> Sub_classVO_list = Main_classSrv
						.getSub_classByMain_class_id(req.getParameter("mainClass_id"));
				req.setAttribute("Sub_classVO_list", Sub_classVO_list);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 轉交
				String url = "/back-end/sub_class/mainclass_id_sub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 轉交 ***********************/
			}
		}

		if ("update_subClass_id".equals(action)) {
			/*********************** 0.設定錯誤訊息容器 ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
			String subClass_id = req.getParameter("subClass_id");
			/*********************** 2.開始新增資料 ***********************/
			Sub_classService srv = new Sub_classService();
			Sub_classVO sub_classVO = srv.getOneMain_class(subClass_id);
			req.setAttribute("sub_classVO", sub_classVO);
			/*********************** 3.新增完成,準備轉交 ***********************/
			String url = "/back-end/sub_class/update_Sub_class_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

		}

		if ("update".equals(action)) {
			/*********************** 0.設定錯誤訊息容器 ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
			String subClass_id = req.getParameter("subClass_id");
			String subClass_name = req.getParameter("subClass_name");
			String subClass_status = req.getParameter("subClass_status");
			String mainClass_id = req.getParameter("mainClass_id");

			Sub_classVO sub_classVO = new Sub_classVO();
			sub_classVO.setMainClass_id(mainClass_id);
			sub_classVO.setSubClass_id(subClass_id);
			sub_classVO.setSubClass_name(subClass_name);
			sub_classVO.setSubClass_status(Integer.parseInt(subClass_status));

//			subClass_name錯誤處理
			if (subClass_name == null || subClass_name.trim().length() == 0) {
				errorMsgs.add("副課程類別名稱請勿空白");
			}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("sub_classVO", sub_classVO);
				String url = "/back-end/sub_class/update_Sub_class_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

			/*********************** 2.開始新增資料 ***********************/
			Sub_classService Srv = new Sub_classService();
			Srv.update(sub_classVO);
			/*********************** 3.新增完成,準備轉交 ***********************/
			Main_classService Main_classSrv = new Main_classService();
			Main_classVO main_classVO = Main_classSrv.getOneMain_class(mainClass_id);
			req.setAttribute("main_classVO", main_classVO);

			Set<Sub_classVO> Sub_classVO_list = Main_classSrv
					.getSub_classByMain_class_id(req.getParameter("mainClass_id"));

			req.setAttribute("Sub_classVO_list", Sub_classVO_list);
			String url = "/back-end/sub_class/mainclass_id_sub_class.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** 其他可能的錯誤處理 ***********************/
			/*************************** 轉交 ***********************/
		}
		if ("listOneSub_class".equals(action)) {
			/*********************** 0.設定錯誤訊息容器 ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
			try {
				String subClass_id = req.getParameter("subClass_id");
				String subClass_name = req.getParameter("subClass_name");
				String regex = "^[S][C][I][0-9]{8}$";
				if (subClass_id == null || subClass_id.trim().length() == 0) {
					if (subClass_name == null || subClass_name.trim().length() == 0) {
						errorMsgs.add("請輸入【副課程類別編號】或【副課程類別名稱】");
					}
				}
				if (subClass_id.matches(regex)) {
					errorMsgs.add("請輸入正確格式");
				}

				if (!errorMsgs.isEmpty()) {
					String url = "/back-end/sub_class/select_page.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				/*********************** 2.開始新增資料 ***********************/
				Sub_classService Srv = new Sub_classService();
				Sub_classVO sub_classVO = Srv.getOneMain_class(subClass_id);
				req.setAttribute("sub_classVO", sub_classVO);
				/*********************** 3.新增完成,準備轉交 ***********************/
				String url = "/back-end/sub_class/listOneSub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*********************** 其他可能的錯誤處理 ***********************/
			} catch (Exception e) {
				errorMsgs.add("資料不存在");
				/*************************** 轉交 ***********************/
				String url = "/back-end/sub_class/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

		}
		if ("delete_all".equals(action)) {
			/*********************** 0.設定錯誤訊息容器 ***********************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
				String subClass_id = req.getParameter("subClass_id");
				/*********************** 2.開始新增資料 ***********************/
				Main_classVO main_classVO = new Main_classVO();
				main_classVO.setMainClass_id(req.getParameter("mainClass_id"));
				main_classVO.setMainClass_name(req.getParameter("mainClass_name"));
				req.setAttribute("main_classVO", main_classVO);

				Sub_classService Srv = new Sub_classService();
				Srv.deleteSub_class(subClass_id);

				Main_classService Main_classSrv = new Main_classService();
				Set<Sub_classVO> Sub_classVO_list = Main_classSrv
						.getSub_classByMain_class_id(req.getParameter("mainClass_id"));
				req.setAttribute("Sub_classVO_list", Sub_classVO_list);
				/*********************** 3.新增完成,準備轉交 ***********************/
				String url = "/back-end/sub_class/listSub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				/*************************** 其他可能的錯誤處理 **********************************/
				errorMsgs.add("該筆資料已經關聯，請勿刪除");
				Main_classService Main_classSrv = new Main_classService();
				Set<Sub_classVO> Sub_classVO_list = Main_classSrv
						.getSub_classByMain_class_id(req.getParameter("mainClass_id"));
				req.setAttribute("Sub_classVO_list", Sub_classVO_list);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 轉交
				String url = "/back-end/sub_class/listSub_class.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 轉交 ***********************/
			}
		}

	}
}
