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
			// ���o�ӷ��Ѽ�
			String action = req.getParameter("action");
			
// �Ӧ�select_pageSubMessage.jsp���ШD (getOne_For_Display)	
			if ("getOne_For_Display".equals(action)) { // �Ӧ�select_pageSubMessage.jsp���ШD

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
					String submsg_id = req.getParameter("submsg_id");
							String submsg_idReg = "^[S][M][(0-9)]{0,7}$";
					if (submsg_id == null || submsg_id.trim().length() == 0) {
						errorMsgs.add("�п�J�p�d���s��");
					}
							else if(!submsg_id.matches(submsg_idReg)) {
										errorMsgs.add("�榡���~");
									}

					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/sub_message/select_pageSubMessage.jsp");
						failureView.forward(req, res);
						return;// �{�����_
					}
					/*************************** 2.�}�l�d�߸�� *****************************************/
					Sub_messageService sub_messageSvc = new Sub_messageService();
					Sub_messageVO sub_messageVO = sub_messageSvc.getOneSub_message(submsg_id);
					if (sub_messageVO == null) {
						errorMsgs.add("�d�L���");
					}

					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/main_message/select_pageSubMessage.jsp");
						failureView.forward(req, res);
						return;// �{�����_
					}
					/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
					req.setAttribute("sub_messageVO", sub_messageVO); // ��Ʈw���X��sub_messageVO����,�s�Jreq
					String url = "/back-end/sub_message/listOneSubMessage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOnesubMessage.jsp
					successView.forward(req, res);

					/*************************** ��L�i�઺���~�B�z *************************************/
				} catch (Exception e) {
					errorMsgs.add("�L�k���o���:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("//back-end/main_message/select_pageSubMessage.jsp");
					failureView.forward(req, res);
				}
			}
			
// �Ӧ�listAllSubMessage.jsp���ШD (getOne_For_Update)			
			if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllSubMessage.jsp���ШD

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.�����ШD�Ѽ� ****************************************/
					String submsg_id = new String(req.getParameter("submsg_id"));
					System.out.println(submsg_id);
					/*************************** 2.�}�l�d�߸�� ****************************************/
					Sub_messageService sub_messageSvc = new Sub_messageService();
					Sub_messageVO sub_messageVO = sub_messageSvc.getOneSub_message(submsg_id);
					System.out.println(sub_messageVO);
					/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
					req.setAttribute("sub_messageVO", sub_messageVO); // ��Ʈw���X��sub_messageVO����,�s�Jreq
					String url = "/back-end/sub_message/update_pageSubMessage_input.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_pageSubMessage_input.jsp
					successView.forward(req, res);

					/*************************** ��L�i�઺���~�B�z **********************************/
				} catch (Exception e) {
					errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/sub_message/listAllSubMessage.jsp");
					failureView.forward(req, res);
				}
			}	
			
// �Ӧ�update_pageSubMessage_input.jsp���ШD (update)			
			if ("update".equals(action)) { // �Ӧ�update_pageSubMessage_input.jsp���ШD

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
						String submsg_id = req.getParameter("submsg_id");
						String mainmsg_id = req.getParameter("mainmsg_id");
						String member_id = req.getParameter("member_id");
						String submsg_text = req.getParameter("submsg_text");
						if (submsg_text == null || submsg_text.trim().length() == 0) {
							errorMsgs.add("�d���ФŪť�");
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
						req.setAttribute("sub_messageVO", sub_messageVO); // �t����J�榡���~��sub_messageVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/sub_message/update_pageSubMessage_input.jsp");
						failureView.forward(req, res);
						return; // �{�����_
					}
					/*************************** 2.�}�l�ק��� *****************************************/
					Sub_messageService sub_messageSvc = new Sub_messageService();
					long data = new Date().getTime();
					java.sql.Timestamp submsg_time = new Timestamp(data);
					sub_messageSvc.updateSub_message(mainmsg_id, member_id, submsg_time, submsg_text, Integer.parseInt(submsg_status), submsg_id);
					/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
					req.setAttribute("sub_messageVO", sub_messageVO); // ��Ʈwupdate���\��,���T����esub_messageVO����,�s�Jreq
					String url = "/back-end/sub_message/listAllSubMessage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneSubMessage.jsp
					successView.forward(req, res);

					/*************************** ��L�i�઺���~�B�z *************************************/
				} catch (Exception e) {
					errorMsgs.add("�ק��ƥ���:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sub_message/update_pageSubMessage_input.jsp");
					failureView.forward(req, res);
				}
			}		
			
