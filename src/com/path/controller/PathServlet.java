package com.path.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.path.model.PathService;
import com.path.model.PathVO;
import com.record.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 100 * 1024 * 1024, maxRequestSize = 5 * 5 * 100
		* 1024 * 1024)
public class PathServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			InputStream in;
			BufferedInputStream bf;
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Part part = req.getPart("path_pic");
				byte[] path_pic = null;
				String picBase64 = req.getParameter("picBase64");
				if (part.getSize() != 0) {
					in = part.getInputStream();
					bf = new BufferedInputStream(in);
					path_pic = new byte[in.available()];
					in.read(path_pic);
					bf.close();
					in.close();
				} else if (picBase64 != null && picBase64.trim().length() != 0) {
					path_pic = Base64.getDecoder().decode(picBase64.getBytes());
				}

				java.sql.Date rcd_uploadtime = new java.sql.Date(System.currentTimeMillis());

				String rcd_content = req.getParameter("rcd_content");
				if (rcd_content == null || rcd_content.trim().length() == 0) {
					errorMsgs.add("上傳內容: 請勿空白");
				}

				String mb_id = req.getParameter("mb_id");

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rcd_content", rcd_content); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/record/addRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				PathService pathSvc = new PathService();
				String path_no = pathSvc.insertPathFromWeb(path_pic);

				RecordVO recordVO = new RecordVO();
				recordVO.setRcd_uploadtime(rcd_uploadtime);
				recordVO.setRcd_content(rcd_content);
				recordVO.setPath_no(path_no);
				recordVO.setMb_id(mb_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recordVO", recordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(
							"/front_end/index.jsp?pageRun=personal_page/personal_page.jsp&meOrFollow=me");
					failureView.forward(req, res);
					return;
				}
				RecordService recordSvc = new RecordService();
				recordVO = recordSvc.addRecord(rcd_uploadtime, rcd_content, path_no, mb_id);
				// 為什麼不用256行empVO進行新增,因為可以在service中有必要還可以加工(框架)
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/index.jsp?pageRun=personal_page/personal_page.jsp&meOrFollow=me";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/record/addRecord.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("rcd_no");
//System.out.println(str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入紀錄編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/record/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String rcd_no = null;
				try {
					rcd_no = new String(str);
//System.out.println(rcd_no);
				} catch (Exception e) {
					errorMsgs.add("紀錄編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/record/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RecordService recordSvc = new RecordService();
				RecordVO recordVO = recordSvc.getOneRecord(rcd_no);

				if (recordVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/record/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
//System.out.println(recordVO.getRcd_no());
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("recordVO", recordVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/record/listOneRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/record/select_page.jsp");
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
				String rcd_no = new String(req.getParameter("rcd_no"));
//System.out.println("getOne_For_Update");
				/*************************** 2.開始查詢資料 ****************************************/
				RecordService recordSvc = new RecordService();
				RecordVO recordVO = recordSvc.getOneRecord(rcd_no);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("recordVO", recordVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/record/update_record_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/record/listAllRecord.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String rcd_no = new String(req.getParameter("rcd_no").trim());
//System.out.println(rcd_no);

				java.sql.Date rcd_uploadtime = null;
				try {
					rcd_uploadtime = java.sql.Date.valueOf(req.getParameter("rcd_uploadtime").trim());
				} catch (IllegalArgumentException e) {
					rcd_uploadtime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String rcd_content = req.getParameter("rcd_content");
				if (rcd_content == null || rcd_content.trim().length() == 0) {
					errorMsgs.add("上傳內容: 請勿空白");
				}
//System.out.println(rcd_content);	
				Integer rcd_status = null;
				try {
					rcd_status = new Integer(req.getParameter("rcd_status").trim());
				} catch (NumberFormatException e) {
					rcd_status = 1;
					errorMsgs.add("狀態請填數字.");
				}
//System.out.println("here");					
				String path_no = req.getParameter("path_no").trim();
				if (path_no == null || path_no.trim().length() == 0) {
					errorMsgs.add("路徑編號請勿空白");
				}

				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("路徑編號請勿空白");
				}

				Integer rcd_thumb_amount = null;
				try {
					rcd_thumb_amount = new Integer(req.getParameter("rcd_thumb_amount").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("狀態請填數字.");
				}
				Integer rcd_metoo_amount = null;
				try {
					rcd_metoo_amount = new Integer(req.getParameter("rcd_metoo_amount").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("狀態請填數字.");
				}
//System.out.println(rcd_metoo_amount);
				RecordVO recordVO = new RecordVO();
				recordVO.setRcd_no(rcd_no);
				recordVO.setRcd_uploadtime(rcd_uploadtime);
				recordVO.setRcd_content(rcd_content);
				recordVO.setRcd_status(rcd_status);
				recordVO.setPath_no(path_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recordVO", recordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/record/update_record_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				RecordService rcdSvc = new RecordService();
				recordVO = rcdSvc.updateRecord(rcd_no, rcd_uploadtime, rcd_content, rcd_status, path_no);
				recordVO.setMb_id(mb_id);
				recordVO.setRcd_metoo_amount(rcd_metoo_amount);
				recordVO.setRcd_thumb_amount(rcd_thumb_amount);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("recordVO", recordVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front_end/record/listOneRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/record/update_record_input.jsp");
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
				String rcd_no = new String(req.getParameter("rcd_no"));
//System.out.println(rcd_no);
				/*************************** 2.開始刪除資料 ***************************************/
				RecordService rcdSvc = new RecordService();
				rcdSvc.deleteRecord(rcd_no);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/record/listAllRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/record/listAllRecord.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
