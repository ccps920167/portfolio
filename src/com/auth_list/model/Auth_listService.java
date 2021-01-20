package com.auth_list.model;

import java.util.List;

public class Auth_listService {

	public Auth_list_interface dao;
	
	public Auth_listService() {
		dao = new Auth_listDAO();
	}
	public Auth_listVO addAuth_list(String auth_id,String auth_func) {
		
		Auth_listVO auth_listVO = new Auth_listVO();
		
		auth_listVO.setAuth_id(auth_id);
        auth_listVO.setAuth_func(auth_func);
        dao.insert(auth_listVO);
        
		return auth_listVO;
	}
	
    public Auth_listVO updateAuth_list(String auth_id,String auth_func) {
    
    	Auth_listVO alVO =new Auth_listVO();
    	
    	alVO.setAuth_id(auth_id);
    	alVO.setAuth_func(auth_func);
    	dao.update(alVO);
    	
    	return alVO;
    }
    
    public void deleteAuth_list(String auth_id) {
    	dao.delete(auth_id);
    }
    
    public Auth_listVO getOneAuth_list(String auth_id) {
    	return dao.findByPrimaryKey(auth_id);
    }
    public List<Auth_listVO> getAll(){
    	return dao.getAll();
    }
	
	
	
	
}
