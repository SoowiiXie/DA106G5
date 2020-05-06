package com.cp_get.model;

import java.util.List;

public class Cp_getService {

	private Cp_getDAO_interface dao;

	public Cp_getService() {

		dao = new Cp_getDAO();

	}

	public List<Cp_getVO> searchMemberGetCoupon(String mb_id) {

		return dao.searchMemberGetCoupon(mb_id);

	}

	
	public List<Cp_getVO> listAmemberCpGetStatus(Cp_getVO cp_getVO){
		
		return dao.listAmemberCpGetStatus(cp_getVO);
	}
	
	public int aMemberUseCoupon(Cp_getVO cp_getVO) {
		
		return dao.aMemberUseCoupon(cp_getVO);
	}
	
	public int aMemberGetCoupon(Cp_getVO cp_getVO) {
		
		return dao.aMemberGetCoupon(cp_getVO);
	}

}
