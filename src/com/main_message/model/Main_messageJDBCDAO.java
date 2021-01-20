package com.main_message.model;

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


public class Main_messageJDBCDAO implements Main_messageDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO main_message (mainmsg_id,class_id,member_id,mainmsg_time,mainmsg_text,msg_source,msg_status) VALUES ('MM' || lpad(main_message_SEQ.NEXTVAL,5,'0'),?,?,?,?,?,?)";

	private static final String DELETE_main_message = "DELETE FROM main_message where mainmsg_id  = ?";
	private static final String DELETE_sub_message = "DELETE FROM sub_message where mainmsg_id  = ?";	
	
	private static final String GET_ALL_STMT = "SELECT mainmsg_id,class_id,member_id,mainmsg_time,mainmsg_text,msg_source,msg_status FROM main_message order by mainmsg_id";
	private static final String GET_ONE_STMT = "SELECT * FROM main_message where mainmsg_id = ?";
	
	private static final String GET_main_message_id_Bymain_message_STMT = ""
			+ "SELECT * "
			+ "FROM sub_message "
			+ "where msg_id  = ? "
			+ "order by submsg_id";
	
	private static final String UPDATE = "UPDATE main_message set class_id=?,member_id=?,mainmsg_time=?,mainmsg_text=?,msg_source=?,msg_status=? where mainmsg_id =?";

	
//    insert
	
	@Override
	public void insert(Main_messageVO main_messageVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
        try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, main_messageVO.getClass_id());
			pstmt.setString(2, main_messageVO.getMember_id());
			pstmt.setTimestamp(3, main_messageVO.getMainmsg_time());
			pstmt.setString(4, main_messageVO.getMainmsg_text());
			pstmt.setString(5, main_messageVO.getMsg_source());
			pstmt.setInt(6, main_messageVO.getMsg_status());
		

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
	}
	
//    update	
	
	@Override
	public void update(Main_messageVO main_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, main_messageVO.getClass_id());
			pstmt.setString(2, main_messageVO.getMember_id());
			pstmt.setTimestamp(3, main_messageVO.getMainmsg_time());
			pstmt.setString(4, main_messageVO.getMainmsg_text());
			pstmt.setString(5, main_messageVO.getMsg_source());
			pstmt.setInt(6, main_messageVO.getMsg_status());
			pstmt.setString(7, main_messageVO.getMainmsg_id());

			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	

	
// delete
	
	@Override
	public void delete(String mainmsg_id) {
		int updateCount_sub_message = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除小留言中的大留言ID
			pstmt = con.prepareStatement(DELETE_sub_message);
			pstmt.setString(1, mainmsg_id);
			updateCount_sub_message = pstmt.executeUpdate();
			// 再刪除大留言的ID
			pstmt = con.prepareStatement(DELETE_main_message);
			pstmt.setString(1, mainmsg_id);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除大留言id編號:" + mainmsg_id + "時,共有小留言" + updateCount_sub_message
					+ "則留言同時被刪除");
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	
	
//	findByPrimaryKey	
	
	@Override
	public Main_messageVO findByPrimaryKey(String mainmsg_id) {

		Main_messageVO main_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mainmsg_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				main_messageVO = new Main_messageVO();
				main_messageVO.setMainmsg_id(rs.getString("mainmsg_id"));
				main_messageVO.setClass_id(rs.getString("class_id"));
				main_messageVO.setMember_id(rs.getString("member_id"));
				main_messageVO.setMainmsg_time(rs.getTimestamp("mainmsg_time"));
				main_messageVO.setMainmsg_text(rs.getString("mainmsg_text"));
				main_messageVO.setMsg_source(rs.getString("msg_source"));
				main_messageVO.setMsg_status(rs.getInt("msg_status"));
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
		return main_messageVO;
	}
	
	
	
//     List
	
	@Override
	public List<Main_messageVO> getAll() {
		List<Main_messageVO> list = new ArrayList<Main_messageVO>();
		Main_messageVO main_messageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				main_messageVO = new Main_messageVO();
				main_messageVO.setMainmsg_id(rs.getString("mainmsg_id"));
				main_messageVO.setClass_id(rs.getString("class_id"));
				main_messageVO.setMember_id(rs.getString("member_id"));
				main_messageVO.setMainmsg_time(rs.getTimestamp("mainmsg_time"));
				main_messageVO.setMainmsg_text(rs.getString("mainmsg_text"));
				main_messageVO.setMsg_source(rs.getString("msg_source"));
				main_messageVO.setMsg_status(rs.getInt("msg_status"));
				list.add(main_messageVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

		Main_messageJDBCDAO dao = new Main_messageJDBCDAO();
		
	
		
		
		//新增
//		Main_messageVO main_messageVO = new Main_messageVO();
//		main_messageVO.setClass_id("CLA00011");
//		main_messageVO.setMember_id("MEM00011");
//		main_messageVO.setMainmsg_time(java.sql.Timestamp.valueOf("2020-12-02 16:31:00"));
//		main_messageVO.setMainmsg_text("題目好難");
//		main_messageVO.setMsg_source("學生");
//		main_messageVO.setMsg_status(1);
//		
//		dao.insert(main_messageVO);
	
		
		
		// 修改update
//		Main_messageVO main_messageVO = new Main_messageVO();
//		main_messageVO.setClass_id("CLA00010");
//		main_messageVO.setMember_id("MEM00010");
//		main_messageVO.setMainmsg_time(java.sql.Timestamp.valueOf("2020-12-02 18:34:00"));
//		main_messageVO.setMainmsg_text("題目怎麼這麼難");
//		main_messageVO.setMsg_source("學生");
//		main_messageVO.setMsg_status(1);
//		main_messageVO.setMainmsg_id("MM00010");
//		dao.update(main_messageVO);
	
		
		//刪除
//		dao.delete("MM00010");
	
		
		
		//查詢部門
		
		List<Main_messageVO> list = dao.getAll();
		for (Main_messageVO main_messageVO : list) {
			System.out.print(main_messageVO.getMainmsg_id()+ ",");
			System.out.print(main_messageVO.getClass_id() + ",");
			System.out.print(main_messageVO.getMember_id() + ",");
			System.out.print(main_messageVO.getMainmsg_time() + ",");
			System.out.print(main_messageVO.getMainmsg_text() + ",");
			System.out.print(main_messageVO.getMsg_source() + ",");
			System.out.print(main_messageVO.getMsg_status());
			
			
			System.out.println();
		}
		
//		 查詢
		Main_messageVO main_messageVO = dao.findByPrimaryKey("MM00001");
		System.out.print(main_messageVO.getMainmsg_id()+ ",");
		System.out.print(main_messageVO.getClass_id() + ",");
		System.out.print(main_messageVO.getMember_id() + ",");
		System.out.print(main_messageVO.getMainmsg_time() + ",");
		System.out.print(main_messageVO.getMainmsg_text() + ",");
		System.out.print(main_messageVO.getMsg_source() + ",");
		System.out.print(main_messageVO.getMsg_status());
		System.out.println("---------------------");	

		
			
	}

}

	

	