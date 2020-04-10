package com.loc_rpt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Loc_rptDAO implements Loc_rpt_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO loc_rpt (loc_rpt_no,rpt_reason,loc_no,mb_id) values ('locr'||LPAD(to_char(LOC_RPT_SEQ.nextval), 5, '0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT loc_rpt_no, rpt_reason, rpt_status, loc_no, mb_id FROM loc_rpt ORDER BY loc_rpt_no";
	private static final String GET_ONE_STMT = "SELECT loc_rpt_no, rpt_reason, rpt_status, loc_no, mb_id FROM loc_rpt WHERE loc_rpt_no = ?";
	private static final String DELETE = "DELETE FROM loc_rpt where loc_rpt_no = ?";
	private static final String UPDATE = "UPDATE loc_rpt SET rpt_reason = ?, rpt_status = ? where loc_rpt_no = ?";
	private static final String UPDATE_ALL_LOC_STATUS = "UPDATE loc_rpt SET rpt_status = ? where loc_no = ?";

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G5_DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(Loc_rptVO loc_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, loc_rptVO.getRpt_reason());
//			pstmt.setInt(2, cmt_rptVO.getRpt_status());
			pstmt.setString(2, loc_rptVO.getLoc_no());
			pstmt.setString(3, loc_rptVO.getMb_id());


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
	public void update(Loc_rptVO loc_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, loc_rptVO.getRpt_reason());
			pstmt.setInt(2, loc_rptVO.getRpt_status());
			pstmt.setString(3, loc_rptVO.getLoc_rpt_no());

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
	public void updateByLocNo(Loc_rptVO loc_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ALL_LOC_STATUS,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			pstmt.setInt(1, loc_rptVO.getRpt_status());
			pstmt.setString(2, loc_rptVO.getLoc_no());
			
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
	public void delete(String loc_rpt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, loc_rpt_no);

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
	public Loc_rptVO findByPrimaryKey(String loc_rptVO) {
		Loc_rptVO loc_rpt = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, loc_rptVO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				loc_rpt = new Loc_rptVO();
				loc_rpt.setLoc_rpt_no(rs.getString("loc_rpt_no"));
				loc_rpt.setRpt_reason(rs.getString("rpt_reason"));
				loc_rpt.setRpt_status(rs.getInt("rpt_status"));
				loc_rpt.setLoc_no(rs.getString("loc_no"));
				loc_rpt.setMb_id(rs.getString("mb_id"));
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return loc_rpt;
	}



	@Override
	public List<Loc_rptVO> getAll() {
		List<Loc_rptVO> list = new ArrayList<Loc_rptVO>();
		Loc_rptVO loc_rptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				loc_rptVO = new Loc_rptVO();
				loc_rptVO.setLoc_rpt_no(rs.getString("loc_rpt_no"));
				loc_rptVO.setRpt_reason(rs.getString("rpt_reason"));
				loc_rptVO.setRpt_status(rs.getInt("rpt_status"));
				loc_rptVO.setLoc_no(rs.getString("loc_no"));
				loc_rptVO.setMb_id(rs.getString("mb_id"));
				list.add(loc_rptVO); // Store the row in the list				
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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


	@Override
	public List<Loc_rptVO> getAllUWish(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();

		List<Loc_rptVO> list_map = new ArrayList<Loc_rptVO>();
		Loc_rptVO loc_rptVO_map = null;

		Connection con = null;
		PreparedStatement pstmt_map = null;
		ResultSet rs_map = null;

		sb.append("SELECT * FROM loc_rpt where ");
		for (Entry<String, String[]> entry : map.entrySet()) {
			sb.append("(");
			for (int i = 0; i < entry.getValue().length; i++) {
				if (i == 0) {
					sb.append(entry.getKey() + " = " + entry.getValue()[i]);
				} else {
					sb.append(" OR " + entry.getKey() + " = " + entry.getValue()[i]);
				}
			}
			sb.append(") and ");
		}
		sb.append(" 1=1 ");

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt_map = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs_map = pstmt_map.executeQuery();
			while (rs_map.next()) {
				// empVO 也稱為 Domain objects
				loc_rptVO_map = new Loc_rptVO();
				loc_rptVO_map.setLoc_no(rs_map.getString("loc_no"));
				loc_rptVO_map.setLoc_rpt_no(rs_map.getString("loc_rpt_no"));
				loc_rptVO_map.setMb_id(rs_map.getString("mb_id"));
				loc_rptVO_map.setRpt_reason(rs_map.getString("rpt_reason"));
				loc_rptVO_map.setRpt_status(rs_map.getInt("rpt_status"));

				list_map.add(loc_rptVO_map); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs_map != null) {
				try {
					rs_map.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt_map != null) {
				try {
					pstmt_map.close();
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
		return list_map;
	}
	
}
