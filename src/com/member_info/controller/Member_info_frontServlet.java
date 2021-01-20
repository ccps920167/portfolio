package com.member_info.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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

import com.member_info.model.Member_infoService;
import com.member_info.model.Member_infoVO;
import com.order_info.model.Order_infoVO;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;

@WebServlet("/member_info/Member_info_frontServlet")
@MultipartConfig(fileSizeThreshold = 10 * 1024 * 1024, maxFileSize = 30 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024
		* 1024)
public class Member_info_frontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		// ���Ϥ�
		if ("member_pic".equals(action)) {
			res.setContentType("image/jpeg"); // �]�w�^������
			String member_id = req.getParameter("member_id");
			Member_infoService member_infoSvc = new Member_infoService(); // �I�sService��k
			Member_infoVO member_infoVO = member_infoSvc.getOneMember_info(member_id); // �Q��id�o�쪫��
			OutputStream out = res.getOutputStream(); // �إ߿�X�y
			try {
				if (member_infoVO.getMember_pic() != null || member_infoVO.getMember_pic().length != 0) { // �P�_�ɮ׬O�_�s�b
					res.setContentLength(member_infoVO.getMember_pic().length); // �q���s�����ɮת���
					InputStream in = new ByteArrayInputStream(member_infoVO.getMember_pic()); // �Nbyte[]�ഫ��InputStream
					byte[] buf = new byte[member_infoVO.getMember_pic().length]; // 4K buffer //�]�wbyte[]�j�p
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len); // �N��ƿ�X
					}
				} else {
					// �d�߭Ȥ��s�b
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);    //��X���~�T��
					InputStream in = getServletContext().getResourceAsStream("/img/NoResult/person.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
			} catch (Exception e) {
				// �S����J�d�߭�
				InputStream in = getServletContext().getResourceAsStream("/img/NoResult/person.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			} finally {
				out.close();
			}
		}

		// ==========================================================================================

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("member_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���s��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_info/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				String member_id = null;
				try {
					member_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("�|���s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_info/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Member_infoService member_infoSvc = new Member_infoService();
				Member_infoVO member_infoVO = member_infoSvc.getOneMember_info(member_id);
				if (member_infoVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_info/select_page.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("member_infoVO", member_infoVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/member_info/listOneMember_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_info/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			System.out.println("getOne_For_Update");
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String member_id = new String(req.getParameter("member_id"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				Member_infoService member_infoSvc = new Member_infoService();
				Member_infoVO member_infoVO = member_infoSvc.getOneMember_info(member_id);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("member_infoVO", member_infoVO);
				String url = "/front-end/member_info/update_member_info_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member_info/listAllMember_info.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String member_id = req.getParameter("member_id");
				String member_name = req.getParameter("member_name");
				String member_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (member_name == null || member_name.trim().length() == 0) {
					errorMsgs.add("�|���m�W: �ФŪť�");
				} else if (!member_name.trim().matches(member_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���m�W: �u��O���B�^��r�� , �B���ץ��ݦb2��10����");
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

				String member_role = req.getParameter("member_role");
				if (member_role == null || member_role.trim().length() == 0) {
					member_role = "0";
					errorMsgs.add("�|������: �ФŪť�");
				}

				String member_gender = req.getParameter("member_gender");
				if (member_gender == null || member_gender.trim().length() == 0) {
					member_gender = "0";
					errorMsgs.add("�|���ʧO: �ФŪť�");
				}

				java.sql.Date member_birthday = null;
				try {
					member_birthday = java.sql.Date.valueOf(req.getParameter("member_birthday").trim());
				} catch (IllegalArgumentException e) {
					member_birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�ͤ�!");
				}

				String member_occupation = req.getParameter("member_occupation");
				String member_occupationReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,20}$";
				if (member_occupation == null || member_occupation.trim().length() == 0) {
					errorMsgs.add("�|��¾�~: �ФŪť�");
				} else if (!member_occupation.trim().matches(member_occupationReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|��¾�~: �u��O�^��r���M����, �B���ץ��ݦb1��20����");
				}

				String member_address = req.getParameter("member_address");
				if (member_address == null || member_address.trim().length() == 0) {
					errorMsgs.add("�|���a�}: �ФŪť�");
				}

				String member_invoice = req.getParameter("member_invoice");
				String member_invoiceReg = "^(/){1}[(A-Z0-9)(+.)(\\-)]{7}$";
				if (member_invoice == null || member_invoice.trim().length() == 0) {
					errorMsgs.add("����s��: �ФŪť�");
					member_invoice = "0";
				} else if (!member_invoice.trim().matches(member_invoiceReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("����s��: �Ĥ@�X�����y/�z, ��l�C�X�h�ѼƦr�i0-9�j�B�j�g�^��iA-Z�j�P�S��Ÿ��i.�j�i-�j�i+�j�զ�");
				}

				Part member_pic = req.getPart("member_pic");
				Member_infoService member_infoSvc = new Member_infoService();
				byte[] buffer = null;
				InputStream fis = member_pic.getInputStream();
				if (fis.available() != 0) {
					buffer = new byte[fis.available()];
					fis.read(buffer);
					fis.close();
				} else {
					buffer = member_infoSvc.getOneMember_info(member_id).getMember_pic();
				}

				String teachclass_on = req.getParameter("teachclass_on");
				if (teachclass_on == null || teachclass_on.trim().length() == 0) {
					teachclass_on = "0";
					errorMsgs.add("�}��}�ҹw��: �ФŪť�");
				}

				String learnclass_on = req.getParameter("learnclass_on");
				if (learnclass_on == null || learnclass_on.trim().length() == 0) {
					learnclass_on = "0";
					errorMsgs.add("�}��׽ҹw��: �ФŪť�");
				}

				String member_homework = req.getParameter("member_homework");

				if (member_homework == null || member_homework.trim().length() == 0) {
					member_homework = "0";
					errorMsgs.add("�}��@�~�w��: �ФŪť�");
				}

				String member_about = req.getParameter("member_about");
				if (member_about == null || member_about.trim().length() == 0) {
					errorMsgs.add("�����: �ФŪť�");
				}

				String member_good_for = req.getParameter("member_good_for");
				if (member_good_for == null || member_good_for.trim().length() == 0) {
					errorMsgs.add("�M��: �ФŪť�");
				}
				java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.sql.Timestamp register_time = new java.sql.Timestamp(sdf.parse(req.getParameter("register_time")).getTime());
				java.sql.Timestamp member_update =  new java.sql.Timestamp(System.currentTimeMillis());
					
				String traccount = req.getParameter("traccount");
				String traccountReg = "^[(0-9)]{12,14}$";
				if (traccount == null || traccount.trim().length() == 0) {
					errorMsgs.add("�״ڱb��: �ФŪť�");
				} else if (!traccount.trim().matches(traccountReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�״ڱb��: �u��O�Ʀr, ���ץ��ݦb12��14����");
				}

				String bank_code = req.getParameter("bank_code");
				String bank_codeReg = "^[(0-9)]{3}$";
				if (bank_code == null || bank_code.trim().length() == 0) {
					errorMsgs.add("�Ȧ�N��: �ФŪť�");
				} else if (!bank_code.trim().matches(bank_codeReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�Ȧ�N��: �u��O�Ʀr, ���ץ��ݬ�3");
				}

				Member_infoVO member_infoVO = new Member_infoVO();
				member_infoVO.setMember_id(member_id);
				member_infoVO.setMember_name(member_name);
				member_infoVO.setMember_email(member_email);
				member_infoVO.setMember_password(member_password);
				member_infoVO.setMember_role(Integer.valueOf(member_role));
				member_infoVO.setMember_gender(Integer.valueOf(member_gender));
				member_infoVO.setMember_birthday(member_birthday);
				member_infoVO.setMember_occupation(member_occupation);
				member_infoVO.setMember_address(member_address);
				member_infoVO.setMember_invoice(member_invoice);
				member_infoVO.setMember_pic(buffer);
				member_infoVO.setTeachclass_on(Integer.valueOf(teachclass_on));
				member_infoVO.setLearnclass_on(Integer.valueOf(learnclass_on));
				member_infoVO.setMember_homework(Integer.valueOf(member_homework));
				member_infoVO.setMember_about(member_about);
				member_infoVO.setMember_good_for(member_good_for);
				member_infoVO.setRegister_time(register_time);
				member_infoVO.setMember_update(member_update);
				member_infoVO.setTraccount(traccount);
				member_infoVO.setBank_code(bank_code);
				System.out.println(register_time);
				System.out.println(member_update);
				System.out.println(errorMsgs);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("member_infoVO", member_infoVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member_info/update_member_info_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				member_infoVO = member_infoSvc.update(member_id, member_name, member_email, member_password,
						Integer.valueOf(member_role), Integer.valueOf(member_gender), member_birthday,
						member_occupation, member_address, member_invoice, buffer, Integer.valueOf(teachclass_on),
						Integer.valueOf(learnclass_on), Integer.valueOf(member_homework), member_about, member_good_for,
						register_time, member_update, traccount, bank_code);
				
				session.setAttribute("member_infoVO", member_infoVO);
				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("member_infoVO", member_infoVO);
				String url = "/front-end/member_info/listOneMember_info.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member_info/update_member_info_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String member_name = req.getParameter("member_name");
				String member_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (member_name == null || member_name.trim().length() == 0) {
					errorMsgs.add("�|���m�W: �ФŪť�");
				} else if (!member_name.trim().matches(member_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���m�W: �u��O���B�^��r�� , �B���ץ��ݦb2��10����");
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
				Integer member_role = Integer.valueOf(req.getParameter("member_role"));
				if (req.getParameter("member_role") == null || req.getParameter("member_role").trim().length() == 0) {
					errorMsgs.add("�|������: �ФŪť�");
				}
				Integer member_gender = Integer.valueOf(req.getParameter("member_gender"));
				if (req.getParameter("member_gender") == null
						|| (req.getParameter("member_gender")).trim().length() == 0) {
					errorMsgs.add("�|���ʧO: �ФŪť�");
				}

				java.sql.Date member_birthday = null;
				try {
					member_birthday = java.sql.Date.valueOf(req.getParameter("member_birthday").trim());
				} catch (IllegalArgumentException e) {
					member_birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J�ͤ�!");
				}

				String member_occupation = req.getParameter("member_occupation");
				String member_occupationReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,20}$";
				if (member_occupation == null || member_occupation.trim().length() == 0) {
					errorMsgs.add("�|��¾�~: �ФŪť�");
				} else if (!member_occupation.trim().matches(member_occupationReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|��¾�~: �u��O�^��r���M����, �B���ץ��ݦb1��20����");
				}

				String member_address = req.getParameter("member_address");
				if (member_address == null || member_address.trim().length() == 0) {
					errorMsgs.add("�|���a�}: �ФŪť�");
				}

				String member_invoice = req.getParameter("member_invoice");
				String member_invoiceReg = "^(/){1}[(A-Z0-9)(+.)(\\-)]{7}$";
				if (member_invoice == null || member_invoice.trim().length() == 0) {
					errorMsgs.add("����s��: �ФŪť�");
				} else if (!member_invoice.trim().matches(member_invoiceReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("����s��: �Ĥ@�X�����y/�z, ��l�C�X�h�ѼƦr�i0-9�j�B�j�g�^��iA-Z�j�P�S��Ÿ��i.�j�i-�j�i+�j�զ�");
				}

				Part member_pic = req.getPart("member_pic");
				byte[] buffer = null;
				InputStream fis = member_pic.getInputStream();
				if (fis.available() != 0) {
					buffer = new byte[fis.available()];
					fis.read(buffer);
					fis.close();
				} else {
					InputStream in = getServletContext()
							.getResourceAsStream("/front-end/member_info/images/bighead.jpg");
//					ServletOutputStream out = res.getOutputStream();
					buffer = new byte[in.available()];
					in.read(buffer);
//					out.write(buffer);
					in.close();
				}

				Integer teachclass_on = Integer.valueOf(req.getParameter("teachclass_on"));

				if (req.getParameter("teachclass_on") == null
						|| (req.getParameter("teachclass_on")).trim().length() == 0) {
					errorMsgs.add("�}��}�ҹw��: �ФŪť�");
				}

				Integer learnclass_on = Integer.valueOf(req.getParameter("learnclass_on"));

				if (req.getParameter("learnclass_on") == null
						|| (req.getParameter("learnclass_on")).trim().length() == 0) {
					errorMsgs.add("�}��׽ҹw��: �ФŪť�");
				}

				Integer member_homework = Integer.valueOf(req.getParameter("member_homework"));

				if (req.getParameter("member_homework") == null
						|| (req.getParameter("member_homework")).trim().length() == 0) {
					errorMsgs.add("�}��@�~�w��: �ФŪť�");
				}

				String member_about = req.getParameter("member_about");
				if (member_about == null || member_about.trim().length() == 0) {
					errorMsgs.add("�����: �ФŪť�");
				}
				String member_good_for = req.getParameter("member_good_for");
				if (member_good_for == null || member_good_for.trim().length() == 0) {
					errorMsgs.add("�M��: �ФŪť�");
				}

				java.sql.Timestamp register_time = null;
				try {
					register_time = java.sql.Timestamp.valueOf(req.getParameter("register_time").trim());
				} catch (IllegalArgumentException e) {
					register_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("���U�ɶ�:�ФŪť�");
				}

				java.sql.Timestamp member_update = null;
				try {
					member_update = java.sql.Timestamp.valueOf(req.getParameter("member_update").trim());
				} catch (IllegalArgumentException e) {
					member_update = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("��s�ɶ�:�ФŪť�");
				}

				String traccount = req.getParameter("traccount");
				String traccountReg = "^[(0-9)]{12,14}$";
				if (traccount == null || traccount.trim().length() == 0) {
					errorMsgs.add("�״ڱb��: �ФŪť�");
				} else if (!traccount.trim().matches(traccountReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�״ڱb��: �u��O�Ʀr, ���ץ��ݦb12��14����");
				}

				String bank_code = req.getParameter("bank_code");
				String bank_codeReg = "^[(0-9)]{3}$";
				if (bank_code == null || bank_code.trim().length() == 0) {
					errorMsgs.add("�Ȧ�N��: �ФŪť�");
				} else if (!bank_code.trim().matches(bank_codeReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�Ȧ�N��: �u��O�Ʀr, ���ץ��ݬ�3");
				}

				String member_id = req.getParameter("member_id");

				Member_infoVO member_infoVO = new Member_infoVO();
				member_infoVO.setMember_id(member_id);
				member_infoVO.setMember_name(member_name);
				member_infoVO.setMember_email(member_email);
				member_infoVO.setMember_password(member_password);
				member_infoVO.setMember_role(member_role);
				member_infoVO.setMember_gender(member_gender);
				member_infoVO.setMember_birthday(member_birthday);
				member_infoVO.setMember_occupation(member_occupation);
				member_infoVO.setMember_address(member_address);
				member_infoVO.setMember_invoice(member_invoice);
				member_infoVO.setMember_pic(buffer);
				member_infoVO.setTeachclass_on(teachclass_on);
				member_infoVO.setLearnclass_on(learnclass_on);
				member_infoVO.setMember_homework(member_homework);
				member_infoVO.setMember_about(member_about);
				member_infoVO.setMember_good_for(member_good_for);
				member_infoVO.setRegister_time(register_time);
				member_infoVO.setMember_update(member_update);
				member_infoVO.setTraccount(traccount);
				member_infoVO.setBank_code(bank_code);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("member_infoVO", member_infoVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member_info/addMember_info.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				Member_infoService member_infoSvc = new Member_infoService();
				member_infoVO = member_infoSvc.add(member_id, member_name, member_email, member_password, member_role,
						member_gender, member_birthday, member_occupation, member_address, member_invoice, buffer,
						teachclass_on, learnclass_on, member_homework, member_about, member_good_for, register_time,
						member_update, traccount, bank_code);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				String url = "/front-end/member_info/listAllMember_info.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�s�W��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_info/addMember_info.jsp");
				failureView.forward(req, res);
			}
		}


		
		
		
		// ===========================================�s�Wuser�|���b��
		// addMember_user.jsp-�D���n�����������========================
		if ("insert_user".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String member_name = req.getParameter("member_name");
				String member_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (member_name == null || member_name.trim().length() == 0) {
					errorMsgs.add("�|���m�W: �ФŪť�");
				} else if (!member_name.trim().matches(member_nameReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("�|���m�W: �u��O���B�^��r�� , �B���ץ��ݦb2��10����");
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
				Integer member_role = Integer.valueOf(req.getParameter("member_role"));
				if (req.getParameter("member_role") == null || req.getParameter("member_role").trim().length() == 0) {
					errorMsgs.add("�|������: �ФŪť�");
				}
				Integer member_gender = Integer.valueOf(req.getParameter("member_gender"));
				if (req.getParameter("member_gender") == null
						|| (req.getParameter("member_gender")).trim().length() == 0) {
					errorMsgs.add("�|���ʧO: �ФŪť�");
				}

				java.sql.Date member_birthday = null;
//						try {
//							member_birthday = java.sql.Date.valueOf(req.getParameter("member_birthday").trim());
//						} catch (IllegalArgumentException e) {
//							member_birthday = new java.sql.Date(System.currentTimeMillis());
//							errorMsgs.add("�п�J�ͤ�!");
//						}

				String member_occupation = req.getParameter("member_occupation");
//						String member_occupationReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{1,20}$";
//						if (member_occupation == null || member_occupation.trim().length() == 0) {
//							errorMsgs.add("�|��¾�~: �ФŪť�");
//						} else if (!member_occupation.trim().matches(member_occupationReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//							errorMsgs.add("�|��¾�~: �u��O�^��r���M����, �B���ץ��ݦb1��20����");
//						}

				String member_address = req.getParameter("member_address");
//						if (member_address == null || member_address.trim().length() == 0) {
//							errorMsgs.add("�|���a�}: �ФŪť�");
//						}

				String member_invoice = req.getParameter("member_invoice");
//						String member_invoiceReg = "^(/){1}[(A-Z0-9)(+.)(\\-)]{7}$";
//						if (member_invoice == null || member_invoice.trim().length() == 0) {
//							errorMsgs.add("����s��: �ФŪť�");
//							member_invoice = "0";
//						} else if (!member_invoice.trim().matches(member_invoiceReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//							errorMsgs.add("����s��: �Ĥ@�X�����y/�z, ��l�C�X�h�ѼƦr�i0-9�j�B�j�g�^��iA-Z�j�P�S��Ÿ��i.�j�i-�j�i+�j�զ�");
//						}

				Part member_pic = req.getPart("member_pic");
				byte[] buffer = null;
				InputStream fis = member_pic.getInputStream();
				if (fis.available() != 0) {
					buffer = new byte[fis.available()];
					fis.read(buffer);
					fis.close();
				} else {
					InputStream in = getServletContext()
							.getResourceAsStream("/front-end/member_info/images/bighead.jpg");
//							ServletOutputStream out = res.getOutputStream();
					buffer = new byte[in.available()];
					in.read(buffer);
//							out.write(buffer);
					in.close();
				}

				Integer teachclass_on = Integer.valueOf(req.getParameter("teachclass_on"));

//						if (req.getParameter("teachclass_on") == null
//								|| (req.getParameter("teachclass_on")).trim().length() == 0) {
//							errorMsgs.add("�}��}�ҹw��: �ФŪť�");
//						}

				Integer learnclass_on = Integer.valueOf(req.getParameter("learnclass_on"));

//						if (req.getParameter("learnclass_on") == null
//								|| (req.getParameter("learnclass_on")).trim().length() == 0) {
//							errorMsgs.add("�}��׽ҹw��: �ФŪť�");
//						}

				Integer member_homework = Integer.valueOf(req.getParameter("member_homework"));

//						if (req.getParameter("member_homework") == null
//								|| (req.getParameter("member_homework")).trim().length() == 0) {
//							errorMsgs.add("�}��@�~�w��: �ФŪť�");
//						}

				String member_about = req.getParameter("member_about");
//						if (member_about == null || member_about.trim().length() == 0) {
//							errorMsgs.add("�����: �ФŪť�");
//						}
				String member_good_for = req.getParameter("member_good_for");
//						if (member_good_for == null || member_good_for.trim().length() == 0) {
//							errorMsgs.add("�M��: �ФŪť�");
//						}

				java.sql.Timestamp register_time = null;
				try {
					register_time = java.sql.Timestamp.valueOf(req.getParameter("register_time").trim());
				} catch (IllegalArgumentException e) {
					register_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("���U�ɶ�:�ФŪť�");
				}

				java.sql.Timestamp member_update = null;
				try {
					member_update = java.sql.Timestamp.valueOf(req.getParameter("member_update").trim());
				} catch (IllegalArgumentException e) {
					member_update = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("��s�ɶ�:�ФŪť�");
				}

				String traccount = req.getParameter("traccount");
//						String traccountReg = "^[(0-9)]{12,14}$";
//						if (traccount == null || traccount.trim().length() == 0) {
//							errorMsgs.add("�״ڱb��: �ФŪť�");
//						} else if (!traccount.trim().matches(traccountReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//							errorMsgs.add("�״ڱb��: �u��O�Ʀr, ���ץ��ݦb12��14����");
//						}

				String bank_code = req.getParameter("bank_code");
//						String bank_codeReg = "^[(0-9)]{3}$";
//						if (bank_code == null || bank_code.trim().length() == 0) {
//							errorMsgs.add("�Ȧ�N��: �ФŪť�");
//						} else if (!bank_code.trim().matches(bank_codeReg)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//							errorMsgs.add("�Ȧ�N��: �u��O�Ʀr, ���ץ��ݬ�3");
//						}

				String member_id = req.getParameter("member_id");

				Member_infoVO member_infoVO = new Member_infoVO();
				member_infoVO.setMember_id(member_id);
				member_infoVO.setMember_name(member_name);
				member_infoVO.setMember_email(member_email);
				member_infoVO.setMember_password(member_password);
				member_infoVO.setMember_role(member_role);
				member_infoVO.setMember_gender(member_gender);
				member_infoVO.setMember_birthday(member_birthday);
				member_infoVO.setMember_occupation(member_occupation);
				member_infoVO.setMember_address(member_address);
				member_infoVO.setMember_invoice(member_invoice);
				member_infoVO.setMember_pic(buffer);
				member_infoVO.setTeachclass_on(teachclass_on);
				member_infoVO.setLearnclass_on(learnclass_on);
				member_infoVO.setMember_homework(member_homework);
				member_infoVO.setMember_about(member_about);
				member_infoVO.setMember_good_for(member_good_for);
				member_infoVO.setRegister_time(register_time);
				member_infoVO.setMember_update(member_update);
				member_infoVO.setTraccount(traccount);
				member_infoVO.setBank_code(bank_code);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("member_infoVO", member_infoVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member_info/addMember_user.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				Member_infoService member_infoSvc = new Member_infoService();
				member_infoVO = member_infoSvc.add(member_id, member_name, member_email, member_password, member_role,
						member_gender, member_birthday, member_occupation, member_address, member_invoice, buffer,
						teachclass_on, learnclass_on, member_homework, member_about, member_good_for, register_time,
						member_update, traccount, bank_code);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				String url = "/front-end/member_info/listAllMember_info.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�s�W��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member_info/addMember_user.jsp");
				failureView.forward(req, res);
			}
		}
		// ===========================================�s�Wuser�|���b������===========================================

		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String member_id = new String(req.getParameter("member_id"));

				/*************************** 2.�}�l�R����� ***************************************/
				Member_infoService member_infoSvc = new Member_infoService();
				member_infoSvc.delete(member_id);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/front-end/member_info/listAllMember_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member_info/listAllMember_info.jsp");
				failureView.forward(req, res);
			}
		}
		
		

		if ("getOne_For_DisplayOrder".equals(action)) {
			try {
				Member_infoVO member_infoVO = (Member_infoVO)session.getAttribute("member_infoVO");
				String member_id=member_infoVO.getMember_id();
				Order_listService order_list = new Order_listService();
				Map<Order_infoVO, List<Order_listVO>> order_infoVO1 = order_list.getMemberOrder(member_id);
				req.setAttribute("order_infoVO1", order_infoVO1);
				RequestDispatcher success = req.getRequestDispatcher("/front-end/order_info/orderinform.jsp");
				success.forward(req, res);
				
			} catch (Exception e) {
				RequestDispatcher diss = req.getRequestDispatcher("/index.jsp");
				diss.forward(req, res);
			}
					
		}
		
		

	}

}
