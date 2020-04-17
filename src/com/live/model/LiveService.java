package com.live.model;

import java.util.List;

public class LiveService {

	private LiveDAO_interface dao;
	
	public LiveService() {
		dao = new LiveDAO();
	}

	public List<LiveVO> getAll() {
		return dao.getAll();
	}
	
	public List<LiveVO> getAllTake4() {
		return dao.getAllTake4();
	}
}
