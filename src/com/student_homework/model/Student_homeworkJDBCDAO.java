package com.student_homework.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.eclipse.jdt.internal.compiler.batch.Main;


public class Student_homeworkJDBCDAO implements Student_homeworkDAO_interface {
	private static final String INSERT = "INSERT INTO STUDENT_HOMEWORK VALUES('SH'||lPAD(STUDENT_HOMEWORK_SEQ.NEXTVAL,5,'0'),?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE STUDENT_HOMEWORK SET TEACHERHW_ID= ?,MEMBER_ID= ?,HW_NAME= ?,HW_CONTENT= ?,FILE_DATA= ?,HW_UPLOADTIME= ?,HW_UPDATETIME= ? WHERE STUDENTHW_ID = ?";
	private static final String DELETE = "DELETE FROM STUDENT_HOMEWORK WHERE STUDENTHW_ID = ?";
	private static final String SELECT = "SELECT * FROM STUDENT_HOMEWORK WHERE STUDENTHW_ID = ?";
	private static final String selectByTrhwId = "SELECT * FROM STUDENT_HOMEWORK WHERE TEACHERHW_ID = ?";
	private static final String GET_ALL = "SELECT * FROM STUDENT_HOMEWORK ORDER BY STUDENTHW_ID";
	private static final String getAll_orderByUpdate = "SELECT * FROM STUDENT_HOMEWORK ORDER BY HW_UPDATETIME DESC";
	private static final String worklist = "select studenthw_id, st.teacherhw_id, mem.member_id,mem.member_homework, st.hw_name, st.hw_content, st.file_data, st.hw_updatetime, st.hw_uploadtime, tr.hw_name, mem.member_name, tr.file_data, member_pic, member_homework \r\n" + 
			"			from student_homework st\r\n" + 
			"			join member_info mem on mem.member_id=st.member_id\r\n" + 
			"			join teacher_homework tr on tr.teacherhw_id=st.teacherhw_id\r\n" + 
			"			join class_unit cu on cu.unit_id=tr.unit_id\r\n" + 
			"			join class_chapter cc on cc.chapter_id=cu.chapter_id\r\n" + 
			"			where cc.class_id= ?\r\n" + 
			"			ORDER BY st.hw_updatetime DESC";
	
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
	public void insert(Student_homeworkVO student_homeworkVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
            
			pstmt.setString(1, student_homeworkVO.getTeacherhw_id());
			pstmt.setString(2, student_homeworkVO.getMember_id());
			pstmt.setString(3, student_homeworkVO.getHw_name());
			pstmt.setString(4, student_homeworkVO.getHw_content());
			pstmt.setBytes(5, student_homeworkVO.getFile_data());
			pstmt.setTimestamp(6, student_homeworkVO.getHw_uploadtime());
			pstmt.setTimestamp(7, student_homeworkVO.getHw_updatetime());
			
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
	public void update(Student_homeworkVO student_homeworkVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, student_homeworkVO.getTeacherhw_id());
			pstmt.setString(2, student_homeworkVO.getMember_id());
			pstmt.setString(3, student_homeworkVO.getHw_name());
			pstmt.setString(4, student_homeworkVO.getHw_content());
			pstmt.setBytes(5, student_homeworkVO.getFile_data());
			pstmt.setTimestamp(6, student_homeworkVO.getHw_uploadtime());
			pstmt.setTimestamp(7, student_homeworkVO.getHw_updatetime());
			pstmt.setString(8,student_homeworkVO.getStudenthw_id());
			
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
	public void delete(String studenthw_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, studenthw_id);

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
	public Student_homeworkVO select(String studenthw_id) {
		Student_homeworkVO student_homeworkVO = new Student_homeworkVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT);
			pstmt.setString(1, studenthw_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				student_homeworkVO.setStudenthw_id(rs.getString("STUDENTHW_ID"));
				student_homeworkVO.setTeacherhw_id(rs.getString("TEACHERHW_ID"));
				student_homeworkVO.setMember_id(rs.getString("MEMBER_ID"));
				student_homeworkVO.setHw_name(rs.getString("HW_NAME"));
				student_homeworkVO.setHw_content(rs.getString("HW_CONTENT"));
				student_homeworkVO.setFile_data(rs.getBytes("FILE_DATA"));
				student_homeworkVO.setHw_uploadtime(rs.getTimestamp("HW_UPLOADTIME"));
				student_homeworkVO.setHw_updatetime(rs.getTimestamp("HW_UPDATETIME"));
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
		return student_homeworkVO;
	}
	@Override
	public List<Student_homeworkVO> selectByTrhwId(String teacherhw_id) {
		List<Student_homeworkVO> list = new ArrayList<Student_homeworkVO>();
		Student_homeworkVO student_homeworkVO = new Student_homeworkVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(selectByTrhwId);
			pstmt.setString(1, teacherhw_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				student_homeworkVO.setStudenthw_id(rs.getString("STUDENTHW_ID"));
				student_homeworkVO.setTeacherhw_id(rs.getString("TEACHERHW_ID"));
				student_homeworkVO.setMember_id(rs.getString("MEMBER_ID"));
				student_homeworkVO.setHw_name(rs.getString("HW_NAME"));
				student_homeworkVO.setHw_content(rs.getString("HW_CONTENT"));
				student_homeworkVO.setFile_data(rs.getBytes("FILE_DATA"));
				student_homeworkVO.setHw_uploadtime(rs.getTimestamp("HW_UPLOADTIME"));
				student_homeworkVO.setHw_updatetime(rs.getTimestamp("HW_UPDATETIME"));
				list.add(student_homeworkVO);
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
	public List<Student_homeworkVO> getAll() {
		List<Student_homeworkVO> list = new ArrayList<Student_homeworkVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Student_homeworkVO student_homeworkVO = new Student_homeworkVO();
				student_homeworkVO.setStudenthw_id(rs.getString("STUDENTHW_ID"));
				student_homeworkVO.setTeacherhw_id(rs.getString("TEACHERHW_ID"));
				student_homeworkVO.setMember_id(rs.getString("MEMBER_ID"));
				student_homeworkVO.setHw_name(rs.getString("HW_NAME"));
				student_homeworkVO.setHw_content(rs.getString("HW_CONTENT"));
				student_homeworkVO.setFile_data(rs.getBytes("FILE_DATA"));
				student_homeworkVO.setHw_uploadtime(rs.getTimestamp("HW_UPLOADTIME"));
				student_homeworkVO.setHw_updatetime(rs.getTimestamp("HW_UPDATETIME"));
				list.add(student_homeworkVO);
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
	public List<Map<String,Object>> worklist(String class_id) {
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(worklist);
			pstmt.setString(1, class_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> temp_map = new HashMap<String, Object>();
				temp_map.put("studenthw_id", rs.getString("studenthw_id"));
				temp_map.put("st_hw_name",rs.getString("hw_name"));
				temp_map.put("st_hw_content",rs.getString("hw_content"));
				temp_map.put("st_file_data",rs.getBytes("file_data"));
				temp_map.put("st_hw_updatetime",rs.getTimestamp("hw_updatetime"));
				temp_map.put("st_hw_uploadtime",rs.getTimestamp("hw_uploadtime"));
				temp_map.put("teacherhw_id",rs.getString("teacherhw_id"));
				temp_map.put("tr_hw_name",rs.getString("hw_name"));
				temp_map.put("tr_file_data",rs.getBytes("file_data"));
				temp_map.put("member_id",rs.getString("member_id"));
				temp_map.put("member_homework",rs.getString("member_homework"));
				temp_map.put("member_name",rs.getString("member_name"));
				temp_map.put("member_pic",rs.getBytes("member_pic"));
				temp_map.put("member_homework",rs.getInt("member_homework"));
				list.add(temp_map);
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
	public List<Student_homeworkVO> getAll_orderByUpdate() {
		List<Student_homeworkVO> list = new ArrayList<Student_homeworkVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(getAll_orderByUpdate);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Student_homeworkVO student_homeworkVO = new Student_homeworkVO();
				student_homeworkVO.setStudenthw_id(rs.getString("STUDENTHW_ID"));
				student_homeworkVO.setTeacherhw_id(rs.getString("TEACHERHW_ID"));
				student_homeworkVO.setMember_id(rs.getString("MEMBER_ID"));
				student_homeworkVO.setHw_name(rs.getString("HW_NAME"));
				student_homeworkVO.setHw_content(rs.getString("HW_CONTENT"));
				student_homeworkVO.setFile_data(rs.getBytes("FILE_DATA"));
				student_homeworkVO.setHw_uploadtime(rs.getTimestamp("HW_UPLOADTIME"));
				student_homeworkVO.setHw_updatetime(rs.getTimestamp("HW_UPDATETIME"));
				list.add(student_homeworkVO);
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
