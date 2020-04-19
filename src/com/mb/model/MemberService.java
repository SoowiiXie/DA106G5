package com.mb.model;

import java.sql.Date;
import java.util.List;

public class MemberService {

private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public MemberVO addMember(String mb_id, String mb_pwd, String mb_name, Integer mb_gender, 
			Date mb_birthday, String mb_email, byte[] mb_pic) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMb_id(mb_id);
		memberVO.setMb_pwd(mb_pwd);
		memberVO.setMb_name(mb_name);
		memberVO.setMb_gender(mb_gender);
		memberVO.setMb_birthday(mb_birthday);
		memberVO.setMb_email(mb_email);
		memberVO.setMb_pic(mb_pic);
		dao.insert(memberVO);
		return memberVO;
	}
	
	public MemberVO updateMember(String mb_id, String mb_pwd, String mb_name, Integer mb_gender, 
			Date mb_birthday, String mb_email, byte[] mb_pic, Integer mb_lv,
			Integer mb_rpt_times, Integer mb_status) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMb_id(mb_id);
		memberVO.setMb_pwd(mb_pwd);
		memberVO.setMb_name(mb_name);
		memberVO.setMb_gender(mb_gender);
		memberVO.setMb_birthday(mb_birthday);
		memberVO.setMb_email(mb_email);
		memberVO.setMb_pic(mb_pic);
		memberVO.setMb_lv(mb_lv);
		memberVO.setMb_rpt_times(mb_rpt_times);
		memberVO.setMb_status(mb_status);
		dao.update(memberVO);
		return memberVO;
	}
	
	public MemberVO getOneMember(String mb_id) {
		return dao.findByPrimaryKey(mb_id);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	// 被檢舉次數+1
	public String addOneToRptTime(String mb_id) {
		return dao.addOneToRptTime(mb_id);
	}
}
