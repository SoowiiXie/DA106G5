package com.coupon.model;

import java.util.List;

public class CouponService {

	private CouponDAO_interface dao;

	public CouponService() {

		dao = new CouponDAO();

	}

	public CouponVO searchCoupon(String cp_no) {

		return dao.searchCoupon(cp_no);
	}

	public List<CouponVO> getAll() {

		return dao.getAll();

	}
	
	public int addCoupon(CouponVO couponVo) {
		
		return dao.addCoupon(couponVo);		
	}
	
}
