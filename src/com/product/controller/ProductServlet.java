package com.product.controller;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
		
		if("getIncludePath".equals(action)) {
			String includePath = "/back_end/product/addProduct.jsp"; //讓include增加商品頁面的bar得到網址
//			System.out.println(includePath);
			req.setAttribute("includePath", includePath);
			
			RequestDispatcher goToStaff = req.getRequestDispatcher("/back_end/staff/index.jsp"); //include商品的bar
			goToStaff.forward(req, res);
			return;
		}
		
//		if ("addProduct3".equals(action)) {
//		List<String> errorMsgs = new LinkedList<String>();
//		// Store this set in the request scope, in case we need to
//		// send the ErrorPage view.
//		req.setAttribute("errorMsgs", errorMsgs);
//
//		try {
//		
//			String pd_name = req.getParameter("pd_name");
//			String pd_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
//			if (pd_name == null || pd_name.trim().length() == 0) {
//				errorMsgs.add("商品名稱: 請勿空白");
//			} else if (!pd_name.trim().matches(pd_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
//			}
//
//			Integer pd_price = null;
//			try {
//				pd_price = new Integer(req.getParameter("pd_price").trim());
//			} catch (NumberFormatException e) {
//				pd_price = 0;
//				errorMsgs.add("金額請填數字.");
//			}
//
//			String pd_detail = req.getParameter("pd_detail");
//			if (pd_detail == null || pd_detail.trim().length() == 0) {
//				errorMsgs.add("商品詳述: 請勿空白");
//			}
//
//			String pd_typeNo = req.getParameter("pd_typeNo").trim();
//
//			ProductVO productVO = new ProductVO();
//
//			byte[] pd_pic = null;
//			InputStream in;
//			BufferedInputStream bf;
//
//			try {
//
//				Part part = req.getPart("pd_pic");
//				Part part2 = req.getPart("pd_pic2");
//                List<Part> parts = new ArrayList<Part>();
//                parts.add(part);
//                parts.add(part2);
//                System.out.println(parts);
//                
//                List<byte[]> pd_pics = new ArrayList();
//                
//			   for(Part aPart : parts) {
//					in = aPart.getInputStream();
//				bf = new BufferedInputStream(in);
//				int piclen = 0;
//				if (aPart.getSize() != 0) {
//					pd_pic = new byte[bf.available()]; // 暫存記憶體
//					piclen++;
//					
//				}
//				bf.read(pd_pic);
//				bf.close();
//				in.close();
//				
//				pd_pics.add(pd_pic);
//				if(piclen == 0) {
//					errorMsgs.add("請上傳產品圖片");
//				}
//
//		
//			}
//			     System.out.println(pd_pics);
//				
//				String picBase64 = req.getParameter("picBase64");
//				in = part.getInputStream();
//				bf = new BufferedInputStream(in);
//				if (part.getSize() != 0) {
//					pd_pic = new byte[bf.available()]; // 暫存記憶體
//
//				} else if (picBase64 != null && picBase64.trim().length() != 0) { // 第一次有上傳圖片，第二次無，使用參數傳進來的picBase64
//					pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
//				} else if (pd_pic == null || pd_pic.length == 0) {
//					errorMsgs.add("產品圖片請勿空白");
//
//				}
//				bf.read(pd_pic);
//				bf.close();
//				in.close();
//				
//				
//
//				if (picBase64 != null && picBase64.trim().length() != 0) {
//					pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
//				} // 若都沒有，mb_pic 以 null 送出
//				productVO.setPd_name(pd_name);
//				productVO.setPd_price(pd_price);
//				productVO.setPd_detail(pd_detail);
//				productVO.setPd_typeNo(pd_typeNo);
//				productVO.setPd_pic(pd_pics.get(0));
//				productVO.setPd_pic2(pd_pics.get(1));
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//				System.out.println("請上傳圖片");
//				
//				
//
//			} catch (IOException e) {
//				e.printStackTrace();
//
//			}
//			/*--------------------------------------------------------------*/
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//                
//				req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
//				String includePath = "/back_end/product/addProduct.jsp";
////				System.out.println(includePath);
//				req.setAttribute("includePath", includePath);
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/index.jsp");
//				failureView.forward(req, res);
//				System.out.println("3");
//				return;
//			}
//
//			/*************************** 2.開始新增資料 ***************************************/
//			ProductService productService = new ProductService();
//			
//			productVO = productService.addProduct3(productVO);
//	     
//			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String includePath = "/back_end/product/AllList2.jsp";
////			System.out.println(includePath);
//			req.setAttribute("includePath", includePath);
////			String url = "/back_end/product/addProduct.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp"); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//			return;
//		} catch (Exception e) {
//			errorMsgs.add(e.getMessage());
//			
//            
//			String includePath = "/back_end/product/addProduct.jsp";
////			System.out.println(includePath);
//			req.setAttribute("includePath", includePath);
////			String url = "/back_end/product/addProduct.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp"); // 新增成功後轉交listAllEmp.jsp
//			successView.forward(req, res);
//			return;
//		}
//
//	}
//		

		
//		if ("addProduct2".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//			
//				String pd_name = req.getParameter("pd_name");
//				String pd_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
//				if (pd_name == null || pd_name.trim().length() == 0) {
//					errorMsgs.add("商品名稱: 請勿空白");
//				} else if (!pd_name.trim().matches(pd_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
//				}
//
//				Integer pd_price = null;
//				try {
//					pd_price = new Integer(req.getParameter("pd_price").trim());
//				} catch (NumberFormatException e) {
//					pd_price = 0;
//					errorMsgs.add("金額請填數字.");
//				}
//
//				String pd_detail = req.getParameter("pd_detail");
//				if (pd_detail == null || pd_detail.trim().length() == 0) {
//					errorMsgs.add("商品詳述: 請勿空白");
//				}
//
//				String pd_typeNo = req.getParameter("pd_typeNo").trim();
//
//				ProductVO productVO = new ProductVO();
//
//				/*--------------------------------------------------------------*/
//				//＝＝＝＝＝＝＝＝＝＝＝＝＝＝多張圖片尚未完成=======================//
////				List<byte[]> pd_pics = new ArrayList<byte[]>();
////				byte[] pd_pic = null;
////				InputStream in;
////				BufferedInputStream bf;
////
////				try {
////
////					Collection<Part> parts = req.getParts();
////					int piclen = 0;
////					for(Part part: parts) {
////						in = part.getInputStream();
////						bf = new BufferedInputStream(in);
////						if (part.getSize() != 0) {
////							pd_pic = new byte[bf.available()]; // 暫存記憶體
////							piclen++;
////						}
////						bf.read(pd_pic);
////						bf.close();
////						in.close();
////						
////						pd_pics.add(pd_pic);
////						if(piclen == 0) {
////							errorMsgs.add("請上傳產品圖片");
////						}
////
//////						if (picBase64 != null && picBase64.trim().length() != 0) {
//////							pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
//////						}
////					}
//				//＝＝＝＝＝＝＝＝＝＝＝＝＝＝多張圖片尚未完成=======================//
//				           System.out.println(req.getParameter("pd_pic"));
//				           System.out.println(req.getParameter("pd_pic2"));
//				List<byte[]> pd_pics = new ArrayList<byte[]>();				
//				byte[] pd_pic = null;
//				byte[] pd_pic2 = null;
//				InputStream in;
//				BufferedInputStream bf;
//
//				try {
//
//					Collection<Part> parts = req.getParts();
//					String picBase64 = req.getParameter("picBase64");
//					int piclen = 0;
//					for(Part part: parts) {
//						in = part.getInputStream();
//						bf = new BufferedInputStream(in);
//						if (part.getSize() != 0) {
//							pd_pic = new byte[bf.available()]; // 暫存記憶體
//							System.out.println("有東西");
//							piclen++;
//						}
//						
//						bf.read(pd_pic);
//						bf.close();
//						in.close();
//
//						pd_pics.add(pd_pic);
//						if(piclen == 0) {
//							errorMsgs.add("請上傳產品圖片");
//						}
//
//						if (picBase64 != null && picBase64.trim().length() != 0) {
//							pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
//						}
//					}
//						
//						
//					if (picBase64 != null && picBase64.trim().length() != 0) {
//						pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
//					} // 若都沒有，mb_pic 以 null 送出
//					productVO.setPd_name(pd_name);
//					productVO.setPd_price(pd_price);
//					productVO.setPd_detail(pd_detail);
//					productVO.setPd_typeNo(pd_typeNo);
//					productVO.setPd_pic(pd_pic);
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//					System.out.println("a");
//
//				} catch (IOException e) {
//					e.printStackTrace();
//
//				}
//				/*--------------------------------------------------------------*/
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//
//					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					String includePath = "/back_end/product/AllList2.jsp";
////					System.out.println(includePath);
//					req.setAttribute("includePath", includePath);
//					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/index.jsp");
//					failureView.forward(req, res);
//					System.out.println("3");
//					return;
//				}
//
//				/*************************** 2.開始新增資料 ***************************************/
//				ProductService productService = new ProductService();
//				productVO = productService.addProduct(pd_name, pd_price, pd_detail, pd_typeNo, pd_pic);
//		     
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String includePath = "/back_end/product/AllList2.jsp";
////				System.out.println(includePath);
//				req.setAttribute("includePath", includePath);
////				String url = "/back_end/product/addProduct.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp"); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
//				return;
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/addProduct.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//		}
		

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
					String includePath = "/back_end/product/addProduct.jsp";
