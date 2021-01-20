package com.main_class.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.sub_class.model.Sub_classVO;

public class Main_classJDBCDAO implements Main_classDAO_interface {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private String userid = "TEA102G5";
	private String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO MAIN_CLASS VALUES('MCI'||LPAD(MAIN_CLASS_SEQ.NEXTVAL,5,'0'),?,?)";
	private static final String INSERT_NO_STATUS = "INSERT INTO MAIN_CLASS (MAINCLASS_ID,MAINCLASS_NAME)VALUES('MCI'||LPAD(MAIN_CLASS_SEQ.NEXTVAL,5,'0'),?)";
	private static final String INSERT_SUB_NO_STATUS = "INSERT INTO SUB_CLASS(SUBCLASS_ID,MAINCLASS_ID,SUBCLASS_NAME) VALUES('SCI'||LPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MAIN_CLASS ORDER BY MAINCLASS_id";
	private static final String GET_ONE_STMT = "SELECT * FROM MAIN_CLASS WHERE MAINCLASS_id = ?";
	private static final String GET_SUB_CLASS_BYMain_class_id_STMT = "SELECT * FROM SUB_CLASS WHERE MAINCLASS_id = ? ORDER BY SUBCLASS_id";

	private static final String DELETE_MAIN_CLASS = "DELETE FROM MAIN_CLASS where MAINCLASS_id = ?";
	private static final String DELETE_SUB_CLASS = "DELETE FROM SUB_CLASS where MAINCLASS_id = ?";
	private static final String UPDATE = "UPDATE MAIN_CLASS SET MAINCLASS_NAME	=?, MAINCLASS_STATUS=? WHERE MAINCLASS_id = ?";

	public void insert_main_sub(Main_classVO main_classVO, Sub_classVO[] sub_classVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);

			// 設定於 pstm.executeUpdate()之前
			conn.setAutoCommit(false);
			
			// 新增main
			pstmt = conn.prepareStatement(INSERT_NO_STATUS);
			pstmt.setString(1, main_classVO.getMainClass_name());
			pstmt.executeUpdate();
			
			
			
			// 新增sub
			for(Sub_classVO item :sub_classVO) {
				pstmt = conn.prepareStatement(INSERT_SUB_NO_STATUS);
				pstmt.setString(1, item.getMainClass_id());
				pstmt.setString(2, item.getSubClass_name());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {

		}
	}

	@Override
	public void insert(Main_classVO main_classVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, main_classVO.getMainClass_name());
			pstmt.setInt(2, main_classVO.getMainClass_status());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(Main_classVO main_classVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, main_classVO.getMainClass_name());
			pstmt.setInt(2, main_classVO.getMainClass_status());
			pstmt.setString(3, main_classVO.getMainClass_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(String main_class_id) {
		int updateCount_sub_class = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除子類別
			pstmt = con.prepareStatement(DELETE_SUB_CLASS);
			pstmt.setString(1, main_class_id);
			updateCount_sub_class = pstmt.executeUpdate();

			// 再刪除父類別
			pstmt = con.prepareStatement(DELETE_MAIN_CLASS);
			pstmt.setString(1, main_class_id);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除主課程類別編號" + main_class_id + "時,共有子類別" + updateCount_sub_class + "被刪除");

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
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
	public Main_classVO findByPrimaryKey(String main_class_id) {

		Main_classVO main_classVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, main_class_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				main_classVo = new Main_classVO();
				main_classVo.setMainClass_id(rs.getString("MAINCLASS_id"));
				main_classVo.setMainClass_name(rs.getString("MAINCLASS_NAME"));
				main_classVo.setMainClass_status(rs.getInt("MAINCLASS_STATUS"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return main_classVo;
	}

	@Override
	public List<Main_classVO> getAll() {
		List<Main_classVO> list = new ArrayList<Main_classVO>();
		Main_classVO Main_classVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Main_classVO = new Main_classVO();
				Main_classVO.setMainClass_id(rs.getString("MAINCLASS_ID"));
				Main_classVO.setMainClass_name(rs.getString("MAINCLASS_NAME"));
				Main_classVO.setMainClass_status(rs.getInt("MAINCLASS_STATUS"));
				list.add(Main_classVO); // Store the row in the list
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	@Override
	public Set<Sub_classVO> getSub_classByMainClass_ID(String mainClass_id) {
		Set<Sub_classVO> set = new LinkedHashSet<Sub_classVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Sub_classVO sub_classVo = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userid, passwd);
			pstmt = conn.prepareStatement(GET_SUB_CLASS_BYMain_class_id_STMT);
			pstmt.setString(1, mainClass_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sub_classVo = new Sub_classVO();
				sub_classVo.setSubClass_id(rs.getString("SUBCLASS_id"));
				sub_classVo.setSubClass_name(rs.getString("SUBCLASS_NAME"));
				sub_classVo.setSubClass_status(rs.getInt("SUBCLASS_STATUS"));
				sub_classVo.setMainClass_id(mainClass_id);
				set.add(sub_classVo);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return set;
	}

	// 測試
	public static void main(String[] args) {

		Main_classJDBCDAO dao = new Main_classJDBCDAO();

//		// 新增 OK
//		Main_classVO Main_classVO1 = new Main_classVO();
//		Main_classVO1.setMainClass_name("大分類測試");
//		Main_classVO1.setMainClass_status(1);
//		dao.insert(Main_classVO1);
//
//		// 修改 OK
//		Main_classVO Main_classVO2 = new Main_classVO();
//		Main_classVO2.setMainClass_id("MCI00007");
//		Main_classVO2.setMainClass_name("大分類測試2");
//		Main_classVO2.setMainClass_status(0);
//		dao.update(Main_classVO2);

//		// 刪除
//		dao.delete("MCI00007");

//		// 查詢
//		Main_classVO Main_classVO3 = dao.findByPrimaryKey("MCI00001");
//		System.out.println(Main_classVO3.getMainClass_id());
//		System.out.println(Main_classVO3.getMainClass_name());
//		if(Main_classVO3.getMainClass_status()==0) {
//			System.out.println("隱藏");
//		}else {
//			System.out.println("顯示");
//		}

//		// 查詢所有資料
//		List<Main_classVO> list = dao.getAll();
//		for (Main_classVO item : list) {
//			System.out.print(item.getMainClass_id());
//			System.out.print(item.getMainClass_name());
//			if (item.getMainClass_status() == 0) {
//				System.out.println("隱藏");
//			} else {
//				System.out.println("顯示");
//			}
//			System.out.println();
//		}

		// 查詢主類別內所有子類別
//		Set<Sub_classVO> set = dao.getSub_classByMainClass_ID("MCI00002");
//		for (Sub_classVO item : set) {
//			System.out.println(item.getSubClass_id());
//			System.out.println(item.getSubClass_name());
//			if (item.getSubClass_status() == 0) {
//				System.out.println("隱藏");
//			} else {
//				System.out.println("顯示");
//			}
//			System.out.println();
//		}
	}

	@Override
	public List<Main_classVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
}
