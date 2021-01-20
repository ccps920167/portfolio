package com.evaluation.model;

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


public class EvaluationDAOJDBC implements EvaluationDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO evaluation VALUES ('EI' || lpad(evaluation_SEQ.NEXTVAL,5, '0'),?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE evaluation set class_id=?, member_id=? , evaluation_class=? , evaluation_score=? , evaluation_time=? , evaluation_status=? where evaluation_id = ?";
	private static final String GET_ALL_STMT = "SELECT evaluation_id , class_id , member_id , evaluation_class , evaluation_score , evaluation_time , evaluation_status FROM evaluation order by evaluation_time";
	private static final String GET_ONE_STMT = "SELECT evaluation_id , class_id , member_id , evaluation_class , evaluation_score , evaluation_time , evaluation_status FROM evaluation where evaluation_id = ?";

	@Override
	public void insert(EvaluationVO evaluationVO1) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, evaluationVO1.getClass_id());
			pstmt.setString(2, evaluationVO1.getMember_id());
			pstmt.setString(3, evaluationVO1.getEvaluation_class());
			pstmt.setInt(4, evaluationVO1.getEvaluation_score());
			pstmt.setTimestamp(5, evaluationVO1.getEvaluation_time());
			pstmt.setInt(6, evaluationVO1.getEvaluation_status());
		
			pstmt.executeUpdate();

			// Handle any driver errors

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(EvaluationVO evaluationVO2) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(7, evaluationVO2.getEvaluation_id());
			pstmt.setString(1, evaluationVO2.getClass_id());
			pstmt.setString(2, evaluationVO2.getMember_id());
			pstmt.setString(3, evaluationVO2.getEvaluation_class());
			pstmt.setInt(4, evaluationVO2.getEvaluation_score());
			pstmt.setTimestamp(5, evaluationVO2.getEvaluation_time());
			pstmt.setInt(6, evaluationVO2.getEvaluation_status());
			pstmt.executeUpdate();

			// Handle any driver errors

			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public EvaluationVO findByPrimaryKey(String evaluation_id) {
		EvaluationVO evaluationVO3 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, evaluation_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// EvaluationVO 也稱為 Domain objects
				evaluationVO3 = new EvaluationVO();
				evaluationVO3.setEvaluation_id(rs.getString("evaluation_id"));
				evaluationVO3.setClass_id(rs.getString("class_id"));
				evaluationVO3.setMember_id(rs.getString("member_id"));
				evaluationVO3.setEvaluation_class(rs.getString("evaluation_class"));
				evaluationVO3.setEvaluation_score(rs.getInt("evaluation_score"));
				evaluationVO3.setEvaluation_time(rs.getTimestamp("evaluation_time"));
				evaluationVO3.setEvaluation_status(rs.getInt("evaluation_status"));
			}
			// Handle any driver errors

			
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
		return evaluationVO3;
	}
	@Override
	public List<EvaluationVO> getAll() {
		List<EvaluationVO> list = new ArrayList<EvaluationVO>();
		EvaluationVO evaluationVO4 = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				evaluationVO4 = new EvaluationVO();
				evaluationVO4.setEvaluation_id(rs.getString("evaluation_id"));
				evaluationVO4.setClass_id(rs.getString("class_id"));
				evaluationVO4.setMember_id(rs.getString("member_id"));
				evaluationVO4.setEvaluation_class(rs.getString("evaluation_class"));
				evaluationVO4.setEvaluation_score(rs.getInt("evaluation_score"));
				evaluationVO4.setEvaluation_time(rs.getTimestamp("evaluation_time"));
				evaluationVO4.setEvaluation_status(rs.getInt("evaluation_status"));
				list.add(evaluationVO4); // Store the row in the list

			}


			// Handle any SQL errors
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

	public static void main(String[] args) {
		EvaluationDAOJDBC dao = new EvaluationDAOJDBC();
		// insert
//		EvaluationVO evaluationVO1 = new EvaluationVO();
//		evaluationVO1.setClass_id("CLA00001");
//		evaluationVO1.setMember_id("MEM00001");
//		evaluationVO1.setEvaluation_class(
//				"老師說的很棒，非常之好，好到不能自己，老師說的很棒，非常之好，好到不能自己，老師說的很棒，非常之好，好到不能自己，老師說的很棒，非常之好，好到不能自己，老師說的很棒，非常之好，好到不能自己，老師說的很棒，非常之好，好到不能自己，");
//		evaluationVO1.setEvaluation_score(10);
//		evaluationVO1.setEvaluation_time(java.sql.Timestamp.valueOf("2020-11-27 19:48:51"));
//		evaluationVO1.setEvaluation_status(0);
//		dao.insert(evaluationVO1);

		// update
//		EvaluationVO evaluationVO2 = new EvaluationVO();
//
//		evaluationVO2.setEvaluation_id("EL00068");
//		evaluationVO2.setClass_id("CLA00002");
//		evaluationVO2.setMember_id("MEM00002");
//		evaluationVO2.setEvaluation_class(
//				"蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦蝦");
//		evaluationVO2.setEvaluation_score(0);
//		evaluationVO2.setEvaluation_time(java.sql.Timestamp.valueOf("2020-11-28 20:00:00"));
//		evaluationVO2.setEvaluation_status(1);
//		dao.update(evaluationVO2);

		// List getAll()
//		List<EvaluationVO> list = dao.getAll();
//		for (EvaluationVO aEva : list) {
//			System.out.print(aEva.getEvaluation_id() + ",");
//			System.out.print(aEva.getClass_id() + ",");
//			System.out.print(aEva.getMember_id() + ",");
//			System.out.print(aEva.getEvaluation_class() + ",");
//			System.out.print(aEva.getEvaluation_score() + ",");
//			System.out.print(aEva.getEvaluation_time() + ",");
//			System.out.print(aEva.getEvaluation_status());
//			System.out.println();

			// get one
//			EvaluationVO evaluationVO4 = dao.findByPrimaryKey("EI00011");
//			System.out.print(evaluationVO4.getEvaluation_id() + ",");
//			System.out.print(evaluationVO4.getClass_id() + ",");
//			System.out.print(evaluationVO4.getMember_id() + ",");
//			System.out.print(evaluationVO4.getEvaluation_class() + ",");
//			System.out.print(evaluationVO4.getEvaluation_score() + ",");
//			System.out.print(evaluationVO4.getEvaluation_time() + ",");
//			System.out.println(evaluationVO4.getEvaluation_status());
			System.out.println("---------------------");

		}
	}
//}
