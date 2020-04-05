package com.metoo.model;

import java.util.List;

public interface MeToo_interface {
	 public void insert(MeTooVO meTooVO);
     public void update(MeTooVO meTooVO);
     public void delete(String mb_id, String rcd_no);
     public MeTooVO findByPrimaryKey(String rcd_no, String mb_id);
     public List<MeTooVO> getAll();
}
