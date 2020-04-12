package com.group_follow.model;

import java.util.List;

public class Group_followService {

	private Group_followDAO_interface dao;

	public Group_followService() {
		dao = new Group_followDAO();
	}

	public Group_followVO addGroupfollow(String grp_no, String mb_id) {

		Group_followVO group_followVO = new Group_followVO();

		group_followVO.setGrp_no(grp_no);
		group_followVO.setMb_id(mb_id);

		dao.insert(group_followVO);

		return group_followVO;
	}

	public Group_followVO updateGroupfollow(String grp_no, String mb_id) {

		Group_followVO group_followVO = new Group_followVO();

		group_followVO.setGrp_no(grp_no);
		group_followVO.setMb_id(mb_id);

		dao.update(group_followVO);

		return group_followVO;
	}

	public void deleteGroupfollow(String grp_no) {
		dao.delete(grp_no);
	}

	public Group_followVO getOneGroupfollow(String grp_no) {
		return dao.findByPrimaryKey(grp_no);
	}

	public List<Group_followVO> getAll() {
		return dao.getAll();
	}
}
