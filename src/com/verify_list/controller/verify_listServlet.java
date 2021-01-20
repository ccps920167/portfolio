package com.verify_list.controller;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.allClass_info.model.AllClass_infoService;
import com.allClass_info.model.AllClass_infoVO;
import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.verify_list.model.*;

@WebServlet("/verify_list/verify_listServlet")
@MultipartConfig(maxFileSize =10*1024*1024,fileSizeThreshold = 1024*1024 , maxRequestSize = 5*5*1024*1024)
public class verify_listServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		// �s�W
		if ("insert".equals(action)) {
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);

			/*************************** 1.�����ШD�Ѽ� ****************************************/
			try {
				String class_id = req.getParameter("class_id");
				String class_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (class_id == null || class_id.trim().isEmpty()) {
					erMsgs.add("�ҵ{�W�ٽФŪť�");
				} else if (!class_id.trim().matches(class_idReg)) {
					erMsgs.add("�ҵ{�W�ٽп�J���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				String admin_id = req.getParameter("admin_id");
				String admin_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (admin_id == null || admin_id.trim().isEmpty()) {
					erMsgs.add("�f�֤H���ФŪť�");
				} else if (!admin_id.trim().matches(admin_idReg)) {
					erMsgs.add("�f�֤H���п�J���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				String class_check = req.getParameter("class_check");
				if (class_check == null || class_check.trim().isEmpty()) {
					erMsgs.add("�f�֬����ФŪť�");
				}

				Timestamp upload_time = null;
//				try {
//					upload_time = java.sql.Timestamp.valueOf(req.getParameter("upload_time").trim());
//					System.out.println("1");
//				} catch (IllegalArgumentException e) {
					upload_time = new java.sql.Timestamp(System.currentTimeMillis());
//					erMsgs.add("�п�J���");
//				}



				Verify_listVO verify_listVO = new Verify_listVO();
				verify_listVO.setAdmin_id(admin_id);
				verify_listVO.setClass_id(class_id);
				verify_listVO.setClass_check(class_check);
				verify_listVO.setUpload_time(upload_time);

				
				if(!erMsgs.isEmpty()) {
					req.setAttribute("verify_listVO", verify_listVO);
					RequestDispatcher fail = req.getRequestDispatcher("/back-end/verify_list/addVL.jsp");
					fail.forward(req, res);
					return;
				}
				/***************************2.�}�l�s�W���***************************************/
				Verify_listService vlSrc = new Verify_listService();
				verify_listVO = vlSrc.addVerify_list(class_id, admin_id, class_check, upload_time);
				System.out.println("2");
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/verify_list/ListAllVL.jsp";
				RequestDispatcher sucess = req.getRequestDispatcher(url);
				sucess.forward(req, res);

			} catch (Exception e) {
				
//				erMsgs.add(e.getMessage());
				erMsgs.add("�L�k�s�W���");
				System.out.println(e.getMessage());
				RequestDispatcher fail = req
						.getRequestDispatcher("/back-end/verify_list/addVL.jsp");
				fail.forward(req, res);
			}
		}
		
		//�d1
		if("SearchOne".equals(action)) {
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);
			
			try {
				String str = req.getParameter("verify_id");
				
				if(str == null || str.trim().isEmpty()) {
					erMsgs.add("�п�J�f�ָ�ƽs��");
				}	
				
				if(!erMsgs.isEmpty()) {
					RequestDispatcher fail = req.getRequestDispatcher("/back-end/verify_list/Select_pageVL.jsp");
					fail.forward(req, res);
					return;
				}
				
				
				/***************************2.�}�l�d�߸��*****************************************/
				Verify_listService vlSrc = new Verify_listService();
				Verify_listVO verify_listVO = vlSrc.getOneVerify_list(str);
				
				if(verify_listVO == null) {
					erMsgs.add("�d�L���");
				}
				if(!erMsgs.isEmpty()) {
					RequestDispatcher fail = req.getRequestDispatcher("/back-end/verify_list/Select_pageVL.jsp");
					fail.forward(req, res);
					return;
				}
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("verify_listVO", verify_listVO);
				RequestDispatcher sucess = req.getRequestDispatcher("/back-end/verify_list/ListOneVL.jsp");
				sucess.forward(req, res);
			} catch (Exception e) {
				erMsgs.add(e.getMessage());
//				System.out.println("�o�̿��F");
				RequestDispatcher fail = req.getRequestDispatcher("/back-end/verify_list/Select_pageVL.jsp");
				fail.forward(req, res);
			}
			
			
		}
		
		//�ק� �Ӧ�ListAllVL
		if ("getOne_For_Update".equals(action)) { // �Ӧ�ListAllVL.jsp���ШD

			List<String> erMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("erMsgs", erMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String verify_id = new String(req.getParameter("verify_id"));
				System.out.println(verify_id);
				
				/***************************2.�}�l�d�߸��****************************************/
				Verify_listService vlSvc = new Verify_listService();
				Verify_listVO verify_listVO = vlSvc.getOneVerify_list(verify_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("verify_listVO", verify_listVO);         // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/verify_list/updateVL.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				erMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/verify_list/ListAllVL.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//�ק�
		if ("updateOne".equals(action)) {
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);

			try {

				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String verify_id = req.getParameter("verify_id");
				
				System.out.println(verify_id);
				
				String class_id = req.getParameter("class_id");
				String class_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (class_id == null || class_id.trim().isEmpty()) {
					erMsgs.add("�ҵ{�W�ٽФŪť�");
				} else if (!class_id.trim().matches(class_idReg)) {
					erMsgs.add("�ҵ{�W�ٽп�J���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}
                
				System.out.println(class_id);
				
				String admin_id = req.getParameter("admin_id");
				String admin_idReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (admin_id == null || admin_id.trim().isEmpty()) {
					erMsgs.add("�f�֤H���ФŪť�");
				} else if (!admin_id.trim().matches(admin_idReg)) {
					erMsgs.add("�f�֤H���п�J���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				System.out.println(admin_id);
				
				String class_check = req.getParameter("class_check");
				if (class_check == null || class_check.trim().isEmpty()) {
					erMsgs.add("�f�֬����ФŪť�");
				}
				
				System.out.println(class_check);

				Timestamp upload_time = null;
//				try {
//					upload_time = java.sql.Timestamp.valueOf(req.getParameter("upload_time").trim());
//				} catch (IllegalArgumentException e) {
					upload_time = new java.sql.Timestamp(System.currentTimeMillis());
//					erMsgs.add("�п�J���");
//				}
				
				System.out.println(upload_time);

				Verify_listVO verify_listVO = new Verify_listVO();
				verify_listVO.setVerify_id(verify_id);
				verify_listVO.setAdmin_id(admin_id);
				verify_listVO.setClass_id(class_id);
				verify_listVO.setClass_check(class_check);
				verify_listVO.setUpload_time(upload_time);
				
				if(!erMsgs.isEmpty()) {
					req.setAttribute("verify_listVO", verify_listVO);
					RequestDispatcher fail = req.getRequestDispatcher("/back-end/verify_list/addVL.jsp");
					fail.forward(req, res);
					return;
				}
				/***************************2.�}�l�s�W���***************************************/
				Verify_listService vlSvc = new Verify_listService();
				verify_listVO = vlSvc.updateVerify_list(class_id, admin_id, class_check, upload_time, verify_id);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				req.setAttribute("verify_listVO", verify_listVO);
				String url = "/back-end/verify_list/ListAllVL.jsp";
				RequestDispatcher sucess = req.getRequestDispatcher(url);
				sucess.forward(req, res);

			} catch (Exception e) {
				erMsgs.add(e.getMessage());
				RequestDispatcher fail = req
						.getRequestDispatcher("/back-end/verify_list/updateVL.jsp");
				fail.forward(req, res);
			}
		}
		
		
		
		//�R��
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> erMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("erMsgs", erMsgs);
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String verify_id = new String(req.getParameter("verify_id"));
//				System.out.println(verify_id);
				
				/***************************2.�}�l�R�����***************************************/
				Verify_listService vlSrc = new Verify_listService();
				vlSrc.deleteVerify_list(verify_id);
				System.out.println("here");
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				String url = "/back-end/verify_list/ListAllVL.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				System.out.println("�A�n");
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				erMsgs.add("�R����ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/verify_list/ListAllVL.jsp");
				failureView.forward(req, res);
			}
		}
/***************************************************************************************/		
		//�޲z���f�ֽҵ{
		if("back-verifyClass_list".equals(action)){
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String class_id = req.getParameter("class_id");
				
				/***************************2.�}�l�d�߸��****************************************/
				Class_infoService ciSvc = new Class_infoService();
				Class_infoVO Class_infoVO = ciSvc.getOneClass_info(class_id);
				
				/***************************1.�����ШD�Ѽ�****************************************/
				/***************************2.�}�l�d�߸��****************************************/
				AllClass_infoService aciSrv = new AllClass_infoService(); 
				AllClass_infoVO AllClass_infoVO = aciSrv.getAll(class_id);
				session.setAttribute("AllClass_infoVO", AllClass_infoVO); 
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("Class_infoVO", Class_infoVO);         // ��Ʈw���X��Class_infoVO����,�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/verify_list/back-add_verify_list-Details.jsp");
	    	   	failureView.forward(req, res);
				return;// �{�����_

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				erMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/verify_list/back-verify_listAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("fail".equals(action)){
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);
		
			try {

				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String class_id = req.getParameter("class_id");


				Integer class_status = 6;
				
				String class_check = req.getParameter("class_verify");
				String admin_id= (String)session.getAttribute("admin_id");
				
				Timestamp upload_time = null;
				upload_time = new java.sql.Timestamp(System.currentTimeMillis());
				

				Class_infoVO class_infoVO = new Class_infoVO();

				// Send the use back to the form, if there were errors
				if (!erMsgs.isEmpty()) {
					req.setAttribute("Class_infoVO", class_infoVO); // �t����J�榡���~��Class_infoVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/class_info/update_Class_info_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				Class_infoService ciSvc = new Class_infoService();
				ciSvc.updateStatus(class_status, admin_id, class_id);
				
				Verify_listService veSvc = new Verify_listService();
				veSvc.addVerify_list(class_id, admin_id, class_check, upload_time);		
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/verify_list/back-verify_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllClass_info.jsp
				successView.forward(req, res);				


				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				erMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/class_info/listAllClass_info.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("success".equals(action)){
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);
		
			try {
//				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String class_id = req.getParameter("class_id");



				Integer class_status = 1;
				
				String class_check = null;
				String admin_id= (String)session.getAttribute("admin_id");
				
				Timestamp upload_time = null;
				upload_time = new java.sql.Timestamp(System.currentTimeMillis());
				
				Timestamp startfund_date = null;
				startfund_date = new java.sql.Timestamp(System.currentTimeMillis());
				
				Timestamp startclass_time = null;
				Long startclassLong = System.currentTimeMillis()+((long)60*24*60*60*1000);
				startclass_time = new java.sql.Timestamp(startclassLong);
				
				Timestamp update_time = null;
  				update_time = new java.sql.Timestamp(System.currentTimeMillis());

				Class_infoVO class_infoVO = new Class_infoVO();
//				class_infoVO.setClass_id(class_id);
//				class_infoVO.setClass_name(class_name);
//				class_infoVO.setMember_id(member_id);
//				class_infoVO.setSubclass_id(subclass_id);
//				class_infoVO.setStartfund_date(startfund_date);
//				class_infoVO.setStartclass_time(startclass_time);
//				class_infoVO.setClass_description(class_description);
//				class_infoVO.setClass_picture(class_picture);
//				class_infoVO.setStartfund_price(startfund_price);
//				class_infoVO.setOriginal_price(original_price);
//				class_infoVO.setPeople_threshold(people_threshold);
//				class_infoVO.setClass_length(class_length);
				// Send the use back to the form, if there were errors
				if (!erMsgs.isEmpty()) {
					req.setAttribute("Class_infoVO", class_infoVO); // �t����J�榡���~��Class_infoVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/class_info/update_Class_info_input.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println(startfund_date);
				System.out.println(startclass_time);
				/***************************2.�}�l�s�W���***************************************/
				Class_infoService ciSvc = new Class_infoService();
				ciSvc.updateVerify1(class_status, admin_id, class_id, startfund_date, startclass_time, update_time);
				
				Verify_listService veSvc = new Verify_listService();
				veSvc.addVerify_list(class_id, admin_id, class_check, upload_time);		
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/verify_list/back-verify_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllClass_info.jsp
				successView.forward(req, res);				

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				erMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
System.out.println(e.getMessage());				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/verify_list/back-verify_listAll.jsp");
				
				failureView.forward(req, res);
			}
		}
		
		//�޲z�����ҵ{���A
		if("updateClassStatus".equals(action)){
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String class_id = req.getParameter("class_id");
				
				/***************************2.�}�l�d�߸��****************************************/
				Class_infoService ciSvc = new Class_infoService();
				Class_infoVO Class_infoVO = ciSvc.getOneClass_info(class_id);
				AllClass_infoService aciSrv = new AllClass_infoService(); 
				AllClass_infoVO AllClass_infoVO = aciSrv.getAll(class_id);
				session.setAttribute("AllClass_infoVO", AllClass_infoVO); 				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("Class_infoVO", Class_infoVO);         // ��Ʈw���X��Class_infoVO����,�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/verify_list/UpdateClassStatus.jsp");
	    	   	failureView.forward(req, res);
				return;// �{�����_

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				erMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/verify_list/back-verify_listAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("changeClassStatus".equals(action)){
			List<String> erMsgs = new LinkedList<String>();
			req.setAttribute("erMsgs", erMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/

//				session.getAttribute("");
				String admin_id = (String)session.getAttribute("admin_id");
				
				String class_id = req.getParameter("class_id");
//System.out.println(class_id);
				Integer class_status = Integer.valueOf(req.getParameter("class_status"));
//System.out.println(class_status);


                Class_infoVO Class_infoVO = new Class_infoVO();
				
				/***************************2.�}�l�d�߸��****************************************/
				Class_infoService ciSvc = new Class_infoService();
				ciSvc.updateStatus(class_status, admin_id, class_id);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("Class_infoVO", Class_infoVO);         // ��Ʈw���X��Class_infoVO����,�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/verify_list/back-verify_listAll.jsp");
	    	   	failureView.forward(req, res);
				return;// �{�����_

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				erMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/verify_list/Select_pageVL.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		//�Ӧ�back-verify_list6.jsp
				if("returnClassCheck".equals(action)){
					List<String> erMsgs = new LinkedList<String>();
					req.setAttribute("erMsgs", erMsgs);
System.out.println("123");
					try {
						/***************************1.�����ШD�Ѽ�****************************************/
						String class_id = req.getParameter("class_id");
System.out.println(class_id);
						/***************************2.�}�l�d�߸��****************************************/
						Class_infoService ciSvc = new Class_infoService();
						Class_infoVO Class_infoVO = ciSvc.getOneClass_info(class_id);
						AllClass_infoService aciSrv = new AllClass_infoService(); 
						AllClass_infoVO AllClass_infoVO = aciSrv.getAll(class_id);
						session.setAttribute("AllClass_infoVO", AllClass_infoVO); 
						
						Verify_listService vlSvc = new Verify_listService();
						Verify_listVO ClassCheckVO = vlSvc.getClassCheck(class_id);
System.out.println(ClassCheckVO.getClass_check());
						req.setAttribute("ClassCheckVO", ClassCheckVO);
						/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
						req.setAttribute("Class_infoVO", Class_infoVO); 
						// ��Ʈw���X��Class_infoVO����,�s�Jreq
System.out.println("789");
						RequestDispatcher failureView = req.getRequestDispatcher("/back-end/verify_list/ClassReturn.jsp");
			    	   	failureView.forward(req, res);
						return;// �{�����_

						/***************************��L�i�઺���~�B�z**********************************/
					} catch (Exception e) {
						erMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/verify_list/back-verify_listAll.jsp");
						failureView.forward(req, res);
					}
				}


		

	}

}
