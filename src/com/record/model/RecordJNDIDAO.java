package com.record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RecordJNDIDAO implements RecordDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO Record (rcd_no, rcd_uploadtime, rcd_content, path_no, mb_id) VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT rcd_no, rcd_uploadtime, rcd_content, rcd_thumb_amount, rcd_metoo_amount, rcd_status, path_no, mb_id FROM record ORDER BY rcd_no";
	private static final String GET_ONE_STMT = "SELECT rcd_no, rcd_uploadtime, rcd_content, rcd_thumb_amount, rcd_metoo_amount, rcd_status, path_no, mb_id FROM record WHERE rcd_no = ?";
	private static final String DELETE = "DELETE FROM record where rcd_no = ?";
	private static final String UPDATE = "UPDATE record SET rcd_uploadtime = ?, rcd_content = ?, rcd_status=?, path_no=? where rcd_no = ?";

	
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
	public void insert(RecordVO recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, recordVO.getRcd_uploadtime());
			pstmt.setString(2, recordVO.getRcd_content());
			pstmt.setString(3, recordVO.getPath_no());
			pstmt.setString(4, recordVO.getMb_id());

			pstmt.executeUpdate();
			System.out.println("ok");
			// Handle any driver errors
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
	public void update(RecordVO recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, recordVO.getRcd_uploadtime());
			pstmt.setString(2, recordVO.getRcd_content());
			pstmt.setInt(3, recordVO.getRcd_status());
			pstmt.setString(4, recordVO.getPath_no());
			pstmt.setString(5, recordVO.getRcd_no());
//System.out.println(recordVO.getRcd_no());
			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String rcd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rcd_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public RecordVO findByPrimaryKey(String rcd_no) {
		RecordVO recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rcd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				recordVO = new RecordVO();
				recordVO.setRcd_no(rs.getString("rcd_no"));
				recordVO.setRcd_uploadtime(rs.getDate("rcd_uploadtime"));
				recordVO.setRcd_content(rs.getString("rcd_content"));
				recordVO.setRcd_thumb_amount(rs.getInt("rcd_thumb_amount"));
				recordVO.setRcd_metoo_amount(rs.getInt("rcd_metoo_amount"));
				recordVO.setRcd_status(rs.getInt("rcd_status"));
				recordVO.setPath_no(rs.getString("path_no"));
				recordVO.setMb_id(rs.getString("mb_id"));
			}
			// Handle any driver errors
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
		return recordVO;
	}



	@Override
	public List<RecordVO> getAll() {
		List<RecordVO> list = new ArrayList<RecordVO>();
		RecordVO recordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recordVO = new RecordVO();
				recordVO.setRcd_no(rs.getString("rcd_no"));
				recordVO.setRcd_uploadtime(rs.getDate("rcd_uploadtime"));
				recordVO.setRcd_content(rs.getString("rcd_content"));
				recordVO.setRcd_thumb_amount(rs.getInt("rcd_thumb_amount"));
				recordVO.setRcd_metoo_amount(rs.getInt("rcd_metoo_amount"));
				recordVO.setRcd_status(rs.getInt("rcd_status"));
				recordVO.setPath_no(rs.getString("path_no"));
				recordVO.setMb_id(rs.getString("mb_id"));
				list.add(recordVO); // Store the row in the list
			}

			// Handle any driver errors
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
	
	
	
}
	
