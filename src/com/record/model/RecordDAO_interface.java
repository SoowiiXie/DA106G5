package com.record.model;

import java.util.List;

public interface RecordDAO_interface {
	 public void insert(RecordVO recordVO);
     public void update(RecordVO RecordVO);
     public void delete(String rcd_no);
     public RecordVO findByPrimaryKey(String rcd_no);
     public List<RecordVO> getAll();
}
