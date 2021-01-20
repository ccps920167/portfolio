package com.evaluation.controller;

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
import com.member_info.model.Member_infoService;
import com.member_info.model.Member_infoVO;

@WebServlet("/Evaluation/putEvaluation")
public class putEvaluation extends HttpServlet {
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

				// �N��ƥ]�˨�VO
				EvaluationVO evaluationVO = new EvaluationVO();
				evaluationVO.setClass_id(jsonObject.getString("class_id"));
				evaluationVO.setMember_id(jsonObject.getString("member_id"));
				evaluationVO.setEvaluation_class(jsonObject.getString("evaluation_class"));
				evaluationVO.setEvaluation_score(Integer.parseInt(jsonObject.getString("evaluation_score")));
				evaluationVO.setEvaluation_time(new java.sql.Timestamp(new Date().getTime()));
				evaluationVO.setEvaluation_status(1);

				JSONObject AllJsonObject = new JSONObject(evaluationVO);

				// �i�J��Ʈw
				EvaluationService Srv = new EvaluationService();
				Srv.addEvaluation(evaluationVO);

				// �^�Ǩ�e��AJAX
				System.out.println(AllJsonObject.toString());
				out.write(AllJsonObject.toString());
				out.flush();
				out.close();
			}

			if ("getAll".equals(action)) {
				// ���^req����Json��� class_id
				String class_id = jsonObject.getString("class_id");

				// �i�J��Ʈw���X��� List<EvaluationVO>
				EvaluationService Srv = new EvaluationService();
				List<EvaluationVO> evaluationList = Srv.getAll();
				List<EvaluationVO> evaluationClass = new ArrayList<EvaluationVO>();
				Map<String, String> map = new HashMap<String, String>();

				List<Member_infoVO> Srv2 = new Member_infoService().getAll();

				// �]�j�� �]�ijson��
				JSONObject AllJsonObject = new JSONObject();
				int i = 0;
				for (Member_infoVO mem : Srv2) {
					for (EvaluationVO item : evaluationList) {
						if (item.getClass_id().equals(class_id)) {
							if (mem.getMember_id().equals(item.getMember_id())) {
								// ��ID
								map.put("member_id", item.getMember_id());
								map.put("member_email", mem.getMember_email());
								map.put("class_id", item.getClass_id());
								map.put("evaluation_class", item.getEvaluation_class());
								map.put("evaluation_score", String.valueOf(item.getEvaluation_score()));
								map.put("evaluation_time", String.valueOf(sdf.format(item.getEvaluation_time())));
								map.put("evaluation_status", String.valueOf(item.getEvaluation_status()));
								AllJsonObject.put(String.valueOf(i), map);
								i++;
							}
						}
					}
				}
				// �^�Ǩ�e��AJAX
				out.write(AllJsonObject.toString());
				out.flush();
				out.close();

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/*********************** json������string��k ***********************/
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
