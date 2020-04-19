package com.pd_type.model;

import java.util.List;

public class Pd_typeService {

	private Pd_typeDAO_interface dao;

	public Pd_typeService() {

		dao = new Pd_typeDAO();
	}

	public List<Pd_typeVO> getAll() {

		return dao.getAll();
	}

	public Pd_typeVO searchType(String pd_typeNo) {

		return dao.searchType(pd_typeNo);
	}

}
