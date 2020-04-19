package com.mb.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.location.model.LocationService;
import com.location.model.LocationVO;
import com.mb.model.MemberDAO;
import com.mb.model.MemberService;
import com.mb.model.MemberVO;

@MultipartConfig
public class MemberServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// ***註冊、修改的資料判斷
		// ***登入、註冊時，若已登入過會直接導向XXX
		// ***AJAX改到Controller
		// *** 性別選擇改用Context的MAP
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String servletPath = req.getParameter("servletPath");  // 從哪裡來
		
		if ("searchMb".equals(action)) { // 登入

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mb_id = req.getParameter("mb_id");
				if (mb_id == null || (mb_id.trim()).length() == 0) {
					errorMsgs.add("帳號不得為空白");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mb_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的VO物件,存入Session
				String url = "/front_end/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { // 登入

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mb_id = req.getParameter("mb_id");
				if (mb_id == null || (mb_id.trim()).length() == 0) {
					errorMsgs.add("帳號不得為空白");
				}
				
				String mb_pwd = req.getParameter("mb_pwd");
				if (mb_pwd == null || (mb_pwd.trim()).length() == 0) {
					errorMsgs.add("密碼不得為空白");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mb_id);
				if (memberVO == null || !memberVO.getMb_pwd().equals(mb_pwd)) {
					errorMsgs.add("帳號或密碼有誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				session.setAttribute("memberVO", memberVO); // 資料庫取出的VO物件,存入Session
				String url = "/front_end/member/listOneMember.jsp";  // 
//				String url = "/front_end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 修改
			List<String> errorMsgs = new LinkedList<String>();
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
				memberVO.setMb_email(mb_email);
				memberVO.setMb_pic(mb_pic);
				memberVO.setMb_lv(mb_lv);
				memberVO.setMb_rpt_times(mb_rpt_times);
				memberVO.setMb_status(mb_status);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/member/update_member.jsp");
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
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mb_id = req.getParameter("mb_id");
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("帳號不得為空白");
				}
				
				String mb_pwd = req.getParameter("mb_pwd");
				if (mb_pwd == null || mb_pwd.trim().length() == 0) {
					errorMsgs.add("密碼不得為空白");
				}
				
				String mb_name = req.getParameter("mb_name");
				if (mb_name == null || mb_name.trim().length() == 0) {
					errorMsgs.add("姓名不得為空白");
				}
				
				// 性別
				String gender = req.getParameter("mb_gender");
				Integer mb_gender = null;
				if (gender == null) {
					errorMsgs.add("請選擇性別");
				}else {
					mb_gender = Integer.parseInt(gender);
				}
				
				String mb_email = req.getParameter("mb_email");
				if (mb_email == null || mb_email.trim().length() == 0) {
					errorMsgs.add("e-mail不得為空白");
				}
				
				String mb_line = req.getParameter("mb_line");
				if( mb_line != null && mb_line.length() != 0)
					mb_line.trim();
				
				
				java.sql.Date mb_birthday = null;
				String date = req.getParameter("mb_birthday");
				if( date != null && date.length() != 0)
					mb_birthday = java.sql.Date.valueOf(date.trim());;
				
				
				// 圖片
				Part part = req.getPart("mb_pic");
				byte[] mb_pic = null;
				String picBase64 = req.getParameter("picBase64");
				if(part.getSize() != 0) {  // 有上傳圖片
					InputStream in = part.getInputStream();
					mb_pic = new byte[in.available()];
					in.read(mb_pic);
					in.close();
				}else if(picBase64 != null && picBase64.trim().length() != 0) {  // 第一次有上傳圖片，第二次無，使用參數傳進來的picBase64
					mb_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
				}  // 若都沒有，mb_pic 以 null 送出
				
				MemberVO memberVO = new MemberVO();
				memberVO.setMb_id(mb_id);
				memberVO.setMb_pwd(mb_pwd);
				memberVO.setMb_name(mb_name);
				memberVO.setMb_gender(mb_gender);
				memberVO.setMb_line(mb_line);
				memberVO.setMb_birthday(mb_birthday);
				memberVO.setMb_email(mb_email);
				memberVO.setMb_pic(mb_pic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(servletPath);
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
				RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
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
