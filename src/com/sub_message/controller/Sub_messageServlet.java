package com.sub_message.controller;

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

import com.sub_message.model.Sub_messageService;
import com.sub_message.model.Sub_messageVO;


@WebServlet("/Sub_message/Sub_messageServlet")
public class Sub_messageServlet extends HttpServlet {
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
			
// 來自select_pageSubMessage.jsp的請求 (getOne_For_Display)	
			if ("getOne_For_Display".equals(action)) { // 來自select_pageSubMessage.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
					String submsg_id = req.getParameter("submsg_id");
							String submsg_idReg = "^[S][M][(0-9)]{0,7}$";
					if (submsg_id == null || submsg_id.trim().length() == 0) {
						errorMsgs.add("請輸入小留言編號");
					}
							else if(!submsg_id.matches(submsg_idReg)) {
										errorMsgs.add("格式錯誤");
									}

					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/sub_message/select_pageSubMessage.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
					/*************************** 2.開始查詢資料 *****************************************/
					Sub_messageService sub_messageSvc = new Sub_messageService();
					Sub_messageVO sub_messageVO = sub_messageSvc.getOneSub_message(submsg_id);
					if (sub_messageVO == null) {
						errorMsgs.add("查無資料");
					}

					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/main_message/select_pageSubMessage.jsp");
						failureView.forward(req, res);
						return;// 程式中斷
					}
					/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
					req.setAttribute("sub_messageVO", sub_messageVO); // 資料庫取出的sub_messageVO物件,存入req
					String url = "/back-end/sub_message/listOneSubMessage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnesubMessage.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 *************************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("//back-end/main_message/select_pageSubMessage.jsp");
					failureView.forward(req, res);
				}
			}
			
// 來自listAllSubMessage.jsp的請求 (getOne_For_Update)			
			if ("getOne_For_Update".equals(action)) { // 來自listAllSubMessage.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 ****************************************/
					String submsg_id = new String(req.getParameter("submsg_id"));
					System.out.println(submsg_id);
					/*************************** 2.開始查詢資料 ****************************************/
					Sub_messageService sub_messageSvc = new Sub_messageService();
					Sub_messageVO sub_messageVO = sub_messageSvc.getOneSub_message(submsg_id);
					System.out.println(sub_messageVO);
					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("sub_messageVO", sub_messageVO); // 資料庫取出的sub_messageVO物件,存入req
					String url = "/back-end/sub_message/update_pageSubMessage_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_pageSubMessage_input.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/sub_message/listAllSubMessage.jsp");
					failureView.forward(req, res);
				}
			}	
			
// 來自update_pageSubMessage_input.jsp的請求 (update)			
			if ("update".equals(action)) { // 來自update_pageSubMessage_input.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						String submsg_id = req.getParameter("submsg_id");
						String mainmsg_id = req.getParameter("mainmsg_id");
						String member_id = req.getParameter("member_id");
						String submsg_text = req.getParameter("submsg_text");
						if (submsg_text == null || submsg_text.trim().length() == 0) {
							errorMsgs.add("留言請勿空白");
								}
						String submsg_status = req.getParameter("submsg_status");
						
					Sub_messageVO sub_messageVO = new Sub_messageVO();
					sub_messageVO.setMainmsg_id(mainmsg_id);
					sub_messageVO.setMember_id(member_id);
					sub_messageVO.setSubmsg_text(submsg_text);
					sub_messageVO.setSubmsg_status(Integer.parseInt(submsg_status));
					sub_messageVO.setSubmsg_id(submsg_id);
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("sub_messageVO", sub_messageVO); // 含有輸入格式錯誤的sub_messageVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/sub_message/update_pageSubMessage_input.jsp");
						failureView.forward(req, res);
						return; // 程式中斷
					}
					/*************************** 2.開始修改資料 *****************************************/
					Sub_messageService sub_messageSvc = new Sub_messageService();
					long data = new Date().getTime();
					java.sql.Timestamp submsg_time = new Timestamp(data);
					sub_messageSvc.updateSub_message(mainmsg_id, member_id, submsg_time, submsg_text, Integer.parseInt(submsg_status), submsg_id);
					/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
					req.setAttribute("sub_messageVO", sub_messageVO); // 資料庫update成功後,正確的的esub_messageVO物件,存入req
					String url = "/back-end/sub_message/listAllSubMessage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneSubMessage.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 *************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sub_message/update_pageSubMessage_input.jsp");
					failureView.forward(req, res);
				}
			}		
			
