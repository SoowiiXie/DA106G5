package com.grouper.model;

import java.util.List;

public class GrouperService {

	private GrouperDAO_interface dao;

	public GrouperService() {
		dao = new GrouperDAO();
	}

	public GrouperVO addGroup(String grp_no, String mb_id, String loc_no, java.sql.Timestamp grp_applystart, 
			java.sql.Timestamp grp_applyend, java.sql.Timestamp grp_start, java.sql.Timestamp grp_end, String grp_name, 
			String grp_content, Integer grp_personmax, Integer grp_personmin, Integer grp_personcount, 
			Integer grp_status, Integer grp_follow) {

		GrouperVO grouperVO = new GrouperVO();

		grouperVO.setGrp_no(grp_no);
		grouperVO.setMb_id(mb_id);
		grouperVO.setLoc_no(loc_no);
		grouperVO.setGrp_applystart(grp_applystart);
		grouperVO.setGrp_applyend(grp_applyend);
		grouperVO.setGrp_start(grp_start);
		grouperVO.setGrp_end(grp_end);
		grouperVO.setGrp_name(grp_name);
		grouperVO.setGrp_content(grp_content);
		grouperVO.setGrp_personmax(grp_personmax);
		grouperVO.setGrp_personmin(grp_personmin);
		grouperVO.setGrp_personcount(grp_personcount);
		grouperVO.setGrp_status(grp_status);
		grouperVO.setGrp_follow(grp_follow);
		dao.insert(grouperVO);

		return grouperVO;
	}

	public GrouperVO updateGroup(String grp_no, String mb_id, String loc_no, java.sql.Timestamp grp_applystart, 
			java.sql.Timestamp grp_applyend, java.sql.Timestamp grp_start, java.sql.Timestamp grp_end, String grp_name, 
			String grp_content, Integer grp_personmax, Integer grp_personmin, Integer grp_personcount, 
			Integer grp_status, Integer grp_follow) {

		GrouperVO grouperVO = new GrouperVO();

		grouperVO.setGrp_no(grp_no);
		grouperVO.setMb_id(mb_id);
		grouperVO.setLoc_no(loc_no);
		grouperVO.setGrp_applystart(grp_applystart);
		grouperVO.setGrp_applyend(grp_applyend);
		grouperVO.setGrp_start(grp_start);
		grouperVO.setGrp_end(grp_end);
		grouperVO.setGrp_name(grp_name);
		grouperVO.setGrp_content(grp_content);
		grouperVO.setGrp_personmax(grp_personmax);
		grouperVO.setGrp_personmin(grp_personmin);
		grouperVO.setGrp_personcount(grp_personcount);
		grouperVO.setGrp_status(grp_status);
		grouperVO.setGrp_follow(grp_follow);
		dao.update(grouperVO);

		return grouperVO;
	}

	public void deleteGroup(String grp_no) {
		dao.delete(grp_no);
	}

	public GrouperVO getOneGroup(String grp_no) {
		return dao.findByPrimaryKey(grp_no);
	}

	public List<GrouperVO> getAll() {
		return dao.getAll();
	}
	public GrouperVO goinGroup(Integer grp_personcount) {

		GrouperVO grouperVO = new GrouperVO();
		grouperVO.setGrp_personcount(grp_personcount);		

		return grouperVO;
	}
	
}
