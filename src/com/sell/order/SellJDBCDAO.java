package com.sell.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.class_info.model.Class_infoVO;
import com.member_info.model.Member_infoVO;
import com.order_info.model.Order_infoVO;
import com.order_list.model.Order_listVO;


public class SellJDBCDAO implements SellDAO_interface {
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
	public SellVO getAll(Timestamp start_dateTime, Timestamp end_dateTime) {
		//SellVO 物件
		SellVO sellVO = new SellVO();
		//取得訂單資訊和明細
		Map <Order_infoVO,List<Order_listVO>> Order_infoVOList = new LinkedHashMap<Order_infoVO, List<Order_listVO>>();
		//單元內的課程明細
		Set<Class_infoVO> OrderClass_infoVO = new HashSet<Class_infoVO>();
		//會員明細
		List<Member_infoVO> Member_infoVOList = new ArrayList<Member_infoVO>(); 
		//課程明細
		List<Class_infoVO> Class_infoVOList = new ArrayList<Class_infoVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			
			con = ds.getConnection();
			//取得訂單資訊和明細
			String sql_order = 
					"SELECT * "
					+" FROM ORDER_INFO "
					+" INNER JOIN ORDER_LIST ON ORDER_INFO.ORDER_ID = ORDER_LIST.ORDER_ID "
					+" INNER JOIN CLASS_INFO ON ORDER_LIST.CLASS_ID = CLASS_INFO.CLASS_ID "
					+" WHERE ORDER_INFO.ORDER_TIME >= ? AND ORDER_INFO.ORDER_TIME <= ?" 
					+" ORDER BY ORDER_TIME";
			pstmt = con.prepareStatement(sql_order);
			pstmt.setTimestamp(1, start_dateTime);
			pstmt.setTimestamp(2, end_dateTime);
			rs = pstmt.executeQuery();
			
			Order_listVO vo2 = null;

			while (rs.next()) {
				Class_infoVO class_info  = new Class_infoVO();
				class_info.setClass_id(rs.getString("class_id"));
				class_info.setMember_id(rs.getString("member_id"));
				class_info.setSubclass_id(rs.getString("subclass_id"));
				class_info.setAdmin_id(rs.getString("admin_id"));
				class_info.setClass_name(rs.getString("class_name"));
				class_info.setClass_status(rs.getInt("class_status"));
				class_info.setStartfund_date(rs.getTimestamp("startfund_date"));
				class_info.setStartclass_time(rs.getTimestamp("startclass_time"));
				class_info.setClass_description(rs.getString("class_description"));
				class_info.setStartfund_price(rs.getInt("startfund_price"));
				class_info.setPeople_threshold(rs.getInt("people_threshold"));
				class_info.setClass_length(rs.getString("class_length"));
				class_info.setUpdate_time(rs.getTimestamp("update_time"));
				
				OrderClass_infoVO.add(class_info);
				
				/***************************************************************************/
				Order_infoVO vo  = new Order_infoVO();
				vo.setOrder_ID(rs.getString("order_ID"));
				vo.setOrder_status(rs.getInt("order_status"));
				vo.setMember_id(rs.getString("member_id"));
				vo.setOrder_time(rs.getTimestamp("order_time"));
				vo.setPay_way(rs.getString("pay_way"));
				vo.setPayment_time(rs.getTimestamp("payment_time"));
				vo.setCoupon_ID(rs.getString("coupon_ID"));
				vo.setAmount(rs.getInt("amount"));
				
				if(Order_infoVOList.get(vo)==null) {
					List<Order_listVO> list = new ArrayList<Order_listVO>();
					vo2 = new Order_listVO();
					vo2.setOrder_list_id(rs.getString("order_list_id"));
					vo2.setOrder_id(rs.getString("order_id"));
					vo2.setClass_id(rs.getString("class_id"));
					vo2.setPurchase_plan(rs.getString("purchase_plan"));
					list.add(vo2);
					Order_infoVOList.put(vo,list);
				}else {
					vo2 = new Order_listVO();
					vo2.setOrder_list_id(rs.getString("order_list_id"));
					vo2.setOrder_id(rs.getString("order_id"));
					vo2.setClass_id(rs.getString("class_id"));
					vo2.setPurchase_plan(rs.getString("purchase_plan"));
					Order_infoVOList.get(vo).add(vo2);
				}
			}
			sellVO.setOrderClass_infoVO(OrderClass_infoVO);
			sellVO.setOrder_infoVOList(Order_infoVOList);
			/*****************************************************************************/
			//取得會員明細
			String sql_member = 
					"select * "
					+" from member_info "
					+" WHERE REGISTER_TIME >= ? AND REGISTER_TIME <= ?" 
					+" order by REGISTER_TIME";
			pstmt = con.prepareStatement(sql_member);
			pstmt.setTimestamp(1, start_dateTime);
			pstmt.setTimestamp(2, end_dateTime);
			
			rs = pstmt.executeQuery();
			Member_infoVO member = null;
			while (rs.next()) {
				member = new Member_infoVO();
				member.setMember_id(rs.getString("member_id"));
				member.setMember_name(rs.getString("member_name"));
				member.setMember_email(rs.getString("member_email"));
				member.setMember_password(rs.getString("member_password"));
				member.setMember_role(rs.getInt("member_role"));
				member.setMember_gender(rs.getInt("member_gender"));
				member.setMember_birthday(rs.getDate("member_birthday"));
				member.setMember_occupation(rs.getString("member_occupation"));
				member.setMember_address(rs.getString("member_address"));
				member.setMember_invoice(rs.getString("member_invoice"));
				member.setMember_pic(rs.getBytes("member_pic"));
				member.setTeachclass_on(rs.getInt("teachclass_on"));
				member.setLearnclass_on(rs.getInt("learnclass_on"));
				member.setMember_homework(rs.getInt("member_homework"));
				member.setMember_about(rs.getString("member_about"));
				member.setMember_good_for(rs.getString("member_good_for"));
				member.setRegister_time(rs.getTimestamp("Register_time"));
				member.setMember_update(rs.getTimestamp("member_update"));
				member.setTraccount(rs.getString("traccount"));
				member.setBank_code(rs.getString("bank_code"));
				Member_infoVOList.add(member);
			}
			sellVO.setMember_infoVOList(Member_infoVOList);
			
			/*****************************************************************************/
			//課程明細
			String sql_class = 
					" select * "
					+" from class_info"
					+" WHERE STARTFUND_DATE >= ? AND STARTFUND_DATE <= ?"
					+" order by STARTFUND_DATE";
			pstmt = con.prepareStatement(sql_class);
			pstmt.setTimestamp(1, start_dateTime);
			pstmt.setTimestamp(2, end_dateTime);
			rs = pstmt.executeQuery();
			Class_infoVO class_info = null;
			while (rs.next()) {
				class_info  = new Class_infoVO();
				class_info.setClass_id(rs.getString("class_id"));
				class_info.setMember_id(rs.getString("member_id"));
				class_info.setSubclass_id(rs.getString("subclass_id"));
				class_info.setAdmin_id(rs.getString("admin_id"));
				class_info.setClass_name(rs.getString("class_name"));
				class_info.setClass_status(rs.getInt("class_status"));
				class_info.setStartfund_date(rs.getTimestamp("startfund_date"));
				class_info.setStartclass_time(rs.getTimestamp("startclass_time"));
				class_info.setClass_description(rs.getString("class_description"));
				class_info.setStartfund_price(rs.getInt("startfund_price"));
				class_info.setPeople_threshold(rs.getInt("people_threshold"));
				class_info.setClass_length(rs.getString("class_length"));
				class_info.setUpdate_time(rs.getTimestamp("update_time"));
				
				Class_infoVOList.add(class_info);
			}
			sellVO.setClass_infoVOList(Class_infoVOList);
			
			
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
		return sellVO;
	}
	
}