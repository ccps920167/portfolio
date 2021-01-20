package com.post_message.model;

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



public class Post_messageJDBCDAO implements Post_messageDAO_interface {
	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TEA102G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO post_message (post_id,post_content,send_time,admin_id,target_type) VALUES ('PI' || lpad(post_message_SEQ.NEXTVAL,5,'0'),?,?,?,?)";

	private static final String DELETE_post_message = "DELETE FROM post_message where post_id  = ?";
		
	
	private static final String GET_ALL_STMT = "SELECT post_id,post_content,send_time,admin_id,target_type FROM post_message order by post_id";
	private static final String GET_ONE_STMT = "SELECT * FROM post_message where post_id = ?";
	
//	private static final String GET_admin_id_Byadmin_auth_STMT = ""
//			+ "SELECT * "
//			+ "FROM post_message "
//			+ "where admin_id  = ? "
//			+ "order by admin_id";
	
	private static final String UPDATE = "UPDATE post_message set post_content=?,send_time=?,admin_id=?,target_type=? where post_id =?";

	
	
//  insert
	@Override
	public void insert(Post_messageVO post_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
        try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, post_messageVO.getPost_content());
			pstmt.setTimestamp(2, post_messageVO.getSend_time());
			pstmt.setString(3, post_messageVO.getAdmin_id());
			pstmt.setInt(4, post_messageVO.getTarget_type());
	

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
	public void update(Post_messageVO post_messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, post_messageVO.getPost_content());
			pstmt.setTimestamp(2, post_messageVO.getSend_time());
			pstmt.setString(3, post_messageVO.getAdmin_id());
			pstmt.setInt(4, post_messageVO.getTarget_type());
			pstmt.setString(5, post_messageVO.getPost_id());
			
			
			

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
	public void delete(String post_id) {
		int updateCount_post_message = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			
			con = ds.getConnection();

			// 1���]�w�� pstm.executeUpdate()���e
			con.setAutoCommit(false);

			// 
			pstmt = con.prepareStatement(DELETE_post_message);
			pstmt.setString(1,post_id);
			updateCount_post_message = pstmt.executeUpdate();
			
			pstmt.executeUpdate();

			// 2���]�w�� pstm.executeUpdate()����
			con.commit();
			con.setAutoCommit(true);
			
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3���]�w���exception�o�ͮɤ�catch�϶���
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
	public Post_messageVO findByPrimaryKey(String post_id) {
		Post_messageVO post_messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, post_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO �]�٬� Domain objects
				post_messageVO = new Post_messageVO();
				post_messageVO.setPost_id (rs.getString("post_id"));
				post_messageVO.setPost_content(rs.getString("post_content"));
				post_messageVO.setSend_time(rs.getTimestamp("send_time"));
				post_messageVO.setAdmin_id(rs.getString("admin_id"));
				post_messageVO.setTarget_type(rs.getInt("target_type"));
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
		return post_messageVO;
	}

//    List
	@Override
	public List<Post_messageVO> getAll() {
		List<Post_messageVO> list = new ArrayList<Post_messageVO>();
		Post_messageVO post_messageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post_messageVO  = new Post_messageVO();
				post_messageVO.setPost_id (rs.getString("post_id"));
				post_messageVO.setPost_content(rs.getString("post_content"));
				post_messageVO.setSend_time(rs.getTimestamp("send_time"));
				post_messageVO.setAdmin_id(rs.getString("admin_id"));
				post_messageVO.setTarget_type(rs.getInt("target_type"));
				list.add(post_messageVO); // Store the row in the list
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

		Post_messageJDBCDAO dao = new Post_messageJDBCDAO();
		
		
		//�s�W
//		Post_messageVO post_messageVO = new Post_messageVO();
//
//		post_messageVO.setPost_content("�t�Ϧn§�u�f��");
//		post_messageVO.setSend_time(java.sql.Timestamp.valueOf("2020-12-05 01:50:00"));
//		post_messageVO.setAdmin_id("AI00004");
//		post_messageVO.setTarget_type("1");
//		
//		dao.insert(post_messageVO);
		
		

		// �ק�update
//		Post_messageVO dao1 = new Post_messageVO();
//		
//		
//		dao1.setPost_content("�t���u�f��");
//		dao1.setSend_time(java.sql.Timestamp.valueOf("2020-12-05 01:50:00"));
//		dao1.setAdmin_id("AI00004");
//		dao1.setTarget_type("1");
//		dao1.setPost_id("PI00006");
//		
//		dao.update(dao1);
//		
		//�R��
//		dao.delete("PI00010");
		

		//�d�ߥ������
		List<Post_messageVO> list = dao.getAll();
		
		for (Post_messageVO dao1 : list) {
			System.out.print(dao1.getPost_id()+ ",");
			System.out.print(dao1.getPost_content() + ",");
			System.out.print(dao1.getSend_time() + ",");
			System.out.print(dao1.getAdmin_id() + ",");
			System.out.print(dao1.getTarget_type() + ",");

			System.out.println("");
		}
		
		//�d�߳�@���
				Post_messageVO post_messageVO = dao.findByPrimaryKey("PI00001");
				
					System.out.print(post_messageVO.getPost_id()+ ",");
					System.out.print(post_messageVO.getPost_content() + ",");
					System.out.print(post_messageVO.getSend_time() + ",");
					System.out.print(post_messageVO.getAdmin_id() + ",");
					System.out.print(post_messageVO.getTarget_type() + ",");

					System.out.println("");
				
	 }
}
