package com.mb_rpt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MB_RPTDAO implements MB_RPTDAO_interface{
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";
	
	private static final String INSERT_STMT = 
			"INSERT INTO MB_RPT (MB_RPT_NO, MB_ID_1, MB_ID_2, RPT_REASON) VALUES ('MBR'||LPAD(to_char(MB_RPT_NO_SEQ.NEXTVAL), 5, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM MB_RPT order by MB_RPT_NO";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM MB_RPT where MB_RPT_NO = ?";
	private static final String UPDATE = 
			"UPDATE MB_RPT set MB_ID_1=?, MB_ID_2=?, RPT_REASON=?, RPT_STATUS=? where MB_RPT_NO = ?";
	
	// 連線池
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
	public void insert(MB_RPTVO mb_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, mb_rptVO.getMb_id_1());
			pstmt.setString(2, mb_rptVO.getMb_id_2());
			pstmt.setString(3, mb_rptVO.getRpt_reason());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public void update(MB_RPTVO mb_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, mb_rptVO.getMb_id_1());
			pstmt.setString(2, mb_rptVO.getMb_id_2());
			pstmt.setString(3, mb_rptVO.getRpt_reason());
			pstmt.setInt(4, mb_rptVO.getRpt_status());
			pstmt.setString(5, mb_rptVO.getMb_rpt_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public MB_RPTVO findByPrimaryKey(String mb_rpt_no) {
		MB_RPTVO mb_rptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mb_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				mb_rptVO = new MB_RPTVO();
				mb_rptVO.setMb_rpt_no(rs.getString("mb_rpt_no"));
				mb_rptVO.setMb_id_1(rs.getString("mb_id_1"));
				mb_rptVO.setMb_id_2(rs.getString("mb_id_2"));
				mb_rptVO.setRpt_reason(rs.getString("rpt_reason"));
				mb_rptVO.setRpt_status(rs.getInt("rpt_status"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return mb_rptVO;
	}

	@Override
	public List<MB_RPTVO> getAll() {
		List<MB_RPTVO> list = new ArrayList<MB_RPTVO>();
		MB_RPTVO mb_rptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				mb_rptVO = new MB_RPTVO();
				mb_rptVO.setMb_rpt_no(rs.getString("mb_rpt_no"));
				mb_rptVO.setMb_id_1(rs.getString("mb_id_1"));
				mb_rptVO.setMb_id_2(rs.getString("mb_id_2"));
				mb_rptVO.setRpt_reason(rs.getString("rpt_reason"));
				mb_rptVO.setRpt_status(rs.getInt("rpt_status"));
				list.add(mb_rptVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
//	// 測試
//	public static void main(String[] args) {
//		MB_RPTDAO dao = new MB_RPTDAO();
//		
//		// 查
//		List<MB_RPTVO> all = dao.getAll();
//		for(MB_RPTVO mb_rptVO:all) {
//			System.out.println(mb_rptVO.getMb_rpt_no());	
//			System.out.println(mb_rptVO.getMb_id_1());
//			System.out.println(mb_rptVO.getMb_id_2());
//			System.out.println(mb_rptVO.getRpt_reason());
//			System.out.println(mb_rptVO.getRpt_status());
//		}
//		
//		// 增
//		MB_RPTVO mb_rptVO = new MB_RPTVO();
//		mb_rptVO.setMb_id_1("soowii123");
//		mb_rptVO.setMb_id_2("vain123");
//		mb_rptVO.setRpt_reason("HOHIOHOH");
//		dao.insert(mb_rptVO);
//		
//		// 改
//		MB_RPTVO mb_rptVO = new MB_RPTVO();
//		mb_rptVO.setMb_id_1("soowii123");
//		mb_rptVO.setMb_id_2("vain123");
//		mb_rptVO.setRpt_reason("AAAA");
//		mb_rptVO.setRpt_status(2);
//		mb_rptVO.setMb_rpt_no("MBR00006");
//		dao.update(mb_rptVO);
//		
//		// 用PK查詢
//		MB_RPTVO mb_rptVO = dao.findByPrimaryKey("MBR00005");
//		System.out.println(mb_rptVO.getMb_rpt_no());
//		System.out.println(mb_rptVO.getMb_id_1());
//		System.out.println(mb_rptVO.getMb_id_2());
//		System.out.println(mb_rptVO.getRpt_reason());
//		System.out.println(mb_rptVO.getRpt_status());
//	}

}
