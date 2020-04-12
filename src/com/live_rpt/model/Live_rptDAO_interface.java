package com.live_rpt.model;

import java.util.List;
import java.util.Map;


public interface Live_rptDAO_interface {
	
	public void insert(Live_rptVO Live_rptVO);

	public void update(Live_rptVO Live_rptVO);

	public void updateByCmtNo(Live_rptVO Live_rptVO);
	
	public String getRptedMb_id(String live_rpt_no);

	public void delete(String live_rpt_no);

	public Live_rptVO findByPrimaryKey(String live_rpt_no);

	public List<Live_rptVO> getAll();

	// 萬用型
	public List<Live_rptVO> getAllUWish(Map<String, String[]> map);
	
	

}
