package com.filters;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin_auth.model.Admin_authVO;

public class adminAuthD_filter implements Filter {

	private FilterConfig config;

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		
		List list = (List) session.getAttribute("admin_auth");
		Admin_authVO Auth_listA = (Admin_authVO) list.get(3);
		if(Auth_listA.getAuth_status() == 1) {
			chain.doFilter(request, response);
		}else {
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/index-back.jsp");
			req.setAttribute("Auth_listError", "µLÅv­­");
			failureView.forward(req, res);
		}
		
	}
}
