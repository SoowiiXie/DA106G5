package com.record.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecordTest extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RecordService recordSvc = new RecordService();

		//查詢
		List<RecordVO> list= recordSvc.getByMb_id("anjavababy520");
		System.out.println(list);
		
		//查詢
		List<RecordVO> listFollow= recordSvc.getByMbs_id(new String[] {"anjavababy520","soowii123"});
		System.out.println(listFollow);

	}
}
