package com.mb.model;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public void updateLine(MemberVO memberVO);
    public MemberVO findByPrimaryKey(String mb_id);
    public List<MemberVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    
    public String addOneToRptTime(String mb_id);  // 被檢舉次數+1
    // public Set<MemberVO> searchByName(String mb_name);
}
