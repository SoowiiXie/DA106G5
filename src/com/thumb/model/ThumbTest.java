package com.thumb.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ThumbTest extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ThumbDAO dao = new ThumbDAO();

		//新增
		ThumbVO thumbVO1 = new ThumbVO();
		thumbVO1.setRcd_no("rcd00001");
		thumbVO1.setMb_id("yiwen123");
		dao.insert(thumbVO1);
		thumbVO1.setRcd_no("rcd00001");
		thumbVO1.setMb_id("xuan123");
		dao.insert(thumbVO1);
		thumbVO1.setRcd_no("rcd00001");
		thumbVO1.setMb_id("michael123");
		dao.insert(thumbVO1);
		thumbVO1.setRcd_no("rcd00001");
		thumbVO1.setMb_id("vain123");
		dao.insert(thumbVO1);
		thumbVO1.setRcd_no("rcd00001");
		thumbVO1.setMb_id("weijhih123");
		dao.insert(thumbVO1);


		//修改
//		ThumbVO thumbVO2 = new ThumbVO();
//		thumbVO2.setRcd_no("rcd00005");
//		thumbVO2.setMb_id("yiwen123");
////		thumbVO2.setMb_id("yiwen123");
////		不用這行就可以同時代表兩個Mb_id
//		dao.update(thumbVO2);

		//刪除
//		dao.delete("rcd00005", "soowii123");
		System.out.println(dao.countAllThumb("rcd00001"));
		
		//查詢
//		ThumbVO thumbVO3 = dao.findByPrimaryKey("rcd00001", "soowii123");
//		System.out.print(thumbVO3.getRcd_no() + ",");
//		System.out.print(thumbVO3.getMb_id() + "\n");
//		System.out.println("---------------------");

		//查詢getall
//		List<ThumbVO> list = dao.getAll();
//		for (ThumbVO thumbVO : list) {
//			System.out.print(thumbVO.getRcd_no() + ",");
//			System.out.print(thumbVO.getMb_id());
//			System.out.println();
//		}
	}
}
