package com.verify_list.model;

import java.sql.Connection;
import java.util.List;

public interface Verify_list_interface {
	public void insert(Verify_listVO verify_listVO);
    public void update(Verify_listVO verify_listVO);
    public void delete(String verify_listVO);
    public Verify_listVO findByPrimaryKey(String verify_listVO);
    public List<Verify_listVO> getAll();
    public void updateVerify(Verify_listVO verify_listVO);
    public Verify_listVO getClassCheck(String class_id);
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map);

}
