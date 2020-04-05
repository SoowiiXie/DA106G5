package com.group_rpt.model;

import java.util.List;

public class Group_rptService {

	private Group_rptDAO_interface dao;

	public Group_rptService() {
		dao = new Group_rptDAO();
	}

	public Group_rptVO addGroup_rpt(String grp_no, String mb_id, String rpt_reason,
			Integer rpt_status) {

		Group_rptVO group_rptVO = new Group_rptVO();

		group_rptVO.setGrp_no(grp_no);
		group_rptVO.setMb_id(mb_id);
		group_rptVO.setRpt_reason(rpt_reason);
		group_rptVO.setRpt_status(rpt_status);

		dao.insert(group_rptVO);

		return group_rptVO;
	}

	public Group_rptVO updateGroup_rpt(String group_rpt_no, String grp_no, String mb_id, String rpt_reason,
			Integer rpt_status) {

		Group_rptVO group_rptVO = new Group_rptVO();

		group_rptVO.setGroup_rpt_no(group_rpt_no);
		group_rptVO.setGrp_no(grp_no);
		group_rptVO.setMb_id(mb_id);
		group_rptVO.setRpt_reason(rpt_reason);
		group_rptVO.setRpt_status(rpt_status);
		dao.update(group_rptVO);

		return group_rptVO;
	}

	public void deleteGroup_rpt(String group_rpt_no) {
		dao.delete(group_rpt_no);
	}

	public Group_rptVO getOneGroup_rpt(String group_rpt_no) {
		return dao.findByPrimaryKey(group_rpt_no);
	}

	public List<Group_rptVO> getAll() {
		return dao.getAll();
	}
}
