package com.group_detail.model;

import java.util.List;
import java.util.Set;

public class Grp_detailService {

	private Grp_detailDAO_interface dao;

	public Grp_detailService() {
		dao = new Grp_detailDAO();
	}

	public Grp_detailVO addGrp_detail(String mb_id, String grp_no) {
		
		Grp_detailVO grp_detailVO = new Grp_detailVO();
		System.out.println("svc");
		grp_detailVO.setMb_id(mb_id);
		grp_detailVO.setGrp_no(grp_no);
		dao.insert(grp_detailVO);
		return grp_detailVO;
	}

	public Grp_detailVO updateGrp_detail(String mb_id, String grp_no, Integer grp_register) {

		Grp_detailVO grp_detailVO = new Grp_detailVO();

		grp_detailVO.setMb_id(mb_id);
		grp_detailVO.setGrp_no(grp_no);
		grp_detailVO.setGrp_register(grp_register);		
		dao.update(grp_detailVO);

		return grp_detailVO;
	}

	public void deleteGrp_detail(String mb_id) {
		dao.delete(mb_id);
	}

	public List<Grp_detailVO> getAllGrp_detailByMb_id(String mb_id) {
		return dao.findByPrimaryKey(mb_id);
	}

	public List<Grp_detailVO> getAll() {
		return dao.getAll();
	}
	public int getTotalPeople(String grp_no) {
		return dao.totalPeople(grp_no);		
	}
	public int getTotalGroup(String mb_id) {
		return dao.totalGroup(mb_id);		
	}
}
