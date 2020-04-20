package com.orders.model;

import java.util.Iterator;
import java.util.List;

import com.od_detail.model.Od_detailVO;

public class OrdersService {

	private OrdersDAO_interface dao;

	public OrdersService() {

		dao = new OrdersDAO();

	}

	public List<OrdersVO> getAllOrders() {

		return dao.getAll();
	}

	public OrdersVO addOrders(String mb_id, Integer od_totalPrice, String od_add) {

		OrdersVO ordersVO = new OrdersVO();
		ordersVO.setMb_id(mb_id);
		ordersVO.setOd_totalPrice(od_totalPrice);
		ordersVO.setOd_add(od_add);
		return dao.addOrders(ordersVO);
	}

	public String addOrdersWithPd_detail(OrdersVO ordersVO, List<Od_detailVO> list) {
		return dao.addOrdersWithPd_detail(ordersVO, list);

	}
	
	 public List<OrdersVO> searchMemberOrders(String mb_id){
		     return dao.searchMemberOrders(mb_id);
	 }

}
