package com.xuan.que_rpt.model;

import java.util.List;

public interface Question_RPTDAO_interface {
	public void insert(Question_RPTVO qusetion_rptVO);
    public void update(Question_RPTVO qusetion_rptVO);
    public Question_RPTVO findByPrimaryKey(String question_no);
    public List<Question_RPTVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
