package com.metoo.model;

public class MeTooService {

	private MeToo_interface dao;

	public MeTooService() {
		dao = new MeTooDAO();
	}

	public MeTooVO addMeToo(String mb_id ,String rcd_no) {
		// 增
		MeTooVO cmt_rptVO_insert = new MeTooVO();
		cmt_rptVO_insert.setMb_id(mb_id);
//		cmtVO_insert.setCmt_time(java.sql.Date.valueOf("2020-01-01"));
		cmt_rptVO_insert.setRcd_no(rcd_no);
		dao.insert(cmt_rptVO_insert);
		return cmt_rptVO_insert;
	}

//	public Cmt_rptVO updateCmt_rpt(String cmt_rpt_no, String rpt_reason, Integer rpt_status, String cmt_no, String mb_id) {
//
//		// 改
//		Cmt_rptVO cmt_rptVO_update = new Cmt_rptVO();
//		cmt_rptVO_update.setRpt_reason(rpt_reason);
//		cmt_rptVO_update.setRpt_status(rpt_status);
//		cmt_rptVO_update.setCmt_rpt_no(cmt_rpt_no);
//		dao.update(cmt_rptVO_update);
//		cmt_rptVO_update.setCmt_no(cmt_no);
//		cmt_rptVO_update.setMb_id(mb_id);
//
//		return cmt_rptVO_update;
//	}

	//刪
	public void deleteMeToo(String mb_id ,String rcd_no) {
		dao.delete(mb_id , rcd_no);
	}

    public Integer countAllMeToos(String rcd_no) {
    	return dao.countAllMeToos(rcd_no);
    }
	
}
