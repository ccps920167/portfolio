package com.member_interest.model;

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

public class Member_interestDAOJDBC implements Member_interestDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO member_interest VALUES ('MEBIN' || lpad(MEBIN_SEQ.NEXTVAL,5, '0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT interest_id , member_id , subclass_id , interest_status FROM member_interest";
	private static final String GET_ONE_STMT = "SELECT interest_id , member_id , subclass_id , interest_status FROM member_interest where interest_id = ?";
	private static final String GET_ONEBYMEMBERID_STMT = "SELECT interest_id , member_id , subclass_id , interest_status  FROM member_interest where member_id = ?";
	private static final String DELETE_STMT = "DELETE FROM member_interest where interest_id = ?";
//	private static final String DELETE_DEPT = "DELETE FROM dept2 where deptno = ?";	
	private static final String UPDATE = "UPDATE member_interest set member_id=?, subclass_id=? , interest_status=? where interest_id = ?";
	
	@Override
	 public void deletebySubclass(String member_id,String subclass_id) {
	  Connection con = null;
	  PreparedStatement pstmt = null;

	  try {
	   System.out.println(member_id);
	   System.out.println(subclass_id);
	   String sql="DELETE FROM MEMBER_INTEREST WHERE MEMBER_ID=? AND SUBCLASS_ID=?";
	   con = ds.getConnection();
	   pstmt = con.prepareStatement(sql);

	   pstmt.setString(1, member_id);
	   pstmt.setString(2, subclass_id);
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

	@Override
	public void insert(Member_interestVO member_interestVO1) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, member_interestVO1.getMember_id());
			pstmt.setString(2, member_interestVO1.getSubclass_id());
			pstmt.setInt(3, member_interestVO1.getInterest_status());
			pstmt.executeUpdate();

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
	public void update(Member_interestVO member_interestVO3) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(4, member_interestVO3.getInterest_id());
			pstmt.setString(1, member_interestVO3.getMember_id());
			pstmt.setString(2, member_interestVO3.getSubclass_id());
			pstmt.setInt(3, member_interestVO3.getInterest_status());
			pstmt.executeUpdate();

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
	public void delete(String interset_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, interset_id);

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


	@Override
	public Member_interestVO findByPrimaryKey(String interest_id) {

		Member_interestVO member_interestVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, interest_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// member_interestVO 也稱為 Domain objects
				member_interestVO = new Member_interestVO();
				member_interestVO.setInterest_id(rs.getString("interest_id"));
				member_interestVO.setMember_id(rs.getString("member_id"));
				member_interestVO.setSubclass_id(rs.getString("subclass_id"));
				member_interestVO.setInterest_status(rs.getInt("interest_status"));
			}

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
		return member_interestVO;

	}

	@Override
	public List<Member_interestVO> findBymember_id(String member_id) {

		List<Member_interestVO> list = new ArrayList<Member_interestVO>();
	
		Member_interestVO member_interestVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONEBYMEMBERID_STMT);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// member_interestVO 也稱為 Domain objects
				member_interestVO = new Member_interestVO();
				member_interestVO.setInterest_id(rs.getString("interest_id"));
				member_interestVO.setMember_id(rs.getString("member_id"));
				member_interestVO.setSubclass_id(rs.getString("subclass_id"));
				member_interestVO.setInterest_status(rs.getInt("interest_status"));
				list.add(member_interestVO);
			}

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
		return list;

	}
	@Override
	public List<Member_interestVO> getAll() {
		List<Member_interestVO> list = new ArrayList<Member_interestVO>();
		Member_interestVO member_interestVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection(); 	
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				member_interestVO = new Member_interestVO();
				member_interestVO.setInterest_id(rs.getString("interest_id"));
				member_interestVO.setMember_id(rs.getString("member_id"));
				member_interestVO.setSubclass_id(rs.getString("subclass_id"));
				member_interestVO.setInterest_status(rs.getInt("interest_status"));
				list.add(member_interestVO); // Store the row in the list

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

		Member_interestDAOJDBC dao = new Member_interestDAOJDBC();
		// insert
		Member_interestVO member_interestVO1 = new Member_interestVO();
		member_interestVO1.setMember_id("MEM00012");
		member_interestVO1.setSubclass_id("SCI00022");
		member_interestVO1.setInterest_status(1);
		dao.insert(member_interestVO1); 

		// List getAll()
//		List<Member_interestVO> list = dao.getAll();
//		for (Member_interestVO aInterest : list) {
//			System.out.print(aInterest.getInterest_id() + ",");
//			System.out.print(aInterest.getMember_id() + ",");
//			System.out.print(aInterest.getSubclass_id() + ",");
//			System.out.print(aInterest.getInterest_status());
//			System.out.println();

		// get one
//		Member_interestVO member_interestVO2 = dao.findByPrimaryKey("MEBIN00001");
//		System.out.print(member_interestVO2.getInterest_id() + ",");
//		System.out.print(member_interestVO2.getMember_id() + ",");
//		System.out.print(member_interestVO2.getSubclass_id() + ",");
//		System.out.println(member_interestVO2.getInterest_status());
//		System.out.println("---------------------");

		// update
//		Member_interestVO member_interestVO3 = new Member_interestVO();
//		member_interestVO3.setInterest_id("MEBIN00011");
//		member_interestVO3.setMember_id("MEM00011");
//		member_interestVO3.setSubclass_id("SCI00020");
//		member_interestVO3.setInterest_status(0);
//		dao.update(member_interestVO3);
		
		//Delete
//		dao.delete("MEBIN00011");

	}


		
}