//					System.out.println(includePath);
					req.setAttribute("includePath", includePath);
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/index.jsp");
					failureView.forward(req, res);
					System.out.println("3");
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ProductService productService = new ProductService();
				productVO = productService.addProduct(pd_name, pd_price, pd_detail, pd_typeNo, pd_pic);
		     
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String includePath = "/back_end/product/AllList2.jsp";
//				System.out.println(includePath);
				req.setAttribute("includePath", includePath);
//				String url = "/back_end/product/addProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp"); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());

                
				String includePath = "/back_end/product/addProduct.jsp";
//				System.out.println(includePath);
				req.setAttribute("includePath", includePath);
//				String url = "/back_end/product/addProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp"); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				return;
			}

		}

		if (action.equals("getOne_For_update")) { // 來自listAllEmp.jsp的請求
         System.out.println("有進來喔");
//			List<String> errorMsgs = new LinkedList<String>();
         List<String> errorMsgs = (List<String>) req.getAttribute("errorMsgs");
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String pd_no = req.getParameter("pd_no");
				String pd_name = req.getParameter("pd_name");
				System.out.println(pd_no);
				System.out.println(pd_name);
				String whichPage = req.getParameter("whichPage");
			
//				System.out.println("開頁頁數是" + whichPage);

				/*************************** 2.開始查詢資料 ****************************************/
				ProductService productService = new ProductService();
				ProductVO productVO = productService.findOneProduct(pd_no);
                 
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
//				String url = "/back_end/product/UpdateProduct.jsp";
				req.setAttribute("whichPage", whichPage);
				String includePath = "/back_end/product/UpdateProduct.jsp";
//				System.out.println(includePath);
				req.setAttribute("includePath", includePath);
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp");
				successView.forward(req, res);
                  return;
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				String includePath = "/back_end/product/UpdateProduct.jsp";
				req.setAttribute("includePath", includePath);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/AllList2.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("updateProduct".equals(action)) { // 來自addProducr.jsp的請求
			System.out.println("也有進來喔");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			String whichPage = (String) req.getAttribute("whichPage");
//			System.out.println("修改的該頁頁數是" + whichPage);

			String pd_no = req.getParameter("pd_no").trim();
			String whichPage = req.getParameter("whichPage");
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
//				String includePath = "/back_end/product/UpdateProduct.jsp";
//              
//				req.setAttribute("includePath", includePath);
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/UpdateProduct.jsp");
				req.setAttribute("errorMsgs", errorMsgs);	
				RequestDispatcher failureView = req.getRequestDispatcher("/ProductServlet?action=getOne_For_update&&pd_no="
				+pd_no);
				
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 ***************************************/

			try {
				ProductService productService = new ProductService();
				productService.updateProduct(pd_no, pd_name, pd_price, pd_detail, pd_typeNo, pd_status, pd_pic);

				/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
//				req.getSession().setAttribute("pd_typeNo", pd_typeNo); //因應被明軒include影響故註解
//				String url = "/back_end/product/AllList2.jsp";
				String includePath = "/back_end/product/AllList2.jsp";
				
				req.setAttribute("includePath", includePath);
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp");
				successView.forward(req, res);
                return;
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				req.setAttribute("whichPage", whichPage);
				String includePath = "/back_end/product/AllList2.jsp";
//				System.out.println(includePath);
				req.setAttribute("includePath", includePath);
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/UpdateProduct.jsp");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/index.jsp");
				failureView.forward(req, res);
				return;
			}

		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String whichPage = req.getParameter("whichPage");
			
//			System.out.println("刪除的該頁頁數字" + whichPage);
			try {
				/*************************** 1.接收請求參數 ***************************************/
				String pd_no = req.getParameter("pd_no");

				/*************************** 2.開始刪除資料 ***************************************/
				ProductService productService = new ProductService();
				productService.deleteProduct(pd_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//				String url = "/back_end/product/AllList2.jsp";
				String includePath = "/back_end/product/AllList2.jsp";
//				System.out.println(includePath);
				req.setAttribute("includePath", includePath);
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp");
				successView.forward(req, res);
				return;
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/AllList2.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if (action.equals("searchTypeList")) {
			String pd_typeNo = req.getParameter("pd_typeNo");

			req.getSession().setAttribute("pd_typeNo", pd_typeNo);
			String includePath = "/back_end/product/AllList2.jsp"; //讓include增加商品頁面的bar得到網址
//			System.out.println(includePath);
			req.setAttribute("includePath", includePath);
//			String url = "/back_end/product/AllList2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp");
			successView.forward(req, res);
			return;
		}
		if (action.equals("changePd_status1")) {

			String pd_no = req.getParameter("pd_no");
			String whichPage = req.getParameter("whichPage");
			ProductVO productVO = new ProductVO();

			productVO.setPd_no(pd_no);
			productVO.setPd_status(1);

			ProductService productService = new ProductService();
			productService.changeStatus(productVO);

			req.setAttribute("whichPage", whichPage);
//			String url = "/back_end/product/AllList2.jsp";
			String includePath = "/back_end/product/AllList2.jsp";
//			System.out.println(includePath);
			req.setAttribute("includePath", includePath);
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp");
			successView.forward(req, res);
			return;
		}

		if (action.equals("changePd_status2")) {

			String pd_no = req.getParameter("pd_no");
			String whichPage = req.getParameter("whichPage");
			ProductVO productVO = new ProductVO();

			productVO.setPd_no(pd_no);
			productVO.setPd_status(2);

			ProductService productService = new ProductService();
			productService.changeStatus(productVO);

			req.setAttribute("whichPage", whichPage);
//			String url = "/back_end/product/AllList2.jsp";
			String includePath = "/back_end/product/AllList2.jsp";
//			System.out.println(includePath);
			req.setAttribute("includePath", includePath);
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 AllList2.jsp
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp");
			successView.forward(req, res);
			return;
		}

		if (action.equals("CompositeQuery_Product")) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map", map1);
					map = map1;
				}
//				System.out.println(map);

				if ("".contentEquals(map.get("lowPrice")[0]) && !"".contentEquals(map.get("highPrice")[0])) {
					errorMsgs.add("最低價格請勿空白");
				}

				if (!"".contentEquals(map.get("lowPrice")[0]) && "".contentEquals(map.get("highPrice")[0])) {
					errorMsgs.add("最高價格請勿空白");
				}

				if (!errorMsgs.isEmpty()) {

					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product/Pd_typeNoList.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始複合查詢 ***************************************/
				ProductService productService = new ProductService();
				List<ProductVO> list = productService.superGetAll(map);

				req.setAttribute("list", list);
				String url = "/front_end/product/Pd_typeNoList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 AllList2.jsp
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				errorMsgs.add("搜尋資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product/ShopHome.jsp");
				failureView.forward(req, res);
				return;
			}

		}
		
		if ("addProduct3".equals(action)) {
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

			byte[] pd_pic = null;
			InputStream in;
			BufferedInputStream bf;

			try {

				Part part = req.getPart("pd_pic");
				Part part2 = req.getPart("pd_pic2");
				Part part3 = req.getPart("pd_pic3");
				Part part4 = req.getPart("pd_pic4");
                List<Part> parts = new ArrayList<Part>();
                parts.add(part);
                parts.add(part2);
                parts.add(part3);
                parts.add(part4);
                System.out.println(parts);
                
                List<byte[]> pd_pics = new ArrayList();
                
			   for(Part aPart : parts) {
					in = aPart.getInputStream();
				bf = new BufferedInputStream(in);
				int piclen = 0;
				if (aPart.getSize() != 0) {
					pd_pic = new byte[bf.available()]; // 暫存記憶體
					piclen++;
					
				}
				bf.read(pd_pic);
				bf.close();
				in.close();
				
				pd_pics.add(pd_pic);
				if(parts == null) {
					errorMsgs.add("請上傳產品圖片");
				}

		
			}
			     System.out.println(pd_pics);
				
				String picBase64 = req.getParameter("picBase64");
				in = part.getInputStream();
				bf = new BufferedInputStream(in);
				if (part.getSize() != 0) {
					pd_pic = new byte[bf.available()]; // 暫存記憶體

				} else if (picBase64 != null && picBase64.trim().length() != 0) { // 第一次有上傳圖片，第二次無，使用參數傳進來的picBase64
					pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
				} else if (pd_pics == null || pd_pics.size() == 0) {
					errorMsgs.add("產品圖片請勿空白");

				}
				bf.read(pd_pic);
				bf.close();
				in.close();
				// 若都沒有，mb_pic 以 null 送出
				productVO.setPd_name(pd_name);
				productVO.setPd_price(pd_price);
				productVO.setPd_detail(pd_detail);
				productVO.setPd_typeNo(pd_typeNo);
				productVO.setPd_pic(pd_pics.get(0));
				productVO.setPd_pic2(pd_pics.get(1));
				productVO.setPd_pic3(pd_pics.get(2));
				productVO.setPd_pic4(pd_pics.get(3));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("請上傳圖片");
				
				

			} catch (IOException e) {
				e.printStackTrace();

			}
			/*--------------------------------------------------------------*/

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
                
				req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
				String includePath = "/back_end/product/addProduct.jsp";
//				System.out.println(includePath);
				req.setAttribute("includePath", includePath);
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/index.jsp");
				failureView.forward(req, res);
				System.out.println("3");
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductService productService = new ProductService();
			
			productVO = productService.addProduct3(productVO);
	     
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String includePath = "/back_end/product/AllList2.jsp";
//			System.out.println(includePath);
			req.setAttribute("includePath", includePath);
//			String url = "/back_end/product/addProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp"); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			return;
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			System.out.println("hi");
            
			String includePath = "/back_end/product/addProduct.jsp";
//			System.out.println(includePath);
			req.setAttribute("includePath", includePath);
//			String url = "/back_end/product/addProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp"); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			return;
		}

	}
		

		
