package com.group_follow.model;

import java.util.List;

import com.group_detail.model.Grp_detailVO;

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

	public void deleteGroupfollow(String grp_no, String mb_id) {
		Group_followVO group_followVO = new Group_followVO();

		group_followVO.setGrp_no(grp_no);
		group_followVO.setMb_id(mb_id);

		dao.delete(group_followVO);
	}

	public List<Group_followVO> getOneGroupfollow(String mb_id) {
		return dao.findByPrimaryKey(mb_id);
	}

	public List<Group_followVO> getAll() {
		return dao.getAll();
	}
	public int totalFollowPeople(String grp_no) {
		return dao.totalFollowPeople(grp_no);		
	}
	public int totalFollowGroup(String mb_id) {
		return dao.totalFollowGroup(mb_id);		
	}

	public List<Group_followVO> getAllGroup_followByMb_id(String mb_id) {
		// TODO Auto-generated method stub
		return dao.findByPrimaryKey(mb_id);
	}
	//加入雙主鍵
	public void insertByTwo(String grp_no, String mb_id) {
		System.out.println("insert");
		dao.insertByTwo(grp_no, mb_id);		
	}
	//刪除雙主鍵
	public void deleteByTwo(String grp_no, String mb_id) {
		System.out.println("delete");
		dao.deleteByTwo(grp_no, mb_id);		
	}
	//是否有追蹤
	public boolean isFollow(String grp_no, String mb_id) {
		return dao.isFollow(grp_no, mb_id);
	}
}
