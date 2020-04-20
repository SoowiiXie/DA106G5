package com.group_detail.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.group_detail.model.*;

public class Group_detailServlet extends HttpServlet {

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
				String str = req.getParameter("mb_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String mb_id = null;
				try {
					 mb_id = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Grp_detailService grpdetailSvc = new Grp_detailService();
				Grp_detailVO grp_detailVO = grpdetailSvc.getOneGrp_detail(mb_id);
				if (grp_detailVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_detail/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("grp_detailVO", grp_detailVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/group_detail/listOneGroupdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_detail/select_page.jsp");
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
				String mb_id = new String(req.getParameter("mb_id"));
				
				/***************************2.開始查詢資料****************************************/
				Grp_detailService grpdetailSvc = new Grp_detailService();
				Grp_detailVO grp_detailVO = grpdetailSvc.getOneGrp_detail(mb_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("grp_detailVO", grp_detailVO);         // 資料庫取出的grp_detailVO物件,存入req
				String url = "/front_end/group_detail/update_groupdetail_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_grouper_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_detail/listAllGroupdetail.jsp");
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
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}
				
				String grp_no = req.getParameter("grp_no").trim();
				if (grp_no == null || grp_no.trim().length() == 0) {
					errorMsgs.add("請輸入揪團編號");
				}

				Integer grp_register = new Integer(req.getParameter("grp_register").trim());
				
				Grp_detailVO grp_detailVO = new Grp_detailVO();
				grp_detailVO.setMb_id(mb_id);
				grp_detailVO.setGrp_no(grp_no);				
				grp_detailVO.setGrp_register(grp_register);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("grp_detailVO", grp_detailVO); // 含有輸入格式錯誤的grp_detailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group/update_groupdetail_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Grp_detailService grpdetailSvc = new Grp_detailService();
				grp_detailVO = grpdetailSvc.updateGrp_detail(mb_id, grp_no, grp_register);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("grp_detailVO", grp_detailVO); // 資料庫update成功後,正確的的grp_detailVO物件,存入req
				String url = "/front_end/group_detail/listAllGroupdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_detail/update_groupdetail_input.jsp");
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
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}
				
				String grp_no = req.getParameter("grp_no");
				if (grp_no == null || grp_no.trim().length() == 0) {
					errorMsgs.add("揪團編號:請勿空白");
	            }
				
				Integer grp_register = new Integer(req.getParameter("grp_register").trim());
				
				Grp_detailVO grp_detailVO = new Grp_detailVO();
				grp_detailVO.setMb_id(mb_id);
				grp_detailVO.setGrp_no(grp_no);
				grp_detailVO.setGrp_register(grp_register);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("grp_detailVO", grp_detailVO); // 含有輸入格式錯誤的grp_detailVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_detail/addGroupRpt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料***************************************/
				Grp_detailService grpdetailSvc = new Grp_detailService();
				grp_detailVO = grpdetailSvc.addGrp_detail(mb_id, grp_no, grp_register);
				/***************************3.修改完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/group_detail/listAllGroupdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_detail/addGroupdetail.jsp");
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
				String mb_id = new String(req.getParameter("mb_id"));
				
				/***************************2.開始刪除資料***************************************/
				Grp_detailService grp_detailSvc = new Grp_detailService();
				grp_detailSvc.deleteGrp_detail(mb_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/group_detail/listAllGroupdetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_detail/listAllGroupdetail.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getGroupCount".equals(action)) { // 來自listAllGroup.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String grp_no = new String(req.getParameter("grp_no"));
				
				/***************************2.開始處理資料***************************************/
				Grp_detailService grp_detailSvc = new Grp_detailService();
				int peoplecount = grp_detailSvc.getTotalPeople(grp_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)***********/								
				req.setAttribute("peoplecount", peoplecount);
				String url = "/front_end/group_detail/countpeople.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("查詢不到該資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_detail/countpeople.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getPeopleCount".equals(action)) { // 來自listAllGroup.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mb_id = new String(req.getParameter("mb_id"));
				
				/***************************2.開始處理資料***************************************/
				Grp_detailService grp_detailSvc = new Grp_detailService();
				int groupcount = grp_detailSvc.getTotalGroup(mb_id);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)***********/								
				req.setAttribute("groupcount", groupcount);
				String url = "/front_end/group_detail/countgroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("查詢不到該資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_detail/countgroup.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
