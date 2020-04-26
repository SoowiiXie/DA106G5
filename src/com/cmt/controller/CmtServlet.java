package com.cmt.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONObject;

import com.cmt.model.*;
import com.cmt_rpt.model.Cmt_rptService;
import com.cmt_rpt.model.Cmt_rptVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 100 * 1024 * 1024, maxRequestSize = 5 * 5 * 100
		* 1024 * 1024)
public class CmtServlet extends HttpServlet {

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
				String str = req.getParameter("cmt_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入地標編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String cmt_no = null;
				try {
					cmt_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CmtService cmtSvc = new CmtService();
				CmtVO cmtVO = cmtSvc.getOneCmt(cmt_no);
				if (cmtVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cmtVO", cmtVO); // 資料庫取出的VO物件,存入req
				String url = "/front_end/cmt/listOneCmt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
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
				String cmt_no = new String(req.getParameter("cmt_no"));
				/*************************** 2.開始查詢資料 ****************************************/
				CmtService cmtSvc = new CmtService();
				CmtVO cmtVO = cmtSvc.getOneCmt(cmt_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("cmtVO", cmtVO); // 資料庫取出的VO物件,存入req
				String url = "/front_end/cmt/update_cmt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update__input.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/listAllCmt.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update__input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String pageRun =req.getParameter("pageRun");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
//				String loc_no = new String(req.getParameter("loc_no").trim());
				String mbDoThisID =req.getParameter("mbDoThisID");
				String cmt_no =req.getParameter("cmt_no");
				String cmt_content = req.getParameter("cmt_content");
				String rpt_reason = req.getParameter("cmt_content");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cmt_content == null || cmt_content.trim().length() == 0) {
					errorMsgs.add("留言內容: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				java.sql.Date cmt_time = null;
//				try {
				cmt_time = java.sql.Date.valueOf(req.getParameter("cmt_time").trim());
//				} catch (IllegalArgumentException e) {
//					cmt_time=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				Integer cmt_status = new Integer(req.getParameter("cmt_status").trim());
				String rcd_no = req.getParameter("rcd_no").trim();
				String mb_id = req.getParameter("mb_id").trim();

				CmtVO cmtVO = new CmtVO();
				// cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
				cmtVO.setCmt_no(cmt_no);
				cmtVO.setCmt_content(cmt_content);
				cmtVO.setCmt_time(cmt_time);
				cmtVO.setCmt_status(cmt_status);
				cmtVO.setRcd_no(rcd_no);
				cmtVO.setMb_id(mb_id);
				
				Cmt_rptVO cmt_rptVO = new Cmt_rptVO();
				cmt_rptVO.setCmt_no(cmt_no);
				cmt_rptVO.setRpt_reason(rpt_reason);
				cmt_rptVO.setMb_id(mb_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cmtVO", cmtVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp?"+pageRun);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				CmtService cmtSvc = new CmtService();
				if(mbDoThisID.equals(cmtSvc.getOneCmt(cmt_no).getMb_id())) {
					cmtVO = cmtSvc.updateCmt(cmt_content, cmt_status, cmt_no, cmt_time, rcd_no, mb_id);
				}else {
					Cmt_rptService cmt_rptSvc = new Cmt_rptService();
					cmt_rptVO = cmt_rptSvc.addCmt_rpt(rpt_reason, cmt_no, mb_id);
				}
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cmtVO", cmtVO); // 資料庫update成功後,正確的的VO物件,存入req
				req.setAttribute("cmt_rptVO", cmt_rptVO); // 資料庫update成功後,正確的的VO物件,存入req
				String url = "/front_end/index.jsp?"+pageRun;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp?"+pageRun);
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String pageRun =req.getParameter("pageRun");
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				// cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
//				String loc_no = new String(req.getParameter("loc_no").trim());
//				String cmt_no =req.getParameter("cmt_no");
				String cmt_content = req.getParameter("cmt_content");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (cmt_content == null || cmt_content.trim().length() == 0) {
					errorMsgs.add("留言內容: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				java.sql.Date cmt_time = null;
//				try {
//				cmt_time = java.sql.Date.valueOf(req.getParameter("cmt_time").trim());
//				} catch (IllegalArgumentException e) {
				cmt_time=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				Integer cmt_status = null;
//				try {
//					cmt_status = new Integer(req.getParameter("cmt_status").trim());
//				} catch (NumberFormatException e) {
//					cmt_status = 1;
//					errorMsgs.add("留言狀態請填數字.");
//				}
				String rcd_no = req.getParameter("rcd_no").trim();
				String mb_id = req.getParameter("mb_id").trim();

				CmtVO cmtVO = new CmtVO();
				// cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
//				cmtVO.setCmt_no(cmt_no);
				cmtVO.setCmt_content(cmt_content);
				cmtVO.setCmt_time(cmt_time);
//				cmtVO.setCmt_status(cmt_status);
				cmtVO.setRcd_no(rcd_no);
				cmtVO.setMb_id(mb_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cmtVO", cmtVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp?"+pageRun);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				CmtService cmtSvc = new CmtService();
				cmtVO = cmtSvc.addCmt(cmt_content, cmt_time, rcd_no, mb_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/index.jsp?"+pageRun;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp?"+pageRun);
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
				// cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
//				String loc_no = new String(req.getParameter("loc_no").trim());
				String cmt_no =req.getParameter("cmt_no");
				CmtService cmtSvc = new CmtService();
				CmtVO cmtVO = cmtSvc.getOneCmt(cmt_no);
				Integer cmt_status = cmtVO.getCmt_status();
				if (cmt_status==2) {
					cmt_status=1;
				}else {
					cmt_status=2;
				}
				cmtVO.setCmt_status(cmt_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("cmtVO", cmtVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp?pageRun=personal_page/personal_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				cmtVO = cmtSvc.updateCmt(cmtVO.getCmt_content(), cmtVO.getCmt_status(), cmtVO.getCmt_no(), cmtVO.getCmt_time(), cmtVO.getRcd_no(), cmtVO.getMb_id());

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cmtVO", cmtVO); // 資料庫update成功後,正確的的VO物件,存入req
				String url = "/front_end/index.jsp?pageRun=personal_page/personal_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp?pageRun=personal_page/personal_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getByRcd_no".equals(action)) { // 來自select_page.jsp的請求
			// cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("rcd_no");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String rcd_no = new String(str);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CmtService cmtSvc = new CmtService();
				List<CmtVO>cmtVO_list =cmtSvc.getByRcd_no(rcd_no);
				if (cmtVO_list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cmtVO_list", cmtVO_list); // 資料庫取出的VO物件,存入req
				String url = "/front_end/cmt/listAllUWish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				
				/*************************** 2.開始查詢資料 *****************************************/
				CmtService cmtSvc = new CmtService();
				List<CmtVO> cmtVO_list =cmtSvc.getByMb_id(mb_id);
				if (cmtVO_list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("cmtVO_list", cmtVO_list); // 資料庫取出的VO物件,存入req
				String url = "/front_end/cmt/listAllUWish.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("ajaxGetOne4Update".equals(action)) {
			try {
				// Retrieve form parameters.
				String cmt_no = req.getParameter("cmt_no");
				CmtDAO dao = new CmtDAO();
				CmtVO cmtVO = dao.findByPrimaryKey(cmt_no);
				JSONObject obj = new JSONObject();
			    obj.put("cmt_content", cmtVO.getCmt_content());
			    obj.put("cmt_no", cmtVO.getCmt_no());
			    obj.put("cmt_status", cmtVO.getCmt_status());
			    obj.put("cmt_time", cmtVO.getCmt_time());
			    obj.put("mb_id", cmtVO.getMb_id());
			    obj.put("rcd_no", cmtVO.getRcd_no());
			    
				// 取出的empVO送給listOneEmp.jsp
//				RequestDispatcher successView = req.getRequestDispatcher("/front_end/index.jsp");
//				successView.forward(req, res);
//				return;
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
