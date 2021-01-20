package com.login_history.model;

import java.util.List;

public interface Login_historyDAO_interface {
	public void insert(Login_historyVO login_historyVO);

	public void update(Login_historyVO login_historyVO);

	public void delete(String login_id);

	public Login_historyVO findByPrimaryKey(String login_id);

	public List<Login_historyVO> getAll();

}
