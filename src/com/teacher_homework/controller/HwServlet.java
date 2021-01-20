package com.teacher_homework.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.teacher_homework.model.Teacher_homeworkService;
import com.teacher_homework.model.Teacher_homeworkVO;

/**
 * Servlet implementation class HwServlet
 */
@WebServlet("/HwServlet")
@MultipartConfig(fileSizeThreshold = 1024*1024,maxFileSize = 5*1024*1024,maxRequestSize = 5*1024*1024)
public class HwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Teacher_homeworkService service = new Teacher_homeworkService();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		session.removeAttribute("teacher_homeworkVOError");
		session.removeAttribute("teacher_homeworkOK");

		if ("update".equals(action)) {
			update(req, res);
		}
		if ("insert".equals(action)) {
			insert(req, res);
		}
		if("selectHomework".equals(action)) {
			selectHomework(req, res);
		}
		if("display_pic".equals(action)) {
			display_pic(req, res);
		}
	}
	
	public void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HttpSession session = req.getSession();
		session.removeAttribute("teacher_homeworkVOError");	
		String url = req.getHeader("Referer");
		String[] urlByte = url.split("G5");
		try {
//			getParameter form makeHomework
//			teacherhw_id create by SEQ
			
//			unit_id
			String unit_id = req.getParameter("unit_id").trim();
			String unitReg = "^(UNI)(\\d){5}$";
			if (unit_id == null || unit_id.trim().length() == 0) {
				errorMsgs.add("unit id can not be empty");
			} else if(!unit_id.trim().matches(unitReg)) {
				errorMsgs.add("unit id only can be 'UNI + five number' ex:UNI00001");
			}
//			hw_name
			String hw_name = req.getParameter("hw_name").trim();
			if (hw_name == null || hw_name.trim().length() == 0) {
				errorMsgs.add("請填寫作業名稱");
			}else if(hw_name.length()>60) {
				errorMsgs.add("作業名稱不能超過20個字喔");
			}
//			hw_content
			String hw_content = req.getParameter("hw_content").trim();
			if (hw_content == null || hw_content.trim().length() == 0) {
				errorMsgs.add("請填寫作業內容");
			}else if(hw_content.length()>666) {
				errorMsgs.add("作業內容不能超過666個字喔");
			}
			
//			file_data
			byte[] file_data = null;
			Part part = req.getPart("file_data");
			InputStream input = null;
			if(part != null) {
				input = part.getInputStream();
				file_data = new byte[input.available()];
				input.read(file_data);
				input.close();
			}else if(part == null){
				//沒有圖片就沒有圖片
			}
//			uploadtime is default system currenttime
			
//			updatetime is default system currenttime

//			setValue into VO
			Teacher_homeworkVO teacher_homeworkVO = new Teacher_homeworkVO();
			teacher_homeworkVO.setUnit_id(unit_id);
			teacher_homeworkVO.setHw_name(hw_name);
			teacher_homeworkVO.setHw_content(hw_content);
			teacher_homeworkVO.setFile_data(file_data);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				session.setAttribute("teacher_homeworkVOError", errorMsgs);
				res.sendRedirect(req.getContextPath()+urlByte[1]);	
				return;
			}
			teacher_homeworkVO = service.insert(unit_id,hw_name,hw_content,file_data);
			session.setAttribute("teacher_homeworkOK", "OK");
			res.sendRedirect(req.getContextPath()+urlByte[1]);				
