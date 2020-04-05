package com.cmt_rpt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Cmt_rptJDBCDAO implements Cmt_rptDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO cmt_rpt (cmt_rpt_no,rpt_reason,rpt_status,cmt_no,mb_id) values ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'),?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id FROM cmt_rpt ORDER BY cmt_rpt_no";
	private static final String GET_ONE_STMT = "SELECT cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id FROM cmt_rpt WHERE cmt_rpt_no = ?";
	private static final String DELETE = "DELETE FROM cmt_rpt where cmt_rpt_no = ?";
	private static final String UPDATE = "UPDATE cmt_rpt SET rpt_reason = ?, rpt_status = ? where cmt_rpt_no = ?";

	@Override
	public void insert(Cmt_rptVO cmt_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, cmt_rptVO.getRpt_reason());
			pstmt.setInt(2, cmt_rptVO.getRpt_status());
			pstmt.setString(3, cmt_rptVO.getCmt_no());
			pstmt.setString(4, cmt_rptVO.getMb_id());


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
	public void update(Cmt_rptVO cmt_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, cmt_rptVO.getRpt_reason());
			pstmt.setInt(2, cmt_rptVO.getRpt_status());
			pstmt.setString(3, cmt_rptVO.getCmt_rpt_no());

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
	public void delete(String cmt_rpt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, cmt_rpt_no);

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
	public Cmt_rptVO findByPrimaryKey(String cmt_rptVO) {
		Cmt_rptVO cmt_rpt = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
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

		Cmt_rptJDBCDAO dao = new Cmt_rptJDBCDAO();

//		 //新增
//		Cmt_rptVO cmt_rptVO1 = new Cmt_rptVO();
//		cmt_rptVO1.setRpt_reason("這個我不行!!!!!!");
//		cmt_rptVO1.setRpt_status(1);
//		cmt_rptVO1.setCmt_no("cmt00001");
//		cmt_rptVO1.setMb_id("soowii123");
//		dao.insert(cmt_rptVO1);

//		//修改
//		Cmt_rptVO cmt_rptVO2 = new Cmt_rptVO();
//		cmt_rptVO2.setRpt_reason("他我可以");
//		cmt_rptVO2.setRpt_status(1);
//		cmt_rptVO2.setCmt_rpt_no("cmtr00006");
//		dao.update(cmt_rptVO2);

//		//刪除
//		dao.delete("cmtr00006");

//		//查詢
//		Cmt_rptVO cmt_rptVO3 = dao.findByPrimaryKey("cmtr00001");
//		System.out.print(cmt_rptVO3.getCmt_rpt_no() + ",");
//		System.out.print(cmt_rptVO3.getRpt_reason() + ",");
//		System.out.print(cmt_rptVO3.getRpt_status() + ",");
//		System.out.print(cmt_rptVO3.getCmt_no() + ",");
//		System.out.print(cmt_rptVO3.getMb_id() + "\n");
//		System.out.println("---------------------");

//		// 查詢getall
//		List<Cmt_rptVO> list = dao.getAll();
//		for (Cmt_rptVO cmt_rptVO : list) {
//			System.out.print(cmt_rptVO.getCmt_rpt_no() + ",");
//			System.out.print(cmt_rptVO.getRpt_reason() + ",");
//			System.out.print(cmt_rptVO.getRpt_status() + ",");
//			System.out.print(cmt_rptVO.getCmt_no() + ",");
//			System.out.print(cmt_rptVO.getMb_id());
//
//			System.out.println();
//		}
		
		
	}

}
