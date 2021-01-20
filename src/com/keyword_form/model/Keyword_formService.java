package com.keyword_form.model;

import java.util.List;
import java.util.Map;




public class Keyword_formService {
	private Keyword_formDAO_interface dao ;

	public Keyword_formService() {
		dao = new Keyword_formDAOJDBC();
	}

	public void  addKeyword_form(Keyword_formVO keyword_formVO) {
		
		dao.insert(keyword_formVO);
	}

	//預留給 Struts 2 用的
//	public void addKeyword_form(Keyword_formVO keyword_formVO) {
//		dao.insert(keyword_formVO);
//	}

	public Keyword_formVO updatekeyword_form(String keyword_id ,java.sql.Date search_date , String search_word) {

		Keyword_formVO Keyword_formVO = new Keyword_formVO();


		Keyword_formVO.setKeyword_id(keyword_id);
		Keyword_formVO.setSearch_date(search_date);
		Keyword_formVO.setSearch_word(search_word);
		dao.update(Keyword_formVO);

		return Keyword_formVO;
	}

	//預留給 Struts 2 用的
	public void updateKeyword_form(Keyword_formVO Keyword_formVO) {
		dao.update(Keyword_formVO);
	}

	public void deleteKeyword_form(String keyword_id) {
		dao.delete(keyword_id);
	}

	public List<Keyword_formVO> getKeywordbydate(String startdate , String enddate) {
		return dao.getKeywordbydate(startdate , enddate );
	}

	public List<Keyword_formVO> getAll() {
		return dao.getAll();
	}
	public Keyword_formVO getOnekeyword_form(String  keyword_id) {
		return dao.findByPrimaryKey(keyword_id);
	}
	
	public List<Integer> getKeywordCountList(String search_word) {
		return dao.getKeywordCountList(search_word);
	}
	public  int getKeywordCount(String search_word) {
		return dao.getKeywordCount(search_word);
	}
	
	
	}

