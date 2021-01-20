package com.class_info.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.class_info.model.*;


@WebServlet("/class_info/Class_infoImgAndVideo")
@MultipartConfig(maxFileSize =10*1024*1024,fileSizeThreshold = 1024*1024 , maxRequestSize = 5*5*1024*1024)
public class Class_infoImgAndVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

		//�ն���@-���Ϥ�
		  if("class_pic".equals(action)){
    	   	res.setContentType("image/jpeg"); // �]�w�^������
    	   	String class_id = req.getParameter("class_id");
			Class_infoService Srv = new Class_infoService(); // �I�sService��k
			Class_infoVO class_unitVO = Srv.getOneClass_info(class_id); // �Q��id�o�쪫��
			OutputStream out = res.getOutputStream(); // �إ߿�X�y
			try {
				if (class_unitVO.getClass_picture()!= null || class_unitVO.getClass_picture().length != 0) { // �P�_�ɮ׬O�_�s�b
					res.setContentLength(class_unitVO.getClass_picture().length); // �q���s�����ɮת���
					InputStream in = new ByteArrayInputStream(class_unitVO.getClass_picture()); // �Nbyte[]�ഫ��InputStream
					byte[] buf = new byte[class_unitVO.getClass_picture().length]; // 4K buffer //�]�wbyte[]�j�p
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
			//�ն���@-���v��
		  if("class_video".equals(action)){
    	   	res.setContentType("video/mp4"); // �]�w�^������
    	   	String class_id = req.getParameter("class_id");
    	   	res.setHeader("Accept-Ranges", "bytes");
			Class_infoService Srv = new Class_infoService(); // �I�sService��k
			byte[] getClassVideo = Srv.getClassVideo(class_id); // �Q��id�o�쪫��
			OutputStream out = res.getOutputStream(); // �إ߿�X�y
			try {
				if (getClassVideo != null || getClassVideo.length != 0) { // �P�_�ɮ׬O�_�s�b
					res.setContentLength(getClassVideo.length); // �q���s�����ɮת���
					InputStream in = new ByteArrayInputStream(getClassVideo); // �Nbyte[]�ഫ��InputStream
					byte[] buf = new byte[getClassVideo.length]; // 4K buffer //�]�wbyte[]�j�p
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len); // �N��ƿ�X
					}
				} else {
					// �d�߭Ȥ��s�b
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);    //��X���~�T��
					InputStream in = getServletContext().getResourceAsStream("/img/NoResult/NoResult.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
			} catch (Exception e) {
				// �S����J�d�߭�
				InputStream in = getServletContext().getResourceAsStream("/img/NoResult/NoResult.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			} finally {
				out.close();
			}
    	}	  
		
		
		
	}


}
