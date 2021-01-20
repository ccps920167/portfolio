package com.sub_class.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//�R���ݽT�{
//�d�ߤl���O�ݽT�{

public class Sub_classDAO implements Sub_classDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO SUB_CLASS VALUES('SCI'||LPAD(SUB_CLASS_SEQ.NEXTVAL,5,'0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM SUB_CLASS ORDER BY SUBCLASS_id";
	private static final String GET_ONE_STMT = "SELECT * FROM SUB_CLASS WHERE SUBCLASS_id = ?";
	private static final String GET_CLASS_INFO_BySUB_CLASS_id_STMT = "SELECT * FROM CLASS_INFO WHERE SUBCLASS_id = ? ORDER BY CLASS_id";
	private static final String GET_MEMBER_INTEREST_BySUB_CLASS_id_STMT = "SELECT * FROM MEMBER_INTEREST WHERE SUBCLASS_id = ? ORDER BY CLASS_id";

	private static final String DELETE_SUB_CLASS = ""
			+ "DELETE FROM SUB_CLASS "
			+ "where SUBCLASS_id = ?";

	private static final String UPDATE = "UPDATE SUB_CLASS SET SUBCLASS_NAME=?, SUBCLASS_STATUS=? , MAINCLASS_id=? WHERE SUBCLASS_id = ?";

	@Override
	public void insert(Sub_classVO sub_classVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sub_classVO.getMainClass_id());
			pstmt.setString(2, sub_classVO.getSubClass_name());
			pstmt.setInt(3, sub_classVO.getSubClass_status());

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
	public void update(Sub_classVO sub_classVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con =  ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sub_classVO.getSubClass_name());
			pstmt.setInt(2, sub_classVO.getSubClass_status());
			pstmt.setString(3, sub_classVO.getMainClass_id());
			pstmt.setString(4, sub_classVO.getSubClass_id());

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
	public void delete(String sub_class_id) {
		int updateCount_class_info = 0;
		int updateCount_member_interest = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

		
			con =  ds.getConnection();

			pstmt = con.prepareStatement(DELETE_SUB_CLASS);
			pstmt.setString(1, sub_class_id);
			pstmt.executeUpdate();

			System.out.println(
					"�R���D�ҵ{���O�s��" + sub_class_id + "��,�@���ҵ{" 
					+ updateCount_class_info + "�Q�R��"
					+ updateCount_member_interest + "�Q�R��");

		} catch (SQLException se) {
			if (con != null) {
				try {
					con.close();
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
	public Sub_classVO findByPrimaryKey(String subclass_id) {

		Sub_classVO sub_classVo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con =  ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, subclass_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sub_classVo = new Sub_classVO();
				sub_classVo.setSubClass_id(rs.getString("SUBCLASS_id"));
				sub_classVo.setSubClass_name(rs.getString("SUBCLASS_NAME"));
				sub_classVo.setSubClass_status(rs.getInt("SUBCLASS_STATUS"));
				sub_classVo.setMainClass_id(rs.getString("MAINCLASS_id"));
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
		return sub_classVo;
	}

	@Override
	public List<Sub_classVO> getAll() {
		List<Sub_classVO> list = new ArrayList<Sub_classVO>();
		Sub_classVO sub_classVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con =  ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sub_classVO = new Sub_classVO();
				sub_classVO.setSubClass_id(rs.getString("SUBCLASS_id"));
				sub_classVO.setSubClass_name(rs.getString("SUBCLASS_NAME"));
				sub_classVO.setSubClass_status(rs.getInt("SUBCLASS_STATUS"));
				sub_classVO.setMainClass_id(rs.getString("MAINCLASS_id"));
				list.add(sub_classVO); // Store the row in the list
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

//@Override
//	public Set<Main_classVO> getSub_classByMain_class_id(String main_class_id) {
//		
//		return null;
//	}

	//����
	public static void main(String[] args) {

		Sub_classDAO dao = new Sub_classDAO();

//		// �s�W OK
//		Sub_classVO sub_classVO1 = new Sub_classVO();
//		sub_classVO1.setSubClass_name("�p��������");
//		sub_classVO1.setSubClass_status(1);
//		sub_classVO1.setMainClass_id("MCI00004");
//		dao.insert(sub_classVO1);
//
//
//		// �ק� OK
//		Sub_classVO sub_classVO2 = new Sub_classVO();
//		sub_classVO2.setSubClass_id("SCI00031");
//		sub_classVO2.setSubClass_name("�p��������3");
//		sub_classVO2.setSubClass_status(0);
//		sub_classVO2.setMainClass_id("MCI00002");
//		dao.update(sub_classVO2);

//		// �R��   //���ҵ{�~�వ
//		dao.delete("SCI00031");

//		// �d��
//		Sub_classVO sub_classVO3 = dao.findByPrimaryKey("SCI00002");
//		System.out.println(sub_classVO3.getSubClass_id());
//		System.out.println(sub_classVO3.getSubClass_name());
//		if(sub_classVO3.getSubClass_status()==0) {
//			System.out.println("����");
//		}else {
//			System.out.println("���");
//		}
//		System.out.println(sub_classVO3.getMainClass_id());

//		// �d�ߩҦ����
//		List<Sub_classVO> list = dao.getAll();
//		for (Sub_classVO item : list) {
//			System.out.print(item.getSubClass_id());
//			System.out.print(item.getSubClass_name());
//			if (item.getSubClass_status() == 0) {
//				System.out.println("����");
//			} else {
//				System.out.println("���");
//			}
//			System.out.print(item.getMainClass_id());
//			System.out.println();
//		}
//		
//		// �d�ߥD���O���Ҧ��l���O
//		Set<Sub_classVO> set = dao.getSub_classByMain_class_id("MCI00002");
//		for (Sub_classVO item : set) {
//			System.out.println(item.getSub_class_id());
//			System.out.println(item.getSub_class_name());
//			if (item.getSub_class_status() == 0) {
//				System.out.println("����");
//			} else {
//				System.out.println("���");
//			}
//			System.out.println();
//		}
	}
}
