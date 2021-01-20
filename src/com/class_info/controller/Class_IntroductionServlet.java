package com.class_info.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.image.ImageUtil;
import com.keyword_form.model.Keyword_formService;
import com.keyword_form.model.Keyword_formVO;
import com.order_list.model.Order_listService;

/**
 * Servlet implementation class Class_infoServlet
 */
@WebServlet("/Class_info/Class_Introduction")
public class Class_IntroductionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Class_IntroductionServlet() {
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// �ҵ{�d�߭���
		if ("search".equals(action)) {

			// ====================================�����ϥΪ̿�J����r==========================================
			if (req.getParameter("subclass_id") == null) {
				/*********************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z *************************/

				java.sql.Date search_date = null;
				try {
					search_date = new java.sql.Date(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
					search_date = new java.sql.Date(System.currentTimeMillis());
//			    errorMsgs.add("�п�ܤ��!");//�w�w�]�ثe������ÿ��
				}
				// ��J���r�ťխn�_�}�x�s
				List<String> searchword = java.util.Arrays.asList(req.getParameter("class_name").split(" "));

				Keyword_formVO keyword_formVO = new Keyword_formVO();

				/*************************** 2.�}�l�s�W��� ***************************************/
				Keyword_formService keyword_formSvc = new Keyword_formService();
				for (String saveinput : searchword) {

					if (!saveinput.replaceAll("[\\pP\\p{Punct}]", "").isEmpty()) {
						keyword_formVO.setSearch_date(search_date);
						keyword_formVO.setSearch_word(saveinput.replaceAll("[\\pP\\p{Punct}]", ""));
						System.out.println(search_date);
						System.out.println(saveinput);
						keyword_formSvc.addKeyword_form(keyword_formVO);
					} else {
						res.sendRedirect(req.getContextPath() + "/index.jsp");
						return;
					}
				}

				System.out.println("�s�W����r" + keyword_formVO);

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) ***********/
				// ���եΤ~��歶���A��ڱM�׫h�Τ���
				// String url = "/back-end/keyword_form/listAllKeyword_form.jsp";
				// RequestDispatcher successView = req.getRequestDispatcher(url); //
				// �s�W���\�����listAllKeyword_form.jsp
				// successView.forward(req, res);

			}
			// ======================================����r�s���Ʈw����}�l�d��================================================

			String Category = req.getParameter("Category");
			if ("class_list_search".equals(Category)) {
				String class_name = req.getParameter("class_name");
				Class_infoService Srv = new Class_infoService(); // �I�sService��k
				Queue<Class_infoVO> class_infoVO = (LinkedList) Srv.getAll();
				List<Class_infoVO> returnList = null;
				List<List> Conform = null;
				ArrayList<String> list = new ArrayList<String>(Arrays.asList((class_name.toUpperCase()).split(" ")));

				list.add(0, class_name);
				for (String name : list) {
					System.out.println("�d�� :" + name);
					Conform = getConform(class_infoVO, name);
					class_infoVO = (Queue<Class_infoVO>) Conform.get(0);
					if (returnList == null) {
						returnList = Conform.get(1);
					} else {
						for (Class_infoVO item : (Queue<Class_infoVO>) Conform.get(1)) {
							returnList.add(item);
						}
					}

				}

				if (returnList.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/class_info/class_list_noitme.jsp");
					failureView.forward(req, res);
				} else {
					req.setAttribute("class_infoVO", returnList);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_info/class_list.jsp");
					failureView.forward(req, res);
					return;
				}

			} else if ("subclass".equals(Category)) {
				String subclass_id = req.getParameter("subclass_id");
				Class_infoService Srv = new Class_infoService(); // �I�sService��k
				List<Class_infoVO> class_infoVO = Srv.getAll();
				List<Class_infoVO> returnList = new ArrayList<Class_infoVO>();
				for (Class_infoVO item : class_infoVO) {
					if (item.getClass_status() == 3 || item.getClass_status() == 4 || item.getClass_status() == 2
							|| item.getClass_status() == 1) {
						if (item.getSubclass_id().equals(subclass_id)) {
							if (getSearch(item) != null) {
								returnList.add(item);
							}
						}
					}
				}
				if (returnList.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/class_info/class_list_noitme.jsp");
					failureView.forward(req, res);
				} else {
					req.setAttribute("class_infoVO", returnList);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/class_info/class_list.jsp");
					failureView.forward(req, res);
					return;
				}
			}
		}

