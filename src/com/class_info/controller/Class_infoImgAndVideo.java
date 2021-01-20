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
		

		//博雅實作-取圖片
		  if("class_pic".equals(action)){
    	   	res.setContentType("image/jpeg"); // 設定回應類型
    	   	String class_id = req.getParameter("class_id");
			Class_infoService Srv = new Class_infoService(); // 呼叫Service方法
			Class_infoVO class_unitVO = Srv.getOneClass_info(class_id); // 利用id得到物件
			OutputStream out = res.getOutputStream(); // 建立輸出流
			try {
				if (class_unitVO.getClass_picture()!= null || class_unitVO.getClass_picture().length != 0) { // 判斷檔案是否存在
					res.setContentLength(class_unitVO.getClass_picture().length); // 通知瀏覽器檔案長度
					InputStream in = new ByteArrayInputStream(class_unitVO.getClass_picture()); // 將byte[]轉換成InputStream
					byte[] buf = new byte[class_unitVO.getClass_picture().length]; // 4K buffer //設定byte[]大小
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len); // 將資料輸出
					}
				} else {
					// 查詢值不存在
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);    //輸出錯誤訊息
					InputStream in = getServletContext().getResourceAsStream("/img/NoResult/unnamed.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
			} catch (Exception e) {
				// 沒有輸入查詢值
				InputStream in = getServletContext().getResourceAsStream("/img/NoResult/unnamed.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			} finally {
				out.close();
			}
    	}
			//博雅實作-取影片
		  if("class_video".equals(action)){
    	   	res.setContentType("video/mp4"); // 設定回應類型
    	   	String class_id = req.getParameter("class_id");
    	   	res.setHeader("Accept-Ranges", "bytes");
			Class_infoService Srv = new Class_infoService(); // 呼叫Service方法
			byte[] getClassVideo = Srv.getClassVideo(class_id); // 利用id得到物件
			OutputStream out = res.getOutputStream(); // 建立輸出流
			try {
				if (getClassVideo != null || getClassVideo.length != 0) { // 判斷檔案是否存在
					res.setContentLength(getClassVideo.length); // 通知瀏覽器檔案長度
					InputStream in = new ByteArrayInputStream(getClassVideo); // 將byte[]轉換成InputStream
					byte[] buf = new byte[getClassVideo.length]; // 4K buffer //設定byte[]大小
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len); // 將資料輸出
					}
				} else {
					// 查詢值不存在
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);    //輸出錯誤訊息
					InputStream in = getServletContext().getResourceAsStream("/img/NoResult/NoResult.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
			} catch (Exception e) {
				// 沒有輸入查詢值
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
