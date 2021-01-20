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
	// ���o�ӷ��Ѽ�
				String action = req.getParameter("action");
				
// �Ӧ�select_pagePostMessage.jsp���ШD (getOne_For_Display)
				if ("getOne_For_Display".equals(action)) { 

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
						     String post_id = req.getParameter("post_id");
						     String post_idReg = "^[P][I][(0-9)]{5}$";
						if (post_id == null || post_id.trim().length() == 0) {
							errorMsgs.add("�п�J���i�T���s��");
						}
						else if(!post_id.matches(post_idReg)) {
							errorMsgs.add("�榡���~");
						}

						if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/post_message/select_pagePostMessage.jsp");
							failureView.forward(req, res);
							return;// �{�����_
						}
						/*************************** 2.�}�l�d�߸�� *****************************************/
						Post_messageService post_messageSvc = new Post_messageService();
						Post_messageVO post_messageVO = post_messageSvc.getOnePost_message(post_id);

						/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
						req.setAttribute("post_messageVO", post_messageVO); // ��Ʈw���X��post_messageVO����,�s�Jreq
						String url = "/back-end/post_message/listOnePostMessage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOnePostMessage.jsp
						successView.forward(req, res);

						/*************************** ��L�i�઺���~�B�z *************************************/
					} catch (Exception e) {
						errorMsgs.add("�L�k���o���:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/post_message/select_pagePostMessage.jsp");
						failureView.forward(req, res);
					}
				}
				
// �Ӧ�listAllPostMessage.jsp���ШD (getOne_For_Update)				
				if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllPostMessage.jsp���ШD

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.�����ШD�Ѽ� ****************************************/
						String post_id = new String(req.getParameter("post_id"));
						System.out.println(post_id);
						/*************************** 2.�}�l�d�߸�� ****************************************/
						Post_messageService post_messageSvc = new Post_messageService();
						Post_messageVO post_messageVO = post_messageSvc.getOnePost_message(post_id);
						System.out.println(post_messageVO);
						/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
						req.setAttribute("post_messageVO", post_messageVO); // ��Ʈw���X��Post_messageVO����,�s�Jreq
						String url = "/back-end/post_message/update_postMessage_input.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_PostMessage_input.jsp
						successView.forward(req, res);

						/*************************** ��L�i�઺���~�B�z **********************************/
					} catch (Exception e) {
						errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/post_message/listAllPostMessage.jsp");
						failureView.forward(req, res);
					}
				}	

