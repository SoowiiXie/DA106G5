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

		if ("addProduct".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pd_name = req.getParameter("pd_name");
				String pd_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (pd_name == null || pd_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!pd_name.trim().matches(pd_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
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

				/*************************** 2.開始新增資料 ***************************************/
				ProductService productService = new ProductService();
				productVO = productService.addProduct(pd_name, pd_price, pd_detail, pd_typeNo);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product/addProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/addProduct.jsp");
				failureView.forward(req, res);
			}

		}

		if (action.equals("getOne_For_update")) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String pd_no = req.getParameter("pd_no");

				/*************************** 2.開始查詢資料 ****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.findOneProduct(pd_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/product/UpdateProduct.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/ListAll.jsp");
				failureView.forward(req, res);
			}
		}

		if ("updateProduct".equals(action)) { // 來自addProducr.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pd_no = req.getParameter("pd_no").trim();

				String pd_name = req.getParameter("pd_name");
				String pd_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (pd_name == null || pd_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!pd_name.trim().matches(pd_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
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
				Integer pd_status = new Integer(req.getParameter("pd_status"));

				ProductVO productVO = new ProductVO();
				productVO.setPd_no(pd_no);
				productVO.setPd_name(pd_name);
				productVO.setPd_price(pd_price);
				productVO.setPd_detail(pd_detail);
				productVO.setPd_typeNo(pd_typeNo);
				productVO.setPd_status(pd_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/UpdateProduct.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 ***************************************/
				ProductService productService = new ProductService();
				productVO = productService.updateProduct(pd_no, pd_name, pd_price, pd_detail, pd_typeNo, pd_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product/ListAll.jsp";
				req.setAttribute("productVO", productVO);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/UpdateProduct.jsp");
				failureView.forward(req, res);
			}

		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String pd_no = req.getParameter("pd_no");

				/*************************** 2.開始刪除資料 ***************************************/
				ProductService productService = new ProductService();
				productService.deleteProduct(pd_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product/ListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/ListAll.jsp");
				failureView.forward(req, res);
			}
		}


	}

}
