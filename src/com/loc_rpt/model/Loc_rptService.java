package com.loc_rpt.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loc_rptService {

	private Loc_rpt_interface dao;

	public Loc_rptService() {
		dao = new Loc_rptDAO();
	}

	public Loc_rptVO addLoc_rpt(String rpt_reason, String loc_no, String mb_id) {
		// 增
		Loc_rptVO loc_rptVO_insert = new Loc_rptVO();
		loc_rptVO_insert.setRpt_reason(rpt_reason);
//		cmtVO_insert.setCmt_time(java.sql.Date.valueOf("2020-01-01"));
		loc_rptVO_insert.setLoc_no(loc_no);
		loc_rptVO_insert.setMb_id(mb_id);
		dao.insert(loc_rptVO_insert);

		return loc_rptVO_insert;
	}

	public Loc_rptVO updateLoc_rpt(String loc_rpt_no, String rpt_reason, Integer rpt_status, String loc_no, String mb_id) {

		// 改
		Loc_rptVO loc_rptVO_update = new Loc_rptVO();
		loc_rptVO_update.setRpt_reason(rpt_reason);
		loc_rptVO_update.setRpt_status(rpt_status);
		loc_rptVO_update.setLoc_rpt_no(loc_rpt_no);
		dao.update(loc_rptVO_update);
		loc_rptVO_update.setLoc_no(loc_no);
		loc_rptVO_update.setMb_id(mb_id);

		return loc_rptVO_update;
	}
	
	public Loc_rptVO updateLoc_rptByLocNo(String loc_rpt_no, String rpt_reason, Integer rpt_status, String loc_no, String mb_id) {
		// 改
		Loc_rptVO loc_rptVO_update = new Loc_rptVO();
		loc_rptVO_update.setRpt_status(rpt_status);
		loc_rptVO_update.setLoc_no(loc_no);
		dao.updateByLocNo(loc_rptVO_update);
		loc_rptVO_update.setRpt_reason(rpt_reason);
		loc_rptVO_update.setLoc_rpt_no(loc_rpt_no);
		loc_rptVO_update.setMb_id(mb_id);
		
		return loc_rptVO_update;
	}

	//不刪，只隱藏
//	public void deleteCmt() {}

	public Loc_rptVO getOneLoc_rpt(String loc_rpt_no) {
		return dao.findByPrimaryKey(loc_rpt_no);
	}

	public List<Loc_rptVO> getAll() {
		return dao.getAll();
	}
	
	public List<Loc_rptVO> getByMb_id(String mb_id) {
		Map<String, String[]> map = new HashMap<>();
		map.put("mb_id", new String[] { mb_id });
		return dao.getAllUWish(map,"");
	}
	
}
