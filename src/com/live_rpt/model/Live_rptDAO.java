package com.live_rpt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

			pstmt.setInt(1, Live_rptVO.getRpt_status());
			pstmt.setString(2, Live_rptVO.getRpt_reason());
			pstmt.setString(3, Live_rptVO.getLive_no());
			pstmt.setString(4, Live_rptVO.getMb_id());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
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
	public void update(Live_rptVO Live_rptVO) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setInt(1, Live_rptVO.getRpt_status());
			pstmt.setString(2, Live_rptVO.getRpt_reason());
			pstmt.setString(3, Live_rptVO.getLive_rpt_no());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
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

//	@Override
//	public void updateByCmtNo(Live_rptVO Live_rptVO) {
//		// TODO Auto-generated method stub
//		
//	}

//	@Override
//	public String getRptedMb_id(String live_rpt_no) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void delete(String live_rpt_no) {
		
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, live_rpt_no);


			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
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

//	@Override
//	public Live_rptVO findByPrimaryKey(String live_rpt_no) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Live_rptVO> getAll() {
		List<Live_rptVO> list = new ArrayList<Live_rptVO>();
		Live_rptVO live_rptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
//			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				live_rptVO = new Live_rptVO();
				live_rptVO.setLive_rpt_no(rs.getString("LIVE_RPT_NO"));
				live_rptVO.setLive_no(rs.getString("LIVE_NO"));
				live_rptVO.setRpt_reason(rs.getString("RPT_REASON)"));
				live_rptVO.setMb_id(rs.getString("MB_ID"));
				live_rptVO.setRpt_status(rs.getInt("RPT_STATUS"));
				list.add(live_rptVO); // Store the row in the list				
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	}

//	@Override
//	public List<Live_rptVO> getAllUWish(Map<String, String[]> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void updateByCmtNo(Live_rptVO Live_rptVO) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public String getRptedMb_id(String live_rpt_no) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
	
	
	
	
	public static void main (String args[]) {
		Live_rptDAO dao = new Live_rptDAO();
		
		
		
		//增
		Live_rptVO live_rptVO = new Live_rptVO();
		live_rptVO.setLive_no("LIR00006");
		live_rptVO.setLive_rpt_no("LIV00002");
		live_rptVO.setMb_id("soowii123");
		live_rptVO.setRpt_reason("not good");
		live_rptVO.setRpt_status(1);
		
		dao.insert(live_rptVO);
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
