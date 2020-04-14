package com.abl.model;

import java.util.List;
import java.util.Map;

public interface AbilityDAO_interface {
	public void insert(AbilityVO abilityVO);
    public void update(AbilityVO abilityVO);
    public void delete(String ability_no);
    public AbilityVO findByPrimaryKey(String ability_no);
    public List<AbilityVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    
    // 取得全部權限，用MAP傳回
    public Map<String,String> getAllToMap();
}
