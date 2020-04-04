package com.weather_detail.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Weather_detailTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Weather_detailDAO dao = new Weather_detailDAO();

		// 增
		Weather_detailVO weather_detailVO_insert = new Weather_detailVO();
		// WEATHER_TIME, WEATHER_PLACE, WTH_STATUS, WTH_HIGH, WTH_LOW, WTH_COMFORT,
		// WTH_RAIN_CHANCE
		weather_detailVO_insert.setWeather_time(java.sql.Timestamp.valueOf("2020-03-14 10:06:09"));
		weather_detailVO_insert.setWeather_place("健志愛的小窩");
		weather_detailVO_insert.setWth_status("晴時多雲偶陣雨");
		weather_detailVO_insert.setWth_high(69);
		weather_detailVO_insert.setWth_low(10);
		weather_detailVO_insert.setWth_comfort("舒服");
		weather_detailVO_insert.setWth_rain_chance(100);

		dao.insert(weather_detailVO_insert);

		// 改
		Weather_detailVO weather_detailVO_update = new Weather_detailVO();
		weather_detailVO_update.setWeather_time(java.sql.Timestamp.valueOf("2020-03-14 10:06:09"));
		weather_detailVO_update.setWeather_place("健志愛的小窩");
		weather_detailVO_update.setWth_status("晴時多雲偶陣雨");
		weather_detailVO_update.setWth_high(69);
		weather_detailVO_update.setWth_low(10);
		weather_detailVO_update.setWth_comfort("超級機掰爽得要命");
		weather_detailVO_update.setWth_rain_chance(100);

		dao.update(weather_detailVO_update);

		// 用PK查詢
		Weather_detailVO weather_detailVO_getByPK = dao
				.findByPrimaryKey(java.sql.Timestamp.valueOf("2020-03-14 10:06:09"), "健志愛的小窩");
		System.out.println("新增並修改的PK是:" + weather_detailVO_getByPK.getWeather_time() + "的"
				+ weather_detailVO_getByPK.getWeather_place());
		System.out.print("修改後的值是:");
		// WEATHER_TIME, WEATHER_PLACE, WTH_STATUS, WTH_HIGH, WTH_LOW, WTH_COMFORT,
		// WTH_RAIN_CHANCE
		System.out.print(weather_detailVO_getByPK.getWth_status() + ",");
		System.out.print(weather_detailVO_getByPK.getWth_high() + ",");
		System.out.print(weather_detailVO_getByPK.getWth_low() + ",");
		System.out.print(weather_detailVO_getByPK.getWth_comfort() + ",");
		System.out.print(weather_detailVO_getByPK.getWth_rain_chance());

		// 刪
		dao.delete(java.sql.Timestamp.valueOf("2020-03-14 10:06:09"), "健志愛的小窩");

		// 查
		List<Weather_detailVO> all = dao.getAll();
		System.out.println("\n刪掉剛剛新增的後剩:");
		// WEATHER_TIME, WEATHER_PLACE, WTH_STATUS, WTH_HIGH, WTH_LOW, WTH_COMFORT,
		// WTH_RAIN_CHANCE
		for (Weather_detailVO weather_detailVO : all) {
			System.out.println(weather_detailVO.getWeather_time() + "," + weather_detailVO.getWeather_place() + ","
					+ weather_detailVO.getWth_status() + "," + weather_detailVO.getWth_high() + ","
					+ weather_detailVO.getWth_low() + "," + weather_detailVO.getWth_comfort() + ","
					+ weather_detailVO.getWth_rain_chance());
		}

		// 查
		Map<String, String[]> map = new HashMap<>();
		map.put("WEATHER_PLACE", new String[] { "'桃園市'", "'高雄市'", "'健志愛的小窩'" });
		map.put("WTH_STATUS", new String[] { "'晴'", "'颱風'", "'愛情來的太快就像龍捲風'", "'晴時多雲偶陣雨'" });
		map.put("WEATHER_TIME", new String[] { "TO_TIMESTAMP('2020-03-14 06:00:00', 'YYYY-MM-DD HH24:MI:SS')",
				"TO_TIMESTAMP('2020-03-14 18:00:00', 'YYYY-MM-DD HH24:MI:SS')" });
		System.out.println("getAllUWish(map)：");
		List<Weather_detailVO> all_map = dao.getAllUWish(map);
		for (Weather_detailVO weather_detailVO_map : all_map) {
			System.out.println(weather_detailVO_map.getWeather_time() + "," + weather_detailVO_map.getWeather_place()
					+ "," + weather_detailVO_map.getWth_status() + "," + weather_detailVO_map.getWth_high() + ","
					+ weather_detailVO_map.getWth_low() + "," + weather_detailVO_map.getWth_comfort() + ","
					+ weather_detailVO_map.getWth_rain_chance());
		}
	}
}
