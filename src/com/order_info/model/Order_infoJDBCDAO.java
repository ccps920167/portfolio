 package com.order_info.model;

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

import com.order_list.model.Order_listJDBCDAO;
import com.order_list.model.Order_listVO;

public class Order_infoJDBCDAO implements Order_infoDAO_interface {
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
	private static final String INSERT_STMT = "INSERT INTO Order_info (order_ID,member_id,order_time,payment_time,pay_way,order_status,coupon_ID,amount) VALUES ('OID' || lpad(order_infoSQE.NEXTVAL,5, '0'), ?, ?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE Order_info set member_id=?, order_time=?, payment_time=?,pay_way=?, order_status=?,coupon_ID=?,amount=? where order_ID = ?";
	private static final String DELETE_STMT = "DELETE FROM Order_info WHERE order_ID = ?";
	private static final String GET_ONE_STMT = "SELECT order_ID, member_id, order_time, payment_time, pay_way,order_status,coupon_ID,amount FROM Order_info where order_ID = ?";
	private static final String GET_ALL_STMT = "SELECT order_ID , member_id, order_time, payment_time,pay_way,order_status, coupon_ID,amount FROM Order_info";
	private static final String GET_member_id = "SELECT order_ID, member_id, order_time, payment_time, pay_way,order_status,coupon_ID,amount FROM Order_info where member_id = ?";
	
			public void insert(Order_infoVO order_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, order_infoVO.getMember_id());
			pstmt.setTimestamp(2, order_infoVO.getOrder_time());
			pstmt.setTimestamp(3, order_infoVO.getPayment_time());
			pstmt.setString(4, order_infoVO.getPay_way());
			pstmt.setInt(5, order_infoVO.getOrder_status());
			pstmt.setString(6, order_infoVO.getCoupon_ID());
			pstmt.setInt(7, order_infoVO.getAmount());
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

	public void update(Order_infoVO order_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, order_infoVO.getMember_id());
			pstmt.setTimestamp(2, order_infoVO.getOrder_time());
			pstmt.setTimestamp(3, order_infoVO.getPayment_time());
			pstmt.setString(4, order_infoVO.getPay_way());
			pstmt.setInt(5, order_infoVO.getOrder_status());
			pstmt.setString(6, order_infoVO.getCoupon_ID());
			pstmt.setString(7, order_infoVO.getOrder_ID());
			pstmt.setInt(8, order_infoVO.getAmount());
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

	public void delete(String order_infoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1, order_infoVO);
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

	public Order_infoVO findByPrimaryKey(String order_ID) {
		Order_infoVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, order_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new Order_infoVO();
				vo.setOrder_ID(rs.getString("order_ID"));
				vo.setMember_id(rs.getString("member_id"));
				vo.setOrder_time(rs.getTimestamp("order_time"));
				vo.setPayment_time(rs.getTimestamp("payment_time"));
				vo.setPay_way(rs.getString("pay_way"));
				vo.setOrder_status(rs.getInt("order_status"));
				vo.setCoupon_ID(rs.getString("coupon_ID"));
				vo.setAmount(rs.getInt("amount"));
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
		return vo;
	}

	public List<Order_infoVO> getAll() {
		List<Order_infoVO> empList = new ArrayList<Order_infoVO>();
		Order_infoVO vo2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo2 = new Order_infoVO();
				vo2.setOrder_ID(rs.getString("order_ID"));
				vo2.setMember_id(rs.getString("member_id"));
				vo2.setOrder_time(rs.getTimestamp("order_time"));
				vo2.setPayment_time(rs.getTimestamp("payment_time"));
				vo2.setPay_way(rs.getString("pay_way"));
				vo2.setOrder_status(rs.getInt("order_status"));
				vo2.setCoupon_ID(rs.getString("coupon_ID"));
				vo2.setAmount(rs.getInt("amount"));
				empList.add(vo2);
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
		return empList;
	}

	@Override
	public void insertwithinfo(Order_infoVO order_infoVO, List<Order_listVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			con.setAutoCommit(false);
			String cols[] = { "order_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, order_infoVO.getMember_id());
			pstmt.setTimestamp(2, order_infoVO.getOrder_time());
			pstmt.setTimestamp(3, order_infoVO.getPayment_time());
			pstmt.setString(4, order_infoVO.getPay_way());
			pstmt.setInt(5, order_infoVO.getOrder_status());
			pstmt.setString(6, order_infoVO.getCoupon_ID());
			pstmt.setInt(7, order_infoVO.getAmount());
			System.out.println("333");
			pstmt.executeUpdate();
			String next_order_infoVO = null;
			ResultSet rs = pstmt.getGeneratedKeys();
		
			if (rs.next()) {
				next_order_infoVO = rs.getString(1);
			} else {
				System.out.println("未取得主鍵");
			}
			rs.close();
			Order_listJDBCDAO dao = new Order_listJDBCDAO();
			for (Order_listVO order : list) {
				order.setOrder_id(next_order_infoVO);
				dao.inser2(order, con);
			}
			con.commit();
			con.setAutoCommit(true);
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
	
	
	public Order_infoVO findmember_id(String member_id) {
		Order_infoVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_member_id);
			pstmt.setString(1, member_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new Order_infoVO();
				vo.setOrder_ID(rs.getString("order_ID"));
				vo.setMember_id(rs.getString("member_id"));
				vo.setOrder_time(rs.getTimestamp("order_time"));
				vo.setPayment_time(rs.getTimestamp("payment_time"));
				vo.setPay_way(rs.getString("pay_way"));
				vo.setOrder_status(rs.getInt("order_status"));
				vo.setCoupon_ID(rs.getString("coupon_ID"));
				vo.setAmount(rs.getInt("amount"));
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
		return vo;
	}
	

	//訂單明細
	 public List<MemberOrder_infoVO> getMemberOrderList() {
	  List<MemberOrder_infoVO> memberordervo = new ArrayList<MemberOrder_infoVO>();
	  MemberOrder_infoVO vo=null;
	  Connection con = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  try {
	   
	   String sql="SELECT B.ORDER_LIST_ID ,A.ORDER_ID,A.ORDER_TIME,A.PAYMENT_TIME,A.PAY_WAY,A.AMOUNT,B.CLASS_ID,B.PURCHASE_PLAN FROM ORDER_INFO A INNER JOIN ORDER_LIST B ON A.ORDER_ID=B.ORDER_ID ORDER BY A.ORDER_ID" ;
	   con = ds.getConnection();
	   pstmt = con.prepareStatement(sql);

	   rs = pstmt.executeQuery();
	   System.out.println(rs.next());
	   while (rs.next()) {
	    vo=new MemberOrder_infoVO();
	    vo.setOrder_list_id(rs.getString("order_list_id"));
	    vo.setOrder_id(rs.getString("order_id"));
	    vo.setOrder_time(rs.getTimestamp("order_time"));
	    vo.setPayment_time(rs.getTimestamp("payment_time"));
	    vo.setPay_way(rs.getString("pay_way"));
	    vo.setAmount(rs.getInt("amount"));
	    vo.setClass_id(rs.getString("class_id"));
	    vo.setPurchase_plan(rs.getString("purchase_plan"));
	    memberordervo.add(vo);
	    
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
	  return memberordervo;
	 }
	
}