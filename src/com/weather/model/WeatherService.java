package com.weather.model;

import java.util.List;

public class WeatherService {

	private Weather_interface dao;

	public WeatherService() {
		dao = new WeatherDAO();
	}

	public List<WeatherVO> getAll() {
		return dao.getAll();
	}
}
