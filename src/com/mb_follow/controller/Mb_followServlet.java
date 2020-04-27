package com.mb_follow.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mb.model.MemberService;
import com.mb.model.MemberVO;
import com.mb_follow.model.Mb_followService;

public class Mb_followServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		String servletPath = req.getParameter("servletPath"); // 從哪裡來
		
		if ("follow".equals(action)) { // 關注
			PrintWriter out = res.getWriter();
			try {
				
				/***************************查詢資料****************************************/
				String mb_id = req.getParameter("mb_id");
				String mb_id_followed = req.getParameter("mb_id_followed");
				
				Mb_followService mb_followSvc = new Mb_followService();
				if(mb_followSvc.hasFollow(mb_id, mb_id_followed)) {
					mb_followSvc.deleteByString(mb_id, mb_id_followed);
					out.write("deleteFollow");
				}else {
					mb_followSvc.insertByString(mb_id, mb_id_followed);
					out.write("addFollow");
				}
				out.flush();
				out.close();
				return;
								
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
			}
		}
	};
}
