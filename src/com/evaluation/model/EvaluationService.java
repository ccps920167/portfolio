package com.evaluation.model;


import java.util.List;

import com.evaluation.model.EvaluationVO;



public class EvaluationService {
	private EvaluationDAO_interface dao;
	
	public EvaluationService() {
		dao = new EvaluationDAOJDBC();
	}

	public EvaluationVO addEvaluation(String class_id , String member_id , String evaluation_class , Integer evaluation_score , java.sql.Timestamp evaluation_time , Integer evaluation_status) {
		
		EvaluationVO evaluationVO = new EvaluationVO();
		evaluationVO.setClass_id(class_id);
		evaluationVO.setMember_id(member_id);
		evaluationVO.setEvaluation_class(evaluation_class);
		evaluationVO.setEvaluation_score(evaluation_score);
		evaluationVO.setEvaluation_time(evaluation_time);
		evaluationVO.setEvaluation_status(evaluation_status);
		dao.insert(evaluationVO);

		return evaluationVO;
	}

	//預留給 Struts 2 用的
	public void addEvaluation(EvaluationVO evaluationVO) {
		dao.insert(evaluationVO);
	}

	public EvaluationVO updateEvaluation(String evaluation_id , String class_id , String member_id , String evaluation_class , Integer evaluation_score , java.sql.Timestamp evaluation_time , Integer evaluation_status) {

		EvaluationVO evaluationVO = new EvaluationVO();

		evaluationVO.setEvaluation_id(evaluation_id);
		evaluationVO.setClass_id(class_id);
		evaluationVO.setMember_id(member_id);
		evaluationVO.setEvaluation_class(evaluation_class);
		evaluationVO.setEvaluation_score(evaluation_score);
		evaluationVO.setEvaluation_time(evaluation_time);
		evaluationVO.setEvaluation_status(evaluation_status);
		dao.update(evaluationVO);

		return evaluationVO;
	}

	//預留給 Struts 2 用的
	public void updateEvaluation(EvaluationVO evaluationVO) {
		dao.update(evaluationVO);
	}

//	public void deleteEvaluation(String evaluation_id) {
//		dao.delete(interest_id);
//	}
	
	public EvaluationVO findByPrimaryKey(String evaluation_id) {
		return dao.findByPrimaryKey(evaluation_id);
	}


	public List<EvaluationVO> getAll() {
		return dao.getAll();
	}
	}
