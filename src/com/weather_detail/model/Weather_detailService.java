package com.weather_detail.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Weather_detailService {

	private Weather_detail_interface dao;

	public Weather_detailService() {
		dao = new Weather_detailDAO();
	}

	public Weather_detailVO addWeather_detail(Timestamp weather_time, String weather_place, String wth_status,
			Integer wth_high, Integer wth_low, String wth_comfort, Integer wth_rain_chance) {

		// 增
		Weather_detailVO weather_detailVO_insert = new Weather_detailVO();
		// WEATHER_TIME, WEATHER_PLACE, WTH_STATUS, WTH_HIGH, WTH_LOW, WTH_COMFORT,
		// WTH_RAIN_CHANCE
		weather_detailVO_insert.setWeather_time(weather_time);
//		weather_detailVO_insert.setWeather_time(java.sql.Timestamp.valueOf("2020-03-14 10:06:09"));
		weather_detailVO_insert.setWeather_place(weather_place);
		weather_detailVO_insert.setWth_status(wth_status);
		weather_detailVO_insert.setWth_high(wth_high);
		weather_detailVO_insert.setWth_low(wth_low);
		weather_detailVO_insert.setWth_comfort(wth_comfort);
		weather_detailVO_insert.setWth_rain_chance(wth_rain_chance);

		dao.insert(weather_detailVO_insert);
		return weather_detailVO_insert;
	}

	public Weather_detailVO updateWeather_detail(Timestamp weather_time, String weather_place, String wth_status,
			Integer wth_high, Integer wth_low, String wth_comfort, Integer wth_rain_chance) {

		// 改
		Weather_detailVO weather_detailVO_update = new Weather_detailVO();
		weather_detailVO_update.setWeather_time(weather_time);
		weather_detailVO_update.setWeather_place(weather_place);
		weather_detailVO_update.setWth_status(wth_status);
		weather_detailVO_update.setWth_high(wth_high);
		weather_detailVO_update.setWth_low(wth_low);
		weather_detailVO_update.setWth_comfort(wth_comfort);
		weather_detailVO_update.setWth_rain_chance(wth_rain_chance);

		dao.update(weather_detailVO_update);
		return weather_detailVO_update;
	}

	public void deleteWeather_detail(Timestamp weather_time, String weather_place) {
		// 刪
		dao.delete(weather_time, weather_place);
	}
	
	public void deleteAllWeather_detail() {
		// 刪
		dao.deleteAll();
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
		dao.getAll().forEach(System.out::println);
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
