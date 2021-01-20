package com.admin_auth.model;

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


public class Admin_authJDBCDAO implements Admin_auth_interface {
	
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
	
	private static final String INSERT_STMT = 
			"INSERT INTO admin_auth VALUES (?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE admin_auth set auth_status =?, auth_update =? WHERE admin_id=? AND auth_id=?";
	private static final String DELETE = 
			"DELETE FROM admin_auth where admin_id= ?";
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
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, admin_authVO.getAdmin_id());
			pstmt.setString(2, admin_authVO.getAuth_id());
			pstmt.setInt(3, admin_authVO.getAuth_status());
			pstmt.setTimestamp(4, admin_authVO.getAuth_update());
			
			pstmt.executeUpdate();
			
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
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setInt(1, admin_authVO.getAuth_status());
				pstmt.setTimestamp(2, admin_authVO.getAuth_update());
				pstmt.setString(3, admin_authVO.getAdmin_id());
				pstmt.setString(4, admin_authVO.getAuth_id());
				
				pstmt.executeUpdate();
				
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

	@Override
	public void delete(String admin_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, admin_id);
			
			    pstmt.executeUpdate();
			    
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
			

	}

	@Override
	public List<Admin_authVO> findByPrimaryKey(String admin_id) {
		List<Admin_authVO> list = new ArrayList<Admin_authVO>();
		Admin_authVO aaVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
		    pstmt.setString(1, admin_id);
		    rs = pstmt.executeQuery();

			while (rs.next()) {
			aaVO = new Admin_authVO();
			aaVO.setAdmin_id(rs.getString("admin_id"));
			aaVO.setAuth_id(rs.getString("auth_id"));
			aaVO.setAuth_status(rs.getInt("auth_status"));
			aaVO.setAuth_update(rs.getTimestamp("auth_update"));
			list.add(aaVO);
			}
			
		    
			
			
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
		
		
		
		
		return list;
	}

	@Override
	public List<Admin_authVO> getAll() {
		List<Admin_authVO> list = new ArrayList<Admin_authVO>();
		Admin_authVO aaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
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
			
			con = ds.getConnection();
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
	
	
	public static void main(String[] args) {
		Admin_authJDBCDAO dao = new Admin_authJDBCDAO();
		
		// 新增
//		Admin_authVO Admin_authVO1 = new Admin_authVO();
//		Admin_authVO1.setAdmin_id("AI00011");
//		Admin_authVO1.setAuth_id("A");
//		Admin_authVO1.setAuth_status(0);
//		Admin_authVO1.setAuth_update(java.sql.Timestamp.valueOf("2020-12-05 14:23:00"));
//		
//		dao.insert(Admin_authVO1);
		
		//修改
//		Admin_authVO Admin_authVO2 = new Admin_authVO();
//		Admin_authVO2.setAuth_status(1);
//		Admin_authVO2.setAuth_update(java.sql.Timestamp.valueOf("2020-12-05 14:23:00"));
//		Admin_authVO2.setAdmin_id("AI00001");
//		Admin_authVO2.setAuth_id("A");
//		dao.update(Admin_authVO2);

		//刪除
//		Admin_authVO Admin_authVO4 = new Admin_authVO();
//		Admin_authVO4.setAdmin_id("AI00001");
//		Admin_authVO4.setAuth_id("A");
//		dao.delete(Admin_authVO4);
		
		// 查詢
//		Admin_authVO Admin_authVO5 = new Admin_authVO();
//		Admin_authVO5.setAdmin_id("AI00001");
//		List <Admin_authVO> list = dao.findByPrimaryKey("AI00001");
//		System.out.println(list.size());
//		for (Admin_authVO aaVO : list) {
//			System.out.println(aaVO.getAdmin_id()+"，");
//			System.out.println(aaVO.getAuth_id()+"，");
//			System.out.println(aaVO.getAuth_status()+"，");
//			System.out.println(aaVO.getAuth_update()+"。");
//			System.out.println();
//		}
		
//		Admin_authVO Admin_authVO3 = dao.findByPrimaryKey("AI00001");
//		System.out.println(Admin_authVO3.getAdmin_id()+"，");
//		System.out.println(Admin_authVO3.getAuth_id()+"，");
//		System.out.println(Admin_authVO3.getAuth_status()+"，");
//		System.out.println(Admin_authVO3.getAuth_update()+"。");
		
		//getAll 查詢
//		List <Admin_authVO> list = dao.getAll();
//		for (Admin_authVO aaVO : list) {
//			System.out.println(aaVO.getAdmin_id()+"，");
//			System.out.println(aaVO.getAuth_id()+"，");
//			System.out.println(aaVO.getAuth_status()+"，");
//			System.out.println(aaVO.getAuth_update()+"。");
//			System.out.println();
//		}
		
		//findByPK(String admin_id) 測試
//		Admin_authVO Admin_authVO6 = dao.findByPK("AI00001");
//			System.out.println(Admin_authVO6.getAdmin_id()+"，");
//			System.out.println(Admin_authVO6.getAuth_id()+"，");
//			System.out.println(Admin_authVO6.getAuth_status()+"，");
//			System.out.println(Admin_authVO6.getAuth_update()+"。");
//			System.out.println();
//		}
		
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
