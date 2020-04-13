package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cp_get.model.Cp_getService;
import com.cp_get.model.Cp_getVO;



public class CpGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		

		String action = req.getParameter("action");

		if (action.equals("searchMemberCpGet")) {

			String mb_id = req.getParameter("mb_id");

	
			
			req.setAttribute("mb_id", mb_id);

			String url = "/back_end/product/ListOneMemberGetCoupon.jsp";
			
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
			
			
			
			
		}

	}

}
