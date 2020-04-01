package com.staff.model;

import java.util.List;

public class StaffService {

	private StaffDAO_interface dao;
	
	public StaffService() {
		dao = new StaffDAO();
	}
	
	public StaffVO addStaff(String staff_id, String staff_pwd, String staff_name) {
		
		StaffVO staffVO = new StaffVO();
		staffVO.setStaff_id(staff_id);
		staffVO.setStaff_pwd(staff_pwd);
		staffVO.setStaff_name(staff_name);
		dao.insert(staffVO);
		return staffVO;
	}
	
	public StaffVO updateStaff(String staff_id, String staff_pwd, String staff_name, Integer staff_status) {
		
		StaffVO staffVO = new StaffVO();
		staffVO.setStaff_id(staff_id);
		staffVO.setStaff_pwd(staff_pwd);
		staffVO.setStaff_name(staff_name);
		staffVO.setStaff_status(staff_status);
		dao.update(staffVO);
		return staffVO;
	}

	public StaffVO getOneStaff(String staff_id) {
		return dao.findByPrimaryKey(staff_id);
	}

	public List<StaffVO> getAll() {
		return dao.getAll();
	}
}
