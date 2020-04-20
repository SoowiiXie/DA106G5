package com.pd_follow.model;

import java.util.List;

public class Pd_followService {

	private Pd_followDAO_interface dao;
	
	public Pd_followService(){
		
		dao = new Pd_followDAO(); 
		
	}
	
	public int insertMemberOneProduct (Pd_followVO pd_followVO) {
		
		return dao.insertMemberOneProduct(pd_followVO);
	}
	
	public int deleteMemberOneProduct(Pd_followVO pd_followVO) {
		
          return dao.deleteMemberOneProduct(pd_followVO);
	}
	
	public List<Pd_followVO> searchMemberAllPdFollow(String mb_id){
		
		return dao.searchMemberAllPdFollow(mb_id);
	}
	

	
}
