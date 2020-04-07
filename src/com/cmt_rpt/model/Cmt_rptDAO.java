package com.cmt_rpt.model;

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




public class Cmt_rptDAO implements Cmt_rpt_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO cmt_rpt (cmt_rpt_no,rpt_reason,cmt_no,mb_id) values ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id FROM cmt_rpt ORDER BY cmt_rpt_no";
	private static final String GET_ONE_STMT = "SELECT cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id FROM cmt_rpt WHERE cmt_rpt_no = ?";
	private static final String DELETE = "DELETE FROM cmt_rpt where cmt_rpt_no = ?";
	private static final String UPDATE = "UPDATE cmt_rpt SET rpt_reason = ?, rpt_status = ? where cmt_rpt_no = ?";

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
	public void insert(Cmt_rptVO cmt_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, cmt_rptVO.getRpt_reason());
			pstmt.setInt(2, cmt_rptVO.getRpt_status());
			pstmt.setString(3, cmt_rptVO.getCmt_no());
			pstmt.setString(4, cmt_rptVO.getMb_id());


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
	public void update(Cmt_rptVO cmt_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, cmt_rptVO.getRpt_reason());
			pstmt.setInt(2, cmt_rptVO.getRpt_status());
			pstmt.setString(3, cmt_rptVO.getCmt_rpt_no());

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
	public void delete(String cmt_rpt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, cmt_rpt_no);

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
	public Cmt_rptVO findByPrimaryKey(String cmt_rptVO) {
		Cmt_rptVO cmt_rpt = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, cmt_rptVO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cmt_rpt = new Cmt_rptVO();
				cmt_rpt.setCmt_rpt_no(rs.getString("cmt_rpt_no"));
				cmt_rpt.setRpt_reason(rs.getString("rpt_reason"));
				cmt_rpt.setRpt_status(rs.getInt("rpt_status"));
				cmt_rpt.setCmt_no(rs.getString("cmt_no"));
				cmt_rpt.setMb_id(rs.getString("mb_id"));
				cmt_rpt.setCmt_rpt_no(rs.getString("cmt_rpt_no"));
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
		return cmt_rpt;
	}



	@Override
	public List<Cmt_rptVO> getAll() {
		List<Cmt_rptVO> list = new ArrayList<Cmt_rptVO>();
		Cmt_rptVO cmt_rptVO = null;

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
				cmt_rptVO = new Cmt_rptVO();
				cmt_rptVO.setCmt_rpt_no(rs.getString("cmt_rpt_no"));
				cmt_rptVO.setRpt_reason(rs.getString("rpt_reason"));
				cmt_rptVO.setRpt_status(rs.getInt("rpt_status"));
				cmt_rptVO.setCmt_no(rs.getString("cmt_no"));
				cmt_rptVO.setMb_id(rs.getString("mb_id"));
				list.add(cmt_rptVO); // Store the row in the list				
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
	public List<Cmt_rptVO> getAllUWish(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();

		List<Cmt_rptVO> list_map = new ArrayList<Cmt_rptVO>();
		Cmt_rptVO cmt_rptVO_map = null;

		Connection con = null;
		PreparedStatement pstmt_map = null;
		ResultSet rs_map = null;

		sb.append("SELECT * FROM commentt where ");
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
				cmt_rptVO_map = new Cmt_rptVO();
				cmt_rptVO_map.setCmt_no(rs_map.getString("cmt_no"));
				cmt_rptVO_map.setCmt_rpt_no(rs_map.getString("cmt_rpt_no"));
				cmt_rptVO_map.setMb_id(rs_map.getString("mb_id"));
				cmt_rptVO_map.setRpt_reason(rs_map.getString("rpt_reason"));
				cmt_rptVO_map.setRpt_status(rs_map.getInt("rpt_status"));

				list_map.add(cmt_rptVO_map); // Store the row in the list
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
