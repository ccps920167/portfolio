package com.test.model;

import java.util.List;

public class TestService {
	private TestDAO_interface dao;
	
	public TestService() {
		dao=new TestJDBCDAO();
	}
	public TestVO update(String test_id,String unit_id,String test_answer,String test_content, String opta,String optb,String optc,String optd) {
		TestVO testVO=new TestVO();
		testVO.setTest_id(test_id);
		testVO.setUnit_id(unit_id);
		testVO.setTest_answer(test_answer);
		testVO.setTest_content(test_content);
		testVO.setOpta(opta);
		testVO.setOptb(optb);
		testVO.setOptc(optc);
		testVO.setOptd(optd);
		
		dao.update(testVO);
		return testVO;
	}
	
	
	public TestVO add(String unit_id,String test_answer,String test_content, String opta,String optb,String optc,String optd) {
		TestVO testVO=new TestVO();
		testVO.setUnit_id(unit_id);
		testVO.setTest_answer(test_answer);
		testVO.setTest_content(test_content);
		testVO.setOpta(opta);
		testVO.setOptb(optb);
		testVO.setOptc(optc);
		testVO.setOptd(optd);
		dao.insert(testVO);
		
		return testVO;
	}

	public List<TestVO> getAll() {
		return dao.getAll();
	}

	public TestVO getOneTest(String  test_id) {
		return dao.findByPrimaryKey(test_id);
	}
	public void delete(String test_id) {
		dao.delete(test_id);
	}

}
