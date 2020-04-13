package com.group_follow.model;

import java.util.*;
import java.sql.*;


public class Group_followJDBCDAO implements Group_followDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
		"INSERT INTO grp_follow (GRP_NO,MB_ID) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT GRP_NO,MB_ID FROM grp_follow order by GRP_NO";
	private static final String GET_ONE_STMT = 
		"SELECT GRP_NO,MB_ID FROM grp_follow where GRP_NO = ?";
	private static final String DELETE = 
		"DELETE FROM grp_follow where GRP_NO = ?";
	private static final String UPDATE = 
		"UPDATE grp_follow set MB_ID=? where GRP_NO = ?";

	@Override
	public void insert(Group_followVO group_followVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, group_followVO.getGrp_no());
			pstmt.setString(2, group_followVO.getMb_id());

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
	public void update(Group_followVO group_followVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			
			pstmt.setString(1, group_followVO.getMb_id());
			pstmt.setString(2, group_followVO.getGrp_no());

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
	public void delete(String grp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, grp_no);

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
	public Group_followVO findByPrimaryKey(String grp_no) {

		Group_followVO group_followVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, grp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				group_followVO = new Group_followVO();
				group_followVO.setGrp_no(rs.getString("grp_no"));
				group_followVO.setMb_id(rs.getString("mb_id"));
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
		return group_followVO;
	}

	@Override
	public List<Group_followVO> getAll() {
		List<Group_followVO> list = new ArrayList<Group_followVO>();
		Group_followVO group_followVO = null;

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
				group_followVO = new Group_followVO();
				group_followVO.setGrp_no(rs.getString("grp_no"));
				group_followVO.setMb_id(rs.getString("mb_id"));
				list.add(group_followVO); // Store the row in the list
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

	public static void main(String[] args) {

		Group_followJDBCDAO dao = new Group_followJDBCDAO();
		
		// 新增
		Group_followVO group_followVO1 = new Group_followVO();		
		group_followVO1.setGrp_no("grp00004");
		group_followVO1.setMb_id("soowii123");		
		dao.insert(group_followVO1);
//		
//		// 修
//		Group_followVO group_followVO2 = new Group_followVO();
//		group_followVO2.setGrp_no("grp00002");
//		group_followVO2.setMb_id("xuan123");		
//		dao.update(group_followVO2);
//
//		// 刪
//		dao.delete("grp00004");

		//查
		Group_followVO group_followVO3 = dao.findByPrimaryKey("grp00001");
		System.out.print(group_followVO3.getGrp_no() + ",");
		System.out.println(group_followVO3.getMb_id() + ",");
		
		System.out.println("---------------------");

		// 查全部
		List<Group_followVO> list = dao.getAll();
		for (Group_followVO aGrp : list) {
			System.out.print(aGrp.getGrp_no() + ",");
			System.out.print(aGrp.getMb_id() + ",");
			System.out.println();
		}
	}
}