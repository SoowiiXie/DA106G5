package com.product.controller;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pd_follow.model.Pd_followService;
import com.pd_follow.model.Pd_followVO;

public class Pd_followServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if (action.equals("AddPd_follow")) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pd_no = req.getParameter("pd_no");
				String mb_id = (String) session.getAttribute("mb_id");
				Pd_followVO pd_followVO = new Pd_followVO();
				pd_followVO.setMb_id(mb_id);
				pd_followVO.setPd_no(pd_no);

				Pd_followService pd_followService = new Pd_followService();
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("pd_followVO", pd_followVO); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ShoppingServlet?action=findOneProduct&pd_no=" + pd_no);
					failureView.forward(req, res);
					return; // 程式中斷
				}

				pd_followService.insertMemberOneProduct(pd_followVO);
				String url = "/ShoppingServlet?action=findOneProduct&pd_no=" + pd_no;
				String addType_follow = "商品收藏成功";
				req.setAttribute("addType_follow", addType_follow);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);
				return;

			} catch (Exception e) {
				if ("A database error occured. ORA-00001: unique constraint (DA106G5.PK_PD_FOLLOW) violated"
						.equals(e.getMessage().trim())) { // 來自listAllEmp.jsp
					try {
						/*************************** 1.接收請求參數 ***************************************/
						String mb_id = (String) session.getAttribute("mb_id");
						String pd_no = req.getParameter("pd_no");
						/*************************** 2.開始刪除資料 ***************************************/
						Pd_followVO pd_followVO = new Pd_followVO();
						pd_followVO.setMb_id(mb_id);
						pd_followVO.setPd_no(pd_no);
						Pd_followService pd_followService = new Pd_followService();
						pd_followService.deleteMemberOneProduct(pd_followVO);

						/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
						String url = "/ShoppingServlet?action=findOneProduct&pd_no=" + pd_no;
						String addType_follow = "取消商品收藏";
						req.setAttribute("addType_follow", addType_follow);
						RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception eDel) {
						errorMsgs.add("刪除資料失敗:" + eDel.getMessage());
						String pd_no = req.getParameter("pd_no");
						RequestDispatcher failureViewDel = req
								.getRequestDispatcher("/ShoppingServlet?action=findOneProduct&pd_no=" + pd_no);
						failureViewDel.forward(req, res);
					}

				} else {
					errorMsgs.add(e.getMessage());
					String pd_no = req.getParameter("pd_no");
					RequestDispatcher failureView = req
							.getRequestDispatcher("/ShoppingServlet?action=findOneProduct&pd_no=" + pd_no);
					failureView.forward(req, res);

				}

			}

		}
		if (action.equals("DeleteOnePd_follow")) {
			String whichPage = req.getParameter("whichPage");
			System.out.println("頁數測試"+whichPage);
			String requestURL = req.getParameter("requestURL");
			String pd_no = req.getParameter("pd_no");
			String mb_id = (String) session.getAttribute("mb_id");
			Pd_followVO pd_followVO = new Pd_followVO();
			pd_followVO.setPd_no(pd_no);
			pd_followVO.setMb_id(mb_id);
			Pd_followService pd_followService = new Pd_followService();
			pd_followService.deleteMemberOneProduct(pd_followVO);
			String url = requestURL;
			req.setAttribute("whichPage", whichPage);
			RequestDispatcher failureView = req.getRequestDispatcher(url+"?whichPage="+whichPage);
			failureView.forward(req, res);
			return;
		}
	}
}