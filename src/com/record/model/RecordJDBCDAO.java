package com.record.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class RecordJDBCDAO implements RecordDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO Record (rcd_no, rcd_uploadtime, rcd_content, rcd_thumb_amount, rcd_metoo_amount, rcd_status, path_no, mb_id) VALUES ('rcd'||LPAD(to_char(RCD_NO_SEQ.nextval), 5, '0'),?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT rcd_no, rcd_uploadtime, rcd_content, rcd_thumb_amount, rcd_metoo_amount, rcd_status, path_no, mb_id FROM record ORDER BY rcd_no";
	private static final String GET_ONE_STMT = "SELECT rcd_no, rcd_uploadtime, rcd_content, rcd_thumb_amount, rcd_metoo_amount, rcd_status, path_no, mb_id FROM record WHERE rcd_no = ?";
	private static final String DELETE = "DELETE FROM record where rcd_no = ?";
	private static final String UPDATE = "UPDATE record SET rcd_uploadtime = ?, rcd_content = ?, rcd_thumb_amount=?, rcd_metoo_amount=?, rcd_status=?, path_no=?, mb_id=? where rcd_no = ?";

	@Override
	public void insert(RecordVO recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setDate(1, recordVO.getRcd_uploadtime());
			pstmt.setString(2, recordVO.getRcd_content());
			pstmt.setInt(3, recordVO.getRcd_thumb_amount());
			pstmt.setInt(4, recordVO.getRcd_metoo_amount());
			pstmt.setInt(5, recordVO.getRcd_status());
			pstmt.setString(6, recordVO.getPath_no());
			pstmt.setString(7, recordVO.getMb_id());

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
	public void update(RecordVO recordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setDate(1, recordVO.getRcd_uploadtime());
			pstmt.setString(2, recordVO.getRcd_content());
			pstmt.setInt(3, recordVO.getRcd_thumb_amount());
			pstmt.setInt(4, recordVO.getRcd_metoo_amount());
			pstmt.setInt(5, recordVO.getRcd_status());
			pstmt.setString(6, recordVO.getPath_no());
			pstmt.setString(7, recordVO.getMb_id());
			pstmt.setString(8, recordVO.getRcd_no());
			
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
	public void delete(String rcd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rcd_no);

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
	public RecordVO findByPrimaryKey(String rcd_no) {
		RecordVO recordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rcd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
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

		RecordJDBCDAO dao = new RecordJDBCDAO();

//		//新增
//		RecordVO recordVO1 = new RecordVO();
//		recordVO1.setRcd_uploadtime(java.sql.Date.valueOf("2020-03-01"));
//		recordVO1.setRcd_content("新紀錄!!!!!!");
//		recordVO1.setRcd_thumb_amount(300);
//		recordVO1.setRcd_metoo_amount(200);
//		recordVO1.setRcd_status(1);
//		recordVO1.setPath_no("p00001");
//		recordVO1.setMb_id("soowii123");
//		dao.insert(recordVO1);
		

//		//修改.紀錄修改:會員編號?按讚數?metoo數?
//		RecordVO recordVO2 = new RecordVO();
//		recordVO2.setRcd_uploadtime(java.sql.Date.valueOf("2000-03-01"));
//		recordVO2.setRcd_content("沒什麼大不了");
//		recordVO2.setRcd_thumb_amount(1000);
//		recordVO2.setRcd_metoo_amount(1000);
//		recordVO2.setRcd_status(1);
//		recordVO2.setPath_no("p00001");
//		recordVO2.setMb_id("soowii123");
//		recordVO2.setRcd_no("rcd00006");
//		dao.update(recordVO2);

//		//刪除
//		dao.delete("rcd00006");

//		//查詢
//		RecordVO recordVO3 = dao.findByPrimaryKey("rcd00001");
//		System.out.print(recordVO3.getRcd_no() + ",");
//		System.out.print(recordVO3.getRcd_uploadtime() + ",");
//		System.out.print(recordVO3.getRcd_content() + ",");
//		System.out.print(recordVO3.getRcd_thumb_amount() + ",");
//		System.out.print(recordVO3.getRcd_metoo_amount() + ",");
//		System.out.print(recordVO3.getRcd_status() + ",");
//		System.out.print(recordVO3.getPath_no() + ",");
//		System.out.print(recordVO3.getMb_id() + "\n");
//		System.out.println("---------------------");

//		//查詢getall
//		List<RecordVO> list = dao.getAll();
//		for (RecordVO recordVO : list) {
//			System.out.print(recordVO.getRcd_no() + ",");
//			System.out.print(recordVO.getRcd_uploadtime() + ",");
//			System.out.print(recordVO.getRcd_content() + ",");
//			System.out.print(recordVO.getRcd_thumb_amount() + ",");
//			System.out.print(recordVO.getRcd_metoo_amount() + ",");
//			System.out.print(recordVO.getRcd_status() + ",");
//			System.out.print(recordVO.getPath_no() + ",");
//			System.out.print(recordVO.getMb_id());
//			System.out.println();
//		}
		
		
		
	}

}
