package com.main_message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.main_message.model.Main_messageService;
import com.main_message.model.Main_messageVO;



@WebServlet("/Main_message/Main_messageServlet")
public class Main_messageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
//doGet	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
//doPost
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// 取得來源參數
		String action = req.getParameter("action");

// 來自select_pageMainMessage.jsp的請求 (getOne_For_Display)
		if ("getOne_For_Display".equals(action)) { // 來自select_pageMainMessage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mainmsg_id = req.getParameter("mainmsg_id");
						String mainmsg_idReg = "^[M][M][(0-9)]{5}$";
				if (mainmsg_id == null || mainmsg_id.trim().length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
						else if(!mainmsg_id.matches(mainmsg_idReg)) {
									errorMsgs.add("格式錯誤");
								}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/main_message/select_pageMainMessage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				Main_messageVO main_messageVO = main_messageSvc.getOnemain_message(mainmsg_id);
				if (main_messageVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/main_message/select_pageMainMessage.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("main_messageVO", main_messageVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/main_message/listOneMainMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/main_message/select_pageMainMessage.jsp");
				failureView.forward(req, res);
			}
		}

// 來自listAllMainMessage.jsp的請求 (getOne_For_Update)
		if ("getOne_For_Update".equals(action)) { // 來自listAllMainMessage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String mainmsg_id = new String(req.getParameter("mainmsg_id"));
				System.out.println(mainmsg_id);
				/*************************** 2.開始查詢資料 ****************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				Main_messageVO main_messageVO = main_messageSvc.getOnemain_message(mainmsg_id);
				System.out.println(main_messageVO);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("main_messageVO", main_messageVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/main_message/update_mainMessage_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/main_message/listAllMainMessage.jsp");
				failureView.forward(req, res);
			}
		}

// 來自update_emp_input.jsp的請求 (update)
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		  String mainmsg_id = req.getParameter("mainmsg_id");
		  String class_id = req.getParameter("class_id").trim();
		  String member_id = req.getParameter("member_id");
		  String mainmsg_text = req.getParameter("mainmsg_text").trim();
				if (mainmsg_text == null || mainmsg_text.trim().length() == 0) {
					errorMsgs.add("留言請勿空白");
				}

		  String msg_source = req.getParameter("msg_source").trim();
				if (msg_source == null || msg_source.trim().length() == 0) {
					errorMsgs.add("來源請勿空白");
				}
	
	       String	msg_status = req.getParameter("msg_status");

				Main_messageVO main_messagevo = new Main_messageVO();
                main_messagevo.setClass_id(class_id);
				main_messagevo.setMember_id(member_id);
				main_messagevo.setMainmsg_text(mainmsg_text);
				main_messagevo.setMsg_source(msg_source);
				main_messagevo.setMsg_status(Integer.parseInt(msg_status));
				main_messagevo.setMainmsg_id(mainmsg_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("main_messageVO", main_messagevo); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/main_message/update_mainMessage_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				long data = new Date().getTime();
				java.sql.Timestamp mainmsg_time = new Timestamp(data);
				main_messageSvc.updateMain_message(class_id, member_id, mainmsg_time, mainmsg_text,msg_source,Integer.parseInt(msg_status), mainmsg_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("main_messageVO", main_messagevo); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/main_message/listAllMainMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/main_message/update_mainMessage_input.jsp");
				failureView.forward(req, res);
			}
		}

// 來自addMainMessage.jsp的請求 (insert)
		if ("insert".equals(action)) { // 來自addMainMessage.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String class_id = req.getParameter("class_id");
				String class_idReg = "^[C][L][A][(0-9)]{0,8}$";
				if (class_id == null || class_id.trim().length() == 0) {
					errorMsgs.add("課程編號: 請勿空白");
				} else if (!class_id.trim().matches(class_idReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("課程編號: 只能是英文字母、數字  且長度必需在4到8之間");
				}

				String member_id = req.getParameter("member_id");
				String member_idReg = "^[M][E][M][(0-9)]{0,8}$";
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				} else if (!member_id.trim().matches(member_idReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員編號: 只能是英文字母、數字  且長度必需在4到8之間");
				}

				String mainmsg_text = req.getParameter("mainmsg_text").trim();
				if (mainmsg_text == null || mainmsg_text.trim().length() == 0) {
					errorMsgs.add("留言請勿空白");
				}

				String msg_source = req.getParameter("msg_source").trim();
				if (msg_source == null || msg_source.trim().length() == 0) {
					errorMsgs.add("default student");
				}

				Integer msg_status = null;
				try {
					msg_status = new Integer(req.getParameter("msg_status").trim());
					System.out.println(msg_status);
				} catch (NumberFormatException e) {
					msg_status = 1; // 預設回傳值
					errorMsgs.add("default 1");
				}

				Main_messageVO main_messageVO = new Main_messageVO();
				main_messageVO.setClass_id(class_id);
				main_messageVO.setMember_id(member_id);
				main_messageVO.setMainmsg_text(mainmsg_text);
				main_messageVO.setMsg_source(msg_source);
				main_messageVO.setMsg_status(msg_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("main_messageVO", main_messageVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/main_message/addMainMessage.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				long data = new Date().getTime();
				java.sql.Timestamp mainmsg_time = new Timestamp(data);
				main_messageVO = main_messageSvc.addMain_message(class_id, member_id,mainmsg_time, mainmsg_text, msg_source,msg_status);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

				String url = "/back-end/main_message/listAllMainMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMainMessage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/main_message/addMainMessage.jsp");
				failureView.forward(req, res);
			}
		}

// listAllMainMessage.jsp (delete)
		if ("delete".equals(action)) { // 來自listAllMainMessage.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String mainmsg_id = new String(req.getParameter("mainmsg_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				main_messageSvc.deletemain_message(mainmsg_id);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/main_message/listAllMainMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/main_message/listAllMainMessage.jsp");
				failureView.forward(req, res);
			}
		}

	}

}