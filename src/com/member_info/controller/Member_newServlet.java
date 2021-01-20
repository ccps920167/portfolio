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
		if ("checkEmail".equals(action)) { // 來自update_emp_input.jsp的請求

			String member_email = req.getParameter("member_email");
			
			PrintWriter out = res.getWriter();
			
			Member_infoService Srv = new Member_infoService();
			Map<String, String> map = new HashMap<String, String>();
			
			String member_emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
			try {
				if (member_email.matches(member_emailReg)) {
					for (Member_infoVO item : Srv.getAll()) {
						if (item.getMember_email().equals(member_email)) {
							out.write("Email重複，請更換Email");
							break;
						}
					}
				}else {
					out.write("請確認Email格式");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				out.close();
			}

		}
		
		if ("checkPWD".equals(action)) { // 來自update_emp_input.jsp的請求

			String member_password = req.getParameter("member_password");
			PrintWriter out = res.getWriter();
			Member_infoService Srv = new Member_infoService();
			Map<String, String> map = new HashMap<String, String>();
			JSONObject AllJsonObject = new JSONObject();
			String member_passwordReg = "^[(a-zA-Z0-9)]{6,12}$";
			try {
				if (!member_password.matches(member_passwordReg)) {
					out.write("請確認密碼格式，介於6~12碼英文或數字");	
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				out.close();
			}

		}

		if ("insert_user".equals(action)) { // 來自update_emp_input.jsp的請求
			System.out.println("進入insert_user");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 驗證碼訊息
			String insrand = req.getParameter("insrand");
			HttpSession session = req.getSession();
			String rand = (String) session.getAttribute("rand");

			System.out.println("LoginHandler" + rand);
			if (rand == null) {
				errorMsgs.add("驗證逾時,請重新登入");
				
			}
			if (!(insrand.trim().equals(rand))) {
				errorMsgs.add("驗證碼錯誤,請重新輸入");
				
			}
			
			String member_email = req.getParameter("member_email");
			String member_emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
			if (member_email == null || member_email.trim().length() == 0) {
				errorMsgs.add("會員信箱: 請勿空白");
			} else if (!member_email.trim().matches(member_emailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員信箱格式錯誤");
			}

			String member_password = req.getParameter("member_password");
			String member_passwordReg = "^[(a-zA-Z0-9)]{6,12}$";
			if (member_password == null || member_password.trim().length() == 0) {
				errorMsgs.add("會員密碼: 請勿空白");
			} else if (!member_password.trim().matches(member_passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員密碼: 只能是英文字母和數字, 且長度必需在6到12之間");
			}
			
			String  AgainMember_password = req.getParameter("AgainMember_password");
			
			if(!member_password.equals(AgainMember_password)) {
				errorMsgs.add("密碼不相符");
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
			member_infoVO.setMember_occupation("無資料");
			member_infoVO.setMember_address("無資料");
			member_infoVO.setMember_invoice("無資料");
			member_infoVO.setMember_pic(null);
			member_infoVO.setTeachclass_on(1);
			member_infoVO.setLearnclass_on(1);
			member_infoVO.setMember_homework(1);
			member_infoVO.setMember_about("無自我介紹");
			member_infoVO.setMember_good_for("無自我介紹");
			member_infoVO.setRegister_time(register_time);
			member_infoVO.setMember_update(member_update);
			member_infoVO.setTraccount("無資料");
			member_infoVO.setBank_code("無資料");
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("member_infoVO", member_infoVO); // 含有輸入格式錯誤的empVO物件,也存入req
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sign_in/NewMember.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			
			try {
			/*************************** 2.開始修改資料 *****************************************/
			Member_infoAddService member_infoSvc = new Member_infoAddService();
			member_infoVO = member_infoSvc.add(member_infoVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			String url = "/member_info/LoginHandler?action=login";
			req.setAttribute("account", member_infoVO.getMember_email());
			System.out.println(member_infoVO.getMember_email());
			req.setAttribute("password", member_infoVO.getMember_password());
			
			req.setAttribute("login_arrange", req.getParameter("login_arrange"));
			req.setAttribute("login_ip", req.getParameter("login_ip"));
			
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("帳號重複，請更換Email");
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("member_infoVO", member_infoVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/sign_in/NewMember.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			
			}

	}

}
