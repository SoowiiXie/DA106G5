package com.live.model;

import java.util.List;

public class LiveDAO implements LiveDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO LIVE ( LIVE_NO, LIVE_CONTENT, LIVE_STATUS, LIVE_STARTTEASER ,LIVE_START, MB_ID) VALUES ( ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE order by LIVE_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE where MB_ID = ?";
	private static final String UPDATE = "UPDATE LIVE SET LIVE_CONTENT=?, LIVE_STATUS=?, LIVE_STARTTEASER=?, LIVE_START=? where MB_ID = ?";
	private static final String DELETE = "DELETE FROM LIVE where MB_ID = ?";

	@Override
	public void insert(LiveVO memberVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(LiveVO memberVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(LiveVO memberVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public LiveVO findByPrimaryKey(String mb_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LiveVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
