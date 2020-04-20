package com.product.controller;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class ProductServlet
 */
@MultipartConfig
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
				System.out.println(pd_typeNo);
				ProductVO productVO = new ProductVO();

				/*--------------------------------------------------------------*/
				byte[] pd_pic = null;
				InputStream in;
				BufferedInputStream bf;

				try {

					Part part = req.getPart("pd_pic");
					String picBase64 = req.getParameter("picBase64");
					in = part.getInputStream();
					bf = new BufferedInputStream(in);
					if (part.getSize() != 0) {
						pd_pic = new byte[bf.available()]; // 暫存記憶體

					} else if (picBase64 != null && picBase64.trim().length() != 0) { // 第一次有上傳圖片，第二次無，使用參數傳進來的picBase64
						pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
					} else if (pd_pic == null || pd_pic.length == 0) {
						errorMsgs.add("產品圖片請勿空白");

					}
					bf.read(pd_pic);
					bf.close();
					in.close();

					if (picBase64 != null && picBase64.trim().length() != 0) {
						pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
					} // 若都沒有，mb_pic 以 null 送出
					productVO.setPd_name(pd_name);
					productVO.setPd_price(pd_price);
					productVO.setPd_detail(pd_detail);
					productVO.setPd_typeNo(pd_typeNo);
					productVO.setPd_pic(pd_pic);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.out.println("a");

				} catch (IOException e) {
					e.printStackTrace();

				}
				/*--------------------------------------------------------------*/

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/addProduct.jsp");
					failureView.forward(req, res);
					System.out.println("3");
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProductService productService = new ProductService();
				productVO = productService.addProduct(pd_name, pd_price, pd_detail, pd_typeNo, pd_pic);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product/addProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/addProduct.jsp");
				failureView.forward(req, res);
				return;
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
				String whichPage = req.getParameter("whichPage");
				System.out.println("開頁頁數是" + whichPage);

				/*************************** 2.開始查詢資料 ****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.findOneProduct(pd_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/back_end/product/UpdateProduct.jsp";
				req.setAttribute("whichPage", whichPage);

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
			String whichPage = (String) req.getAttribute("whichPage");
			System.out.println("修改的該頁頁數是" + whichPage);
//			try {
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

			/*--------------------------------------------------------------*/
			byte[] pd_pic = null;
			InputStream in;
			BufferedInputStream bf;

			try {

				Part part = req.getPart("pd_pic");
				if (part.getSize() != 0) { // 有上傳圖片
					in = part.getInputStream();
					bf = new BufferedInputStream(in);
					pd_pic = new byte[bf.available()]; // 暫存記憶體
					bf.read(pd_pic);
					bf.close();
					in.close();

				} else { // 沒有上傳圖片，用原來的圖片

					ProductService productService = new ProductService();
					pd_pic = productService.search_pd_pic(pd_no);
				}

				if (pd_pic == null || pd_pic.length == 0) {
					errorMsgs.add("產品圖片請勿空白");

				}
				productVO.setPd_no(pd_no);
				productVO.setPd_name(pd_name);
				productVO.setPd_price(pd_price);
				productVO.setPd_detail(pd_detail);
				productVO.setPd_typeNo(pd_typeNo);
				productVO.setPd_status(pd_status);
				productVO.setPd_pic(pd_pic);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("1");

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("2");
			}
			/*--------------------------------------------------------------*/

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/UpdateProduct.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 ***************************************/

			try {
				ProductService productService = new ProductService();
				productService.updateProduct(pd_no, pd_name, pd_price, pd_detail, pd_typeNo, pd_status, pd_pic);

				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
				req.getSession().setAttribute("pd_typeNo", pd_typeNo);
				String url = "/back_end/product/AllList2.jsp";
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
			String whichPage = req.getParameter("whichPage");
			System.out.println("刪除的該頁頁數字" + whichPage);
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String pd_no = req.getParameter("pd_no");

				/*************************** 2.開始刪除資料 ***************************************/
				ProductService productService = new ProductService();
				productService.deleteProduct(pd_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/product/AllList2.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/AllList2.jsp");
				failureView.forward(req, res);
			}
		}

		if (action.equals("searchTypeList")) {
			String pd_typeNo = req.getParameter("pd_typeNo");

			req.getSession().setAttribute("pd_typeNo", pd_typeNo);

			String url = "/back_end/product/AllList2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

	}

}
