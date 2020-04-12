package com.staff.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mb.model.MemberService;
import com.mb.model.MemberVO;
import com.staff.model.StaffService;
import com.staff.model.StaffVO;

public class StaffServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// 註冊、修改的資料判斷
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("getOne_For_Display".equals(action)) { // 登入   OK
			// XX
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
		
		if ("select_management".equals(action)) { // 選擇管理
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			switch(req.getParameter("management")) {
				case "01":  // 管理員管理
					
					break;
				
				case "02":  // 留言管理
					
					break;
				
				case "03":  // 檢舉管理
					
					break;
					
				case "04":  // 商城管理	
					
					break;
				
				case "05":  // 問題回報管理
					
					break;
			}
			
				
			
		}

		if ("update".equals(action)) { // 修改
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mb_id = req.getParameter("mb_id").trim();
				
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
				
				Integer mb_gender = Integer.parseInt(req.getParameter("mb_gender"));
				
				String mb_line = req.getParameter("mb_line");
				if( mb_line != null && mb_line.length() != 0)
					mb_line.trim();
				
				
				java.sql.Date mb_birthday = null;
				String date = req.getParameter("mb_birthday");
				if( date != null && date.length() != 0)
					mb_birthday = java.sql.Date.valueOf(date.trim());;
				
				
				// 圖片    
					
				byte[] mb_pic = null;
				Part part = req.getPart("mb_pic");
				if(part.getSize() != 0) {  // 有上傳圖片
					
					InputStream in = part.getInputStream();
					mb_pic = new byte[in.available()];
					in.read(mb_pic);
					in.close();
					
				}else {  // 沒有上傳圖片，用原來的圖片
					mb_pic = ((MemberVO)session.getAttribute("memberVO")).getMb_pic();
				}
				
				
				Integer mb_lv = Integer.parseInt(req.getParameter("mb_lv"));
				Integer mb_rpt_times = Integer.parseInt(req.getParameter("mb_rpt_times"));
				Integer mb_status = Integer.parseInt(req.getParameter("mb_status"));
				
				MemberVO memberVO = new MemberVO();
				memberVO.setMb_id(mb_id);
				memberVO.setMb_pwd(mb_pwd);
				memberVO.setMb_name(mb_name);
				memberVO.setMb_gender(mb_gender);
				memberVO.setMb_line(mb_line);
				memberVO.setMb_birthday(mb_birthday);
				memberVO.setMb_line(mb_email);
				memberVO.setMb_pic(mb_pic);
				memberVO.setMb_lv(mb_lv);
				memberVO.setMb_rpt_times(mb_rpt_times);
				memberVO.setMb_status(mb_status);
				
				
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
				memberVO = memberSvc.updateMember(mb_id, mb_pwd, mb_name, mb_gender, mb_line, mb_birthday, 
						mb_email, mb_pic, mb_lv, mb_rpt_times, mb_status);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				session.setAttribute("memberVO", memberVO);
				String url = "/front_end/member/listOneMember.jsp";  // 
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
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
		
		if ("logout".equals(action)) { // 登出
			session.removeAttribute("memberVO");
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
			failureView.forward(req, res);
		}
	}
}
