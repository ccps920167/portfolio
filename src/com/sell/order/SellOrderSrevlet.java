package com.sell.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.class_info.model.Class_infoVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.member_info.model.Member_infoVO;
import com.order_info.model.Order_infoVO;
import com.sub_class.model.Sub_classVO;

/**
 * Servlet implementation class Class_infoServlet
 */
@WebServlet(urlPatterns = { "/sell/SellOrderSrevlet" })
public class SellOrderSrevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 得到時間段List
	public static List<String> findDates(Date dBegin, Date dEnd) {
		java.text.SimpleDateFormat df_db = new SimpleDateFormat("yyyy-MM-dd");
		List lDate = new ArrayList();
		lDate.add(df_db.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(dEnd);
		while (dEnd.after(calBegin.getTime())) {
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(df_db.format(calBegin.getTime()));
		}
		return lDate;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 要處理session 存取權
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json;charset=utf-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();
		System.out.println(action);
		if ("sell".equals(action)) {
			try {
				String start_dateTime = req.getParameter("start_dateTime");
				String end_dateTime = req.getParameter("end_dateTime");
				SellService Srv = new SellService();
				java.text.SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				SellVO sellVO = Srv.getAll(new Timestamp(df.parse(start_dateTime).getTime()),
						new Timestamp(df.parse(end_dateTime).getTime()));
				
				HttpSession session = req.getSession();
				session.setAttribute("sellVO", sellVO);
				
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
				
				// 大JSON : MAP標籤(只有四個key)
				Map<String, List<Map<String, List<String>>>> json = (Map<String, List<Map<String, List<String>>>>) new HashMap<String, List<Map<String, List<String>>>>();

				// 建立時間段List
				java.text.SimpleDateFormat df_db = new SimpleDateFormat("yyyy-MM-dd");
				List<String> Date = findDates(df_db.parse(start_dateTime), df_db.parse(end_dateTime));
				Map<String, List<String>> labelsDateMap = (Map<String, List<String>>) new HashMap<String, List<String>>();

				/************************************
				 * 營業額趨勢圖 + 購買人次趨勢圖
				 ************************************/

				// List : map value(兩個元素)
				List<Map<String, List<String>>> Turnover = new ArrayList<Map<String, List<String>>>();
				List<Map<String, List<String>>> Purchasers = new ArrayList<Map<String, List<String>>>();

				// map : list 內值 key labels / data
				Map<String, List<String>> dataTurnoverMap = (Map<String, List<String>>) new HashMap<String, List<String>>();
				Map<String, List<String>> dataPurchasersMap = (Map<String, List<String>>) new HashMap<String, List<String>>();

				// List : list 內值 value labels / data
				List<String> dataTurnover = (List<String>) new ArrayList<String>();
				List<String> dataPurchasers = (List<String>) new ArrayList<String>();
				for (int i = 0; i < Date.size(); i++) {
					dataTurnover.add("0");
					dataPurchasers.add("0");
				}

				Set<Order_infoVO> key = sellVO.getOrder_infoVOList().keySet();
				for (int i = 0; i < Date.size(); i++) {
					for (Order_infoVO order_infoVo : key) {
						if (Date.get(i).equals(df_db.format(order_infoVo.getOrder_time()))) {
							// 如果labels 沒有該日期
							if (dataTurnover.get(i).equals("0")) {
								dataTurnover.set(i, String.valueOf(order_infoVo.getAmount()));
								dataPurchasers.set(i, "1");
							} else {
								int amount = Integer.parseInt(dataTurnover.get(i)) + order_infoVo.getAmount();
								dataTurnover.set(i, String.valueOf(amount));
								int Purchaser = Integer.parseInt(dataPurchasers.get(i)) + 1;
								dataPurchasers.set(i, String.valueOf(Purchaser));
							}
						}
					}
				}

				/************************************
				 * 會員人數趨勢圖
				 ************************************/
				// List : map value(兩個元素)
				List<Map<String, List<String>>> newMember = new ArrayList<Map<String, List<String>>>();

				// 會員人數趨勢圖
				// map : list 內值 key labels / data
				Map<String, List<String>> dataNewMemberMap = (Map<String, List<String>>) new HashMap<String, List<String>>();

				// List : list 內值 value labels / data
				List<String> dataNewMember = (List<String>) new ArrayList<String>();
				for (int i = 0; i < Date.size(); i++) {
					dataNewMember.add("0");
				}

				for (int i = 0; i < Date.size(); i++) {
					for (Member_infoVO member_info : sellVO.getMember_infoVOList()) {
						if (Date.get(i).equals(df_db.format(member_info.getRegister_time()))) {
							if (dataNewMember.get(i).equals("0")) {
								dataNewMember.set(i, "1");
							} else {
								int NewMember = Integer.parseInt(dataNewMember.get(i)) + 1;
								dataNewMember.set(i, String.valueOf(NewMember));
							}
						}
					}
				}

				/************************************
				 * 課程數量分布圖
				 ************************************/
				// List : map value(兩個元素)
				List<Map<String, List<String>>> subClass = new ArrayList<Map<String, List<String>>>();

				// 課程數量分布圖
				// map : list 內值 key labels / data
				Map<String, List<String>> dataSubClassMap = (Map<String, List<String>>) new HashMap<String, List<String>>();

				// 取得子課程
				List<String> labelsSub_class = (List<String>) new ArrayList<String>();
				Map<String, List<String>> labelsSub_classMap = (Map<String, List<String>>) new HashMap<String, List<String>>();
				Map<String, Sub_classVO> Sub_classMap = (Map<String, Sub_classVO>) new HashMap<String, Sub_classVO>();
				ServletContext context = getServletContext();
				Map<String, List<Sub_classVO>> MainSubClass = (Map<String, List<Sub_classVO>>) context
						.getAttribute("MainSubClass");
				for (String keys : (Set<String>) MainSubClass.keySet()) {
					for (Sub_classVO Sub_classVO : MainSubClass.get(keys)) {
						Sub_classMap.put(Sub_classVO.getSubClass_id(), Sub_classVO);
					}
				}

				// List : list 內值 value labels / data
				List<String> dataSubClass = (List<String>) new ArrayList<String>();

				// 課程數量分布圖


				for (Class_infoVO class_infoVO : sellVO.getOrderClass_infoVO()) {
					if (labelsSub_class
							.indexOf((Sub_classMap.get(class_infoVO.getSubclass_id()).getSubClass_name())) == -1) {
						labelsSub_class.add((Sub_classMap.get(class_infoVO.getSubclass_id()).getSubClass_name()));
						dataSubClass.add("1");
					}else {
						int index = labelsSub_class
								.indexOf((Sub_classMap.get(class_infoVO.getSubclass_id()).getSubClass_name()));
						dataSubClass.set(index, String.valueOf(Integer.parseInt(dataSubClass.get(index))+1));
					}

				}

				/************************************
				 * 放入容器
				 ************************************/
				labelsDateMap.put("labels", Date);
				dataTurnoverMap.put("data", dataTurnover);
				dataPurchasersMap.put("data", dataPurchasers);
				dataNewMemberMap.put("data", dataNewMember);

				labelsSub_classMap.put("labels", labelsSub_class);
				dataSubClassMap.put("data", dataSubClass);

				Turnover.add(labelsDateMap);
				Turnover.add(dataTurnoverMap);

				Purchasers.add(labelsDateMap);
				Purchasers.add(dataPurchasersMap);

				newMember.add(labelsDateMap);
				newMember.add(dataNewMemberMap);

				subClass.add(labelsSub_classMap);
				subClass.add(dataSubClassMap);

				json.put("Turnover", Turnover);
				json.put("Purchasers", Purchasers);
				json.put("newMember", newMember);
				json.put("subClass", subClass);

				// 轉成Json格式
				String jsonStr = gson.toJson(json);
				System.out.println(jsonStr);

				// 回傳到前端AJAX
				out.write(jsonStr);
				out.flush();
				out.close();

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
}