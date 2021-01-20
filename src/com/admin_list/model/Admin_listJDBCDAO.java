package com.admin_list.model;

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

import com.admin_auth.model.Admin_authJDBCDAO;
import com.admin_auth.model.Admin_authVO;


public class Admin_listJDBCDAO implements Admin_list_interface {
	
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
			"INSERT INTO admin_list VALUES ('AI' || lpad(admin_list_SEQ.NEXTVAL,5,'0'),?,?,?)";
	private static final String UPDATE_STMT = 
			"UPDATE admin_list set admin_name=?, admin_pwd=?, admin_status=? WHERE admin_id=?";
	private static final String DELETE = 
			"DELETE FROM admin_list where admin_id= ?";
	private static final String GET_ONE_STMT = 
			"SELECT admin_id, admin_name, admin_pwd, admin_status FROM admin_list where admin_id = ?";
	private static final String GET_ALL_STMT = 
			"SELECT admin_id, admin_name, admin_pwd, admin_status FROM admin_list order by admin_id";
//	private static final String INSERT_STMT_ADMIN_AUTH = 
//			"INSERT INTO admin_auth VALUES (?,?,?,?)";

	
	@Override
	public void insert(Admin_listVO admin_listVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, admin_listVO.getAdmin_name());
			pstmt.setString(2, admin_listVO.getAdmin_pwd());
			pstmt.setInt(3, admin_listVO.getAdmin_status());
			
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
	public void update(Admin_listVO admin_listVO) {
		 Connection con = null;
		    PreparedStatement pstmt = null;
		    
		    try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_STMT);
				
				pstmt.setString(1, admin_listVO.getAdmin_name());
				pstmt.setString(2, admin_listVO.getAdmin_pwd());
				pstmt.setInt(3, admin_listVO.getAdmin_status());
				pstmt.setString(4, admin_listVO.getAdmin_id());
				
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
	public void delete(String admin_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
			try {
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);
				
				pstmt.setString(1, admin_listVO);
			
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
	public Admin_listVO findByPrimaryKey(String admin_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Admin_listVO alVO = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
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

			
			con = ds.getConnection();
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
	
	public static void main(String[] args) {
		
			Admin_listJDBCDAO dao = new Admin_listJDBCDAO();
			
			// 新增
//			Admin_listVO admin_listVO1 = new Admin_listVO();
//			admin_listVO1.setAdmin_name("admin11");
//			admin_listVO1.setAdmin_pwd("123456");
//			admin_listVO1.setAdmin_status(1);
			
//			ArrayList admin_listList = new ArrayList();
//			
//			dao.addWithAdmin_list(admin_listVO1, admin_listList);
			
			//修改
//			Admin_listVO admin_listVO2 = new Admin_listVO();
//			admin_listVO2.setAdmin_name("test");
//			admin_listVO2.setAdmin_pwd("123");
//			admin_listVO2.setAdmin_status(1);
//			admin_listVO2.setAdmin_id("AI00011");
//			dao.update(admin_listVO2);

			//刪除
//			dao.delete("AI00011");
			
			// 查詢
//			Admin_listVO admin_listVO3 = dao.findByPrimaryKey("AI00001");
//			System.out.println(admin_listVO3.getAdmin_id()+"，");
//			System.out.println(admin_listVO3.getAdmin_name()+"，");
//			System.out.println(admin_listVO3.getAdmin_pwd()+"，");
//			System.out.println(admin_listVO3.getAdmin_status()+"。");
			
			//getAll 查詢
//			List <Admin_listVO> list = dao.getAll();
//			for (Admin_listVO alVO : list) {
//				System.out.println(alVO.getAdmin_id()+"，");
//				System.out.println(alVO.getAdmin_name()+"，");
//				System.out.println(alVO.getAdmin_pwd()+"，");
//				System.out.println(alVO.getAdmin_status()+"。");
//				System.out.println();
//			}
			
			
		

	}
	
	//在這裡利用同一個連線呼叫下面addWithAdmin_list()對兩個表格新增同一筆資料
		@Override
		public String addWithAdmin_list(Admin_listVO admin_listVO, List<Admin_authVO> Admin_authVO) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String next_admin_id = null;
			try {
				
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(INSERT_STMT);

				// 1.設定於pstmt.executeUpdate()之前
				conn.setAutoCommit(false);

				// 先新增訂單
				String cols[] = { "admin_id" };
				pstmt = conn.prepareStatement(INSERT_STMT, cols);
				pstmt.setString(1, admin_listVO.getAdmin_name());
				pstmt.setString(2, admin_listVO.getAdmin_pwd());
				pstmt.setInt(3, admin_listVO.getAdmin_status());
				pstmt.executeUpdate();

				// 取得對應的自增主鍵值
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					next_admin_id = rs.getString(1);
					System.out.println("自增主鍵值= " + next_admin_id + "(剛新增成功的訂單編號)");
				} else {
					System.out.println("未取得自增主鍵值");
				}
				rs.close();
				// 再同時新增訂單明細內容
				Admin_authJDBCDAO dao = new Admin_authJDBCDAO();

				for (Admin_authVO admin_listVO1 : Admin_authVO) {
					admin_listVO1.setAdmin_id(next_admin_id);
					dao.addWithAdmin_auth(admin_listVO1, conn);
				}

				// 2.設定於pstmt.executeUpdate()之後
				conn.commit();
				conn.setAutoCommit(true);
//				System.out.println("orderItemList.size()-B= "
//						+ orderItemList.size());
//				System.out.println("新增訂單編號" + next_orderid + "時，共有明細"
//						+ orderItemList.size() + "筆同時被新增");

			} catch (SQLException se) {
				if (conn != null) {
					try {
						// 3.設定於當有exception發生時之catch區塊內
						System.err.print("Transaction is being ");
						System.err.println("rolled back-由-OrderItem");
						conn.rollback();
					} catch (SQLException excep) {
						throw new RuntimeException("rollback error occured. "
								+ excep.getMessage());
					}
				}
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return new String(next_admin_id);
		}
    
	//實作介面裡的function給上面的方法來呼叫
//	@Override
//	public void addWithAdmin_auth(Admin_authVO admin_authVO, Connection con) {
//		PreparedStatement pstmt = null;
//
//		try {
//			pstmt = con.prepareStatement(INSERT_STMT_ADMIN_AUTH);
//
//			pstmt.setString(1, admin_authVO.getAdmin_id());
//			pstmt.setString(2, admin_authVO.getAuth_id());
//			pstmt.setInt(3, admin_authVO.getAuth_status());
//			pstmt.setTimestamp(4, admin_authVO.getAuth_update());
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 3.設定於當有exception發生時之catch區塊內
//					System.err.print("Transaction is being ");
//					System.err.println("rolled back-由-OrderMaster");
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. " + excep.getMessage());
//				}
//			}
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//		}
//		
//	}
	
    

}
