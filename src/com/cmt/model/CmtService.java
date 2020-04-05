package com.cmt.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CmtService {

	private Cmt_interface dao;

	public CmtService() {
		dao = new CmtDAO();
	}

	public CmtVO addWeather_detail(String cmt_content, Date cmt_time, String rcd_no, String mb_id) {

		// 增 cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
		CmtVO cmtVO_insert = new CmtVO();
		cmtVO_insert.setCmt_content(cmt_content);
//		cmtVO_insert.setCmt_time(java.sql.Date.valueOf("2020-01-01"));
		cmtVO_insert.setCmt_time(cmt_time);
		cmtVO_insert.setRcd_no(rcd_no);
		cmtVO_insert.setMb_id(mb_id);
		dao.insert(cmtVO_insert);

		return cmtVO_insert;
	}

	public CmtVO updateWeather_detail(String cmt_content, Integer cmt_status, String cmt_no) {

		// 改
		CmtVO cmtVO_update = new CmtVO();
		cmtVO_update.setCmt_content(cmt_content);
		cmtVO_update.setCmt_status(cmt_status);
		cmtVO_update.setCmt_no(cmt_no);
		dao.update(cmtVO_update);

		return cmtVO_update;
	}

	public void deleteWeather_detail(Timestamp weather_time, String weather_place) {
		// 刪
		dao.delete(weather_time, weather_place);
	}

	public List<Weather_detailVO> getOneWeather_detail(Timestamp weather_time, String weather_place) {
		Map<String, String[]> map = new HashMap<>();
		map.put("WEATHER_PLACE", new String[] { "'" + weather_place + "'" });
//		方法一:切字串
//		String[] strRaw = weather_time.toString().split("\\.");
//		String str = "TO_TIMESTAMP('" + strRaw[0].toString() + "', 'YYYY-MM-DD HH24:MI:SS')";
//		方法二:設Format
		String str = "TO_TIMESTAMP('" + weather_time.toString() + "', 'YYYY-MM-DD HH24:MI:SS.FF')";
		map.put("WEATHER_TIME", new String[] { str });
//		map.put("WEATHER_TIME", new String[] { weather_time });
		return dao.getAllUWish(map);
	}

	public List<Weather_detailVO> getAll() {
		return dao.getAll();
	}

	public List<Weather_detailVO> getByWeather_place(String weather_place) {
		Map<String, String[]> map = new HashMap<>();
		String str = "'" + weather_place + "'";
		map.put("WEATHER_PLACE", new String[] { str });
		return dao.getAllUWish(map);
	}

	public List<Weather_detailVO> getByWeather_time(Timestamp weather_time) {
		Map<String, String[]> map = new HashMap<>();
		String str = "TO_TIMESTAMP('" + weather_time.toString() + "', 'YYYY-MM-DD HH24:MI:SS.FF')";
		map.put("WEATHER_TIME", new String[] { str });
		return dao.getAllUWish(map);
	}
}
