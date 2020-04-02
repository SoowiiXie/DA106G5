package com.grouper.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.grouper.model.*;

public class GrouperServlet extends HttpServlet {

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
				String str = req.getParameter("Grp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String Grp_no = null;
				try {
					Grp_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("揪團格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				GrouperService grpSvc = new GrouperService();
				GrouperVO grouperVO = grpSvc.getOneGrouper("Grp_no");
				if (grouperVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("grouperVO", grouperVO); // 資料庫取出的empVO物件,存入req
				String url = "/group/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group/select_page.jsp");
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
				String Grp_no = new String(req.getParameter("Grp_no"));
				
				/***************************2.開始查詢資料****************************************/
				GrouperService grpSvc = new GrouperService();
				GrouperVO grouperVO = grpSvc.getOneGrouper("Grp_no");
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("grouperVO", grouperVO);         // 資料庫取出的grouperVO物件,存入req
				String url = "/group/update_group_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_grouper_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group/listAllGroup.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_group_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String Grp_no = new String(req.getParameter("Grp_no").trim());
				
//				String grp_no = req.getParameter("Grp_no");
				String enameReg = "^grp$";
				if (Grp_no == null || Grp_no.trim().length() == 0) {
					errorMsgs.add("揪團編號:請勿空白");
				} else if(!Grp_no.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("揪團編號: 請輸入正確格式");
	            }
				
				String Mb_id = req.getParameter("Mb_id").trim();
				if (Mb_id == null || Mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}
				
				String Loc_no = req.getParameter("Loc_no").trim();
				if (Loc_no == null || Loc_no.trim().length() == 0) {
					errorMsgs.add("請輸入地標名稱");
				}
				
				java.sql.Date Grp_applystart = null;
				try {
					Grp_applystart = java.sql.Date.valueOf(req.getParameter("Grp_applystart").trim());
				} catch (IllegalArgumentException e) {
					Grp_applystart=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入揪團開始時間!");
				}
				
				java.sql.Date Grp_applyend = null;
				try {
					Grp_applyend = java.sql.Date.valueOf(req.getParameter("Grp_applyend").trim());
				} catch (IllegalArgumentException e) {
					Grp_applyend=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入揪團結束時間!");
				}
				
				java.sql.Date Grp_start = null;
				try {
					Grp_start = java.sql.Date.valueOf(req.getParameter("Grp_start").trim());
				} catch (IllegalArgumentException e) {
					Grp_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名開始時間!");
				}
				
				java.sql.Date Grp_end = null;
				try {
					Grp_end = java.sql.Date.valueOf(req.getParameter("Grp_end").trim());
				} catch (IllegalArgumentException e) {
					Grp_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名結束時間!");
				}
				
				String Grp_name = req.getParameter("Grp_name").trim();
				if (Grp_name == null || Grp_name.trim().length() == 0) {
					errorMsgs.add("請輸入揪團名稱");
				}

				String Grp_content = req.getParameter("Grp_content").trim();
				if (Grp_content== null || Loc_no.trim().length() == 0) {
					errorMsgs.add("請輸入揪團狀態");
				}

				Integer Grp_personmax = new Integer(req.getParameter("Grp_personmax").trim());
				
				Integer Grp_personmin = new Integer(req.getParameter("Grp_personmin").trim());
				
				Integer Grp_personcount = new Integer(req.getParameter("Grp_personcount").trim());
				
				Integer Grp_status = new Integer(req.getParameter("Grp_status").trim());
				
				Integer Grp_follow = new Integer(req.getParameter("Grp_follow").trim());

				GrouperVO grouperVO = new GrouperVO();
				grouperVO.setGrp_no(Grp_no);
				grouperVO.setMb_id(Mb_id);
				grouperVO.setLoc_no(Loc_no);
				grouperVO.setGrp_applystart(Grp_applystart);
				grouperVO.setGrp_applyend(Grp_applyend);
				grouperVO.setGrp_start(Grp_start);
				grouperVO.setGrp_end(Grp_end);
				grouperVO.setGrp_name(Grp_name);
				grouperVO.setGrp_content(Grp_name);
				grouperVO.setGrp_personmax(Grp_personmax);
				grouperVO.setGrp_personmin(Grp_personmin);
				grouperVO.setGrp_personcount(Grp_personcount);
				grouperVO.setGrp_status(Grp_status);
				grouperVO.setGrp_follow(Grp_follow);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("grouperVO", grouperVO); // 含有輸入格式錯誤的grouperVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/group/update_group_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				GrouperService empSvc = new GrouperService();
				grouperVO = empSvc.updateGrouper(Grp_no, Mb_id, Loc_no, Grp_applystart, Grp_applyend,Grp_start, 
						Grp_end, Grp_name, Grp_name, Grp_personmax, Grp_personmin, Grp_personcount, Grp_status, 
						Grp_follow);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("grouperVO", grouperVO); // 資料庫update成功後,正確的的grouperVO物件,存入req
				String url = "/emp/listOneGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/update_grouper_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addGroup.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String Grp_no = new String(req.getParameter("Grp_no").trim());
				
//				String grp_no = req.getParameter("Grp_no");
				String enameReg = "^grp$";
				if (Grp_no == null || Grp_no.trim().length() == 0) {
					errorMsgs.add("揪團編號:請勿空白");
				} else if(!Grp_no.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("揪團編號: 請輸入正確格式");
	            }
				
				String Mb_id = req.getParameter("Mb_id").trim();
				if (Mb_id == null || Mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}
				
				String Loc_no = req.getParameter("Loc_no").trim();
				if (Loc_no == null || Loc_no.trim().length() == 0) {
					errorMsgs.add("請輸入地標名稱");
				}
				
				java.sql.Date Grp_applystart = null;
				try {
					Grp_applystart = java.sql.Date.valueOf(req.getParameter("Grp_applystart").trim());
				} catch (IllegalArgumentException e) {
					Grp_applystart=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入揪團開始時間!");
				}
				
				java.sql.Date Grp_applyend = null;
				try {
					Grp_applyend = java.sql.Date.valueOf(req.getParameter("Grp_applyend").trim());
				} catch (IllegalArgumentException e) {
					Grp_applyend=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入揪團結束時間!");
				}
				
				java.sql.Date Grp_start = null;
				try {
					Grp_start = java.sql.Date.valueOf(req.getParameter("Grp_start").trim());
				} catch (IllegalArgumentException e) {
					Grp_start=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名開始時間!");
				}
				
				java.sql.Date Grp_end = null;
				try {
					Grp_end = java.sql.Date.valueOf(req.getParameter("Grp_end").trim());
				} catch (IllegalArgumentException e) {
					Grp_end=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入報名結束時間!");
				}
				
				String Grp_name = req.getParameter("Grp_name").trim();
				if (Grp_name == null || Grp_name.trim().length() == 0) {
					errorMsgs.add("請輸入揪團名稱");
				}

				String Grp_content = req.getParameter("Grp_content").trim();
				if (Grp_content== null || Loc_no.trim().length() == 0) {
					errorMsgs.add("請輸入揪團狀態");
				}

				Integer Grp_personmax = new Integer(req.getParameter("Grp_personmax").trim());
				
				Integer Grp_personmin = new Integer(req.getParameter("Grp_personmin").trim());
				
				Integer Grp_personcount = new Integer(req.getParameter("Grp_personcount").trim());
				
				Integer Grp_status = new Integer(req.getParameter("Grp_status").trim());
				
				Integer Grp_follow = new Integer(req.getParameter("Grp_follow").trim());

				GrouperVO grouperVO = new GrouperVO();
				grouperVO.setGrp_no(Grp_no);
				grouperVO.setMb_id(Mb_id);
				grouperVO.setLoc_no(Loc_no);
				grouperVO.setGrp_applystart(Grp_applystart);
				grouperVO.setGrp_applyend(Grp_applyend);
				grouperVO.setGrp_start(Grp_start);
				grouperVO.setGrp_end(Grp_end);
				grouperVO.setGrp_name(Grp_name);
				grouperVO.setGrp_content(Grp_name);
				grouperVO.setGrp_personmax(Grp_personmax);
				grouperVO.setGrp_personmin(Grp_personmin);
				grouperVO.setGrp_personcount(Grp_personcount);
				grouperVO.setGrp_status(Grp_status);
				grouperVO.setGrp_follow(Grp_follow);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("grouperVO", grouperVO); // 含有輸入格式錯誤的grouperVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addGroup.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料***************************************/
				GrouperService empSvc = new GrouperService();
				grouperVO = empSvc.updateGrouper(Grp_no, Mb_id, Loc_no, Grp_applystart, Grp_applyend,Grp_start, 
						Grp_end, Grp_name, Grp_name, Grp_personmax, Grp_personmin, Grp_personcount, Grp_status, 
						Grp_follow);
				
				/***************************3.修改完成,準備轉交(Send the Success view)***********/
				String url = "/group/listAllGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group/addGroup.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllGroup.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String Grp_no = new String(req.getParameter("Grp_no"));
				
				/***************************2.開始刪除資料***************************************/
				GrouperService empSvc = new GrouperService();
				empSvc.deleteGrouper("Grp_no");
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/group/listAllGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/group/listAllGroup.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
