package com.class_info.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.allClass_info.model.AllClass_infoService;
import com.allClass_info.model.AllClass_infoVO;
import com.class_chapter.model.Class_chapterService;
import com.class_chapter.model.Class_chapterVO;
import com.class_info.model.*;
import com.class_unit.model.Class_unitService;
import com.class_unit.model.Class_unitVO;
import com.member_info.model.Member_infoVO;


@WebServlet("/class_info/class_infoServlet")
@MultipartConfig()
public class Class_infoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("SearchOne".equals(action)) {     // 查1      來自select_pageClass_info.jsp的請求

			List<String> erMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("erMsgs", erMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String class_id = req.getParameter("class_id");
				System.out.println(class_id);
				if (class_id == null || (class_id.trim()).length() == 0) {
					erMsgs.add("請輸入課程編號");
				}
				// Send the use back to the form, if there were errors
				if (!erMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/class_info/select_pageClass_info.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
								
				/***************************2.開始查詢資料*****************************************/
				Class_infoService ciSvc = new Class_infoService();
				Class_infoVO Class_infoVO = ciSvc.getOneClass_info(class_id);
				if (Class_infoVO == null) {
					erMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!erMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/class_info/select_pageClass_info.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("Class_infoVO", Class_infoVO); // 資料庫取出的Class_infoVO物件,存入req
				String url = "/back-end/class_info/listOneClass_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				erMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/class_info/select_pageClass_info.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllClass_info.jsp的請求

			List<String> erMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("erMsgs", erMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String class_id = req.getParameter("class_id");
				
				/***************************2.開始查詢資料****************************************/
				Class_infoService ciSvc = new Class_infoService();
				Class_infoVO Class_infoVO = ciSvc.getOneClass_info(class_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("Class_infoVO", Class_infoVO);         // 資料庫取出的Class_infoVO物件,存入req
				String url = "/back-end/class_info/update_Class_info_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_Class_info_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				erMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/class_info/listAllClass_info.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_Class_info_input.jsp的請求
			
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String class_id = req.getParameter("class_id").trim();
				String class_name = req.getParameter("class_name");
				if (class_name == null || class_name.trim().length() == 0) {
					erMsgs.add("課程名稱: 請勿空白");
				}
				
				String member_id = req.getParameter("member_id");
				String memidReg = "^[(a-zA-Z0-9_)]{8}$";
				if (member_id == null || member_id.trim().length() == 0) {
					erMsgs.add("會員編號: 請勿空白");
				} 
				else if(!member_id.trim().matches(memidReg)) { //以下練習正則(規)表示式(regular-expression)
					erMsgs.add("會員編號: 只能是英文字母和數字, 且長度必須為10");
	            }
				String subclass_id = req.getParameter("subclass_id");
				String subclass_idReg = "^[(a-zA-Z0-9_)]{8}$";
				if (subclass_id == null || subclass_id.trim().length() == 0) {
					erMsgs.add("副類別課程編號: 請勿空白");
				} 
				else if(!subclass_id.trim().matches(subclass_idReg)) { //以下練習正則(規)表示式(regular-expression)
					erMsgs.add("副類別課程編號: 只能是英文字母和數字, 且長度必須為10");
	            }
				Timestamp startfund_date = null;
				try {					
					startfund_date = java.sql.Timestamp.valueOf(req.getParameter("startfund_date").trim());
				} catch (IllegalArgumentException e) {
					erMsgs.add("請輸入開始募資日期");
					System.out.println(startfund_date);
				}				
				Timestamp startclass_time = null;
				try {
					startclass_time = java.sql.Timestamp.valueOf(req.getParameter("startclass_time").trim());
				} catch (IllegalArgumentException e) {
					erMsgs.add("請輸入開課日期");
				}
				String class_description = req.getParameter("class_description");
				if (class_description == null || class_description.trim().length() == 0) {
					erMsgs.add("課程描述: 請勿空白");
				} 
				Part part = req.getPart("class_picture");
				byte[] class_picture = null;
				InputStream fis = part.getInputStream();
				if(fis.available() != 0) {
					class_picture = new byte[fis.available()];
					fis.read(class_picture);
					fis.close();
				}else {
					erMsgs.add("請上傳課程圖片");
				}

				Integer startfund_price = Integer.valueOf(req.getParameter("startfund_price"));
				String startfund_priceReg = "^[(0-9)]";
				if (req.getParameter("startfund_price") == null || req.getParameter("startfund_price").trim().length() == 0) {
					erMsgs.add("募資售價: 請勿空白");
				} 
//				else if(!req.getParameter("startfund_price").trim().matches(startfund_priceReg) && startfund_price < 50) { //以下練習正則(規)表示式(regular-expression)
//					erMsgs.add("募資售價: 只能是數字, 且金額必須大於50");
//	            }
				Integer original_price = Integer.valueOf(req.getParameter("original_price"));
				String original_priceReg = "^[(0-9)]";
				if (req.getParameter("original_price") == null || req.getParameter("original_price").trim().length() == 0) {
					erMsgs.add("定價: 請勿空白");
				}
//				else if(!req.getParameter("original_price").trim().matches(original_priceReg) && original_price < 100) { //以下練習正則(規)表示式(regular-expression)
//					erMsgs.add("定價: 只能是數字, 且金額必須大於100");
//	            }
				Integer people_threshold = Integer.valueOf(req.getParameter("people_threshold"));
				String peopleTReg = "^[(0-9)]";
				if (req.getParameter("people_threshold") == null || req.getParameter("people_threshold").trim().length() == 0) {
					erMsgs.add("門檻人數: 請勿空白");
				} 
//				else if(!req.getParameter("people_threshold").trim().matches(peopleTReg) && people_threshold < 30) { //以下練習正則(規)表示式(regular-expression)
//					erMsgs.add("門檻人數: 只能是數字, 且必須大於30");
//	            }
				String class_length = req.getParameter("class_length");
//				String class_lengthReg = "^[(a-zA-Z0-9_)]{10}$";
//				int peopleT = Integer.parseInt(people_threshold);
				if (class_length == null || class_length.trim().length() == 0) {
					erMsgs.add("課程長度: 請勿空白");
				}
//				 else if(!people_threshold.trim().matches(peopleTReg) && peopleT >= 30) { //以下練習正則(規)表示式(regular-expression)
//					erMsgs.add("門檻人數: 只能是數字, 且必須大於30");
//	            }		
				
				
				Part part_video = req.getPart("video_fundraising");
				byte[] video_fundraising = null;
				InputStream fisVF = part_video.getInputStream();
				if(fisVF.available() != 0) {
					video_fundraising = new byte[fisVF.available()];
					fisVF.read(video_fundraising);
					fisVF.close();
				}else {
					erMsgs.add("請上傳募資影片");
				}
				
				Timestamp update_time = null;
				update_time = new java.sql.Timestamp(System.currentTimeMillis());
				
				String admin_id = null;
				
				Integer class_status = 7;
				

				Class_infoVO class_infoVO = new Class_infoVO();
				class_infoVO.setClass_id(class_id);
				class_infoVO.setClass_name(class_name);
				class_infoVO.setMember_id(member_id);
				class_infoVO.setSubclass_id(subclass_id);
				class_infoVO.setStartfund_date(startfund_date);
				class_infoVO.setStartclass_time(startclass_time);
				class_infoVO.setClass_description(class_description);
				class_infoVO.setClass_picture(class_picture);
				class_infoVO.setStartfund_price(startfund_price);
				class_infoVO.setOriginal_price(original_price);
				class_infoVO.setPeople_threshold(people_threshold);
				class_infoVO.setClass_length(class_length);
				
				// Send the use back to the form, if there were errors
				if (!erMsgs.isEmpty()) {
					req.setAttribute("Class_infoVO", class_infoVO); // 含有輸入格式錯誤的Class_infoVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/class_info/update_Class_info_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Class_infoService ciSvc = new Class_infoService();
				class_infoVO = ciSvc.updateClass_info(
						class_id, 
						class_name, 
						member_id, 
						class_status, 
						subclass_id, 
						startfund_date, 
						startclass_time, 
						class_description, 
						class_picture, 
						startfund_price, 
						original_price, 
						people_threshold, 
						class_length, 
						video_fundraising, 
						update_time, 
						admin_id);
						
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "/back-end/class_info/listAllClass_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllClass_info.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				erMsgs.add(e.getMessage());
//				System.out.println(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/class_info/update_Class_info_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert1".equals(action)) { // 來自addClass_info.jsp的請求  
			
			List<String> erMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("erMsgs", erMsgs);
			
//			Map<String, String[]> map = req.getParameterMap();
//			Set<String> keySet = map.keySet();
//			for(String keyString : keySet) {
//				System.out.println(keyString);
//				System.out.println(map.get(keyString));
//			}
			
			

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//String class_id = req.getParameter("class_id");
//				String enameReg = "^[(a-zA-Z0-9)]{10}$";
//				if (class_id == null || class_id.trim().length() == 0) {
//					erMsgs.add("課程編號: 請勿空白");
//				} else if(!class_id.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					erMsgs.add("課程編號: 只能是英文字母和數字, 且長度必須為10");
//	            }
				
				String class_name = req.getParameter("class_name");
				if (class_name == null || class_name.trim().length() == 0) {
					erMsgs.add("課程名稱: 請勿空白");
				}
				
				String member_id = req.getParameter("member_id");
				String memidReg = "^[(a-zA-Z0-9_)]{8}$";
				if (member_id == null || member_id.trim().length() == 0) {
					erMsgs.add("會員編號: 請勿空白");
				} 
				else if(!member_id.trim().matches(memidReg)) { //以下練習正則(規)表示式(regular-expression)
					erMsgs.add("會員編號: 只能是英文字母和數字, 且長度必須為10");
	            }
				
				String subclass_id = req.getParameter("subclass_id");
				String subclass_idReg = "^[(a-zA-Z0-9_)]{8}$";
				if (subclass_id == null || subclass_id.trim().length() == 0) {
					erMsgs.add("副類別課程編號: 請勿空白");
				} 
				else if(!subclass_id.trim().matches(subclass_idReg)) { //以下練習正則(規)表示式(regular-expression)
					erMsgs.add("副類別課程編號: 只能是英文字母和數字, 且長度必須為10");
	            }
				
				Timestamp startfund_date = null;
				try {					
					startfund_date = java.sql.Timestamp.valueOf(req.getParameter("startfund_date").trim());
				} catch (IllegalArgumentException e) {
					erMsgs.add("請輸入開始募資日期");
					System.out.println(startfund_date);
				}
				
//				java.sql.Date hiredate = null;
//				try {
//hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				
				Timestamp startclass_time = null;
				try {
					startclass_time = java.sql.Timestamp.valueOf(req.getParameter("startclass_time").trim());
//					System.out.println("1");
//					System.out.println(startclass_time);
				} catch (IllegalArgumentException e) {
//					startclass_time = new java.sql.Timestamp(System.currentTimeMillis());
					erMsgs.add("請輸入開課日期");
//					System.out.println(startclass_time);
				}
				
				String class_description = req.getParameter("class_description");
				if (class_description == null || class_description.trim().length() == 0) {
					erMsgs.add("課程描述: 請勿空白");
				} 
//				
				Part class_picture = req.getPart("class_picture");
				byte[] buffer = null;
				InputStream fis = class_picture.getInputStream();
				if(fis.available() != 0) {
					buffer = new byte[fis.available()];
					fis.read(buffer);
					fis.close();
				}else {
					erMsgs.add("請上傳課程圖片");
				}
				
				Integer startfund_price = Integer.valueOf(req.getParameter("startfund_price"));
				String startfund_priceReg = "^[(0-9)]";
				if (req.getParameter("startfund_price") == null || req.getParameter("startfund_price").trim().length() == 0) {
					erMsgs.add("募資售價: 請勿空白");
				} 
				else if(!req.getParameter("startfund_price").trim().matches(startfund_priceReg) && startfund_price < 50) { //以下練習正則(規)表示式(regular-expression)
					erMsgs.add("募資售價: 只能是數字, 且金額必須大於50");
	            }
				
				Integer original_price = Integer.valueOf(req.getParameter("original_price"));
				String original_priceReg = "^[(0-9)]";
				if (req.getParameter("original_price") == null || req.getParameter("original_price").trim().length() == 0) {
					erMsgs.add("定價: 請勿空白");
				}
				else if(!req.getParameter("original_price").trim().matches(original_priceReg) && original_price < 100) { //以下練習正則(規)表示式(regular-expression)
					erMsgs.add("定價: 只能是數字, 且金額必須大於100");
	            }
				
				Integer people_threshold = Integer.valueOf(req.getParameter("people_threshold"));
				String peopleTReg = "^[(0-9)]";
				if (req.getParameter("people_threshold") == null || req.getParameter("people_threshold").trim().length() == 0) {
					erMsgs.add("門檻人數: 請勿空白");
				} 
				else if(!req.getParameter("people_threshold").trim().matches(peopleTReg) && people_threshold < 30) { //以下練習正則(規)表示式(regular-expression)
					erMsgs.add("門檻人數: 只能是數字, 且必須大於30");
	            }
				
				String class_length = req.getParameter("class_length");
//				String class_lengthReg = "^[(a-zA-Z0-9_)]{10}$";
//				int peopleT = Integer.parseInt(people_threshold);
				if (class_length == null || class_length.trim().length() == 0) {
					erMsgs.add("課程長度: 請勿空白");
				}
//				 else if(!people_threshold.trim().matches(peopleTReg) && peopleT >= 30) { //以下練習正則(規)表示式(regular-expression)
//					erMsgs.add("門檻人數: 只能是數字, 且必須大於30");
//	            }		
//				
				
				Part video_fundraising = req.getPart("video_fundraising");
				byte[] bufferVF = null;
				InputStream fisVF = video_fundraising.getInputStream();
				if(fisVF.available() != 0) {
					bufferVF = new byte[fisVF.available()];
					fisVF.read(bufferVF);
					fisVF.close();
				}else {
					erMsgs.add("請上傳募資影片");
				}
				
				Timestamp update_time = null;
				update_time = new java.sql.Timestamp(System.currentTimeMillis());
				
				String admin_id = null;
				
				Integer class_status = 7;
				

				Class_infoVO class_infoVO = new Class_infoVO();
				class_infoVO.setClass_name(class_name);
				class_infoVO.setMember_id(member_id);
				class_infoVO.setSubclass_id(subclass_id);
				class_infoVO.setStartfund_date(startfund_date);
				class_infoVO.setStartclass_time(startclass_time);
				class_infoVO.setClass_description(class_description);
				class_infoVO.setClass_picture(buffer);
				class_infoVO.setStartfund_price(startfund_price);
				class_infoVO.setOriginal_price(original_price);
				class_infoVO.setPeople_threshold(people_threshold);
				class_infoVO.setClass_length(class_length);
				

				// Send the use back to the form, if there were errors
				if (!erMsgs.isEmpty()) {
					req.setAttribute("Class_infoVO", class_infoVO); // 含有輸入格式錯誤的Class_infoVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/class_info/addClass_info.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Class_infoService ciSvc = new Class_infoService();
				class_infoVO = ciSvc.addClass_info(class_name, member_id, class_status, subclass_id, startfund_date, startclass_time, class_description, buffer, startfund_price, original_price, people_threshold, class_length, bufferVF, update_time, admin_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/class_info/listAllClass_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				erMsgs.add(e.getMessage());
//				System.out.println(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/class_info/addClass_info.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllClass_info.jsp

			List<String> erMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("erMsgs", erMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String class_id = req.getParameter("class_id");
				System.out.println(class_id);
				
				/***************************2.開始刪除資料***************************************/
				Class_infoService ciSvc = new Class_infoService();
				ciSvc.deleteClass_info(class_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/class_info/listAllClass_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				erMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/class_info/listAllClass_info.jsp");
				failureView.forward(req, res);
			}
		}
		

//		接收ajax傳來的資料
		if ("class2Form".equals(action)) { // from verify_list2.jsp
			HttpSession session = req.getSession();

			// 把課程名稱存入session
			String class_name = req.getParameter("class_name");
			session.setAttribute("verifyClass_name", class_name);

			Integer original_price = Integer.valueOf(req.getParameter("original_price"));
			session.setAttribute("verifyOriginal_price", original_price);

			Integer startfund_price = Integer.valueOf(req.getParameter("startfund_price"));
			session.setAttribute("verifyStartfund_price", startfund_price);

			Integer people_threshold = Integer.valueOf(req.getParameter("people_threshold"));
			session.setAttribute("verifyPeople_threshold", people_threshold);

			String class_length = req.getParameter("class_length");
			session.setAttribute("verifyClass_length", class_length);

			String sub_class = req.getParameter("sub_class");
			session.setAttribute("verifySub_class", sub_class);

			String class_description = req.getParameter("class_description");
			session.setAttribute("verifyClass_description", class_description);
			
		}

		if ("insert".equals(action)) { // 來自verify_list2.jsp的請求
//			System.out.println("-1");
			List<String> erMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("erMsgs", erMsgs);
			HttpSession session = req.getSession();

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String class_name = req.getParameter("class_name");
				if (class_name != null) {
					if (class_name == null || class_name.trim().length() == 0) {
						erMsgs.add("課程名稱: 請勿空白");
					}
				}


				Member_infoVO member_idVO = (Member_infoVO) session.getAttribute("member_infoVO");

				String member_id = member_idVO.getMember_id();
				String subclass_id = req.getParameter("subClass_id");
				Timestamp startfund_date = null;

				String class_description = req.getParameter("class_description");
				if (class_description != null) {
					if (class_description == null || class_description.trim().length() == 0) {
						erMsgs.add("課程描述: 請勿空白");
					}
				}
				byte[] buffer = null;

				String startfund_price = (String) req.getParameter("startfund_price");
//							Integer startfund_priceInt = Integer.valueOf(startfund_price);
				if (startfund_price != null || req.getParameter("startfund_price").equals("")) {
					String startfund_priceReg = "^[0-9]*$";
					if (req.getParameter("startfund_price") == null
							|| req.getParameter("startfund_price").trim().length() == 0) {
						erMsgs.add("募資售價: 請勿空白");
					} else if (req.getParameter("startfund_price").trim().matches(startfund_priceReg)
							&& Integer.valueOf(startfund_price) < 50) { // 以下練習正則(規)表示式(regular-expression)
						erMsgs.add("募資售價: 只能是數字, 且金額必須大於50");
					}
				}

				String original_price = (String) req.getParameter("original_price");
//							Integer original_priceInt = Integer.valueOf(original_price);
				String original_priceReg = "^[0-9]*$";
				if (original_price != null) {
					if (req.getParameter("original_price") == null
							|| req.getParameter("original_price").trim().length() == 0) {
						erMsgs.add("定價: 請勿空白");
					} else if (req.getParameter("original_price").trim().matches(original_priceReg)
							&& Integer.valueOf(original_price) < 100) { // 以下練習正則(規)表示式(regular-expression)
						erMsgs.add("定價: 只能是數字, 且金額必須大於100");
					}
				}
				
				String people_threshold = (String) req.getParameter("people_threshold");
//							Integer people_thresholdInt = Integer.valueOf(people_threshold);
				if (people_threshold != null) {
					String peopleTReg = "^[0-9]*$";
					if (req.getParameter("people_threshold") == null
							|| req.getParameter("people_threshold").trim().length() == 0) {
						erMsgs.add("門檻人數: 請勿空白");
					} else if (req.getParameter("people_threshold").trim().matches(peopleTReg)
							&& Integer.valueOf(people_threshold) < 30) { // 以下練習正則(規)表示式(regular-expression)
						erMsgs.add("門檻人數: 只能是數字, 且必須大於30");
					}
				}
				String class_length = req.getParameter("class_length");

				if (class_length != null) {
					if (class_length == null || class_length.trim().length() == 0) {
						erMsgs.add("課程長度: 請勿空白");
					}
				}

				byte[] bufferVF = null;

				Timestamp update_time = null;
				update_time = new java.sql.Timestamp(System.currentTimeMillis());
				

				String admin_id = null;

				Integer class_status = 7;

				Class_infoVO class_infoVO = new Class_infoVO();
				class_infoVO.setClass_name(class_name);
				class_infoVO.setMember_id(member_id);
				class_infoVO.setSubclass_id(subclass_id);
				class_infoVO.setStartfund_date(startfund_date);
				class_infoVO.setStartclass_time(null);
				class_infoVO.setClass_description(class_description);
				class_infoVO.setClass_picture(buffer);
				class_infoVO.setStartfund_price(Integer.valueOf(startfund_price));
				class_infoVO.setOriginal_price(Integer.valueOf(original_price));
				class_infoVO.setPeople_threshold(Integer.valueOf(people_threshold));
				class_infoVO.setClass_length(class_length);

				// Send the use back to the form, if there were errors
				if (!erMsgs.isEmpty()) {
					req.setAttribute("Class_infoVO", class_infoVO); // 含有輸入格式錯誤的Class_infoVO物件,也存入req
//System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/verify_list/verify_list2.jsp");
					failureView.forward(req, res);
					return;
				}
//System.out.println("!erMsgs.isEmpty() XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");

				/*************************** 2.開始新增資料 ***************************************/
				Class_infoService ciSvc = new Class_infoService();
				class_infoVO = ciSvc.addClass_info(class_name, member_id, class_status, subclass_id, startfund_date,
						null, class_description, buffer, Integer.valueOf(startfund_price),
						Integer.valueOf(original_price), Integer.valueOf(people_threshold), class_length, bufferVF,
						update_time, admin_id);

				// 取出剛新增的課程之id
				Class_infoVO findClass_id = ciSvc.findClass_id(class_name, member_id, subclass_id);
				String class_id = findClass_id.getClass_id();
				List<Class_infoVO> member_teach = (List<Class_infoVO>)session.getAttribute("member_teach");
				member_teach.add(findClass_id);
				session.setAttribute("member_teach", member_teach);
				session.setAttribute("verifyClass_id", class_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/verify_list/verify_list3.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				return;
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				erMsgs.add(e.getMessage());
//							System.out.println(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/verify_list/verify_list2.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		// 接收ajax傳來的資料
		if ("class3Form".equals(action)) { // from verify_list3.jsp
			HttpSession session = req.getSession();

			try {

				// 從session中取得審核中的課程編號
				String class_id = (String) session.getAttribute("verifyClass_id");
//			String class_id = "CLA00062";

				String chapterClass_name = req.getParameter("chapterClass_name");
//System.out.println(chapterClass_name);

				String unitclass_name = req.getParameter("unitClass_name");
//System.out.println(unitclass_name);
				Timestamp video_updatetime = new Timestamp(System.currentTimeMillis());

				Class_chapterVO class_chapterVO = new Class_chapterVO();
				class_chapterVO.setChapter_name(chapterClass_name);
				class_chapterVO.setClass_id(class_id);

//System.out.println("555");
				String[] unitclass_list = unitclass_name.toString().split("不");
				List<Class_unitVO> classUnitList = new ArrayList<Class_unitVO>();
				for (int i = 0; i < unitclass_list.length; i++) {
					Class_unitVO class_unitVO = new Class_unitVO();
					class_unitVO.setUnit_name(unitclass_list[i]);
					class_unitVO.setVideo_status(0);
					class_unitVO.setVideo(null);
					class_unitVO.setVideo_long(null);
					class_unitVO.setVideo_updatetime(video_updatetime);
					classUnitList.add(class_unitVO);
				}
//System.out.println("66666666666");
				/*************************** 2.開始修改資料 *****************************************/
				Class_chapterService ccSrc = new Class_chapterService();
				ccSrc.inserwithunit(class_chapterVO, classUnitList);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//System.out.println("7777777777");
				
				AllClass_infoService allClassInfoSrv = new AllClass_infoService();
				AllClass_infoVO all = allClassInfoSrv.getAll(class_id);
				Set<Class_chapterVO> Chapter = (Set<Class_chapterVO>) all.getChapterAndUnit().keySet();
				List<Class_chapterVO> ChapterList = new ArrayList<Class_chapterVO>();
				ServletContext context = getServletContext();
				Map<String, List<Class_chapterVO>> initClass = (Map<String, List<Class_chapterVO>>)context.getAttribute("initClass");
				Map<String, List<Class_unitVO>> initChapter = (Map<String, List<Class_unitVO>>)context.getAttribute("initChapter");
				
				for(Class_chapterVO item :Chapter) {
					ChapterList.add(item);
					initChapter.put(item.getChapter_id(),all.getChapterAndUnit().get(item));
				}
				initClass.put(class_id,(List<Class_chapterVO>) ChapterList);
				
				String url = "/front-end/verify_list/verify_list4.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/verify_list/verify_list1.jsp");
				failureView.forward(req, res);
				return;
			}

		}

		// 取出存在Session中的課程資訊送出審核

		if ("class4form".equals(action)) { // 來自verify_list4.jsp的請求

			List<String> erMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("erMsgs", erMsgs);
			HttpSession session = req.getSession();

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

System.out.println("0000000000");
				Part class_picture = req.getPart("class_picture");
//						Part class_picture = (Part)session.getAttribute("verifyClass_picture");
				byte[] buffer = null;
				if (class_picture != null) {
					InputStream fis = class_picture.getInputStream();
					if (fis.available() != 0) {
						buffer = new byte[fis.available()];
						fis.read(buffer);
						fis.close();
					} else {
						erMsgs.add("請上傳課程圖片");
					}
				}
System.out.println("11111111111111");
				Part video_fundraising = req.getPart("video_fundraising");
//						Part video_fundraising = (Part)session.getAttribute("verifyVideo_fundraising");
				byte[] bufferVF = null;
				InputStream fisVF = video_fundraising.getInputStream();
				if (fisVF.available() != 0) {
					bufferVF = new byte[fisVF.available()];
					fisVF.read(bufferVF);
					fisVF.close();
				} else {
					erMsgs.add("請上傳募資影片");
				}
System.out.println("22222222222");
				Timestamp update_time = null;
				update_time = new java.sql.Timestamp(System.currentTimeMillis());

				// 改成從session撈
//				String class_id = "CLA00061";
				String class_id = (String) session.getAttribute("verifyClass_id");
System.out.println(class_id);
				Class_infoVO class_infoVO = new Class_infoVO();
				class_infoVO.setClass_picture(buffer);
				class_infoVO.setVideo_fundraising(bufferVF);

				// Send the use back to the form, if there were errors
				if (!erMsgs.isEmpty()) {
					req.setAttribute("Class_infoVO", class_infoVO); // 含有輸入格式錯誤的Class_infoVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/verify_list/verify_list4.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Class_infoService ciSvc = new Class_infoService();
				ciSvc.updateFund(buffer, bufferVF, update_time, class_id);
System.out.println("3333333333333333");
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front-end/verify_list/verify_list5.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				erMsgs.add(e.getMessage());
				System.out.println(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/verify_list/verify_list4.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		// 上傳教課課程 來自verify_list5.jsp
		if ("uploadUnit".equals(action)) {
			
			HttpSession session = req.getSession();

			List<String> erMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("erMsgs", erMsgs);
			java.sql.Timestamp video_updatetime = new java.sql.Timestamp(new Date().getTime());

//			List<Byte[]> uploadList = new ArrayList<Byte[]>();
			Map<String, String[]> paraMap = req.getParameterMap();	
			Set<String> set = paraMap.keySet();
			for(String word:set) {
				if(!word.equals("action")) {
					Part uploadClass=req.getPart(word);
					System.out.println(uploadClass.getContentType());
					byte[] uploadClassData = null;
					InputStream is = uploadClass.getInputStream();		
//				    if (is.available() != 0) { //要從前端JS擋是否全部都有上傳檔案
					    uploadClassData = new byte[is.available()];
					    is.read(uploadClassData);
					    is.close();
				Class_unitService service = new Class_unitService();
				service.updateVideo(word, uploadClassData, video_updatetime);
//				    }
				}
			}
			
			Integer class_status = 0;
			String admin_id = null;
			String class_id = (String)session.getAttribute("verifyClass_id");
			System.out.println(class_id);
			Class_infoService ciSvc = new Class_infoService();
			ciSvc.updateStatus(class_status, admin_id, class_id);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			// 轉交
			String url = "/front-end/verify_list/verify_list6.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		
	}


}
