package com.location.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocationTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		LocationDAO dao = new LocationDAO();

		// 增 loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic
		LocationVO locationVO_insert = new LocationVO();
//		locationVO_insert.setLoc_no("loc00007");
		locationVO_insert.setLoc_typeno("3");
		locationVO_insert.setLongitude("123.456");
		locationVO_insert.setLatitude("78.90");
		locationVO_insert.setLoc_status(7);// 有給default
		locationVO_insert.setLoc_address("桃園");
		InputStream in;
//		FileInputStream in;
		BufferedInputStream bf;
		try {
			in = getServletContext().getResourceAsStream("/fake_picture/loc" + String.format("%05d", 6) + ".jpg");
//			in = new FileInputStream("/fake_picture/loc" + String.format("%05d", 6) + ".jpg");
			bf = new BufferedInputStream(in);
			byte[] image = new byte[bf.available()];// 讀入的圖檔,暫存在記憶體
			bf.read(image);
			locationVO_insert.setLoc_pic(image);
			bf.close();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.insert(locationVO_insert);

		// 改
		LocationVO locationVO_update = new LocationVO();
		locationVO_update.setLoc_no("loc00007");
		locationVO_update.setLoc_typeno("3");
		locationVO_update.setLongitude("123.456");
		locationVO_update.setLatitude("78.90");
		locationVO_update.setLoc_status(7);// 有給default
		locationVO_update.setLoc_address("全家就是我家");
		try {
			in = getServletContext().getResourceAsStream("/fake_picture/loc" + String.format("%05d", 6) + ".jpg");
			bf = new BufferedInputStream(in);
			byte[] image = new byte[bf.available()];// 讀入的圖檔,暫存在記憶體
			bf.read(image);
			locationVO_update.setLoc_pic(image);
			bf.close();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.update(locationVO_update);

		// 用PK查詢
		LocationVO locationVO_getByPK = dao.findByPrimaryKey("loc00007");
		System.out.println("新增並修改的PK是:" + locationVO_getByPK.getLoc_no());
		System.out.print("修改後的值是:");
		System.out.print(locationVO_getByPK.getLoc_typeno() + ",");
		System.out.print(locationVO_getByPK.getLongitude() + ",");
		System.out.print(locationVO_getByPK.getLatitude() + ",");
		System.out.print(locationVO_getByPK.getLoc_status() + ",");
		System.out.print(locationVO_getByPK.getLoc_address() + ",");
		System.out.println(locationVO_getByPK.getLoc_pic());

		// 刪
//		dao.delete("loc00007");

		// 查
		List<LocationVO> all = dao.getAll();
		System.out.println("\n刪掉剛剛新增的後剩:");
		for (LocationVO locationVO : all) {
			System.out.println(locationVO.getLoc_no() + "," + locationVO.getLoc_typeno() + ","
					+ locationVO.getLongitude() + "," + locationVO.getLatitude() + "," + locationVO.getLoc_status()
					+ "," + locationVO.getLoc_address() + "," + locationVO.getLoc_pic());
		}

		// 查
		Set<LocationVO> loc_typenoAll = dao.getLocationByLoc_typeno("1");
		System.out.println("\n取得所有Loc_typeno=1的地標:");
		for (LocationVO locationVO : loc_typenoAll) {
			System.out.println(locationVO.getLoc_no() + "," + locationVO.getLoc_typeno() + ","
					+ locationVO.getLongitude() + "," + locationVO.getLatitude() + "," + locationVO.getLoc_status()
					+ "," + locationVO.getLoc_address() + "," + locationVO.getLoc_pic());
		}
	}
}
