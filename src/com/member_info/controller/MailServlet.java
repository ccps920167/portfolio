package com.member_info.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member_info.model.Member_infoService;
import com.member_info.model.Member_infoVO;

@WebServlet("/member_info/MailServlet")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String checkmail;
	String checkpassword;
	String checkusername;

	private boolean sendMail() // email �������, password ���K�X
	{
		java.util.Properties property = new java.util.Properties();
		property.put("mail.host", "smtp.gmail.com"); // �]�w�l����A��
		property.put("mail.transport.protocol", "smtp"); // �]�w�q�T��w
		property.setProperty("mail.smtp.ssl.enable", "true");// SSL
		property.setProperty("mail.smtp.auth", "true");//

		Authenticator authenticator = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				// �]�w�ϥΫH�c�b���K�X
				return new PasswordAuthentication("ccps9201672", "cma890116");
			}
		};
		Session sess = Session.getInstance(property, authenticator); // ���o Session
		MimeMessage msg = new MimeMessage(sess); // �HSession ���Ѽ� �إߤ@�ʹq�l�l��

		String loginpage = "http://localhost:8081/TEA102G5/front-end/sign_in/sign_in.jsp";
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tbody>");
		sb.append("<tr><td style='border:0'>"+"<span style='font-size:1.2em;color:#00c9e7'>"+checkusername+"</span>����/�p�j �z�n�A"+"</td></tr>");
		sb.append("<tr><td style='border:0;font-size:0.75em'>�z���K�X��:"+checkpassword+"</td></tr>");
		sb.append("<tr>��<a href=\""+loginpage+"\">���s�n�J</a></tr>");
		sb.append("Tomato�ǲߥ��x�P�±z�����");
		sb.append("</tbody>");
		
		String msgs = sb.toString();		
		
		try {
			msg.setFrom(new InternetAddress("ccps9201672@gmail.com")); // �H���
			// �N����̪� InetAddress ����s�W���ϥΪ�
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(checkmail));
			msg.setSubject("�d�߱K�X���G"); // �l�󪺥D��
			msg.setContent(msgs,"text/html;charset=UTF-8"); // �l�󪺤��e...�ϥΪ̪��K�X
			msg.setSentDate(new java.util.Date()); // �]�w�H�e��� ���{�b
			Transport.send(msg); // �H�e�l��
			return true;
		} catch (AddressException ae) {
			System.out.println(ae);
			return false; // �O�o�n return false
		} catch (MessagingException me) {
			System.out.println(me);
			return false; // �O�o�n return false
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("Big5");
		res.setContentType("text/html; charset=Big5");
//		PrintWriter out = res.getWriter();

		String action = req.getParameter("action");

		if ("forgotpassword".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String findaccount = req.getParameter("findaccount");
				if (findaccount == null || (findaccount.trim()).length() == 0) {
					errorMsgs.add("�п�J�d�߱b��");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
					failureView.forward(req, res);
					return;
				} // �{�����_
				/*************************** 2.�}�l�d�߸�� ****************************************/
				Member_infoService member_infoSvc = new Member_infoService();
				Member_infoVO member_infoVO = member_infoSvc.getAccountPassword(findaccount);
				checkmail = member_infoVO.getMember_email();
				checkpassword = member_infoVO.getMember_password();
				checkusername = member_infoVO.getMember_name();
				Boolean is_ok = sendMail();
				if (!is_ok) {
					errorMsgs.add("�H�c�b�����~");
				} else {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
					failureView.forward(req, res);
				}
			} catch (Exception e) {
				errorMsgs.add("�L���b���A�Х����U" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
