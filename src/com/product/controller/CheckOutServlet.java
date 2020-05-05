package com.product.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
import com.od_detail.model.Od_detailLineVO;
import com.od_detail.model.Od_detailVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.product.model.ProductVO;

import org.json.*;

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		String linePay = req.getParameter("payMethod");

		HttpSession session = req.getSession();
		if (linePay.equals("LinePay")) {
			String cp_get = (String) session.getAttribute("cp_get");
			
//			String mb_id = (String) session.getAttribute("mb_id");
			String mb_id = (String) req.getParameter("mb_id");
//			System.out.println("req: "+mb_id);
			
			Integer discount = (Integer) session.getAttribute("discount");
			
			String payMethod = req.getParameter("payMethod");
			
			Integer totalPrice = (Integer) session.getAttribute("totalPrice");
			@SuppressWarnings("unchecked")
			Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");
			@SuppressWarnings("unchecked")
			ProductVO buyOne = (ProductVO)((Vector<ProductVO>) session.getAttribute("shoppingCart")).get(0);
			
			List<String> errorMsgs = new LinkedList<String>();
//			Pd_typeVO pd_typeVO = new Pd_typeVO();
			String od_add = req.getParameter("od_add");
			if (od_add == null || od_add.trim().length() == 0) {
				errorMsgs.add("地址: 地址請勿空白");
			}
			
			Integer od_totalPrice = totalPrice;
			
			
			//原本的ordersVO
			OrdersVO ordersVO = new OrdersVO();
//			System.out.println("VO: "+mb_id);
			ordersVO.setMb_id(mb_id);
			ordersVO.setOd_totalPrice(od_totalPrice);
			ordersVO.setOd_add(od_add);
			ordersVO.setOd_discount(discount);
			ordersVO.setCp_no(cp_get);
			Cp_getVO cp_getVO = new Cp_getVO();
			cp_getVO.setMb_id(mb_id);
			cp_getVO.setCp_status(1);
			
			//原本的orders_deyailVO
			List<Od_detailVO> testList = new ArrayList<Od_detailVO>(); // 將購物車每一欄資料匯入訂單明細每一筆資料，再倒入訂單明細陣列(多筆訂單明細)
//			for (int i = 0; i < buylist.size(); i++) {
//				ProductVO order = buylist.get(i);
//				String pd_no = order.getPd_no();
//				int pd_price = order.getPd_price();
//				int pd_quantity = order.getPd_quantity();
//				String pd_typeSize = order.getPd_typeSize();
//				Od_detailVO od_detailVO = new Od_detailVO();
//				od_detailVO.setPd_no(pd_no);
//				od_detailVO.setOd_price(pd_price);
//				od_detailVO.setOd_amount(pd_quantity);
//				od_detailVO.setPd_size(pd_typeSize);
//				testList.add(od_detailVO);
//			}
			
			//LinePay的VO不使用優惠券，且只能買一個
			ProductVO order = buyOne;
			//id及名稱(line) (1,1)
			String pd_no = order.getPd_no();
			String productName = order.getPd_name();
			
			//價格 (2,2)
			int pd_price = order.getPd_price();
			int amount = order.getPd_price();
			
			//數量(在這邊只會是1) (4,2)
			int pd_quantity = 1;
			String pd_typeSize = order.getPd_typeSize();
			
			//Line的貨幣及成功頁面 (4,3)
			String currency = "TWD";
			
			Od_detailVO od_detailVO = new Od_detailVO();
			od_detailVO.setPd_no(pd_no);
			od_detailVO.setOd_price(pd_price);
			od_detailVO.setOd_amount(pd_quantity);
			od_detailVO.setPd_size(pd_typeSize);
			testList.add(od_detailVO);
			
			Od_detailLineVO od_detailLineVO = new Od_detailLineVO();
			od_detailLineVO.setProductName(productName);
//			od_detailLineVO.setProductName("trytrykan");
			od_detailLineVO.setAmount(amount);
			od_detailLineVO.setCurrency(currency);
			
			if (!errorMsgs.isEmpty()) {
				
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/product/Checkout.jsp");
				failureView.forward(req, res);
				
				return;
			}
			OrdersService ordersService = new OrdersService();
			String od_no = ordersService.addOrdersWithPd_detail(ordersVO, testList);
			//同時也給Linebot的資料庫新增一筆訂單，只存必要資訊
			ordersService.addOrdersPG(od_no, mb_id, 1);
			
			//訂單編號 (4,4)
			ordersVO.setOd_no(od_no);
			od_detailLineVO.setOrderId(od_no);
			
			//圖片網址或base64
//			Od_detailService od_detailSvc = new Od_detailService();
//			Od_detailVO od_detailVO4Pic = od_detailSvc.search_one_oeder_detail(od_no).get(0);
			//這是別的套件的
//			String productImageUrl = Base64.encodeBytes(buyOne.getPd_pic());
//			locationJsonVO.setPic(Base64.getEncoder().encodeToString(rs.getBytes("loc_pic")));
//			String productImageUrl = Base64.getEncoder().encodeToString(buyOne.getPd_pic());
			od_detailLineVO.setProductImageUrl("https://tshop.r10s.jp/afrobeat/cabinet/cl_kd/1346525.jpg?fitin=84:84");
//			od_detailLineVO.setProductImageUrl("https://f.ecimg.tw/items/DIBF2RA9008PMTH/000001_1542332754.jpg");
			
			session.setAttribute("ordersVO", ordersVO);
			session.setAttribute("payMethod", payMethod);
//			req.setAttribute("ordersVO", ordersVO);
//			req.setAttribute("payMethod", payMethod);
			session.setAttribute("buylistCount", 0);
			
			//跳轉網址(4,5)
			String url = "http://localhost:8081/DA106_G5/front_end/product/MemberTransaction_record.jsp";
//			od_detailLineVO.setConfirmUrl(url);
			od_detailLineVO.setConfirmUrl(url);
			
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			
//			successView.forward(req, res);
			
			if (buylist != null) {
				buylist = null;
				session.setAttribute("shoppingCart", buylist);
			}
//			res.setHeader("Content-Type", "application/json");
//			res.setHeader("X-LINE-ChannelId", "1653839045");
//			res.setHeader("X-LINE-ChannelSecret", "7df947ce3ae22422b7319419a1e4d8a5");
			
			String jsonStr = new JSONObject(od_detailLineVO).toString();
			System.out.println(jsonStr);
//			PrintWriter out = res.getWriter();
//			out.write(jsonStr);
//			out.flush();
//			out.close();
//			res.sendRedirect("https://sandbox-api-pay.line.me/v2/payments/request");
			
			req.setCharacterEncoding("UTF-8");
			
			//複製網路開始
			boolean doSuccess = false; 
		    java.io.BufferedWriter wr = null; 
		    try { 
		      URL urlLinePay = new URL("https://sandbox-api-pay.line.me/v2/payments/request"); 
		      
		      HttpURLConnection URLConn = (HttpURLConnection) urlLinePay.openConnection(); 
		      URLConn.setDoOutput(true); 
		      URLConn.setDoInput(true);
		      
		      //LineApi要求
		      ((HttpURLConnection) URLConn).setRequestMethod("POST"); 
			  URLConn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			  URLConn.setRequestProperty("X-LINE-ChannelId", "1653839045");
			  URLConn.setRequestProperty("X-LINE-ChannelSecret", "7df947ce3ae22422b7319419a1e4d8a5");
		      
			  URLConn.setUseCaches(false); 
		      URLConn.setAllowUserInteraction(true); 
		      HttpURLConnection.setFollowRedirects(true); 
		      URLConn.setInstanceFollowRedirects(true); 
		      URLConn.setRequestProperty("User-agent","Mozilla/5.0 (Windows; U; Windows NT 6.0; zh-TW; rv:1.9.1.2) "+ "Gecko/20090729 Firefox/3.5.2 GTB5 (.NET CLR 3.5.30729)"); 
		      URLConn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"); 
		      URLConn.setRequestProperty("Accept-Language","zh-tw,en-us;q=0.7,en;q=0.3"); 
		      URLConn.setRequestProperty("Accept-Charse","utf-8,Big5;q=0.7,*;q=0.7"); 
		    
//		      if (cookie != null) 
//		        URLConn.setRequestProperty("Cookie", cookie); 
//		      if (referer != null) 
//		        URLConn.setRequestProperty("Referer", referer); 
//		      URLConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
//		      URLConn.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length)); 
//		      URLConn.setRequestProperty("Content-Length", String.valueOf(jsonStr.getBytes().length)); 
		      java.io.DataOutputStream dos = new java.io.DataOutputStream(URLConn.getOutputStream()); 
		      dos.writeBytes(jsonStr); 
//		      java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(URLConn.getInputStream(),charset)); 
		      java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(URLConn.getInputStream(),"UTF-8")); 
//		      String lines; 
//		      while ((lines = rd.readLine()) != null) { 
//		        System.out.println(lines); 
//		      } 
		      String lines = rd.readLine();
		      System.out.println(lines); 
		      JSONObject jsonObj = new JSONObject(lines);
		      JSONObject jsonObjInfo = new JSONObject(jsonObj.get("info").toString());
		      JSONObject jsonObjPaymentUrl = new JSONObject(jsonObjInfo.get("paymentUrl").toString());
		      String jsonWeb = jsonObjPaymentUrl.get("web").toString();
		      System.out.println(jsonWeb);
		      rd.close(); 
//		      RequestDispatcher successView = req.getRequestDispatcher(jsonWeb);
//		      successView.forward(req, res);
		      res.sendRedirect(jsonWeb);  // 登入換地址，此處也要換
		      
		    } catch (java.io.IOException e) { 
		      doSuccess = false; 
		      System.out.println(e);
		    } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally { 
		      if (wr != null) { 
		        try { 
		          wr.close(); 
		        } catch (java.io.IOException ex) { 
		        	System.out.println(ex);
		        } 
		        wr = null; 
		      } 
		    } 
//		    return doSuccess; 
		    //複製網路結束
		    System.out.println("doSuccess: " + doSuccess);
			return;
		}

		else if (action.equals("getOd_detail_Information")) {
			String cp_get = (String) session.getAttribute("cp_get");
			String mb_id = (String) session.getAttribute("mb_id");
			Integer discount = (Integer) session.getAttribute("discount");
			
			String payMethod = req.getParameter("payMethod");

			Integer totalPrice = (Integer) session.getAttribute("totalPrice");
			@SuppressWarnings("unchecked")
			Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shoppingCart");
			List<String> errorMsgs = new LinkedList<String>();
//			Pd_typeVO pd_typeVO = new Pd_typeVO();
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
			//同時也給Linebot的資料庫新增一筆訂單，只存必要資訊
//			System.out.println("mb_id: " + mb_id);
			ordersService.addOrdersPG(od_no, mb_id, 1);
			
			ordersVO.setOd_no(od_no);
			session.setAttribute("ordersVO", ordersVO);
			session.setAttribute("payMethod", payMethod);
//			req.setAttribute("ordersVO", ordersVO);
//			req.setAttribute("payMethod", payMethod);
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
