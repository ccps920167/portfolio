package com.coupon.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;


@WebServlet("/coupon/CouponServlet")
public class CouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> error = new LinkedList<String>();
			req.setAttribute("error", error);

			try {
				String str = req.getParameter("couponVO1");
				String couponVOO = "[C][O][U][0-9]{5}";
				if (str == null || str.trim().length() == 0) {
					error.add("請輸入優惠卷編號");
					
					
				} else if (!str.matches(couponVOO)) {
					error.add("格式錯誤,請重新輸入");
				}
				if (!error.isEmpty()) {
					RequestDispatcher Dis = req.getRequestDispatcher("/back-end/coupon/select-page.jsp");
					Dis.forward(req, res);
					return; // 要return 不然會衝下去
				}

				CouponService couponVOsvc = new CouponService();
				CouponVO couponVO = couponVOsvc.getOneCoupon(str);
				if (couponVO == null) {
					error.add("查無資料");
				}
				if (!error.isEmpty()) {
					RequestDispatcher Dis = req.getRequestDispatcher("/back-end/coupon/select-page.jsp");
					Dis.forward(req, res);
					return;
				}

				req.setAttribute("couponVO", couponVO);
				RequestDispatcher Diss = req.getRequestDispatcher("/back-end/coupon/listOneCoupon.jsp");
				Diss.forward(req, res);
			} catch (Exception e) {
				error.add("查無資料");
				RequestDispatcher Diss = req.getRequestDispatcher("/back-end/coupon/select-page.jsp");
				Diss.forward(req, res);
			}
		}

		if ("insert".equals(action)) {
			List<String> error = new LinkedList<String>();
			req.setAttribute("error", error);
			try {
				String coupon_code = req.getParameter("coupon_code");
				if (coupon_code == null || coupon_code.trim().length() == 0) {
					error.add("請輸入優惠卷代碼");
				}
				Integer coupon_amount = null;
				try {
					coupon_amount = new Integer(req.getParameter("coupon_amount"));
				} catch (NumberFormatException e) {
					coupon_amount = 100;
					error.add("優惠卷金額請輸入數字");
				}
				java.sql.Timestamp coupon_time = null;
				try {
					coupon_time = java.sql.Timestamp.valueOf(req.getParameter("coupon_time"));
				} catch (Exception e) {
					coupon_time = new java.sql.Timestamp(System.currentTimeMillis());
					error.add("時間格式不對");
				}

				java.sql.Timestamp coupon_expiry = null;
				try {
					coupon_expiry = java.sql.Timestamp.valueOf(req.getParameter("coupon_expiry"));
				} catch (Exception e) {
					coupon_expiry = new java.sql.Timestamp(System.currentTimeMillis());
					error.add("時間格式不對");
				}
				CouponVO couponVO = new CouponVO();
				couponVO.setCoupon_code(coupon_code);
				couponVO.setCoupon_amount(coupon_amount);
				couponVO.setCoupon_time(coupon_time);
				couponVO.setCoupon_expiry(coupon_expiry);

				if (!error.isEmpty()) {
					req.setAttribute("couponVO", couponVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/addCoupon.jsp");
					failureView.forward(req, res);
					return;
				}
				CouponService couponsvc = new CouponService();
				couponVO = couponsvc.addCoupon(coupon_code, coupon_amount, coupon_time, coupon_expiry);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/coupon/listAllCoupon.jsp"); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				error.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/addCoupon.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {
			List<String> error = new LinkedList<String>();
			req.setAttribute("error", error);

			try {
				String coupon = req.getParameter("coupon");
				CouponService couponSvc = new CouponService();
				couponSvc.deleteEmp(coupon);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/coupon/listAllCoupon.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				error.add("刪除失敗");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { 

			List<String> error = new LinkedList<String>();
			req.setAttribute("error", error);
			try {
				String coupon = req.getParameter("coupon");

				CouponService CouponSvc = new CouponService();
				CouponVO couponVO = CouponSvc.getOneCoupon(coupon);

				req.setAttribute("couponVO", couponVO);
				String url = "/back-end/coupon/updateCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				error.add("無法取得要修改的資料:");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {
			List<String> error = new LinkedList<String>();
			req.setAttribute("error", error);
			try {
				String coupon_id = req.getParameter("coupon_id");
				
				String coupon_code = req.getParameter("coupon_code");
				if (coupon_code == null || coupon_code.trim().length() == 0) {
					error.add("請輸入優惠卷代碼");
				}
				
				Integer coupon_amount = null;
				try {
					coupon_amount = new Integer(req.getParameter("coupon_amount"));
				} catch (NumberFormatException e) {
					coupon_amount = 100;
					error.add("優惠卷金額請輸入數字");
				}
				java.sql.Timestamp coupon_time = null;
				try {
					coupon_time = java.sql.Timestamp.valueOf(req.getParameter("coupon_time"));
				} catch (Exception e) {
					coupon_time = new java.sql.Timestamp(System.currentTimeMillis());
					error.add("時間格式不對");
				}

				java.sql.Timestamp coupon_expiry = null;
				try {
					coupon_expiry = java.sql.Timestamp.valueOf(req.getParameter("coupon_expiry"));
				} catch (Exception e) {
					coupon_expiry = new java.sql.Timestamp(System.currentTimeMillis());
					error.add("時間格式不對");
				}
				CouponVO couponVO = new CouponVO();
				couponVO.setCoupon_code(coupon_id);
				couponVO.setCoupon_code(coupon_code);
				couponVO.setCoupon_amount(coupon_amount);
				couponVO.setCoupon_time(coupon_time);
				couponVO.setCoupon_expiry(coupon_expiry);

				if (!error.isEmpty()) {
					req.setAttribute("couponVO", couponVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/updateCoupon.jsp");
					failureView.forward(req, res);
					return;
				}
				CouponService couponsvc = new CouponService();
				couponVO = couponsvc.updateCoupon(coupon_id, coupon_code, coupon_amount, coupon_time, coupon_expiry);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/coupon/listAllCoupon.jsp");
				successView.forward(req, res);
			} catch (Exception e) {
				error.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/updateCoupon.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
