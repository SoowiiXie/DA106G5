package com.group_detail.model;

import java.util.List;

public class Grp_detailService {

	private Grp_detailDAO_interface dao;

	public Grp_detailService() {
		dao = new Grp_detailDAO();
	}

	public Grp_detailVO addGrp_detail(String mb_id, String grp_no, Integer grp_register) {

		Grp_detailVO grp_detaiVO = new Grp_detailVO();

		grp_detaiVO.setMb_id(mb_id);
		grp_detaiVO.setGrp_no(grp_no);
		grp_detaiVO.setGrp_register(grp_register);
		dao.insert(grp_detaiVO);

		return grp_detaiVO;
	}

	public Grp_detailVO updateGrp_detail(String mb_id, String grp_no, Integer grp_register) {

		Grp_detailVO grp_detaiVO = new Grp_detailVO();

		grp_detaiVO.setMb_id(mb_id);
		grp_detaiVO.setGrp_no(grp_no);
		grp_detaiVO.setGrp_register(grp_register);		
		dao.update(grp_detaiVO);

		return grp_detaiVO;
	}

	public void deleteGrp_detail(String mb_id) {
		dao.delete(mb_id);
	}

	public Grp_detailVO getOneGrp_detail(String mb_id) {
		return dao.findByPrimaryKey(mb_id);
	}

	public List<Grp_detailVO> getAll() {
		return dao.getAll();
	}
}
