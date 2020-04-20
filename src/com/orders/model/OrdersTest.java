package com.orders.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrdersTest extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		OrdersService OrderSvc = new OrdersService();
		System.out.println(OrderSvc.searchMemberOrdersPG("soowii123"));
		System.out.println(OrderSvc.getAllOrdersPG());
	}
}
