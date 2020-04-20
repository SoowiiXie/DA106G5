package com.rcd_rpt.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.record.model.RecordService;
import com.record.model.RecordVO;
import com.rcd_rpt.model.Rcd_rptService;
import com.rcd_rpt.model.Rcd_rptVO;
import com.mb.model.MemberService;
import com.mb.model.MemberVO;

/**
 * Servlet implementation class Rcd_rptServlet
 */

public class Rcd_rptServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("rcd_rpt_no");
//System.out.println(str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言檢舉編號");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String rcd_rpt_no = null;
				try {
					rcd_rpt_no = new String(str);
				} catch (Exception e) {
					errorMsgs.add("留言檢舉編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Rcd_rptService rcd_rptSvc = new Rcd_rptService();
				Rcd_rptVO rcd_rptVO = rcd_rptSvc.getOneRcd_rpt(rcd_rpt_no);
				if (rcd_rpt_no == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rcd_rptVO", rcd_rptVO); // 資料庫取出的VO物件,存入req
				String url = "/back_end/rcd_rpt/listOneRcd_rpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/select_page.jsp");
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
				String rcd_rpt_no = new String(req.getParameter("rcd_rpt_no"));
//System.out.println(rcd_rpt_no);
				/*************************** 2.開始查詢資料 ****************************************/
				Rcd_rptService rcd_rptSvc = new Rcd_rptService();
				Rcd_rptVO rcd_rptVO = rcd_rptSvc.getOneRcd_rpt(rcd_rpt_no);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("rcd_rptVO", rcd_rptVO); // 資料庫取出的VO物件,存入req
				String url = "/back_end/rcd_rpt/update_rcd_rpt_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update__input.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/listAllRcd_rpt.jsp");
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
				String rcd_rpt_no =req.getParameter("rcd_rpt_no");
//System.out.println(rcd_rpt_no);		
				String rpt_reason = req.getParameter("rpt_reason");
				if (rpt_reason == null || rpt_reason.trim().length() == 0) {
					errorMsgs.add("留言檢舉內容: 請勿空白");
				}
				
				Integer rpt_status = new Integer(req.getParameter("rpt_status").trim());
				
				String rcd_no = req.getParameter("rcd_no").trim();
				
				String mb_id = req.getParameter("mb_id").trim();

				Rcd_rptVO rcd_rptVO = new Rcd_rptVO();
				rcd_rptVO.setRcd_rpt_no(rcd_rpt_no);
				rcd_rptVO.setRpt_reason(rpt_reason);
				rcd_rptVO.setRpt_status(rpt_status);
				rcd_rptVO.setRcd_no(rcd_no);
				rcd_rptVO.setMb_id(mb_id);
//System.out.println(mb_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rcd_rptVO", rcd_rptVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/update_rcd_rpt_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Rcd_rptService rcd_rptSvc = new Rcd_rptService();
				rcd_rptVO = rcd_rptSvc.updateRcd_rpt(rcd_rpt_no, rpt_reason, rpt_status, rcd_no, mb_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("rcd_rptVO", rcd_rptVO); // 資料庫update成功後,正確的的VO物件,存入req
				String url = "/back_end/rcd_rpt/listOneRcd_rpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/update_rcd_rpt_input.jsp");
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
				String rpt_reason = req.getParameter("rpt_reason");
				if (rpt_reason == null || rpt_reason.trim().length() == 0) {
					errorMsgs.add("檢舉留言內容: 請勿空白");
				}
				
				String rcd_no = req.getParameter("rcd_no").trim();
				if (rcd_no == null || rcd_no.trim().length() == 0) {
					errorMsgs.add("留言編號: 請勿空白");}
				
				String mb_id = req.getParameter("mb_id").trim();
				if (mb_id == null || mb_id.trim().length() == 0) {
					errorMsgs.add("檢舉的會員編號: 請勿空白");}
				
				Rcd_rptVO rcd_rptVO = new Rcd_rptVO();
				rcd_rptVO.setRpt_reason(rpt_reason);;
				rcd_rptVO.setRcd_no(rcd_no);
				rcd_rptVO.setMb_id(mb_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("rcd_rptVO", rcd_rptVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/addRcd_rpt.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				Rcd_rptService rcd_rptSvc = new Rcd_rptService();
				rcd_rptVO = rcd_rptSvc.addRcd_rpt(rpt_reason, rcd_no, mb_id);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back_end/rcd_rpt/listAllRcd_rpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/addRcd_rpt.jsp");
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
				String rcd_rpt_no =req.getParameter("rcd_rpt_no");
				String rpt_reason = req.getParameter("rpt_reason");
				String rcd_no = req.getParameter("rcd_no").trim();
				String mb_id = req.getParameter("mb_id").trim();
				Integer rpt_status = new Integer(req.getParameter("rpt_status").trim());
				if (rpt_status==1) {//如果是未審(1)，就改成失敗(3)，被檢舉會員的被檢舉總次數不變
					rpt_status=3;
				}else if(rpt_status==2){//如果是成功(2)，就改成失敗(3)，被檢舉會員的被檢舉總次數-1
					rpt_status=3;
					/*************************** 找到被檢舉的人 ****************************************/
					MemberService memberSvc = new MemberService();
					Rcd_rptService rcd_rptSrv = new Rcd_rptService();
					MemberVO memberVO = memberSvc.getOneMember(rcd_rptSrv.getRptedMb_id(rcd_no));
					memberSvc.updateMember(memberVO.getMb_id(), memberVO.getMb_pwd(), memberVO.getMb_name(), memberVO.getMb_gender(), memberVO.getMb_birthday(), memberVO.getMb_email(), memberVO.getMb_pic(), memberVO.getMb_lv(), memberVO.getMb_rpt_times()-1, memberVO.getMb_status());
					/*************************** 把被檢舉的留言上架(1) ****************************************/
					RecordService recordSvc = new RecordService();
					RecordVO recordVO = recordSvc.getOneRecord(rcd_no);
					recordSvc.updateRecord(recordVO.getRcd_no(), recordVO.getRcd_uploadtime(), recordVO.getRcd_content(), 1, recordVO.getPath_no());
				}else {//剩下的情況就是失敗(3)，改成成功(2)，被檢舉會員的被檢舉總次數+1
					rpt_status=2;
					/*************************** 找到被檢舉的人 ****************************************/
					MemberService memberSvc = new MemberService();
					Rcd_rptService rcd_rptSrv = new Rcd_rptService();
					MemberVO memberVO = memberSvc.getOneMember(rcd_rptSrv.getRptedMb_id(rcd_no));
					System.out.println(memberSvc.addOneToRptTime(memberVO.getMb_id()));
					/*************************** 把被檢舉的留言下架(2) ****************************************/
					RecordService rcdSvc = new RecordService();
					RecordVO recordVO = rcdSvc.getOneRecord(rcd_no);
					rcdSvc.updateRecord(recordVO.getRcd_no(), recordVO.getRcd_uploadtime(), recordVO.getRcd_content(), 2, recordVO.getPath_no());
				}
				Rcd_rptVO rcd_rptVO = new Rcd_rptVO();
				rcd_rptVO.setRcd_rpt_no(rcd_rpt_no);
				rcd_rptVO.setRpt_reason(rpt_reason);
				rcd_rptVO.setRpt_status(rpt_status);
				rcd_rptVO.setRcd_no(rcd_no);
				rcd_rptVO.setMb_id(mb_id);
				/*************************** 2.開始修改資料 *****************************************/
				Rcd_rptService rcd_rptSvc = new Rcd_rptService();
				rcd_rptVO = rcd_rptSvc.updateRcd_rptByRcdNo(rcd_rpt_no, rpt_reason, rpt_status, rcd_no, mb_id);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url ="/back_end/rcd_rpt/listAllRcd_rpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/listAllRcd_rpt.jsp");
				failureView.forward(req, res);
			}
		}
		
//		if ("getByMb_id".equals(action)) { // 來自select_page.jsp的請求
//			// cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			try {
//				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//				String mb_id = req.getParameter("mb_id");
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/rcd_rpt/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//	
//				/*************************** 2.開始查詢資料 *****************************************/
//				Rcd_rptService rcd_rptSvc = new Rcd_rptService();
//				List<Rcd_rptVO> cmt_rptVO_list =rcd_rptSvc.getRptedMb_id(mb_id);
//				if (cmt_rptVO_list == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/select_page.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				req.setAttribute("cmt_rptVO_list", cmt_rptVO_list); // 資料庫取出的VO物件,存入req
//				String url = "/back_end/cmt_rpt/listAllUWish.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//				successView.forward(req, res);
//				
//				/*************************** 其他可能的錯誤處理 *************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/cmt_rpt/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}

}
