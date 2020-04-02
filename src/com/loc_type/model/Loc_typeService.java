package com.loc_type.model;

import java.util.List;

public class Loc_typeService {

	private Loc_type_interface dao;

	public Loc_typeService() {
		dao = new Loc_typeDAO();
	}

	public Loc_typeVO addLocation(String loc_typeno, String loc_info) {
		// 增
		Loc_typeVO loc_typeVO_insert = new Loc_typeVO();
		loc_typeVO_insert.setLoc_typeno(loc_typeno);
		loc_typeVO_insert.setLoc_info(loc_info);
		dao.insert(loc_typeVO_insert);
		return loc_typeVO_insert;
	}

	public Loc_typeVO updateLocation(String loc_typeno, String loc_info) {
		// 改
		Loc_typeVO loc_typeVO_update = new Loc_typeVO();
		loc_typeVO_update.setLoc_typeno(loc_typeno);
		loc_typeVO_update.setLoc_info(loc_info);
		dao.update(loc_typeVO_update);
		return loc_typeVO_update;
	}

	public void deleteLocation(String loc_typeno) {
		dao.delete(loc_typeno);
	}

	public Loc_typeVO getOneLocation(String loc_typeno) {
		return dao.findByPrimaryKey(loc_typeno);
	}

	public List<Loc_typeVO> getAll() {
		return dao.getAll();
	}
}
