package com.coupon.model;

public class CouponService {

	
	private CouponDAO_interface dao;
	
	
	
	public CouponService(){
		
		dao = new CouponDAO();
		
	}
	
	
	
	public CouponVO searchCoupon(String cp_no) {
		
		
		return dao.searchCoupon(cp_no);
	}
	
}
