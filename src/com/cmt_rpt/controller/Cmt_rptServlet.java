package com.cmt_rpt.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.cmt.model.*;
import com.cmt_rpt.model.*;
import com.mb.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 100 * 1024 * 1024, maxRequestSize = 5 * 5 * 100
		* 1024 * 1024)
public class Cmt_rptServlet extends HttpServlet {

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

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("cmt_rpt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言檢舉編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String cmt_rpt_no = null;
				try {
					cmt_rpt_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("留言檢舉編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Cmt_rptService cmt_rptSvc = new Cmt_rptService();
				Cmt_rptVO cmt_rptVO = cmt_rptSvc.getOneCmt_rpt(cmt_rpt_no);
				if (cmt_rptVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cmt_rptVO", cmt_rptVO); // 資料庫取出的VO物件,存入req
				String url = "/back_end/cmt_rpt/listOneCmt_rpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String cmt_rpt_no = new String(req.getParameter("cmt_rpt_no"));
				/*************************** 2.開始查詢資料 ****************************************/
				Cmt_rptService cmt_rptSvc = new Cmt_rptService();
				Cmt_rptVO cmt_rptVO = cmt_rptSvc.getOneCmt_rpt(cmt_rpt_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("cmt_rptVO", cmt_rptVO); // 資料庫取出的VO物件,存入req
				String url = "/back_end/cmt_rpt/update_cmt_rpt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update__input.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/listAllCmt_rpt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update__input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id
//				String loc_no = new String(req.getParameter("loc_no").trim());
				String cmt_rpt_no =req.getParameter("cmt_rpt_no");
				String rpt_reason = req.getParameter("rpt_reason");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (rpt_reason == null || rpt_reason.trim().length() == 0) {
					errorMsgs.add("留言檢舉內容: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				Integer rpt_status = new Integer(req.getParameter("rpt_status").trim());
				String cmt_no = req.getParameter("cmt_no").trim();
				String mb_id = req.getParameter("mb_id").trim();

				Cmt_rptVO cmt_rptVO = new Cmt_rptVO();
				cmt_rptVO.setCmt_rpt_no(cmt_rpt_no);
				cmt_rptVO.setRpt_reason(rpt_reason);
				cmt_rptVO.setRpt_status(rpt_status);
				cmt_rptVO.setCmt_no(cmt_no);
				cmt_rptVO.setMb_id(mb_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cmt_rptVO", cmt_rptVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/update_cmt_rpt_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Cmt_rptService cmt_rptSvc = new Cmt_rptService();
				cmt_rptVO = cmt_rptSvc.updateCmt_rpt(cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cmt_rptVO", cmt_rptVO); // 資料庫update成功後,正確的的VO物件,存入req
				String url = "/back_end/cmt_rpt/listOneCmt_rpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/update_cmt_rpt_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id
//				String loc_no = new String(req.getParameter("loc_no").trim());
//				String cmt_no =req.getParameter("cmt_no");
				String rpt_reason = req.getParameter("rpt_reason");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (rpt_reason == null || rpt_reason.trim().length() == 0) {
					errorMsgs.add("檢舉留言內容: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String cmt_no = req.getParameter("cmt_no").trim();
				if (cmt_no == null || cmt_no.trim().length() == 0) {
					errorMsgs.add("留言編號: 請勿空白");}
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("檢舉的會員編號: 請勿空白");}
				Cmt_rptVO cmt_rptVO = new Cmt_rptVO();
				cmt_rptVO.setRpt_reason(rpt_reason);;
				cmt_rptVO.setCmt_no(cmt_no);
				cmt_rptVO.setMb_id(mb_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cmt_rptVO", cmt_rptVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/addCmt_rpt.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				Cmt_rptService cmt_rptSvc = new Cmt_rptService();
				cmt_rptVO = cmt_rptSvc.addCmt_rpt(rpt_reason, cmt_no, mb_id);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/cmt/listAllCmt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/addCmt_rpt.jsp");
				failureView.forward(req, res);
			}
		}
		
//		fakeDelete=上/下架，因為不會刪除也等於only update cmt_status
		if ("fakeDelete".equals(action)) { // 來自listAllEmp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id
//				String loc_no = new String(req.getParameter("loc_no").trim());
				String cmt_rpt_no =req.getParameter("cmt_rpt_no");
				String rpt_reason = req.getParameter("rpt_reason");
				String cmt_no = req.getParameter("cmt_no").trim();
				String mb_id = req.getParameter("mb_id").trim();
				Integer rpt_status = new Integer(req.getParameter("rpt_status").trim());
				if (rpt_status==1) {//如果是未審(1)，就改成失敗(3)，被檢舉會員的被檢舉總次數不變
					rpt_status=3;
				}else if(rpt_status==2){//如果是成功(2)，就改成失敗(3)，被檢舉會員的被檢舉總次數-1
					rpt_status=3;
					/*************************** 找到被檢舉的人 ****************************************/
					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.getOneMember(mb_id);
					memberSvc.updateMember(memberVO.getMb_id(), memberVO.getMb_pwd(), memberVO.getMb_name(), memberVO.getMb_gender(), memberVO.getMb_line(), memberVO.getMb_birthday(), memberVO.getMb_email(), memberVO.getMb_pic(), memberVO.getMb_lv(), memberVO.getMb_rpt_times()-1, memberVO.getMb_status());
					/*************************** 把被檢舉的留言上架(1) ****************************************/
					CmtService cmtSvc = new CmtService();
					CmtVO cmtVO = cmtSvc.getOneCmt(cmt_no);
					cmtSvc.updateCmt(cmtVO.getCmt_content(), 1, cmtVO.getCmt_no(), cmtVO.getCmt_time(), cmtVO.getRcd_no(), cmtVO.getMb_id());
				}else {//剩下的情況就是失敗(3)，改成成功(2)，被檢舉會員的被檢舉總次數+1
					rpt_status=2;
					/*************************** 找到被檢舉的人 ****************************************/
					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.getOneMember(mb_id);
					memberSvc.updateMember(memberVO.getMb_id(), memberVO.getMb_pwd(), memberVO.getMb_name(), memberVO.getMb_gender(), memberVO.getMb_line(), memberVO.getMb_birthday(), memberVO.getMb_email(), memberVO.getMb_pic(), memberVO.getMb_lv(), memberVO.getMb_rpt_times()+1, memberVO.getMb_status());
					/*************************** 把被檢舉的留言下架(2) ****************************************/
					CmtService cmtSvc = new CmtService();
					CmtVO cmtVO = cmtSvc.getOneCmt(cmt_no);
					cmtSvc.updateCmt(cmtVO.getCmt_content(), 2, cmtVO.getCmt_no(), cmtVO.getCmt_time(), cmtVO.getRcd_no(), cmtVO.getMb_id());
				}
				Cmt_rptVO cmt_rptVO = new Cmt_rptVO();
				cmt_rptVO.setCmt_rpt_no(cmt_rpt_no);
				cmt_rptVO.setRpt_reason(rpt_reason);
				cmt_rptVO.setRpt_status(rpt_status);
				cmt_rptVO.setCmt_no(cmt_no);
				cmt_rptVO.setMb_id(mb_id);
				/*************************** 2.開始修改資料 *****************************************/
				Cmt_rptService cmt_rptSvc = new Cmt_rptService();
				cmt_rptVO = cmt_rptSvc.updateCmt_rptByCmtNo(cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url ="/back_end/cmt_rpt/listAllCmt_rpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/listAllCmt_rpt.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getByMb_id".equals(action)) { // 來自select_page.jsp的請求
			// cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String mb_id = req.getParameter("mb_id");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
	
				/*************************** 2.開始查詢資料 *****************************************/
				Cmt_rptService cmt_rptSvc = new Cmt_rptService();
				List<Cmt_rptVO> cmt_rptVO_list =cmt_rptSvc.getByMb_id(mb_id);
				if (cmt_rptVO_list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cmt_rptVO_list", cmt_rptVO_list); // 資料庫取出的VO物件,存入req
				String url = "/back_end/cmt_rpt/listAllUWish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
