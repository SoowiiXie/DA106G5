package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class FrontEndProductServlet
 */

public class FrontEndProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("hi");

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if (action.equals("List_OnMarket_Pd_typeNo")) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String pd_typeNo = req.getParameter("pd_typeNo");

				ProductService productService = new ProductService();
				List<ProductVO> list = productService.OnMarketWithPd_typeNO(2, pd_typeNo);
              
				req.setAttribute("list", list);
				String url = "/front_end/product/Pd_typeNoList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/product/ShopHome.jsp");
				failureView.forward(req, res);
			}
		}

	}

}
