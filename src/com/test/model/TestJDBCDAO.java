package com.test.model;

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

public class TestJDBCDAO implements TestDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO TEST VALUES ('TU'||lPAD(TEST_SEQ.NEXTVAL,5,'0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT test_id,unit_id ,test_answer ,test_content,opta,optb,optc,optd FROM Test order by test_id";
	private static final String GET_ONE_STMT = "SELECT test_id,unit_id ,test_answer ,test_content,opta,optb,optc,optd FROM Test where test_id = ?";
	private static final String DELETE = "DELETE FROM Test where test_id = ?";
	private static final String UPDATE = "UPDATE Test set unit_id=?,test_answer=?,test_content=?,opta=?,optb=?,optc=?,optd=? where test_id = ?";

	@Override
	public void insert(TestVO testVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, testVO.getUnit_id());
			pstmt.setString(2, testVO.getTest_answer());
			pstmt.setString(3, testVO.getTest_content());
			pstmt.setString(4, testVO.getOpta());
			pstmt.setString(5, testVO.getOptb());
			pstmt.setString(6, testVO.getOptc());
			pstmt.setString(7, testVO.getOptd());

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
	public void update(TestVO testVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, testVO.getUnit_id());
			pstmt.setString(2, testVO.getTest_answer());
			pstmt.setString(3, testVO.getTest_content());
			pstmt.setString(4, testVO.getOpta());
			pstmt.setString(5, testVO.getOptb());
			pstmt.setString(6, testVO.getOptc());
			pstmt.setString(7, testVO.getOptd());
			pstmt.setString(8, testVO.getTest_id());

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
	public void delete(String test_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, test_id);

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
	public TestVO findByPrimaryKey(String test_id) {
		TestVO testVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, test_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				testVO = new TestVO();
				testVO.setTest_id(rs.getString("test_id"));
				testVO.setUnit_id(rs.getString("unit_id"));
				testVO.setTest_answer(rs.getString("test_answer"));
				testVO.setTest_content(rs.getString("test_content"));
				testVO.setOpta(rs.getString("opta"));
				testVO.setOptb(rs.getString("optb"));
				testVO.setOptc(rs.getString("optc"));
				testVO.setOptd(rs.getString("optd"));

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
		return testVO;
	}

	@Override
	public List<TestVO> getAll() {
		List<TestVO> list = new ArrayList<TestVO>();
		TestVO testVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				testVO = new TestVO();
				testVO.setTest_id(rs.getString("test_id"));
				testVO.setUnit_id(rs.getString("unit_id"));
				testVO.setTest_answer(rs.getString("test_answer"));
				testVO.setTest_content(rs.getString("test_content"));
				testVO.setOpta(rs.getString("opta"));
				testVO.setOptb(rs.getString("optb"));
				testVO.setOptc(rs.getString("optc"));
				testVO.setOptd(rs.getString("optd"));

				list.add(testVO);
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
