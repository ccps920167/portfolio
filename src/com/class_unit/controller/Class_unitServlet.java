package com.class_unit.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.class_unit.model.Class_unitService;
import com.class_unit.model.Class_unitVO;
import com.sub_class.model.Sub_classVO;

@WebServlet("/Class_unit/Class_unitServlet")
@MultipartConfig(fileSizeThreshold = 5 * 5 * 5 * 1024 * 1024, maxFileSize = 5 * 5 * 5 * 1024 * 1024, maxRequestSize = 5
		* 5 * 5 * 1024 * 1024 * 1024)
public class Class_unitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action"); // ��action�Ѽ� ���if��k
		// ���v��
		if ("many_video".equals(action)) {
			res.setContentType("video/mp4"); // �]�w�^������
			res.setHeader("Accept-Ranges", "bytes");
			String unit_id = req.getParameter("unit_id").trim(); // ���oget�Ѽ� �� ?unit_id
			Class_unitService class_unitService = new Class_unitService(); // �I�sService��k
			Class_unitVO class_unitVO = class_unitService.select(unit_id); // �Q��id�o�쪫��
			OutputStream out = res.getOutputStream(); // �إ߿�X�y
			try {
				if (class_unitVO.getVideo() != null || class_unitVO.getVideo().length != 0) { // �P�_�ɮ׬O�_�s�b
					res.addHeader("Accept-Ranges", "bytes");
					res.addHeader("Content-Length", "100");
					res.setStatus(200);
					res.setContentLength(class_unitVO.getVideo().length); // �q���s�����ɮת���
					InputStream in = new ByteArrayInputStream(class_unitVO.getVideo()); // �Nbyte[]�ഫ��InputStream
					byte[] buf = new byte[class_unitVO.getVideo().length]; // 4K buffer //�]�wbyte[]�j�p
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len); // �N��ƿ�X
					}

				} else {
					// �d�߭Ȥ��s�b
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);    //��X���~�T��
					InputStream in = getServletContext().getResourceAsStream("/img/NoResult/unnamed.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
			} catch (Exception e) {
				// �S����J�d�߭�
				InputStream in = getServletContext().getResourceAsStream("/img/NoResult/unnamed.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			} finally {
				out.close();
			}
		}

		// AJEX - �ק窱�A
		// �ǤJ��� �v��id �ק窱�A
		if ("video_status_update".equals(action)) {
			res.setContentType("text/plain"); // �]�w�^������
			String unit_id = req.getParameter("unit_id").trim(); // ���oget�Ѽ� �� ?unit_id
			String video_status = req.getParameter("video_status").trim();
			// �S���쪺���~�B�z
			Class_unitService class_unitService = new Class_unitService(); // �I�sService��k
			Class_unitVO class_unitVO = class_unitService.select(unit_id); // �Q��id�o�쪫��
			OutputStream out = res.getOutputStream(); // �إ߿�X�y
//			try {
			if (class_unitVO != null) { // �P�_�ɮ׬O�_�s�b
				java.sql.Timestamp video_updatetime = new Timestamp(System.currentTimeMillis());
				class_unitService.update(class_unitVO.getUnit_id(), class_unitVO.getChapter_id(),
						class_unitVO.getUnit_name(), class_unitVO.getVideo(), class_unitVO.getVideo_long(),
						video_updatetime, Integer.parseInt(video_status));
			} else {
				System.out.println("�d�߭Ȥ��s�b");
			}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}finally {
			out.close();
//			}
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		// ���o�ӷ��Ѽ�
		String action = req.getParameter("action");

//		if ("listEmps_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				
//				/***************************1.�N��J����ରMap**********************************/ 
//				//�ĥ�Map<String,String[]> getParameterMap()����k 
//				//�`�N:an immutable java.util.Map 
//				Map<String, String[]> map = req.getParameterMap();
//				
//				/***************************2.�}�l�ƦX�d��***************************************/
//				Class_unitService service = new Class_unitService();
//				List<Class_unitVO>list = service.getClass_unitAll();
//				
//				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
//				req.setAttribute("listEmps_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
//				RequestDispatcher successView = req.getRequestDispatcher("/back-end/Class_unit/listEmps_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
//				successView.forward(req, res);
//				
//				/***************************��L�i�઺���~�B�z**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		

		/******************************** ��}�d���� ********************************/
		if ("getALL".equals(action)) {
			/*************************** ��� ***********************/
			String url = "/back-end/class_unit/listClass_unit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/******************************** ��}�s�W��� ********************************/
		if ("addnew".equals(action)) {
			/*************************** ��� ***********************/
			String url = "/back-end/class_unit/addClass_unit_all.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/******************************** �d�ߤ@����� ********************************/
		if ("listOneClass_unit".equals(action)) { // �Ӧ�listOneClass_unit.jsp���ШD

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			// �إ߮e���˿��~���+��J�ݩʤ�
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			// �榡�P�_

			String unit_id = req.getParameter("unit_id");
			
			/*************************** 2.�}�l�d�߸�� ***************************************/
			Class_unitService Class_unitSrv = new Class_unitService();
			// ��Ʈw���X�����,�s�J����
			Class_unitVO Class_unitVO = Class_unitSrv.select(unit_id);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			// ����s�J�ݩ�
			req.setAttribute("class_unitVO", Class_unitVO);
			// ���
			String url = "/back-end/class_unit/listOneClass_unit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z **********************************/

		}
		/******************************** �s�W�@����� ********************************/
		if ("insert_all".equals(action)) { // �Ӧ�addClass_unit.jsp���ШD
			// �˿��~���
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			String unit_name = req.getParameter("unit_name");
			System.out.println(unit_name);
			// unit_name�P�_�O�_����
			if (unit_name == null || unit_name.trim().isEmpty()) {
				errorMsgs.add("�椸�W�٤��i�H�ť�");
			}
			String chapter_id = req.getParameter("chapter_id");
			if (chapter_id == null || chapter_id.trim().isEmpty()) {
				errorMsgs.add("���`���i�ť�");
			}
			Integer video_status = 0;
			Part video_part = req.getPart("video");
			byte[] video = null;
			if (video_part != null) {
				InputStream in = video_part.getInputStream();
				video = new byte[in.available()];
				in.read(video);
				in.close();
			}
			String video_long = req.getParameter("video_long");
			// �w�g�s�b����Ʀs�J����
			Class_unitVO Class_unitVo = new Class_unitVO();
			Class_unitVo.setUnit_name(unit_name);
			Class_unitVo.setChapter_id(chapter_id);

			// �����~�T�����^�h
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Class_unitVO", Class_unitVo); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_unit/addClass_unit_all.jsp");
				failureView.forward(req, res);
				return;
			}

			java.sql.Timestamp video_updatetime = new java.sql.Timestamp(new Date().getTime());

			/*************************** 2.�}�l�s�W��� ***************************************/
			Class_unitService service = new Class_unitService();
			service.insert(chapter_id, unit_name, video, video_long, video_updatetime, video_status);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			// ���
			String url = "/back-end/class_unit/listClass_unit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z **********************************/

		}
		/******************************** �s�W�@����� ********************************/
		if ("insert".equals(action)) { // �Ӧ�addClass_unit.jsp���ШD
			// �˿��~���
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			String unit_name = req.getParameter("unit_name");
			System.out.println(unit_name);
			// unit_name�P�_�O�_����
			if (unit_name == null || unit_name.trim().isEmpty()) {
				errorMsgs.add("�椸�W�٤��i�H�ť�");
			}
			String chapter_id = req.getParameter("chapter_id");
			if (chapter_id == null || chapter_id.trim().isEmpty()) {
				errorMsgs.add("���`���i�ť�");
			}

			Integer video_status = 0;

			Class_unitService service = new Class_unitService();
			Part video_part = req.getPart("video");
			InputStream in = video_part.getInputStream();
			byte[] video =null;
			if (in.available() != 0) {
				video = new byte[in.available()];
				in.read(video);
				in.close();
			}

			String video_long = req.getParameter("video_long");

			// �w�g�s�b����Ʀs�J����
			Class_unitVO Class_unitVo = new Class_unitVO();
			Class_unitVo.setUnit_name(unit_name);
			Class_unitVo.setChapter_id(chapter_id);

			// �����~�T�����^�h
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Class_unitVO", Class_unitVo); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/class_unit/addClass_unit.jsp");
				failureView.forward(req, res);
				return;
			}

			java.sql.Timestamp video_updatetime = new java.sql.Timestamp(new Date().getTime());

			/*************************** 2.�}�l�s�W��� ***************************************/
			service.insert(chapter_id, unit_name, video, video_long, video_updatetime, video_status);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			// ���
			String url = "/back-end/class_unit/listClass_unit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z **********************************/

		}

		/******************************** �s�W�@����� ********************************/
		if ("delete".equals(action)) {
			// �˿��~���
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
//			�����@���Ѽ� �R�����Ǹ�
				String unit_id = req.getParameter("unit_id").trim();
				/*************************** 2.�}�l�s�W��� ***************************************/
				Class_unitService Srv = new Class_unitService();
				Srv.delete(unit_id);
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				// ���
				String url = "/back-end/class_unit/listClass_unit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (RuntimeException e) {
				errorMsgs.add("�ӵ���Ƥw�g���p�A�ФŧR��");
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/class_unit/listClass_unit.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
			} catch (Exception ex) {
				System.out.println("�����~");
				/*************************** ��L�i�઺���~�B�z **********************************/
				errorMsgs.add(ex.getMessage());
				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				// ���
				String url = "/back-end/class_unit/listClass_unit.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			}
		}
		if ("update_unit_id".equals(action)) {
			/*********************** 0.�]�w���~�T���e�� ***********************/
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z ***********************/
			String unit_id = req.getParameter("unit_id");
			/*********************** 2.�}�l�s�W��� ***********************/
			Class_unitService Srv = new Class_unitService();
			Class_unitVO Class_unitVO = Srv.select(unit_id);
			req.setAttribute("class_unitVO", Class_unitVO);
			/*********************** 3.�s�W����,�ǳ���� ***********************/
			// ���
			String url = "/back-end/class_unit/update_Class_unit_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			/*********************** ��L�i�઺���~�B�z ***********************/
			/*************************** ��� ***********************/

		}

		if ("update".equals(action)) {
			// �˿��~���
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Class_unitService service = new Class_unitService();
			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/
			String unit_id = req.getParameter("unit_id");
			String unit_name = req.getParameter("unit_name");
			// unit_name�P�_�O�_����
			if (unit_name == null || unit_name.trim().isEmpty()) {
				errorMsgs.add("�椸�W�٤��i�H�ť�");
			}
			String chapter_id = req.getParameter("chapter_id");
			if (chapter_id == null || chapter_id.trim().isEmpty()) {
				errorMsgs.add("���`���i�ť�");
			}

			Integer video_status = 0;

			Part video_part = req.getPart("video");
			InputStream in = video_part.getInputStream();
			byte[] video =null;
			if (in.available() == 0) {
				video=service.select(unit_id).getVideo();
			} else {
				video = new byte[in.available()];
				in.read(video);
				in.close();
			}
			String video_long = req.getParameter("video_long");

			// �w�g�s�b����Ʀs�J����
			Class_unitVO Class_unitVo = new Class_unitVO();
			Class_unitVo.setUnit_name(unit_name);
			Class_unitVo.setChapter_id(chapter_id);

			// �����~�T�����^�h
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("Class_unitVO", Class_unitVo); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/class_unit/update_Video_record_input.jsp");
				failureView.forward(req, res);
				return;
			}

			java.sql.Timestamp video_updatetime = new java.sql.Timestamp(new Date().getTime());

			/*************************** 2.�}�l�s�W��� ***************************************/

			service.update(unit_id, chapter_id, unit_name, video, video_long, video_updatetime, video_status);

			/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
			// ���
			String url = "/back-end/class_unit/listClass_unit.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** ��L�i�઺���~�B�z **********************************/
		}
//		if ("unit_id_sub_class".equals(action)) {
//			
//			/***********************0.�]�w���~�T���e��***********************/
//			//���ο��~�B�z
//			/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z***********************/
//			String unit_id = req.getParameter("unit_id");
//			String unit_name = req.getParameter("unit_name");
//			/*********************** 2.�}�l�s�W���***********************/
//			Class_unitService Srv = new Class_unitService();
//			Set<Sub_classVO> Sub_classVO_list = Srv.getSub_classByClass_unit_id(unit_id);
//			Class_unitVO Class_unitVO = new Class_unitVO();
//			Class_unitVO.setUnit_id(unit_id);
//			Class_unitVO.setUnit_name(unit_name);
//			req.setAttribute("Class_unitVO", Class_unitVO);
//
//			req.setAttribute("Sub_classVO_list", Sub_classVO_list);
//			/*********************** 3.�s�W����,�ǳ����***********************/
//			String url = "/back-end/sub_class/unit_id_sub_class.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//			/*********************** ��L�i�઺���~�B�z***********************/
//			/*************************** ��� ***********************/
//			
//		}

	}
}
