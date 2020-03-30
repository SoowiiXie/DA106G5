package com.weather.model;

import java.util.*;

public interface Weather_interface {
	public void insert(WeatherVO weatherVO);

	public void update(WeatherVO weatherVO);

	public void delete(String wth_status);

	public WeatherVO findByPrimaryKey(String wth_status);

	public List<WeatherVO> getAll();

}