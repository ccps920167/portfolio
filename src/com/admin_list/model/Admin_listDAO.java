package com.admin_list.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.admin_auth.model.Admin_authVO;

public class Admin_listDAO implements Admin_list_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEA102G5";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE admin_list set admin_name=?, admin_pwd=?, admin_status=? WHERE admin_id=?";
	private static final String DELETE = 
			"DELETE FROM admin_list where admin_id= ?";
	private static final String GET_ONE_STMT = 
			"SELECT admin_id, admin_name, admin_pwd, admin_status FROM admin_list where admin_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT admin_id, admin_name, admin_pwd, admin_status FROM admin_list order by admin_id";

	
	@Override
	public void insert(Admin_listVO admin_listVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, admin_listVO.getAdmin_name());
			pstmt.setString(2, admin_listVO.getAdmin_pwd());
			pstmt.setInt(3, admin_listVO.getAdmin_status());
			
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
	public void update(Admin_listVO admin_listVO) {
		 Connection con = null;
		    PreparedStatement pstmt = null;
		    
		    try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setString(1, admin_listVO.getAdmin_name());
				pstmt.setString(2, admin_listVO.getAdmin_pwd());
				pstmt.setInt(3, admin_listVO.getAdmin_status());
				pstmt.setString(4, admin_listVO.getAdmin_id());
				
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

	@Override
	public void delete(String admin_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, admin_listVO);
			
			    pstmt.executeUpdate();
			    
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
			
			
	}

	@Override
	public Admin_listVO findByPrimaryKey(String admin_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Admin_listVO alVO = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
		    pstmt.setString(1, admin_listVO);
		    rs = pstmt.executeQuery();

			while (rs.next()) {
			alVO = new Admin_listVO();
			alVO.setAdmin_id(rs.getString("admin_id"));
			alVO.setAdmin_name(rs.getString("admin_name"));
			alVO.setAdmin_pwd(rs.getString("admin_pwd"));
			alVO.setAdmin_status(rs.getInt("admin_status"));
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
		
		
		
		
		return alVO;
	}

	@Override
	public List<Admin_listVO> getAll() {
		ArrayList<Admin_listVO> list = new ArrayList<Admin_listVO>();
		Admin_listVO alVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				alVO = new Admin_listVO();
				alVO.setAdmin_id(rs.getString("admin_id"));
				alVO.setAdmin_name(rs.getString("admin_name"));
				alVO.setAdmin_pwd(rs.getString("admin_pwd"));
				alVO.setAdmin_status(rs.getInt("admin_status"));
				list.add(alVO); // Store the row in the list
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


	@Override
	public String addWithAdmin_list(Admin_listVO admin_listVO, List<Admin_authVO> Admin_authVO) {
		// TODO Auto-generated method stub
		return null;
	}



}
