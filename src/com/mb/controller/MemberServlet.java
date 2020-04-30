package com.mb.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Base64;
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

import org.json.JSONObject;

import com.mb.model.MemberService;
import com.mb.model.MemberVO;
import com.staff.model.StaffService;
import com.staff.model.StaffVO;

@MultipartConfig
public class MemberServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String servletPath = req.getParameter("servletPath"); // 從哪裡來

		if ("searchMb".equals(action)) { 

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
//					RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp?pageRun=personal_page/personal_page_other.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO searchMbVO = memberSvc.getOneMember(mb_id);
				
				if (searchMbVO == null || (mb_id.trim()).length() == 0) {
					errorMsgs.add("查無此帳號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp?pageRun=personal_page/personal_page_other.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("searchMbVO", searchMbVO); // 資料庫取出的VO物件,存入Session
				
				String url = "/front_end/index.jsp?pageRun=personal_page/personal_page_other.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp?pageRun=personal_page/personal_page_other.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { // 登入
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
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
					RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mb_id);
				if (memberVO == null || !memberVO.getMb_pwd().equals(mb_pwd)) {
					errorMsgs.add("帳號或密碼有誤");
				}
				// 把Line資訊塞進去
				if (memberVO != null && memberVO.getMb_pic() == null && memberVO.getMb_line_pic() == null) {
					MemberVO memberLineVO = memberSvc.getOneMemberPG(mb_id);
					if (memberLineVO != null) {
						memberSvc.updateLine(memberLineVO.getMb_line_id(), memberLineVO.getMb_line_pic(),
											 memberLineVO.getMb_line_display(), memberLineVO.getMb_line_status(), mb_id);
					}
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				session.setAttribute("memberVO", memberVO); // 資料庫取出的VO物件,存入Session
				
//				String url = req.getContextPath() + "/front_end/member/listOneMember.jsp";  // 測試
				String url = req.getContextPath() + "/front_end/index.jsp?pageRun=personal_page/personal_page.jsp";
				
				String originalJSP = (String)session.getAttribute("originalJSP");  // 
				if(originalJSP != null) {
					url = originalJSP;
					if((req.getContextPath() + "/front_end/index.jsp").equals(originalJSP)) {
						url = req.getContextPath() + "/front_end/index.jsp?pageRun=personal_page/personal_page.jsp";
					}
					session.removeAttribute("originalJSP");
				}
				res.sendRedirect(url);
				return;

				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
//				failureView.forward(req, res);
//			}
		}

		if ("update".equals(action)) { // 修改
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MemberService memberSvc = new MemberService();
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mb_id = req.getParameter("mb_id");

				String mb_pwd = req.getParameter("mb_pwd");
				if (mb_pwd == null || mb_pwd.trim().length() == 0) {
					errorMsgs.add("密碼不得為空白");
				}

				String mb_name = req.getParameter("mb_name");
				if (mb_name == null || mb_name.trim().length() == 0) {
					errorMsgs.add("姓名不得為空白");
				}

				String mb_email = req.getParameter("mb_email");
				if (mb_email == null || mb_email.trim().length() == 0) {
					errorMsgs.add("e-mail不得為空白");
				}

				Integer mb_gender = Integer.parseInt(req.getParameter("mb_gender"));

				java.sql.Date mb_birthday = null;
				String date = req.getParameter("mb_birthday");
				if (date != null && date.length() != 0)
					mb_birthday = java.sql.Date.valueOf(date.trim());
				;

				// 圖片

				byte[] mb_pic = null;
				Part part = req.getPart("mb_pic");
				if (part.getSize() != 0) { // 有上傳圖片

					InputStream in = part.getInputStream();
					mb_pic = new byte[in.available()];
					in.read(mb_pic);
					in.close();

				} else { // 沒有上傳圖片，用原來的圖片
					mb_pic = memberSvc.getOneMember(mb_id).getMb_pic();
				}

				Integer mb_lv = Integer.parseInt(req.getParameter("mb_lv"));
				Integer mb_rpt_times = Integer.parseInt(req.getParameter("mb_rpt_times"));
				Integer mb_status = Integer.parseInt(req.getParameter("mb_status"));

				MemberVO memberVO = new MemberVO();
				memberVO.setMb_id(mb_id);
				memberVO.setMb_pwd(mb_pwd);
				memberVO.setMb_name(mb_name);
				memberVO.setMb_gender(mb_gender);
				memberVO.setMb_birthday(mb_birthday);
				memberVO.setMb_email(mb_email);
				memberVO.setMb_pic(mb_pic);
				memberVO.setMb_lv(mb_lv);
				memberVO.setMb_rpt_times(mb_rpt_times);
				memberVO.setMb_status(mb_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				
				memberVO = memberSvc.updateMember(mb_id, mb_pwd, mb_name, mb_gender, mb_birthday, mb_email, mb_pic,
						mb_lv, mb_rpt_times, mb_status);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = null;
				if(servletPath.contains("front_end")) {  // 從前端修改
					session.setAttribute("memberVO", memberVO);
					url = "/front_end/member/listOneMember.jsp"; 
				}else {  // 從後端修改
					url = "/back_end/staff/listAllMember.jsp"; 
				}
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
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
				} else {
					mb_gender = Integer.parseInt(gender);
				}

				String mb_email = req.getParameter("mb_email");
				if (mb_email == null || mb_email.trim().length() == 0) {
					errorMsgs.add("e-mail不得為空白");
				}

				java.sql.Date mb_birthday = null;
				String date = req.getParameter("mb_birthday");
				if (date != null && date.length() != 0)
					mb_birthday = java.sql.Date.valueOf(date.trim());
				;

				// 圖片
				Part part = req.getPart("mb_pic");
				byte[] mb_pic = null;
				String picBase64 = req.getParameter("picBase64");
				if (part.getSize() != 0) { // 有上傳圖片
					InputStream in = part.getInputStream();
					mb_pic = new byte[in.available()];
					in.read(mb_pic);
					in.close();
				} else if (picBase64 != null && picBase64.trim().length() != 0) { // 第一次有上傳圖片，第二次無，使用參數傳進來的picBase64
					mb_pic = Base64.getDecoder().decode(picBase64.getBytes("UTF-8"));
				} // 若都沒有，mb_pic 以 null 送出

				MemberVO memberVO = new MemberVO();
				memberVO.setMb_id(mb_id);
				memberVO.setMb_pwd(mb_pwd);
				memberVO.setMb_name(mb_name);
				memberVO.setMb_gender(mb_gender);
				memberVO.setMb_birthday(mb_birthday);
				memberVO.setMb_email(mb_email);
				memberVO.setMb_pic(mb_pic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.addMember(mb_id, mb_pwd, mb_name, mb_gender, mb_birthday, mb_email, mb_pic);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = "/front_end/member/login.jsp"; //
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
				failureView.forward(req, res);
			}
		}

		if ("check_id".equals(action)) { // Ajax 檢查ID OK

			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();

			JSONObject jsonObj = new JSONObject();
			String result = null;
			try {
				String mb_id = req.getParameter("mb_id");
				if (mb_id == null || (mb_id.trim()).length() == 0) {
					result = "帳號不得為空白";
				}

				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mb_id);
				if (memberVO != null) {
					result = "此帳號已被使用";
				}

				if (result != null) { // 錯誤回傳
					jsonObj.put("result", result);
					out.write(jsonObj.toString());
					out.flush();
					out.close();
					return;
				}

				result = "此帳號可以使用"; // 正確
				jsonObj.put("result", result);
				out.write(jsonObj.toString());
				out.flush();
				out.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("logout".equals(action)) { // 登出
			session.invalidate();
			RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/login.jsp");
			failureView.forward(req, res);
		}
		
		// 後台 - 會員管理
		if ("getOne_Member_For_Update".equals(action)) { // 顯示一筆會員資料For更新 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/***************************查詢資料****************************************/
				String mb_id = req.getParameter("mb_id");
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(mb_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         
				String url = "/back_end/staff/update_member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(servletPath);
				failureView.forward(req, res);
			}
		}	
	}
}
