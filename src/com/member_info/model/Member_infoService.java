package com.member_info.model;

import java.util.List;

public class Member_infoService {

	private Member_infoDAO_interface dao;

	public Member_infoService() {

		dao = new Member_infoJDBCDAO();
	}
	public Member_infoVO update(String member_id,String member_name,String member_email,String member_password,Integer member_role,Integer member_gender,java.sql.Date member_birthday,String member_occupation,String member_address,String member_invoice,byte[] member_pic,Integer teachclass_on,Integer learnclass_on,Integer member_homework,String member_about,String member_good_for,java.sql.Timestamp register_time, java.sql.Timestamp member_update,String traccount,String bank_code ) {
		Member_infoVO member_infoVO =new Member_infoVO();
		member_infoVO.setMember_id(member_id);
		member_infoVO.setMember_name(member_name);
		member_infoVO.setMember_email(member_email);
		member_infoVO.setMember_password(member_password);
		member_infoVO.setMember_role(member_role);
		member_infoVO.setMember_gender(member_gender);
		member_infoVO.setMember_birthday(member_birthday);
		member_infoVO.setMember_occupation(member_occupation);
		member_infoVO.setMember_address(member_address);
		member_infoVO.setMember_invoice(member_invoice);
		member_infoVO.setMember_pic(member_pic);
		member_infoVO.setTeachclass_on(teachclass_on);
		member_infoVO.setLearnclass_on(learnclass_on);
		member_infoVO.setMember_homework(member_homework);
		member_infoVO.setMember_about(member_about);
		member_infoVO.setMember_good_for(member_good_for);
		member_infoVO.setRegister_time(register_time);
		member_infoVO.setMember_update(member_update);
		member_infoVO.setTraccount(traccount);
		member_infoVO.setBank_code(bank_code);

		dao.update(member_infoVO);
		return member_infoVO;
	}
	
	
	public Member_infoVO add(String member_id,String member_name,String member_email,String member_password,Integer member_role,Integer member_gender,java.sql.Date member_birthday,String member_occupation,String member_address,String member_invoice,byte[] member_pic,Integer teachclass_on,Integer learnclass_on,Integer member_homework,String member_about,String member_good_for,java.sql.Timestamp register_time, java.sql.Timestamp member_update,String traccount,String bank_code) {
		Member_infoVO member_infoVO =new Member_infoVO();
		member_infoVO.setMember_id(member_id);
		member_infoVO.setMember_name(member_name);
		member_infoVO.setMember_email(member_email);
		member_infoVO.setMember_password(member_password);
		member_infoVO.setMember_role(member_role);
		member_infoVO.setMember_gender(member_gender);
		member_infoVO.setMember_birthday(member_birthday);
		member_infoVO.setMember_occupation(member_occupation);
		member_infoVO.setMember_address(member_address);
		member_infoVO.setMember_invoice(member_invoice);
		member_infoVO.setMember_pic(member_pic);
		member_infoVO.setTeachclass_on(teachclass_on);
		member_infoVO.setLearnclass_on(learnclass_on);
		member_infoVO.setMember_homework(member_homework);
		member_infoVO.setMember_about(member_about);
		member_infoVO.setMember_good_for(member_good_for);
		member_infoVO.setRegister_time(register_time);
		member_infoVO.setMember_update(member_update);
		member_infoVO.setTraccount(traccount);
		member_infoVO.setBank_code(bank_code);
		
		dao.insert(member_infoVO);
		return member_infoVO;
	}

	public List<Member_infoVO> getAll() {
		return dao.getAll();
	}

	public Member_infoVO getOneMember_info(String  member_id) {
		return dao.findByPrimaryKey(member_id);
	}
	public void delete(String login_id) {
		dao.delete(login_id);
	}
	public Member_infoVO getAccountPassword(String member_email) {
		return dao.findPasswordByMail(member_email);
	}
	
	public Member_infoVO updatestatus(String member_id,Integer teachclass_on,Integer learnclass_on,Integer member_homework) {
		Member_infoVO member_infoVO =new Member_infoVO();
		member_infoVO.setMember_id(member_id);
		member_infoVO.setTeachclass_on(teachclass_on);
		member_infoVO.setLearnclass_on(learnclass_on);
		member_infoVO.setMember_homework(member_homework);
		dao.updatestatus(member_infoVO);
		return member_infoVO;
	}

}
