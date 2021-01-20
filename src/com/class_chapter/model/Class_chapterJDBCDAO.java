package com.class_chapter.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.class_unit.model.Class_unitJDBCDAO;
import com.class_unit.model.Class_unitVO;

public class Class_chapterJDBCDAO implements Class_chapterDAO_interface {
	private static final String INSERT = "INSERT INTO CLASS_CHAPTER VALUES('CLC'||lPAD(CLASS_CHAPTER_SEQ.NEXTVAL,5,'0'),?,?)";
	private static final String UPDATE = "UPDATE CLASS_CHAPTER SET CLASS_ID= ?,CHAPTER_NAME = ? WHERE CHAPTER_ID= ?";
	private static final String DELETE = "DELETE FROM CLASS_CHAPTER WHERE CHAPTER_ID= ?";
	private static final String SELECT = "SELECT * FROM CLASS_CHAPTER WHERE CHAPTER_ID= ?";
	private static final String GET_ALL = "SELECT * FROM CLASS_CHAPTER";
	private static final String GET_ALL_CLASS_ID = "SELECT * FROM CLASS_CHAPTER  WHERE CLASS_ID= ? ";
	private static final String CLASS_ID_GET_ALL_UNIT = "SELECT A.CHAPTER_ID,A.CLASS_ID,A.CHAPTER_NAME,B.UNIT_ID,B.UNIT_NAME,B.VIDEO,B.VIDEO_LONG,B.VIDEO_UPDATETIME,B.VIDEO_STATUS FROM CLASS_CHAPTER A INNER JOIN CLASS_UNIT B ON A.CHAPTER_ID=B.CHAPTER_ID WHERE A.CLASS_ID= ? ";
	
	
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


	@Override
	public void inserwithunit(Class_chapterVO class_chapterVO, List<Class_unitVO> list)  {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			con.setAutoCommit(false);
			String cols[] = { "chapter_ID" };

			pstmt = con.prepareStatement(INSERT, cols);
			pstmt.setString(1, class_chapterVO.getClass_id());
			pstmt.setString(2, class_chapterVO.getChapter_name());
			pstmt.executeUpdate();
			String next_Classunit = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_Classunit = rs.getString(1);
			} else {
				System.out.println("�����o�D��");
			}
			rs.close();
			Class_unitJDBCDAO dao = new Class_unitJDBCDAO();
			for (Class_unitVO unit : list) {
				unit.setChapter_id(next_Classunit);
				dao.inser2(unit, con);
			}
			con.commit();
			con.setAutoCommit(true);
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-��-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public List<Class_chapterVO> getAll(Map<String, String[]> map) {
		List<Class_chapterVO> list = new ArrayList<Class_chapterVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		String sql = "SELECT * FROM CLASS_CHAPTER" + get_WhereCondition(map) + " ORDER BY CHAPTER_ID";
		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Class_chapterVO class_chapterVO = new Class_chapterVO();
				class_chapterVO.setChapter_id(rs.getString("CHAPTER_ID"));
				class_chapterVO.setClass_id(rs.getString("CLASS_ID"));
				class_chapterVO.setChapter_name(rs.getString("CHAPTER_NAME"));
				list.add(class_chapterVO);
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
	//�ޤJMap servlet �Q��request.getParameterMap();  ���� �ҥH����Map�����O<String, String[]>
	public static String get_WhereCondition(Map<String, String[]> map) {
		//��map�Ҧ���key
		Set<String> keys = map.keySet();
		//StringBuffer �إߥi�H���Y���r��
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		//���X�Ҧ���key
		for (String key : keys) {
			//�Q��key���^�Ҧ���value
																					//������[0] checkbox��L��T????
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {    //action�O�̫�@��name
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim()); //���W��=form��檺name  +  ��J�����e
				
				//�Ĥ@���e���nwhere ��L�� �e���nand
				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
			}
		}

		return whereCondition.toString();
	}
													//���W��=form��檺name  +  ��J�����e
	public static String get_aCondition_For_Oracle(String columnName, String value) {
		String aCondition =null;
		//�P�_�Ƕi�h���Ȱ����P���B�z
		//�S���j�p���� ID + ���A
		if ("chapter_id".equals(columnName) || "class_id".equals(columnName)  )     //�Ʀr��̬۵�
			aCondition = columnName + "='" + value+"'";
		// �r�곡���ŦXlike
		else if ("chapter_name".equals(columnName) || "mainClass_name".equals(columnName)) 
			aCondition = columnName + " like '%" + value + "%'";
		
		return aCondition + " ";
	}

	@Override
	public void insert(Class_chapterVO class_chapterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, class_chapterVO.getClass_id());
			pstmt.setString(2, class_chapterVO.getChapter_name());
			
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
	public void update(Class_chapterVO class_chapterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, class_chapterVO.getClass_id());
			pstmt.setString(2, class_chapterVO.getChapter_name());
			pstmt.setString(3, class_chapterVO.getChapter_id());
			
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
	public void delete(String chapter_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, chapter_id);

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
	public Class_chapterVO select(String chapter_id) {
		Class_chapterVO class_chapterVO = new Class_chapterVO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT);

