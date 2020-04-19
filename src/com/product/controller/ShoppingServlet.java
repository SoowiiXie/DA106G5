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

import com.cp_get.model.Cp_getService;
import com.cp_get.model.Cp_getVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;





public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");
		String action = req.getParameter("action");
		
		

		if (!action.equals("GoToWriteShopInformation")) {

			if (action.equals("findOneProduct")) { // ShopHome商城首頁進入該上架之商品

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/*************************** 1.接收請求參數 ****************************************/
					String pd_no = req.getParameter("pd_no");
					System.out.println(pd_no);
					/*************************** 2.開始查詢資料 ****************************************/
					ProductService productService = new ProductService();
					ProductVO productVO = productService.findOneProduct(pd_no);
					ArrayList<String> sizeList = new ArrayList<String>();
					if (productVO.getPd_typeNo().equals("PTN00010") || productVO.getPd_typeNo().equals("PTN00011")
							|| productVO.getPd_typeNo().equals("PTN00012")) {
						sizeList.add("無");
						System.out.print("配件尺寸:"+sizeList);

					} else if (productVO.getPd_typeNo().equals("PTN00003") || productVO.getPd_typeNo().equals("PTN00006")
							|| productVO.getPd_typeNo().equals("PTN00009")) {
						sizeList.add("US7.5");
						sizeList.add("US8.0");
						sizeList.add("US8.5");
						sizeList.add("US9.0");
						sizeList.add("US9.5");
						sizeList.add("US10.0");
						System.out.print("鞋類尺寸:"+sizeList);
						
					} else {
						sizeList.add("XS");
						sizeList.add("S");
						sizeList.add("M");
						sizeList.add("L");
						sizeList.add("XL");
						sizeList.add("XXL");
						System.out.print("服飾尺寸:"+sizeList);
				

					}

					/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
					req.setAttribute("productVO", productVO); 
					req.setAttribute("sizeList", sizeList);
					String url = "/front_end/product/GoOneProduct.jsp";

					RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 OneProductInformation.jsp
					successView.forward(req, res);

					return;
					    
				} catch (Exception e) {
					errorMsgs.add("無法取得商品的資料:" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product/ShopHome.jsp");
					failureView.forward(req, res);
					return;
				}

			}

			// 刪除購物車中的
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.removeElementAt(d);
			}
			// 新增商品至購物車中
			else if (action.equals("AddProductToCar")) {
				boolean match = false;
                 
				// 取得後來新增的商品
				ProductVO aProduct = getProduct(req);
				System.out.println("新增的商品，商品編號："+aProduct.getPd_no());
				System.out.println("新增的商品，商品尺寸："+aProduct.getPd_name());
				System.out.println("新增的商品，商品尺寸："+aProduct.getPd_typeNo());

				// 新增第一個商品籍至購物車時
				if (buylist == null) {
					buylist = new Vector<ProductVO>();
					buylist.add(aProduct);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						ProductVO productVO = buylist.get(i);
                          
						// 假若新增的商品和購物車的商品一樣時
						if (productVO.getPd_name().equals(aProduct.getPd_name()) && productVO.getPd_typeSize().equals(aProduct.getPd_typeSize())) {
							
							productVO.setPd_quantity(productVO.getPd_quantity() + aProduct.getPd_quantity());
							buylist.setElementAt(productVO, i);
							match = true;
							
						} // end of if name matches
					} // end of for

					// 假若新增的商品和購物車的商品不一樣時
					if (!match)
						buylist.add(aProduct);
				}
				
				
			}
              String buylistCount = String.valueOf(buylist.size());
              session.setAttribute("buylistCount", buylistCount);
			session.setAttribute("shoppingCart", buylist);
			
			String url = "/front_end/product/ProductCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;
		}

		// 結帳，計算購物車商品價錢總數
		else if (action.equals("GoToWriteShopInformation")) {
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO order = buylist.get(i);
				int price = order.getPd_price();
				int quantity = order.getPd_quantity();
				total += (price * quantity);
			}
			String mb_id = (String)session.getAttribute("mb_id");
			Cp_getService cp_getService = new Cp_getService();
			Cp_getVO cp_getVO = new Cp_getVO();
			cp_getVO.setMb_id(mb_id);
			cp_getVO.setCp_status(1);
			List<Cp_getVO> couponList = cp_getService.listAmemberCpGetStatus(cp_getVO);
			
			String totalPrice = String.valueOf(total);
			req.setAttribute("totalPrice", totalPrice);
			req.setAttribute("couponList", couponList);
			String url = "/front_end/product/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		    return;
		}

	
		
	}

	private ProductVO getProduct(HttpServletRequest req) {
		String pd_no = req.getParameter("pd_no");
		String pd_name = req.getParameter("pd_name");
		String pd_quantity = req.getParameter("pd_quantity");
		String pd_price = req.getParameter("pd_price");
		String pd_typeNo = req.getParameter("pd_typeNo");
		String pd_typeSize = req.getParameter("pd_typeSize");

		ProductVO bdc = new ProductVO();

		bdc.setPd_no(pd_no);
		bdc.setPd_name(pd_name);
		bdc.setPd_price(new Integer(pd_price));
		bdc.setPd_quantity(new Integer(pd_quantity));
		bdc.setPd_typeNo(pd_typeNo);
		bdc.setPd_typeSize(pd_typeSize);

		return bdc;
	}
}