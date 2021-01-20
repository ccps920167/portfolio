package com.student_homework.controller;

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

import com.student_homework.model.Student_homeworkService;
import com.student_homework.model.Student_homeworkVO;

@WebServlet("/studenthwServlet")
@MultipartConfig
public class studenthwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Student_homeworkService student_homeworkService = new Student_homeworkService();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************************************/
				String str = req.getParameter("studenthw_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("please enter student id");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw_test/studenthw.jsp");
					failureView.forward(req, res);
					return;
				}

				String studenthw_id = null;
				try {
					studenthw_id = str;
				} catch (Exception e) {
					errorMsgs.add("wrong student id format");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw_test/studenthw.jsp");
					failureView.forward(req, res);
					return;// process interrupt
				}

				/********************************************************************/
				Student_homeworkVO student_homeworkVO = student_homeworkService.select(studenthw_id);
				if (student_homeworkVO == null) {
					errorMsgs.add("found nno data");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw_test/studenthw.jsp");
					failureView.forward(req, res);
					return;// process interrupt
				}

				/*************************** (Send the Success view) *************/
				req.setAttribute("student_homeworkVO", student_homeworkVO);
				String url = "/front-end/hw_test/listOneStHW.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/****************************************************************/
			} catch (Exception e) {
				errorMsgs.add("can not catch data:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw_test/studenthw.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*******************************************************************/
				String studenthw_id = req.getParameter("studenthw_id");

				/*******************************************************************/
				Student_homeworkVO vo = student_homeworkService.select(studenthw_id);

				/*************************** (Send the Success view) ************/
				req.setAttribute("student_homeworkVO", vo);
				String url = "/front-end/hw_test/updateStHW.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add("can not catch data that prepare to fixed:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw_test/listAllStHW.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String studenthw_id = req.getParameter("studenthw_id").trim();
				String teacherhw_id = req.getParameter("teacherhw_id").trim();
				String member_id = req.getParameter("member_id").trim();
				byte[] file_data = student_homeworkService.select(studenthw_id).getFile_data();
				Part part = req.getPart("file_data");
				InputStream input = part.getInputStream();

				if (part.getSize() > 0) {
					file_data = new byte[input.available()];
					input.read(file_data);
				} else if (part.getSize() == 0 && file_data != null) {
					input.read(file_data);
				}
				input.close();

				String teacherhwReg = "^(TH)(\\d){5}$";
				if (teacherhw_id == null || teacherhw_id.trim().length() == 0) {
					errorMsgs.add("teacher id can not be empty");
				} else if (!teacherhw_id.trim().matches(teacherhwReg)) {
					errorMsgs.add("teacher id only can be 'TH + five number' ex:TH00001");
				}

				String memberReg = "^(MEM)(\\d){5}$";
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("member id can not be empty");
				} else if (!member_id.trim().matches(memberReg)) {
					errorMsgs.add("member id only can be 'MEM + five number' ex:MEM00001");
				}

				Timestamp hw_uploadtime = null;
				String uploadtime = req.getParameter("hw_uploadtime").trim();
				String uploadtimeReg = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d$";
				if (uploadtime == null || uploadtime.trim().length() == 0) {
					hw_uploadtime = student_homeworkService.select(studenthw_id).getHw_uploadtime();
					errorMsgs.add("uploadtime can not be empty");
				} else if (!uploadtime.trim().matches(uploadtimeReg)) {
					hw_uploadtime = student_homeworkService.select(studenthw_id).getHw_uploadtime();
					errorMsgs.add("format error: yyyy-mm-dd 24h:mm:ss");
				} else {
					hw_uploadtime = Timestamp.valueOf(uploadtime);
				}

				String updatetime = req.getParameter("hw_updatetime").trim();
				if (updatetime == null || updatetime.trim().length() == 0) {
					errorMsgs.add("updatetime can not be empty");
				}
				Timestamp hw_updatetime = Timestamp.valueOf(updatetime);

				Student_homeworkVO student_homeworkVO = new Student_homeworkVO();
				student_homeworkVO.setStudenthw_id(studenthw_id);
				student_homeworkVO.setTeacherhw_id(teacherhw_id);
				student_homeworkVO.setMember_id(member_id);
				student_homeworkVO.setFile_data(file_data);
				student_homeworkVO.setHw_uploadtime(hw_uploadtime);
				student_homeworkVO.setHw_updatetime(hw_updatetime);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("student_homeworkVO", student_homeworkVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw_test/updateStHW.jsp");
					failureView.forward(req, res);
					return;
				}
//				update info to database
//				student_homeworkVO = student_homeworkService.update(studenthw_id,teacherhw_id,member_id,file_data,hw_uploadtime);

				/*************************** (Send the Success view) *************/
				req.setAttribute("student_homeworkVO", student_homeworkVO);
				String url = "/front-end/hw_test/listOneStHW.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/****************************************************************/
			} catch (Exception e) {
				errorMsgs.add("update failed:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw_test/updateStHW.jsp");
				failureView.forward(req, res);
			}
		}

		if ("display_pic".equals(action)) {
			OutputStream out = res.getOutputStream();
			try {
				String studenthw_id = req.getParameter("studenthw_id");
				Student_homeworkVO vo = student_homeworkService.select(studenthw_id);
				byte[] pic = vo.getFile_data();
				if (pic != null) {
					out.write(pic);
					out.close();
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

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/************************************************/
				String teacherhw_id = req.getParameter("teacherhw_id").trim();
				String member_id = req.getParameter("member_id").trim();
				byte[] file_data = null;
				Part part = req.getPart("file_data");
				InputStream input = part.getInputStream();
				file_data = new byte[input.available()];
				input.read(file_data);
				input.close();

				String teacherhwReg = "^(TH)(\\d){5}$";
				if (teacherhw_id == null || teacherhw_id.trim().length() == 0) {
					errorMsgs.add("teacher id can not be empty");
				} else if (!teacherhw_id.trim().matches(teacherhwReg)) {
					errorMsgs.add("teacher id only can be 'TH + five number' ex:TH00001");
				}

				String memberReg = "^(MEM)(\\d){5}$";
				if (member_id == null || member_id.trim().length() == 0) {
					errorMsgs.add("member id: can not be empty");
				} else if (!member_id.trim().matches(memberReg)) {
					errorMsgs.add("member id only can be 'MEM + five number' ex:MEM00001");
				}

				Student_homeworkVO student_homeworkVO = new Student_homeworkVO();

				student_homeworkVO.setTeacherhw_id(teacherhw_id);
				student_homeworkVO.setMember_id(member_id);
				student_homeworkVO.setFile_data(file_data);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("student_homeworkVO", student_homeworkVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw_test/doHomework.jsp");
					failureView.forward(req, res);
					return;
				}

				/******************************************************************/
//				student_homeworkVO = student_homeworkService.insert(teacherhw_id,member_id,file_data);

				/*************************** (Send the Success view) ***********/
				String url = "/front-end/hw_test/listAllStHW.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw_test/doHomework.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/******************************************************************/
				String studenthw_id = req.getParameter("studenthw_id");

				/******************************************************************/
				student_homeworkService.delete(studenthw_id);

				/*************************** (Send the Success view) ***********/
				String url = "/front-end/hw_test/listAllStHW.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************************************************/
			} catch (Exception e) {
				errorMsgs.add("delete failed:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/hw_test/listAllStHW.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
