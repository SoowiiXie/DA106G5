package com.location.model;

import java.util.*;

public interface Location_interface {
	public void insert(LocationVO locationVO);

	public void update(LocationVO locationVO);

	public void delete(String loc_no);

	public LocationVO findByPrimaryKey(String loc_no);

	public List<LocationVO> getAll();

	// 查詢某地點種類的地點(一對多)(回傳 Set)
	public Set<LocationVO> getLocationByLoc_typeno(String loc_typeno);
	
	// 萬用型
		public List<LocationVO> getAllUWish(Map<String, String[]> map, String orderBy);
}