			pstmt.setString(1, chapter_id);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				class_chapterVO.setChapter_id(rs.getString("CHAPTER_ID"));
				class_chapterVO.setClass_id(rs.getString("CLASS_ID"));
				class_chapterVO.setChapter_name(rs.getString("CHAPTER_NAME"));
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
		return class_chapterVO;
	}
	
	@Override
	public List<Class_chapterVO> getAll() {
		List<Class_chapterVO> list = new ArrayList<Class_chapterVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Class_chapterVO class_chapterVO = new Class_chapterVO();
				class_chapterVO.setChapter_id(rs.getString("CHAPTER_ID"));
				class_chapterVO.setClass_id(rs.getString("CLASS_ID"));
				class_chapterVO.setChapter_name(rs.getString("CHAPTER_NAME"));
				list.add(class_chapterVO);
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
	public List<Class_chapterVO> getClassAll(String class_id) {
		List<Class_chapterVO> list = new ArrayList<Class_chapterVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_CLASS_ID);
			pstmt.setString(1, class_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Class_chapterVO class_chapterVO = new Class_chapterVO();
				class_chapterVO.setChapter_id(rs.getString("CHAPTER_ID"));
				class_chapterVO.setClass_id(rs.getString("CLASS_ID"));
				class_chapterVO.setChapter_name(rs.getString("CHAPTER_NAME"));
				list.add(class_chapterVO);
			}
			// Handle any driver errors
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
	public List<Class_chapterVO2> getChapterUnit(String class_id) {
		List<Class_chapterVO2> list = new ArrayList<Class_chapterVO2>();
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(CLASS_ID_GET_ALL_UNIT);
			pstmt.setString(1, class_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Class_chapterVO2 class_chapterVO2 = new Class_chapterVO2();
				class_chapterVO2.setChapter_id(rs.getString("CHAPTER_ID"));
				class_chapterVO2.setClass_id(rs.getString("CLASS_ID"));
				class_chapterVO2.setChapter_name(rs.getString("CHAPTER_NAME"));
				class_chapterVO2.setUnit_id(rs.getString("UNIT_ID"));
				class_chapterVO2.setUnit_name(rs.getString("UNIT_NAME"));
				class_chapterVO2.setVideo(rs.getBytes("VIDEO"));
				class_chapterVO2.setVideo_long(rs.getString("VIDEO_LONG"));
				class_chapterVO2.setVideo_updatetime(rs.getTimestamp("VIDEO_UPDATETIME"));
				class_chapterVO2.setVideo_status(rs.getInt("VIDEO_STATUS"));
				list.add(class_chapterVO2);
			}
			// Handle any driver errors
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
	
	@Override //����@�ҵ{�Ҧ����`���
	public Map<Class_chapterVO, List<Class_unitVO>> getClassChapter(String class_id) {
		Map<Class_chapterVO, List<Class_unitVO>> map = new TreeMap<Class_chapterVO, List<Class_unitVO>>();
		
		Class_unitVO vo2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(CLASS_ID_GET_ALL_UNIT);
			pstmt.setString(1, class_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Class_chapterVO vo = new Class_chapterVO();
				vo.setChapter_id(rs.getString("CHAPTER_ID"));
				vo.setClass_id(rs.getString("CLASS_ID"));
				vo.setChapter_name(rs.getString("CHAPTER_NAME"));

				
				if(map.get(vo)==null) {
					List<Class_unitVO> list = new ArrayList<Class_unitVO>();
					vo2 = new Class_unitVO();
					vo.setChapter_id(rs.getString("CHAPTER_ID"));
					vo2.setUnit_id(rs.getString("UNIT_ID"));
					vo2.setUnit_name(rs.getString("UNIT_NAME"));
					vo2.setVideo(rs.getBytes("VIDEO"));
					vo2.setVideo_long(rs.getString("VIDEO_LONG"));
					vo2.setVideo_updatetime(rs.getTimestamp("VIDEO_UPDATETIME"));
					vo2.setVideo_status(rs.getInt("VIDEO_STATUS"));
					list.add(vo2);
					map.put(vo,list);
				}else {
					vo2 = new Class_unitVO();
					vo.setChapter_id(rs.getString("CHAPTER_ID"));
					vo2.setUnit_id(rs.getString("UNIT_ID"));
					vo2.setUnit_name(rs.getString("UNIT_NAME"));
					vo2.setVideo(rs.getBytes("VIDEO"));
					vo2.setVideo_long(rs.getString("VIDEO_LONG"));
					vo2.setVideo_updatetime(rs.getTimestamp("VIDEO_UPDATETIME"));
					vo2.setVideo_status(rs.getInt("VIDEO_STATUS"));
					map.get(vo).add(vo2);
				}
				
			}
			
			
			
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
		return map;
	}
	
}
