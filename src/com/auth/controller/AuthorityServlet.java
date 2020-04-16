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
	
		if ("update".equals(action)) {   // 個人 / 管理員 資料修改  OK
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String staff_id = req.getParameter("staff_id");
				
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
							.getRequestDispatcher(servletPath);
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				StaffService staffSvc = new StaffService();
				staffVO = staffSvc.updateStaff(staff_id, staff_pwd, staff_name, staff_status);
				// 因為更新不需要更新加入時間，傳回的物件也沒有加入時間，所以要再自己加上加入時間
				staffVO.setStaff_join(staff_join);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = null;
				if("/back_end/staff/update_self.jsp".equals(servletPath)) {  // 管理員個人資料修改
					session.setAttribute("staffVO", staffVO);
					url = "/back_end/staff/select_page.jsp";
				}else if("/back_end/staff/update_staff.jsp".equals(servletPath)) {  // 管理管理員資料修改
					url = "/back_end/staff/listAllStaff.jsp";
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
	}
	
}
