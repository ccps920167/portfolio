package com.test.model;

import java.util.List;

public interface TestDAO_interface {
	public void insert(TestVO testVO);

	public void update(TestVO testVO);

	public void delete(String test_id);

	public TestVO findByPrimaryKey(String test_id);

	public List<TestVO> getAll();
}
