package com.keyword_form.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keyword_form.model.Keyword_formService;
import com.keyword_form.model.Keyword_formVO;
import com.member_info.model.Member_infoService;

@WebServlet("/keyword_form/Keyword_formServlet")
public class Keyword_formServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("------------------------");
		System.out.println("Keyword_formController");
		System.out.println("action = " + action);
		System.out.println("------------------------");

		if ("get_by_date".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String startdate = req.getParameter("startdate");
				String enddate = req.getParameter("enddate");
				if (startdate == null || (startdate.trim()).length() == 0) {
					errorMsgs.add("請選擇開始日期");
				}
				if (enddate == null || (enddate.trim()).length() == 0) {
					errorMsgs.add("請選擇結束日期");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Keyword_formService keyword_formSvc = new Keyword_formService();
				List<Keyword_formVO> list = (List<Keyword_formVO>) keyword_formSvc.getKeywordbydate(startdate, enddate);
				System.out.println(list);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jspp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("startdate", startdate);
				req.setAttribute("enddate", enddate);
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/keyword_form/Keyword_formbydate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
				failureView.forward(req, res);
			}
		}
		//========================統計關鍵字次數-Start============================
		if ("getcount".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String search_word;
			int count;

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				/*************************** 2.開始查詢資料 *****************************************/
				Keyword_formService keyword_formSvc = new Keyword_formService();
				List<Keyword_formVO> list = (List<Keyword_formVO>) keyword_formSvc.getAll();
				
				List <String>searchwordlist = new ArrayList<String>();
				List <Integer>countlist = new ArrayList<Integer>();
				
				Map<String,Integer> map = new HashMap<String,Integer>();
				for(Keyword_formVO keylist:list) {
				search_word = keylist.getSearch_word()+"";
//				System.out.println(search_word);
				searchwordlist.add(search_word);
				}
				for(String a:searchwordlist) {
//					System.out.println(a);
					count=keyword_formSvc.getKeywordCount(a);
					countlist.add(count);
					map.put(a, count);
				}
				System.out.println(map);
//				for(String k:map.keySet()) {
//					System.out.println(k);
//				}
//				for(int y:map.values()) {
//					System.out.println(y);
//				}
				
				if (map.isEmpty()) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("map", map); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/keyword_form/Keyword_formCount.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
			
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//========================統計關鍵字次數-END==============================
		

	if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.接收請求參數****************************************/
			String keyword_id = req.getParameter("keyword_id").trim();
			
			/***************************2.開始查詢資料****************************************/
			Keyword_formService keyword_formSvc = new Keyword_formService();
			Keyword_formVO keyword_formVO = keyword_formSvc.getOnekeyword_form(keyword_id);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("keyword_formVO", keyword_formVO);         // 資料庫取出的empVO物件,存入req
			String url = "/back-end/keyword_form/update_Keyword_form.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/keyword_form/listAllKeyword_form.jsp");
			failureView.forward(req, res);
		}
	}

	if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
	
		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			//keyword_id為seq生成，不可更改
			String keyword_id = req.getParameter("keyword_id").trim();
			System.out.println("1");
			//
			Keyword_formService keyword_formSvc = new Keyword_formService();
			Keyword_formVO keyword_formvo = keyword_formSvc.getOnekeyword_form(keyword_id);
			
			java.sql.Date search_date = null;
			try {
			search_date = java.sql.Date.valueOf(req.getParameter("search_date").trim());
			}catch (IllegalArgumentException e) {
				search_date=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			System.out.println("2");
			
			String search_word = req.getParameter("search_word");
			if (search_word == null || search_word.trim().length() == 0) {
				search_word = (keyword_formvo.getSearch_word() != null) ? keyword_formvo.getSearch_word() : "請勿空白";
				errorMsgs.add("搜尋內容請勿空白");
			}	
			System.out.println("3");
			
	
			Keyword_formVO keyword_formVO = new Keyword_formVO();
			
			keyword_formVO.setKeyword_id(keyword_id);
			keyword_formVO.setSearch_date(search_date);
			keyword_formVO.setSearch_word(search_word);
			System.out.println("填裝完成");

//		 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("keyword_formVO", keyword_formVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/keyword_form/update_Keyword_form.jsp");
				failureView.forward(req, res);
				return; //程式中斷
	}
			
			/***************************2.開始修改資料*****************************************/
			keyword_formVO = keyword_formSvc.updatekeyword_form(keyword_id, search_date, search_word);
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("keyword_formVO", keyword_formVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/keyword_form/listOneKeyword_form.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/keyword_form/update_Keyword_form.jsp");
			failureView.forward(req, res);
		}
	}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				java.sql.Date search_date = null;
				try {
				search_date = java.sql.Date.valueOf(req.getParameter("search_date").trim());
				}catch (IllegalArgumentException e) {
					search_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請選擇日期!");
				}
				//輸入的字空白要斷開儲存，還要研究
				String search_word = req.getParameter("search_word").trim();
//				String search_wordReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
//				if (search_word == null || search_word.trim().length() == 0) {
//					errorMsgs.add("");
//				} else if (!search_word.trim().matches(search_word)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("搜尋內容: 只能是中、英文字母, 且長度必需在2到20之間");
//				}
				
//				} catch (NumberFormatException e) {
//					errorMsgs.add(e.getMessage());
//				}catch(RuntimeException e) {
//					errorMsgs.add(e.getMessage());
//				}
				
//			    String keyword_id = req.getParameter("keyword_id");

				Keyword_formVO keyword_formVO = new Keyword_formVO();
				keyword_formVO.setSearch_date(search_date);
				keyword_formVO.setSearch_word(search_word);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("keyword_formVO", keyword_formVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/addKeyword_form.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Keyword_formService keyword_formSvc = new Keyword_formService();
				keyword_formSvc.addKeyword_form(keyword_formVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/keyword_form/listAllKeyword_form.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllKeyword_form.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
				failureView.forward(req, res);
			}
		}
		//刪除
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String keyword_id = new String(req.getParameter("keyword_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Keyword_formService keyword_formSvc = new Keyword_formService();
				keyword_formSvc.deleteKeyword_form(keyword_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/keyword_form/Keyword_formAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
