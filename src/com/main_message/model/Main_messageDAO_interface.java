package com.main_message.model;

import java.util.List;


public interface Main_messageDAO_interface {
    public void insert(Main_messageVO Main_messageVO);
    public void update(Main_messageVO Main_messageVO);
    public void delete(String mainmsg_id);
    public Main_messageVO findByPrimaryKey(String mainmsg_id);
    public List<Main_messageVO> getAll();
    
    //琩高琘场(癸)(肚 Set)
//    public Set<Main_message_idVO> getEmpsByDeptno(Integer deptno);
    //窾ノ狡琩高(肚把计篈Map)(肚 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}

