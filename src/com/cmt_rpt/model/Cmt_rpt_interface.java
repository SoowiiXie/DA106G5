package com.cmt_rpt.model;

import java.util.List;
import java.util.Map;

public interface Cmt_rpt_interface {
	public void insert(Cmt_rptVO cmt_rptVO);

	public void update(Cmt_rptVO cmt_rptVO);

	public void delete(String cmt_rptVO);

	public Cmt_rptVO findByPrimaryKey(String cmt_rpt_no);

	public List<Cmt_rptVO> getAll();

	// 萬用型
	public List<Cmt_rptVO> getAllUWish(Map<String, String[]> map);
}
