package com.allClass_info.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.class_chapter.model.Class_chapterVO;
import com.class_info.model.Class_infoVO;
import com.class_unit.model.Class_unitVO;
import com.main_message.model.Main_messageVO;
import com.member_info.model.Member_infoVO;
import com.order_info.model.Order_infoVO;
import com.order_list.model.Order_listVO;
import com.student_homework.model.Student_homeworkVO;
import com.sub_class.model.Sub_classVO;
import com.sub_message.model.Sub_messageVO;
import com.teacher_homework.model.Teacher_homeworkVO;
import com.test.model.TestVO;

public class AllClass_infoJDBCDAO implements AllClass_info_interface {

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
	public AllClass_infoVO getInfo(String class_id) {

		AllClass_infoVO allClass_infoVO = new AllClass_infoVO();
		// 課程資料 *
		Class_infoVO class_info = new Class_infoVO() ;
		// 章節+單元資料*
		Map<Class_chapterVO, List<Class_unitVO>> chapterAndUnit = new TreeMap<Class_chapterVO, List<Class_unitVO>>();
		// 訊息資料 *
		Map<Main_messageVO, List<Sub_messageVO>> messageClass = new HashMap<Main_messageVO, List<Sub_messageVO>>();
		Map<Main_messageVO, List<Sub_messageVO>> messageLearn = new HashMap<Main_messageVO, List<Sub_messageVO>>();
		// 類別資料*
		Sub_classVO sub_class = null;
		// 老師資料 *
		Member_infoVO teacher = null;
		// 訂單資料*
		Map<Order_infoVO, List<Order_listVO>> Order = new HashMap<Order_infoVO, List<Order_listVO>>();
		// 學生資料 *
		Set<String> students = new HashSet<String>();
		// 課業資料
		Map<Teacher_homeworkVO, List<Student_homeworkVO>> homework = new TreeMap<Teacher_homeworkVO, List<Student_homeworkVO>>();
		// 考試資料
		List<TestVO> test = new ArrayList<TestVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			
			con = ds.getConnection();
			
			try {
				// class
				String sql_class = " select * " + " from class_info " + " WHERE class_id = ?" ;
				pstmt = con.prepareStatement(sql_class);
				pstmt.setString(1, class_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
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
				}
				allClass_infoVO.setClass_infoVO(class_info);;
				} catch (Exception e) {
					System.err.println(e.getMessage()+"無資料");
				}
			/*****************************************************************************/

			// Map<Class_chapterVO,List<Class_unitVO>> chapterAndUnit = null ;
			try {
			
			String sql_chapter = " SELECT * " + " FROM CLASS_CHAPTER "
					+ " INNER JOIN CLASS_UNIT ON CLASS_CHAPTER.CHAPTER_ID = CLASS_UNIT.CHAPTER_ID "
					+ " WHERE CLASS_CHAPTER.CLASS_ID = ?" + " ORDER BY CLASS_CHAPTER.CHAPTER_ID";
			pstmt = con.prepareStatement(sql_chapter);
			pstmt.setString(1, class_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Class_chapterVO class_chapterVO = new Class_chapterVO();
				class_chapterVO.setChapter_id(rs.getString("CHAPTER_ID"));
				class_chapterVO.setClass_id(rs.getString("CLASS_ID"));
				class_chapterVO.setChapter_name(rs.getString("CHAPTER_NAME"));
				Class_unitVO vo = null;
				if (chapterAndUnit.get(class_chapterVO) == null) {
					List<Class_unitVO> list = new ArrayList<Class_unitVO>();
					vo = new Class_unitVO();
					vo.setUnit_id(rs.getString("UNIT_ID"));
					vo.setChapter_id(rs.getString("CHAPTER_ID"));
					vo.setUnit_name(rs.getString("UNIT_NAME"));
					vo.setVideo_long(rs.getString("VIDEO_LONG"));
					vo.setVideo(rs.getBytes("VIDEO"));
					vo.setVideo_updatetime(rs.getTimestamp("VIDEO_UPDATETIME"));
					vo.setVideo_status(rs.getInt("VIDEO_STATUS"));

					list.add(vo);
					chapterAndUnit.put(class_chapterVO, list);
				} else {
					vo = new Class_unitVO();
					vo.setUnit_id(rs.getString("UNIT_ID"));
					vo.setChapter_id(rs.getString("CHAPTER_ID"));
					vo.setUnit_name(rs.getString("UNIT_NAME"));
					vo.setVideo_long(rs.getString("VIDEO_LONG"));
					vo.setVideo(rs.getBytes("VIDEO"));
					vo.setVideo_updatetime(rs.getTimestamp("VIDEO_UPDATETIME"));
					vo.setVideo_status(rs.getInt("VIDEO_STATUS"));
					chapterAndUnit.get(class_chapterVO).add(vo);
				}
			}
			allClass_infoVO.setChapterAndUnit(chapterAndUnit);
			} catch (Exception e) {
				System.err.println(e.getMessage()+"無chapterAndUnit");
			}
			/*****************************************************************************/
			
			// 取得訂單資訊和明細
			
			String sql_order = "SELECT * " + " FROM ORDER_INFO "
					+ " INNER JOIN ORDER_LIST ON ORDER_INFO.ORDER_ID = ORDER_LIST.ORDER_ID "
					+ " INNER JOIN CLASS_INFO ON ORDER_LIST.CLASS_ID = CLASS_INFO.CLASS_ID "
					+ " WHERE CLASS_INFO.CLASS_ID = ?" + " ORDER BY ORDER_TIME";
			pstmt = con.prepareStatement(sql_order);
			pstmt.setString(1, class_id);
			rs = pstmt.executeQuery();
			Order_infoVO vo = null;
			Order_listVO vo2 = null;

			while (rs.next()) {
				try {
				students.add(rs.getString("member_id"));
				} catch (Exception e) {
					System.err.println("學生"+e.getMessage());
				}
				/***************************************************************************/
				try {
				vo = new Order_infoVO();

				vo.setOrder_ID(rs.getString("order_ID"));
				vo.setOrder_status(rs.getInt("order_status"));
				vo.setMember_id(rs.getString("member_id"));
				vo.setOrder_time(rs.getTimestamp("order_time"));
				vo.setPay_way(rs.getString("pay_way"));
				vo.setPayment_time(rs.getTimestamp("payment_time"));
				vo.setCoupon_ID(rs.getString("coupon_ID"));
				vo.setAmount(rs.getInt("amount"));

				if (Order.get(vo) == null) {
					List<Order_listVO> list = new ArrayList<Order_listVO>();
					vo2 = new Order_listVO();
					vo2.setOrder_list_id(rs.getString("order_list_id"));
					vo2.setOrder_id(rs.getString("order_id"));
					vo2.setClass_id(rs.getString("class_id"));
					vo2.setPurchase_plan(rs.getString("purchase_plan"));
					list.add(vo2);
					Order.put(vo, list);
				} else {
					vo2 = new Order_listVO();
					vo2.setOrder_list_id(rs.getString("order_list_id"));
					vo2.setOrder_id(rs.getString("order_id"));
					vo2.setClass_id(rs.getString("class_id"));
					vo2.setPurchase_plan(rs.getString("purchase_plan"));
					Order.get(vo).add(vo2);
				}
				} catch (Exception e) {
					System.err.println("訂單"+e.getMessage());
				}
			}
			allClass_infoVO.setOrder(Order);
			allClass_infoVO.setStudent(students);
			
			/*****************************************************************************/
			try {
			// teacher
			String sql_member = " select * " + " from member_info " + " WHERE member_id = ?" + " order by member_id";
			pstmt = con.prepareStatement(sql_member);
			pstmt.setString(1, class_info.getMember_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				teacher = new Member_infoVO();
				teacher.setMember_id(rs.getString("member_id"));
				teacher.setMember_name(rs.getString("member_name"));	
				teacher.setMember_email(rs.getString("member_email"));
				teacher.setMember_password(rs.getString("member_password"));
				teacher.setMember_role(rs.getInt("member_role"));
				teacher.setMember_gender(rs.getInt("member_gender"));
				teacher.setMember_birthday(rs.getDate("member_birthday"));
				teacher.setMember_occupation(rs.getString("member_occupation"));
				teacher.setMember_address(rs.getString("member_address"));
				teacher.setMember_invoice(rs.getString("member_invoice"));
				teacher.setMember_pic(rs.getBytes("member_pic"));
				teacher.setTeachclass_on(rs.getInt("teachclass_on"));
				teacher.setLearnclass_on(rs.getInt("learnclass_on"));
				teacher.setMember_homework(rs.getInt("member_homework"));
				teacher.setMember_about(rs.getString("member_about"));
				teacher.setMember_good_for(rs.getString("member_good_for"));
				teacher.setRegister_time(rs.getTimestamp("Register_time"));
				teacher.setMember_update(rs.getTimestamp("member_update"));
				teacher.setTraccount(rs.getString("traccount"));
				teacher.setBank_code(rs.getString("bank_code"));
			}
			allClass_infoVO.setTeacher(teacher);
			} catch (Exception e) {
				System.err.println(e.getMessage()+"無teacher");
			}
			/*****************************************************************************/
			// sub_class + main_class
			String sql_sub_class = " select * " + " from sub_class " + " WHERE SUBCLASS_ID = ?";
			pstmt = con.prepareStatement(sql_sub_class);
			pstmt.setString(1, class_info.getSubclass_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sub_class = new Sub_classVO();
				sub_class.setSubClass_id(rs.getString("SUBCLASS_id"));
				sub_class.setSubClass_name(rs.getString("SUBCLASS_NAME"));
				sub_class.setSubClass_status(rs.getInt("SUBCLASS_STATUS"));
				sub_class.setMainClass_id(rs.getString("MAINCLASS_id"));
				allClass_infoVO.setSub_class(sub_class);
			}

			/*****************************************************************************/
			// 訊息資料
//			Map<Main_messageVO,List<Sub_messageVO>> messageClass = null ;
//			Map<Main_messageVO,List<Sub_messageVO>> messageLearn = null ;
			try {
			String sql_message = "SELECT * " + " FROM MAIN_MESSAGE "
					+ " INNER JOIN SUB_MESSAGE ON MAIN_MESSAGE.MAINMSG_ID = SUB_MESSAGE.MAINMSG_ID "
					+ " WHERE MAIN_MESSAGE.CLASS_ID = ?" + " ORDER BY MAIN_MESSAGE.MAINMSG_ID ";
			pstmt = con.prepareStatement(sql_message);
			pstmt.setString(1, class_id);
			rs = pstmt.executeQuery();
			Main_messageVO main_messageVO = null;
			while (rs.next()) {
				main_messageVO = new Main_messageVO();
				main_messageVO = new Main_messageVO();
				main_messageVO.setMainmsg_id(rs.getString("mainmsg_id"));
				main_messageVO.setClass_id(rs.getString("class_id"));
				main_messageVO.setMember_id(rs.getString("member_id"));
				main_messageVO.setMainmsg_time(rs.getTimestamp("mainmsg_time"));
				main_messageVO.setMainmsg_text(rs.getString("mainmsg_text"));
				main_messageVO.setMsg_source(rs.getString("msg_source"));
				main_messageVO.setMsg_status(rs.getInt("msg_status"));

				Sub_messageVO sub_messageVO = null;
				if (messageClass.get(main_messageVO) == null || messageLearn.get(main_messageVO) == null) {
					List<Sub_messageVO> list = null;
					if ("學習頁面".equals(main_messageVO.getMsg_source())) {
						list = new ArrayList<Sub_messageVO>();
						sub_messageVO = new Sub_messageVO();
						sub_messageVO.setSubmsg_id(rs.getString("submsg_id"));
						sub_messageVO.setMainmsg_id(rs.getString("mainmsg_id"));
						sub_messageVO.setMember_id(rs.getString("member_id"));
						sub_messageVO.setSubmsg_time(rs.getTimestamp("submsg_time"));
						sub_messageVO.setSubmsg_text(rs.getString("submsg_text"));
						sub_messageVO.setSubmsg_status(rs.getInt("submsg_status"));
						list.add(sub_messageVO);
						messageLearn.put(main_messageVO, list);
					} else if ("課程頁面".equals(main_messageVO.getMsg_source())) {
						list = new ArrayList<Sub_messageVO>();
						sub_messageVO = new Sub_messageVO();
						sub_messageVO.setSubmsg_id(rs.getString("submsg_id"));
						sub_messageVO.setMainmsg_id(rs.getString("mainmsg_id"));
						sub_messageVO.setMember_id(rs.getString("member_id"));
						sub_messageVO.setSubmsg_time(rs.getTimestamp("submsg_time"));
						sub_messageVO.setSubmsg_text(rs.getString("submsg_text"));
						sub_messageVO.setSubmsg_status(rs.getInt("submsg_status"));
						list.add(sub_messageVO);
						messageClass.put(main_messageVO, list);
					}
				} else if ("學習頁面".equals(main_messageVO.getMsg_source())) {

					sub_messageVO = new Sub_messageVO();
					sub_messageVO.setSubmsg_id(rs.getString("submsg_id"));
					sub_messageVO.setMainmsg_id(rs.getString("mainmsg_id"));
					sub_messageVO.setMember_id(rs.getString("member_id"));
					sub_messageVO.setSubmsg_time(rs.getTimestamp("submsg_time"));
					sub_messageVO.setSubmsg_text(rs.getString("submsg_text"));
					sub_messageVO.setSubmsg_status(rs.getInt("submsg_status"));
					messageLearn.get(main_messageVO).add(sub_messageVO);

				} else if ("課程頁面".equals(main_messageVO.getMsg_source())) {
					sub_messageVO = new Sub_messageVO();
					sub_messageVO.setSubmsg_id(rs.getString("submsg_id"));
					sub_messageVO.setMainmsg_id(rs.getString("mainmsg_id"));
					sub_messageVO.setMember_id(rs.getString("member_id"));
					sub_messageVO.setSubmsg_time(rs.getTimestamp("submsg_time"));
					sub_messageVO.setSubmsg_text(rs.getString("submsg_text"));
					sub_messageVO.setSubmsg_status(rs.getInt("submsg_status"));
					messageClass.get(main_messageVO).add(sub_messageVO);
				}
			}
			allClass_infoVO.setMessageClass(messageClass);
			allClass_infoVO.setMessageLearn(messageLearn);

			} catch (Exception e) {
				System.err.println(e.getMessage()+"無message");
			}
			/*****************************************************************************/
//			//課業資料
//			Map <Teacher_homeworkVO,List<Student_homeworkVO>> homework;
			try {
			String unit_ids = get_WhereCondition(chapterAndUnit);

			String sql_homework = " SELECT * " + " FROM TEACHER_HOMEWORK "
					+ " INNER JOIN STUDENT_HOMEWORK ON  TEACHER_HOMEWORK.TEACHERHW_ID  = STUDENT_HOMEWORK.TEACHERHW_ID"
					+ unit_ids + " ORDER BY TEACHER_HOMEWORK.UNIT_ID ";
			pstmt = con.prepareStatement(sql_homework);
			rs = pstmt.executeQuery();

			Teacher_homeworkVO teacher_homeworkVO = null;
			while (rs.next()) {
				teacher_homeworkVO = new Teacher_homeworkVO();
				teacher_homeworkVO.setTeacherhw_id(rs.getString("TEACHERHW_ID"));
				teacher_homeworkVO.setUnit_id(rs.getString("UNIT_ID"));
				teacher_homeworkVO.setHw_name(rs.getString("HW_NAME"));
				teacher_homeworkVO.setHw_content(rs.getString("HW_CONTENT"));
				teacher_homeworkVO.setFile_data(rs.getBytes("FILE_DATA"));
				teacher_homeworkVO.setHw_uploadtime(rs.getTimestamp("HW_UPLOADTIME"));
				teacher_homeworkVO.setHw_updatetime(rs.getTimestamp("HW_UPDATETIME"));

				Student_homeworkVO student_homeworkVO = null;
				if (homework.get(teacher_homeworkVO) == null) {
					List<Student_homeworkVO> list = new ArrayList<Student_homeworkVO>();
					student_homeworkVO = new Student_homeworkVO();
					student_homeworkVO.setStudenthw_id(rs.getString("STUDENTHW_ID"));
					student_homeworkVO.setTeacherhw_id(rs.getString("TEACHERHW_ID"));
					student_homeworkVO.setMember_id(rs.getString("MEMBER_ID"));
					student_homeworkVO.setFile_data(rs.getBytes("FILE_DATA"));
					student_homeworkVO.setHw_uploadtime(rs.getTimestamp("HW_UPLOADTIME"));
					student_homeworkVO.setHw_updatetime(rs.getTimestamp("HW_UPDATETIME"));
					list.add(student_homeworkVO);
					homework.put(teacher_homeworkVO, list);

				} else {
					student_homeworkVO = new Student_homeworkVO();
					student_homeworkVO.setStudenthw_id(rs.getString("STUDENTHW_ID"));
					student_homeworkVO.setTeacherhw_id(rs.getString("TEACHERHW_ID"));
					student_homeworkVO.setMember_id(rs.getString("MEMBER_ID"));
					student_homeworkVO.setFile_data(rs.getBytes("FILE_DATA"));
					student_homeworkVO.setHw_uploadtime(rs.getTimestamp("HW_UPLOADTIME"));
					student_homeworkVO.setHw_updatetime(rs.getTimestamp("HW_UPDATETIME"));
					homework.get(teacher_homeworkVO).add(student_homeworkVO);
				}
			}
			allClass_infoVO.setHomework(homework);
			} catch (Exception e) {
				System.err.println(e.getMessage()+"無homework");
			}
			/*****************************************************************************/
//			List<TestVO> test = null;
			try {
			String testUnit_ids = get_WhereConditionTest(chapterAndUnit);

			String sql_test = " SELECT * " + " FROM TEST "
					+ testUnit_ids + " ORDER BY TEST_ID ";
			pstmt = con.prepareStatement(sql_test);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				TestVO testVO = new TestVO();
				testVO.setTest_id(rs.getString("test_id"));
				testVO.setUnit_id(rs.getString("unit_id"));
				testVO.setTest_answer(rs.getString("test_answer"));
				testVO.setTest_content(rs.getString("test_content"));
				testVO.setOpta(rs.getString("opta"));
				testVO.setOptb(rs.getString("optb"));
				testVO.setOptc(rs.getString("optc"));
				testVO.setOptd(rs.getString("optd"));
				test.add(testVO);
			}
			allClass_infoVO.setTest(test);
			} catch (Exception e) {
				System.err.println(e.getMessage()+"無test");
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
		return allClass_infoVO;
	}

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		if ("TEACHER_HOMEWORK.UNIT_ID".equals(columnName)) // 用於其他
			aCondition = columnName + "= '" + value+"' ";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<Class_chapterVO, List<Class_unitVO>> map) {
		Set<Class_chapterVO> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (Class_chapterVO key : keys) {
			List<Class_unitVO> List = (List<Class_unitVO>) map.get(key);
			for (Class_unitVO vlaue : List) {

				if (vlaue.getUnit_id() != null && vlaue.getUnit_id().trim().length() != 0) {
					count++;
					String aCondition = get_aCondition_For_Oracle("TEACHER_HOMEWORK.UNIT_ID",
							vlaue.getUnit_id().trim());

					if (count == 1)
						whereCondition.append(" where " + aCondition);
					else
						whereCondition.append(" or " + aCondition);
				}
			}
		}
		return whereCondition.toString();
	}
	
	
	public static String get_aCondition_For_OracleTest(String columnName, String value) {

		String aCondition = null;

		if ("UNIT_ID".equals(columnName)) // 用於其他
			aCondition = columnName + "= '" + value+"' ";

		return aCondition + " ";
	}

	public static String get_WhereConditionTest(Map<Class_chapterVO, List<Class_unitVO>> map) {
		Set<Class_chapterVO> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (Class_chapterVO key : keys) {
			List<Class_unitVO> List = (List<Class_unitVO>) map.get(key);
			for (Class_unitVO vlaue : List) {

				if (vlaue.getUnit_id() != null && vlaue.getUnit_id().trim().length() != 0) {
					count++;
					String aCondition = get_aCondition_For_OracleTest("UNIT_ID",
							vlaue.getUnit_id().trim());

					if (count == 1)
						whereCondition.append(" where " + aCondition);
					else
						whereCondition.append(" or " + aCondition);
				}
			}
		}
		return whereCondition.toString();
	}
}