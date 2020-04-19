package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cp_get.model.Cp_getVO;
import com.od_detail.model.Od_detailVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.pd_type.model.Pd_typeVO;
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
			String cp_get = (String) session.getAttribute("cp_get");
			String mb_id = (String) session.getAttribute("mb_id");
			Integer discount = (Integer) session.getAttribute("discount");
			
			String payMethod = req.getParameter("payMethod");

			Integer totalPrice = (Integer) session.getAttribute("totalPrice");
			Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");
			List<String> errorMsgs = new LinkedList<String>();
			Pd_typeVO pd_typeVO = new Pd_typeVO();
			String od_add = req.getParameter("od_add");
			if (od_add == null || od_add.trim().length() == 0) {
				errorMsgs.add("地址: 地址請勿空白");
			}

			Integer od_totalPrice = totalPrice;
			OrdersVO ordersVO = new OrdersVO();
			ordersVO.setMb_id(mb_id);
			ordersVO.setOd_totalPrice(od_totalPrice);
			ordersVO.setOd_add(od_add);
			ordersVO.setOd_discount(discount);
			ordersVO.setCp_no(cp_get);
			Cp_getVO cp_getVO = new Cp_getVO();
			cp_getVO.setMb_id(mb_id);
			cp_getVO.setCp_status(1);

			List<Od_detailVO> testList = new ArrayList<Od_detailVO>(); // 將購物車每一欄資料匯入訂單明細每一筆資料，再倒入訂單明細陣列(多筆訂單明細)
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				String pd_no = order.getPd_no();
				int pd_price = order.getPd_price();
				int pd_quantity = order.getPd_quantity();
				String pd_typeSize = order.getPd_typeSize();
				Od_detailVO od_detailVO = new Od_detailVO();
				od_detailVO.setPd_no(pd_no);
				od_detailVO.setOd_price(pd_price);
				od_detailVO.setOd_amount(pd_quantity);
				od_detailVO.setPd_size(pd_typeSize);
				testList.add(od_detailVO);

			}
			if (!errorMsgs.isEmpty()) {

				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product/Checkout.jsp");
				failureView.forward(req, res);

				return;
			}
			OrdersService ordersService = new OrdersService();
			String od_no = ordersService.addOrdersWithPd_detail(ordersVO, testList);
			ordersVO.setOd_no(od_no);
			req.setAttribute("ordersVO", ordersVO);
			req.setAttribute("payMethod", payMethod);
			session.setAttribute("buylistCount", 0);

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
