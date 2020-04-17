package com.auth.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.abl.model.AbilityService;
import com.auth.model.AuthorityService;
import com.auth.model.AuthorityVO;
import com.mb.model.MemberService;
import com.mb.model.MemberVO;
import com.staff.model.StaffService;
import com.staff.model.StaffVO;

public class AuthorityServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String servletPath = req.getParameter("servletPath");  // 從哪裡來
	
		if ("update_authority".equals(action)) {   // 權限修改
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// 來源不是使用者輸入，所以不用錯誤處理
				String staff_id = req.getParameter("staff_id");
				String[] abilitys = req.getParameterValues("ability_no");
				
				/*************************** 2.開始查詢資料 *****************************************/
				AuthorityService authoritySvc = new AuthorityService();
				// 刪除某個管理員所有權限
				authoritySvc.deleteOneStaffAllAuthority(staff_id);
				if(abilitys!=null) {
					for(String ability_no:abilitys) {
						// 新增權限
						authoritySvc.addAuthority(staff_id, ability_no);
					}
				}
				
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				
				String url = "/back_end/staff/listAllStaff.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
	
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(servletPath);
				failureView.forward(req, res);
			}
		}
	}
	
}
