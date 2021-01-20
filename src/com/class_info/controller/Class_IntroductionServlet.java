package com.class_info.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.image.ImageUtil;
import com.keyword_form.model.Keyword_formService;
import com.keyword_form.model.Keyword_formVO;
import com.order_list.model.Order_listService;

/**
 * Servlet implementation class Class_infoServlet
 */
@WebServlet("/Class_info/Class_Introduction")
public class Class_IntroductionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Class_IntroductionServlet() {
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 課程查詢頁面
		if ("search".equals(action)) {

			// ====================================紀錄使用者輸入關鍵字==========================================
			if (req.getParameter("subclass_id") == null) {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				java.sql.Date search_date = null;
				try {
					search_date = new java.sql.Date(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
					search_date = new java.sql.Date(System.currentTimeMillis());
//			    errorMsgs.add("請選擇日期!");//已預設目前日期隱藏選單
				}
				// 輸入的字空白要斷開儲存
				List<String> searchword = java.util.Arrays.asList(req.getParameter("class_name").split(" "));

				Keyword_formVO keyword_formVO = new Keyword_formVO();

				/*************************** 2.開始新增資料 ***************************************/
				Keyword_formService keyword_formSvc = new Keyword_formService();
				for (String saveinput : searchword) {

					if (!saveinput.replaceAll("[\\pP\\p{Punct}]", "").isEmpty()) {
						keyword_formVO.setSearch_date(search_date);
						keyword_formVO.setSearch_word(saveinput.replaceAll("[\\pP\\p{Punct}]", ""));
						System.out.println(search_date);
						System.out.println(saveinput);
						keyword_formSvc.addKeyword_form(keyword_formVO);
					} else {
						res.sendRedirect(req.getContextPath() + "/index.jsp");
						return;
					}
				}

				System.out.println("新增關鍵字" + keyword_formVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				// 測試用才轉交頁面，實際專案則用不到
				// String url = "/back-end/keyword_form/listAllKeyword_form.jsp";
				// RequestDispatcher successView = req.getRequestDispatcher(url); //
				// 新增成功後轉交listAllKeyword_form.jsp
				// successView.forward(req, res);

			}
			// ======================================關鍵字存到資料庫之後開始查詢================================================

			String Category = req.getParameter("Category");
			if ("class_list_search".equals(Category)) {
				String class_name = req.getParameter("class_name");
				Class_infoService Srv = new Class_infoService(); // 呼叫Service方法
				Queue<Class_infoVO> class_infoVO = (LinkedList) Srv.getAll();
				List<Class_infoVO> returnList = null;
				List<List> Conform = null;
				ArrayList<String> list = new ArrayList<String>(Arrays.asList((class_name.toUpperCase()).split(" ")));

				list.add(0, class_name);
				for (String name : list) {
					System.out.println("查找 :" + name);
					Conform = getConform(class_infoVO, name);
					class_infoVO = (Queue<Class_infoVO>) Conform.get(0);
					if (returnList == null) {
						returnList = Conform.get(1);
					} else {
						for (Class_infoVO item : (Queue<Class_infoVO>) Conform.get(1)) {
							returnList.add(item);
						}
					}

				}

				if (returnList.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/class_info/class_list_noitme.jsp");
					failureView.forward(req, res);
				} else {
					req.setAttribute("class_infoVO", returnList);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_info/class_list.jsp");
					failureView.forward(req, res);
					return;
				}

			} else if ("subclass".equals(Category)) {
				String subclass_id = req.getParameter("subclass_id");
				Class_infoService Srv = new Class_infoService(); // 呼叫Service方法
				List<Class_infoVO> class_infoVO = Srv.getAll();
				List<Class_infoVO> returnList = new ArrayList<Class_infoVO>();
				for (Class_infoVO item : class_infoVO) {
					if (item.getClass_status() == 3 || item.getClass_status() == 4 || item.getClass_status() == 2
							|| item.getClass_status() == 1) {
						if (item.getSubclass_id().equals(subclass_id)) {
							if (getSearch(item) != null) {
								returnList.add(item);
							}
						}
					}
				}
				if (returnList.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/class_info/class_list_noitme.jsp");
					failureView.forward(req, res);
				} else {
					req.setAttribute("class_infoVO", returnList);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_info/class_list.jsp");
					failureView.forward(req, res);
					return;
				}
			}
		}

		if ("class_pic_lg".equals(action)) {
			res.setContentType("image/jpeg"); // 設定回應類型
			String class_id = req.getParameter("class_id");
			Class_infoService Srv = new Class_infoService(); // 呼叫Service方法
			byte[] getClassPic = Srv.getClassPic(class_id);
			OutputStream out = res.getOutputStream(); // 建立輸出流
			int sm = 1000;
			try {
				if (getClassPic != null || getClassPic.length != 0) { // 判斷檔案是否存在
					byte[] scaledPic = ImageUtil.shrink(getClassPic, sm);
					res.setContentLength(scaledPic.length); // 通知瀏覽器檔案長度
					InputStream in = new ByteArrayInputStream(scaledPic); // 將byte[]轉換成InputStream
					byte[] buf = new byte[scaledPic.length]; // 4K buffer //設定byte[]大小
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len); // 將資料輸出
					}
				} else {
					// 查詢值不存在

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

		if ("class_pic_sm".equals(action)) {
			res.setContentType("image/jpeg"); // 設定回應類型
			String class_id = req.getParameter("class_id");
			Class_infoService Srv = new Class_infoService(); // 呼叫Service方法
			byte[] getClassPic = Srv.getClassPic(class_id);
			OutputStream out = res.getOutputStream(); // 建立輸出流
			int sm = 200;
			try {
				if (getClassPic != null || getClassPic.length != 0) { // 判斷檔案是否存在
					byte[] scaledPic = ImageUtil.shrink(getClassPic, sm);
					res.setContentLength(scaledPic.length); // 通知瀏覽器檔案長度
					InputStream in = new ByteArrayInputStream(scaledPic); // 將byte[]轉換成InputStream
					byte[] buf = new byte[scaledPic.length]; // 4K buffer //設定byte[]大小
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len); // 將資料輸出
					}
				} else {
					// 查詢值不存在

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
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected Class_infoVO getSearch(Class_infoVO class_infoVO) {
		String class_id = class_infoVO.getClass_id();
		Order_listService orderSrv = new Order_listService();
		List<Map<String, String>> people = orderSrv.getClassID(class_id);
		// 判定是否已經未超過募資人數
		if (people.size() < class_infoVO.getPeople_threshold()) {
			// 小於募資人數(募資中) + 還在募資時間
			if ((class_infoVO.getStartfund_date().getTime() + (long) 30 * 24 * 60 * 60 * 1000) > new java.util.Date()
					.getTime()) {
				return class_infoVO;
			}

		} else {
			// 判定是否已經超過募資人數
			int date_start = class_infoVO.getStartfund_date()
					.compareTo(new java.sql.Timestamp(new java.util.Date().getTime() + 60 * 24 * 60 * 60 * 1000));
			if ((class_infoVO.getStartfund_date().getTime() + (long) 30 * 24 * 60 * 60 * 1000) > new java.util.Date()
					.getTime()) {
				// 募資中+已開課
				return class_infoVO;
			}
		}
		return class_infoVO;
	}

	protected List<List> getConform(Queue<Class_infoVO> Class_infoVO, String word) {
		//送出資料
		List<List> list = new ArrayList<List>();
		//篩選不符合
		LinkedList<Class_infoVO> Class_info = new LinkedList<Class_infoVO>();
		//篩選符合(第一次篩)
		LinkedList<Class_infoVO> returnList = new LinkedList<Class_infoVO>();
		
		//如果文字有空白 陣列>1
		if (word.split(" ").length > 1) {

			//篩選完全符合
			LinkedList<Class_infoVO> first = new LinkedList<Class_infoVO>();
			//提取陣列關鍵字
			for (int i = 0; i < word.split(" ").length; i++) {
				//第一個關鍵字
				if (i == 0) {
					//篩選所有符合的內容
					for (Class_infoVO item : Class_infoVO) {
						//判斷狀態
						if (item.getClass_status() == 4 || item.getClass_status() == 1) {
							//判斷文字符合
							if ((item.getClass_name().toUpperCase()).contains(word.split(" ")[i].toUpperCase())) {
								//判斷開課狀態
								if (getSearch(item) != null) {
									//放入第一次篩選中
									returnList.add(item);
								}
							} else {
								//文字不符合放入下次篩選List
								Class_info.add(item);
							}

						}
					}
				} else {
					
					////第N個關鍵字
					for (Class_infoVO classInfo : returnList) {
						//如果沒有第一次篩選的陣列(同時符合的課程)
						if ((classInfo.getClass_name().toUpperCase()).contains(word.split(" ")[i].toUpperCase())) {
							first.add(classInfo);
						}else {
							//不存在(單一符合)
							Class_info.add(classInfo);							
						}
					}
				}
			}
			list.add(Class_info);
			list.add(first);
			return list;
		} else {

			for (Class_infoVO item : Class_infoVO) {
				if (item.getClass_status() == 4 || item.getClass_status() == 1) {
					if ((item.getClass_name().toUpperCase()).contains(word)) {
						if (getSearch(item) != null) {
							returnList.add(item);
						}
					} else {
						Class_info.add(item);
					}
				}
			}
			list.add(Class_info);
			list.add(returnList);
			return list;
		}
	}
}
