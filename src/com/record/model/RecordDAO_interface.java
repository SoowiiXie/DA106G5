package com.record.model;

import java.util.List;
import java.util.Map;

public interface RecordDAO_interface {
	 public void insert(RecordVO recordVO);
     public void update(RecordVO RecordVO);
     public void delete(String rcd_no);
     public RecordVO findByPrimaryKey(String rcd_no);
     public List<RecordVO> getAll();
     // 萬用型
     public List<RecordVO> getAllUWish(Map<String, String[]> map, String orderBy);
}
