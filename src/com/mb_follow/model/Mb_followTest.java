package com.mb_follow.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Mb_followTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		Mb_followDAO dao = new Mb_followDAO();
		Mb_followService Mb_followSvc = new Mb_followService();
		String[] mbs_id=Mb_followSvc.getByMb_id("anjavababy520");
		for (int i = 0; i < mbs_id.length; i++) {
			System.out.println(mbs_id[i]);
		}
	}
}
