package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;

public class OrdersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if (action.equals("getOne_For_Od_detail")) {

			String od_no = req.getParameter("od_no");

			req.setAttribute("od_no", od_no);

			String url = "/back_end/product/One_Od_detail_Information.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			return;

		}
		
		if(action.equals("getAllList")) {
			OrdersService ordersService = new OrdersService();
			List<OrdersVO> list = ordersService.getAllOrders();
            req.getSession().setAttribute("list", list);			

            String url = "/back_end/product/ListAllOrders.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			return;
		}

		if (action.equals("searchMemberOrders")) {

			String mb_id = req.getParameter("mb_id");

			OrdersService ordersService = new OrdersService();
			List<OrdersVO> list = ordersService.searchMemberOrders(mb_id);
			
			req.getSession().setAttribute("list", list);
			String url = "/back_end/product/ListAllOrders.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			return;
		}

	}

}
