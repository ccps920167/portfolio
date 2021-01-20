package com.class_info.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Class_infoJDBCDAO implements Class_info_interface {

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

	private static final String INSERT_STMT = "INSERT INTO CLASS_INFO VALUES('CLA'||LPAD(class_info_SEQ.NEXTVAL,5,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE class_info set member_id=?, subclass_id=?, admin_id=?, class_name=?, class_status=?, startfund_date=?, startclass_time=?, class_description=?, class_picture=?, original_price=?, startfund_price=?, people_threshold=?, class_length=?, video_fundraising=?, update_time=? WHERE class_id=?";
	private static final String DELETE = "DELETE FROM class_info where class_id= ?";
	private static final String GET_ONE_STMT = "SELECT class_id, member_id, subclass_id, admin_id, class_name, class_status, startfund_date, startclass_time, class_description, class_picture, original_price, startfund_price, people_threshold, class_length, video_fundraising, update_time FROM class_info WHERE class_id= ? ";
	private static final String GET_ALL_STMT = "SELECT class_id, member_id, subclass_id, admin_id, class_name, class_status, startfund_date, startclass_time, class_description, class_picture, original_price, startfund_price, people_threshold, class_length, video_fundraising, update_time FROM class_info order by class_id";
	private static final String UPDATESTATUS_STMT = 
			"UPDATE class_info set class_status=? ,admin_id=? WHERE class_id=?";
	private static final String UPDATE_FUND = 
			"UPDATE class_info set class_picture=? ,video_fundraising=?, update_time=? WHERE class_id=?";
	private static final String GET_ONE_CLASSID = 
			"SELECT class_id FROM class_info WHERE member_id=? and class_name=? and subclass_id=?";
	private static final String UPDATE_STATUS_TOFUND = 
			"UPDATE class_info set class_status=? ,admin_id=?,startfund_date=?,startclass_time=?,update_time=? WHERE class_id=?";
	
	// ===================================查興趣索引前8筆課程======================================

	public List<Class_infoVO> get_ROWNUM_8_Bysub_class(String subclass_id) {
		List<Class_infoVO> list = new ArrayList<Class_infoVO>();
		Class_infoVO ciVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"SELECT * FROM CLASS_INFO WHERE CLASS_STATUS=1 OR CLASS_STATUS =4 AND  ROWNUM < 9 AND SUBCLASS_ID='"
							+ subclass_id + "' ORDER BY dbms_random.value");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ciVO = new Class_infoVO();
				ciVO.setClass_id(rs.getString("class_id"));
				ciVO.setMember_id(rs.getString("member_id"));
				ciVO.setSubclass_id(rs.getString("subclass_id"));
				ciVO.setAdmin_id(rs.getString("admin_id"));
				ciVO.setClass_name(rs.getString("class_name"));
				ciVO.setClass_status(rs.getInt("class_status"));
				ciVO.setStartfund_date(rs.getTimestamp("startfund_date"));
				ciVO.setStartclass_time(rs.getTimestamp("startclass_time"));
				ciVO.setClass_description(rs.getString("class_description"));
				ciVO.setOriginal_price(rs.getInt("original_price"));
				ciVO.setStartfund_price(rs.getInt("startfund_price"));
				ciVO.setPeople_threshold(rs.getInt("people_threshold"));
				ciVO.setClass_length(rs.getString("class_length"));
//	     ciVO.setVideo_fundraising(rs.getBytes("video_fundraising"));
				ciVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(ciVO); // Store the row in the list
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

	// ===================================查興趣索引前8筆課程結束======================================

	@Override
	public List<Class_infoVO> getFund() {
		List<Class_infoVO> list = new ArrayList<Class_infoVO>();
		Class_infoVO ciVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT CLASS_ID,CLASS_NAME,STARTFUND_DATE,CLASS_PICTURE,STARTFUND_PRICE,PEOPLE_THRESHOLD FROM CLASS_INFO WHERE CLASS_STATUS='1'";
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ciVO = new Class_infoVO();
				ciVO.setClass_id(rs.getString("class_id"));
				ciVO.setClass_name(rs.getString("class_name"));
				ciVO.setStartfund_date(rs.getTimestamp("startfund_date"));
				ciVO.setClass_picture(rs.getBytes("class_picture"));
				ciVO.setStartfund_price(rs.getInt("startfund_price"));
				ciVO.setPeople_threshold(rs.getInt("people_threshold"));
				list.add(ciVO); // Store the row in the list
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
	public List<Class_infoVO> getTop8() {
		List<Class_infoVO> list = new ArrayList<Class_infoVO>();
		Class_infoVO ciVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM (SELECT *FROM (SELECT CLASS_ID ,COUNT(*) C FROM ORDER_LIST GROUP BY CLASS_ID ORDER BY COUNT(*) DESC)WHERE ROWNUM<=8) A LEFT JOIN CLASS_INFO B ON A.CLASS_ID=B.CLASS_ID ORDER BY C DESC";
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ciVO = new Class_infoVO();
				ciVO.setClass_id(rs.getString("class_id"));
				ciVO.setMember_id(rs.getString("member_id"));
				ciVO.setSubclass_id(rs.getString("subclass_id"));
				ciVO.setAdmin_id(rs.getString("admin_id"));
				ciVO.setClass_name(rs.getString("class_name"));
				ciVO.setClass_status(rs.getInt("class_status"));
				ciVO.setStartfund_date(rs.getTimestamp("startfund_date"));
				ciVO.setStartclass_time(rs.getTimestamp("startclass_time"));
				ciVO.setClass_description(rs.getString("class_description"));
				ciVO.setClass_picture(rs.getBytes("class_picture"));
				ciVO.setOriginal_price(rs.getInt("original_price"));
				ciVO.setStartfund_price(rs.getInt("startfund_price"));
				ciVO.setPeople_threshold(rs.getInt("people_threshold"));
				ciVO.setClass_length(rs.getString("class_length"));
				ciVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(ciVO); // Store the row in the list
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

	// 有教的課程
	public List<Class_infoVO> getTeachAll(String member_id) {
		List<Class_infoVO> list = new ArrayList<Class_infoVO>();
		Class_infoVO ciVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM CLASS_INFO WHERE member_id='" + member_id + "' ORDER BY CLASS_ID";

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ciVO = new Class_infoVO();
				ciVO.setClass_id(rs.getString("class_id"));
				ciVO.setMember_id(rs.getString("member_id"));
				ciVO.setSubclass_id(rs.getString("subclass_id"));
				ciVO.setAdmin_id(rs.getString("admin_id"));
				ciVO.setClass_name(rs.getString("class_name"));
				ciVO.setClass_status(rs.getInt("class_status"));
				ciVO.setStartfund_date(rs.getTimestamp("startfund_date"));
				ciVO.setStartclass_time(rs.getTimestamp("startclass_time"));
				ciVO.setClass_description(rs.getString("class_description"));
				ciVO.setOriginal_price(rs.getInt("original_price"));
				ciVO.setStartfund_price(rs.getInt("startfund_price"));
				ciVO.setPeople_threshold(rs.getInt("people_threshold"));
				ciVO.setClass_length(rs.getString("class_length"));
//				ciVO.setVideo_fundraising(rs.getBytes("video_fundraising"));
				ciVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(ciVO); // Store the row in the list
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

	// 查開課中前10筆
	public List<Class_infoVO> get_ROWNUM_8() {
		List<Class_infoVO> list = new ArrayList<Class_infoVO>();
		Class_infoVO ciVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(
					"SELECT * FROM CLASS_INFO WHERE CLASS_STATUS=4 AND  ROWNUM < 9  ORDER BY CLASS_ID");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ciVO = new Class_infoVO();
				ciVO.setClass_id(rs.getString("class_id"));
				ciVO.setMember_id(rs.getString("member_id"));
				ciVO.setSubclass_id(rs.getString("subclass_id"));
				ciVO.setAdmin_id(rs.getString("admin_id"));
				ciVO.setClass_name(rs.getString("class_name"));
				ciVO.setClass_status(rs.getInt("class_status"));
				ciVO.setStartfund_date(rs.getTimestamp("startfund_date"));
				ciVO.setStartclass_time(rs.getTimestamp("startclass_time"));
				ciVO.setClass_description(rs.getString("class_description"));
				ciVO.setOriginal_price(rs.getInt("original_price"));
				ciVO.setStartfund_price(rs.getInt("startfund_price"));
				ciVO.setPeople_threshold(rs.getInt("people_threshold"));
				ciVO.setClass_length(rs.getString("class_length"));
//				ciVO.setVideo_fundraising(rs.getBytes("video_fundraising"));
				ciVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(ciVO); // Store the row in the list
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
	public List<Class_infoVO> getAll(Map<String, String[]> map) {
		List<Class_infoVO> list = new ArrayList<Class_infoVO>();
		Class_infoVO ciVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM CLASS_INFO " + get_WhereCondition(map) + " ORDER BY CLASS_ID";

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ciVO = new Class_infoVO();
				ciVO.setClass_id(rs.getString("class_id"));
				ciVO.setMember_id(rs.getString("member_id"));
				ciVO.setSubclass_id(rs.getString("subclass_id"));
				ciVO.setAdmin_id(rs.getString("admin_id"));
				ciVO.setClass_name(rs.getString("class_name"));
				ciVO.setClass_status(rs.getInt("class_status"));
				ciVO.setStartfund_date(rs.getTimestamp("startfund_date"));
				ciVO.setStartclass_time(rs.getTimestamp("startclass_time"));
				ciVO.setClass_description(rs.getString("class_description"));
				ciVO.setOriginal_price(rs.getInt("original_price"));
				ciVO.setStartfund_price(rs.getInt("startfund_price"));
				ciVO.setPeople_threshold(rs.getInt("people_threshold"));
				ciVO.setClass_length(rs.getString("class_length"));
				ciVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(ciVO); // Store the row in the list
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

	// 引入Map servlet 利用request.getParameterMap(); 取值 所以接取Map必須是<String, String[]>
	public static String get_WhereCondition(Map<String, String[]> map) {
		// 取map所有的key
		Set<String> keys = map.keySet();
		// StringBuffer 建立可以伸縮的字串
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		// 取出所有的key
		for (String key : keys) {
			// 利用key取回所有的value
			// 為什麼[0] checkbox其他資訊????
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) { // action是最後一個name
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim()); // 欄位名稱=form表單的name + 輸入的內容

				// 第一筆前面要where 其他比 前面要and
				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
			}
		}

		return whereCondition.toString();
	}

	// 欄位名稱=form表單的name + 輸入的內容
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;
		// 判斷傳進去的值做不同的處理
		// 沒有大小之分 ID + 狀態

		if ("class_id".equals(columnName) || "member_id".equals(columnName) || "class_status".equals(columnName)
				|| "subclass_id".equals(columnName) || "admin_id".equals(columnName)) // 數字兩者相等
			aCondition = columnName + "='" + value + "'";
		if ("class_status".equals(columnName)) // 數字兩者相等
			aCondition = columnName + "=" + value;
		// 有大小之分 數字(價格) 人數
		else if ("startfund_price".equals(columnName) || "original_price".equals(columnName)
				|| "people_threshold".equals(columnName))
			aCondition = columnName + "=" + value;
		// 字串部分符合like
		else if ("class_name".equals(columnName) || "mainClass_name".equals(columnName))
			aCondition = columnName + " like '%" + value + "%'";
		// 日期處理
		else if ("startfund_date".equals(columnName) || "startclass_time".equals(columnName)
				|| "update_time".equals(columnName)) // 用於Oracle的date
			aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		return aCondition + " ";
	}

	@Override
	public void insert(Class_infoVO class_infoVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT_STMT);

			pstmt.setString(1, class_infoVO.getMember_id());
			pstmt.setString(2, class_infoVO.getSubclass_id());
			pstmt.setString(3, class_infoVO.getAdmin_id());
			pstmt.setString(4, class_infoVO.getClass_name());
			pstmt.setInt(5, class_infoVO.getClass_status());
			pstmt.setTimestamp(6, class_infoVO.getStartfund_date());
			pstmt.setTimestamp(7, class_infoVO.getStartclass_time());
			pstmt.setString(8, class_infoVO.getClass_description());
			pstmt.setBytes(9, class_infoVO.getClass_picture());
			pstmt.setInt(10, class_infoVO.getOriginal_price());
			pstmt.setInt(11, class_infoVO.getStartfund_price());
			pstmt.setInt(12, class_infoVO.getPeople_threshold());
			pstmt.setString(13, class_infoVO.getClass_length());
			pstmt.setBytes(14, class_infoVO.getVideo_fundraising());
			pstmt.setTimestamp(15, class_infoVO.getUpdate_time());

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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(Class_infoVO class_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, class_infoVO.getMember_id());
			pstmt.setString(2, class_infoVO.getSubclass_id());
			pstmt.setString(3, class_infoVO.getAdmin_id());
			pstmt.setString(4, class_infoVO.getClass_name());
			pstmt.setInt(5, class_infoVO.getClass_status());
			pstmt.setTimestamp(6, class_infoVO.getStartfund_date());
			pstmt.setTimestamp(7, class_infoVO.getStartclass_time());
			pstmt.setString(8, class_infoVO.getClass_description());
			pstmt.setBytes(9, class_infoVO.getClass_picture());
			pstmt.setInt(10, class_infoVO.getOriginal_price());
			pstmt.setInt(11, class_infoVO.getStartfund_price());
			pstmt.setInt(12, class_infoVO.getPeople_threshold());
			pstmt.setString(13, class_infoVO.getClass_length());
			pstmt.setBytes(14, class_infoVO.getVideo_fundraising());
			pstmt.setTimestamp(15, class_infoVO.getUpdate_time());
			pstmt.setString(16, class_infoVO.getClass_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	@Override
	public void delete(String class_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, class_id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public Class_infoVO findByPrimaryKey(String class_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Class_infoVO ciVO = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, class_infoVO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ciVO = new Class_infoVO();
				ciVO.setClass_id(rs.getString("class_id"));
				ciVO.setMember_id(rs.getString("member_id"));
				ciVO.setSubclass_id(rs.getString("subclass_id"));
				ciVO.setAdmin_id(rs.getString("admin_id"));
				ciVO.setClass_name(rs.getString("class_name"));
				ciVO.setClass_status(rs.getInt("class_status"));
				ciVO.setStartfund_date(rs.getTimestamp("startfund_date"));
				ciVO.setStartclass_time(rs.getTimestamp("startclass_time"));
				ciVO.setClass_description(rs.getString("class_description"));
				ciVO.setClass_picture(rs.getBytes("class_picture"));
				ciVO.setOriginal_price(rs.getInt("original_price"));
				ciVO.setStartfund_price(rs.getInt("startfund_price"));
				ciVO.setPeople_threshold(rs.getInt("people_threshold"));
				ciVO.setClass_length(rs.getString("class_length"));
				ciVO.setVideo_fundraising(rs.getBytes("video_fundraising"));
				ciVO.setUpdate_time(rs.getTimestamp("update_time"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return ciVO;
	}

	@Override
	public List<Class_infoVO> getAll() {
		List<Class_infoVO> list = new LinkedList<Class_infoVO>();
		Class_infoVO ciVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ciVO = new Class_infoVO();
				ciVO.setClass_id(rs.getString("class_id"));
				ciVO.setMember_id(rs.getString("member_id"));
				ciVO.setSubclass_id(rs.getString("subclass_id"));
				ciVO.setAdmin_id(rs.getString("admin_id"));
				ciVO.setClass_name(rs.getString("class_name"));
				ciVO.setClass_status(rs.getInt("class_status"));
				ciVO.setStartfund_date(rs.getTimestamp("startfund_date"));
				ciVO.setStartclass_time(rs.getTimestamp("startclass_time"));
				ciVO.setClass_description(rs.getString("class_description"));
				ciVO.setOriginal_price(rs.getInt("original_price"));
				ciVO.setStartfund_price(rs.getInt("startfund_price"));
				ciVO.setPeople_threshold(rs.getInt("people_threshold"));
				ciVO.setClass_length(rs.getString("class_length"));
				ciVO.setUpdate_time(rs.getTimestamp("update_time"));
				list.add(ciVO); // Store the row in the list
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

	public static void main(String[] args) {

		Class_infoJDBCDAO dao = new Class_infoJDBCDAO();
//
//		// 新增
//		Class_infoVO class_infoVO1 = new Class_infoVO();
//		class_infoVO1.setMember_id("MEM00001");
//		class_infoVO1.setSubclass_id("SCI00001");
//		class_infoVO1.setAdmin_id("AI00001");
//		class_infoVO1.setClass_name("新增");
//		class_infoVO1.setClass_status(1);
//		class_infoVO1.setStartfund_date(java.sql.Timestamp.valueOf("2020-10-07 14:23:00"));
//		class_infoVO1.setStartclass_time(java.sql.Timestamp.valueOf("2020-12-07 14:23:00"));
//		class_infoVO1.setClass_description(null);
//		class_infoVO1.setClass_picture(null);
//		class_infoVO1.setOriginal_price(2000);
//		class_infoVO1.setStartfund_price(1000);
//		class_infoVO1.setPeople_threshold(80);
//		class_infoVO1.setClass_length(null);
//		class_infoVO1.setVideo_fundraising(null);
//		class_infoVO1.setUpdate_time(java.sql.Timestamp.valueOf("2020-12-05 14:23:00"));
//		
//		dao.insert(class_infoVO1);

		// 修改
//		Class_infoVO class_infoVO2 = new Class_infoVO();
//		class_infoVO2.setMember_id("MEM00001");
//		class_infoVO2.setSubclass_id("SCI00001");
//		class_infoVO2.setAdmin_id("AI00001");
//		class_infoVO2.setClass_name("修改修改修改");
//		class_infoVO2.setClass_status(4);
//		class_infoVO2.setStartfund_date(java.sql.Timestamp.valueOf("2020-10-07 14:23:00"));
//		class_infoVO2.setStartclass_time(java.sql.Timestamp.valueOf("2020-12-07 14:23:00"));
//		class_infoVO2.setClass_description(null);
//		class_infoVO2.setClass_picture(null);
//		class_infoVO2.setOriginal_price(2000);
//		class_infoVO2.setStartfund_price(1000);
//		class_infoVO2.setPeople_threshold(80);
//		class_infoVO2.setClass_length(null);
//		class_infoVO2.setVideo_fundraising(null);
//		class_infoVO2.setUpdate_time(java.sql.Timestamp.valueOf("2020-12-05 14:23:00"));
//		class_infoVO2.setClass_id("CLA00060");
//		dao.update(class_infoVO2);

		// 刪除
//		dao.delete("CLA00061");

		// 查詢
//		Class_infoVO class_infoVO3 = dao.findByPrimaryKey("CLA00060");
//		System.out.println(class_infoVO3.getClass_id()+"，");
//		System.out.println(class_infoVO3.getMember_id()+"，");
//		System.out.println(class_infoVO3.getSubclass_id()+"，");
//		System.out.println(class_infoVO3.getAdmin_id()+"，");
//		System.out.println(class_infoVO3.getClass_name()+"，");
//		System.out.println(class_infoVO3.getClass_status()+"，");
//		System.out.println(class_infoVO3.getStartfund_date()+"，");
//		System.out.println(class_infoVO3.getStartclass_time()+"，");
//		System.out.println(class_infoVO3.getClass_description()+"，");
//		System.out.println(class_infoVO3.getClass_picture2()+"，");
//		System.out.println(class_infoVO3.getOriginal_price()+"。");
//		System.out.println(class_infoVO3.getStartfund_price()+"，");
//		System.out.println(class_infoVO3.getPeople_threshold()+"，");
//		System.out.println(class_infoVO3.getClass_length()+"，");
//		System.out.println(class_infoVO3.getVideo_fundraising()+"，");
//		System.out.println(class_infoVO3.getUpdate_time()+"，");

		// getAll 查詢
//		List<Class_infoVO> list = dao.getAll();
//		for (Class_infoVO ciVO : list) {
//			
//			System.out.println(ciVO.getClass_id()+"，");
//			System.out.println(ciVO.getMember_id()+"，");
//			System.out.println(ciVO.getSubclass_id()+"，");
//			System.out.println(ciVO.getAdmin_id()+"，");
//			System.out.println(ciVO.getClass_name()+"，");
//			System.out.println(ciVO.getClass_status()+"，");
//			System.out.println(ciVO.getStartfund_date()+"，");
//			System.out.println(ciVO.getStartclass_time()+"，");
//			System.out.println(ciVO.getClass_description()+"，");
//			System.out.println(ciVO.getClass_picture()+"，");
//			System.out.println(ciVO.getOriginal_price()+"，");
//			System.out.println(ciVO.getStartfund_price()+"，");
//			System.out.println(ciVO.getPeople_threshold()+"，");
//			System.out.println(ciVO.getClass_length()+"，");
//			System.out.println(ciVO.getVideo_fundraising()+"，");
//			System.out.println(ciVO.getUpdate_time()+"。");
//			
//			System.out.println();
//		}

	}

	@Override
	public byte[] findByPrimaryKey_video(String class_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] video = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, class_infoVO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				video = rs.getBytes("video_fundraising");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return video;
	}

	@Override
	public byte[] findByPrimaryKey_pic(String class_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byte[] pic = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, class_infoVO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pic = rs.getBytes("class_picture");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return pic;
	}

	@Override
	public void updateStatus(Class_infoVO class_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATUS_STMT);

			pstmt.setInt(1, class_infoVO.getClass_status());
			pstmt.setString(2, class_infoVO.getAdmin_id());
			pstmt.setString(3, class_infoVO.getClass_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}

	@Override
	public void updateFund(Class_infoVO class_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FUND);

			pstmt.setBytes(1, class_infoVO.getClass_picture());
			pstmt.setBytes(2, class_infoVO.getVideo_fundraising());
			pstmt.setTimestamp(3, class_infoVO.getUpdate_time());
			pstmt.setString(4, class_infoVO.getClass_id());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}

	@Override
	public Class_infoVO findClass_id(Class_infoVO class_infoVO) {
		Class_infoVO civo = new Class_infoVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CLASSID);

			System.out.println(GET_ONE_CLASSID);
			System.out.println(class_infoVO.getMember_id());
			System.out.println(class_infoVO.getClass_name());
			System.out.println(class_infoVO.getSubclass_id());

			pstmt.setString(1, class_infoVO.getMember_id());
			pstmt.setString(2, class_infoVO.getClass_name());
			pstmt.setString(3, class_infoVO.getSubclass_id());

			pstmt.executeUpdate();

			rs = pstmt.executeQuery();
			while (rs.next()) {
				civo = new Class_infoVO();
				civo.setClass_id(rs.getString("class_id"));
			}

			System.out.println(civo.getClass_id());

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return civo;
	}
	
	@Override
	public void updateVerify1(Class_infoVO class_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STATUS_TOFUND);
			
			pstmt.setInt(1, class_infoVO.getClass_status());
			pstmt.setString(2, class_infoVO.getAdmin_id());
			pstmt.setTimestamp(3, class_infoVO.getStartfund_date());
			pstmt.setTimestamp(4, class_infoVO.getStartclass_time());
			pstmt.setTimestamp(5, class_infoVO.getUpdate_time());
			pstmt.setString(6, class_infoVO.getClass_id());
			
			pstmt.executeUpdate();


		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}		
	}

}
