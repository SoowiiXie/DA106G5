package com.thumb.model;

import java.util.List;

public interface ThumbDAO_interface {
	 public void insert(ThumbVO thumbVO);
     public void update(ThumbVO thumbVO);
     public void delete(String rcd_no, String mb_id);
     public ThumbVO findByPrimaryKey(String rcd_no, String mb_id);
     public List<ThumbVO> getAll();
}
