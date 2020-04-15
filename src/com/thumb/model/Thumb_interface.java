package com.thumb.model;

public interface Thumb_interface {
	 public void insert(ThumbVO thumbVO);
//     public void update(ThumbVO thumbVO);
     public void delete(String rcd_no, String mb_id);
//     public ThumbVO findByPrimaryKey(String rcd_no, String mb_id);
//     public List<ThumbVO> getAll();
	 public Integer countAllThumbs(String rcd_no);
	 public Integer countOneThumb(String rcd_no, String mb_id);
}
