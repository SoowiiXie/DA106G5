package com.thumb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.json.JSONArray;
//import org.json.JSONObject;

import com.thumb.model.*;

public class ThumbAjaxResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThumbAjaxResponse() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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
					RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 ***************************************/
				ThumbService thumbSvc = new ThumbService();
				thumbVO = thumbSvc.addThumb(mb_id, rcd_no);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(String.valueOf(thumbSvc.countAllThumbs(rcd_no)));
				out.flush();
				out.close();
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
						res.setContentType("text/plain");
						res.setCharacterEncoding("UTF-8");
						PrintWriter out = res.getWriter();
						out.write(String.valueOf(thumbSvc.countAllThumbs(rcd_no)));
						out.flush();
						out.close();
						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception eDel) {
						errorMsgs.add("刪除資料失敗:" + eDel.getMessage());
						RequestDispatcher failureViewDel = req.getRequestDispatcher("/front_end/index.jsp");
						failureViewDel.forward(req, res);
					}
				}
				else {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp");
				failureView.forward(req, res);
				}
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
