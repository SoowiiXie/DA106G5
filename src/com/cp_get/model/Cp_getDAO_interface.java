package com.cp_get.model;

import java.util.List;

public interface Cp_getDAO_interface {

	public int aMemberGetCoupon(Cp_getVO cp_getVO);//新增持有優惠卷，某會員得到優惠卷。
	public int aMemberUseCoupon(Cp_getVO cp_getVO);  //修改某會員持有的某張優惠卷使用狀態，例如使用優惠卷  
	public List<Cp_getVO> searchMemberGetCoupon(String mb_id); //列出某會員持有的全部優惠卷 
	public List<Cp_getVO> listAmemberCpGetStatus(Cp_getVO cp_getVO); // 查詢開會員某狀態的優惠券，例如列出"可使用狀態"的優惠卷
	
	//LIST_A_MEMBER_CP_GET_BY_STATUS
  //public int delete(String mb_no); 刪除所有優惠卷，目前用不到
	
}
