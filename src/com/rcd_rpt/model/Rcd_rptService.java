package com.rcd_rpt.model;

import java.util.List;

public class Rcd_rptService {

	private Rcd_rptDAO_interface dao;//為了框架所以使用介面多型宣告,可以做到0相依性

	public Rcd_rptService() {
		dao = new Rcd_rptJDBCDAO();
	}

	public Rcd_rptVO addRcd_rpt(String rpt_reason, String rcd_no, String mb_id) {

		Rcd_rptVO rcd_rptVO = new Rcd_rptVO();

		rcd_rptVO.setRpt_reason(rpt_reason);
		rcd_rptVO.setRcd_no(rcd_no);
		rcd_rptVO.setMb_id(mb_id);
		dao.insert(rcd_rptVO);

		return rcd_rptVO;
	}

	public Rcd_rptVO updateRcd_rpt(String rpt_reason, Integer rpt_status) {

		Rcd_rptVO rcd_rptVO = new Rcd_rptVO();

		rcd_rptVO.setRpt_reason(rpt_reason);
		rcd_rptVO.setRpt_status(rpt_status);
		dao.update(rcd_rptVO);

		return rcd_rptVO;
	}

	public void deleteRcd_rpt(String rcd_rpt_no) {
		dao.delete(rcd_rpt_no);
	}

	public Rcd_rptVO getOneRcd_rpt(String rcd_rpt_no) {
		return dao.findByPrimaryKey(rcd_rpt_no);
	}

	public List<Rcd_rptVO> getAll() {
		return dao.getAll();
	}
}