//			上述以外的例外
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			session.setAttribute("teacher_homeworkVOError", errorMsgs);
			res.sendRedirect(req.getContextPath()+urlByte[1]);	
		}
	}
	
	public void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HttpSession session = req.getSession();
		session.removeAttribute("teacher_homeworkVOError");	
		String url = req.getHeader("Referer");
		String[] urlByte = url.split("G5");
		try {
//teacherhw_id
			String teacherhw_id = req.getParameter("teacherhw_id").trim();
			Teacher_homeworkVO vo = service.select(teacherhw_id);
//unit_id不變
			String unit_id = vo.getUnit_id(); 
//hw_name
			String hw_name = req.getParameter("hw_name").trim();
			if (hw_name == null || hw_name.trim().length() == 0) {
				errorMsgs.add("請填寫作業名稱");
			}else if(hw_name.length()>60) {
				errorMsgs.add("作業名稱不能超過20個字喔");
			}
//hw_content
			String hw_content = req.getParameter("hw_content").trim();
			if (hw_content == null || hw_content.trim().length() == 0) {
				errorMsgs.add("請填寫作業內容");
			}else if(hw_content.length()>666) {
				errorMsgs.add("作業內容不能超過666個字喔");
			}
//			file_data
			byte[] file_data = null;
			Part part = req.getPart("file_data");
			InputStream input = null;
			if(part != null) {
				input = part.getInputStream();
				file_data = new byte[input.available()];
				input.read(file_data);
				input.close();
			}else if(part == null){
				//沒有圖片就沒有圖片
			}
//uploadtime不變
			Timestamp hw_uploadtime = vo.getHw_uploadtime();
			
//updatetime不變
			Timestamp hw_updatetime = vo.getHw_updatetime();
			
//			setValue into VO
			vo.setHw_name(hw_name);
			vo.setHw_content(hw_content);
			vo.setFile_data(file_data);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("teacher_homeworkVO", vo);
				session.setAttribute("teacher_homeworkVOError", errorMsgs);
				res.sendRedirect(req.getContextPath()+urlByte[1]);	
				return;
			}	
//更新資料庫
			vo = service.update(teacherhw_id,unit_id,hw_name,hw_content,file_data,hw_uploadtime);
//跳到作業問題.jsp
			req.setAttribute("teacher_homeworkVO", vo);
			session.setAttribute("teacher_homeworkOK", "OK");
			res.sendRedirect(req.getContextPath()+urlByte[1]);	
		} catch (Exception e) {
//			show error at update page
			errorMsgs.add("getParameter時，發生問題:" + e.getMessage());
			session.setAttribute("teacher_homeworkVOError", errorMsgs);
			res.sendRedirect(req.getContextPath()+urlByte[1]);	
		}
	}
	
	public void selectHomework(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HttpSession session = req.getSession();
		session.removeAttribute("teacher_homeworkVOError");	
		String url = req.getHeader("Referer");
		String[] urlByte = url.split("G5");
		try {
			String teacherhw_id = req.getParameter("teacherhw_id");
			
			req.getSession().setAttribute("trVO", service.select(teacherhw_id));
			session.setAttribute("teacher_homeworkOK", "OK");
			res.sendRedirect(req.getContextPath()+urlByte[1]);	
			
		} catch (Exception e) {
//			show error at update page
			errorMsgs.add("getParameter時，發生問題:" + e.getMessage());
			session.setAttribute("teacher_homeworkVOError", errorMsgs);
			res.sendRedirect(req.getContextPath()+urlByte[1]);	
		}
	}
	
	public void display_pic(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String teacherhw_id = req.getParameter("teacherhw_id");
		Teacher_homeworkVO vo = service.select(teacherhw_id);
		byte[] pic = vo.getFile_data();
		if(pic != null) {				
			OutputStream output = res.getOutputStream();
			output.write(pic);
			output.close();
		}
	}
	
	public void include_path(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		HttpSession session = req.getSession();
		session.removeAttribute("teacher_homeworkVOError");	
		String url = req.getHeader("Referer");
		String[] urlByte = url.split("G5");
		
		try {
			String include_path = req.getParameter("include_path");
			req.getSession().setAttribute("include_path",include_path);
			session.setAttribute("teacher_homeworkOK", "OK");
			res.sendRedirect(req.getContextPath()+urlByte[1]);		
		} catch (Exception e) {
//			show error at update page
			errorMsgs.add("getParameter時，發生問題:" + e.getMessage());
			session.setAttribute("teacher_homeworkVOError", errorMsgs);
			res.sendRedirect(req.getContextPath()+urlByte[1]);	
		}
	}
}

	



