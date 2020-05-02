package com.staff.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.abl.model.AbilityService;
import com.auth.model.AuthorityService;
import com.staff.model.StaffService;
import com.staff.model.StaffVO;

public class StaffServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// *** listAllStaff中移除自己或Boss
		// ** listAllStaff  下拉式選單篩選在職、離職
		// *** listAllStaff 離職用紅字粗體顯示
		// 選擇管理項目  ***做到一半  ***判斷是否登入
		
		//  會員分頁掛了!!!!!
		//  errorMsg 兩個問題
		//  後台首頁預設畫面  Line 162
		//  可以做一個沒有XX權限的畫面(未擁有該權限時跳出的畫面)
//			新增/修改成功時，跳Sweet Alert  V  修改自己還沒有成功Alert
//			驗證信
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
//		String servletPath = req.getParameter("servletPath");  // 從哪裡來
		String loginPath = "/back_end/staff/login.jsp";
		String indexPath = "/back_end/staff/index.jsp";
		String includePath = req.getParameter("includePath"); 

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
					RequestDispatcher failureView = req.getRequestDispatcher(loginPath);
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
					RequestDispatcher failureView = req.getRequestDispatcher(loginPath);
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				session.setAttribute("staffVO", staffVO); // 資料庫取出的VO物件,存入Session
				
				RequestDispatcher successView = req.getRequestDispatcher(indexPath); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(loginPath);
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) {   // 個人 / 管理員 資料修改  OK
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			req.setAttribute("incluePath", includePath);  // 放入來源路徑，用以錯誤回去include用
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
					req.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的staffVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(indexPath);
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始查詢資料 *****************************************/
				StaffService staffSvc = new StaffService();
				staffVO = staffSvc.updateStaff(staff_id, staff_pwd, staff_name, staff_status);
				// 因為更新不需要更新加入時間，傳回的物件也沒有加入時間，所以要再自己加上加入時間
				staffVO.setStaff_join(staff_join);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				if("/back_end/staff/update_self.jsp".equals(includePath)) {  // 管理員個人資料修改
					session.setAttribute("staffVO", staffVO);
//					includePath = "";  // 改預設首頁
				}else if("/back_end/staff/update_staff.jsp".equals(includePath)){  //  更新管理員資料
					includePath = "/back_end/staff/listAllStaff.jsp";
				}
				req.setAttribute("incluePath", includePath);  
				RequestDispatcher successView = req.getRequestDispatcher(indexPath); // 成功轉交 onePage.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(indexPath);
				failureView.forward(req, res);
			}
		}
		
		if ("select_management".equals(action)) { // 選擇管理項目  OK ***一半  ***判斷是否登入
			
			// 取得管理員
			StaffVO staffVO = (StaffVO) session.getAttribute("staffVO");
			
			// 取得被點選的管理按鈕
			String management = req.getParameter("management");
			
			// 取得管理員的所有權限
			AuthorityService authoritySvc = new AuthorityService();
			Set<String> staffAuthority = authoritySvc.getOneStaffAuthority(staffVO.getStaff_id());
			
			// 用Map裝管理編號與相對應的路徑
			Map<String,String> incluePathMap = new HashMap<String,String>();
			incluePathMap.put("00", "/back_end/staff/update_self.jsp");    // 個人資料管理  V
			incluePathMap.put("01", "/back_end/staff/listAllStaff.jsp");   // 管理員資料管理  V
			incluePathMap.put("02", "/back_end/staff/listAllMember.jsp");  // 會員管理  V
			incluePathMap.put("03", "/back_end/product/AllList2.jsp");  // 商城管理
			incluePathMap.put("04", "/front_end/location/listAllLocation.jsp");  // 地標管理
			incluePathMap.put("05", "/back_end/staff/listAllStaff.jsp");  // 檢舉管理
			incluePathMap.put("06", "/back_end/staff/listAllStaff.jsp");  // 問題回報管理
			
			// 判斷管理員是否有該權限
			if(staffAuthority.contains(management) || "00".equals(management)) {
				req.setAttribute("incluePath", incluePathMap.get(management));  // 將被點選的管理，傳回路徑(用以include)
				RequestDispatcher successView = req.getRequestDispatcher(indexPath);
				successView.forward(req, res);
				return;
				
			}else {  // 沒有權限   可以做一個沒有XX權限的畫面
				AbilityService abilitySvc = new AbilityService();
				Map<String,String> allAbility = abilitySvc.getAllToMap();
				req.setAttribute("no_authority", "您尚未擁有 "+ allAbility.get(management) +" 的權限");
				RequestDispatcher failureView = req.getRequestDispatcher(indexPath);
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 顯示一筆管理員資料For更新 OK

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/***************************查詢資料****************************************/
				StaffService staffSvc = new StaffService();
				StaffVO staffVO = staffSvc.getOneStaff(req.getParameter("staff_id"));
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("staffVO", staffVO);         
				req.setAttribute("incluePath", "/back_end/staff/update_staff.jsp");
				RequestDispatcher successView = req.getRequestDispatcher(indexPath);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(indexPath);
				failureView.forward(req, res);
			}
		}
		
		if ("check_id".equals(action)) { // Ajax 檢查ID  OK
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			
			JSONObject jsonObj = new JSONObject();
			String result = null;
			try {
			String staff_id = req.getParameter("staff_id");
			if (staff_id == null || (staff_id.trim()).length() == 0) {
				result = "帳號不得為空白";
			}
			
			StaffService staffSvc = new StaffService();
			StaffVO staffVO = staffSvc.getOneStaff(staff_id);
			if(staffVO != null) {
				result = "此帳號已被使用";
			}
			
			if(result != null) {  // 錯誤回傳
				jsonObj.put("result", result);
				out.write(jsonObj.toString());
				out.flush();
				out.close();
				return;
			}
			
			result = "此帳號可以使用";  // 正確
			jsonObj.put("result", result);
			out.write(jsonObj.toString());
			out.flush();
			out.close();
			
			}catch(Exception e) {
				result = "AJAX發生錯誤";  
				try {
					jsonObj.put("result", result);
					out.write(jsonObj.toString());
					out.flush();
					out.close();
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				
			}
		}
		
		if ("for_addStaff".equals(action)) {
			req.setAttribute("incluePath", "/back_end/staff/addStaff.jsp");
			RequestDispatcher successView = req.getRequestDispatcher(indexPath); 
			successView.forward(req, res);
		}
		if ("insert".equals(action)) { // 新增  OK
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			req.setAttribute("incluePath", includePath);  // 放入來源路徑，用以錯誤回去include用
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String staff_id = req.getParameter("staff_id");
				if (staff_id == null || staff_id.trim().length() == 0) {
					errorMsgs.add("帳號不得為空白");
				}
				
				String staff_pwd = req.getParameter("staff_pwd");
				if (staff_pwd == null || staff_pwd.trim().length() == 0) {
					errorMsgs.add("密碼不得為空白");
				}
				
				String staff_name = req.getParameter("staff_name");
				if (staff_name == null || staff_name.trim().length() == 0) {
					errorMsgs.add("名字不得為空白");
				}
				
				StaffVO staffVO = new StaffVO();
				staffVO.setStaff_id(staff_id);
				staffVO.setStaff_pwd(staff_pwd);
				staffVO.setStaff_name(staff_name);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的staffVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher(indexPath);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				StaffService StaffSvc = new StaffService();
				staffVO = StaffSvc.addStaff(staff_id, staff_pwd, staff_name);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("incluePath", "/back_end/staff/listAllStaff.jsp");  // 成功後include的畫面
				RequestDispatcher successView = req.getRequestDispatcher(indexPath); // 導回後台首頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(indexPath);
				failureView.forward(req, res);
			}
		}
		
		if ("logout".equals(action)) { // 登出  OK
			session.invalidate();
			RequestDispatcher failureView = req.getRequestDispatcher(loginPath);
			failureView.forward(req, res);
		}
		
		if ("back".equals(action)) { // 返回
			String backPath = req.getParameter("backPath");
			req.setAttribute("incluePath", backPath);
			RequestDispatcher failureView = req.getRequestDispatcher(indexPath);
			failureView.forward(req, res);
		}
		
	}
}
