package com.grouper.model;

import java.util.List;

public class GrouperService {

	private GrouperDAO_interface dao;

	public GrouperService() {
		dao = new GrouperDAO();
	}

	public GrouperVO addGrouper(String Grp_no, String Mb_id, String Loc_no, java.sql.Date Grp_applystart, 
			java.sql.Date Grp_applyend, java.sql.Date Grp_start, java.sql.Date Grp_end, String Grp_name, 
			String Grp_content, Integer Grp_personmax, Integer Grp_personmin, Integer Grp_personcount, 
			Integer Grp_status, Integer Grp_follow) {

		GrouperVO grouperVO = new GrouperVO();

		grouperVO.setGrp_no(Grp_no);
		grouperVO.setMb_id(Mb_id);
		grouperVO.setLoc_no(Loc_no);
		grouperVO.setGrp_applystart(Grp_applystart);
		grouperVO.setGrp_applyend(Grp_applyend);
		grouperVO.setGrp_start(Grp_start);
		grouperVO.setGrp_end(Grp_end);
		grouperVO.setGrp_name(Grp_name);
		grouperVO.setGrp_content(Grp_content);
		grouperVO.setGrp_personmax(Grp_personmax);
		grouperVO.setGrp_personmin(Grp_personmin);
		grouperVO.setGrp_personcount(Grp_personcount);
		grouperVO.setGrp_status(Grp_status);
		grouperVO.setGrp_follow(Grp_follow);
		dao.insert(grouperVO);

		return grouperVO;
	}

	public GrouperVO updateGrouper(String Grp_no, String Mb_id, String Loc_no, java.sql.Date Grp_applystart, 
			java.sql.Date Grp_applyend, java.sql.Date Grp_start, java.sql.Date Grp_end, String Grp_name, 
			String Grp_content, Integer Grp_personmax, Integer Grp_personmin, Integer Grp_personcount, 
			Integer Grp_status, Integer Grp_follow)  {

		GrouperVO grouperVO = new GrouperVO();

		grouperVO.setGrp_no(Grp_no);
		grouperVO.setMb_id(Mb_id);
		grouperVO.setLoc_no(Loc_no);
		grouperVO.setGrp_applystart(Grp_applystart);
		grouperVO.setGrp_applyend(Grp_applyend);
		grouperVO.setGrp_start(Grp_start);
		grouperVO.setGrp_end(Grp_end);
		grouperVO.setGrp_name(Grp_name);
		grouperVO.setGrp_content(Grp_content);
		grouperVO.setGrp_personmax(Grp_personmax);
		grouperVO.setGrp_personmin(Grp_personmin);
		grouperVO.setGrp_personcount(Grp_personcount);
		grouperVO.setGrp_status(Grp_status);
		grouperVO.setGrp_follow(Grp_follow);
		dao.update(grouperVO);

		return grouperVO;
	}

	public void deleteGrouper(String Grp_no) {
		dao.delete(Grp_no);
	}

	public GrouperVO getOneGrouper(String Grp_no) {
		return dao.findByPrimaryKey(Grp_no);
	}

	public List<GrouperVO> getAll() {
		return dao.getAll();
	}
}
