package com.filters;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.class_info.model.Class_infoService;
import com.class_info.model.Class_infoVO;
import com.member_info.model.Member_infoService;
import com.member_info.model.Member_infoVO;
import com.order_info.model.Order_infoService;
import com.order_list.model.Order_listService;
import com.order_list.model.Order_listVO;

public class student_filter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("進入氯氣");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String action = (String) request.getParameter("action");
		String class_id = (String) req.getParameter("class_id");

		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		if (session.getAttribute("judgement") == null) {
			Class_infoService Srv = new Class_infoService(); // 呼叫Service方法
			Class_infoVO class_infoVO = Srv.getOneClass_info(class_id); // 利用id得到物件
			req.setAttribute("class_infoVO", class_infoVO);
			RequestDispatcher failureView = req
					.getRequestDispatcher("/Class_info/VisitorsClassServlet?action=class_Introduction"); // classOnly.jsp
			failureView.forward(req, res);
			return;
		} else {
			Map<String, String> map = (Map<String, String>) session.getAttribute("judgement");
			// 符合學生身分
			if ("0".equals(map.get("role"))) {
				System.out.println("符合學生身分");
				Member_infoVO member_infoVO = (Member_infoVO) session.getAttribute("member_infoVO");
				if ("class_Introduction".equals(action)) {
					Class_infoService Srv = new Class_infoService(); // 呼叫Service方法
					Class_infoVO class_infoVO = Srv.getOneClass_info(class_id); // 利用id得到物件
					if (class_infoVO != null) { // 判斷檔案是否存在
						List<Order_listVO> list = (List) session.getAttribute("member_class");
						for (Order_listVO item : list) {
							if (item.getClass_id().equals(class_id)) {
								System.out.println("有購買課程");
								req.setAttribute("class_infoVO", class_infoVO);
								System.out.println(req.getRequestURL());
								chain.doFilter(request, response);
								return;
							}
						}
						System.out.println("沒有購買課程");
						req.setAttribute("class_infoVO", class_infoVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Class_info/VisitorsClassServlet?action=class_Introduction"); // classOnly.jsp
						failureView.forward(req, res);
						return;
					}
				}

			} else if ("1".equals(map.get("role"))) {
				System.out.println("符合老師身分");
				// 符合老師身分
				Member_infoVO member_infoVO = (Member_infoVO) session.getAttribute("member_infoVO");
				if ("class_Introduction".equals(action)) {
					Class_infoService Srv = new Class_infoService(); // 呼叫Service方法
					Class_infoVO class_infoVO = Srv.getOneClass_info(class_id); // 利用id得到物件
					if (class_infoVO != null) { // 判斷檔案是否存在
						// 有學
						List<Order_listVO> member_class = (List) session.getAttribute("member_class");
						for (Order_listVO item : member_class) {
							if (item.getClass_id().equals(class_id)) {
								System.out.println("//有學");
								req.setAttribute("class_infoVO", class_infoVO);
								chain.doFilter(request, response);
								return;
							}
						}
						// 有開課
						List<Class_infoVO> member_teach = (List) session.getAttribute("member_teach");
						for (Class_infoVO item : member_teach) {
							if (item.getClass_id().equals(class_id)) {
								System.out.println("//有開課");
								req.setAttribute("class_infoVO", class_infoVO);
								chain.doFilter(request, response);
								return;
							}
						}
						System.out.println("//都沒有");
						req.setAttribute("class_infoVO", class_infoVO);
						RequestDispatcher failureView = req
								.getRequestDispatcher("/Class_info/VisitorsClassServlet?action=class_Introduction"); // classOnly.jsp
						failureView.forward(req, res);
						return;

					}
				}
			}
		}
	}
}
