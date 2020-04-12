package com.mb_follow.model;

import java.util.List;

public interface Mb_followDAO_interface {
	
	
	public void insert(Mb_followVO Mb_followVO);
	public void insertByString(String mb_id,String md_id_followed);
	public void delete(Mb_followVO Mb_followVO);
	public void deleteByString(String mb_id,String md_id_followed);
	public List<Mb_followVO> findByMbId(String mb_id);
	public List<Mb_followVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<LiveVO> getAll(Map<String, String[]> map);

}
