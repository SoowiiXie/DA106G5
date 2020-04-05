package com.path_follow.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class Path_followJDBCDAO implements Path_followDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO path_follow (path_no,mb_id) values (?,?)";
	private static final String GET_ALL_STMT = "SELECT path_no,mb_id FROM path_follow ORDER BY mb_id";
	private static final String GET_ONE_STMT = "SELECT path_no,mb_id FROM path_follow WHERE path_no = ? and mb_id = ?";
	private static final String DELETE = "DELETE FROM path_follow where path_no = ? and mb_id = ?";
	private static final String UPDATE = "UPDATE path_follow SET path_no = ?, mb_id = ? where mb_id = ?";

	@Override
	public void insert(Path_followVO path_followVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, path_followVO.getPath_no());
			pstmt.setString(2, path_followVO.getMb_id());

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
	public void update(Path_followVO path_followVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, path_followVO.getPath_no());
			pstmt.setString(2, path_followVO.getMb_id());
			pstmt.setString(3, path_followVO.getMb_id());

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
	public void delete(String path_no, String mb_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, path_no);
			pstmt.setString(2, mb_id);

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
	public Path_followVO findByPrimaryKey(String path_no, String mb_id) {
		Path_followVO path_followVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, path_no);
			pstmt.setString(2, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				path_followVO = new Path_followVO();
				path_followVO.setPath_no(rs.getString("path_no"));
				path_followVO.setMb_id(rs.getString("mb_id"));
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
		return path_followVO;
	}



	@Override
	public List<Path_followVO> getAll() {
		List<Path_followVO> list = new ArrayList<Path_followVO>();
		Path_followVO path_followVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				path_followVO = new Path_followVO();
				path_followVO.setPath_no(rs.getString("path_no"));
				path_followVO.setMb_id(rs.getString("mb_id"));
				list.add(path_followVO); // Store the row in the list
		
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

		Path_followJDBCDAO dao = new Path_followJDBCDAO();

//		//新增
//		Path_followVO path_followVO = new Path_followVO();
//		path_followVO.setPath_no("p00005");
//		path_followVO.setMb_id("soowii123");
//		dao.insert(path_followVO);
		

//		//修改?
//		Path_followVO path_followVO2 = new Path_followVO();
//		path_followVO2.setPath_no("p00004");
//		path_followVO2.setMb_id("vain123");
//		path_followVO2.setMb_id("vain123");
//		dao.update(path_followVO2);

//		//刪除
//		dao.delete("p00005", "soowii123");

//		//查詢
//		Path_followVO path_followVO3 = dao.findByPrimaryKey("p00001", "soowii123");
//		System.out.print(path_followVO3.getPath_no() + ",");
//		System.out.print(path_followVO3.getMb_id() + "\n");
//		System.out.println("---------------------");

//		//查詢getall
//		List<Path_followVO> list = dao.getAll();
//		for (Path_followVO path_followVO : list) {
//			System.out.print(path_followVO.getPath_no() + ",");
//			System.out.print(path_followVO.getMb_id());
//
//			System.out.println();
//		}
		
		
	}

}
