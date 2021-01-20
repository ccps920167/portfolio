package com.admin_auth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Admin_authDAO implements Admin_auth_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO admin_auth VALUES (?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE admin_auth set auth_status =?, auth_update =? WHERE admin_id=? AND auth_id=?";
	private static final String DELETE = 
			"DELETE FROM admin_auth where admin_id= ? AND auth_id = ?";
	private static final String GET_ONE_STMT = 
			"SELECT admin_id, auth_id, auth_status, auth_update FROM admin_auth where admin_id = ? ";
	private static final String GET_ALL_STMT = 
			"SELECT admin_id, auth_id, auth_status, auth_update FROM admin_auth order by admin_id";
	private static final String INSERT_STMT_ADMIN_AUTH = 
			"INSERT INTO admin_auth VALUES (?,?,?,?)";


	@Override
	public void insert(Admin_authVO admin_authVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, admin_authVO.getAdmin_id());
			pstmt.setString(2, admin_authVO.getAuth_id());
			pstmt.setInt(3, admin_authVO.getAuth_status());
			pstmt.setTimestamp(4, admin_authVO.getAuth_update());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Admin_authVO admin_authVO) {
		 Connection con = null;
		    PreparedStatement pstmt = null;
		    
		    try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setInt(1, admin_authVO.getAuth_status());
				pstmt.setTimestamp(2, admin_authVO.getAuth_update());
				pstmt.setString(3, admin_authVO.getAdmin_id());
				pstmt.setString(4, admin_authVO.getAuth_id());
				
				pstmt.executeUpdate();
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException se) {
				se.printStackTrace();
			}finally {
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con != null) {
						try {
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
		
				}
			}

	}

//	@Override
//	public void delete(Admin_authVO admin_authVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		
//			try {
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);
//				pstmt = con.prepareStatement(DELETE);
//				
//				pstmt.setString(1, admin_authVO.getAdmin_id());
//				pstmt.setString(2, admin_authVO.getAuth_id());
//			
//			    pstmt.executeUpdate();
//			    
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally {
//				
//			    if(pstmt != null) {
//				    try {
//					    pstmt.close();
//				    } catch (SQLException e) {
//					    e.printStackTrace();
//				    }
//			    }
//			    if(con != null) {
//				    try {
//				    	con.close();
//				    } catch (SQLException e) {
//				    	e.printStackTrace();
//				    }
//			    }
//			}
//			
//
//	}

//	@Override
//	public List<Admin_authVO> findByPrimaryKey(Admin_authVO admin_authVO) {
//		List<Admin_authVO> list = new ArrayList<Admin_authVO>();
//		Admin_authVO aaVO = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//			
//		    pstmt.setString(1, admin_authVO.getAdmin_id());
//		    rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//			aaVO = new Admin_authVO();
//			aaVO.setAdmin_id(rs.getString("admin_id"));
//			aaVO.setAuth_id(rs.getString("auth_id"));
//			aaVO.setAuth_status(rs.getInt("auth_status"));
//			aaVO.setAuth_update(rs.getTimestamp("auth_update"));
//			list.add(aaVO);
//			}
//			
//		    
//			
//			
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			if(pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if(con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		
//		
//		
//		return list;
//	}

	@Override
	public List<Admin_authVO> getAll() {
		List<Admin_authVO> list = new ArrayList<Admin_authVO>();
		Admin_authVO aaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				aaVO = new Admin_authVO();
				aaVO.setAdmin_id(rs.getString("admin_id"));
				aaVO.setAuth_id(rs.getString("auth_id"));
				aaVO.setAuth_status(rs.getInt("auth_status"));
				aaVO.setAuth_update(rs.getTimestamp("auth_update"));
				list.add(aaVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}
	public Admin_authVO findByPK(String admin_id){
		Admin_authVO aaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
		    pstmt.setString(1, admin_id);
		    rs = pstmt.executeQuery();

			while (rs.next()) {
			aaVO = new Admin_authVO();
			aaVO.setAdmin_id(rs.getString("admin_id"));
			aaVO.setAuth_id(rs.getString("auth_id"));
			aaVO.setAuth_status(rs.getInt("auth_status"));
			aaVO.setAuth_update(rs.getTimestamp("auth_update"));
			}
			
		    
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		return aaVO;
	}

	@Override
	public void delete(String admin_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Admin_authVO> findByPrimaryKey(String admin_id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addWithAdmin_auth(Admin_authVO admin_authVO, Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT_ADMIN_AUTH);

			pstmt.setString(1, admin_authVO.getAdmin_id());
			pstmt.setString(2, admin_authVO.getAuth_id());
			pstmt.setInt(3, admin_authVO.getAuth_status());
			pstmt.setTimestamp(4, admin_authVO.getAuth_update());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3.設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-OrderMaster");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
		}
		
	}
	

}