// 來自addSubMessage.jsp的請求 (insert)		
			if ("insert".equals(action)) { // 來自addSubMessage.jsp的請求

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
							
					String mainmsg_id = req.getParameter("mainmsg_id");
					String mainmsg_idReg = "^[M][M][(0-9)]{0,8}$";
					if (mainmsg_id == null || mainmsg_id.trim().length() == 0) {
						errorMsgs.add("大留言編號: 請勿空白");
					} else if (!mainmsg_id.trim().matches(mainmsg_idReg)) { // 以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("大留言編號: 只能是英文字母、數字  ");
					}

					String member_id = req.getParameter("member_id");
					String member_idReg = "^[M][E][M][(0-9)]{0,8}$";
					if (member_id == null || member_id.trim().length() == 0) {
						errorMsgs.add("會員編號: 請勿空白");
					} else if (!member_id.trim().matches(member_idReg)) { // 以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("會員編號: 只能是英文字母、數字  ");
					}
					
					String submsg_text = req.getParameter("submsg_text").trim();
					if (submsg_text == null || submsg_text.trim().length() == 0) {
						errorMsgs.add("留言請勿空白");
					}

					Integer submsg_status = null;
					try {
						submsg_status = new Integer(req.getParameter("submsg_status"));
					} catch (NumberFormatException e) {
						submsg_status = 1; // 預設回傳值
						errorMsgs.add("狀態請勿空白");
					}

					Sub_messageVO sub_messageVO = new Sub_messageVO();
					sub_messageVO.setMainmsg_id(mainmsg_id);
					sub_messageVO.setMember_id(member_id);
					sub_messageVO.setSubmsg_text(submsg_text);
					sub_messageVO.setSubmsg_status(submsg_status);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("sub_messageVO", sub_messageVO); // 含有輸入格式錯誤的sub_messageVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/sub_message/addSubMessage.jsp");
						failureView.forward(req, res);
						return;
					}

					/*************************** 2.開始新增資料 ***************************************/
					Sub_messageService sub_messageSvc = new Sub_messageService();
					long data = new Date().getTime();
					java.sql.Timestamp submsg_time = new Timestamp(data);
					sub_messageVO = sub_messageSvc.addSub_message(mainmsg_id, member_id, submsg_time,submsg_text,submsg_status);
					/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

					String url = "/back-end/sub_message/listAllSubMessage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllSubMessage.jsp
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sub_message/addSubMessage.jsp");
					failureView.forward(req, res);
				}
			}
			
// listAllSubMessage.jsp (delete)			
			if ("delete".equals(action)) { // 來自listAllSubMessage.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					/*************************** 1.接收請求參數 ***************************************/
					String submsg_id = new String(req.getParameter("submsg_id"));

					/*************************** 2.開始刪除資料 ***************************************/
					Sub_messageService sub_messageSvc = new Sub_messageService();
					sub_messageSvc.deleteSub_message(submsg_id);

					/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
					String url = "/back-end/sub_message/listAllSubMessage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);

					/*************************** 其他可能的錯誤處理 **********************************/
				} catch (Exception e) {
					errorMsgs.add("刪除資料失敗:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Sub_message/listAllSubMessage.jsp");
					failureView.forward(req, res);
				}
			}

		}
}
