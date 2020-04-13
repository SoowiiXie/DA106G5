package com.live_rpt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Live_rptDAO implements Live_rptDAO_interface{
	
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";
	
	private static final String INSERT_STMT = "INSERT INTO LIVE_RPT (LIVE_RPT_NO,RPT_STATUS,RPT_REASON,LIVE_NO,MB_ID) values ('LIR'||LPAD(to_char(LIVE_RPT_NO_SEQ.NEXTVAL), 5, '0'), 5, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE_RPT ORDER BY LIVE_RPT_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM LIVE_RPT WHERE LIVE_RPT_NO = ?";
	private static final String DELETE = "DELETE FROM LIVE_RPT WHERE LIVE_RPT_NO = ?";
	private static final String UPDATE = "UPDATE LIVE_RPT SET RPT_STATUS = ?, RPT_REASON = ? WHERE LIVE_RPT_NO = ?";
	private static final String UPDATE_ALL_CMT_STATUS = "UPDATE LIVE_RPT SET RPT_STATUS = ? where LIVE_NO = ?";
	private static final String GET_RPTED_MB_ID = "SELECT LIVE.MB_ID FROM LIVE JOIN LIVE_RPT ON LIVE.LIVE_NO = LIVE_RPT.LIVE_NO WHERE LIVE_RPT.LIVE_NO = ?";
	
	
	
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G5_DB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	

	@Override
	public void insert(Live_rptVO Live_rptVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, cmt_rptVO.getRpt_reason());
//			pstmt.setInt(2, cmt_rptVO.getRpt_status());
			pstmt.setString(2, cmt_rptVO.getCmt_no());
			pstmt.setString(3, cmt_rptVO.getMb_id());


			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	public void update(Live_rptVO Live_rptVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByCmtNo(Live_rptVO Live_rptVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRptedMb_id(String live_rpt_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String live_rpt_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Live_rptVO findByPrimaryKey(String live_rpt_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Live_rptVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Live_rptVO> getAllUWish(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
