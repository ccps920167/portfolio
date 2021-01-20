package com.member_info.model;

import java.util.*;

public interface Member_infoDAO_interface {
	public void insert(Member_infoVO member_infoVO);
	public void update(Member_infoVO member_infoVO);
	public void delete(String member_id);
	public Member_infoVO findByPrimaryKey(String member_id);
    public List<Member_infoVO> getAll();
    public Member_infoVO findPasswordByMail(String member_email);
    public void updatestatus(Member_infoVO member_infoVO);
    
}
