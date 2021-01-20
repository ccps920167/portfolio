package com.keyword_form.model;

import java.util.List;
import java.util.Map;


public interface Keyword_formDAO_interface {
	
	public void insert(Keyword_formVO keyword_formVO);
	 public void update(Keyword_formVO keyword_formVO);
    public Keyword_formVO findByPrimaryKey(String keyword_id);
    public List<Keyword_formVO> getAll();
	public void delete(String keyword_id);
	public List<Keyword_formVO> getKeywordbydate(String startdate, String enddate);
	public int getKeywordCount(String search_word);
	public List<Integer> getKeywordCountList(String search_word);   

}
