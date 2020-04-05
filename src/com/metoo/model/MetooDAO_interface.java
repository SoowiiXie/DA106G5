package com.metoo.model;

import java.util.List;

public interface MetooDAO_interface {
	 public void insert(MetooVO metooVO);
     public void update(MetooVO metooVO);
     public void delete(String mb_id, String rcd_no);
     public MetooVO findByPrimaryKey(String rcd_no, String mb_id);
     public List<MetooVO> getAll();
}
