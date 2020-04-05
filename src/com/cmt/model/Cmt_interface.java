package com.cmt.model;

import java.util.List;

public interface Cmt_interface {
	 public void insert(CmtVO cmtVO);
     public void update(CmtVO cmtVO);
     public void delete(String cmtVO);
     public CmtVO findByPrimaryKey(String cmt_no);
     public List<CmtVO> getAll();
}
