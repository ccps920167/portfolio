package com.verify_list.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class Verify_listJDBCDAO implements Verify_list_interface {

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
			"INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL, 5, '0'),?,?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE verify_list set class_id=?, admin_id=?, class_check=?, upload_time=? WHERE verify_id=?";
	private static final String DELETE = 
			"DELETE FROM verify_list where verify_id= ?";
	private static final String GET_ONE_STMT = 
			"SELECT verify_id, class_id, admin_id, class_check, upload_time FROM verify_list where verify_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT verify_id, class_id, admin_id, class_check, upload_time FROM verify_list order by verify_id";
	private static final String INSERTWITHCLASS_STMT = 
			"INSERT INTO verify_list VALUES ('VL' || lpad(verify_list_SEQ.NEXTVAL, 5, '0'),?,null,null,?)";
	private static final String UPDATEWITHCLASS_STMT = 
			"UPDATE verify_list set  admin_id=?, class_check=?, upload_time=? WHERE verify_id=?";
	private static final String GET_CLASS_CHECK = 
			"SELECT verify_id, class_id, admin_id, class_check, upload_time FROM verify_list where class_id = ?";
	
	@Override
	public void insert(Verify_listVO verify_listVO) {
        
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, verify_listVO.getClass_id());
			pstmt.setString(2, verify_listVO.getAdmin_id());
			pstmt.setString(3, verify_listVO.getClass_check());
			pstmt.setTimestamp(4, verify_listVO.getUpload_time());
			
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
	public void update(Verify_listVO verify_listVO) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    
	    try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, verify_listVO.getClass_id());
			pstmt.setString(2, verify_listVO.getAdmin_id());
			pstmt.setString(3, verify_listVO.getClass_check());
			pstmt.setTimestamp(4, verify_listVO.getUpload_time());
			pstmt.setString(5, verify_listVO.getVerify_id());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
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
	public void delete(String verify_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, verify_listVO);
			
			    pstmt.executeUpdate();
			    
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}finally {
				
			    if(pstmt != null) {
				    try {
					    pstmt.close();
				    } catch (SQLException se) {
					    se.printStackTrace();
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
	public Verify_listVO findByPrimaryKey(String verify_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Verify_listVO vlVO = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
		    pstmt.setString(1, verify_listVO);
		    rs = pstmt.executeQuery();

			while (rs.next()) {
			vlVO = new Verify_listVO();
			vlVO.setVerify_id(rs.getString("verify_id"));
			vlVO.setClass_id(rs.getString("class_id"));
			vlVO.setAdmin_id(rs.getString("admin_id"));
			vlVO.setClass_check(rs.getString("class_check"));
			vlVO.setUpload_time(rs.getTimestamp("upload_time"));
			}
			
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
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
		
		
		
		
		return vlVO;
	}

	@Override
	public ArrayList<Verify_listVO> getAll() {
		ArrayList<Verify_listVO> list = new ArrayList<Verify_listVO>();
		Verify_listVO vlVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vlVO = new Verify_listVO();
				vlVO.setVerify_id(rs.getString("verify_id"));
				vlVO.setClass_id(rs.getString("class_id"));
				vlVO.setAdmin_id(rs.getString("admin_id"));
				vlVO.setClass_check(rs.getString("class_check"));
				vlVO.setUpload_time(rs.getTimestamp("upload_time"));
				list.add(vlVO); // Store the row in the list
			}

			ds.getConnection();
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


	@Override
	public void updateVerify(Verify_listVO verify_listVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Verify_listVO getClassCheck(String class_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Verify_listVO vlVO = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_CLASS_CHECK);
			
		    pstmt.setString(1, class_id);
		    rs = pstmt.executeQuery();

			while (rs.next()) {
			vlVO = new Verify_listVO();
			vlVO.setVerify_id(rs.getString("verify_id"));
			vlVO.setClass_id(rs.getString("class_id"));
			vlVO.setAdmin_id(rs.getString("admin_id"));
			vlVO.setClass_check(rs.getString("class_check"));
			vlVO.setUpload_time(rs.getTimestamp("upload_time"));
			}
			
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
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
			
		return vlVO;

	}




}
