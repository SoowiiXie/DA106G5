package com.location.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.location.model.*;

public class LocationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("loc_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/location/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String loc_no = null;
				try {
					loc_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/location/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				LocationService locationSvc = new LocationService();
				LocationVO locationVO = locationSvc.getOneLocation(loc_no);
				if (locationVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/location/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("locationVO", locationVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/location/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/location/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String loc_no = new String(req.getParameter("loc_no"));
				
				/***************************2.開始查詢資料****************************************/
				LocationService locationSvc = new LocationService();
				LocationVO locationVO = locationSvc.getOneLocation(loc_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("locationVO", locationVO);         // 資料庫取出的empVO物件,存入req
				String url = "/front_end/location/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/location/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String loc_no = new String(req.getParameter("loc_no").trim());
				
				String loc_typeno = req.getParameter("loc_typeno");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (loc_typeno == null || loc_typeno.trim().length() == 0) {
					errorMsgs.add("地點編號: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String longitude = req.getParameter("longitude").trim();
				if (longitude == null || longitude.trim().length() == 0) {
					errorMsgs.add("經度請勿空白");
				}	
				
				String latitude = req.getParameter("latitude").trim();
				if (latitude == null || latitude.trim().length() == 0) {
					errorMsgs.add("緯度請勿空白");
				}	
				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

				Integer loc_status = null;
				try {
					loc_status = new Integer(req.getParameter("loc_status").trim());
				} catch (NumberFormatException e) {
					loc_status = 1;
					errorMsgs.add("地點狀態請填數字.");
				}

				String loc_address = req.getParameter("loc_address").trim();
				if (loc_address == null || loc_address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				
				byte[] loc_pic = req.getParameter("loc_pic").getBytes();
				if (loc_pic == null || loc_pic.length == 0) {
					errorMsgs.add("地標圖片請勿空白");
				}	

//				loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic
				LocationVO locationVO = new LocationVO();
				locationVO.setLoc_no(loc_no);
				locationVO.setLoc_typeno(loc_typeno);
				locationVO.setLongitude(longitude);
				locationVO.setLatitude(latitude);
				locationVO.setLoc_status(loc_status);
				locationVO.setLoc_address(loc_address);
				locationVO.setLoc_pic(loc_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("locationVO", locationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/location/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				LocationService locationSvc = new LocationService();
				locationVO = locationSvc.updateLocation(loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("locationVO", locationVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front_end/location/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/location/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String loc_typeno = req.getParameter("loc_typeno");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (loc_typeno == null || loc_typeno.trim().length() == 0) {
					errorMsgs.add("地點編號: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String longitude = req.getParameter("longitude").trim();
				if (longitude == null || longitude.trim().length() == 0) {
					errorMsgs.add("經度請勿空白");
				}	
				
				String latitude = req.getParameter("latitude").trim();
				if (latitude == null || latitude.trim().length() == 0) {
					errorMsgs.add("緯度請勿空白");
				}	
				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

				Integer loc_status = null;
				try {
					loc_status = new Integer(req.getParameter("loc_status").trim());
				} catch (NumberFormatException e) {
					loc_status = 1;
					errorMsgs.add("地點狀態請填數字.");
				}

				String loc_address = req.getParameter("loc_address").trim();
				if (loc_address == null || loc_address.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				
				byte[] loc_pic = req.getParameter("loc_pic").getBytes();
				if (loc_pic == null || loc_pic.length == 0) {
					errorMsgs.add("地標圖片請勿空白");
				}

				LocationVO locationVO = new LocationVO();
//				loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic
				locationVO.setLoc_typeno(loc_typeno);
				locationVO.setLongitude(longitude);
				locationVO.setLatitude(latitude);
				locationVO.setLoc_status(loc_status);
				locationVO.setLoc_address(loc_address);
				locationVO.setLoc_pic(loc_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("locationVO", locationVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/location/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				LocationService locationSvc = new LocationService();
				locationVO = locationSvc.addLocation(loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/location/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/location/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String loc_no = new String(req.getParameter("loc_no"));
				
				/***************************2.開始刪除資料***************************************/
				LocationService locationSvc = new LocationService();
				locationSvc.deleteLocation(loc_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/location/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/location/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
