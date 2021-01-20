package com.student_homework.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.student_homework.model.Student_homeworkService;
import com.student_homework.model.Student_homeworkVO;

@WebServlet("/StHwServlet")
@MultipartConfig
public class StHwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Student_homeworkService service = new Student_homeworkService();
	


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String url = req.getHeader("Referer");
		String[] urlByte = url.split("G5");
		HttpSession session = req.getSession();
		session.removeAttribute("student_homeworkVOError");
		session.removeAttribute("student_homeworkOK");

		if ("update".equals(action)) {
			update(req, res);
		}
		if ("insert".equals(action)) {
			insert(req, res);
		}
		if("display_pic".equals(action)) {
			display_pic(req, res);
		}
	}
	
	public void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		String url = req.getHeader("Referer");
		String[] urlByte = url.split("G5");
		HttpSession session = req.getSession();
		session.removeAttribute("student_homeworkVOError");		

		try {
			/************************************************/
			String teacherhw_id = req.getParameter("teacherhw_id").trim();
			String member_id = req.getParameter("member_id").trim();
			String hw_name = req.getParameter("hw_name").trim();
			if(hw_name.length()==0) {
				errorMsgs.add("請輸入標題");
			}else if(hw_name.getBytes("UTF-8").length>60){
				errorMsgs.add("標題文字不能超過20個字");
			}

			String hw_content = req.getParameter("hw_content").trim();
			if(hw_content.length()==0) {
				errorMsgs.add("請輸入標題");
			}else if(hw_content.getBytes("UTF-8").length>60){
				errorMsgs.add("內容文字不能超過600個字");
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
			
			String teacherhwReg = "^(TH)(\\d){5}$";
			if (teacherhw_id == null || teacherhw_id.trim().length() == 0) {
				errorMsgs.add("teacher id can not be empty");
			} else if(!teacherhw_id.trim().matches(teacherhwReg)) {
				errorMsgs.add("teacher id only can be 'TH + five number' ex:TH00001");
            }

			String memberReg = "^(MEM)(\\d){5}$";
			if (member_id == null || member_id.trim().length() == 0) {
				errorMsgs.add("member id: can not be empty");
			} else if(!member_id.trim().matches(memberReg)) {
				errorMsgs.add("member id only can be 'MEM + five number' ex:MEM00001");
            }

			Student_homeworkVO student_homeworkVO = new Student_homeworkVO();
			
			student_homeworkVO.setTeacherhw_id(teacherhw_id);
			student_homeworkVO.setMember_id(member_id);
			student_homeworkVO.setHw_name(hw_name);
			student_homeworkVO.setHw_content(hw_content);
			student_homeworkVO.setFile_data(file_data);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				session.setAttribute("student_homeworkVOError", errorMsgs);
				res.sendRedirect(req.getContextPath()+urlByte[1]);
				return;
			}
			
			/******************************************************************/
			student_homeworkVO = service.insert(teacherhw_id,member_id,hw_name,hw_content,file_data);
			/***************************(Send the Success view)***********/
			session.setAttribute("student_homeworkOK", "OK");
			res.sendRedirect(req.getContextPath()+urlByte[1]);			
			/*************************************************************/
		} catch (Exception e) {
			errorMsgs.add("update failed:"+e.getMessage());
			session.setAttribute("student_homeworkVOError", errorMsgs);
			res.sendRedirect(req.getContextPath()+urlByte[1]);		
		}
	}
	
	public void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		String url = req.getHeader("Referer");
		String[] urlByte = url.split("G5");
		HttpSession session = req.getSession();
		session.removeAttribute("student_homeworkVOError");
		try {
			
			String studenthw_id = req.getParameter("studenthw_id").trim();

			Student_homeworkVO vo = service.select(studenthw_id);
			
			String teacherhw_id = req.getParameter("teacherhw_id").trim();
			
			String member_id = req.getParameter("member_id").trim();
			
			String hw_name = req.getParameter("hw_name").trim();
			if(hw_name.length()==0) {
				errorMsgs.add("請輸入標題");
			}else if(hw_name.getBytes("UTF-8").length>60){
				errorMsgs.add("標題文字不能超過20個字");
			}
			
			String hw_content = req.getParameter("hw_content").trim();
			if(hw_content.length()==0) {
				errorMsgs.add("請輸入標題");
			}else if(hw_content.getBytes("UTF-8").length>60){
				errorMsgs.add("內容文字不能超過600個字");
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
			
			String teacherhwReg = "^(TH)(\\d){5}$";
			if (teacherhw_id == null || teacherhw_id.trim().length() == 0) {
				errorMsgs.add("teacher id can not be empty");
			} else if(!teacherhw_id.trim().matches(teacherhwReg)) {
				errorMsgs.add("teacher id only can be 'TH + five number' ex:TH00001");
            }
			
			String memberReg = "^(MEM)(\\d){5}$";
			if (member_id == null || member_id.trim().length() == 0) {
				errorMsgs.add("member id can not be empty");
			} else if(!member_id.trim().matches(memberReg)) {
				errorMsgs.add("member id only can be 'MEM + five number' ex:MEM00001");
            }
			
			Timestamp hw_uploadtime = new Timestamp(new Date().getTime());
			Timestamp hw_updatetime = new Timestamp(new Date().getTime());

			vo.setStudenthw_id(studenthw_id);
			vo.setTeacherhw_id(teacherhw_id);
			vo.setMember_id(member_id);
			vo.setHw_name(hw_name);
			vo.setHw_content(hw_content);
			vo.setFile_data(file_data);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				session.setAttribute("student_homeworkVOError", errorMsgs);
				res.sendRedirect(req.getContextPath()+urlByte[1]);
				return;
			}	
//			update info to database
			vo = service.update(studenthw_id,teacherhw_id,member_id,hw_name,hw_content,file_data,hw_uploadtime);
			/***************************(Send the Success view)*************/
			session.setAttribute("student_homeworkOK", "OK");
			res.sendRedirect(req.getContextPath()+urlByte[1]);

			/****************************************************************/
		} catch (Exception e) {
			errorMsgs.add("update failed:"+e.getMessage());
			session.setAttribute("student_homeworkVOError", errorMsgs);
			res.sendRedirect(req.getContextPath()+urlByte[1]);
		}
	}
	
	public void display_pic(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String studenthw_id = req.getParameter("studenthw_id");
		Student_homeworkVO vo = service.select(studenthw_id);
		byte[] pic = vo.getFile_data();
		if(pic != null) {				
			OutputStream output = res.getOutputStream();
			output.write(pic);
			output.close();
		}
	}
}
