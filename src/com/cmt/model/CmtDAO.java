package com.cmt.model;

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

public class CmtDAO implements Cmt_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO commentt (cmt_no,cmt_content,cmt_time,rcd_no,mb_id) values ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id FROM commentt ORDER BY cmt_no";
	private static final String GET_ONE_STMT = "SELECT cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id FROM commentt WHERE cmt_no = ?";
	private static final String DELETE = "DELETE FROM commentt where cmt_no = ?";
	private static final String UPDATE = "UPDATE commentt SET cmt_content = ?, cmt_status = ? where cmt_no = ?";

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
	public void insert(CmtVO cmtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, cmtVO.getCmt_content());
			pstmt.setDate(2, cmtVO.getCmt_time());
			pstmt.setString(3, cmtVO.getRcd_no());
			pstmt.setString(4, cmtVO.getMb_id());

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
	public void update(CmtVO cmtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, cmtVO.getCmt_content());
			pstmt.setInt(2, cmtVO.getCmt_status());
			pstmt.setString(3, cmtVO.getCmt_no());

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
	public void delete(String cmt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, cmt_no);

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
	public CmtVO findByPrimaryKey(String cmt_no) {
		CmtVO cmt = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, cmt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cmt = new CmtVO();
				cmt.setCmt_no(rs.getString("cmt_no"));
				cmt.setCmt_content(rs.getString("cmt_content"));
				cmt.setCmt_time(rs.getDate("cmt_time"));
				cmt.setCmt_status(rs.getInt("cmt_status"));
				cmt.setRcd_no(rs.getString("rcd_no"));
				cmt.setMb_id(rs.getString("mb_id"));
				cmt.setCmt_no(rs.getString("cmt_no"));
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
		return cmt;
	}

	@Override
	public List<CmtVO> getAll() {
		List<CmtVO> list = new ArrayList<CmtVO>();
		CmtVO cmtVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cmtVO = new CmtVO();
				cmtVO.setCmt_no(rs.getString("cmt_no"));
				cmtVO.setCmt_content(rs.getString("cmt_content"));
				cmtVO.setCmt_time(rs.getDate("cmt_time"));
				cmtVO.setCmt_status(rs.getInt("cmt_status"));
				cmtVO.setRcd_no(rs.getString("rcd_no"));
				cmtVO.setMb_id(rs.getString("mb_id"));
				list.add(cmtVO); // Store the row in the list
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
	public List<CmtVO> getAllUWish(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();

		List<CmtVO> list_map = new ArrayList<CmtVO>();
		CmtVO cmtVO_map = null;

		Connection con = null;
		PreparedStatement pstmt_map = null;
		ResultSet rs_map = null;

		sb.append("SELECT * FROM Cmt where ");
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
				cmtVO_map = new CmtVO();
				cmtVO_map.setCmt_no(rs_map.getString("cmt_no"));
				cmtVO_map.setCmt_content(rs_map.getString("cmt_content"));
				cmtVO_map.setCmt_time(rs_map.getDate("cmt_time"));
				cmtVO_map.setCmt_status(rs_map.getInt("cmt_status"));
				cmtVO_map.setRcd_no(rs_map.getString("rcd_no"));
				cmtVO_map.setMb_id(rs_map.getString("mb_id"));
				list_map.add(cmtVO_map); // Store the row in the list
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
