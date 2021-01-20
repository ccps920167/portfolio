package com.login_history.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Login_historyJDBCDAO implements Login_historyDAO_interface {
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
	
	private static final String INSERT_STMT = "INSERT INTO login_history  VALUES ('LOGIN' || lpad(LOGID_SEQ.NEXTVAL,5, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT login_id,login_time ,login_arrange ,login_ip,member_id FROM login_history order by login_id";
	private static final String GET_ONE_STMT = "SELECT login_id,login_time ,login_arrange ,login_ip,member_id FROM login_history where login_id = ?";
	private static final String DELETE = "DELETE FROM login_history where login_id = ?";
	private static final String UPDATE = "UPDATE login_history set login_time=?,login_arrange=?,login_ip=?,member_id=? where login_id = ?";


	@Override
	public void insert(Login_historyVO login_historyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setTimestamp(1, login_historyVO.getLogin_time());
			pstmt.setString(2, login_historyVO.getLogin_arrange());
			pstmt.setString(3, login_historyVO.getLogin_ip());
			pstmt.setString(4, login_historyVO.getMember_id());
			
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
	public void update(Login_historyVO login_historyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setTimestamp(1, login_historyVO.getLogin_time());
			pstmt.setString(2, login_historyVO.getLogin_arrange());
			pstmt.setString(3, login_historyVO.getLogin_ip());
			pstmt.setString(4, login_historyVO.getMember_id());
			pstmt.setString(5, login_historyVO.getLogin_id());
			
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
	public void delete(String login_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, login_id);

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
	public Login_historyVO findByPrimaryKey(String login_id) {
		Login_historyVO login_historyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, login_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				login_historyVO = new Login_historyVO();
				login_historyVO.setLogin_id(rs.getString("login_id"));
				login_historyVO.setLogin_time(rs.getTimestamp("login_time"));
				login_historyVO.setLogin_arrange(rs.getString("login_arrange"));
				login_historyVO.setLogin_ip(rs.getString("login_ip"));
				login_historyVO.setMember_id(rs.getString("member_id"));
				
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
		return login_historyVO;


	}

	@Override
	public List<Login_historyVO> getAll() {
		List<Login_historyVO> list = new ArrayList<Login_historyVO>();
		Login_historyVO login_historyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				login_historyVO = new Login_historyVO();
				login_historyVO.setLogin_id(rs.getString("login_id"));
				login_historyVO.setLogin_time(rs.getTimestamp("login_time"));
				login_historyVO.setLogin_arrange(rs.getString("login_arrange"));
				login_historyVO.setLogin_ip(rs.getString("login_ip"));
				login_historyVO.setMember_id(rs.getString("member_id"));
				
				
				list.add(login_historyVO);
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
