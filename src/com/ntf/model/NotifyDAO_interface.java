package com.ntf.model;

import java.util.List;

public interface NotifyDAO_interface {
	public void insert(NotifyVO notifyVO);
    public void update(NotifyVO notifyVO);
    public NotifyVO findByPrimaryKey(String ntf_no);
    public List<NotifyVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
    public List<NotifyVO> getAllByMb_id(String mb_id);
    public Integer countNotReads(String mb_id);
    public List<NotifyVO> getAllByMb_idReallyAll(String mb_id);
    public void updateStatus(NotifyVO notifyVO);
}
