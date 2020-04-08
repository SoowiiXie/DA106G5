package com.record.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.record.model.*;


public class RecordServlet extends HttpServlet{

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
				String str = req.getParameter("rcd_no");
//System.out.println(str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入紀錄編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/record/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
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
					RequestDispatcher failureView = req
							.getRequestDispatcher("/record/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				RecordService recordSvc = new RecordService();
				RecordVO recordVO = recordSvc.getOneRecord(rcd_no);
				
				if (recordVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/record/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
//System.out.println(recordVO.getRcd_no());
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recordVO", recordVO); // 資料庫取出的empVO物件,存入req
				String url = "/record/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/record/select_page.jsp");
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
				String rcd_no = new String(req.getParameter("rcd_no"));
//System.out.println(rcd_no);
				/***************************2.開始查詢資料****************************************/
				RecordService recordSvc = new RecordService();
				RecordVO recordVO = recordSvc.getOneRecord(rcd_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("recordVO", recordVO);         // 資料庫取出的empVO物件,存入req
				String url = "/record/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/record/listAllEmp.jsp");
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
				String rcd_no = new String(req.getParameter("rcd_no").trim());
				
				java.sql.Date rcd_uploadtime = null;
				try {
					rcd_uploadtime = java.sql.Date.valueOf(req.getParameter("rcd_uploadtime").trim());
				} catch (IllegalArgumentException e) {
					rcd_uploadtime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String rcd_content = req.getParameter("rcd_content");
				if (rcd_content == null || rcd_content.trim().length() == 0) {
					errorMsgs.add("上傳內容: 請勿空白");
				}
				
				Integer rcd_status = null;
				try {
					rcd_status = new Integer(req.getParameter("rcd_status").trim());
				} catch (NumberFormatException e) {
					rcd_status = 1;
					errorMsgs.add("狀態請填數字.");
				}
				
				String path_no = req.getParameter("path_no").trim();
				if (path_no == null || path_no.trim().length() == 0) {
					errorMsgs.add("路徑編號請勿空白");
				}	

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
							.getRequestDispatcher("/record/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				RecordService rcdSvc = new RecordService();
				recordVO = rcdSvc.updateRecord(rcd_no, rcd_uploadtime, rcd_content, rcd_status, path_no);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("recordVO", recordVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/record/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/record/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				java.sql.Date rcd_uploadtime = null;
				try {
					rcd_uploadtime = java.sql.Date.valueOf(req.getParameter("rcd_uploadtime").trim());
				} catch (IllegalArgumentException e) {
					rcd_uploadtime=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				String rcd_content = req.getParameter("rcd_content");
				if (rcd_content == null || rcd_content.trim().length() == 0) {//null:方便除錯用(可不加)
					errorMsgs.add("上傳內容: 請勿空白");
				} 
//System.out.println(rcd_content);
				String path_no = req.getParameter("path_no");
				String mb_id = req.getParameter("mb_id");
//System.out.println(path_no);
//System.out.println(mb_id);
//				String path_no = new String(req.getParameter("path_no").trim());
//				String mb_id = new String(req.getParameter("mb_id")).trim();

				RecordVO recordVO = new RecordVO();
				recordVO.setRcd_uploadtime(rcd_uploadtime);
				recordVO.setRcd_content(rcd_content);
				recordVO.setPath_no(path_no);
				recordVO.setMb_id(mb_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("recordVO", recordVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/record/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				RecordService recordSvc = new RecordService();
				recordVO = recordSvc.addRecord(rcd_uploadtime, rcd_content, path_no, mb_id);
				//為什麼不用256行empVO進行新增,因為可以在service中有必要還可以加工(框架)
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/record/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/record/addEmp.jsp");
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
				String rcd_no = new String(req.getParameter("rcd_no"));
//System.out.println(rcd_no);
				/***************************2.開始刪除資料***************************************/
				RecordService rcdSvc = new RecordService();
				rcdSvc.deleteRecord(rcd_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/record/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/record/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
