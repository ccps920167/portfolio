package com.member_info.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.login_history.model.Login_historyService;
import com.login_history.model.Login_historyVO;
import com.member_info.model.Member_infoService;
import com.member_info.model.Member_infoVO;
import com.member_interest.model.Member_interestService;
import com.member_interest.model.Member_interestVO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;
import com.sub_class.model.Sub_classService;
import com.sub_class.model.Sub_classVO;
import com.video_record.model.Video_recordService;
import com.video_record.model.Video_recordVO;

@WebServlet("/member_info/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String checkmail;
	String checkpassword;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	protected boolean allowUser(String account, String password) {
//		System.out.println("捕捉使用者帳密");
//		System.out.println(account);
//		System.out.println(password);

		if (account.equals(checkmail) && password.equals(checkpassword))
			return true;
		else
			return false;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("Big5");
		res.setContentType("text/html; charset=Big5");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		// action=login登入
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 驗證碼訊息
			String insrand = req.getParameter("insrand");
			HttpSession session = req.getSession();
			String rand = (String) session.getAttribute("rand");

			System.out.println("LoginHandler" + rand);
			if (rand == null) {
				errorMsgs.add("驗證逾時,請重新登入");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
				failureView.forward(req, res);
				return;
			}
			if (!(insrand.trim().equals(rand))) {
				errorMsgs.add("驗證碼錯誤,請重新輸入");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
				failureView.forward(req, res);
				return;
			}

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String account = req.getParameter("account");
				String password = req.getParameter("password");

				System.out.println(account + password);
				if (req.getParameter("account") == null || req.getParameter("password") == null) {
					account = (String) req.getAttribute("account");
					password = (String) req.getAttribute("password");
				}

				if (account == null || (account.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}

				if (password == null || (password.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
					failureView.forward(req, res);
					return;
				} // 程式中斷

				/*************************** 2.開始查詢資料 ****************************************/
				Member_infoService member_infoSvc = new Member_infoService();
				Member_infoVO member_infoVO = member_infoSvc.getAccountPassword(account);
//				System.out.println("LoginHandler" + member_infoVO.getMember_id());
				checkmail = member_infoVO.getMember_email();
				checkpassword = member_infoVO.getMember_password();

				// 【檢查該帳號 , 密碼是否有效】

				if (!allowUser(account, password)) { // 【帳號 , 密碼無效時】
					errorMsgs.add("帳號密碼無效，請重新登入");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
					failureView.forward(req, res);
					return;
				} else { // 【帳號 , 密碼有效時, 才做以下工作】
					session.setAttribute("account", account); // *工作1: 才在session內做已經登入過的標識

					//***********************************猜你想學抓資料流程*********************************
					// ============================從登入後存的session取得會員ID=============================

					String memberid = member_infoVO.getMember_id();
//							System.out.println("有進來拿memberid");
//							System.out.println(memberid);
					// ============================從登入的session會員去找該會員興趣==========================

					Member_interestService member_interestSvc = new Member_interestService();
					List<Member_interestVO> memberinterestlist = (List<Member_interestVO>) member_interestSvc
							.findBymember_id(memberid);

//							System.out.println("有進來拿subclass_id");
//							System.out.println(list.get(0).getSubclass_id());
					Set<String> slist = new TreeSet<String>();
					Set<String> slistname = new TreeSet<String>();

					Sub_classService subsrv = new Sub_classService();
					System.out.println("使用者的興趣為:");
					for (Member_interestVO s : memberinterestlist) {
						slist.add(s.getSubclass_id());
						Sub_classVO sub_classVO = subsrv.getOneMain_class(s.getSubclass_id());
						slistname.add(sub_classVO.getSubClass_name());
						System.out.print(s.getSubclass_id() + " ");
					}
					System.out.println("");
					// ========================從登入的session會員興趣去查找興趣對應的副課程類別=====================
					// 先找出所有課程資料
					Class_infoService class_infoSvc = new Class_infoService();
					List<Class_infoVO> allclasslist = (List<Class_infoVO>) class_infoSvc.getAll();

					System.out.println("副課程列表對應興趣:");
					Set<String> slist2 = new TreeSet<String>();
					for (Class_infoVO ci : allclasslist) {
						slist2.add(ci.getSubclass_id());
					}
					for (String ci : slist2) {
						System.out.print(ci + " ");
					}
					System.out.println("");
					// 比對兩者交集的subclass_id
					slist2.retainAll(slist);
					System.out.println("比對使用者興趣的課程類別:" + slist2);

					// ========================從篩選出來的課程類別去查對應的課程=============================
//							List<Class_infoVO> list = new ArrayList<Class_infoVO>();
					for (String Sci : slist2) {
						List<Class_infoVO> class_row_8 = (List<Class_infoVO>) class_infoSvc
								.get_ROWNUM_8_Bysub_class(Sci);

						// ============================找出興趣及對應的課程存到session=============================

						session.setAttribute("ROWNUM_8_Bysub_class", class_row_8);// 找出的課程內容物件
						session.setAttribute("slistname", slistname);// 找出的會員興趣名稱
//								System.out.println(class_row_8);

					}
					// ================================猜你想學抓資料流程結束==================================

					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							System.out.println(location);
							// 產生judgement MAP物件
							Map<String, String> judgement = new HashMap<String, String>();
							// 取出會員資料(account傳入參數，上面是用這個查)
							String member_role = member_infoVO.getMember_role() + "";
							judgement.put("role", member_role);

							/** 影片觀看紀錄(博雅) *************************************************/
							Map<String, List<Video_recordVO>> member_Video_record = new HashMap<String, List<Video_recordVO>>();
							List<Video_recordVO> member_Video_record_list = new Video_recordService()
									.getMemberAll(member_infoVO.getMember_id());
							member_Video_record = new Video_recordService().getMemberAll(member_infoVO.getMember_id())
									.stream().collect(Collectors.groupingBy(Video_recordVO::getMember_id));
							session.setAttribute("member_Video_record", member_Video_record);

							/** 學習課程(博雅) *************************************************/
							List<Order_listVO> member_class = new Order_listService()
									.getMemberClass(member_infoVO.getMember_id());
							session.setAttribute("member_class", member_class);

							/** 教授課程(博雅) *************************************************/
							List<Class_infoVO> member_teach = new Class_infoService()
									.getTeachAll(member_infoVO.getMember_id());
							session.setAttribute("member_teach", member_teach);
							session.setAttribute("judgement", judgement);
							session.setAttribute("member_infoVO", member_infoVO);
							session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)

							/** 登入歷史紀錄(穎賢) *************************************************/

							java.sql.Timestamp login_time = null;
							try {
								login_time = java.sql.Timestamp.valueOf(req.getParameter("login_time").trim());
							} catch (IllegalArgumentException e) {
								login_time = new java.sql.Timestamp(System.currentTimeMillis());
								errorMsgs.add("請輸入日期!");
							}

							String login_arrange = req.getParameter("login_arrange");
							String login_arrangeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
							if (login_arrange == null || login_arrange.trim().length() == 0) {
								errorMsgs.add("登入歷史紀錄裝置: 請勿空白");
							} else if (!login_arrange.trim().matches(login_arrangeReg)) { // 以下練習正則(規)表示式(regular-expression)
								errorMsgs.add("登入歷史紀錄裝置: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
							}

							String login_ip = req.getParameter("login_ip");
							String login_ipReg = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
							if (login_ip == null || login_ip.trim().length() == 0) {
								errorMsgs.add("登入IP: 請勿空白");
							} else if (!login_ip.trim().matches(login_ipReg)) { // 以下練習正則(規)表示式(regular-expression)
								errorMsgs.add("登入IP: 格式為XXX.XXX.XXX.XXX且只能填數字");
							}

							Login_historyVO login_historyVO = new Login_historyVO();
							String member_id = member_infoVO.getMember_id();

							login_historyVO.setLogin_time(login_time);
							login_historyVO.setLogin_arrange(login_arrange);
							login_historyVO.setLogin_ip(login_ip);
							login_historyVO.setMember_id(member_id);

							Login_historyService login_historySvc = new Login_historyService();
							login_historyVO = login_historySvc.add(login_time, login_arrange, login_ip, member_id);
							req.setAttribute("login_historyVO", login_historyVO);

							res.sendRedirect(location);
							return;
						} else {

							// 產生judgement MAP物件
							Map<String, String> judgement = new HashMap<String, String>();
							// 取出會員資料(account傳入參數，上面是用這個查)
							String member_role = member_infoVO.getMember_role() + "";
							judgement.put("role", member_role);
							System.out.println(member_role);
							/** 影片觀看紀錄(博雅) *************************************************/
							Map<String, List<Video_recordVO>> member_Video_record = new HashMap<String, List<Video_recordVO>>();
							List<Video_recordVO> member_Video_record_list = new Video_recordService()
									.getMemberAll(member_infoVO.getMember_id());
							member_Video_record = new Video_recordService().getMemberAll(member_infoVO.getMember_id())
									.stream().collect(Collectors.groupingBy(Video_recordVO::getMember_id));
							session.setAttribute("member_Video_record", member_Video_record);

							/** 學習課程(博雅) *************************************************/
							List<Order_listVO> member_class = new Order_listService()
									.getMemberClass(member_infoVO.getMember_id());
							session.setAttribute("member_class", member_class);
							/** 教授課程(博雅) *************************************************/
							List<Class_infoVO> member_teach = new Class_infoService()
									.getTeachAll(member_infoVO.getMember_id());
							session.setAttribute("member_teach", member_teach);
							session.setAttribute("judgement", judgement);
							session.setAttribute("member_infoVO", member_infoVO);

							/** 登入歷史紀錄(穎賢) *************************************************/

							java.sql.Timestamp login_time = new Timestamp(new Date().getTime());

							String login_arrange = req.getParameter("login_arrange");
							String login_arrangeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
							if (login_arrange == null || login_arrange.trim().length() == 0) {
								errorMsgs.add("登入歷史紀錄裝置: 請勿空白");
							} else if (!login_arrange.trim().matches(login_arrangeReg)) { // 以下練習正則(規)表示式(regular-expression)
								errorMsgs.add("登入歷史紀錄裝置: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
							}

							String login_ip = req.getParameter("login_ip");
							String login_ipReg = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
							if (login_ip == null || login_ip.trim().length() == 0) {
								errorMsgs.add("登入IP: 請勿空白");
							} else if (!login_ip.trim().matches(login_ipReg)) { // 以下練習正則(規)表示式(regular-expression)
								errorMsgs.add("登入IP: 格式為XXX.XXX.XXX.XXX且只能填數字");
							}

							Login_historyVO login_historyVO = new Login_historyVO();
							String member_id = member_infoVO.getMember_id();

							login_historyVO.setLogin_time(login_time);
							login_historyVO.setLogin_arrange(login_arrange);
							login_historyVO.setLogin_ip(login_ip);
							login_historyVO.setMember_id(member_id);

							Login_historyService login_historySvc = new Login_historyService();
							login_historyVO = login_historySvc.add(login_time, login_arrange, login_ip, member_id);
							req.setAttribute("login_historyVO", login_historyVO);

							req.setAttribute("member_infoVOLogin", member_infoVO);
							RequestDispatcher successView = req.getRequestDispatcher("/index.jsp");
							successView.forward(req, res);
																					// (-->如無來源網頁:則重導至login_success.jsp)
						}
					} catch (Exception ignored) {
						System.out.println(ignored.getMessage());
					}
				}
			} catch (Exception e) {
				errorMsgs.add("無此帳號，請先註冊" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
				failureView.forward(req, res);
			}
		}
		// action=logout登出
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
	}

}