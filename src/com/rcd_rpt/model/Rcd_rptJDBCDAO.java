package com.rcd_rpt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Rcd_rptJDBCDAO implements Rcd_rptDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO rcd_rpt (rcd_rpt_no,rpt_reason,rpt_status,rcd_no,mb_id) values ('rcdr'||LPAD(to_char(RCD_RPT_SEQ.nextval), 5, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT rcd_rpt_no, rpt_reason, rpt_status, rcd_no, mb_id FROM rcd_rpt ORDER BY rcd_rpt_no";
	private static final String GET_ONE_STMT = "SELECT rcd_rpt_no, rpt_reason, rpt_status, rcd_no, mb_id FROM rcd_rpt WHERE rcd_rpt_no = ?";
	private static final String DELETE = "DELETE FROM rcd_rpt where rcd_rpt_no = ?";
	private static final String UPDATE = "UPDATE rcd_rpt SET rpt_reason = ?, rpt_status = ?, rcd_no = ?, mb_id = ? where rcd_rpt_no = ?";

	@Override
	public void insert(Rcd_rptVO rcd_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rcd_rptVO.getRpt_reason());
			pstmt.setInt(2, rcd_rptVO.getRpt_status());
			pstmt.setString(3, rcd_rptVO.getRcd_no());
			pstmt.setString(4, rcd_rptVO.getMb_id());

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
	public void update(Rcd_rptVO rcd_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rcd_rptVO.getRpt_reason());
			pstmt.setInt(2, rcd_rptVO.getRpt_status());
			pstmt.setString(3, rcd_rptVO.getRcd_no());
			pstmt.setString(4, rcd_rptVO.getMb_id());
			pstmt.setString(5, rcd_rptVO.getRcd_rpt_no());

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
	public void delete(String rcd_rpt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rcd_rpt_no);

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
	public Rcd_rptVO findByPrimaryKey(String rcd_rpt_no) {
		Rcd_rptVO rcd_rpt = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rcd_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				rcd_rpt = new Rcd_rptVO();
				rcd_rpt.setRcd_rpt_no(rs.getString("rcd_rpt_no"));
				rcd_rpt.setRpt_reason(rs.getString("rpt_reason"));
				rcd_rpt.setRpt_status(rs.getInt("rpt_status"));
				rcd_rpt.setRcd_no(rs.getString("rcd_no"));
				rcd_rpt.setMb_id(rs.getString("mb_id"));
				rcd_rpt.setRcd_rpt_no(rs.getString("rcd_rpt_no"));
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
		return rcd_rpt;
	}



	@Override
	public List<Rcd_rptVO> getAll() {
		List<Rcd_rptVO> list = new ArrayList<Rcd_rptVO>();
		Rcd_rptVO rcd_rptVO = null;

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
				rcd_rptVO = new Rcd_rptVO();
				rcd_rptVO.setRcd_rpt_no(rs.getString("rcd_rpt_no"));
				rcd_rptVO.setRpt_reason(rs.getString("rpt_reason"));
				rcd_rptVO.setRpt_status(rs.getInt("rpt_status"));
				rcd_rptVO.setRcd_no(rs.getString("rcd_no"));
				rcd_rptVO.setMb_id(rs.getString("mb_id"));
				list.add(rcd_rptVO); // Store the row in the list
				
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

		Rcd_rptJDBCDAO dao = new Rcd_rptJDBCDAO();

		//新增
//		Rcd_rptVO rcd_rptVO1 = new Rcd_rptVO();
//		rcd_rptVO1.setRpt_reason("檢舉達人在此!!!!!!");
//		rcd_rptVO1.setRpt_status(1);
//		rcd_rptVO1.setRcd_no("rcd00001");
//		rcd_rptVO1.setMb_id("soowii123");
//		dao.insert(rcd_rptVO1);
		

//		//修改.紀錄檢舉可以修改紀錄編號?會員編號?
//		Rcd_rptVO rcd_rptVO2 = new Rcd_rptVO();
//		rcd_rptVO2.setRpt_reason("檢舉磨人報到");
//		rcd_rptVO2.setRpt_status(1);
//		rcd_rptVO2.setRcd_no("rcd00001");
//		rcd_rptVO2.setMb_id("soowii123");
//		rcd_rptVO2.setRcd_rpt_no("rcdr00006");
//		dao.update(rcd_rptVO2);

//		//刪除
//		dao.delete("rcdr00006");

//		//查詢
//		Rcd_rptVO rcd_rptVO3 = dao.findByPrimaryKey("rcdr00001");
//		System.out.print(rcd_rptVO3.getRcd_rpt_no() + ",");
//		System.out.print(rcd_rptVO3.getRpt_reason() + ",");
//		System.out.print(rcd_rptVO3.getRpt_status() + ",");
//		System.out.print(rcd_rptVO3.getRcd_no() + ",");
//		System.out.print(rcd_rptVO3.getMb_id() + "\n");
//		System.out.println("---------------------");

//		//查詢getall
//		List<Rcd_rptVO> list = dao.getAll();
//		for (Rcd_rptVO rcd_rptVO : list) {
//			System.out.print(rcd_rptVO.getRcd_rpt_no() + ",");
//			System.out.print(rcd_rptVO.getRpt_reason() + ",");
//			System.out.print(rcd_rptVO.getRpt_status() + ",");
//			System.out.print(rcd_rptVO.getRcd_no() + ",");
//			System.out.print(rcd_rptVO.getMb_id());
//
//			System.out.println();
//		}
	}

}
