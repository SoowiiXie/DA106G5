package com.metoo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MetooJDBCDAO implements MetooDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO metoo (rcd_no,mb_id) values (?,?)";
	private static final String GET_ALL_STMT = "SELECT rcd_no,mb_id FROM metoo ORDER BY rcd_no";
	private static final String GET_ONE_STMT = "SELECT rcd_no,mb_id FROM metoo WHERE rcd_no=? and mb_id = ?";
	private static final String DELETE = "DELETE FROM metoo where mb_id = ? and rcd_no=?";
	private static final String UPDATE = "UPDATE path_follow SET path_no = ?, mb_id = ? where mb_id = ?";

	@Override
	public void insert(MetooVO metooVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, metooVO.getRcd_no());
			pstmt.setString(2, metooVO.getMb_id());

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
	public void update(MetooVO metooVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, metooVO.getRcd_no());
			pstmt.setString(2, metooVO.getMb_id());
			pstmt.setString(3, metooVO.getMb_id());

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
	public void delete(String mb_id, String rcd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mb_id);
			pstmt.setString(2, rcd_no);

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
	public MetooVO findByPrimaryKey(String rcd_no, String mb_id) {
		MetooVO metooVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rcd_no);
			pstmt.setString(2, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				metooVO = new MetooVO();
				metooVO.setRcd_no(rs.getString("rcd_no"));
				metooVO.setMb_id(rs.getString("mb_id"));
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
		return metooVO;
	}

	@Override
	public List<MetooVO> getAll() {
		List<MetooVO> list = new ArrayList<MetooVO>();
		MetooVO metooVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				metooVO = new MetooVO();
				metooVO.setRcd_no(rs.getString("rcd_no"));
				metooVO.setMb_id(rs.getString("mb_id"));
				list.add(metooVO); // Store the row in the list
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

		MetooJDBCDAO dao = new MetooJDBCDAO();

//		//新增
//		MetooVO metooVO1 = new MetooVO();
//		metooVO1.setRcd_no("rcd00005");
//		metooVO1.setMb_id("soowii123");
//		dao.insert(metooVO1);

//		//修改??
//		MetooVO metooVO2 = new MetooVO();
//		metooVO2.setRcd_no("rcd00004");
//		metooVO2.setMb_id("vain123");
//		metooVO2.setMb_id("vain123");

//		//刪除
//		dao.delete("soowii123", "rcd00005");

//		//查詢
//		MetooVO metooVO3 = dao.findByPrimaryKey("rcd00001", "soowii123");
//		if (metooVO3 != null) {
//			System.out.print(metooVO3.getMb_id() + ",");
//			System.out.print(metooVO3.getRcd_no());
//		} else {
//			System.out.println("沒有找到資料");
//		}

		//查詢getall
//		List<MetooVO> list = dao.getAll();
//		for (MetooVO metooVO : list) {
//			System.out.print(metooVO.getRcd_no() + ",");
//			System.out.print(metooVO.getMb_id()+"\n");
//		}
		
		
		
	}

}
