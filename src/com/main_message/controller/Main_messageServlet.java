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
		// ���o�ӷ��Ѽ�
		String action = req.getParameter("action");

// �Ӧ�select_pageMainMessage.jsp���ШD (getOne_For_Display)
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_pageMainMessage.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String mainmsg_id = req.getParameter("mainmsg_id");
						String mainmsg_idReg = "^[M][M][(0-9)]{5}$";
				if (mainmsg_id == null || mainmsg_id.trim().length() == 0) {
					errorMsgs.add("�п�J�d���s��");
				}
						else if(!mainmsg_id.matches(mainmsg_idReg)) {
									errorMsgs.add("�榡���~");
								}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/main_message/select_pageMainMessage.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				/*************************** 2.�}�l�d�߸�� *****************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				Main_messageVO main_messageVO = main_messageSvc.getOnemain_message(mainmsg_id);
				if (main_messageVO == null) {
					errorMsgs.add("�d�L���");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/main_message/select_pageMainMessage.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("main_messageVO", main_messageVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/main_message/listOneMainMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/main_message/select_pageMainMessage.jsp");
				failureView.forward(req, res);
			}
		}

// �Ӧ�listAllMainMessage.jsp���ШD (getOne_For_Update)
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllMainMessage.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String mainmsg_id = new String(req.getParameter("mainmsg_id"));
				System.out.println(mainmsg_id);
				/*************************** 2.�}�l�d�߸�� ****************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				Main_messageVO main_messageVO = main_messageSvc.getOnemain_message(mainmsg_id);
				System.out.println(main_messageVO);
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("main_messageVO", main_messageVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/main_message/update_mainMessage_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/main_message/listAllMainMessage.jsp");
				failureView.forward(req, res);
			}
		}

// �Ӧ�update_emp_input.jsp���ШD (update)
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
		  String mainmsg_id = req.getParameter("mainmsg_id");
		  String class_id = req.getParameter("class_id").trim();
		  String member_id = req.getParameter("member_id");
		  String mainmsg_text = req.getParameter("mainmsg_text").trim();
				if (mainmsg_text == null || mainmsg_text.trim().length() == 0) {
					errorMsgs.add("�d���ФŪť�");
				}

		  String msg_source = req.getParameter("msg_source").trim();
				if (msg_source == null || msg_source.trim().length() == 0) {
					errorMsgs.add("�ӷ��ФŪť�");
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
					req.setAttribute("main_messageVO", main_messagevo); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/main_message/update_mainMessage_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				long data = new Date().getTime();
				java.sql.Timestamp mainmsg_time = new Timestamp(data);
				main_messageSvc.updateMain_message(class_id, member_id, mainmsg_time, mainmsg_text,msg_source,Integer.parseInt(msg_status), mainmsg_id);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("main_messageVO", main_messagevo); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/main_message/listAllMainMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/main_message/update_mainMessage_input.jsp");
				failureView.forward(req, res);
			}
		}

// �Ӧ�addMainMessage.jsp���ШD (insert)
		if ("insert".equals(action)) { // �Ӧ�addMainMessage.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String class_id = req.getParameter("class_id");
				String class_idReg = "^[C][L][A][(0-9)]{0,8}$";
				if (class_id == null || class_id.trim().length() == 0) {
					errorMsgs.add("�ҵ{�s��: �ФŪť�");
				} else if (!class_id.trim().matches(class_idReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�ҵ{�s��: �u��O�^��r���B�Ʀr  �B���ץ��ݦb4��8����");
				}

				String member_id = req.getParameter("member_id");
				String member_idReg = "^[M][E][M][(0-9)]{0,8}$";
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("�|���s��: �ФŪť�");
				} else if (!member_id.trim().matches(member_idReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���s��: �u��O�^��r���B�Ʀr  �B���ץ��ݦb4��8����");
				}

				String mainmsg_text = req.getParameter("mainmsg_text").trim();
				if (mainmsg_text == null || mainmsg_text.trim().length() == 0) {
					errorMsgs.add("�d���ФŪť�");
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
					msg_status = 1; // �w�]�^�ǭ�
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
					req.setAttribute("main_messageVO", main_messageVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/main_message/addMainMessage.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				long data = new Date().getTime();
				java.sql.Timestamp mainmsg_time = new Timestamp(data);
				main_messageVO = main_messageSvc.addMain_message(class_id, member_id,mainmsg_time, mainmsg_text, msg_source,msg_status);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/

				String url = "/back-end/main_message/listAllMainMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllMainMessage.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/main_message/addMainMessage.jsp");
				failureView.forward(req, res);
			}
		}

// listAllMainMessage.jsp (delete)
		if ("delete".equals(action)) { // �Ӧ�listAllMainMessage.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String mainmsg_id = new String(req.getParameter("mainmsg_id"));

				/*************************** 2.�}�l�R����� ***************************************/
				Main_messageService main_messageSvc = new Main_messageService();
				main_messageSvc.deletemain_message(mainmsg_id);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/main_message/listAllMainMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/main_message/listAllMainMessage.jsp");
				failureView.forward(req, res);
			}
		}

	}

}