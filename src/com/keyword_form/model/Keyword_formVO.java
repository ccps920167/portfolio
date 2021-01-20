package com.keyword_form.model;

import java.sql.Date;

public class Keyword_formVO implements java.io.Serializable {
	private String keyword_id;
	private Date search_date;
	private String search_word;

	public String getKeyword_id() {
		return keyword_id;
	}

	public void setKeyword_id(String keyword_id) {
		this.keyword_id = keyword_id;
	}

	public Date getSearch_date() {
		return search_date;
	}

	public void setSearch_date(Date search_date) {
		this.search_date = search_date;
	}

	public String getSearch_word() {
		return search_word;
	}

	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}

	public Keyword_formVO() {
	}

}
