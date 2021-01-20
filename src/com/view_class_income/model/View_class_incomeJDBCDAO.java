package com.view_class_income.model;

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

//刪除待確認
//查詢子類別待確認

public class View_class_incomeJDBCDAO implements View_class_incomeDAO_interface {
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

	private static final String GET_ALL_STMT = "SELECT * FROM VIEW_CLASS_INCOME";

	@Override
	public List<View_class_incomeVO> getAll() {
		List<View_class_incomeVO> list = new ArrayList<View_class_incomeVO>();
		View_class_incomeVO View_class_incomeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				View_class_incomeVO = new View_class_incomeVO();
				View_class_incomeVO.setClass_id(rs.getString("class_ID"));
				View_class_incomeVO.setClass_name(rs.getString("class_name"));
				View_class_incomeVO.setMember_id(rs.getString("member_id"));
				View_class_incomeVO.setPrice(rs.getInt("price"));
				View_class_incomeVO.setStartfund_date(rs.getTimestamp("startfund_date"));
				View_class_incomeVO.setClass_status(rs.getInt("class_status"));
				list.add(View_class_incomeVO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
	}

	
	public List<View_class_incomeVO> getMemberAll(String member_id) {
		List<View_class_incomeVO> list = new ArrayList<View_class_incomeVO>();
		View_class_incomeVO View_class_incomeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = " SELECT * FROM VIEW_CLASS_INCOME WHERE MEMBER_ID= '"+member_id+"' ORDER BY CLASS_ID";
		System.out.println(sql);
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				View_class_incomeVO = new View_class_incomeVO();
				View_class_incomeVO.setClass_id(rs.getString("class_ID"));
				View_class_incomeVO.setClass_name(rs.getString("class_name"));
				View_class_incomeVO.setMember_id(rs.getString("member_id"));
				View_class_incomeVO.setPrice(rs.getInt("price"));
				View_class_incomeVO.setStartfund_date(rs.getTimestamp("startfund_date"));
				View_class_incomeVO.setClass_status(rs.getInt("class_status"));
				list.add(View_class_incomeVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return list;
		}
}
