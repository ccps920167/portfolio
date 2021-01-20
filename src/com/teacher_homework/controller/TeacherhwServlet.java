package com.teacher_homework.controller;

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
import javax.servlet.http.Part;

import com.teacher_homework.model.Teacher_homeworkService;
import com.teacher_homework.model.Teacher_homeworkVO;

@MultipartConfig
@WebServlet("/TeacherhwServlet")
public class TeacherhwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {
			getOne_For_Display(req, res);
		}
		if ("getOne_For_Update".equals(action)) {
			getOne_For_Update(req, res);
		}
		if ("update".equals(action)) {
			update(req, res);
		}
		if ("insert".equals(action)) {
			insert(req, res);
		}
		if ("delete".equals(action)) {
			delete(req, res);
		}
		if("display_pic".equals(action)) {
			display_pic(req, res);
		}
	}
	
	public void getOne_For_Display(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		try {
//			show error when teacherhw_id is empty
			String teacherhw_id = req.getParameter("teacherhw_id");
			String tridReg = "^(TH)(\\d){5}$";
			if (teacherhw_id == null || teacherhw_id.trim().length() == 0) {
				errorMsgs.add("作業編號不能空白");
			} else if(!teacherhw_id.trim().matches(tridReg)) {
				errorMsgs.add("作業編號格式錯誤 ex:TH00001");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw/teacherHw/hwQuestion.jsp");
				failureView.forward(req, res);
				return;
			}
//			catch data form database
			Teacher_homeworkService service = new Teacher_homeworkService();
			Teacher_homeworkVO vo = service.select(teacherhw_id);
			if (vo == null) {
				errorMsgs.add("found no data");
			}
//			Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw/teacherHw/hwQuestion.jsp");
				failureView.forward(req, res);
				return;//process interrupt
			}
//			forword to url
			req.setAttribute("teacher_homeworkVO", vo);
			String url = "/front-end/hw/teacherHw/listOneTrHW.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/****************************************************************/
		} catch (Exception e) {
//			show error at current page
			errorMsgs.add("that is something go wrong:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw/teacherHw/hwQuestion.jsp");
			failureView.forward(req, res);
		}
	}
	
	public void getOne_For_Update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		try {
			String teacherhw_id = req.getParameter("teacherhw_id");

			Teacher_homeworkService service = new Teacher_homeworkService();
			Teacher_homeworkVO vo = service.select(teacherhw_id);

			req.setAttribute("teacher_homeworkVO", vo);
			String url = "/front-end/hw/teacherHw/updateTrHW.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		} catch (Exception e) {
//			show error at listAll page
			errorMsgs.add("that is something go wrong:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw/teacherHw/listAllTrHW.jsp");
			failureView.forward(req, res);
		}
	}
	
	public void update(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
	
		try {
			Teacher_homeworkService service = new Teacher_homeworkService();
//			getParameter form listAll or listOne
//			teacherhw_id
			String teacherhw_id = req.getParameter("teacherhw_id").trim();
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
				errorMsgs.add("hw_name can not be empty");
			}
//			hw_content
			String hw_content = req.getParameter("hw_content").trim();
			if (hw_content == null || hw_content.trim().length() == 0) {
				errorMsgs.add("hw_content can not be empty");
			}
//			file_data
			byte[] file_data = service.select(teacherhw_id).getFile_data();
			Part part = req.getPart("file_data");
			InputStream input = part.getInputStream();
//			有選擇檔案
			if(part.getSize() > 0) {
				file_data = new byte[input.available()];
				input.read(file_data);
//			沒選擇檔案&原本檔案不為空
			}else if(part.getSize() == 0 && file_data != null){
				System.out.println("沒選檔案");
				input.read(file_data);
				System.out.println("part  == null");
			}
			input.close();	
			
//			uploadtime
			String uploadtime = req.getParameter("hw_uploadtime").trim();
			if (uploadtime == null || uploadtime.trim().length() == 0) {
				errorMsgs.add("uploadtime can not be empty");
			}	
			Timestamp hw_uploadtime = Timestamp.valueOf(uploadtime);
//			updatetime
			String updatetime = req.getParameter("hw_updatetime").trim();
			Timestamp hw_updatetime = Timestamp.valueOf(updatetime);
			
//			setValue into VO
			Teacher_homeworkVO teacher_homeworkVO = new Teacher_homeworkVO();
			teacher_homeworkVO.setTeacherhw_id(teacherhw_id);
			teacher_homeworkVO.setUnit_id(unit_id);
			teacher_homeworkVO.setHw_name(hw_name);
			teacher_homeworkVO.setHw_content(hw_content);
			teacher_homeworkVO.setFile_data(file_data);
			teacher_homeworkVO.setHw_uploadtime(hw_uploadtime);
			teacher_homeworkVO.setHw_updatetime(hw_updatetime);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("teacher_homeworkVO", teacher_homeworkVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw/teacherHw/updateTrHW.jsp");
				failureView.forward(req, res);
				return;
			}	
//			update database info if success
			teacher_homeworkVO = service.update(teacherhw_id,unit_id,hw_name,hw_content,file_data,hw_uploadtime);
//			show result in listOne page
			req.setAttribute("teacher_homeworkVO", teacher_homeworkVO);
			String url = "/front-end/hw/teacherHw/listOneTrHW.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		} catch (Exception e) {
//			show error at update page
			errorMsgs.add("update failed:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw/teacherHw/updateTrHW.jsp");
			failureView.forward(req, res);
		}
	}
	
	public void insert(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
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
				errorMsgs.add("hw_name can not be empty");
			}
//			hw_content
			String hw_content = req.getParameter("hw_content").trim();
			if (hw_content == null || hw_content.trim().length() == 0) {
				errorMsgs.add("hw_content can not be empty");
			}
//			file_data
			byte[] file_data = null;
			Part part = req.getPart("file_data");
			InputStream input = part.getInputStream();
			file_data = new byte[input.available()];
			input.read(file_data);
			input.close();
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
				req.setAttribute("teacher_homeworkVO", teacher_homeworkVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw/teacherHw/makeHomework.jsp");
				failureView.forward(req, res);
				return;
			}	
			Teacher_homeworkService service = new Teacher_homeworkService();
			teacher_homeworkVO = service.insert(unit_id,hw_name,hw_content,file_data);

			String url = "/front-end/hw/teacherHw/listAllTrHW.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);				
//			上述以外的例外
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/hw/teacherHw/makeHomework.jsp");
			failureView.forward(req, res);
		}
	}
	
	public void delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		create errorMassage & set into req
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		try {
			String teacherhw_id = req.getParameter("teacherhw_id");
			
			Teacher_homeworkService service = new Teacher_homeworkService();
			service.delete(teacherhw_id);
									
			String url = "/front-end/hw/teacherHw/listAllTrHW.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		} catch (Exception e) {
			errorMsgs.add("delete failed:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/hw/teacherHw/listAllTrHW.jsp");
			failureView.forward(req, res);
		}
	}
	
	public void display_pic(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Teacher_homeworkService service = new Teacher_homeworkService();
		String teacherhw_id = req.getParameter("teacherhw_id");
		Teacher_homeworkVO vo = service.select(teacherhw_id);
		byte[] pic = vo.getFile_data();
		if(pic != null) {				
			OutputStream output = res.getOutputStream();
			output.write(pic);
			output.close();
		}
	}
}
