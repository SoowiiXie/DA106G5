package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.od_detail.model.Od_detailVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.product.model.ProductVO;

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		HttpSession session = req.getSession();

//		if (action.equals("goToPayAndAdd")) {
//			String mb_id = (String) session.getAttribute("mb_id");
//			Integer amount = new Integer(req.getParameter("amount"));
//			String od_add = req.getParameter("od_add");
//			Integer od_totalPrice = amount;
//			OrdersVO ordersVO = new OrdersVO();
//			ordersVO.setMb_id(mb_id);
//			ordersVO.setOd_totalPrice(od_totalPrice);
//			ordersVO.setOd_add(od_add);
//
//			OrdersService ordersService = new OrdersService();
//
//			ordersVO = ordersService.addOrders(mb_id, od_totalPrice, od_add);
//
//			Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");
//
//			
//
//			String url = "/front_end/product/WriteAddAndPayInformation.jsp";
//
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//
//			successView.forward(req, res);
//		}

		if (action.equals("getOd_detail_Information")) {

			String mb_id = (String) session.getAttribute("mb_id");
			Integer amount = new Integer(req.getParameter("amount"));
			Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");
			String od_add = req.getParameter("od_add");
			Integer od_totalPrice = amount;
			OrdersVO ordersVO = new OrdersVO();
			ordersVO.setMb_id(mb_id);
			ordersVO.setOd_totalPrice(od_totalPrice);
			ordersVO.setOd_add(od_add);
			List<Od_detailVO> testList = new ArrayList<Od_detailVO>();
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				String pd_no = order.getPd_no();
//				String pd_name = order.getPd_name();
				int pd_price = order.getPd_price();
//				String pd_typeNo = order.getPd_typeNo();
				int pd_quantity = order.getPd_quantity();
				Od_detailVO od_detailVO = new Od_detailVO();
				od_detailVO.setPd_no(pd_no);
				od_detailVO.setOd_price(pd_price);
				od_detailVO.setOd_amount(pd_quantity);
				testList.add(od_detailVO);

			}
			OrdersService ordersService = new OrdersService();
			String od_no = ordersService.addOrdersWithPd_detail(ordersVO, testList);
			ordersVO.setOd_no(od_no);
			req.setAttribute("ordersVO",ordersVO);
			
			
			
			String url = "/front_end/product/MemberLookOd_detail.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);

			successView.forward(req, res);
			
			if (buylist != null) {

				buylist = null;

				session.setAttribute("shoppingCart", buylist);

			}
			
			
			return;
			
			
		}

	}

}
