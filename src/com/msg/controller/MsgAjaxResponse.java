package com.msg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

//import org.json.JSONArray;
//import org.json.JSONObject;

import com.msg.model.MessageService;
import com.msg.model.MessageVO;

public class MsgAjaxResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MsgAjaxResponse() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("lookOneMsg".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String msg_no = req.getParameter("msg_no").trim();
				/*************************** 2.開始查詢資料 ***************************************/
				MessageService messageSvc = new MessageService();
				MessageVO messageVO = messageSvc.getOneMessage(msg_no);
				messageSvc.updateMessage(msg_no, messageVO.getMb_id_1(), messageVO.getMb_id_2(), messageVO.getMsg_content(), 2);
				messageVO = messageSvc.getOneMessage(msg_no);
				JSONObject jsobj = new JSONObject(messageVO);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(jsobj.toString());
				out.flush();
				out.close();
				/*************************** 其他可能的錯誤處理 **********************************/
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front_end/index.jsp");
				failureView.forward(req, res);
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
