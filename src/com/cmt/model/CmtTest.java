package com.cmt.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CmtTest extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		CmtDAO dao = new CmtDAO();

		//新增
		CmtVO cmtVO1 = new CmtVO();
		cmtVO1.setCmt_content("強者妳本人");
		cmtVO1.setCmt_time(java.sql.Date.valueOf("2020-01-01"));
		cmtVO1.setRcd_no("rcd00001");
		cmtVO1.setMb_id("soowii123");
		dao.insert(cmtVO1);

		//修改
		CmtVO cmtVO2 = new CmtVO();
		cmtVO2.setCmt_content("差我一點");
		cmtVO2.setCmt_status(1);
		cmtVO2.setCmt_no("cmt00006");
		dao.update(cmtVO2);

		//刪除
		dao.delete("cmt00006");

		//查詢
		CmtVO cmtVO3 = dao.findByPrimaryKey("cmt00001");
		System.out.print(cmtVO3.getCmt_no() + ",");
		System.out.print(cmtVO3.getCmt_content() + ",");
		System.out.print(cmtVO3.getCmt_time() + ",");
		System.out.print(cmtVO3.getCmt_status() + ",");
		System.out.print(cmtVO3.getRcd_no() + ",");
		System.out.print(cmtVO3.getMb_id() + ",");
		System.out.println("---------------------");

		//查詢getall
		List<CmtVO> list = dao.getAll();
		for (CmtVO cmtVO : list) {
			System.out.print(cmtVO.getCmt_no() + ",");
			System.out.print(cmtVO.getCmt_content() + ",");
			System.out.print(cmtVO.getCmt_time() + ",");
			System.out.print(cmtVO.getCmt_status() + ",");
			System.out.print(cmtVO.getRcd_no() + ",");
			System.out.print(cmtVO.getMb_id());

			System.out.println();
		}
	}
}
