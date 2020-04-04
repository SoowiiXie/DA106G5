package com.weather_detail.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.weather_detail.model.Weather_detailService;
import com.weather_detail.model.Weather_detailVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 100 * 1024 * 1024, maxRequestSize = 5 * 5 * 100
		* 1024 * 1024)
public class Weather_detailServlet extends HttpServlet {

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
			// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
			// wth_rain_chance
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("weather_time");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸欲查詢的時間");
				}
				String str2 = req.getParameter("weather_place");
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸欲查詢的地點");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/weather_detail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Timestamp weather_time = null;
				try {
					weather_time = java.sql.Timestamp.valueOf(str.trim());
//					weather_time = new Timestamp(str);
				} catch (Exception e) {
					errorMsgs.add("時間格式不正確");
				}
				String weather_place = null;
				try {
					weather_place = new String(str2);
				} catch (Exception e) {
					errorMsgs.add("時間格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/weather_detail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Weather_detailService weather_detailSvc = new Weather_detailService();
				Weather_detailVO weather_detailVO = (com.weather_detail.model.Weather_detailVO) weather_detailSvc
						.getOneWeather_detail(weather_time, weather_place);
				if (weather_detailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/weather_detail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("weather_detailVO", weather_detailVO); // 資料庫取出的VO物件,存入req
				String url = "/front_end/weather_detail/listOneWeather_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/weather_detail/select_page.jsp");
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
				System.out.println("getOne_For_Update");
				Timestamp weather_time = java.sql.Timestamp.valueOf(req.getParameter("weather_time").trim());
				System.out.println(weather_time);
				String weather_place = new String(req.getParameter("weather_place"));
				/*************************** 2.開始查詢資料 ****************************************/
				Weather_detailService weather_detailSvc = new Weather_detailService();
				List<Weather_detailVO> weather_detailVO =  weather_detailSvc.getOneWeather_detail(weather_time, weather_place);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("weather_detailVO", weather_detailVO); // 資料庫取出的VO物件,存入req
//				String url = "/front_end/weather_detail/update_weather_detail_input.jsp";
				String url = "/front_end/weather_detail/listAllUWish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update__input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/weather_detail/listAllWeather_detail.jsp");
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
//				String loc_no = new String(req.getParameter("loc_no").trim());
				Timestamp weather_time = null;
				try {
					weather_time = Timestamp.valueOf(req.getParameter("weather_time").trim());
				} catch (IllegalArgumentException e) {
					weather_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入時間!");
				}
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				String weather_place = req.getParameter("weather_place");
				if (weather_place == null || weather_place.trim().length() == 0) {
					errorMsgs.add("地點: 請勿空白");
				}
				// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
				// wth_rain_chance
				String wth_status = req.getParameter("wth_status");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (wth_status == null || wth_status.trim().length() == 0) {
					errorMsgs.add("天氣狀況: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer wth_high = null;
				try {
					wth_high = new Integer(req.getParameter("wth_high").trim());
				} catch (NumberFormatException e) {
					wth_high = 777;
					errorMsgs.add("最高溫請填數字.");
				}

				Integer wth_low = null;
				try {
					wth_low = new Integer(req.getParameter("wth_low").trim());
				} catch (NumberFormatException e) {
					wth_low = -777;
					errorMsgs.add("最低溫請填數字.");
				}

				String wth_comfort = req.getParameter("wth_comfort").trim();
				if (wth_comfort == null || wth_comfort.trim().length() == 0) {
					errorMsgs.add("舒適度請勿空白");
				}

				Integer wth_rain_chance = null;
				try {
					wth_rain_chance = new Integer(req.getParameter("wth_rain_chance").trim());
				} catch (NumberFormatException e) {
					wth_rain_chance = 100;
					errorMsgs.add("降雨機率請填數字.");
				}

				Weather_detailVO weather_detailVO = new Weather_detailVO();
				// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
				// wth_rain_chance
				weather_detailVO.setWeather_time(weather_time);
				weather_detailVO.setWeather_place(weather_place);
				weather_detailVO.setWth_status(wth_status);
				weather_detailVO.setWth_high(wth_high);
				weather_detailVO.setWth_low(wth_low);
				weather_detailVO.setWth_comfort(wth_comfort);
				weather_detailVO.setWth_rain_chance(wth_rain_chance);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("weather_detailVO", weather_detailVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/weather_detail/update_weather_detail_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Weather_detailService weather_detailSvc = new Weather_detailService();
				weather_detailVO = weather_detailSvc.updateWeather_detail(weather_time, weather_place, wth_status,
						wth_high, wth_low, wth_comfort, wth_rain_chance);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("weather_detailVO", weather_detailVO); // 資料庫update成功後,正確的的VO物件,存入req
				String url = "/front_end/weather_detail/listOneWeather_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/weather_detail/update_weather_detail_input.jsp");
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
				System.out.println("insert");
				Timestamp weather_time = null;
				try {
					weather_time = Timestamp.valueOf(req.getParameter("weather_time").trim());
				} catch (IllegalArgumentException e) {
					weather_time = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入時間!");
				}
				System.out.println(weather_time);
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				String weather_place = req.getParameter("weather_place");
				if (weather_place == null || weather_place.trim().length() == 0) {
					errorMsgs.add("地點: 請勿空白");
				}
				// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
				// wth_rain_chance
				String wth_status = req.getParameter("wth_status");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (wth_status == null || wth_status.trim().length() == 0) {
					errorMsgs.add("天氣狀況: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer wth_high = null;
				try {
					wth_high = new Integer(req.getParameter("wth_high").trim());
				} catch (NumberFormatException e) {
					wth_high = 777;
					errorMsgs.add("最高溫請填數字.");
				}

				Integer wth_low = null;
				try {
					wth_low = new Integer(req.getParameter("wth_low").trim());
				} catch (NumberFormatException e) {
					wth_low = -777;
					errorMsgs.add("最低溫請填數字.");
				}

				String wth_comfort = req.getParameter("wth_comfort").trim();
				if (wth_comfort == null || wth_comfort.trim().length() == 0) {
					errorMsgs.add("舒適度請勿空白");
				}

				Integer wth_rain_chance = null;
				try {
					wth_rain_chance = new Integer(req.getParameter("wth_rain_chance").trim());
				} catch (NumberFormatException e) {
					wth_rain_chance = 100;
					errorMsgs.add("降雨機率請填數字.");
				}

				Weather_detailVO weather_detailVO = new Weather_detailVO();
				// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
				// wth_rain_chance
				weather_detailVO.setWeather_time(weather_time);
				weather_detailVO.setWeather_place(weather_place);
				weather_detailVO.setWth_status(wth_status);
				weather_detailVO.setWth_high(wth_high);
				weather_detailVO.setWth_low(wth_low);
				weather_detailVO.setWth_comfort(wth_comfort);
				weather_detailVO.setWth_rain_chance(wth_rain_chance);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("weather_detailVO", weather_detailVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/weather_detail/update_weather_detail_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				Weather_detailService weather_detailSvc = new Weather_detailService();
				weather_detailVO = weather_detailSvc.addWeather_detail(weather_time, weather_place, wth_status,
						wth_high, wth_low, wth_comfort, wth_rain_chance);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/front_end/weather_detail/listAllWeather_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/weather_detail/addWeather_detail.jsp");
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
				Timestamp weather_time = Timestamp.valueOf(req.getParameter("weather_time").trim());
				String weather_place = req.getParameter("weather_place");
				/*************************** 2.開始刪除資料 ***************************************/
				Weather_detailService weather_detailSvc = new Weather_detailService();
				weather_detailSvc.deleteWeather_detail(weather_time, weather_place);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/weather_detail/listAllWeather_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/weather_detail/listAllWeather_detail.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOneAllUWish".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			// weather_time, weather_place, wth_status, wth_high, wth_low, wth_comfort,
			// wth_rain_chance
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("weather_time");
				String str2 = req.getParameter("weather_place");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/weather_detail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Timestamp weather_time = java.sql.Timestamp.valueOf(str.trim());
				String weather_place = new String(str2);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/weather_detail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Weather_detailService weather_detailSvc = new Weather_detailService();
				List<Weather_detailVO> weather_detailVO =weather_detailSvc.getByWeather_place(weather_place);
				if (weather_detailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/weather_detail/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("weather_detailVO", weather_detailVO); // 資料庫取出的VO物件,存入req
				String url = "/front_end/weather_detail/listAllUWish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/weather_detail/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