// �Ӧ�addSubMessage.jsp���ШD (insert)		
			if ("insert".equals(action)) { // �Ӧ�addSubMessage.jsp���ШD

				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
							
					String mainmsg_id = req.getParameter("mainmsg_id");
					String mainmsg_idReg = "^[M][M][(0-9)]{0,8}$";
					if (mainmsg_id == null || mainmsg_id.trim().length() == 0) {
						errorMsgs.add("�j�d���s��: �ФŪť�");
					} else if (!mainmsg_id.trim().matches(mainmsg_idReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
						errorMsgs.add("�j�d���s��: �u��O�^��r���B�Ʀr  ");
					}

					String member_id = req.getParameter("member_id");
					String member_idReg = "^[M][E][M][(0-9)]{0,8}$";
					if (member_id == null || member_id.trim().length() == 0) {
						errorMsgs.add("�|���s��: �ФŪť�");
					} else if (!member_id.trim().matches(member_idReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
						errorMsgs.add("�|���s��: �u��O�^��r���B�Ʀr  ");
					}
					
					String submsg_text = req.getParameter("submsg_text").trim();
					if (submsg_text == null || submsg_text.trim().length() == 0) {
						errorMsgs.add("�d���ФŪť�");
					}

					Integer submsg_status = null;
					try {
						submsg_status = new Integer(req.getParameter("submsg_status"));
					} catch (NumberFormatException e) {
						submsg_status = 1; // �w�]�^�ǭ�
						errorMsgs.add("���A�ФŪť�");
					}

					Sub_messageVO sub_messageVO = new Sub_messageVO();
					sub_messageVO.setMainmsg_id(mainmsg_id);
					sub_messageVO.setMember_id(member_id);
					sub_messageVO.setSubmsg_text(submsg_text);
					sub_messageVO.setSubmsg_status(submsg_status);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("sub_messageVO", sub_messageVO); // �t����J�榡���~��sub_messageVO����,�]�s�Jreq
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/sub_message/addSubMessage.jsp");
						failureView.forward(req, res);
						return;
					}

					/*************************** 2.�}�l�s�W��� ***************************************/
					Sub_messageService sub_messageSvc = new Sub_messageService();
					long data = new Date().getTime();
					java.sql.Timestamp submsg_time = new Timestamp(data);
					sub_messageVO = sub_messageSvc.addSub_message(mainmsg_id, member_id, submsg_time,submsg_text,submsg_status);
					/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/

					String url = "/back-end/sub_message/listAllSubMessage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllSubMessage.jsp
					successView.forward(req, res);

					/*************************** ��L�i�઺���~�B�z **********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/sub_message/addSubMessage.jsp");
					failureView.forward(req, res);
				}
			}
			
// listAllSubMessage.jsp (delete)			
			if ("delete".equals(action)) { // �Ӧ�listAllSubMessage.jsp

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					/*************************** 1.�����ШD�Ѽ� ***************************************/
					String submsg_id = new String(req.getParameter("submsg_id"));

					/*************************** 2.�}�l�R����� ***************************************/
					Sub_messageService sub_messageSvc = new Sub_messageService();
					sub_messageSvc.deleteSub_message(submsg_id);

					/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
					String url = "/back-end/sub_message/listAllSubMessage.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
					successView.forward(req, res);

					/*************************** ��L�i�઺���~�B�z **********************************/
				} catch (Exception e) {
					errorMsgs.add("�R����ƥ���:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/Sub_message/listAllSubMessage.jsp");
					failureView.forward(req, res);
				}
			}

		}
}
