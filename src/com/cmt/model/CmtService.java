package com.cmt.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;

public class CmtService {

	private Cmt_interface dao;

	public CmtService() {
		dao = new CmtDAO();
	}

	public CmtVO addCmt(String cmt_content, Date cmt_time, String rcd_no, String mb_id) {
		// 增 cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id
		CmtVO cmtVO_insert = new CmtVO();
		cmtVO_insert.setCmt_content(cmt_content);
//		cmtVO_insert.setCmt_time(java.sql.Date.valueOf("2020-01-01"));
		cmtVO_insert.setCmt_time(cmt_time);
		cmtVO_insert.setRcd_no(rcd_no);
		cmtVO_insert.setMb_id(mb_id);
		dao.insert(cmtVO_insert);

		return cmtVO_insert;
	}

	public CmtVO updateCmt(String cmt_content, Integer cmt_status, String cmt_no, Date cmt_time, String rcd_no, String mb_id) {

		// 改
		CmtVO cmtVO_update = new CmtVO();
		cmtVO_update.setCmt_content(cmt_content);
		cmtVO_update.setCmt_status(cmt_status);
		cmtVO_update.setCmt_no(cmt_no);
		dao.update(cmtVO_update);
		cmtVO_update.setCmt_time(cmt_time);
		cmtVO_update.setRcd_no(rcd_no);
		cmtVO_update.setMb_id(mb_id);

		return cmtVO_update;
	}

	//不刪，只隱藏
//	public void deleteCmt() {}

	public CmtVO getOneCmt(String cmt_no) {
		return dao.findByPrimaryKey(cmt_no);
	}

	public List<CmtVO> getAll() {
		return dao.getAll();
	}
	
	public List<CmtVO> getByRcd_no(String rcd_no) {
		Map<String, String[]> map = new HashMap<>();
		String str = "'" + rcd_no + "'";
		map.put("rcd_no", new String[] { str });
		return dao.getAllUWish(map);
	}

	public List<CmtVO> getByMb_id(String mb_id) {
		Map<String, String[]> map = new HashMap<>();
		String str = "'" + mb_id + "'";
		map.put("mb_id", new String[] { str });
		return dao.getAllUWish(map);
	}
	
	public Integer countAllCmts(String rcd_no) {
    	return dao.countAllCmts(rcd_no);
    }

	public List<CmtVO> getByRcd_no(String rcd_no, Connection con) {
		Map<String, String[]> map = new HashMap<>();
		String str = "'" + rcd_no + "'";
		map.put("rcd_no", new String[] { str });
		return dao.getAllUWish(map, con);
	}
	
}
