package com.keyword_form.model;

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


public class Keyword_formDAOJDBC implements Keyword_formDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO keyword_form VALUES ('KEYI' || lpad(KEYI_SEQ.NEXTVAL,5, '0'),?,?)";
	private static final String GET_ALL_STMT = "SELECT keyword_id , search_date , search_word FROM keyword_form order by keyword_id";
	private static final String GET_ONE_STMT = "SELECT keyword_id , search_date , search_word FROM keyword_form where keyword_id = ?";
	private static final String UPDATE = "UPDATE keyword_form set  search_date=? , search_word=? where keyword_id = ?";
	private static final String DELETE_STMT = "DELETE FROM keyword_form where keyword_id = ?";
	private static final String GET_BY_DATE = "SELECT keyword_id , search_date , search_word FROM keyword_form where search_date between "
			+ "to_date(?,'yyyy/mm/dd')and to_date(?,'yyyy/mm/dd')";
	private static final String GET_COUNTALL_KEYWORD = "SELECT COUNT(search_word) FROM keyword_form where search_word = ?";
	
	
	@Override
	public void delete(String keyword_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, keyword_id);

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
	public void insert(Keyword_formVO keyword_formVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setDate(1, keyword_formVO.getSearch_date());
			pstmt.setString(2, keyword_formVO.getSearch_word());
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
	public void update(Keyword_formVO keyword_formVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(3, keyword_formVO.getKeyword_id());
			pstmt.setDate(1, keyword_formVO.getSearch_date());
			pstmt.setString(2, keyword_formVO.getSearch_word());
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
	public Keyword_formVO findByPrimaryKey(String keyword_id) {
		Keyword_formVO keyword_formVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, keyword_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				keyword_formVO = new Keyword_formVO();
				keyword_formVO.setKeyword_id(rs.getString("keyword_id"));
				keyword_formVO.setSearch_date(rs.getDate("search_date"));
				keyword_formVO.setSearch_word(rs.getString("search_word"));
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
		return keyword_formVO;
	}
	@Override
	public List<Keyword_formVO> getAll() {
		List<Keyword_formVO> list = new ArrayList<Keyword_formVO>();
		Keyword_formVO keyword_formVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				keyword_formVO = new Keyword_formVO();
				keyword_formVO.setKeyword_id(rs.getString("keyword_id"));
				keyword_formVO.setSearch_date(rs.getDate("search_date"));
				keyword_formVO.setSearch_word(rs.getString("search_word"));
				list.add(keyword_formVO); // Store the row in the list

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



//===============================Count Keyword list Start====================================
	public List<Integer> getKeywordCountList(String search_word) {
//		Map<Integer> map = new HashMap<String,Integer>();
		List<Integer> list = new ArrayList<Integer>();
//		Keyword_formVO keyword_formVO = new Keyword_formVO();
//		String searchword = keyword;
		Integer keywordCount=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUNTALL_KEYWORD);

			pstmt.setString(1, search_word);

			rs = pstmt.executeQuery();

			while (rs.next()) {
//				Keyword_formVO keyword_formVO = new Keyword_formVO();
//				String searchword =keyword_formVO.getSearch_word();
//				System.out.println(search_word);
				keywordCount =(Integer)rs.getInt("count(search_word)");
//				System.out.println(rs.getInt("search_word"));
//				System.out.println(keywordCount);
			}
			list.add(keywordCount);
//			System.out.println(map);
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
	
	
//===============================Count Keyword list END  ====================================
//===============================Count Keyword Start====================================
	public int getKeywordCount(String search_word) {
//		Map<Integer> map = new HashMap<String,Integer>();
//		List<Integer> list = new ArrayList<Integer>();
//		Keyword_formVO keyword_formVO = new Keyword_formVO();
//		String searchword = keyword;
		int keywordCount=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_COUNTALL_KEYWORD);
			
			pstmt.setString(1, search_word);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				keywordCount =rs.getInt("count(search_word)");
//				System.out.println(rs.getInt("search_word"));
//				System.out.println(keywordCount);
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
		return keywordCount;
		
	}
	
	
//===============================Count Keyword END  ====================================
//===============================用日期區間查詢所有資料====================================
	@Override
	public List<Keyword_formVO> getKeywordbydate(String startdate, String enddate) {
		List<Keyword_formVO> list = new ArrayList<Keyword_formVO>();
		Keyword_formVO keyword_formVO = null;
				
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_DATE);

			pstmt.setString(1, startdate);
			pstmt.setString(2, enddate);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				keyword_formVO = new Keyword_formVO();
				keyword_formVO.setKeyword_id(rs.getString("keyword_id"));
				keyword_formVO.setSearch_date(rs.getDate("search_date"));
				keyword_formVO.setSearch_word(rs.getString("search_word"));
				list.add(keyword_formVO);
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

//	public static void main(String[] args) {
//		Keyword_formDAOJDBC dao = new Keyword_formDAOJDBC();
//		// insert
//		Keyword_formVO keyword_formVO1 = new Keyword_formVO();
//		keyword_formVO1.setMember_id("MEM00012");
//		keyword_formVO1.setSearch_date(java.sql.Date.valueOf("2020-11-10"));
//		keyword_formVO1.setSearch_word("java");
//		dao.insert(keyword_formVO1);

//		// update
//		Keyword_formVO keyword_formVO2 = new Keyword_formVO();
//		keyword_formVO2.setKeyword_id("KEYI00011");
//		keyword_formVO2.setMember_id("MEM00012");
//		keyword_formVO2.setSearch_date(java.sql.Date.valueOf("2020-11-20"));
//		keyword_formVO2.setSearch_word("Spring");
//		dao.update(keyword_formVO2);
//		
		// List getAll()
//		List<Keyword_formVO> list = dao.getAll();
//		for (Keyword_formVO aKeyword : list) {
//			System.out.print(aKeyword.getKeyword_id() + ",");
//			System.out.print(aKeyword.getMember_id() + ",");
//			System.out.print(aKeyword.getSearch_date() + ",");
//			System.out.print(aKeyword.getSearch_word());
//			System.out.println();
//
//			// get one
//			Keyword_formVO keyword_formVO4 = dao.findByPrimaryKey("KEYI00011");
//			System.out.print(keyword_formVO4.getKeyword_id() + ",");
//			System.out.print(keyword_formVO4.getMember_id() + ",");
//			System.out.print(keyword_formVO4.getSearch_date() + ",");
//			System.out.println(keyword_formVO4.getSearch_word());
//			System.out.println("---------------------");
//		}
//		Keyword_formDAOJDBC dao = new Keyword_formDAOJDBC();
//		Map<String,Integer> map =(Map<String,Integer>) dao.getKeywordCount("java");
//		
//		for(int count:map.values()) {
//			System.out.println(count);}
//			
//			for(String word:map.keySet()) {
//				System.out.println(word);}
//		}
	
//		Keyword_formDAOJDBC dao = new Keyword_formDAOJDBC();
//		int count = dao.getKeywordCount("java");
//		
//			System.out.println(count);
//			
//			for(String word:map.keySet()) {
//				System.out.println(word);}
//		}
		
	}

