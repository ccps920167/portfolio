package com.post_message.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.post_message.model.Post_messageService;
import com.post_message.model.Post_messageVO;


@WebServlet("/Post_message/Post_messageServlet")
public class Post_messageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//doGet	
		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			doPost(req, res);
		}
		
//doPost    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
	// 取得來源參數
				String action = req.getParameter("action");
				
// 來自select_pagePostMessage.jsp的請求 (getOne_For_Display)
				if ("getOne_For_Display".equals(action)) { 

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						     String post_id = req.getParameter("post_id");
						     String post_idReg = "^[P][I][(0-9)]{5}$";
						if (post_id == null || post_id.trim().length() == 0) {
							errorMsgs.add("請輸入公告訊息編號");
						}
						else if(!post_id.matches(post_idReg)) {
							errorMsgs.add("格式錯誤");
						}

						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/post_message/select_pagePostMessage.jsp");
							failureView.forward(req, res);
							return;// 程式中斷
						}
						/*************************** 2.開始查詢資料 *****************************************/
						Post_messageService post_messageSvc = new Post_messageService();
						Post_messageVO post_messageVO = post_messageSvc.getOnePost_message(post_id);

						/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
						req.setAttribute("post_messageVO", post_messageVO); // 資料庫取出的post_messageVO物件,存入req
						String url = "/back-end/post_message/listOnePostMessage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePostMessage.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 *************************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得資料:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/post_message/select_pagePostMessage.jsp");
						failureView.forward(req, res);
					}
				}
				
// 來自listAllPostMessage.jsp的請求 (getOne_For_Update)				
				if ("getOne_For_Update".equals(action)) { // 來自listAllPostMessage.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 ****************************************/
						String post_id = new String(req.getParameter("post_id"));
						System.out.println(post_id);
						/*************************** 2.開始查詢資料 ****************************************/
						Post_messageService post_messageSvc = new Post_messageService();
						Post_messageVO post_messageVO = post_messageSvc.getOnePost_message(post_id);
						System.out.println(post_messageVO);
						/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
						req.setAttribute("post_messageVO", post_messageVO); // 資料庫取出的Post_messageVO物件,存入req
						String url = "/back-end/post_message/update_postMessage_input.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_PostMessage_input.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/post_message/listAllPostMessage.jsp");
						failureView.forward(req, res);
					}
				}	

// 來自update_PostMessage_input.jsp的請求  (update)
				if ("update".equals(action)) { // 來自update_postMessage_input.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				 String post_id = req.getParameter("post_id");
						String post_idReg = "^[P][I][(0-9)]{0,8}$";
						if (post_id == null || post_id.trim().length() == 0) {
							errorMsgs.add("公告訊息編號: 請勿空白");
						} else if (!post_id.trim().matches(post_idReg)) { // 以下練習正則(規)表示式(regular-expression)
							errorMsgs.add("公告訊息編號: 只能是英文字母、數字 ");
						}

				 String post_content = req.getParameter("post_content").trim();
						if (post_content == null || post_content.trim().length() == 0) {
							errorMsgs.add("公告內容: 請勿空白");
						} 
						

//						java.sql.Timestamp send_time = null;
//						try {
//							send_time = java.sql.Timestamp.valueOf(req.getParameter("send_time").trim());
//						} catch (IllegalArgumentException e) {
//							send_time = new java.sql.Timestamp(System.currentTimeMillis());
//							errorMsgs.add("請輸入時間!");
//						}
						
						java.sql.Timestamp send_time = 
								new java.sql.Timestamp(new java.util.Date().getTime());

				String admin_id = req.getParameter("admin_id").trim();
				          String admin_idReg = "^[A][I][(0-9)]{0,8}$";
				       if (admin_id == null || admin_id.trim().length() == 0) {
					      errorMsgs.add("公告人(管理員編號): 請勿空白");
				        } else if (!admin_id.trim().matches(admin_idReg)) { // 以下練習正則(規)表示式(regular-expression)
					     errorMsgs.add("公告人(管理員編號): 只能是英文字母、數字 ");
				        }

				       Integer target_type = null;
						try {
							target_type = new Integer(req.getParameter("target_type").trim());
						} catch (NumberFormatException e) {
							target_type = 1;
							errorMsgs.add("公告對象身份:請勿空白");
						}

					
						String str = new String(req.getParameter("post_id").trim());
						Post_messageVO post_messagevo = new Post_messageVO();

						post_messagevo.setPost_id(post_id);
						post_messagevo.setPost_content(post_content);
						post_messagevo.setSend_time(send_time);
						post_messagevo.setAdmin_id(admin_id);
						post_messagevo.setTarget_type(target_type);
					

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("post_messagevo", post_messagevo); // 含有輸入格式錯誤的post_messagevo物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/post_message/update_postMessage_input.jsp");
							failureView.forward(req, res);
							return; // 程式中斷
						}

						/*************************** 2.開始修改資料 *****************************************/
						Post_messageService post_messageSvc = new Post_messageService();
						post_messagevo = post_messageSvc.updatePost_message(post_id, post_content, send_time, admin_id,
								target_type);

						/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
						req.setAttribute("post_messagevo", post_messagevo); // 資料庫update成功後,正確的的post_messagevo物件,存入req
						String url = "/back-end/post_message/listAllPostMessage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePostMessage.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 *************************************/
					} catch (Exception e) {
						errorMsgs.add("修改資料失敗:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/post_message/update_postMessage_input.jsp");
						failureView.forward(req, res);
					}
				}			
				
