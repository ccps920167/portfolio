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
import com.member_info.model.Member_infoVO;
import com.order_list.model.Order_listVO;

public class allmember_filter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// �i���o session�j
		HttpSession session = req.getSession();
		// �i�q session �P�_��user�O�_�n�J�L�j
		if (session.getAttribute("judgement") == null) {
			session.setAttribute("location", req.getRequestURI()+"?"+req.getQueryString());
			System.out.println("allmember_filter" + session.getAttribute("location"));
			res.sendRedirect(req.getContextPath() + "/front-end/sign_in/sign_in.jsp");
			return;
		} else {
			Map<String, String> map = (Map<String, String>) session.getAttribute("judgement");
			// �ŦX�|������ 
			if ("0".equals(map.get("role")) || "1".equals(map.get("role"))) {
				Member_infoVO member_infoVO = (Member_infoVO) session.getAttribute("member_infoVO");
				if (member_infoVO != null) { // �P�_�ɮ׬O�_�s�b
					chain.doFilter(request, response);
					return;
				}
			} else {
				String location = (String) session.getAttribute("location");
				res.sendRedirect(location);
				System.out.println(location);
			}
		}
	}
}
