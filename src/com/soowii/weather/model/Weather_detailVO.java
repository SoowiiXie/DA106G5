package com.soowii.weather.model;

import java.sql.Timestamp;

public class Weather_detailVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timestamp weather_time;
	private String weather_place;
	private String wth_status;
	private Integer wth_high;
	private Integer wth_low;
	private String wth_comfort;
	private Integer wth_rain_chance;

	public Timestamp getWeather_time() {
		return weather_time;
	}

	public void setWeather_time(Timestamp weather_time) {
		this.weather_time = weather_time;
	}

	public String getWeather_place() {
		return weather_place;
	}

	public void setWeather_place(String weather_place) {
		this.weather_place = weather_place;
	}

	public String getWth_status() {
		return wth_status;
	}

	public void setWth_status(String wth_status) {
		this.wth_status = wth_status;
	}

	public Integer getWth_high() {
		return wth_high;
	}

	public void setWth_high(Integer wth_high) {
		this.wth_high = wth_high;
	}

	public Integer getWth_low() {
		return wth_low;
	}

	public void setWth_low(Integer wth_low) {
		this.wth_low = wth_low;
	}

	public String getWth_comfort() {
		return wth_comfort;
	}

	public void setWth_comfort(String wth_comfort) {
		this.wth_comfort = wth_comfort;
	}

	public Integer getWth_rain_chance() {
		return wth_rain_chance;
	}

	public void setWth_rain_chance(Integer wth_rain_chance) {
		this.wth_rain_chance = wth_rain_chance;
	}

}
