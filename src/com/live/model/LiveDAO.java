package com.live.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	public void insert(LiveVO LiveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, LiveVO.getLive_no());
			pstmt.setString(2, LiveVO.getLive_content());
			pstmt.setInt(3, LiveVO.getLive_status());
			pstmt.setDate(4, LiveVO.getLive_startteaser());
			pstmt.setDate(5, LiveVO.getLive_start());
			pstmt.setString(6, LiveVO.getMb_id());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

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
