package com.orders.model;
import java.util.*;

import com.od_detail.model.Od_detailVO;

public interface OrdersDAO_interface {
	
	public OrdersVO addOrders (OrdersVO ordersVO); //新增訂單
	public int updateOrdersInformation (OrdersVO ordersVO);//修改訂單
	public int deleteOrders(String od_no);//刪除訂單
	public OrdersVO searchOneOrders(String od_no); //用訂單編號查詢單一訂單資料
	public List<OrdersVO> getAll(); //列出所有訂單
    public String addOrdersWithPd_detail(OrdersVO ordersVO, List<Od_detailVO> list);   //同時新增訂單與訂單明細(交易必用)
    public List<OrdersVO> searchMemberOrders(String mb_id); //列出該會員所有訂單
    
}
	