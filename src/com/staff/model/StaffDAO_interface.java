package com.staff.model;

import java.util.List;

public interface StaffDAO_interface {
	public void insert(StaffVO staffVO);
    public void update(StaffVO staffVO);
    public StaffVO findByPrimaryKey(String staff_id);
    public List<StaffVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
