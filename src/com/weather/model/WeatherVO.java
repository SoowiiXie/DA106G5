package com.weather.model;

public class WeatherVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wth_status;
	private byte[] weather_pic;

	public String getWth_status() {
		return wth_status;
	}

	public void setWth_status(String wth_status) {
		this.wth_status = wth_status;
	}

	public byte[] getWeather_pic() {
		return weather_pic;
	}

	public void setWeather_pic(byte[] weather_pic) {
		this.weather_pic = weather_pic;
	}

}
