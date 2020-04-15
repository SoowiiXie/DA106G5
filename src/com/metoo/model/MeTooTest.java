package com.metoo.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MeTooTest extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MeTooDAO dao = new MeTooDAO();

		//新增
//		MeTooVO meTooVO1 = new MeTooVO();
//		meTooVO1.setRcd_no("rcd00001");
//		meTooVO1.setMb_id("weijhih123");
//		dao.insert(meTooVO1);
//		meTooVO1.setRcd_no("rcd00001");
//		meTooVO1.setMb_id("xuan123");
//		dao.insert(meTooVO1);
//		meTooVO1.setRcd_no("rcd00001");
//		meTooVO1.setMb_id("michael123");
//		dao.insert(meTooVO1);
//		meTooVO1.setRcd_no("rcd00001");
//		meTooVO1.setMb_id("vain123");
//		dao.insert(meTooVO1);
//		meTooVO1.setRcd_no("rcd00001");
//		meTooVO1.setMb_id("yiwen123");
//		dao.insert(meTooVO1);

		//修改??
//		MeTooVO meTooVO2 = new MeTooVO();
//		meTooVO2.setRcd_no("rcd00004");
//		meTooVO2.setMb_id("vain123");
//		meTooVO2.setMb_id("vain123");
//		dao.update(meTooVO2);

		//刪除
//		dao.delete("soowii123", "rcd00005");
		System.out.println(dao.countAllMeToos("rcd00001"));
		System.out.println(dao.countOneMeToo("rcd00001","soowii123"));

		//查詢
//		MeTooVO meTooVO3 = dao.findByPrimaryKey("rcd00001", "soowii123");
//		if (meTooVO3 != null) {
//			System.out.print(meTooVO3.getMb_id() + ",");
//			System.out.print(meTooVO3.getRcd_no());
//			System.out.println();
//		} else {
//			System.out.println("沒有找到資料");
//		}

		//查詢getall
//		List<MeTooVO> list = dao.getAll();
//		for (MeTooVO meTooVO : list) {
//			System.out.print(meTooVO.getRcd_no() + ",");
//			System.out.print(meTooVO.getMb_id()+"\n");
//			System.out.println();
//		}
	}
}
