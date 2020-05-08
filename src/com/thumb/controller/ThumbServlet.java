package com.thumb.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import org.json.JSONArray;

import com.mb_follow.model.Mb_followService;
import com.thumb.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 100 * 1024 * 1024, maxRequestSize = 5 * 5 * 100
		* 1024 * 1024)
public class ThumbServlet extends HttpServlet {

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

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String rcd_no = req.getParameter("rcd_no").trim();
				String mb_id = req.getParameter("mb_id").trim();
				
				ThumbVO thumbVO = new ThumbVO();
				thumbVO.setRcd_no(rcd_no);
				thumbVO.setMb_id(mb_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("thumbVO", thumbVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/listAllCmt.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				ThumbService thumbSvc = new ThumbService();
				thumbVO = thumbSvc.addThumb(mb_id, rcd_no);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/front_end/cmt/listAllCmt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			}catch (Exception e) {
				if ("A database error occured. ORA-00001: unique constraint (DA106G5.PK_THUMB) violated".equals(e.getMessage().trim())) { // 來自listAllEmp.jsp
					try {
						/*************************** 1.接收請求參數 ***************************************/
						String rcd_no = req.getParameter("rcd_no").trim();
						String mb_id = req.getParameter("mb_id").trim();
						/*************************** 2.開始刪除資料 ***************************************/
						ThumbService thumbSvc = new ThumbService();
						thumbSvc.deleteThumb(mb_id, rcd_no);
						/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
						String url = "/front_end/cmt/listAllCmt.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception eDel) {
						errorMsgs.add("刪除資料失敗:" + eDel.getMessage());
						RequestDispatcher failureViewDel = req.getRequestDispatcher("/front_end/cmt/listAllCmt.jsp");
						failureViewDel.forward(req, res);
					}
				}
				else {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/cmt/listAllCmt.jsp");
				failureView.forward(req, res);
				}
			}
		}
		
		if ("ajaxGetByRcd_no".equals(action)) {
			try {
				// Retrieve form parameters.
				String rcd_no = req.getParameter("rcd_no");
				String mb_id = req.getParameter("mb_id");
				
				Mb_followService mb_followSvc = new Mb_followService();
				ThumbService thumbSvc = new ThumbService();
				List<ThumbVO> whoThumb_list = thumbSvc.whoThumb(rcd_no);
				List<ThumbFollowVO> whoThumbWithFollow_list = new ArrayList<ThumbFollowVO>();
//				HttpSession session = req.getSession();
//				session.setAttribute("weather_detailVO_list", weather_detailVO_list);
				for (ThumbVO thumbVO4Follow : whoThumb_list) {
					ThumbFollowVO thumbFollowVO = new ThumbFollowVO();
					thumbFollowVO.setMb_id(thumbVO4Follow.getMb_id());
					thumbFollowVO.setRcd_no(thumbVO4Follow.getRcd_no());
					thumbFollowVO.setHasFollow(mb_followSvc.hasFollow(mb_id, thumbVO4Follow.getMb_id()));
					whoThumbWithFollow_list.add(thumbFollowVO);
				}
				JSONArray jsArray = new JSONArray(whoThumbWithFollow_list);
				// 取出的empVO送給listOneEmp.jsp
//				RequestDispatcher successView = req.getRequestDispatcher("/front_end/index.jsp");
//				successView.forward(req, res);
//				return;
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(jsArray.toString());
				out.flush();
				out.close();

				// Handle any unusual exceptions
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
	}
}
