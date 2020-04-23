package com.location.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.*;

public class LocationService {

	private Location_interface dao;

	public LocationService() {
		dao = new LocationDAO();
	}

	public LocationVO addLocation(String loc_typeno, String longitude, String latitude, Integer loc_status,
			String loc_address, byte[] loc_pic) {

		// 增 loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic
		LocationVO locationVO_insert = new LocationVO();
//				locationVO_insert.setLoc_no("loc00007");
		locationVO_insert.setLoc_typeno(loc_typeno);
		locationVO_insert.setLongitude(longitude);
		locationVO_insert.setLatitude(latitude);
		locationVO_insert.setLoc_status(loc_status);// 有給default
		locationVO_insert.setLoc_address(loc_address);
//		InputStream in;
//				FileInputStream in;
//		BufferedInputStream bf;
//		try {
//			in = getServletContext().getResourceAsStream("/fake_picture/loc" + String.format("%05d", 6) + ".jpg");
////					in = new FileInputStream("/fake_picture/loc" + String.format("%05d", 6) + ".jpg");
//			bf = new BufferedInputStream(in);
//			byte[] image = new byte[bf.available()];// 讀入的圖檔,暫存在記憶體
//			bf.read(loc_pic);
			locationVO_insert.setLoc_pic(loc_pic);
//			bf.close();
//			in.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		dao.insert(locationVO_insert);
		return locationVO_insert;
	}

	public LocationVO updateLocation(String loc_no, String loc_typeno, String longitude, String latitude, Integer loc_status,
			String loc_address, byte[] loc_pic) {

		// 改 loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic
		LocationVO locationVO_update = new LocationVO();
		locationVO_update.setLoc_no(loc_no);
		locationVO_update.setLoc_typeno(loc_typeno);
		locationVO_update.setLongitude(longitude);
		locationVO_update.setLatitude(latitude);
		locationVO_update.setLoc_status(loc_status);// 有給default
		locationVO_update.setLoc_address(loc_address);
//		try {
//			in = getServletContext().getResourceAsStream("/fake_picture/loc" + String.format("%05d", 6) + ".jpg");
//			bf = new BufferedInputStream(in);
//			byte[] image = new byte[bf.available()];// 讀入的圖檔,暫存在記憶體
//			bf.read(loc_pic);
			locationVO_update.setLoc_pic(loc_pic);
//			bf.close();
//			in.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		dao.update(locationVO_update);
		return locationVO_update;
	}

	public void deleteLocation(String loc_no) {
		dao.delete(loc_no);
	}

	public LocationVO getOneLocation(String loc_no) {
		return dao.findByPrimaryKey(loc_no);
	}

	public List<LocationVO> getAll() {
		return dao.getAll();
	}
	 
	public JSONArray getAllJSON() {
		return new JSONArray(dao.getAllJSON());
	}
	
	public List<LocationVO> getByLoc_typeno(String[] loc_typeno, String orderBy) {
		Map<String, String[]> map = new HashMap<>();
		map.put("loc_typeno", loc_typeno);
		return dao.getAllUWish(map,orderBy);
	}
	
	public List<LocationVO> getByLoc_typeno2(String loc_typeno, String orderBy) {
		Map<String, String[]> map = new HashMap<>();
		map.put("loc_typeno", new String[] { loc_typeno });
		return dao.getAllUWish(map,orderBy);
	}
}