//		if ("addProduct2".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//			
//				String pd_name = req.getParameter("pd_name");
//				String pd_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
//				if (pd_name == null || pd_name.trim().length() == 0) {
//					errorMsgs.add("商品名稱: 請勿空白");
//				} else if (!pd_name.trim().matches(pd_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");
//				}
//
//				Integer pd_price = null;
//				try {
//					pd_price = new Integer(req.getParameter("pd_price").trim());
//				} catch (NumberFormatException e) {
//					pd_price = 0;
//					errorMsgs.add("金額請填數字.");
//				}
//
//				String pd_detail = req.getParameter("pd_detail");
//				if (pd_detail == null || pd_detail.trim().length() == 0) {
//					errorMsgs.add("商品詳述: 請勿空白");
//				}
//
//				String pd_typeNo = req.getParameter("pd_typeNo").trim();
//
//				ProductVO productVO = new ProductVO();
//
//				/*--------------------------------------------------------------*/
//				//＝＝＝＝＝＝＝＝＝＝＝＝＝＝多張圖片尚未完成=======================//
////				List<byte[]> pd_pics = new ArrayList<byte[]>();
////				byte[] pd_pic = null;
////				InputStream in;
////				BufferedInputStream bf;
////
////				try {
////
////					Collection<Part> parts = req.getParts();
////					int piclen = 0;
////					for(Part part: parts) {
////						in = part.getInputStream();
////						bf = new BufferedInputStream(in);
////						if (part.getSize() != 0) {
////							pd_pic = new byte[bf.available()]; // 暫存記憶體
////							piclen++;
////						}
////						bf.read(pd_pic);
////						bf.close();
////						in.close();
////						
////						pd_pics.add(pd_pic);
////						if(piclen == 0) {
////							errorMsgs.add("請上傳產品圖片");
////						}
////
//////						if (picBase64 != null && picBase64.trim().length() != 0) {
//////							pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
//////						}
////					}
//				//＝＝＝＝＝＝＝＝＝＝＝＝＝＝多張圖片尚未完成=======================//
//				           System.out.println(req.getParameter("pd_pic"));
//				           System.out.println(req.getParameter("pd_pic2"));
//				List<byte[]> pd_pics = new ArrayList<byte[]>();				
//				byte[] pd_pic = null;
//				byte[] pd_pic2 = null;
//				InputStream in;
//				BufferedInputStream bf;
//
//				try {
//
//					Collection<Part> parts = req.getParts();
//					String picBase64 = req.getParameter("picBase64");
//					int piclen = 0;
//					for(Part part: parts) {
//						in = part.getInputStream();
//						bf = new BufferedInputStream(in);
//						if (part.getSize() != 0) {
//							pd_pic = new byte[bf.available()]; // 暫存記憶體
//							System.out.println("有東西");
//							piclen++;
//						}
//						
//						bf.read(pd_pic);
//						bf.close();
//						in.close();
//
//						pd_pics.add(pd_pic);
//						if(piclen == 0) {
//							errorMsgs.add("請上傳產品圖片");
//						}
//
//						if (picBase64 != null && picBase64.trim().length() != 0) {
//							pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
//						}
//					}
//						
//						
//					if (picBase64 != null && picBase64.trim().length() != 0) {
//						pd_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
//					} // 若都沒有，mb_pic 以 null 送出
//					productVO.setPd_name(pd_name);
//					productVO.setPd_price(pd_price);
//					productVO.setPd_detail(pd_detail);
//					productVO.setPd_typeNo(pd_typeNo);
//					productVO.setPd_pic(pd_pic);
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//					System.out.println("a");
//
//				} catch (IOException e) {
//					e.printStackTrace();
//
//				}
//				/*--------------------------------------------------------------*/
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//
//					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					String includePath = "/back_end/product/AllList2.jsp";
////					System.out.println(includePath);
//					req.setAttribute("includePath", includePath);
//					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/index.jsp");
//					failureView.forward(req, res);
//					System.out.println("3");
//					return;
//				}
//
//				/*************************** 2.開始新增資料 ***************************************/
//				ProductService productService = new ProductService();
//				productVO = productService.addProduct(pd_name, pd_price, pd_detail, pd_typeNo, pd_pic);
//		     
//				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String includePath = "/back_end/product/AllList2.jsp";
////				System.out.println(includePath);
//				req.setAttribute("includePath", includePath);
////				String url = "/back_end/product/addProduct.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp"); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);
//				return;
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/addProduct.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//		}

	}

}
