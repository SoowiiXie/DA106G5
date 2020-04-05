package com.cmt_rpt.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cmt_rptTest extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cmt_rptDAO dao = new Cmt_rptDAO();

		 //新增
		Cmt_rptVO cmt_rptVO1 = new Cmt_rptVO();
		cmt_rptVO1.setRpt_reason("這個我不行!!!!!!");
		cmt_rptVO1.setRpt_status(1);
		cmt_rptVO1.setCmt_no("cmt00001");
		cmt_rptVO1.setMb_id("soowii123");
		dao.insert(cmt_rptVO1);

		//修改
		Cmt_rptVO cmt_rptVO2 = new Cmt_rptVO();
		cmt_rptVO2.setRpt_reason("他我可以");
		cmt_rptVO2.setRpt_status(1);
		cmt_rptVO2.setCmt_rpt_no("cmtr00006");
		dao.update(cmt_rptVO2);

		//刪除
		dao.delete("cmtr00006");

		//查詢
		Cmt_rptVO cmt_rptVO3 = dao.findByPrimaryKey("cmtr00001");
		System.out.print(cmt_rptVO3.getCmt_rpt_no() + ",");
		System.out.print(cmt_rptVO3.getRpt_reason() + ",");
		System.out.print(cmt_rptVO3.getRpt_status() + ",");
		System.out.print(cmt_rptVO3.getCmt_no() + ",");
		System.out.print(cmt_rptVO3.getMb_id() + "\n");
		System.out.println("---------------------");

		// 查詢getall
		List<Cmt_rptVO> list = dao.getAll();
		for (Cmt_rptVO cmt_rptVO : list) {
			System.out.print(cmt_rptVO.getCmt_rpt_no() + ",");
			System.out.print(cmt_rptVO.getRpt_reason() + ",");
			System.out.print(cmt_rptVO.getRpt_status() + ",");
			System.out.print(cmt_rptVO.getCmt_no() + ",");
			System.out.print(cmt_rptVO.getMb_id());

			System.out.println();
		}
	}
}
