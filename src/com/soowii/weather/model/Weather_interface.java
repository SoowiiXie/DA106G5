package com.soowii.weather.model;

import java.util.*;
import com.soowii.weather.model.WeatherVO;;

public interface Weather_interface {
	public void insert(WeatherVO weatherVO);

	public void update(WeatherVO weatherVO);

	public void delete(String wth_status);

	public WeatherVO findByPrimaryKey(String wth_status);

	public List<WeatherVO> getAll();

}