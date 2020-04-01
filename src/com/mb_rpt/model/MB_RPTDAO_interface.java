package com.mb_rpt.model;

import java.util.List;

public interface MB_RPTDAO_interface {
	public void insert(MB_RPTVO mb_rptVO);
    public void update(MB_RPTVO mb_rptVO);
    public MB_RPTVO findByPrimaryKey(String mb_rpt_no);
    public List<MB_RPTVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
