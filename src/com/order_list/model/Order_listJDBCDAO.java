package com.order_list.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order_info.model.Order_infoVO;


public class Order_listJDBCDAO implements Order_listDAO_interface {
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
	private static final String INSERT_STMT = "INSERT INTO Order_list (order_list_id,order_id,class_id,purchase_plan) VALUES ('OL' || lpad(order_list_SQE.NEXTVAL,5, '0'), ?, ?,?)";
	private static final String UPDATE = "UPDATE Order_list set order_id=?, class_id=?, purchase_plan=? where order_list_id = ?";
	private static final String DELETE_STMT = "DELETE FROM Order_list WHERE order_list_id = ?";
	private static final String GET_ONE_STMT = "SELECT order_list_id, order_id, class_id, purchase_plan FROM Order_list where order_list_id = ?";
	private static final String GET_ALL_STMT = "SELECT order_list_id , order_id, class_id, purchase_plan FROM Order_list";
	private static final String GET_TWO_STMT = "SELECT order_list_id, order_id, class_id, purchase_plan FROM Order_list where order_ID = ?";
	
	
	public List<Order_listVO> getNumClass(String class_id){
		   List<Order_listVO> Num = new ArrayList<Order_listVO>();
		   Order_listVO vo = null;
		   Connection con = null;
		   PreparedStatement pstmt = null;
		   ResultSet rs = null;
		   try {

		    con = ds.getConnection();
		    String sql = "SELECT ORDER_LIST_ID FROM ORDER_LIST WHERE CLASS_ID=?";
		    pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, class_id);
		    rs = pstmt.executeQuery();

		    while (rs.next()) {
		     vo = new Order_listVO();
		     vo.setOrder_list_id(rs.getString("order_list_id"));
		     
		     Num.add(vo);
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
		   return Num;
		  }
	
	
	@Override
	public void insert(Order_listVO Order_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, Order_listVO.getOrder_id());
			pstmt.setString(2, Order_listVO.getClass_id());
			pstmt.setString(3, Order_listVO.getPurchase_plan());

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
	public void update(Order_listVO Order_listVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, Order_listVO.getOrder_id());
			pstmt.setString(2, Order_listVO.getClass_id());
			pstmt.setString(3, Order_listVO.getPurchase_plan());
			pstmt.setString(4, Order_listVO.getOrder_list_id());
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
	public void delete(String Order_list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			pstmt.setString(1,Order_list);
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
	public Order_listVO findByPrimaryKey(String order_list_id) {
		Order_listVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, order_list_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new Order_listVO();
				vo.setOrder_list_id(rs.getString("order_list_id"));
				vo.setOrder_id(rs.getString("order_id"));
				vo.setClass_id(rs.getString("class_id"));
				vo.setPurchase_plan(rs.getString("purchase_plan"));
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


	@Override
	public List<Order_listVO> getAll() {
		List<Order_listVO> empList = new ArrayList<Order_listVO>();
		Order_listVO vo2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo2 = new Order_listVO();
				vo2.setOrder_list_id(rs.getString("order_list_id"));
				vo2.setOrder_id(rs.getString("order_id"));
				vo2.setClass_id(rs.getString("class_id"));
				vo2.setPurchase_plan(rs.getString("purchase_plan"));
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
	
	public Order_listVO findByOrder_ID(String Order_ID) {
		Order_listVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_TWO_STMT);
			pstmt.setString(1, Order_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo = new Order_listVO();
				vo.setOrder_list_id(rs.getString("order_list_id"));
				vo.setOrder_id(rs.getString("order_id"));
				vo.setClass_id(rs.getString("class_id"));
				vo.setPurchase_plan(rs.getString("purchase_plan"));
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



	public static void main(String[] args) {
		// 新增
		Order_listJDBCDAO order = new Order_listJDBCDAO();
		Order_listVO inf = new Order_listVO();
//		inf.setOrder_id("OID00028");
//		inf.setClass_id("CLA00008");
//		inf.setPurchase_plan("我是誰");
//		order.insert(inf);

		// 修改
//	    Order_listVO inf = new Order_listVO();
//	    inf.setOrder_id("OID00028");
//	    inf.setClass_id("CLA00008");
//	    inf.setPurchase_plan("肚肚物度");
//	    inf.setOrder_list_id("OL00068");
//	    order.update(inf);
		
		//刪除
//		order.delete("OL00068");
		
//		 查詢
		Order_listVO vo1 = order.findByOrder_ID("OID00058");
		System.out.println(vo1.getOrder_list_id());
		System.out.println(vo1.getOrder_id());
		System.out.println(vo1.getClass_id());
		System.out.println(vo1.getPurchase_plan());
		
		// 查詢全部
//		List<Order_listVO> li = order.getAll();
//		for (Order_listVO apt : li) {
//			System.out.println(apt.getOrder_list_id());
//			System.out.println(apt.getOrder_id());
//			System.out.println(apt.getClass_id());
//			System.out.println(apt.getPurchase_plan());

		}
//	}
	
	
//	Set key = map.keyset()
	
	//取該用戶的所有訂單資料
	@Override
	public Map <Order_infoVO,List<Order_listVO>> getMemberOrder(String member_id) {
		Map <Order_infoVO,List<Order_listVO>> map = new HashMap<Order_infoVO, List<Order_listVO>>();
		
		Order_listVO vo2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			String sql = "select * from ORDER_INFO inner Join ORDER_LIST on ORDER_INFO.ORDER_ID =ORDER_LIST.ORDER_ID where member_id = ? and  ORDER_STATUS=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Order_infoVO vo  = new Order_infoVO();
				vo.setOrder_ID(rs.getString("order_ID"));
				vo.setOrder_status(rs.getInt("order_status"));
				vo.setMember_id(rs.getString("member_id"));
				vo.setOrder_time(rs.getTimestamp("order_time"));
				vo.setPay_way(rs.getString("pay_way"));
				vo.setPayment_time(rs.getTimestamp("payment_time"));
				vo.setCoupon_ID(rs.getString("coupon_ID"));
				vo.setAmount(rs.getInt("amount"));
				
				
				if(map.get(vo)==null) {
					List<Order_listVO> list = new ArrayList<Order_listVO>();
					vo2 = new Order_listVO();
					vo2.setOrder_list_id(rs.getString("order_list_id"));
					vo2.setOrder_id(rs.getString("order_id"));
					vo2.setClass_id(rs.getString("class_id"));
					vo2.setPurchase_plan(rs.getString("purchase_plan"));
					list.add(vo2);
					map.put(vo,list);
				}else {
					vo2 = new Order_listVO();
					vo2.setOrder_list_id(rs.getString("order_list_id"));
					vo2.setOrder_id(rs.getString("order_id"));
					vo2.setClass_id(rs.getString("class_id"));
					vo2.setPurchase_plan(rs.getString("purchase_plan"));
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
	
	
	
	
	
	//取該用戶的所有成功購買課程
	@Override
	public List<Order_listVO> getMemberClass(String member_id) {
		List<Order_listVO> empList = new ArrayList<Order_listVO>();
		Order_listVO vo2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			String sql = "select * from ORDER_INFO inner Join ORDER_LIST on ORDER_INFO.ORDER_ID =ORDER_LIST.ORDER_ID where member_id = ? and  ORDER_STATUS=1";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo2 = new Order_listVO();
				vo2.setOrder_list_id(rs.getString("order_list_id"));
				vo2.setOrder_id(rs.getString("order_id"));
				vo2.setClass_id(rs.getString("class_id"));
				vo2.setPurchase_plan(rs.getString("purchase_plan"));
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
	
	
	//課程對應訂單
	@Override
	public List<Map<String,String>> getClassID(String class_id) {
		Map<String,String> map = new HashMap<String,String>();
		List<Map<String,String>> getClassID = new ArrayList<Map<String,String>>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			String sql = "select *  from ORDER_INFO  Join ORDER_LIST on ORDER_INFO.ORDER_ID =ORDER_LIST.ORDER_ID  Join class_info on class_info.class_id =order_list.class_id where order_list.class_id = ? and  ORDER_STATUS=1  ORDER BY ORDER_TIME ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, class_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				map.put("time",dateFormat.format(rs.getTimestamp("order_time")));
				map.put("purchase_plan",rs.getString("purchase_plan"));
				if(rs.getString("purchase_plan").equals("募資價")) {
					map.put("price",Integer.toString(rs.getInt("STARTFUND_PRICE")));
				}
				else {
					map.put("price",Integer.toString(rs.getInt("ORIGINAL_PRICE")));
				}
				getClassID.add(map);
				
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
		return getClassID;
	}
	
	//該訂單所有資料
	
	public List<Order_listVO> getOrderInfo(String order_id){
		List<Order_listVO> empList = new ArrayList<Order_listVO>();
		Order_listVO vo2 = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			con = ds.getConnection();
			String sql = "select * from ORDER_INFO inner Join ORDER_LIST on ORDER_INFO.ORDER_ID =ORDER_LIST.ORDER_ID where  ORDER_INFO.order_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, order_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				vo2 = new Order_listVO();
				vo2.setOrder_list_id(rs.getString("order_list_id"));
				vo2.setOrder_id(rs.getString("order_id"));
				vo2.setClass_id(rs.getString("class_id"));
				vo2.setPurchase_plan(rs.getString("purchase_plan"));
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
		public void inser2(Order_listVO Order_listVO, Connection con) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, Order_listVO.getOrder_id());
			pstmt.setString(2, Order_listVO.getClass_id());
			pstmt.setString(3, Order_listVO.getPurchase_plan());
			pstmt.executeUpdate();
		}
		catch (SQLException se) {
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
	
}

	
