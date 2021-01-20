package com.teacher_homework.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Teacher_homeworkJDBCDAO implements Teacher_homeworkDAO_interface {
	private static final String INSERT = "INSERT INTO TEACHER_HOMEWORK VALUES('TH'||lPAD(TEACHER_HOMEWORK_SEQ.NEXTVAL,5,'0'),?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE TEACHER_HOMEWORK SET UNIT_ID= ?,HW_NAME= ?,HW_CONTENT= ?,FILE_DATA= ?,HW_UPLOADTIME= ?,HW_UPDATETIME= ? WHERE TEACHERHW_ID= ?";
	private static final String DELETE = "DELETE FROM TEACHER_HOMEWORK WHERE TEACHERHW_ID= ?";
	private static final String SELECT = "SELECT * FROM TEACHER_HOMEWORK WHERE TEACHERHW_ID= ?";
	private static final String GET_ALL = "SELECT * FROM TEACHER_HOMEWORK";
	private static final String selectByClassId = "SELECT teacherhw_id, cu.unit_id, cc.chapter_id, tr.hw_name, cu.unit_name, cc.chapter_name, tr.hw_content, tr.file_data\r\n" + 
			"FROM TEACHER_HOMEWORK tr\r\n" + 
			"join class_unit cu on cu.unit_id= tr.unit_id\r\n" + 
			"join class_chapter cc on cc.chapter_id=cu.chapter_id\r\n" + 
			"join class_info ci on ci.class_id=cc.class_id\r\n" + 
			"where ci.class_id = ? \r\n" + 
			"order by cc.chapter_id, cu.unit_id";
	
	
	
	
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
	
	
	@Override
	public List<Map> selectByClassId(String class_id) {
		List<Map> list = new ArrayList<Map>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectByClassId);
			pstmt.setString(1, class_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("teacherhw_id", rs.getString("teacherhw_id"));
				map.put("unit_id", rs.getString("unit_id"));
				map.put("chapter_id", rs.getString("chapter_id"));
				map.put("hw_name", rs.getString("hw_name"));
				map.put("unit_name", rs.getString("unit_name"));
				map.put("chapter_name", rs.getString("chapter_name"));
				map.put("hw_content", rs.getString("hw_content"));
				map.put("file_data", rs.getBytes("file_data"));
				list.add(map);
			}
			// Handle any driver errors
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
		return list;
	}
	
	
	@Override
	 public List<StuTr_homeworkVO> getHwContent(String member_id) {
	  List<StuTr_homeworkVO> list = new ArrayList<StuTr_homeworkVO>();
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	 
	  try {
	   String sql="select TEACHER_HOMEWORK.HW_CONTENT, TEACHER_HOMEWORK.HW_NAME,STUDENT_HOMEWORK.FILE_DATA ,STUDENT_HOMEWORK.MEMBER_ID, STUDENT_HOMEWORK.STUDENTHW_ID from TEACHER_HOMEWORK inner join  STUDENT_HOMEWORK ON teacher_homework.teacherhw_id=STUDENT_HOMEWORK.teacherhw_id WHERE member_id='"+member_id+"'";
	   con = ds.getConnection();
	   pstmt = con.prepareStatement(sql);
	   rs = pstmt.executeQuery();
	   
	   while (rs.next()) {
	    StuTr_homeworkVO stutr_homeworkVO = new StuTr_homeworkVO();
	    
	    stutr_homeworkVO.setHw_content(rs.getString("HW_CONTENT"));
	    stutr_homeworkVO.setHw_name(rs.getString("HW_NAME"));
	    stutr_homeworkVO.setFile_data(rs.getBytes("FILE_DATA"));
	    stutr_homeworkVO.setMember_id(rs.getString("MEMBER_ID"));
	    stutr_homeworkVO.setStudenthw_id(rs.getString("STUDENTHW_ID"));
	    list.add(stutr_homeworkVO);
	   }
	   // Handle any driver errors
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
	  return list;
	 }
	
	
	
	@Override
	public void insert(Teacher_homeworkVO teacher_homeworkVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, teacher_homeworkVO.getUnit_id());
			pstmt.setString(2, teacher_homeworkVO.getHw_name());
			pstmt.setString(3, teacher_homeworkVO.getHw_content());
			pstmt.setBytes(4, teacher_homeworkVO.getFile_data());
			pstmt.setTimestamp(5, teacher_homeworkVO.getHw_uploadtime());
			pstmt.setTimestamp(6, teacher_homeworkVO.getHw_updatetime());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void update(Teacher_homeworkVO teacher_homeworkVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, teacher_homeworkVO.getUnit_id());
			pstmt.setString(2, teacher_homeworkVO.getHw_name());
			pstmt.setString(3, teacher_homeworkVO.getHw_content());
			pstmt.setBytes(4, teacher_homeworkVO.getFile_data());
			pstmt.setTimestamp(5, teacher_homeworkVO.getHw_uploadtime());
			pstmt.setTimestamp(6, teacher_homeworkVO.getHw_updatetime());
			pstmt.setString(7, teacher_homeworkVO.getTeacherhw_id());
			
			pstmt.executeUpdate();
			// Handle any driver errors
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
	public void delete(String teacherhw_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, teacherhw_id);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public Teacher_homeworkVO select(String teacherhw_id) {
		Teacher_homeworkVO teacher_homeworkVO = new Teacher_homeworkVO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT);

			pstmt.setString(1, teacherhw_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				teacher_homeworkVO.setTeacherhw_id(rs.getString("TEACHERHW_ID"));
				teacher_homeworkVO.setUnit_id(rs.getString("UNIT_ID"));
				teacher_homeworkVO.setHw_name(rs.getString("HW_NAME"));
				teacher_homeworkVO.setHw_content(rs.getString("HW_CONTENT"));
				teacher_homeworkVO.setFile_data(rs.getBytes("FILE_DATA"));
				teacher_homeworkVO.setHw_uploadtime(rs.getTimestamp("HW_UPLOADTIME"));
				teacher_homeworkVO.setHw_updatetime(rs.getTimestamp("HW_UPDATETIME"));
			}
			// Handle any driver errors
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
		return teacher_homeworkVO;
	}
	@Override
	public List<Teacher_homeworkVO> getAll() {
		List<Teacher_homeworkVO> list = new ArrayList<Teacher_homeworkVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Teacher_homeworkVO teacher_homeworkVO = new Teacher_homeworkVO();
				teacher_homeworkVO.setTeacherhw_id(rs.getString("TEACHERHW_ID"));
				teacher_homeworkVO.setUnit_id(rs.getString("UNIT_ID"));
				teacher_homeworkVO.setHw_name(rs.getString("HW_NAME"));
				teacher_homeworkVO.setHw_content(rs.getString("HW_CONTENT"));
				teacher_homeworkVO.setFile_data(rs.getBytes("FILE_DATA"));
				teacher_homeworkVO.setHw_uploadtime(rs.getTimestamp("HW_UPLOADTIME"));
				teacher_homeworkVO.setHw_updatetime(rs.getTimestamp("HW_UPDATETIME")); 
				list.add(teacher_homeworkVO);
			}
			// Handle any driver errors
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
		return list;
	}
}
