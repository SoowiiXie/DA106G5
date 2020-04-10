package com.loc_rpt.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Loc_rptTest extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Loc_rptDAO dao = new Loc_rptDAO();

		 //新增
		Loc_rptVO cmt_rptVO1 = new Loc_rptVO();
		cmt_rptVO1.setRpt_reason("這個我不行!!!!!!");
		cmt_rptVO1.setRpt_status(1);
		cmt_rptVO1.setLoc_no("loc00001");
		cmt_rptVO1.setMb_id("soowii123");
		dao.insert(cmt_rptVO1);

		//修改
		Loc_rptVO cmt_rptVO2 = new Loc_rptVO();
		cmt_rptVO2.setRpt_reason("他我可以");
		cmt_rptVO2.setRpt_status(1);
		cmt_rptVO2.setLoc_rpt_no("locr00006");
		dao.update(cmt_rptVO2);

		//刪除
		dao.delete("cmtr00006");

		//查詢
		Loc_rptVO cmt_rptVO3 = dao.findByPrimaryKey("cmtr00001");
		System.out.print(cmt_rptVO3.getLoc_rpt_no() + ",");
		System.out.print(cmt_rptVO3.getRpt_reason() + ",");
		System.out.print(cmt_rptVO3.getRpt_status() + ",");
		System.out.print(cmt_rptVO3.getLoc_no() + ",");
		System.out.print(cmt_rptVO3.getMb_id() + "\n");
		System.out.println("---------------------");

		// 查詢getall
		List<Loc_rptVO> list = dao.getAll();
		for (Loc_rptVO cmt_rptVO : list) {
			System.out.print(cmt_rptVO.getLoc_rpt_no() + ",");
			System.out.print(cmt_rptVO.getRpt_reason() + ",");
			System.out.print(cmt_rptVO.getRpt_status() + ",");
			System.out.print(cmt_rptVO.getLoc_no() + ",");
			System.out.print(cmt_rptVO.getMb_id());

			System.out.println();
		}
	}
}
