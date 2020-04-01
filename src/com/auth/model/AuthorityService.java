package com.auth.model;

import java.util.List;

public class AuthorityService {
	
	private AuthorityDAO_interface dao;
	
	public AuthorityService() {
		dao = new AuthorityDAO();
	}
	
	public AuthorityVO addAuthority(String staff_id, String ability_no) {
		
		AuthorityVO authorityVO = new AuthorityVO();
		authorityVO.setStaff_id(staff_id);
		authorityVO.setAbility_no(ability_no);
		dao.insert(authorityVO);
		return authorityVO;
	}
	
	public void deleteAuthority(String staff_id, String ability_no) {
		dao.delete(staff_id,ability_no);
	}

	public AuthorityVO getOneAuthority(String staff_id, String ability_no) {
		return dao.findByPrimaryKey(staff_id,ability_no);
	}

	public List<AuthorityVO> getAll() {
		return dao.getAll();
	}
}
