package com.cmt.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.cmt.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 100 * 1024 * 1024, maxRequestSize = 5 * 5 * 100
		* 1024 * 1024)
public class LocationServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("cmt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入地標編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String cmt_no = null;
				try {
					cmt_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CmtService cmtSvc = new CmtService();
				CmtVO cmtVO = cmtSvc.getOneCmt(cmt_no);
				if (cmtVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cmtVO", cmtVO); // 資料庫取出的VO物件,存入req
				String url = "/front_end/cmt/listOneCmt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String cmt_no = new String(req.getParameter("cmt_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				CmtService cmtSvc = new CmtService();
				CmtVO cmtVO = cmtSvc.getOneCmt(cmt_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("cmtVO", cmtVO); // 資料庫取出的VO物件,存入req
				String url = "/front_end/cmt/update_cmt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update__input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/listAllCmt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update__input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
//				String loc_no = new String(req.getParameter("loc_no").trim());
				String cmt_no =req.getParameter("cmt_no");
				String cmt_content = req.getParameter("cmt_content");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cmt_content == null || cmt_content.trim().length() == 0) {
					errorMsgs.add("留言內容: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				java.sql.Date cmt_time = null;
				try {
					cmt_time = java.sql.Date.valueOf(req.getParameter("cmt_time").trim());
				} catch (IllegalArgumentException e) {
					cmt_time=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String longitude = req.getParameter("longitude").trim();
				if (longitude == null || longitude.trim().length() == 0) {
					errorMsgs.add("經度請勿空白");
				}

				String latitude = req.getParameter("latitude").trim();
				if (latitude == null || latitude.trim().length() == 0) {
					errorMsgs.add("緯度請勿空白");
				}



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

				LocationVO locationVO = new LocationVO();
//				loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic
				locationVO.setLoc_no(loc_no);
				locationVO.setLoc_typeno(loc_typeno);
				locationVO.setLongitude(longitude);
				locationVO.setLatitude(latitude);
				locationVO.setLoc_status(loc_status);
				locationVO.setLoc_address(loc_address);
				byte[] loc_pic = null;
//				loc_pic = req.getParameter("loc_pic").getBytes();
				InputStream in;
				BufferedInputStream bf;

				try {
					Part part = req.getPart("loc_pic");
					in = part.getInputStream();
//					in = new FileInputStream("/fake_picture/loc" + String.format("%05d", 6) + ".jpg");
					bf = new BufferedInputStream(in);
					loc_pic = new byte[bf.available()];// 讀入的圖檔,暫存在記憶體
					if (loc_pic == null || loc_pic.length == 0) {
						LocationService src = new LocationService();
						LocationVO locationVO_old = src.getOneLocation(loc_no);
						loc_pic = locationVO_old.getLoc_pic();
					}
					bf.read(loc_pic);
					locationVO.setLoc_pic(loc_pic);
					bf.close();
					in.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("locationVO", locationVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/location/update_location_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				LocationService locationSvc = new LocationService();
				locationVO = locationSvc.updateLocation(loc_no, loc_typeno, longitude, latitude, loc_status,loc_address, loc_pic);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("locationVO", locationVO); // 資料庫update成功後,正確的的VO物件,存入req
				String url = "/front_end/location/listOneLocation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/location/update_location_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
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

				LocationVO locationVO = new LocationVO();
//				loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic
				locationVO.setLoc_typeno(loc_typeno);
				locationVO.setLongitude(longitude);
				locationVO.setLatitude(latitude);
				locationVO.setLoc_status(loc_status);
				locationVO.setLoc_address(loc_address);
				byte[] loc_pic = null;
//				loc_pic = req.getParameter("loc_pic").getBytes();
				InputStream in;
				BufferedInputStream bf;

				try {
					Part part = req.getPart("loc_pic");
					in = part.getInputStream();
//					in = new FileInputStream("/fake_picture/loc" + String.format("%05d", 6) + ".jpg");
					bf = new BufferedInputStream(in);
					loc_pic = new byte[bf.available()];// 讀入的圖檔,暫存在記憶體
					if (loc_pic == null || loc_pic.length == 0) {
						errorMsgs.add("地標圖片請勿空白");
					}
					bf.read(loc_pic);
					locationVO.setLoc_pic(loc_pic);
					bf.close();
					in.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("locationVO", locationVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/location/addLocation.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				LocationService locationSvc = new LocationService();
				locationVO = locationSvc.addLocation(loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/location/listAllLocation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/location/addLocation.jsp");
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
				String loc_no = new String(req.getParameter("loc_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				LocationService locationSvc = new LocationService();
				locationSvc.deleteLocation(loc_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/location/listAllLocation.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/location/listAllLocation.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
