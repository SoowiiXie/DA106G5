package com.group_follow.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.group_follow.model.*;

public class Group_followServlet extends HttpServlet {

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
				String str = req.getParameter("grp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String grp_no = null;
				try {
					grp_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("揪團格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Group_followService group_followSvc = new Group_followService();
				Group_followVO group_followVO = group_followSvc.getOneGroupfollow(grp_no);
				if (group_followVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_follow/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("group_followVO", group_followVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/group_follow/listOneGroupfollow.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_follow/select_page.jsp");
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
				String grp_no = new String(req.getParameter("grp_no"));
				
				/***************************2.開始查詢資料****************************************/
				Group_followService group_followSvc = new Group_followService();
				Group_followVO group_followVO = group_followSvc.getOneGroupfollow(grp_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("group_followVO", group_followVO);         // 資料庫取出的grouperVO物件,存入req
				String url = "/front_end/group_follow/update_groupfollow_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_grouper_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_follow/listAllGroupfollow.jsp");
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
				String grp_no = new String(req.getParameter("grp_no").trim());
				
				
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}
				
				Group_followVO group_followVO = new Group_followVO();
				group_followVO.setGrp_no(grp_no);
				group_followVO.setMb_id(mb_id);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_followVO", group_followVO); // 含有輸入格式錯誤的grouperVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_follow/update_group_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Group_followService group_followSvc = new Group_followService();
				group_followVO = group_followSvc.updateGroupfollow(grp_no, mb_id);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("group_followVO", group_followVO); // 資料庫update成功後,正確的的grouperVO物件,存入req
				String url = "/front_end/group_follow/listAllGroupfollow.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_follow/update_groupfollow_input.jsp");
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
				String grp_no = new String(req.getParameter("grp_no").trim());
				

				if (grp_no == null || grp_no.trim().length() == 0) {
					errorMsgs.add("揪團編號:請勿空白");
	            }
				
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}
				
				

				Group_followVO group_followVO = new Group_followVO();
				group_followVO.setGrp_no(grp_no);
				group_followVO.setMb_id(mb_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_followVO", group_followVO); // 含有輸入格式錯誤的grouperVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_follow/addGroupfollow.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料***************************************/
				Group_followService group_followSvc = new Group_followService();
				group_followVO = group_followSvc.addGroupfollow(grp_no, mb_id);
				
				/***************************3.修改完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/group_follow/listAllGroupfollow.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_follow/addGroupfollow.jsp");
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
				String grp_no = new String(req.getParameter("grp_no"));
				
				/***************************2.開始刪除資料***************************************/
				Group_followService group_followSvc = new Group_followService();
				group_followSvc.deleteGroupfollow(grp_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/group_follow/listAllGroupfollow.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_follow/listAllGroupfollow.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
