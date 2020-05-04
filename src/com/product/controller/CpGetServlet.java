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
import javax.servlet.http.HttpSession;

import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;
import com.cp_get.model.Cp_getService;
import com.cp_get.model.Cp_getVO;

public class CpGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");
		
		if("getCouponManagerIncludePath".equals(action)) {
			String includePath = "/back_end/product/CouponManage.jsp"; //讓include增加商品頁面的bar得到網址
			System.out.println(includePath);
			req.setAttribute("includePath", includePath);
			
			RequestDispatcher goToStaff = req.getRequestDispatcher("/back_end/staff/index.jsp"); //include商品的bar
			goToStaff.forward(req, res);
			return;
		}

		if (action.equals("searchMemberCpGet")) {

			String mb_id = req.getParameter("mb_id");

			req.setAttribute("mb_id", mb_id);

			String url = "/back_end/product/ListOneMemberGetCoupon.jsp";

			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			return;

		}

		if (action.equals("addCouponTypeNo")) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String cp_name = req.getParameter("cp_name");
				String cp_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$";
				if (cp_name == null || cp_name.trim().length() == 0) {
					errorMsgs.add("優惠券名稱: 請勿空白");
				} else if (!cp_name.trim().matches(cp_nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("優惠券名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到50之間");

				}
				Integer cp_price = null;
				try {

					cp_price = new Integer(req.getParameter("cp_price").trim());
				} catch (NumberFormatException e) {
					cp_price = 0;
					errorMsgs.add("金額請填數字.");
				}
				String cp_detail = req.getParameter("cp_detail");
				if (cp_detail == null || cp_detail.trim().length() == 0) {
					errorMsgs.add("優惠券備註: 請勿空白");
				}
				CouponVO couponVO = new CouponVO();
				couponVO.setCp_name(cp_name);
				couponVO.setCp_price(cp_price);
				couponVO.setCp_detail(cp_detail);

				/*--------------------------------------------------------------*/

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {

					req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/CouponManage.jsp");
					failureView.forward(req, res);
					System.out.println("3");
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				CouponService couponService = new CouponService();
				couponService.addCoupon(couponVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//				String url = "/back_end/product/CouponManage.jsp";
				String includePath = "/back_end/product/CouponManage.jsp"; //讓include增加商品頁面的bar得到網址
				System.out.println(includePath);
				req.setAttribute("includePath", includePath);
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交couponManage.jsp
				RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp"); //include商品的bar
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/product/CouponManage.jsp");
				failureView.forward(req, res);
				return;
			}

		}
		if (action.equals("giveCoupon")) {

			String mb_id = req.getParameter("soowii123");
			String cp_no = req.getParameter("cp_no");
			System.out.println(cp_no);
            Cp_getVO cp_getVO = new Cp_getVO();
            cp_getVO.setMb_id(mb_id);           
            cp_getVO.setCp_no(cp_no);
            Cp_getService cp_getService = new Cp_getService();
            cp_getService .aMemberGetCoupon(cp_getVO);
//			String url = "/back_end/product/CouponManage.jsp";
        	String includePath = "/back_end/product/CouponManage.jsp"; //讓include增加商品頁面的bar得到網址
			System.out.println(includePath);
			req.setAttribute("includePath", includePath);

//			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			RequestDispatcher successView = req.getRequestDispatcher("/back_end/staff/index.jsp"); //include商品的bar
			successView.forward(req, res);
			return;

		}
	}

}
