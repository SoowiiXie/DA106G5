package com.mb.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.location.model.LocationService;
import com.location.model.LocationVO;
import com.mb.model.MemberService;
import com.mb.model.MemberVO;


public class MemberServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("login".equals(action)) { // 來自select_page.jsp的請求

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
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/Login.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/Login.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", memberVO); // 資料庫取出的VO物件,存入req
				String url = "/front_end/member/Login.jsp";
				errorMsgs.add("登入成功");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/member/Login.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Display".equals(action)) { // 來自listAllEmp.jsp的請求

		}

		if ("update".equals(action)) { // 來自update__input.jsp的請求
			
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			
		}
	}
}
