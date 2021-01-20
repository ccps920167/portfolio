package com.member_info.model;

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


public class Member_infoJDBCDAO implements Member_infoDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO member_info  VALUES ('MEM' || lpad(MEM_SEQ.NEXTVAL,5, '0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT member_id,member_name,member_email,member_password,member_role,member_gender,member_birthday,member_occupation,member_address,member_invoice,member_pic,teachclass_on,learnclass_on,member_homework,member_about,member_good_for,register_time,member_update,traccount,bank_code FROM member_info order by member_id";
	private static final String GET_ONE_STMT = "SELECT member_id,member_name,member_email,member_password,member_role,member_gender,member_birthday,member_occupation,member_address,member_invoice,member_pic,teachclass_on,learnclass_on,member_homework,member_about,member_good_for,register_time,member_update,traccount,bank_code FROM member_info where member_id = ?";
	private static final String GET_LOGIN = "SELECT member_id,member_name,member_email,member_password,member_role,member_gender,member_birthday,member_occupation,member_address,member_invoice,member_pic,teachclass_on,learnclass_on,member_homework,member_about,member_good_for,register_time,member_update,traccount,bank_code FROM member_info where member_email = ?";
	private static final String DELETE = "DELETE FROM member_info where member_id = ?";
	private static final String UPDATE = "UPDATE member_info set member_name=?,member_email=?,member_password=?,member_role=?,member_gender=?,member_birthday=?,member_occupation=?,member_address=?,member_invoice=?,member_pic=?,teachclass_on=?,learnclass_on=?,member_homework=?,member_about=?,member_good_for=?,register_time=?,member_update=?,traccount=?,bank_code=? where member_id = ?";
	private static final String UPDATE_status = "UPDATE member_info set teachclass_on=?,learnclass_on=?,member_homework=? where member_id = ?";
	
	@Override //狀態更新
	 public void updatestatus(Member_infoVO member_infoVO) {
	  Connection con = null;
	  PreparedStatement pstmt = null;

	  try {

	   
	   con = ds.getConnection();
	   pstmt = con.prepareStatement(UPDATE_status);
	  
	   pstmt.setInt(1, member_infoVO.getTeachclass_on());
	   pstmt.setInt(2, member_infoVO.getLearnclass_on());
	   pstmt.setInt(3, member_infoVO.getMember_homework());
	   pstmt.setString(4, member_infoVO.getMember_id());
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
	public void insert(Member_infoVO member_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, member_infoVO.getMember_name());
			pstmt.setString(2, member_infoVO.getMember_email());
			pstmt.setString(3, member_infoVO.getMember_password());
			pstmt.setInt(4, member_infoVO.getMember_role());
			pstmt.setInt(5, member_infoVO.getMember_gender());
			pstmt.setDate(6, member_infoVO.getMember_birthday());
			pstmt.setString(7, member_infoVO.getMember_occupation());
			pstmt.setString(8, member_infoVO.getMember_address());
			pstmt.setString(9, member_infoVO.getMember_invoice());
			pstmt.setBytes(10, member_infoVO.getMember_pic());
			pstmt.setInt(11, member_infoVO.getTeachclass_on());
			pstmt.setInt(12, member_infoVO.getLearnclass_on());
			pstmt.setInt(13, member_infoVO.getMember_homework());
			pstmt.setString(14, member_infoVO.getMember_about());
			pstmt.setString(15, member_infoVO.getMember_good_for());
			pstmt.setTimestamp(16, member_infoVO.getRegister_time());
			pstmt.setTimestamp(17, member_infoVO.getMember_update());
			pstmt.setString(18, member_infoVO.getTraccount());
			pstmt.setString(19, member_infoVO.getBank_code());

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
	public void update(Member_infoVO member_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, member_infoVO.getMember_name());
			pstmt.setString(2, member_infoVO.getMember_email());
			pstmt.setString(3, member_infoVO.getMember_password());
			pstmt.setInt(4, member_infoVO.getMember_role());
			pstmt.setInt(5, member_infoVO.getMember_gender());
			pstmt.setDate(6, member_infoVO.getMember_birthday());
			pstmt.setString(7, member_infoVO.getMember_occupation());
			pstmt.setString(8, member_infoVO.getMember_address());
			pstmt.setString(9, member_infoVO.getMember_invoice());
			pstmt.setBytes(10, member_infoVO.getMember_pic());
			pstmt.setInt(11, member_infoVO.getTeachclass_on());
			pstmt.setInt(12, member_infoVO.getLearnclass_on());
			pstmt.setInt(13, member_infoVO.getMember_homework());
			pstmt.setString(14, member_infoVO.getMember_about());
			pstmt.setString(15, member_infoVO.getMember_good_for());
			pstmt.setTimestamp(16, member_infoVO.getRegister_time());
			pstmt.setTimestamp(17, member_infoVO.getMember_update());
			pstmt.setString(18, member_infoVO.getTraccount());
			pstmt.setString(19, member_infoVO.getBank_code());
			pstmt.setString(20, member_infoVO.getMember_id());
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
	public void delete(String member_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, member_id);

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
	public Member_infoVO findByPrimaryKey(String member_id) {
		Member_infoVO member_infoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member_infoVO = new Member_infoVO();
				member_infoVO.setMember_id(rs.getString("member_id"));
				member_infoVO.setMember_name(rs.getString("member_name"));
				member_infoVO.setMember_email(rs.getString("member_email"));
				member_infoVO.setMember_password(rs.getString("member_password"));
				member_infoVO.setMember_role(rs.getInt("member_role"));
				member_infoVO.setMember_gender(rs.getInt("member_gender"));
				member_infoVO.setMember_birthday(rs.getDate("member_birthday"));
				member_infoVO.setMember_occupation(rs.getString("member_occupation"));
				member_infoVO.setMember_address(rs.getString("member_address"));
				member_infoVO.setMember_invoice(rs.getString("member_invoice"));
				member_infoVO.setMember_pic(rs.getBytes("member_pic"));
				member_infoVO.setTeachclass_on(rs.getInt("teachclass_on"));
				member_infoVO.setLearnclass_on(rs.getInt("learnclass_on"));
				member_infoVO.setMember_homework(rs.getInt("member_homework"));
				member_infoVO.setMember_about(rs.getString("member_about"));
				member_infoVO.setMember_good_for(rs.getString("member_good_for"));
				member_infoVO.setRegister_time(rs.getTimestamp("Register_time"));
				member_infoVO.setMember_update(rs.getTimestamp("member_update"));
				member_infoVO.setTraccount(rs.getString("traccount"));
				member_infoVO.setBank_code(rs.getString("bank_code"));
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
		return member_infoVO;
	}

	@Override
	public List<Member_infoVO> getAll() {
		List<Member_infoVO> list = new ArrayList<Member_infoVO>();
		Member_infoVO member_infoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				member_infoVO = new Member_infoVO();
				member_infoVO.setMember_id(rs.getString("member_id"));
				member_infoVO.setMember_name(rs.getString("member_name"));
				member_infoVO.setMember_email(rs.getString("member_email"));
				member_infoVO.setMember_password(rs.getString("member_password"));
				member_infoVO.setMember_role(rs.getInt("member_role"));
				member_infoVO.setMember_gender(rs.getInt("member_gender"));
				member_infoVO.setMember_birthday(rs.getDate("member_birthday"));
				member_infoVO.setMember_occupation(rs.getString("member_occupation"));
				member_infoVO.setMember_address(rs.getString("member_address"));
				member_infoVO.setMember_invoice(rs.getString("member_invoice"));
				member_infoVO.setMember_pic(rs.getBytes("member_pic"));
				member_infoVO.setTeachclass_on(rs.getInt("teachclass_on"));
				member_infoVO.setLearnclass_on(rs.getInt("learnclass_on"));
				member_infoVO.setMember_homework(rs.getInt("member_homework"));
				member_infoVO.setMember_about(rs.getString("member_about"));
				member_infoVO.setMember_good_for(rs.getString("member_good_for"));
				member_infoVO.setRegister_time(rs.getTimestamp("register_time"));
				member_infoVO.setMember_update(rs.getTimestamp("member_update"));
				member_infoVO.setTraccount(rs.getString("traccount"));
				member_infoVO.setBank_code(rs.getString("bank_code"));
				list.add(member_infoVO);
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
	
	@Override
	public Member_infoVO findPasswordByMail(String member_email) {
		Member_infoVO member_infoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LOGIN);

			pstmt.setString(1, member_email);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member_infoVO = new Member_infoVO();
				member_infoVO.setMember_id(rs.getString("member_id"));
				member_infoVO.setMember_name(rs.getString("member_name"));
				member_infoVO.setMember_email(rs.getString("member_email"));
				member_infoVO.setMember_password(rs.getString("member_password"));
				member_infoVO.setMember_role(rs.getInt("member_role"));
				member_infoVO.setMember_gender(rs.getInt("member_gender"));
				member_infoVO.setMember_birthday(rs.getDate("member_birthday"));
				member_infoVO.setMember_occupation(rs.getString("member_occupation"));
				member_infoVO.setMember_address(rs.getString("member_address"));
				member_infoVO.setMember_invoice(rs.getString("member_invoice"));
				member_infoVO.setMember_pic(rs.getBytes("member_pic"));
				member_infoVO.setTeachclass_on(rs.getInt("teachclass_on"));
				member_infoVO.setLearnclass_on(rs.getInt("learnclass_on"));
				member_infoVO.setMember_homework(rs.getInt("member_homework"));
				member_infoVO.setMember_about(rs.getString("member_about"));
				member_infoVO.setMember_good_for(rs.getString("member_good_for"));
				member_infoVO.setRegister_time(rs.getTimestamp("Register_time"));
				member_infoVO.setMember_update(rs.getTimestamp("member_update"));
				member_infoVO.setTraccount(rs.getString("traccount"));
				member_infoVO.setBank_code(rs.getString("bank_code"));
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
		return member_infoVO;
	}

	public static void main(String[] args) {
		Member_infoJDBCDAO dao = new Member_infoJDBCDAO();

		// 新增
//		Member_infoVO member_infoVO1 = new Member_infoVO();
//		member_infoVO1.setMember_name("NICK");
//		member_infoVO1.setMember_email("XXXXXXX@gmail.com");
//		member_infoVO1.setMember_password("abc123");
//		member_infoVO1.setMember_role(new Integer(1));
//		member_infoVO1.setMember_gender(new Integer(0));
//		member_infoVO1.setMember_birthday(java.sql.Date.valueOf("1991-09-17"));
//		member_infoVO1.setMember_occupation("老師");
//		member_infoVO1.setMember_address("彰化縣員林市");
//		member_infoVO1.setMember_invoice("1010101010");
//		member_infoVO1.setMember_pic(null);
//		member_infoVO1.setTeachclass_on(new Integer(1));
//		member_infoVO1.setLearnclass_on(new Integer(1));
//		member_infoVO1.setMember_homework(new Integer(1));
//		member_infoVO1.setMember_about(null);
//		member_infoVO1.setMember_good_for(null);
//		member_infoVO1.setRegister_time(java.sql.Timestamp.valueOf("2020-11-27 16:48:50"));
//		member_infoVO1.setMember_update(java.sql.Timestamp.valueOf("2020-12-27 16:48:50"));
//		member_infoVO1.setTraccount("111111111111");
//		member_infoVO1.setBank_code("004");
//
//		dao.insert(member_infoVO1);

		// 修改
//		Member_infoVO member_infoVO2 = new Member_infoVO();
//		member_infoVO2.setMember_id("MEM00021");
//		member_infoVO2.setMember_name("pig");
//		member_infoVO2.setMember_email("OOOOOO@gmail.com");
//		member_infoVO2.setMember_password("abc123");
//		member_infoVO2.setMember_role(new Integer(1));
//		member_infoVO2.setMember_gender(new Integer(0));
//		member_infoVO2.setMember_birthday(java.sql.Date.valueOf("1991-09-17"));
//		member_infoVO2.setMember_occupation("老師");
//		member_infoVO2.setMember_address("彰化縣員林市");
//		member_infoVO2.setMember_invoice("1010101010");
//		member_infoVO2.setMember_pic(null);
//		member_infoVO2.setTeachclass_on(new Integer(1));
//		member_infoVO2.setLearnclass_on(new Integer(1));
//		member_infoVO2.setMember_homework(new Integer(1));
//		member_infoVO2.setMember_about(null);
//		member_infoVO2.setMember_good_for(null);
//		member_infoVO2.setRegister_time(java.sql.Timestamp.valueOf("2020-11-27 16:48:50"));
//		member_infoVO2.setMember_update(java.sql.Timestamp.valueOf("2020-12-27 16:48:50"));
//		member_infoVO2.setTraccount("111111111111");
//		member_infoVO2.setBank_code("004");
//		
//		dao.update(member_infoVO2);
		// 刪除
//		dao.delete("MEM00023");

		// 查詢
		Member_infoVO member_infoVO3 =dao.findByPrimaryKey("MEM00001");
		System.out.println(member_infoVO3.getMember_id()+",");
		System.out.println(member_infoVO3.getMember_name()+",");
		System.out.println(member_infoVO3.getMember_email()+",");
		System.out.println(member_infoVO3.getMember_password()+",");
		System.out.println(member_infoVO3.getMember_role()+",");
		System.out.println(member_infoVO3.getMember_gender()+",");
		System.out.println(member_infoVO3.getMember_birthday()+",");
		System.out.println(member_infoVO3.getMember_occupation()+",");
		System.out.println(member_infoVO3.getMember_address()+",");
		System.out.println(member_infoVO3.getMember_invoice()+",");
		System.out.println(member_infoVO3.getMember_pic()+",");
		System.out.println(member_infoVO3.getTeachclass_on()+",");
		System.out.println(member_infoVO3.getLearnclass_on()+",");
		System.out.println(member_infoVO3.getMember_homework()+",");
		System.out.println(member_infoVO3.getMember_about()+",");
		System.out.println(member_infoVO3.getMember_good_for()+",");
		System.out.println(member_infoVO3.getRegister_time()+",");
		System.out.println(member_infoVO3.getMember_update()+",");
		System.out.println(member_infoVO3.getTraccount()+",");
		System.out.println(member_infoVO3.getBank_code()+",");
		System.out.println("---------------------------------");

		// 查詢
//		List<Member_infoVO> list = dao.getAll();
//		for (Member_infoVO aMember_info : list) {
//			System.out.print(aMember_info.getMember_id() + ",");
//			System.out.print(aMember_info.getMember_name() + ",");
//			System.out.print(aMember_info.getMember_email() + ",");
//			System.out.print(aMember_info.getMember_password() + ",");
//			System.out.print(aMember_info.getMember_role() + ",");
//			System.out.print(aMember_info.getMember_gender() + ",");
//			System.out.print(aMember_info.getMember_birthday());
//			System.out.print(aMember_info.getMember_occupation() + ",");
//			System.out.print(aMember_info.getMember_address() + ",");
//			System.out.print(aMember_info.getMember_invoice() + ",");
//			System.out.print(aMember_info.getMember_pic() + ",");
//			System.out.print(aMember_info.getTeachclass_on() + ",");
//			System.out.print(aMember_info.getLearnclass_on() + ",");
//			System.out.print(aMember_info.getMember_homework() + ",");
//			System.out.print(aMember_info.getMember_about() + ",");
//			System.out.print(aMember_info.getMember_good_for() + ",");
//			System.out.print(aMember_info.getRegister_time() + ",");
//			System.out.print(aMember_info.getMember_update() + ",");
//			System.out.print(aMember_info.getTraccount() + ",");
//			System.out.print(aMember_info.getBank_code() + ",");
//	
//			
//			System.out.println();
//		}
	}

	

}
