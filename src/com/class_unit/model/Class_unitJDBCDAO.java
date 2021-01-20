package com.class_unit.model;

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

public class Class_unitJDBCDAO implements Class_unitDAO_interface {
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

	private static final String INSERT = "INSERT INTO CLASS_UNIT VALUES('UNI'||lPAD(CLASS_UNIT_SEQ.NEXTVAL,5,'0'),?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE CLASS_UNIT SET CHAPTER_ID= ?,UNIT_NAME= ?,VIDEO= ?,VIDEO_LONG= ?,VIDEO_UPDATETIME= ?,VIDEO_STATUS= ? WHERE UNIT_ID= ?";
	private static final String DELETE = "DELETE FROM CLASS_UNIT WHERE UNIT_ID= ?";
	private static final String SELECT = "SELECT * FROM CLASS_UNIT WHERE UNIT_ID= ?";
	private static final String GET_ALL = "SELECT * FROM CLASS_UNIT ORDER BY UNIT_ID";
	private static final String GET_ALL_CHAPTER_ID = "SELECT * FROM CLASS_UNIT  WHERE CHAPTER_ID= ? ORDER BY UNIT_ID";
	private static final String UPDATE_VIDEO = "UPDATE CLASS_UNIT SET VIDEO= ?,VIDEO_UPDATETIME= ? WHERE UNIT_ID= ?";

	

	public void inser2(Class_unitVO class_unitVO, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, class_unitVO.getChapter_id());
			pstmt.setString(2, class_unitVO.getUnit_name());
			pstmt.setBytes(3, class_unitVO.getVideo());
			pstmt.setString(4, class_unitVO.getVideo_long());
			pstmt.setTimestamp(5, class_unitVO.getVideo_updatetime());
			pstmt.setInt(6, class_unitVO.getVideo_status());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}
	

	@Override
	public void updateVideo(Class_unitVO class_unitVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_VIDEO);

			pstmt.setBytes(1, class_unitVO.getVideo());
			pstmt.setTimestamp(2, class_unitVO.getVideo_updatetime());
			pstmt.setString(3, class_unitVO.getUnit_id());

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
	 public List<Class_unitVO> getAllunit_id(String class_id) {
	  List<Class_unitVO> list = new ArrayList<Class_unitVO>();
	  Connection con = null;
	 
	  String sql="SELECT B.UNIT_ID FROM(SELECT*FROM CLASS_CHAPTER WHERE CLASS_ID=?) A INNER JOIN CLASS_UNIT B ON A.CHAPTER_ID=B.CHAPTER_ID";
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  try {
	   System.out.println("1");
	   
	   System.out.println("2");
	   con = ds.getConnection();
	   System.out.println("3");
	   pstmt = con.prepareStatement(sql);
	   System.out.println(class_id);
	   pstmt.setString(1, class_id);
	   System.out.println(class_id+"X");
	   rs = pstmt.executeQuery();
	   while (rs.next()) {
	    Class_unitVO class_unitVO = new Class_unitVO();
	    class_unitVO.setUnit_id(rs.getString("UNIT_ID"));
	    
	    list.add(class_unitVO);
	   }
	   System.out.println(list);

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
	  }  return list;
	 }
	
	
	
	@Override
	public void insert(Class_unitVO class_unitVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, class_unitVO.getChapter_id());
			pstmt.setString(2, class_unitVO.getUnit_name());
			pstmt.setBytes(3, class_unitVO.getVideo());
			pstmt.setString(4, class_unitVO.getVideo_long());
			pstmt.setTimestamp(5, class_unitVO.getVideo_updatetime());
			pstmt.setInt(6, class_unitVO.getVideo_status());
			
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
	public void update(Class_unitVO class_unitVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, class_unitVO.getChapter_id());
			pstmt.setString(2, class_unitVO.getUnit_name());
			pstmt.setBytes(3, class_unitVO.getVideo());
			pstmt.setString(4, class_unitVO.getVideo_long());
			pstmt.setTimestamp(5, class_unitVO.getVideo_updatetime());
			pstmt.setInt(6, class_unitVO.getVideo_status());
			pstmt.setString(7,class_unitVO.getUnit_id());
			
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
	public void delete(String unit_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, unit_id);
			pstmt.executeUpdate();

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
	public Class_unitVO findByPrimaryKey(String unit_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Class_unitVO class_unitVO = new Class_unitVO();
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELECT);
			pstmt.setString(1, unit_id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				class_unitVO.setUnit_id(rs.getString("UNIT_ID"));
				class_unitVO.setChapter_id(rs.getString("CHAPTER_ID"));
				class_unitVO.setUnit_name(rs.getString("UNIT_NAME"));
				class_unitVO.setVideo(rs.getBytes("VIDEO"));
				class_unitVO.setVideo_long(rs.getString("VIDEO_LONG"));
				class_unitVO.setVideo_updatetime(rs.getTimestamp("VIDEO_UPDATETIME"));
				class_unitVO.setVideo_status(rs.getInt("VIDEO_STATUS"));
			}


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
		return class_unitVO;
	}

	@Override
	public List<Class_unitVO> getAll() {
		List<Class_unitVO> list = new ArrayList<Class_unitVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Class_unitVO class_unitVO = new Class_unitVO();
				class_unitVO.setUnit_id(rs.getString("UNIT_ID"));
				class_unitVO.setChapter_id(rs.getString("CHAPTER_ID"));
				class_unitVO.setUnit_name(rs.getString("UNIT_NAME"));
				class_unitVO.setVideo_long(rs.getString("VIDEO_LONG"));
				class_unitVO.setVideo_updatetime(rs.getTimestamp("VIDEO_UPDATETIME"));
				class_unitVO.setVideo_status(rs.getInt("VIDEO_STATUS"));
				list.add(class_unitVO);
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
		}		return list;
	}

	@Override
	public List<Class_unitVO> getChapter_id(String chapter_id) {
		List<Class_unitVO> list = new ArrayList<Class_unitVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_CHAPTER_ID);
			pstmt.setString(1, chapter_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Class_unitVO class_unitVO = new Class_unitVO();
				class_unitVO.setUnit_id(rs.getString("UNIT_ID"));
				class_unitVO.setChapter_id(rs.getString("CHAPTER_ID"));
				class_unitVO.setUnit_name(rs.getString("UNIT_NAME"));
				class_unitVO.setVideo(rs.getBytes("VIDEO"));
				class_unitVO.setVideo_long(rs.getString("VIDEO_LONG"));
				class_unitVO.setVideo_updatetime(rs.getTimestamp("VIDEO_UPDATETIME"));
				class_unitVO.setVideo_status(rs.getInt("VIDEO_STATUS"));
				list.add(class_unitVO);
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
		}		return null;
	}

}