		if ("class_pic_lg".equals(action)) {
			res.setContentType("image/jpeg"); // �]�w�^������
			String class_id = req.getParameter("class_id");
			Class_infoService Srv = new Class_infoService(); // �I�sService��k
			byte[] getClassPic = Srv.getClassPic(class_id);
			OutputStream out = res.getOutputStream(); // �إ߿�X�y
			int sm = 1000;
			try {
				if (getClassPic != null || getClassPic.length != 0) { // �P�_�ɮ׬O�_�s�b
					byte[] scaledPic = ImageUtil.shrink(getClassPic, sm);
					res.setContentLength(scaledPic.length); // �q���s�����ɮת���
					InputStream in = new ByteArrayInputStream(scaledPic); // �Nbyte[]�ഫ��InputStream
					byte[] buf = new byte[scaledPic.length]; // 4K buffer //�]�wbyte[]�j�p
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len); // �N��ƿ�X
					}
				} else {
					// �d�߭Ȥ��s�b

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

		if ("class_pic_sm".equals(action)) {
			res.setContentType("image/jpeg"); // �]�w�^������
			String class_id = req.getParameter("class_id");
			Class_infoService Srv = new Class_infoService(); // �I�sService��k
			byte[] getClassPic = Srv.getClassPic(class_id);
			OutputStream out = res.getOutputStream(); // �إ߿�X�y
			int sm = 200;
			try {
				if (getClassPic != null || getClassPic.length != 0) { // �P�_�ɮ׬O�_�s�b
					byte[] scaledPic = ImageUtil.shrink(getClassPic, sm);
					res.setContentLength(scaledPic.length); // �q���s�����ɮת���
					InputStream in = new ByteArrayInputStream(scaledPic); // �Nbyte[]�ഫ��InputStream
					byte[] buf = new byte[scaledPic.length]; // 4K buffer //�]�wbyte[]�j�p
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len); // �N��ƿ�X
					}
				} else {
					// �d�߭Ȥ��s�b

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
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected Class_infoVO getSearch(Class_infoVO class_infoVO) {
		String class_id = class_infoVO.getClass_id();
		Order_listService orderSrv = new Order_listService();
		List<Map<String, String>> people = orderSrv.getClassID(class_id);
		// �P�w�O�_�w�g���W�L�Ҹ�H��
		if (people.size() < class_infoVO.getPeople_threshold()) {
			// �p��Ҹ�H��(�Ҹꤤ) + �٦b�Ҹ�ɶ�
			if ((class_infoVO.getStartfund_date().getTime() + (long) 30 * 24 * 60 * 60 * 1000) > new java.util.Date()
					.getTime()) {
				return class_infoVO;
			}

		} else {
			// �P�w�O�_�w�g�W�L�Ҹ�H��
			int date_start = class_infoVO.getStartfund_date()
					.compareTo(new java.sql.Timestamp(new java.util.Date().getTime() + 60 * 24 * 60 * 60 * 1000));
			if ((class_infoVO.getStartfund_date().getTime() + (long) 30 * 24 * 60 * 60 * 1000) > new java.util.Date()
					.getTime()) {
				// �Ҹꤤ+�w�}��
				return class_infoVO;
			}
		}
		return class_infoVO;
	}

	protected List<List> getConform(Queue<Class_infoVO> Class_infoVO, String word) {
		//�e�X���
		List<List> list = new ArrayList<List>();
		//�z�藍�ŦX
		LinkedList<Class_infoVO> Class_info = new LinkedList<Class_infoVO>();
		//�z��ŦX(�Ĥ@���z)
		LinkedList<Class_infoVO> returnList = new LinkedList<Class_infoVO>();
		
		//�p�G��r���ť� �}�C>1
		if (word.split(" ").length > 1) {

			//�z�粒���ŦX
			LinkedList<Class_infoVO> first = new LinkedList<Class_infoVO>();
			//�����}�C����r
			for (int i = 0; i < word.split(" ").length; i++) {
				//�Ĥ@������r
				if (i == 0) {
					//�z��Ҧ��ŦX�����e
					for (Class_infoVO item : Class_infoVO) {
						//�P�_���A
						if (item.getClass_status() == 4 || item.getClass_status() == 1) {
							//�P�_��r�ŦX
							if ((item.getClass_name().toUpperCase()).contains(word.split(" ")[i].toUpperCase())) {
								//�P�_�}�Ҫ��A
								if (getSearch(item) != null) {
									//��J�Ĥ@���z�襤
									returnList.add(item);
								}
							} else {
								//��r���ŦX��J�U���z��List
								Class_info.add(item);
							}

						}
					}
				} else {
					
					////��N������r
					for (Class_infoVO classInfo : returnList) {
						//�p�G�S���Ĥ@���z�諸�}�C(�P�ɲŦX���ҵ{)
						if ((classInfo.getClass_name().toUpperCase()).contains(word.split(" ")[i].toUpperCase())) {
							first.add(classInfo);
						}else {
							//���s�b(��@�ŦX)
							Class_info.add(classInfo);							
						}
					}
				}
			}
			list.add(Class_info);
			list.add(first);
			return list;
		} else {

			for (Class_infoVO item : Class_infoVO) {
				if (item.getClass_status() == 4 || item.getClass_status() == 1) {
					if ((item.getClass_name().toUpperCase()).contains(word)) {
						if (getSearch(item) != null) {
							returnList.add(item);
						}
					} else {
						Class_info.add(item);
					}
				}
			}
			list.add(Class_info);
			list.add(returnList);
			return list;
		}
	}
}
