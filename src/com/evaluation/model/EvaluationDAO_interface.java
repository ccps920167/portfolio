package com.evaluation.model;

import java.util.List;



public interface EvaluationDAO_interface {
	
	public void insert(EvaluationVO evaluationVO);
	public void update(EvaluationVO evaluationVO);
	public EvaluationVO findByPrimaryKey(String evaluation_id);
	public List<EvaluationVO> getAll();

}
