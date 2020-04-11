package com.loc_rpt.model;

import java.util.List;
import java.util.Map;

public interface Loc_rpt_interface {
	public void insert(Loc_rptVO loc_rptVO);

	public void update(Loc_rptVO loc_rptVO);
	
	public void updateByLocNo(Loc_rptVO loc_rptVO);

	public void delete(String loc_rpt_no);

	public Loc_rptVO findByPrimaryKey(String loc_rpt_no);

	public List<Loc_rptVO> getAll();

	// 萬用型
	public List<Loc_rptVO> getAllUWish(Map<String, String[]> map, String orderBy);
}
