package com.video_record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.main_class.model.Main_classVO;

//刪除待確認
//查詢子類別待確認

public class Video_recordJDBCDAO implements Video_recordDAO_interface {
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

	private static final String INSERT_STMT = "INSERT INTO VIDEO_RECORD VALUES('VR'||LPAD(video_record_CS.NEXTVAL,5,'0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM VIDEO_RECORD";
	private static final String GET_ONE_STMT = "SELECT * FROM VIDEO_RECORD WHERE RECORD_ID = ?";
	private static final String DELETE_SUB_CLASS = "DELETE FROM VIDEO_RECORD WHERE RECORD_ID = ?";
	private static final String UPDATE = "UPDATE VIDEO_RECORD " + "SET CLASS_LAST=?, " + "VIDEO_updateTime=? , "
			+ "MEMBER_ID=? ," + "unit_id=? " + "WHERE RECORD_ID = ?";

	@Override
	public void insert(Video_recordVO video_recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, video_recordVO.getClass_last());
			pstmt.setTimestamp(2, video_recordVO.getVideo_updateTime());
			pstmt.setString(3, video_recordVO.getMember_id());
			pstmt.setString(4, video_recordVO.getunit_id());

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
	public void update(Video_recordVO video_recordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, video_recordVO.getClass_last());
			pstmt.setTimestamp(2, video_recordVO.getVideo_updateTime());
			pstmt.setString(3, video_recordVO.getMember_id());
			pstmt.setString(4, video_recordVO.getunit_id());
			pstmt.setString(5, video_recordVO.getRecord_id());

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
	public void delete(String record_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_SUB_CLASS);
			pstmt.setString(1, record_id);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後

			System.out.println("刪除影片紀錄" + record_id);

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
	public Video_recordVO findByPrimaryKey(String record_id) {

		Video_recordVO video_recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, record_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				video_recordVO = new Video_recordVO();
				video_recordVO.setRecord_id(rs.getString("RECORD_ID"));
				video_recordVO.setClass_last(rs.getString("CLASS_LAST"));
				video_recordVO.setVideo_updateTime(rs.getTimestamp("VIDEO_updateTime"));
				video_recordVO.setMember_id(rs.getString("MEMBER_ID"));
				video_recordVO.setunit_id(rs.getString("unit_id"));
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
		return video_recordVO;
	}

	@Override
	public List<Video_recordVO> getAll() {
		List<Video_recordVO> list = new ArrayList<Video_recordVO>();
		Video_recordVO video_recordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				video_recordVO = new Video_recordVO();
				video_recordVO.setRecord_id(rs.getString("RECORD_ID"));
				video_recordVO.setClass_last(rs.getString("CLASS_LAST"));
				video_recordVO.setVideo_updateTime(rs.getTimestamp("VIDEO_updateTime"));
				video_recordVO.setMember_id(rs.getString("MEMBER_ID"));
				video_recordVO.setunit_id(rs.getString("unit_id"));
				list.add(video_recordVO); // Store the row in the list
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
	public List<Video_recordVO> getAll(Map<String, String[]> map) {
		List<Video_recordVO> list = new ArrayList<Video_recordVO>();
		Video_recordVO video_recordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM  VIDEO_RECORD " + get_WhereCondition(map) + " ORDER BY RECORD_ID";
		System.out.println(sql);
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				video_recordVO = new Video_recordVO();
				video_recordVO.setRecord_id(rs.getString("RECORD_ID"));
				video_recordVO.setClass_last(rs.getString("CLASS_LAST"));
				video_recordVO.setVideo_updateTime(rs.getTimestamp("VIDEO_updateTime"));
				video_recordVO.setMember_id(rs.getString("MEMBER_ID"));
				video_recordVO.setunit_id(rs.getString("unit_id"));
				list.add(video_recordVO);
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
	public String get_WhereCondition(Map<String, String[]> map) {
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

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}

		return whereCondition.toString();
	}

	// 欄位名稱=form表單的name + 輸入的內容
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;
		// 判斷傳進去的值做不同的處理
		if ("record_id".equals(columnName) || "member_id".equals(columnName) || "unit_id".equals(columnName)) // 數字兩者相等
			aCondition = columnName + "='" + value+"'";
		return aCondition + " ";
	}

	
	@Override
	public List<Video_recordVO> getMemberAll(String member_id) {
		List<Video_recordVO> list = new ArrayList<Video_recordVO>();
		Video_recordVO video_recordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM VIDEO_RECORD WHERE member_id='"+member_id+"' ORDER BY RECORD_ID";
		System.out.println(sql);
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				video_recordVO = new Video_recordVO();
				video_recordVO.setRecord_id(rs.getString("RECORD_ID"));
				video_recordVO.setClass_last(rs.getString("CLASS_LAST"));
				video_recordVO.setVideo_updateTime(rs.getTimestamp("VIDEO_updateTime"));
				video_recordVO.setMember_id(rs.getString("MEMBER_ID"));
				video_recordVO.setunit_id(rs.getString("unit_id"));
				list.add(video_recordVO);
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
}
