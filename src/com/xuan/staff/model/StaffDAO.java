package com.xuan.staff.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StaffDAO implements StaffDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
		"INSERT INTO STAFF (STAFF_ID, STAFF_PWD, STAFF_NAME) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM STAFF order by STAFF_ID";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM STAFF where STAFF_ID = ?";
	private static final String UPDATE = 
		"UPDATE STAFF set STAFF_PWD=?, STAFF_NAME=?, STAFF_STATUS=? where STAFF_ID = ?";

	@Override
	public void insert(StaffVO staffVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, staffVO.getStaff_id());
			pstmt.setString(2, staffVO.getStaff_pwd());
			pstmt.setString(3, staffVO.getStaff_name());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(StaffVO staffVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, staffVO.getStaff_pwd());
			pstmt.setString(2, staffVO.getStaff_name());
			pstmt.setInt(3, staffVO.getStaff_status());
			pstmt.setString(4, staffVO.getStaff_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public StaffVO findByPrimaryKey(String staff_id) {
		StaffVO staffVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, staff_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				staffVO = new StaffVO();
				staffVO.setStaff_id(rs.getString("staff_id"));
				staffVO.setStaff_pwd(rs.getString("staff_pwd"));
				staffVO.setStaff_name(rs.getString("staff_name"));
				staffVO.setStaff_join(rs.getTimestamp("staff_join"));
				staffVO.setStaff_status(rs.getInt("staff_status"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		return staffVO;
	}

	@Override
	public List<StaffVO> getAll() {
		List<StaffVO> list = new ArrayList<StaffVO>();
		StaffVO staffVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				staffVO = new StaffVO();
				staffVO.setStaff_id(rs.getString("staff_id"));
				staffVO.setStaff_pwd(rs.getString("staff_pwd"));
				staffVO.setStaff_name(rs.getString("staff_name"));
				staffVO.setStaff_join(rs.getTimestamp("staff_join"));
				staffVO.setStaff_status(rs.getInt("staff_status"));
				list.add(staffVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	
//	 // 測試
//	public static void main(String[] args) {
//		StaffDAO dao = new StaffDAO();
//		
//		// 查
//		List<StaffVO> all = dao.getAll();
//		for(StaffVO staffVO:all) {
//			System.out.println(staffVO.getStaff_id());	
//			System.out.println(staffVO.getStaff_pwd());
//			System.out.println(staffVO.getStaff_name());
//			System.out.println(staffVO.getStaff_join());
//			System.out.println(staffVO.getStaff_status());
//		}
//		
//		// 增
//		StaffVO staffVO = new StaffVO();
//		staffVO.setStaff_id("staff_aaaa");
//		staffVO.setStaff_pwd("abc1234");
//		staffVO.setStaff_name("zachry");
//		dao.insert(staffVO);
//		
//		// 改
//		StaffVO staffVO = new StaffVO();
//		staffVO.setStaff_id("staff_aaaa");
//		staffVO.setStaff_pwd("abc1234");
//		staffVO.setStaff_name("zachry");
//		staffVO.setStaff_status(2);
//		dao.update(staffVO);
//		
//		// 用PK查詢
//		StaffVO staffVO = dao.findByPrimaryKey("staff_aaaa");
//		System.out.println(staffVO.getStaff_id());	
//		System.out.println(staffVO.getStaff_pwd());
//		System.out.println(staffVO.getStaff_name());
//		System.out.println(staffVO.getStaff_join());
//		System.out.println(staffVO.getStaff_status());
//	}

}
