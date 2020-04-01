package com.auth.model;

import java.util.List;

public interface AuthorityDAO_interface {
	public void insert(AuthorityVO authorityVO);
    public void delete(String staff_id, String ability_no);
    public AuthorityVO findByPrimaryKey(String staff_id, String ability_no);
    public List<AuthorityVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
