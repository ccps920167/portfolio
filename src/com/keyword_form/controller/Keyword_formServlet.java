package com.keyword_form.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.keyword_form.model.Keyword_formService;
import com.keyword_form.model.Keyword_formVO;
import com.member_info.model.Member_infoService;

@WebServlet("/keyword_form/Keyword_formServlet")
public class Keyword_formServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("------------------------");
		System.out.println("Keyword_formController");
		System.out.println("action = " + action);
		System.out.println("------------------------");

		if ("get_by_date".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String startdate = req.getParameter("startdate");
				String enddate = req.getParameter("enddate");
				if (startdate == null || (startdate.trim()).length() == 0) {
					errorMsgs.add("�п�ܶ}�l���");
				}
				if (enddate == null || (enddate.trim()).length() == 0) {
					errorMsgs.add("�п�ܵ������");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				Keyword_formService keyword_formSvc = new Keyword_formService();
				List<Keyword_formVO> list = (List<Keyword_formVO>) keyword_formSvc.getKeywordbydate(startdate, enddate);
				System.out.println(list);
				if (list == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jspp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("startdate", startdate);
				req.setAttribute("enddate", enddate);
				req.setAttribute("list", list); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/keyword_form/Keyword_formbydate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
				failureView.forward(req, res);
			}
		}
		//========================�έp����r����-Start============================
		if ("getcount".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String search_word;
			int count;

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				/*************************** 2.�}�l�d�߸�� *****************************************/
				Keyword_formService keyword_formSvc = new Keyword_formService();
				List<Keyword_formVO> list = (List<Keyword_formVO>) keyword_formSvc.getAll();
				
				List <String>searchwordlist = new ArrayList<String>();
				List <Integer>countlist = new ArrayList<Integer>();
				
				Map<String,Integer> map = new HashMap<String,Integer>();
				for(Keyword_formVO keylist:list) {
				search_word = keylist.getSearch_word()+"";
//				System.out.println(search_word);
				searchwordlist.add(search_word);
				}
				for(String a:searchwordlist) {
//					System.out.println(a);
					count=keyword_formSvc.getKeywordCount(a);
					countlist.add(count);
					map.put(a, count);
				}
				System.out.println(map);
//				for(String k:map.keySet()) {
//					System.out.println(k);
//				}
//				for(int y:map.values()) {
//					System.out.println(y);
//				}
				
				if (map.isEmpty()) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("map", map); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/keyword_form/Keyword_formCount.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneEmp.jsp
				successView.forward(req, res);
			
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//========================�έp����r����-END==============================
		

	if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			/***************************1.�����ШD�Ѽ�****************************************/
			String keyword_id = req.getParameter("keyword_id").trim();
			
			/***************************2.�}�l�d�߸��****************************************/
			Keyword_formService keyword_formSvc = new Keyword_formService();
			Keyword_formVO keyword_formVO = keyword_formSvc.getOnekeyword_form(keyword_id);
							
			/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
			req.setAttribute("keyword_formVO", keyword_formVO);         // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/back-end/keyword_form/update_Keyword_form.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);

			/***************************��L�i�઺���~�B�z**********************************/
		} catch (Exception e) {
			errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/keyword_form/listAllKeyword_form.jsp");
			failureView.forward(req, res);
		}
	}

	if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
	
		try {
			/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			//keyword_id��seq�ͦ��A���i���
			String keyword_id = req.getParameter("keyword_id").trim();
			System.out.println("1");
			//
			Keyword_formService keyword_formSvc = new Keyword_formService();
			Keyword_formVO keyword_formvo = keyword_formSvc.getOnekeyword_form(keyword_id);
			
			java.sql.Date search_date = null;
			try {
			search_date = java.sql.Date.valueOf(req.getParameter("search_date").trim());
			}catch (IllegalArgumentException e) {
				search_date=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("�п�J���!");
			}
			System.out.println("2");
			
			String search_word = req.getParameter("search_word");
			if (search_word == null || search_word.trim().length() == 0) {
				search_word = (keyword_formvo.getSearch_word() != null) ? keyword_formvo.getSearch_word() : "�ФŪť�";
				errorMsgs.add("�j�M���e�ФŪť�");
			}	
			System.out.println("3");
			
	
			Keyword_formVO keyword_formVO = new Keyword_formVO();
			
			keyword_formVO.setKeyword_id(keyword_id);
			keyword_formVO.setSearch_date(search_date);
			keyword_formVO.setSearch_word(search_word);
			System.out.println("��˧���");

//		 Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("keyword_formVO", keyword_formVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/keyword_form/update_Keyword_form.jsp");
				failureView.forward(req, res);
				return; //�{�����_
	}
			
			/***************************2.�}�l�ק���*****************************************/
			keyword_formVO = keyword_formSvc.updatekeyword_form(keyword_id, search_date, search_word);
			/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
			req.setAttribute("keyword_formVO", keyword_formVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			String url = "/back-end/keyword_form/listOneKeyword_form.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);

			/***************************��L�i�઺���~�B�z*************************************/
		} catch (Exception e) {
			errorMsgs.add("�ק��ƥ���:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/keyword_form/update_Keyword_form.jsp");
			failureView.forward(req, res);
		}
	}

		if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				java.sql.Date search_date = null;
				try {
				search_date = java.sql.Date.valueOf(req.getParameter("search_date").trim());
				}catch (IllegalArgumentException e) {
					search_date=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�ܤ��!");
				}
				//��J���r�ťխn�_�}�x�s�A�٭n��s
				String search_word = req.getParameter("search_word").trim();
//				String search_wordReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,20}$";
//				if (search_word == null || search_word.trim().length() == 0) {
//					errorMsgs.add("");
//				} else if (!search_word.trim().matches(search_word)) { // �H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("�j�M���e: �u��O���B�^��r��, �B���ץ��ݦb2��20����");
//				}
				
//				} catch (NumberFormatException e) {
//					errorMsgs.add(e.getMessage());
//				}catch(RuntimeException e) {
//					errorMsgs.add(e.getMessage());
//				}
				
//			    String keyword_id = req.getParameter("keyword_id");

				Keyword_formVO keyword_formVO = new Keyword_formVO();
				keyword_formVO.setSearch_date(search_date);
				keyword_formVO.setSearch_word(search_word);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("keyword_formVO", keyword_formVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/addKeyword_form.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W��� ***************************************/
				Keyword_formService keyword_formSvc = new Keyword_formService();
				keyword_formSvc.addKeyword_form(keyword_formVO);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/keyword_form/listAllKeyword_form.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllKeyword_form.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
				failureView.forward(req, res);
			}
		}
		//�R��
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				String keyword_id = new String(req.getParameter("keyword_id"));

				/*************************** 2.�}�l�R����� ***************************************/
				Keyword_formService keyword_formSvc = new Keyword_formService();
				keyword_formSvc.deleteKeyword_form(keyword_id);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/back-end/keyword_form/Keyword_formAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/keyword_form/Keyword_formAll.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
