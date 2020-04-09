package com.path_follow.model;

import java.util.List;

public class Path_followService {

	private Path_followDAO_interface dao;
	
	public Path_followService(){
		dao = new Path_followJDBCDAO();
	}

	public Path_followVO addPath_follow(String path_no, String mb_id) {

		Path_followVO path_followVO = new Path_followVO();

		path_followVO.setPath_no(path_no);
		path_followVO.setMb_id(mb_id);

		dao.insert(path_followVO);

		return path_followVO;
	}

	public Path_followVO updatePath_follow(String path_no, String mb_id) {

		Path_followVO path_followVO = new Path_followVO();

		path_followVO.setPath_no(path_no);
		path_followVO.setMb_id(mb_id);

		dao.update(path_followVO);

		return path_followVO;
	}

	public void deletePath_follow(String path_no,String mb_id) {
		dao.delete(path_no, mb_id);
	}

	public Path_followVO getOnePath_follow(String path_no, String mb_id) {
		return dao.findByPrimaryKey(path_no, mb_id);
	}

	public List<Path_followVO> getAll() {
		return dao.getAll();
	}
}
