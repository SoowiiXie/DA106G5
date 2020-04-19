package com.orders.model;
import java.util.List;

import com.od_detail.model.Od_detailVO;

public class OrdersService {

	private OrdersDAO_interface dao, daoPG;

	public OrdersService() {

		dao = new OrdersDAO();
		daoPG = new OrdersPGDAO();

	}

	public List<OrdersVO> getAllOrders() {

		return dao.getAll();
	}
	
	public List<OrdersVO> getAllOrdersPG() {
		
		return daoPG.getAll();
	}

	public OrdersVO addOrders(String mb_id, Integer od_totalPrice, String od_add) {

		OrdersVO ordersVO = new OrdersVO();
		ordersVO.setMb_id(mb_id);
		ordersVO.setOd_totalPrice(od_totalPrice);
		ordersVO.setOd_add(od_add);
		return dao.addOrders(ordersVO);
	}
	
	public OrdersVO addOrdersPG(String od_no,String mb_id, Integer od_status) {
		
		OrdersVO ordersVO = new OrdersVO();
		ordersVO.setOd_no(od_no);
		ordersVO.setMb_id(mb_id);
		ordersVO.setOd_status(od_status);
		return daoPG.addOrders(ordersVO);
	}

	public String addOrdersWithPd_detail(OrdersVO ordersVO, List<Od_detailVO> list) {
		System.out.println("經過service");
		return dao.addOrdersWithPd_detail(ordersVO, list);

	}
	
	 public List<OrdersVO> searchMemberOrders(String mb_id){
		     return dao.searchMemberOrders(mb_id);
	 }
	 
	 public List<OrdersVO> searchMemberOrdersPG(String mb_id){
		 return daoPG.searchMemberOrders(mb_id);
	 }

}
