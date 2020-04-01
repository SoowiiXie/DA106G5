package com.ntf.model;

import java.util.List;

public interface NotifyDAO_interface {
	public void insert(NotifyVO notifyVO);
    public void update(NotifyVO notifyVO);
    public NotifyVO findByPrimaryKey(String ntf_no);
    public List<NotifyVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
