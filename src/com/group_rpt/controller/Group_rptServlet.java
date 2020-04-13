<<<<<<< HEAD
package com.group_rpt.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.group_rpt.model.*;

public class Group_rptServlet extends HttpServlet {

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
				String str = req.getParameter("group_rpt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團檢舉編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String group_rpt_no = null;
				try {
					group_rpt_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Group_rptService group_rptSvc = new Group_rptService();
				Group_rptVO group_rptVO = group_rptSvc.getOneGroup_rpt(group_rpt_no);
				if (group_rptVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("group_rptVO", group_rptVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/group_rpt/listOneGroupRpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_rpt/select_page.jsp");
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
				String group_rpt_no = new String(req.getParameter("group_rpt_no"));
				
				/***************************2.開始查詢資料****************************************/
				Group_rptService group_rptSvc = new Group_rptService();
				Group_rptVO group_rptVO = group_rptSvc.getOneGroup_rpt(group_rpt_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("group_rptVO", group_rptVO);         // 資料庫取出的grouperVO物件,存入req
				String url = "/front_end/group_rpt/update_group_rpt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_grouper_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_rpt/listAllGroupRpt.jsp");
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

				String group_rpt_no = req.getParameter("group_rpt_no");
				
				String grp_no = req.getParameter("grp_no").trim();
				if (grp_no == null || grp_no.trim().length() == 0) {
					errorMsgs.add("請輸入揪團名稱");
				}
				
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				
				String rpt_reason = req.getParameter("rpt_reason").trim();
				if (rpt_reason == null || rpt_reason.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉理由");
				}

				Integer rpt_status = new Integer(req.getParameter("rpt_status").trim());
				
				

				Group_rptVO group_rptVO = new Group_rptVO();
				
				group_rptVO.setGrp_no(grp_no);
				group_rptVO.setMb_id(mb_id);
				group_rptVO.setRpt_reason(rpt_reason);
				group_rptVO.setRpt_status(rpt_status);
				group_rptVO.setGroup_rpt_no(group_rpt_no);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_rptVO", group_rptVO); // 含有輸入格式錯誤的grouperVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_rpt/update_group_rpt_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Group_rptService group_rptSvc = new Group_rptService();
				group_rptVO = group_rptSvc.updateGroup_rpt(group_rpt_no, grp_no, mb_id, rpt_reason, rpt_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("group_rptVO", group_rptVO); // 資料庫update成功後,正確的的grouperVO物件,存入req
				String url = "/front_end/group_rpt/listOneGroupRpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_rpt/update_group_rpt_input.jsp");
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
				
				String group_rpt_no = req.getParameter("group_rpt_no").trim();
				if (group_rpt_no == null || group_rpt_no.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				
				String grp_no = new String(req.getParameter("grp_no").trim());				
				if (grp_no == null || grp_no.trim().length() == 0) {
					errorMsgs.add("揪團編號:請勿空白");
	            }
				
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}
				
				String rpt_reason = req.getParameter("rpt_reason").trim();
				if (rpt_reason == null || rpt_reason.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉理由");
				}

				Integer rpt_status = new Integer(req.getParameter("rpt_status").trim());
				

				Group_rptVO group_rptVO = new Group_rptVO();
				group_rptVO.setGroup_rpt_no(group_rpt_no);
				group_rptVO.setGrp_no(grp_no);
				group_rptVO.setMb_id(mb_id);				
				group_rptVO.setRpt_reason(rpt_reason);
				group_rptVO.setRpt_status(rpt_status);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_rptVO", group_rptVO); // 含有輸入格式錯誤的grouperVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_rpt/addGroupRpt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料***************************************/
				Group_rptService group_rptSvc = new Group_rptService();
				group_rptVO = group_rptSvc.addGroup_rpt(group_rpt_no, grp_no, mb_id, rpt_reason, rpt_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/group_rpt/listAllGroupRpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_rpt/addGroupRpt.jsp");
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
				String group_rpt_no = new String(req.getParameter("group_rpt_no"));
				
				/***************************2.開始刪除資料***************************************/
				Group_rptService group_rptSvc = new Group_rptService();
				group_rptSvc.deleteGroup_rpt(group_rpt_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/group_rpt/listAllGroupRpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_rpt/listAllGroupRpt.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
=======
package com.group_rpt.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.group_rpt.model.*;

public class Group_rptServlet extends HttpServlet {

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
				String str = req.getParameter("group_rpt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入揪團檢舉編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String group_rpt_no = null;
				try {
					group_rpt_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Group_rptService group_rptSvc = new Group_rptService();
				Group_rptVO group_rptVO = group_rptSvc.getOneGroup_rpt(group_rpt_no);
				if (group_rptVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("group_rptVO", group_rptVO); // 資料庫取出的empVO物件,存入req
				String url = "/front_end/group_rpt/listOneGroupRpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_rpt/select_page.jsp");
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
				String group_rpt_no = new String(req.getParameter("group_rpt_no"));
				
				/***************************2.開始查詢資料****************************************/
				Group_rptService group_rptSvc = new Group_rptService();
				Group_rptVO group_rptVO = group_rptSvc.getOneGroup_rpt(group_rpt_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("group_rptVO", group_rptVO);         // 資料庫取出的grouperVO物件,存入req
				String url = "/front_end/group_rpt/update_group_rpt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_grouper_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_rpt/listAllGroupRpt.jsp");
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

				String group_rpt_no = req.getParameter("group_rpt_no");
				
				String grp_no = req.getParameter("grp_no").trim();
				if (grp_no == null || grp_no.trim().length() == 0) {
					errorMsgs.add("請輸入揪團名稱");
				}
				
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				
				String rpt_reason = req.getParameter("rpt_reason").trim();
				if (rpt_reason == null || rpt_reason.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉理由");
				}

				Integer rpt_status = new Integer(req.getParameter("rpt_status").trim());
				
				

				Group_rptVO group_rptVO = new Group_rptVO();
				group_rptVO.setGroup_rpt_no(group_rpt_no);
				group_rptVO.setGrp_no(grp_no);
				group_rptVO.setMb_id(mb_id);
				group_rptVO.setRpt_reason(rpt_reason);
				group_rptVO.setRpt_status(rpt_status);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_rptVO", group_rptVO); // 含有輸入格式錯誤的grouperVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_rpt/update_group_rpt_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Group_rptService group_rptSvc = new Group_rptService();
				group_rptVO = group_rptSvc.updateGroup_rpt(group_rpt_no, grp_no, mb_id, rpt_reason, rpt_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)*************/
				req.setAttribute("group_rptVO", group_rptVO); // 資料庫update成功後,正確的的grouperVO物件,存入req
				String url = "/front_end/group_rpt/listOneGroupRpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_rpt/update_group_rpt_input.jsp");
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
				
				String group_rpt_no = req.getParameter("group_rpt_no").trim();
				if (group_rpt_no == null || group_rpt_no.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉編號");
				}
				
				String grp_no = new String(req.getParameter("grp_no").trim());				
				if (grp_no == null || grp_no.trim().length() == 0) {
					errorMsgs.add("揪團編號:請勿空白");
	            }
				
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("請輸入會員名稱");
				}
				
				String rpt_reason = req.getParameter("rpt_reason").trim();
				if (rpt_reason == null || rpt_reason.trim().length() == 0) {
					errorMsgs.add("請輸入檢舉理由");
				}

				Integer rpt_status = new Integer(req.getParameter("rpt_status").trim());
				

				Group_rptVO group_rptVO = new Group_rptVO();
				group_rptVO.setGroup_rpt_no(group_rpt_no);
				group_rptVO.setGrp_no(grp_no);
				group_rptVO.setMb_id(mb_id);				
				group_rptVO.setRpt_reason(rpt_reason);
				group_rptVO.setRpt_status(rpt_status);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("group_rptVO", group_rptVO); // 含有輸入格式錯誤的grouperVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/group_rpt/addGroupRpt.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始修改資料***************************************/
				Group_rptService group_rptSvc = new Group_rptService();
				group_rptVO = group_rptSvc.addGroup_rpt(group_rpt_no, grp_no, mb_id, rpt_reason, rpt_status);
				
				/***************************3.修改完成,準備轉交(Send the Success view)***********/
				String url = "/front_end/group_rpt/listAllGroupRpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_rpt/addGroupRpt.jsp");
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
				String group_rpt_no = new String(req.getParameter("group_rpt_no"));
				
				/***************************2.開始刪除資料***************************************/
				Group_rptService group_rptSvc = new Group_rptService();
				group_rptSvc.deleteGroup_rpt(group_rpt_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front_end/group_rpt/listAllGroupRpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front_end/group_rpt/listAllGroupRpt.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
>>>>>>> branch 'master' of https://github.com/SoowiiXie/DA106G5.git
