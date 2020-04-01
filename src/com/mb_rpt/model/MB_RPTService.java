package com.mb_rpt.model;

import java.util.List;

public class MB_RPTService {

private MB_RPTDAO_interface dao;
	
	public MB_RPTService() {
		dao = new MB_RPTDAO();
	}
	
	public MB_RPTVO addMb_rpt(String mb_id_1, String mb_id_2, String rpt_reason) {
		MB_RPTVO mb_rptVO = new MB_RPTVO();
		mb_rptVO.setMb_id_1(mb_id_1);
		mb_rptVO.setMb_id_2(mb_id_2);
		mb_rptVO.setRpt_reason(rpt_reason);
		dao.insert(mb_rptVO);
		return mb_rptVO;
	}
	
	public MB_RPTVO updateMb_rpt(String mb_rpt_no, String mb_id_1, String mb_id_2, String rpt_reason, Integer rpt_status) {
		MB_RPTVO mb_rptVO = new MB_RPTVO();
		mb_rptVO.setMb_rpt_no(mb_rpt_no);
		mb_rptVO.setMb_id_1(mb_id_1);
		mb_rptVO.setMb_id_2(mb_id_2);
		mb_rptVO.setRpt_reason(rpt_reason);
		mb_rptVO.setRpt_status(rpt_status);
		dao.update(mb_rptVO);
		return mb_rptVO;
	}

	public MB_RPTVO getOneAuthority(String mb_rpt_no) {
		return dao.findByPrimaryKey(mb_rpt_no);
	}

	public List<MB_RPTVO> getAll() {
		return dao.getAll();
	}
	
}
