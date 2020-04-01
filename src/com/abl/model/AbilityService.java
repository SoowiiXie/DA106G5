package com.abl.model;

import java.util.List;

public class AbilityService {
	
	private AbilityDAO_interface dao;
	
	public AbilityService() {
		dao = new AbilityJDBCDAO();
	}
	
	public AbilityVO addAbility(String ability_name) {
		
		AbilityVO abilityVO = new AbilityVO();
		abilityVO.setAbility_name(ability_name);
		dao.insert(abilityVO);
		return abilityVO;
	}
	
	public AbilityVO updateAbility(String ability_no, String ability_name) {
		
		AbilityVO abilityVO = new AbilityVO();
		abilityVO.setAbility_no(ability_no);
		abilityVO.setAbility_name(ability_name);
		dao.update(abilityVO);
		return abilityVO;
	}
	
	public void deleteAbility(String ability_no) {
		dao.delete(ability_no);
	}

	public AbilityVO getOneAbility(String ability_no) {
		return dao.findByPrimaryKey(ability_no);
	}

	public List<AbilityVO> getAll() {
		return dao.getAll();
	}
	
}
