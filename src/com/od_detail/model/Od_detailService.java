package com.od_detail.model;

import java.util.List;

public class Od_detailService {

	
	private Od_detailDAO_interface dao;
	
	public Od_detailService() {
		
		dao = new Od_detailDAO();
		
	}
	
	public int addOneOdDetail(Od_detailVO od_detailVO) {
		
		dao.addOneOdDetail(od_detailVO);
		
		return 0;
	}
	
	public List<Od_detailVO >search_one_oeder_detail(String od_no){
		
		  
		
		return dao.search_one_oeder_detail(od_no);
	}
	
	
}
