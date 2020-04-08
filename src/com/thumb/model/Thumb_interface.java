package com.thumb.model;

public interface Thumb_interface {
	 public void insert(ThumbVO thumbVO);
//     public void update(ThumbVO thumbVO);
     public void delete(String rcd_no, String mb_id);
//     public ThumbVO findByPrimaryKey(String rcd_no, String mb_id);
//     public List<ThumbVO> getAll();
	 // 萬用型
	 public Integer countAllThumb(String rcd_no);
}
