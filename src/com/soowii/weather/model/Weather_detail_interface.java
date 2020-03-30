package com.soowii.weather.model;

import java.sql.Timestamp;
import java.util.*;
import com.soowii.weather.model.Weather_detailVO;;

public interface Weather_detail_interface {
	public void insert(Weather_detailVO weather_detailVO);

	public void update(Weather_detailVO weather_detailVO);

	public void delete(Timestamp weather_time, String weather_place);

	public Weather_detailVO findByPrimaryKey(Timestamp weather_time, String weather_place);

	public List<Weather_detailVO> getAll();

	// 查詢某部門的員工(一對多)(回傳 Set)
//	public Set<Weather_detailVO> getWeatherByWeather_time(Timestamp weather_time);
//
//	public Set<Weather_detailVO> getWeatherByWeather_place(String weather_place);
	
	// 萬用型
	public List<Weather_detailVO> getAll(Map<String, String[]> map);
}