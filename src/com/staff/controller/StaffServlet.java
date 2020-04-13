package com.staff.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.auth.model.AuthorityService;
import com.mb.model.MemberService;
import com.mb.model.MemberVO;
import com.staff.model.StaffService;
import com.staff.model.StaffVO;

public class StaffServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// *** 用MAP取出XXX權限
		// *** 延伸上面，在Switch-case中，比對字串改為MAP的KEY
		
		// ** listAllEmp  更改狀態、權限、下拉式選單篩選在職、離職
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("login".equals(action)) { // 登入   OK
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String staff_id = req.getParameter("staff_id");
				if (staff_id == null || (staff_id.trim()).length() == 0) {
					errorMsgs.add("帳號不得為空白");
				}
				
				String staff_pwd = req.getParameter("staff_pwd");
				if (staff_pwd == null || (staff_pwd.trim()).length() == 0) {
					errorMsgs.add("密碼不得為空白");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StaffService staffSvc = new StaffService();
				StaffVO staffVO = staffSvc.getOneStaff(staff_id);
				if (staffVO == null || !staffVO.getStaff_pwd().equals(staff_pwd)) {
					errorMsgs.add("帳號或密碼有誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				session.setAttribute("staffVO", staffVO); // 資料庫取出的VO物件,存入Session
				String url = "/back_end/staff/select_page.jsp";  // 
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/login.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("select_management".equals(action)) { // 選擇管理項目  OK 一半
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			// 取得管理員
			StaffVO staffVO = (StaffVO) session.getAttribute("staffVO");
			// 取得被點選的管理按鈕
			String management = req.getParameter("management");
			
			// 取得管理員的所有權限
			AuthorityService authoritySvc = new AuthorityService();
			Set<String> staffAuthority = authoritySvc.getOneStaffAuthority(staffVO.getStaff_id());
			
			// 判斷管理員是否有該權限
			if(staffAuthority.contains(management)) {
				
				RequestDispatcher successView;
				// ***只有01完成，其餘需要再修改***
				switch(management) {
				case "01":  // 導至管理員管理頁面
					successView = req.getRequestDispatcher("/back_end/staff/listAllStaff.jsp"); 
					successView.forward(req, res);
					break;
				
				case "02":  // 導至留言管理頁面
					successView = req.getRequestDispatcher("/back_end/staff/listAllStaff.jsp"); 
					successView.forward(req, res);
					break;
				
				case "03":  // 導至檢舉管理頁面
					successView = req.getRequestDispatcher("/back_end/staff/listAllStaff.jsp"); 
					successView.forward(req, res);
					break;
					
				case "04":  // 導至商城管理頁面	
					successView = req.getRequestDispatcher("/back_end/staff/listAllStaff.jsp"); 
					successView.forward(req, res);
					break;
				
				case "05":  // 導至問題回報管理頁面
					successView = req.getRequestDispatcher("/back_end/staff/listAllStaff.jsp"); 
					successView.forward(req, res);
					break;
				}
			}else {
				errorMsgs.add("您尚未擁有該權限");   // 用MAP取出XXX權限
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
		}

		if ("update_self".equals(action)) { // 個人修改  OK
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String staff_id = req.getParameter("staff_id").trim();
				
				String staff_pwd = req.getParameter("staff_pwd").trim();
				if (staff_pwd == null || staff_pwd.length() == 0) {
					errorMsgs.add("密碼不得為空白");
				}
				
				String staff_name = req.getParameter("staff_name").trim();
				if (staff_name == null || staff_name.length() == 0) {
					errorMsgs.add("姓名不得為空白");
				}
				
				Timestamp staff_join = Timestamp.valueOf(req.getParameter("staff_join"));
				Integer staff_status = Integer.parseInt(req.getParameter("staff_status"));
				
				StaffVO staffVO = new StaffVO();
				staffVO.setStaff_id(staff_id);
				staffVO.setStaff_pwd(staff_pwd);
				staffVO.setStaff_name(staff_name);
				staffVO.setStaff_join(staff_join);
				staffVO.setStaff_status(staff_status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back_end/staff/update_staff.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StaffService staffSvc = new StaffService();
				staffVO = staffSvc.updateStaff(staff_id, staff_pwd, staff_name, staff_status);
				// 因為更新不需要更新加入時間，傳回的物件也沒有加入時間，所以要再自己加上加入時間
				staffVO.setStaff_join(staff_join);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				session.setAttribute("staffVO", staffVO);
				String url = "/back_end/staff/select_page.jsp";  // 
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/update_staff.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/***************************查詢資料****************************************/
				StaffService staffSvc = new StaffService();
				StaffVO staffVO = staffSvc.getOneStaff(req.getParameter("staff_id"));
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("staffVO", staffVO);         
				String url = "/back_end/staff/update_staff";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back_end/staff/listAllStaff.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { // 新增
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.length() == 0) {
					errorMsgs.add("帳號不得為空白");
				}
				
				String mb_pwd = req.getParameter("mb_pwd").trim();
				if (mb_pwd == null || mb_pwd.length() == 0) {
					errorMsgs.add("密碼不得為空白");
				}
				
				String mb_name = req.getParameter("mb_name").trim();
				if (mb_name == null || mb_name.length() == 0) {
					errorMsgs.add("姓名不得為空白");
				}
				
				String mb_email = req.getParameter("mb_email").trim();
				if (mb_email == null || mb_email.length() == 0) {
					errorMsgs.add("e-mail不得為空白");
				}
				
				// 性別
				
				Integer mb_gender = Integer.parseInt(req.getParameter("mb_gender"));
				
				String mb_line = req.getParameter("mb_line");
				if( mb_line != null && mb_line.length() != 0)
					mb_line.trim();
				
				
				java.sql.Date mb_birthday = null;
				String date = req.getParameter("mb_birthday");
				if( date != null && date.length() != 0)
					mb_birthday = java.sql.Date.valueOf(date.trim());;
				
				
				// 圖片
				Part part = req.getPart("mb_pic");
				InputStream in = part.getInputStream();
				byte[] mb_pic = new byte[in.available()];
				in.read(mb_pic);
				in.close();
				
				MemberVO memberVO = new MemberVO();
				memberVO.setMb_id(mb_id);
				memberVO.setMb_pwd(mb_pwd);
				memberVO.setMb_name(mb_name);
				memberVO.setMb_gender(mb_gender);
				memberVO.setMb_line(mb_line);
				memberVO.setMb_birthday(mb_birthday);
				memberVO.setMb_line(mb_email);
				memberVO.setMb_pic(mb_pic);
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(mb_id, mb_pwd, mb_name, mb_gender, mb_line, mb_birthday, mb_email, mb_pic);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = "/front_end/member/login.jsp";  // 
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("logout".equals(action)) { // 登出  OK
			session.removeAttribute("staffVO");
			RequestDispatcher failureView = req.getRequestDispatcher("/back_end/staff/login.jsp");
			failureView.forward(req, res);
		}
	}
}
