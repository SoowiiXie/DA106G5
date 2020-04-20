package com.grouper.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GrouperTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		GrouperPGDAO dao = new GrouperPGDAO();

		// 查全部
		System.out.println(System.getenv("DATABASE_URL"));
		List<GrouperVO> list = dao.getAll();
		for (GrouperVO aGrp : list) {
//			System.out.print(aGrp.getGrp_no() + ",");
//			System.out.print(aGrp.getMb_id() + ",");
			System.out.print(aGrp.getLoc_no() + ",");
//			System.out.print(aGrp.getGrp_applystart() + ",");
//			System.out.print(aGrp.getGrp_applyend() + ",");
//			System.out.print(aGrp.getGrp_start() + ",");
//			System.out.print(aGrp.getGrp_end());
//			System.out.print(aGrp.getGrp_name() + ",");
//			System.out.print(aGrp.getGrp_content() + ",");
//			System.out.print(aGrp.getGrp_personmax() + ",");
//			System.out.print(aGrp.getGrp_personmin() + ",");
//			System.out.print(aGrp.getGrp_personcount() + ",");
//			System.out.print(aGrp.getGrp_status() + ",");
//			System.out.print(aGrp.getGrp_follow());
			System.out.println();
		}
	}
}
