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

	// �i�ˬd�ϥΪ̿�J���b��(account) �K�X(password)�O�_���ġj
	// �i��ڤW���ܸ�Ʈw�j�M���j
	protected boolean allowUser(String account, String password) {
//		System.out.println("�����ϥΪ̱b�K");
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
		// action=login�n�J
		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// ���ҽX�T��
			String insrand = req.getParameter("insrand");
			HttpSession session = req.getSession();
			String rand = (String) session.getAttribute("rand");

			System.out.println("LoginHandler" + rand);
			if (rand == null) {
				errorMsgs.add("���ҹO��,�Э��s�n�J");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
				failureView.forward(req, res);
				return;
			}
			if (!(insrand.trim().equals(rand))) {
				errorMsgs.add("���ҽX���~,�Э��s��J");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
				failureView.forward(req, res);
				return;
			}

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String account = req.getParameter("account");
				String password = req.getParameter("password");

				System.out.println(account + password);
				if (req.getParameter("account") == null || req.getParameter("password") == null) {
					account = (String) req.getAttribute("account");
					password = (String) req.getAttribute("password");
				}

				if (account == null || (account.trim()).length() == 0) {
					errorMsgs.add("�п�J�b��");
				}

				if (password == null || (password.trim()).length() == 0) {
					errorMsgs.add("�п�J�K�X");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
					failureView.forward(req, res);
					return;
				} // �{�����_

				/*************************** 2.�}�l�d�߸�� ****************************************/
				Member_infoService member_infoSvc = new Member_infoService();
				Member_infoVO member_infoVO = member_infoSvc.getAccountPassword(account);
//				System.out.println("LoginHandler" + member_infoVO.getMember_id());
				checkmail = member_infoVO.getMember_email();
				checkpassword = member_infoVO.getMember_password();

				// �i�ˬd�ӱb�� , �K�X�O�_���ġj

				if (!allowUser(account, password)) { // �i�b�� , �K�X�L�Įɡj
					errorMsgs.add("�b���K�X�L�ġA�Э��s�n�J");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
					failureView.forward(req, res);
					return;
				} else { // �i�b�� , �K�X���Į�, �~���H�U�u�@�j
					session.setAttribute("account", account); // *�u�@1: �~�bsession�����w�g�n�J�L������

					//***********************************�q�A�Q�ǧ��Ƭy�{*********************************
					// ============================�q�n�J��s��session���o�|��ID=============================

					String memberid = member_infoVO.getMember_id();
//							System.out.println("���i�Ӯ�memberid");
//							System.out.println(memberid);
					// ============================�q�n�J��session�|���h��ӷ|������==========================

					Member_interestService member_interestSvc = new Member_interestService();
					List<Member_interestVO> memberinterestlist = (List<Member_interestVO>) member_interestSvc
							.findBymember_id(memberid);

//							System.out.println("���i�Ӯ�subclass_id");
//							System.out.println(list.get(0).getSubclass_id());
					Set<String> slist = new TreeSet<String>();
					Set<String> slistname = new TreeSet<String>();

					Sub_classService subsrv = new Sub_classService();
					System.out.println("�ϥΪ̪����쬰:");
					for (Member_interestVO s : memberinterestlist) {
						slist.add(s.getSubclass_id());
						Sub_classVO sub_classVO = subsrv.getOneMain_class(s.getSubclass_id());
						slistname.add(sub_classVO.getSubClass_name());
						System.out.print(s.getSubclass_id() + " ");
					}
					System.out.println("");
					// ========================�q�n�J��session�|������h�d�俳��������ƽҵ{���O=====================
					// ����X�Ҧ��ҵ{���
					Class_infoService class_infoSvc = new Class_infoService();
					List<Class_infoVO> allclasslist = (List<Class_infoVO>) class_infoSvc.getAll();

					System.out.println("�ƽҵ{�C���������:");
					Set<String> slist2 = new TreeSet<String>();
					for (Class_infoVO ci : allclasslist) {
						slist2.add(ci.getSubclass_id());
					}
					for (String ci : slist2) {
						System.out.print(ci + " ");
					}
					System.out.println("");
					// ����̥涰��subclass_id
					slist2.retainAll(slist);
					System.out.println("���ϥΪ̿��쪺�ҵ{���O:" + slist2);

					// ========================�q�z��X�Ӫ��ҵ{���O�h�d�������ҵ{=============================
//							List<Class_infoVO> list = new ArrayList<Class_infoVO>();
					for (String Sci : slist2) {
						List<Class_infoVO> class_row_8 = (List<Class_infoVO>) class_infoSvc
								.get_ROWNUM_8_Bysub_class(Sci);

						// ============================��X����ι������ҵ{�s��session=============================

						session.setAttribute("ROWNUM_8_Bysub_class", class_row_8);// ��X���ҵ{���e����
						session.setAttribute("slistname", slistname);// ��X���|������W��
//								System.out.println(class_row_8);

					}
					// ================================�q�A�Q�ǧ��Ƭy�{����==================================

					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							System.out.println(location);
							// ����judgement MAP����
							Map<String, String> judgement = new HashMap<String, String>();
							// ���X�|�����(account�ǤJ�ѼơA�W���O�γo�Ӭd)
							String member_role = member_infoVO.getMember_role() + "";
							judgement.put("role", member_role);

							/** �v���[�ݬ���(�ն�) *************************************************/
							Map<String, List<Video_recordVO>> member_Video_record = new HashMap<String, List<Video_recordVO>>();
							List<Video_recordVO> member_Video_record_list = new Video_recordService()
									.getMemberAll(member_infoVO.getMember_id());
							member_Video_record = new Video_recordService().getMemberAll(member_infoVO.getMember_id())
									.stream().collect(Collectors.groupingBy(Video_recordVO::getMember_id));
							session.setAttribute("member_Video_record", member_Video_record);

							/** �ǲ߽ҵ{(�ն�) *************************************************/
							List<Order_listVO> member_class = new Order_listService()
									.getMemberClass(member_infoVO.getMember_id());
							session.setAttribute("member_class", member_class);

							/** �б½ҵ{(�ն�) *************************************************/
							List<Class_infoVO> member_teach = new Class_infoService()
									.getTeachAll(member_infoVO.getMember_id());
							session.setAttribute("member_teach", member_teach);
							session.setAttribute("judgement", judgement);
							session.setAttribute("member_infoVO", member_infoVO);
							session.removeAttribute("location"); // *�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)

							/** �n�J���v����(�o��) *************************************************/

							java.sql.Timestamp login_time = null;
							try {
								login_time = java.sql.Timestamp.valueOf(req.getParameter("login_time").trim());
							} catch (IllegalArgumentException e) {
								login_time = new java.sql.Timestamp(System.currentTimeMillis());
								errorMsgs.add("�п�J���!");
							}

							String login_arrange = req.getParameter("login_arrange");
							String login_arrangeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
							if (login_arrange == null || login_arrange.trim().length() == 0) {
								errorMsgs.add("�n�J���v�����˸m: �ФŪť�");
							} else if (!login_arrange.trim().matches(login_arrangeReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
								errorMsgs.add("�n�J���v�����˸m: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
							}

							String login_ip = req.getParameter("login_ip");
							String login_ipReg = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
							if (login_ip == null || login_ip.trim().length() == 0) {
								errorMsgs.add("�n�JIP: �ФŪť�");
							} else if (!login_ip.trim().matches(login_ipReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
								errorMsgs.add("�n�JIP: �榡��XXX.XXX.XXX.XXX�B�u���Ʀr");
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

							// ����judgement MAP����
							Map<String, String> judgement = new HashMap<String, String>();
							// ���X�|�����(account�ǤJ�ѼơA�W���O�γo�Ӭd)
							String member_role = member_infoVO.getMember_role() + "";
							judgement.put("role", member_role);
							System.out.println(member_role);
							/** �v���[�ݬ���(�ն�) *************************************************/
							Map<String, List<Video_recordVO>> member_Video_record = new HashMap<String, List<Video_recordVO>>();
							List<Video_recordVO> member_Video_record_list = new Video_recordService()
									.getMemberAll(member_infoVO.getMember_id());
							member_Video_record = new Video_recordService().getMemberAll(member_infoVO.getMember_id())
									.stream().collect(Collectors.groupingBy(Video_recordVO::getMember_id));
							session.setAttribute("member_Video_record", member_Video_record);

							/** �ǲ߽ҵ{(�ն�) *************************************************/
							List<Order_listVO> member_class = new Order_listService()
									.getMemberClass(member_infoVO.getMember_id());
							session.setAttribute("member_class", member_class);
							/** �б½ҵ{(�ն�) *************************************************/
							List<Class_infoVO> member_teach = new Class_infoService()
									.getTeachAll(member_infoVO.getMember_id());
							session.setAttribute("member_teach", member_teach);
							session.setAttribute("judgement", judgement);
							session.setAttribute("member_infoVO", member_infoVO);

							/** �n�J���v����(�o��) *************************************************/

							java.sql.Timestamp login_time = new Timestamp(new Date().getTime());

							String login_arrange = req.getParameter("login_arrange");
							String login_arrangeReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
							if (login_arrange == null || login_arrange.trim().length() == 0) {
								errorMsgs.add("�n�J���v�����˸m: �ФŪť�");
							} else if (!login_arrange.trim().matches(login_arrangeReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
								errorMsgs.add("�n�J���v�����˸m: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
							}

							String login_ip = req.getParameter("login_ip");
							String login_ipReg = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
							if (login_ip == null || login_ip.trim().length() == 0) {
								errorMsgs.add("�n�JIP: �ФŪť�");
							} else if (!login_ip.trim().matches(login_ipReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
								errorMsgs.add("�n�JIP: �榡��XXX.XXX.XXX.XXX�B�u���Ʀr");
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
																					// (-->�p�L�ӷ�����:�h���ɦ�login_success.jsp)
						}
					} catch (Exception ignored) {
						System.out.println(ignored.getMessage());
					}
				}
			} catch (Exception e) {
				errorMsgs.add("�L���b���A�Х����U" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/sign_in/sign_in.jsp");
				failureView.forward(req, res);
			}
		}
		// action=logout�n�X
		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.invalidate();
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
	}

}