package com.cmt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CmtJDBCDAO implements CmtDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO commentt (cmt_no,cmt_content,cmt_time,rcd_no,mb_id) values ('cmt'||LPAD(to_char(CMT_NO_SEQ.nextval), 5, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id FROM commentt ORDER BY cmt_no";
	private static final String GET_ONE_STMT = "SELECT cmt_no, cmt_content, cmt_time, cmt_status, rcd_no, mb_id FROM commentt WHERE cmt_no = ?";
	private static final String DELETE = "DELETE FROM commentt where cmt_no = ?";
	private static final String UPDATE = "UPDATE commentt SET cmt_content = ?, cmt_status = ? where cmt_no = ?";

	@Override
	public void insert(CmtVO cmtVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cmtVO.getCmt_content());
			pstmt.setDate(2, cmtVO.getCmt_time());
			pstmt.setString(3, cmtVO.getRcd_no());
			pstmt.setString(4, cmtVO.getMb_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, cmtVO.getCmt_content());
			pstmt.setInt(2, cmtVO.getCmt_status());
			pstmt.setString(3, cmtVO.getCmt_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cmt_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	
	public static void main(String[] args) {

		CmtJDBCDAO dao = new CmtJDBCDAO();

//		//新增
//		CmtVO cmtVO1 = new CmtVO();
//		cmtVO1.setCmt_content("強者妳本人");
//		cmtVO1.setCmt_time(java.sql.Date.valueOf("2020-01-01"));
//		cmtVO1.setRcd_no("rcd00001");
//		cmtVO1.setMb_id("soowii123");
//		dao.insert(cmtVO1);

//		//修改
//		CmtVO cmtVO2 = new CmtVO();
//		cmtVO2.setCmt_content("差我一點");
//		cmtVO2.setCmt_status(1);
//		cmtVO2.setCmt_no("cmt00006");
//		dao.update(cmtVO2);

//		//刪除
//		dao.delete("cmt00006");

//		//查詢
//		CmtVO cmtVO3 = dao.findByPrimaryKey("cmt00001");
//		System.out.print(cmtVO3.getCmt_no() + ",");
//		System.out.print(cmtVO3.getCmt_content() + ",");
//		System.out.print(cmtVO3.getCmt_time() + ",");
//		System.out.print(cmtVO3.getCmt_status() + ",");
//		System.out.print(cmtVO3.getRcd_no() + ",");
//		System.out.print(cmtVO3.getMb_id() + ",");
//		System.out.print(cmtVO3.getCmt_no() + "\n");
//		System.out.println("---------------------");

//		//查詢getall
//		List<CmtVO> list = dao.getAll();
//		for (CmtVO cmtVO : list) {
//			System.out.print(cmtVO.getCmt_no() + ",");
//			System.out.print(cmtVO.getCmt_content() + ",");
//			System.out.print(cmtVO.getCmt_time() + ",");
//			System.out.print(cmtVO.getCmt_status() + ",");
//			System.out.print(cmtVO.getRcd_no() + ",");
//			System.out.print(cmtVO.getMb_id());
//
//			System.out.println();
//		}

	
	}

}
