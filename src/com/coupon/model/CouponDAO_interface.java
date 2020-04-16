package com.coupon.model;
import java.util.*;

public interface CouponDAO_interface {
	public int addCoupon(CouponVO couponVo); //新增優惠卷種類
    public int updateCouponInformation(CouponVO couponVo); //修改優惠卷資料
    public int deleteCoupon(String cp_no); //刪除優惠卷資料
    public CouponVO searchCoupon(String cp_no); //輸入優惠卷編號搜尋優惠卷資料
    public List<CouponVO> getAll(); //列出所有優惠卷資料
}
	