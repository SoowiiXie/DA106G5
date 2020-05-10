package com.group_detail.model;

import java.util.List;
import java.util.Set;

public class Grp_detailService {

	private Grp_detailDAO_interface dao;

	public Grp_detailService() {
		dao = new Grp_detailDAO();
	}

	public Grp_detailVO addGrp_detail(String mb_id, String grp_no, Integer grp_register) {
		
		Grp_detailVO grp_detailVO = new Grp_detailVO();
		System.out.println("svc");
		grp_detailVO.setMb_id(mb_id);
		grp_detailVO.setGrp_no(grp_no);
		grp_detailVO.setGrp_register(grp_register);
		dao.insert(grp_detailVO);
		return grp_detailVO;
	}
	
//	public Grp_detailVO findByPrimaryKeyByGrp_no(String grp_no) {
//		
//		Grp_detailVO grp_detailVO = new Grp_detailVO();
//		System.out.println("svcfind");
//		dao.findByPrimaryKeyByGrp_no(grp_no);
//		return grp_detailVO;
//	}

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
	public boolean isFull(Integer grp_personmax, String grp_no){
		return dao.isFull(grp_personmax, grp_no);
	}
	//加入雙主鍵
		public void insertByThree( String mb_id, String grp_no, Integer grp_register) {
			System.out.println("insert");
			dao.insertByThree(mb_id,grp_no, grp_register);		
	}
	//刪除雙主鍵
	public void deleteByTwo(String mb_id , String grp_no) {
		System.out.println("delete");
		dao.deleteByTwo(mb_id, grp_no);		
	}
	//是否有加入
	public boolean isGoin(String mb_id , String grp_no) {
		return dao.isGoin(mb_id,grp_no);
	}
}
