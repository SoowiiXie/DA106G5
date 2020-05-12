package com.cmt.model;

import java.util.List;
import java.util.Map;
import java.sql.Connection;

public interface Cmt_interface {
	public void insert(CmtVO cmtVO);

	public void update(CmtVO cmtVO);

	public void delete(String cmtVO);

	public CmtVO findByPrimaryKey(String cmt_no);

	public List<CmtVO> getAll();
	
	public Integer countAllCmts(String rcd_no);

	// 萬用型
	public List<CmtVO> getAllUWish(Map<String, String[]> map);
	
	public List<android.com.cmt.model.CmtVO> getAllUWish(Map<String, String[]> map, Connection con);
}
