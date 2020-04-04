package com.productTest.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.vainTest.product.model.ProductService;
import com.vainTest.product.model.ProductVO;

/**
 * Servlet implementation class ProductServlet
 */

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("addProduct".equals(action)) { // 來自addProducr.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pd_name = req.getParameter("pd_name");
				String pd_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pd_name == null || pd_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!pd_name.trim().matches(pd_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer pd_price = null;
				try {
					pd_price = new Integer(req.getParameter("pd_price").trim());
				} catch (NumberFormatException e) {
					pd_price = 0;
					errorMsgs.add("金額請填數字.");
				}

				String pd_detail = req.getParameter("pd_detail");
				if (pd_detail == null || pd_detail.trim().length() == 0) {
					errorMsgs.add("商品詳述: 請勿空白");
				}

				String pd_typeNo = req.getParameter("pd_typeNo").trim();
				
				ProductVO productVO = new ProductVO();
				productVO.setPd_name(pd_name);
				productVO.setPd_price(pd_price);
				productVO.setPd_detail(pd_detail);
				productVO.setPd_typeNo(pd_typeNo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductService productService = new ProductService();
				productVO = productService.addProduct(pd_name, pd_price, pd_detail, pd_typeNo);

				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back_end/product/addProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/product/addProduct.jsp");
				failureView.forward(req, res);
			}

		}

		if ("updateProduct".equals(action)) { // 來自addProducr.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String pd_no = req.getParameter("pd_no");
			
			try {
				String pd_name = req.getParameter("pd_name");
				String pd_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pd_name == null || pd_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!pd_name.trim().matches(pd_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer pd_price = null;
				try {
					pd_price = new Integer(req.getParameter("pd_price").trim());
				} catch (NumberFormatException e) {
					pd_price = 0;
					errorMsgs.add("金額請填數字.");
				}

				String pd_detail = req.getParameter("pd_detail");
				if (pd_detail == null || pd_detail.trim().length() == 0) {
					errorMsgs.add("商品詳述: 請勿空白");
				}

				String pd_typeNo = req.getParameter("pd_typeNo").trim();
				
				ProductVO productVO = new ProductVO();
				productVO.setPd_no(pd_no);
				productVO.setPd_name(pd_name);
				productVO.setPd_price(pd_price);
				productVO.setPd_detail(pd_detail);
				productVO.setPd_typeNo(pd_typeNo);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/UpdateProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料***************************************/
				ProductService productService = new ProductService();
				productVO = productService.updateProduct(pd_no, pd_name, pd_price, pd_detail, pd_typeNo);
                 System.out.println("A");
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/ListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				System.out.println("B");
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/UpdateProduct.jsp");
				failureView.forward(req, res);
			}

		}
		
		
	}

}
