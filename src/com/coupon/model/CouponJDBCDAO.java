package com.coupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CouponJDBCDAO implements CouponDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA102G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO Coupon (coupon_id,coupon_code,coupon_amount,coupon_time,coupon_expiry) VALUES ('COU' || lpad(coupon_SQE.NEXTVAL, 5, '0'), ?, ?,?,?)";
	private static final String UPDATE = "UPDATE Coupon set coupon_code=?, coupon_amount=?, coupon_time=?,coupon_expiry=? where coupon_id = ?";
	private static final String DELETE_STMT = "DELETE FROM Coupon WHERE coupon_id = ?";
	private static final String GET_ONE_STMT = "SELECT coupon_id , coupon_code, coupon_amount, coupon_time, coupon_expiry FROM Coupon where coupon_id = ?";
	private static final String GET_ALL_STMT = "SELECT coupon_id , coupon_code, coupon_amount, coupon_time,coupon_expiry FROM Coupon";
	

	@Override
	public void insert(CouponVO couponVO) {
		Connection con =null;
		PreparedStatement pstmt =null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, couponVO.getCoupon_code());
			pstmt.setInt(2, couponVO.getCoupon_amount());
			pstmt.setTimestamp(3, couponVO.getCoupon_time());
			pstmt.setTimestamp(4, couponVO.getCoupon_expiry());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, couponVO.getCoupon_code());
			pstmt.setInt(2, couponVO.getCoupon_amount());
			pstmt.setTimestamp(3, couponVO.getCoupon_time());
			pstmt.setTimestamp(4, couponVO.getCoupon_expiry());
			pstmt.setString(5, couponVO.getCoupon_id());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String coupon_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, coupon_id);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public CouponVO findByPrimaryKey(String coupon_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		CouponVO vo = null;
		ResultSet rs = null;
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, coupon_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new CouponVO();
				vo.setCoupon_id(rs.getString("coupon_id"));
				vo.setCoupon_code(rs.getString("coupon_code"));
				vo.setCoupon_amount(rs.getInt("coupon_amount"));
				vo.setCoupon_time(rs.getTimestamp("coupon_time"));
				vo.setCoupon_expiry(rs.getTimestamp("coupon_expiry"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return vo;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> empList = new ArrayList<CouponVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		CouponVO vo2 = null;
		ResultSet rs = null;
		try {

		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo2 = new CouponVO();
				vo2.setCoupon_id(rs.getString("coupon_id"));
				vo2.setCoupon_code(rs.getString("coupon_code"));
				vo2.setCoupon_amount(rs.getInt("coupon_amount"));
				vo2.setCoupon_time(rs.getTimestamp("coupon_time"));
				vo2.setCoupon_expiry(rs.getTimestamp("coupon_expiry"));
				empList.add(vo2);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return empList;
	}

	public static void main(String[] args) {
		// 新增
		CouponJDBCDAO dao = new CouponJDBCDAO();
		CouponVO vo = new CouponVO();
		vo.setCoupon_code("江明哲");
		vo.setCoupon_amount(35);
		vo.setCoupon_time(java.sql.Timestamp.valueOf("2020-12-02 04:16:00"));
		vo.setCoupon_expiry(java.sql.Timestamp.valueOf("2020-12-01 04:15:04"));
		dao.insert(vo);

		// 修改
//		CouponVO vo1 = new CouponVO();
//
//		vo1.setCoupon_code("哈哈哈");
//		vo1.setCoupon_amount(35);
//		vo1.setCoupon_time(java.sql.Date.valueOf("2020-12-03"));
//		vo1.setCoupon_expiry(java.sql.Date.valueOf("2020-10-04"));
//		vo1.setCoupon_id("COU00011");
//		dao.update(vo1);

		// 刪除
//		dao.delete("COU00011");

		// 查詢
//		CouponVO vo1=dao.findByPrimaryKey("COU00010");
//		System.out.println(vo1.getCoupon_id());
//		System.out.println(vo1.getCoupon_code());
//		System.out.println(vo1.getCoupon_amount());
//		System.out.println(vo1.getCoupon_time());
//		System.out.println(vo1.getCoupon_expiry());

		// 查詢全部
//		List<CouponVO> li = dao.getAll();
//		for (CouponVO apt : li) {
//			System.out.println(apt.getCoupon_id());
//			System.out.println(apt.getCoupon_code());
//			System.out.println(apt.getCoupon_amount());
//			System.out.println(apt.getCoupon_time());
//			System.out.println(apt.getCoupon_expiry());
//		}

	}

}
