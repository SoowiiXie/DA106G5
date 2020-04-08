package com.cmt_rpt.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cmt_rptService {

	private Cmt_rpt_interface dao;

	public Cmt_rptService() {
		dao = new Cmt_rptDAO();
	}

	public Cmt_rptVO addCmt_rpt(String rpt_reason, String cmt_no, String mb_id) {
		// 增 cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id
		Cmt_rptVO cmt_rptVO_insert = new Cmt_rptVO();
		cmt_rptVO_insert.setRpt_reason(rpt_reason);
//		cmtVO_insert.setCmt_time(java.sql.Date.valueOf("2020-01-01"));
		cmt_rptVO_insert.setCmt_no(cmt_no);
		cmt_rptVO_insert.setMb_id(mb_id);
		dao.insert(cmt_rptVO_insert);

		return cmt_rptVO_insert;
	}

	public Cmt_rptVO updateCmt_rpt(String cmt_rpt_no, String rpt_reason, Integer rpt_status, String cmt_no, String mb_id) {

		// 改
		Cmt_rptVO cmt_rptVO_update = new Cmt_rptVO();
		cmt_rptVO_update.setRpt_reason(rpt_reason);
		cmt_rptVO_update.setRpt_status(rpt_status);
		cmt_rptVO_update.setCmt_rpt_no(cmt_rpt_no);
		dao.update(cmt_rptVO_update);
		cmt_rptVO_update.setCmt_no(cmt_no);
		cmt_rptVO_update.setMb_id(mb_id);

		return cmt_rptVO_update;
	}

	//不刪，只隱藏
//	public void deleteCmt() {}

	public Cmt_rptVO getOneCmt_rpt(String cmt_rpt_no) {
		return dao.findByPrimaryKey(cmt_rpt_no);
	}

	public List<Cmt_rptVO> getAll() {
		return dao.getAll();
	}
	
	public List<Cmt_rptVO> getByMb_id(String mb_id) {
		Map<String, String[]> map = new HashMap<>();
		String str = "'" + mb_id + "'";
		map.put("mb_id", new String[] { str });
		return dao.getAllUWish(map);
	}
	
}
