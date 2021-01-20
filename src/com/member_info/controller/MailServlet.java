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

	private boolean sendMail() // email 為收件者, password 為密碼
	{
		java.util.Properties property = new java.util.Properties();
		property.put("mail.host", "smtp.gmail.com"); // 設定郵件伺服器
		property.put("mail.transport.protocol", "smtp"); // 設定通訊協定
		property.setProperty("mail.smtp.ssl.enable", "true");// SSL
		property.setProperty("mail.smtp.auth", "true");//

		Authenticator authenticator = new Authenticator() {
			@Override
			public PasswordAuthentication getPasswordAuthentication() {
				// 設定使用信箱帳號密碼
				return new PasswordAuthentication("ccps9201672", "cma890116");
			}
		};
		Session sess = Session.getInstance(property, authenticator); // 取得 Session
		MimeMessage msg = new MimeMessage(sess); // 以Session 為參數 建立一封電子郵件

		String loginpage = "http://localhost:8081/TEA102G5/front-end/sign_in/sign_in.jsp";
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("<tbody>");
		sb.append("<tr><td style='border:0'>"+"<span style='font-size:1.2em;color:#00c9e7'>"+checkusername+"</span>先生/小姐 您好，"+"</td></tr>");
		sb.append("<tr><td style='border:0;font-size:0.75em'>您的密碼為:"+checkpassword+"</td></tr>");
		sb.append("<tr>請<a href=\""+loginpage+"\">重新登入</a></tr>");
		sb.append("Tomato學習平台感謝您的支持");
		sb.append("</tbody>");
		
		String msgs = sb.toString();		
		
		try {
			msg.setFrom(new InternetAddress("ccps9201672@gmail.com")); // 寄件者
			// 將收件者的 InetAddress 物件新增給使用者
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(checkmail));
			msg.setSubject("查詢密碼結果"); // 郵件的主旨
			msg.setContent(msgs,"text/html;charset=UTF-8"); // 郵件的內容...使用者的密碼
			msg.setSentDate(new java.util.Date()); // 設定寄送日期 為現在
			Transport.send(msg); // 寄送郵件
			return true;
		} catch (AddressException ae) {
			System.out.println(ae);
			return false; // 記得要 return false
		} catch (MessagingException me) {
			System.out.println(me);
			return false; // 記得要 return false
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
				/*************************** 1.接收請求參數 ****************************************/
				String findaccount = req.getParameter("findaccount");
				if (findaccount == null || (findaccount.trim()).length() == 0) {
					errorMsgs.add("請輸入查詢帳號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
					failureView.forward(req, res);
					return;
				} // 程式中斷
				/*************************** 2.開始查詢資料 ****************************************/
				Member_infoService member_infoSvc = new Member_infoService();
				Member_infoVO member_infoVO = member_infoSvc.getAccountPassword(findaccount);
				checkmail = member_infoVO.getMember_email();
				checkpassword = member_infoVO.getMember_password();
				checkusername = member_infoVO.getMember_name();
				Boolean is_ok = sendMail();
				if (!is_ok) {
					errorMsgs.add("信箱帳號錯誤");
				} else {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
					failureView.forward(req, res);
				}
			} catch (Exception e) {
				errorMsgs.add("無此帳號，請先註冊" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
