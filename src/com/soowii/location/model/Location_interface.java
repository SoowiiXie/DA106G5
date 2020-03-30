package com.soowii.location.model;

import java.util.*;
import com.soowii.location.model.LocationVO;

public interface Location_interface {
	public void insert(LocationVO locationVO);

	public void update(LocationVO locationVO);

	public void delete(String loc_no);

	public LocationVO findByPrimaryKey(String loc_no);

	public List<LocationVO> getAll();

	// 查詢某地點種類的地點(一對多)(回傳 Set)
	public Set<LocationVO> getLocationByLoc_typeno(String loc_typeno);
}