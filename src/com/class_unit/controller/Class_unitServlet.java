package com.class_unit.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.class_unit.model.Class_unitService;
import com.class_unit.model.Class_unitVO;
import com.sub_class.model.Sub_classVO;

@WebServlet("/Class_unit/Class_unitServlet")
@MultipartConfig(fileSizeThreshold = 5 * 5 * 5 * 1024 * 1024, maxFileSize = 5 * 5 * 5 * 1024 * 1024, maxRequestSize = 5
		* 5 * 5 * 1024 * 1024 * 1024)
public class Class_unitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action"); // 取action參數 選擇if方法
		// 取影片
		if ("many_video".equals(action)) {
			res.setContentType("video/mp4"); // 設定回應類型
			res.setHeader("Accept-Ranges", "bytes");
			String unit_id = req.getParameter("unit_id").trim(); // 取得get參數 → ?unit_id
			Class_unitService class_unitService = new Class_unitService(); // 呼叫Service方法
			Class_unitVO class_unitVO = class_unitService.select(unit_id); // 利用id得到物件
			OutputStream out = res.getOutputStream(); // 建立輸出流
			try {
				if (class_unitVO.getVideo() != null || class_unitVO.getVideo().length != 0) { // 判斷檔案是否存在
					res.addHeader("Accept-Ranges", "bytes");
					res.addHeader("Content-Length", "100");
					res.setStatus(200);
					res.setContentLength(class_unitVO.getVideo().length); // 通知瀏覽器檔案長度
					InputStream in = new ByteArrayInputStream(class_unitVO.getVideo()); // 將byte[]轉換成InputStream
					byte[] buf = new byte[class_unitVO.getVideo().length]; // 4K buffer //設定byte[]大小
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len); // 將資料輸出
					}

				} else {
					// 查詢值不存在
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);    //輸出錯誤訊息
					InputStream in = getServletContext().getResourceAsStream("/img/NoResult/unnamed.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
			} catch (Exception e) {
				// 沒有輸入查詢值
				InputStream in = getServletContext().getResourceAsStream("/img/NoResult/unnamed.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			} finally {
				out.close();
			}
		}

		// AJEX - 修改狀態
		// 傳入資料 影片id 修改狀態
		if ("video_status_update".equals(action)) {
			res.setContentType("text/plain"); // 設定回應類型
			String unit_id = req.getParameter("unit_id").trim(); // 取得get參數 → ?unit_id
			String video_status = req.getParameter("video_status").trim();
			// 沒取到的錯誤處理
			Class_unitService class_unitService = new Class_unitService(); // 呼叫Service方法
			Class_unitVO class_unitVO = class_unitService.select(unit_id); // 利用id得到物件
			OutputStream out = res.getOutputStream(); // 建立輸出流
//			try {
			if (class_unitVO != null) { // 判斷檔案是否存在
				java.sql.Timestamp video_updatetime = new Timestamp(System.currentTimeMillis());
				class_unitService.update(class_unitVO.getUnit_id(), class_unitVO.getChapter_id(),
						class_unitVO.getUnit_name(), class_unitVO.getVideo(), class_unitVO.getVideo_long(),
						video_updatetime, Integer.parseInt(video_status));
			} else {
				System.out.println("查詢值不存在");
			}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}finally {
			out.close();
//			}
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// 取得來源參數
		String action = req.getParameter("action");

//		if ("listEmps_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				
//				/***************************1.將輸入資料轉為Map**********************************/ 
//				//採用Map<String,String[]> getParameterMap()的方法 
//				//注意:an immutable java.util.Map 
//				Map<String, String[]> map = req.getParameterMap();
//				
//				/***************************2.開始複合查詢***************************************/
//				Class_unitService service = new Class_unitService();
//				List<Class_unitVO>list = service.getClass_unitAll();
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
//				RequestDispatcher successView = req.getRequestDispatcher("/back-end/Class_unit/listEmps_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		

		/******************************** 轉址查全部 ********************************/
		if ("getALL".equals(action)) {
			/*************************** 轉交 ***********************/
			String url = "/back-end/class_unit/listClass_unit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/******************************** 轉址新增資料 ********************************/
		if ("addnew".equals(action)) {
			/*************************** 轉交 ***********************/
			String url = "/back-end/class_unit/addClass_unit_all.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/******************************** 查詢一筆資料 ********************************/
		if ("listOneClass_unit".equals(action)) { // 來自listOneClass_unit.jsp的請求

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			// 建立容器裝錯誤資料+放入屬性中
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// 格式判斷

			String unit_id = req.getParameter("unit_id");
			
			/*************************** 2.開始查詢資料 ***************************************/
			Class_unitService Class_unitSrv = new Class_unitService();
			// 資料庫取出的資料,存入物件
			Class_unitVO Class_unitVO = Class_unitSrv.select(unit_id);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// 物件存入屬性
			req.setAttribute("class_unitVO", Class_unitVO);
			// 轉交
			String url = "/back-end/class_unit/listOneClass_unit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		}
		/******************************** 新增一筆資料 ********************************/
		if ("insert_all".equals(action)) { // 來自addClass_unit.jsp的請求
			// 裝錯誤資料
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String unit_name = req.getParameter("unit_name");
			System.out.println(unit_name);
			// unit_name判斷是否有值
			if (unit_name == null || unit_name.trim().isEmpty()) {
				errorMsgs.add("單元名稱不可以空白");
			}
			String chapter_id = req.getParameter("chapter_id");
			if (chapter_id == null || chapter_id.trim().isEmpty()) {
				errorMsgs.add("章節不可空白");
			}
			Integer video_status = 0;
			Part video_part = req.getPart("video");
			byte[] video = null;
			if (video_part != null) {
				InputStream in = video_part.getInputStream();
				video = new byte[in.available()];
				in.read(video);
				in.close();
			}
			String video_long = req.getParameter("video_long");
			// 已經存在的資料存入物件
			Class_unitVO Class_unitVo = new Class_unitVO();
			Class_unitVo.setUnit_name(unit_name);
			Class_unitVo.setChapter_id(chapter_id);

			// 有錯誤訊息轉交回去
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Class_unitVO", Class_unitVo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_unit/addClass_unit_all.jsp");
				failureView.forward(req, res);
				return;
			}

			java.sql.Timestamp video_updatetime = new java.sql.Timestamp(new Date().getTime());

			/*************************** 2.開始新增資料 ***************************************/
			Class_unitService service = new Class_unitService();
			service.insert(chapter_id, unit_name, video, video_long, video_updatetime, video_status);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// 轉交
			String url = "/back-end/class_unit/listClass_unit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		}
		/******************************** 新增一筆資料 ********************************/
		if ("insert".equals(action)) { // 來自addClass_unit.jsp的請求
			// 裝錯誤資料
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String unit_name = req.getParameter("unit_name");
			System.out.println(unit_name);
			// unit_name判斷是否有值
			if (unit_name == null || unit_name.trim().isEmpty()) {
				errorMsgs.add("單元名稱不可以空白");
			}
			String chapter_id = req.getParameter("chapter_id");
			if (chapter_id == null || chapter_id.trim().isEmpty()) {
				errorMsgs.add("章節不可空白");
			}

			Integer video_status = 0;

			Class_unitService service = new Class_unitService();
			Part video_part = req.getPart("video");
			InputStream in = video_part.getInputStream();
			byte[] video =null;
			if (in.available() != 0) {
				video = new byte[in.available()];
				in.read(video);
				in.close();
			}

			String video_long = req.getParameter("video_long");

			// 已經存在的資料存入物件
			Class_unitVO Class_unitVo = new Class_unitVO();
			Class_unitVo.setUnit_name(unit_name);
			Class_unitVo.setChapter_id(chapter_id);

			// 有錯誤訊息轉交回去
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Class_unitVO", Class_unitVo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_unit/addClass_unit.jsp");
				failureView.forward(req, res);
				return;
			}

			java.sql.Timestamp video_updatetime = new java.sql.Timestamp(new Date().getTime());

			/*************************** 2.開始新增資料 ***************************************/
			service.insert(chapter_id, unit_name, video, video_long, video_updatetime, video_status);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// 轉交
			String url = "/back-end/class_unit/listClass_unit.jsp";
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
				String unit_id = req.getParameter("unit_id").trim();
				/*************************** 2.開始新增資料 ***************************************/
				Class_unitService Srv = new Class_unitService();
				Srv.delete(unit_id);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 轉交
				String url = "/back-end/class_unit/listClass_unit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (RuntimeException e) {
				errorMsgs.add("該筆資料已經關聯，請勿刪除");
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/class_unit/listClass_unit.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
			} catch (Exception ex) {
				System.out.println("有錯誤");
				/*************************** 其他可能的錯誤處理 **********************************/
				errorMsgs.add(ex.getMessage());
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 轉交
				String url = "/back-end/class_unit/listClass_unit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}
		}
		if ("update_unit_id".equals(action)) {
			/*********************** 0.設定錯誤訊息容器 ***********************/
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 ***********************/
			String unit_id = req.getParameter("unit_id");
			/*********************** 2.開始新增資料 ***********************/
			Class_unitService Srv = new Class_unitService();
			Class_unitVO Class_unitVO = Srv.select(unit_id);
			req.setAttribute("class_unitVO", Class_unitVO);
			/*********************** 3.新增完成,準備轉交 ***********************/
			// 轉交
			String url = "/back-end/class_unit/update_Class_unit_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** 其他可能的錯誤處理 ***********************/
			/*************************** 轉交 ***********************/

		}

		if ("update".equals(action)) {
			// 裝錯誤資料
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Class_unitService service = new Class_unitService();
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String unit_id = req.getParameter("unit_id");
			String unit_name = req.getParameter("unit_name");
			// unit_name判斷是否有值
			if (unit_name == null || unit_name.trim().isEmpty()) {
				errorMsgs.add("單元名稱不可以空白");
			}
			String chapter_id = req.getParameter("chapter_id");
			if (chapter_id == null || chapter_id.trim().isEmpty()) {
				errorMsgs.add("章節不可空白");
			}

			Integer video_status = 0;

			Part video_part = req.getPart("video");
			InputStream in = video_part.getInputStream();
			byte[] video =null;
			if (in.available() == 0) {
				video=service.select(unit_id).getVideo();
			} else {
				video = new byte[in.available()];
				in.read(video);
				in.close();
			}
			String video_long = req.getParameter("video_long");

			// 已經存在的資料存入物件
			Class_unitVO Class_unitVo = new Class_unitVO();
			Class_unitVo.setUnit_name(unit_name);
			Class_unitVo.setChapter_id(chapter_id);

			// 有錯誤訊息轉交回去
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Class_unitVO", Class_unitVo); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/class_unit/update_Video_record_input.jsp");
				failureView.forward(req, res);
				return;
			}

			java.sql.Timestamp video_updatetime = new java.sql.Timestamp(new Date().getTime());

			/*************************** 2.開始新增資料 ***************************************/

			service.update(unit_id, chapter_id, unit_name, video, video_long, video_updatetime, video_status);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// 轉交
			String url = "/back-end/class_unit/listClass_unit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		}
//		if ("unit_id_sub_class".equals(action)) {
//			
//			/***********************0.設定錯誤訊息容器***********************/
//			//不用錯誤處理
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理***********************/
//			String unit_id = req.getParameter("unit_id");
//			String unit_name = req.getParameter("unit_name");
//			/*********************** 2.開始新增資料***********************/
//			Class_unitService Srv = new Class_unitService();
//			Set<Sub_classVO> Sub_classVO_list = Srv.getSub_classByClass_unit_id(unit_id);
//			Class_unitVO Class_unitVO = new Class_unitVO();
//			Class_unitVO.setUnit_id(unit_id);
//			Class_unitVO.setUnit_name(unit_name);
//			req.setAttribute("Class_unitVO", Class_unitVO);
//
//			req.setAttribute("Sub_classVO_list", Sub_classVO_list);
//			/*********************** 3.新增完成,準備轉交***********************/
//			String url = "/back-end/sub_class/unit_id_sub_class.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//			/*********************** 其他可能的錯誤處理***********************/
//			/*************************** 轉交 ***********************/
//			
//		}

	}
}
