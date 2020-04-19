package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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



		if (action.equals("getOd_detail_Information")) {      
			System.out.println("這是結帳");
			String mb_id = (String) session.getAttribute("mb_id");
			  String payMethod = req.getParameter("payMethod");
			 
			Integer amount = new Integer(req.getParameter("amount"));
			Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");
			String od_add = req.getParameter("od_add");
			Integer od_totalPrice = amount;
			OrdersVO ordersVO = new OrdersVO();
			ordersVO.setMb_id(mb_id);
			ordersVO.setOd_totalPrice(od_totalPrice);
			ordersVO.setOd_add(od_add);
			List<Od_detailVO> testList = new ArrayList<Od_detailVO>();  //將購物車每一欄資料匯入訂單明細每一筆資料，再倒入訂單明細陣列(多筆訂單明細)
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				String pd_no = order.getPd_no();
//				String pd_name = order.getPd_name();
				int pd_price = order.getPd_price();
//				String pd_typeNo = order.getPd_typeNo();
				int pd_quantity = order.getPd_quantity();
				String pd_typeSize = order.getPd_typeSize();
				Od_detailVO od_detailVO = new Od_detailVO();
				od_detailVO.setPd_no(pd_no);
				od_detailVO.setOd_price(pd_price);
				od_detailVO.setOd_amount(pd_quantity);
				od_detailVO.setPd_size(pd_typeSize);
				testList.add(od_detailVO);

			}
			OrdersService ordersService = new OrdersService();
			String od_no = ordersService.addOrdersWithPd_detail(ordersVO, testList);
			ordersVO.setOd_no(od_no);
			req.setAttribute("ordersVO",ordersVO);
			req.setAttribute("payMethod", payMethod);
			session.setAttribute("buylistCount", 0);
			//送給PG要有od_no,mb_id,od_status
			ordersService.addOrdersPG(od_no, mb_id, 1);
			
			String url = "/front_end/product/MemberLookOd_detail.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);

			successView.forward(req, res);
			
			if (buylist != null) {

				buylist = null;

				session.setAttribute("shoppingCart", buylist);

			}
			
			
			return;
			
			
		}
		
		if(action.equals("useCoupon")) {
			System.out.println("這是使用優惠卷");
			String mb_id = (String) session.getAttribute("mb_id");
			String od_add = req.getParameter("od_add");
			Integer totalPrice = new Integer(req.getParameter("totalPrice"));
			String cp_get = req.getParameter("cp_get");
			Integer od_totalPrice = totalPrice;
			OrdersVO ordersVO = new OrdersVO();
			ordersVO.setMb_id(mb_id);
			ordersVO.setOd_totalPrice(od_totalPrice);
			ordersVO.setOd_add(od_add);
			
			System.out.println("取得"+ordersVO.getMb_id());
			System.out.println("取得"+ordersVO.getOd_totalPrice());
			System.out.println("取得"+ordersVO.getOd_add());
			System.out.println("優惠卷編號"+cp_get);
			
			String url = "/front_end/product/Checkout.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url);

			successView.forward(req, res);
			
			
			
			
		}

	}

}