// 來自addPostMessage.jsp的請求 (insert)
				if ("insert".equals(action)) { 
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
				try {
					System.out.println("1111111");
				 String post_content = req.getParameter("post_content");
						if (post_content == null || post_content.trim().length() == 0) {
							errorMsgs.add("公告內容: 請勿空白");
						} 
						java.sql.Timestamp send_time = 
								new java.sql.Timestamp(new java.util.Date().getTime());
				String admin_id = req.getParameter("admin_id");
				          String admin_idReg = "^[A][I][(0-9)]{0,8}$";
				       if (admin_id == null || admin_id.trim().length() == 0) {
					      errorMsgs.add("公告人(管理員編號): 請勿空白");
				        } else if (!admin_id.matches(admin_idReg)) { // 以下練習正則(規)表示式(regular-expression)
					     errorMsgs.add("公告人(管理員編號): 只能是英文字母、數字 ");
				        }
				 Integer target_type = null;
						try {
							target_type = new Integer(req.getParameter("target_type"));
						} catch (NumberFormatException e) {
							target_type = 1;
							errorMsgs.add("公告對象身份:請勿空白");
						}
						System.out.println("5");
						Post_messageVO post_messagevo = new Post_messageVO();

						post_messagevo.setPost_content(post_content);
						post_messagevo.setSend_time(send_time);
						post_messagevo.setAdmin_id(admin_id);
						post_messagevo.setTarget_type(target_type);
						System.out.println("555555");
					

						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							System.out.println("errorMsgs");
							req.setAttribute("post_messagevo", post_messagevo); // 含有輸入格式錯誤的post_messagevo物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/post_message/update_postMessage_input.jsp");
							failureView.forward(req, res);
							return; // 程式中斷
						}
						System.out.println("6");
						/*************************** 2.開始新增資料 *****************************************/
						Post_messageService post_messageSvc = new Post_messageService();
						System.out.println("7");
						post_messageSvc.aaddPost_message(post_content, send_time,admin_id, target_type);
						System.out.println("8");
						/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
						req.setAttribute("post_messagevo", post_messagevo); // 資料庫update成功後,正確的的post_messagevo物件,存入req
						String url = "/back-end/post_message/listAllPostMessage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePostMessage.jsp
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 *************************************/
					} catch (Exception e) {
						errorMsgs.add("修改資料失敗:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/post_message/update_postMessage_input.jsp");
						failureView.forward(req, res);
					}
				}
				
//listAllPostMessage.jsp  (delete)
				if ("delete".equals(action)) {	
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);	
					try {
						/*************************** 1.接收請求參數 ***************************************/
						String post_id = new String(req.getParameter("post_id"));

						/*************************** 2.開始刪除資料 ***************************************/
						Post_messageService post_messageSvc = new Post_messageService();
						post_messageSvc.deletepost_message(post_id);

						/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
						String url = "/back-end/post_message/listAllPostMessage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						errorMsgs.add("刪除資料失敗:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/post_message/listAllPostMessage.jsp");
						failureView.forward(req, res);
					}
				}

			}

		}