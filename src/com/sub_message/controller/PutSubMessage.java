package com.sub_message.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.evaluation.model.EvaluationService;
import com.evaluation.model.EvaluationVO;
import com.main_message.model.Main_messageService;
import com.main_message.model.Main_messageVO;
import com.member_info.model.Member_infoService;
import com.member_info.model.Member_infoVO;
import com.sub_message.model.Sub_messageService;
import com.sub_message.model.Sub_messageVO;

@WebServlet("/PutSubMessage/PutSubMessage")
public class PutSubMessage extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		try {
			String json = readJSONString(req);
			JSONObject jsonObject = new JSONObject(json);
			String action = jsonObject.getString("action");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if ("insert".equals(action)) {

				// 將資料包裝到VO
				Sub_messageVO SubMsgVO = new Sub_messageVO();
				
				SubMsgVO.setMainmsg_id(jsonObject.getString("mainmsg_id"));
				SubMsgVO.setMember_id(jsonObject.getString("member_id"));
				SubMsgVO.setSubmsg_status(1);
				SubMsgVO.setSubmsg_text(jsonObject.getString("submsg_text"));
				SubMsgVO.setSubmsg_time(new java.sql.Timestamp(new Date().getTime()));

				JSONObject AllJsonObject = new JSONObject(SubMsgVO);

				// 進入資料庫
				Sub_messageService Srv = new Sub_messageService();
				Srv.addSub_message(jsonObject.getString("mainmsg_id"), 
									jsonObject.getString("member_id"), 
									new java.sql.Timestamp(new Date().getTime()), 
									jsonObject.getString("submsg_text"), 
									1);

				// 回傳到前端AJAX
				out.write(AllJsonObject.toString());
				out.flush();
				out.close();
			}

			if ("getAll".equals(action)) {
				// 取回req內的Json資料 class_id
				String class_id = jsonObject.getString("class_id");
				String msg_source = jsonObject.getString("msg_source");

				// 進入資料庫取出資料 List<Main_messageVO>
				Main_messageService Srv = new Main_messageService();
				List<Main_messageVO> MsgList = Srv.getAll();
				List<Main_messageVO> MsgClass = new ArrayList<Main_messageVO>();
				Map<String, String> map = new HashMap<String, String>();
				List<Member_infoVO> Srv2 = new Member_infoService().getAll();

				// 跑迴圈 包進json內
				JSONObject AllJsonObject = new JSONObject();
				int i = 0;
				for (Member_infoVO mem : Srv2) {
					for (Main_messageVO item : MsgList) {
						if (item.getClass_id().equals(class_id)) {
							if (item.getMsg_source().equals(msg_source)) {
								if (mem.getMember_id().equals(item.getMember_id())) {
									map.put("mainMsg_id", item.getMainmsg_id());
									map.put("member_id", item.getMember_id());
									map.put("member_email", mem.getMember_email());
									map.put("class_id", item.getClass_id());
									map.put("mainmsg_text", item.getMainmsg_text());
									map.put("mainmsg_time", String.valueOf(sdf.format(item.getMainmsg_time())));
									AllJsonObject.put(String.valueOf(i), map);
									i++;
								}
							}
						}
					}
				}
				// 回傳到前端AJAX
				out.write(AllJsonObject.toString());
				out.flush();
				out.close();

			}

			if ("subMessage".equals(action)) {
				// 取回req內的Json資料 class_id
				String mainMsg_id = jsonObject.getString("mainMsg_id");

				// 進入資料庫取出資料 List<Main_messageVO>
				Sub_messageService Srv = new Sub_messageService();
				List<Sub_messageVO> MsgList = Srv.getAll();
				List<Sub_messageVO> MsgClass = new ArrayList<Sub_messageVO>();
				Map<String, String> map = new HashMap<String, String>();
				List<Member_infoVO> Srv2 = new Member_infoService().getAll();

				// 跑迴圈 包進json內
				JSONObject AllJsonObject = new JSONObject();
				int i = 0;
				for (Member_infoVO mem : Srv2) {
					for (Sub_messageVO item : MsgList) {
						if (item.getMainmsg_id().equals(mainMsg_id)) {
							if (item.getSubmsg_status() == 1) {
								if (mem.getMember_id().equals(item.getMember_id())) {
									map.put("member_email", mem.getMember_email());
									map.put("submsg_id", item.getMainmsg_id());
									map.put("member_id", item.getMember_id());
									map.put("submsg_time", String.valueOf(sdf.format(item.getSubmsg_time())));
									map.put("submsg_text", item.getSubmsg_text());
									AllJsonObject.put(String.valueOf(i), map);
									i++;
								}
							}
						}
					}
				}
				// 回傳到前端AJAX
				out.write(AllJsonObject.toString());
				out.flush();
				out.close();

			}
		} catch (

		JSONException e) {
			e.printStackTrace();
		}

	}

	/*********************** json物件轉string方法 ***********************/
	public String readJSONString(HttpServletRequest request) {
		StringBuffer json = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
