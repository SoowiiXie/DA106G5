package com.grouper.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.group_detail.model.*;
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
				String str = req.getParameter("grp_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group/select_page.jsp");
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
							.getRequestDispatcher("/front_end/group/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				GrouperService grpSvc = new GrouperService();
				GrouperVO grouperVO = grpSvc.getOneGroup(grp_no);
				if (grouperVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("grouperVO", grouperVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/group/listOneGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group/select_page.jsp");
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
				GrouperService grpSvc = new GrouperService();
				GrouperVO grouperVO = grpSvc.getOneGroup(grp_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("grouperVO", grouperVO);         // 資料庫取出的grouperVO物件,存入req
				String url = "/front_end/group/update_group_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_grouper_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group/listAllGroup.jsp");
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
//				String grp_no = new String(req.getParameter("grp_no").trim());
				
				String grp_no = req.getParameter("grp_no");
				//test
				System.out.println(grp_no);
//				String enameReg = "^grp$";
//				if (grp_no == null || grp_no.trim().length() == 0) {
//					errorMsgs.add("揪團編號:請勿空白");
//				} else if(!grp_no.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
//					errorMsgs.add("揪團編號: 請輸入正確格式");
//	            }
				
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}
				//test				
				System.out.println("我有到這邊1");
				System.out.println(mb_id);
				System.out.println(mb_id);
				System.out.println(mb_id);
								
				
				String loc_no = req.getParameter("loc_no").trim();
				if (loc_no == null || loc_no.trim().length() == 0) {
					errorMsgs.add("請輸入地標名稱");
				}
				//test
				System.out.println("我有到這邊2");
				System.out.println("loc_no");
				java.sql.Timestamp grp_applystart = null;
				try {
					grp_applystart = java.sql.Timestamp.valueOf(req.getParameter("grp_applystart").trim());
				} catch (IllegalArgumentException e) {
					grp_applystart=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入揪團開始時間!");
				}
				
				java.sql.Timestamp grp_applyend = null;
				try {
					grp_applyend = java.sql.Timestamp.valueOf(req.getParameter("grp_applyend").trim());
				} catch (IllegalArgumentException e) {
					grp_applyend=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入揪團結束時間!");
				}
				
				java.sql.Timestamp grp_start = null;
				try {					
					grp_start = java.sql.Timestamp.valueOf(req.getParameter("grp_start").trim());
				} catch (IllegalArgumentException e) {
					grp_start=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入報名開始時間!");
				}
				
				java.sql.Timestamp grp_end = null;
				try {
					grp_end = java.sql.Timestamp.valueOf(req.getParameter("grp_end").trim());
				} catch (IllegalArgumentException e) {
					grp_end=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入報名結束時間!");
				}
				
				String grp_name = req.getParameter("grp_name").trim();
				if (grp_name == null || grp_name.trim().length() == 0) {
					errorMsgs.add("請輸入揪團名稱");
				}

				String grp_content = req.getParameter("grp_content").trim();
				if (grp_content== null || grp_content.trim().length() == 0) {
					errorMsgs.add("請輸入揪團狀態");
				}

				Integer grp_personmax = new Integer(req.getParameter("grp_personmax").trim());
				
				Integer grp_personmin = new Integer(req.getParameter("grp_personmin").trim());
				
				Integer grp_personcount = new Integer(req.getParameter("grp_personcount").trim());
				
				Integer grp_status = new Integer(req.getParameter("grp_status").trim());
				
				Integer grp_follow = new Integer(req.getParameter("grp_follow").trim());

				GrouperVO grouperVO = new GrouperVO();
				grouperVO.setGrp_no(grp_no);
				grouperVO.setMb_id(mb_id);
				grouperVO.setLoc_no(loc_no);
				grouperVO.setGrp_applystart(grp_applystart);
				grouperVO.setGrp_applyend(grp_applyend);
				grouperVO.setGrp_start(grp_start);
				grouperVO.setGrp_end(grp_end);
				grouperVO.setGrp_name(grp_name);
				grouperVO.setGrp_content(grp_content);
				grouperVO.setGrp_personmax(grp_personmax);
				grouperVO.setGrp_personmin(grp_personmin);
				grouperVO.setGrp_personcount(grp_personcount);
				grouperVO.setGrp_status(grp_status);
				grouperVO.setGrp_follow(grp_follow);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("grouperVO", grouperVO); // 含有輸入格式錯誤的grouperVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/index.jsp?pageRun=group/update_group_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				GrouperService grpSvc = new GrouperService();
				grouperVO = grpSvc.updateGroup(grp_no, mb_id, loc_no, grp_applystart, grp_applyend,grp_start, 
						grp_end, grp_name, grp_content, grp_personmax, grp_personmin, grp_personcount, grp_status, 
						grp_follow);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("grouperVO", grouperVO); // 資料庫update成功後,正確的的grouperVO物件,存入req
				String url = "/front_end/index.jsp?pageRun=group/listAllGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/index.jsp?pageRun=group/update_group_input.jsp");
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
				
//				String grp_no = req.getParameter("grp_no");
//				String enameReg = "^grp$";
				if (grp_no == null || grp_no.trim().length() == 0) {
					errorMsgs.add("揪團編號:請勿空白");
//				} else if(!grp_no.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("揪團編號: 請輸入正確格式");
	            }
				String mb_id = new String(req.getParameter("mb_id").trim());

				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}
				
				String loc_no = req.getParameter("loc_no").trim();
				if (loc_no == null || loc_no.trim().length() == 0) {
					errorMsgs.add("請輸入地標名稱");
				}
				
				java.sql.Timestamp grp_applystart = null;
				try {
					grp_applystart = java.sql.Timestamp.valueOf(req.getParameter("grp_applystart").trim());
				} catch (IllegalArgumentException e) {
					grp_applystart=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入揪團開始時間!");
				}
				
				java.sql.Timestamp grp_applyend = null;
				try {
					grp_applyend = java.sql.Timestamp.valueOf(req.getParameter("grp_applyend").trim());
				} catch (IllegalArgumentException e) {
					grp_applyend=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入揪團結束時間!");
				}
				
				java.sql.Timestamp grp_start = null;
				try {
					grp_start = java.sql.Timestamp.valueOf(req.getParameter("grp_start").trim());
				} catch (IllegalArgumentException e) {
					grp_start=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入報名開始時間!");
				}
				
				java.sql.Timestamp grp_end = null;
				try {
					grp_end = java.sql.Timestamp.valueOf(req.getParameter("grp_end").trim());
				} catch (IllegalArgumentException e) {
					grp_end=new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入報名結束時間!");
				}
				
				String grp_name = req.getParameter("grp_name").trim();
				if (grp_name == null || grp_name.trim().length() == 0) {
					errorMsgs.add("請輸入揪團名稱");
				}

				String grp_content = req.getParameter("grp_content").trim();
				if (grp_content== null || grp_content.trim().length() == 0) {
					errorMsgs.add("請輸入揪團狀態");
				}

				Integer grp_personmax = new Integer(req.getParameter("grp_personmax").trim());
				
				Integer grp_personmin = new Integer(req.getParameter("grp_personmin").trim());
				
				Integer grp_personcount = new Integer(req.getParameter("grp_personcount").trim());
				
				Integer grp_status = new Integer(req.getParameter("grp_status").trim());
				
				Integer grp_follow = new Integer(req.getParameter("grp_follow").trim());

				GrouperVO grouperVO = new GrouperVO();
				grouperVO.setGrp_no(grp_no);
				grouperVO.setMb_id(mb_id);
				grouperVO.setLoc_no(loc_no);
				grouperVO.setGrp_applystart(grp_applystart);
				grouperVO.setGrp_applyend(grp_applyend);
				grouperVO.setGrp_start(grp_start);
				grouperVO.setGrp_end(grp_end);
				grouperVO.setGrp_name(grp_name);
				grouperVO.setGrp_content(grp_content);
				grouperVO.setGrp_personmax(grp_personmax);
				grouperVO.setGrp_personmin(grp_personmin);
				grouperVO.setGrp_personcount(grp_personcount);
				grouperVO.setGrp_status(grp_status);
				grouperVO.setGrp_follow(grp_follow);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("grouperVO", grouperVO); // 含有輸入格式錯誤的grouperVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group/addGroup.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料***************************************/
				GrouperService empSvc = new GrouperService();
				grouperVO = empSvc.addGroup(grp_no, mb_id, loc_no, grp_applystart, grp_applyend,grp_start, 
						grp_end, grp_name, grp_content, grp_personmax, grp_personmin, grp_personcount, grp_status, 
						grp_follow);
				
				/***************************3.修改完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/index.jsp?pageRun=group/listAllGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/index.jsp?pageRun=group/addGroup.jsp");
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
				GrouperService grpSvc = new GrouperService();
				grpSvc.deleteGroup(grp_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/group/listAllGroup.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group/listAllGroup.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				String grp_no = new String(req.getParameter("grp_no"));
				
				/***************************2.開始查詢資料****************************************/
				GrouperService grpSvc = new GrouperService();
				GrouperVO grouperVO = grpSvc.getOneGroup(grp_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("grouperVO", grouperVO);         // 資料庫取出的grouperVO物件,存入req
				String url = "/front_end/index.jsp?pageRun=group/update_group_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_grouper_input.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/front_end/group/listAllGroup.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		
		if ("listGrouper_ByCompositeQuery".equals(action)) { // 來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			try {
				/***************************1.接收請求參數****************************************/
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();				
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				if (req.getParameter("whichPage") == null){
					HashMap<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始查詢資料****************************************/
				GrouperService grpSvc = new GrouperService();
				List<GrouperVO> list = grpSvc.getAll(map);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listGrouper_ByCompositeQuery", list);         // 資料庫取出的grouperVO物件,存入req
				String url = "/front_end/index.jsp?pageRun=group/listGrouper_ByCompositeQuery.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_grouper_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/select_page.jsp");
//				failureView.forward(req, res);
//			}
		}
		
		if ("getOne_Time".equals(action)) {

			try {
				// Retrieve form parameters.
				String grp_no = new String(req.getParameter("grp_no"));
				System.out.println(grp_no);
				GrouperDAO dao = new GrouperDAO();
				GrouperVO grouperVO = dao.findByPrimaryKey(grp_no);

				req.setAttribute("grouperVO", grouperVO); // 資料庫取出的empVO物件,存入req
				System.out.println("C:"+grouperVO);
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front_end/index.jsp?pageRun=group/listAllGroup.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		if ("getOne_Time2".equals(action)) {

			try {
				// Retrieve form parameters.
				String grp_no = new String(req.getParameter("grp_no"));
				System.out.println(grp_no);
				GrouperDAO dao = new GrouperDAO();
				GrouperVO grouperVO = dao.findByPrimaryKey(grp_no);

				req.setAttribute("grouperVO", grouperVO); // 資料庫取出的empVO物件,存入req
				System.out.println("C:"+grouperVO);
				
				//Bootstrap_modal
				boolean openModal=true;
				req.setAttribute("openModal",openModal );
				
				// 取出的empVO送給listOneEmp.jsp
				RequestDispatcher successView = req
						.getRequestDispatcher("/front_end/index.jsp?pageRun=group/listAllGroup.jsp");
				successView.forward(req, res);
				return;

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