// �Ӧ�update_PostMessage_input.jsp���ШD  (update)
				if ("update".equals(action)) { // �Ӧ�update_postMessage_input.jsp���ШD

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				 String post_id = req.getParameter("post_id");
						String post_idReg = "^[P][I][(0-9)]{0,8}$";
						if (post_id == null || post_id.trim().length() == 0) {
							errorMsgs.add("���i�T���s��: �ФŪť�");
						} else if (!post_id.trim().matches(post_idReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
							errorMsgs.add("���i�T���s��: �u��O�^��r���B�Ʀr ");
						}

				 String post_content = req.getParameter("post_content").trim();
						if (post_content == null || post_content.trim().length() == 0) {
							errorMsgs.add("���i���e: �ФŪť�");
						} 
						

//						java.sql.Timestamp send_time = null;
//						try {
//							send_time = java.sql.Timestamp.valueOf(req.getParameter("send_time").trim());
//						} catch (IllegalArgumentException e) {
//							send_time = new java.sql.Timestamp(System.currentTimeMillis());
//							errorMsgs.add("�п�J�ɶ�!");
//						}
						
						java.sql.Timestamp send_time = 
								new java.sql.Timestamp(new java.util.Date().getTime());

				String admin_id = req.getParameter("admin_id").trim();
				          String admin_idReg = "^[A][I][(0-9)]{0,8}$";
				       if (admin_id == null || admin_id.trim().length() == 0) {
					      errorMsgs.add("���i�H(�޲z���s��): �ФŪť�");
				        } else if (!admin_id.trim().matches(admin_idReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					     errorMsgs.add("���i�H(�޲z���s��): �u��O�^��r���B�Ʀr ");
				        }

				       Integer target_type = null;
						try {
							target_type = new Integer(req.getParameter("target_type").trim());
						} catch (NumberFormatException e) {
							target_type = 1;
							errorMsgs.add("���i��H����:�ФŪť�");
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
							req.setAttribute("post_messagevo", post_messagevo); // �t����J�榡���~��post_messagevo����,�]�s�Jreq
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/post_message/update_postMessage_input.jsp");
							failureView.forward(req, res);
							return; // �{�����_
						}

						/*************************** 2.�}�l�ק��� *****************************************/
						Post_messageService post_messageSvc = new Post_messageService();
						post_messagevo = post_messageSvc.updatePost_message(post_id, post_content, send_time, admin_id,
								target_type);

						/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
						req.setAttribute("post_messagevo", post_messagevo); // ��Ʈwupdate���\��,���T����post_messagevo����,�s�Jreq
						String url = "/back-end/post_message/listAllPostMessage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOnePostMessage.jsp
						successView.forward(req, res);

						/*************************** ��L�i�઺���~�B�z *************************************/
					} catch (Exception e) {
						errorMsgs.add("�ק��ƥ���:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/post_message/update_postMessage_input.jsp");
						failureView.forward(req, res);
					}
				}			
				
// �Ӧ�addPostMessage.jsp���ШD (insert)
				if ("insert".equals(action)) { 
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
				try {
					System.out.println("1111111");
				 String post_content = req.getParameter("post_content");
						if (post_content == null || post_content.trim().length() == 0) {
							errorMsgs.add("���i���e: �ФŪť�");
						} 
						java.sql.Timestamp send_time = 
								new java.sql.Timestamp(new java.util.Date().getTime());
				String admin_id = req.getParameter("admin_id");
				          String admin_idReg = "^[A][I][(0-9)]{0,8}$";
				       if (admin_id == null || admin_id.trim().length() == 0) {
					      errorMsgs.add("���i�H(�޲z���s��): �ФŪť�");
				        } else if (!admin_id.matches(admin_idReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					     errorMsgs.add("���i�H(�޲z���s��): �u��O�^��r���B�Ʀr ");
				        }
				 Integer target_type = null;
						try {
							target_type = new Integer(req.getParameter("target_type"));
						} catch (NumberFormatException e) {
							target_type = 1;
							errorMsgs.add("���i��H����:�ФŪť�");
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
							req.setAttribute("post_messagevo", post_messagevo); // �t����J�榡���~��post_messagevo����,�]�s�Jreq
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/post_message/update_postMessage_input.jsp");
							failureView.forward(req, res);
							return; // �{�����_
						}
						System.out.println("6");
						/*************************** 2.�}�l�s�W��� *****************************************/
						Post_messageService post_messageSvc = new Post_messageService();
						System.out.println("7");
						post_messageSvc.aaddPost_message(post_content, send_time,admin_id, target_type);
						System.out.println("8");
						/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
						req.setAttribute("post_messagevo", post_messagevo); // ��Ʈwupdate���\��,���T����post_messagevo����,�s�Jreq
						String url = "/back-end/post_message/listAllPostMessage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOnePostMessage.jsp
						successView.forward(req, res);

						/*************************** ��L�i�઺���~�B�z *************************************/
					} catch (Exception e) {
						errorMsgs.add("�ק��ƥ���:" + e.getMessage());
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
						/*************************** 1.�����ШD�Ѽ� ***************************************/
						String post_id = new String(req.getParameter("post_id"));

						/*************************** 2.�}�l�R����� ***************************************/
						Post_messageService post_messageSvc = new Post_messageService();
						post_messageSvc.deletepost_message(post_id);

						/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
						String url = "/back-end/post_message/listAllPostMessage.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
						successView.forward(req, res);

						/*************************** ��L�i�઺���~�B�z **********************************/
					} catch (Exception e) {
						errorMsgs.add("�R����ƥ���:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/post_message/listAllPostMessage.jsp");
						failureView.forward(req, res);
					}
				}

			}

		}