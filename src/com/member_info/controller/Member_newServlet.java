package com.member_info.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

import com.member_info.model.Member_infoAddService;
import com.member_info.model.Member_infoService;
import com.member_info.model.Member_infoVO;

@WebServlet("/member_info/Member_newServlet")
@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024, maxFileSize = 30 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024
		* 1024)
public class Member_newServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setCharacterEncoding("UTF-8");
		if ("checkEmail".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			String member_email = req.getParameter("member_email");
			
			PrintWriter out = res.getWriter();
			
			Member_infoService Srv = new Member_infoService();
			Map<String, String> map = new HashMap<String, String>();
			
			String member_emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
			try {
				if (member_email.matches(member_emailReg)) {
					for (Member_infoVO item : Srv.getAll()) {
						if (item.getMember_email().equals(member_email)) {
							out.write("Email���ơA�Ч�Email");
							break;
						}
					}
				}else {
					out.write("�нT�{Email�榡");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				out.close();
			}

		}
		
		if ("checkPWD".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			String member_password = req.getParameter("member_password");
			PrintWriter out = res.getWriter();
			Member_infoService Srv = new Member_infoService();
			Map<String, String> map = new HashMap<String, String>();
			JSONObject AllJsonObject = new JSONObject();
			String member_passwordReg = "^[(a-zA-Z0-9)]{6,12}$";
			try {
				if (!member_password.matches(member_passwordReg)) {
					out.write("�нT�{�K�X�榡�A����6~12�X�^��μƦr");	
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				out.close();
			}

		}

		if ("insert_user".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			System.out.println("�i�Jinsert_user");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
			// ���ҽX�T��
			String insrand = req.getParameter("insrand");
			HttpSession session = req.getSession();
			String rand = (String) session.getAttribute("rand");

			System.out.println("LoginHandler" + rand);
			if (rand == null) {
				errorMsgs.add("���ҹO��,�Э��s�n�J");
				
			}
			if (!(insrand.trim().equals(rand))) {
				errorMsgs.add("���ҽX���~,�Э��s��J");
				
			}
			
			String member_email = req.getParameter("member_email");
			String member_emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
			if (member_email == null || member_email.trim().length() == 0) {
				errorMsgs.add("�|���H�c: �ФŪť�");
			} else if (!member_email.trim().matches(member_emailReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("�|���H�c�榡���~");
			}

			String member_password = req.getParameter("member_password");
			String member_passwordReg = "^[(a-zA-Z0-9)]{6,12}$";
			if (member_password == null || member_password.trim().length() == 0) {
				errorMsgs.add("�|���K�X: �ФŪť�");
			} else if (!member_password.trim().matches(member_passwordReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
				errorMsgs.add("�|���K�X: �u��O�^��r���M�Ʀr, �B���ץ��ݦb6��12����");
			}
			
			String  AgainMember_password = req.getParameter("AgainMember_password");
			
			if(!member_password.equals(AgainMember_password)) {
				errorMsgs.add("�K�X���۲�");
			}
			String member_name = member_email.split("@")[0];
			java.sql.Timestamp register_time = new Timestamp(System.currentTimeMillis());
			java.sql.Timestamp member_update = new Timestamp(System.currentTimeMillis());
			
			Member_infoVO member_infoVO = new Member_infoVO();
			member_infoVO.setMember_name(member_name);
			member_infoVO.setMember_email(member_email);
			member_infoVO.setMember_password(member_password);
			member_infoVO.setMember_role(0);
			member_infoVO.setMember_gender(0);
			member_infoVO.setMember_birthday(new Date((long)0));
			member_infoVO.setMember_occupation("�L���");
			member_infoVO.setMember_address("�L���");
			member_infoVO.setMember_invoice("�L���");
			member_infoVO.setMember_pic(null);
			member_infoVO.setTeachclass_on(1);
			member_infoVO.setLearnclass_on(1);
			member_infoVO.setMember_homework(1);
			member_infoVO.setMember_about("�L�ۧڤ���");
			member_infoVO.setMember_good_for("�L�ۧڤ���");
			member_infoVO.setRegister_time(register_time);
			member_infoVO.setMember_update(member_update);
			member_infoVO.setTraccount("�L���");
			member_infoVO.setBank_code("�L���");
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("member_infoVO", member_infoVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sign_in/NewMember.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}
			
			try {
			/*************************** 2.�}�l�ק��� *****************************************/
			Member_infoAddService member_infoSvc = new Member_infoAddService();
			member_infoVO = member_infoSvc.add(member_infoVO);

			/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
			String url = "/member_info/LoginHandler?action=login";
			req.setAttribute("account", member_infoVO.getMember_email());
			System.out.println(member_infoVO.getMember_email());
			req.setAttribute("password", member_infoVO.getMember_password());
			
			req.setAttribute("login_arrange", req.getParameter("login_arrange"));
			req.setAttribute("login_ip", req.getParameter("login_ip"));
			
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�b�����ơA�Ч�Email");
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("member_infoVO", member_infoVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sign_in/NewMember.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}
			
			}

	}

}
