package com.thumb.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThumbJDBCDAO implements ThumbDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO thumb (rcd_no,mb_id) values (?,?)";
	private static final String GET_ALL_STMT = "SELECT rcd_no,mb_id FROM thumb ORDER BY mb_id";
	private static final String GET_ONE_STMT = "SELECT rcd_no,mb_id FROM thumb WHERE rcd_no = ? and mb_id = ?";
	private static final String DELETE = "DELETE FROM thumb where rcd_no = ? and mb_id = ?";
	private static final String UPDATE = "UPDATE thumb SET rcd_no = ?, mb_id = ? where mb_id = ?";

	@Override
	public void insert(ThumbVO thumbVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, thumbVO.getRcd_no());
			pstmt.setString(2, thumbVO.getMb_id());

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
	public void update(ThumbVO thumbVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, thumbVO.getRcd_no());
			pstmt.setString(2, thumbVO.getMb_id());
			pstmt.setString(3, thumbVO.getMb_id());

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
	public void delete(String rcd_no, String mb_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rcd_no);
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
	public ThumbVO findByPrimaryKey(String rcd_no, String mb_id) {
		ThumbVO thumbVO = null;
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
				thumbVO = new ThumbVO();
				thumbVO.setRcd_no(rs.getString("rcd_no"));
				thumbVO.setMb_id(rs.getString("mb_id"));
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
		return thumbVO;
	}

	@Override
	public List<ThumbVO> getAll() {
		List<ThumbVO> list = new ArrayList<ThumbVO>();
		ThumbVO thumbVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				thumbVO = new ThumbVO();
				thumbVO.setRcd_no(rs.getString("Rcd_no"));
				thumbVO.setMb_id(rs.getString("mb_id"));
				list.add(thumbVO); // Store the row in the list

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

		ThumbJDBCDAO dao = new ThumbJDBCDAO();

//		//新增
//		ThumbVO thumbVO1 = new ThumbVO();
//		thumbVO1.setRcd_no("rcd00005");
//		thumbVO1.setMb_id("soowii123");
//		dao.insert(thumbVO1);

//		//修改
//		ThumbVO thumbVO2 = new ThumbVO();
//		thumbVO2.setRcd_no("rcd00005");
//		thumbVO2.setMb_id("yiwen123");
//		thumbVO2.setMb_id("yiwen123");
//		dao.update(thumbVO2);

//		//刪除
//		dao.delete("rcd00005", "soowii123");

//		//查詢
//		ThumbVO thumbVO3 = dao.findByPrimaryKey("rcd00001", "soowii123");
//		System.out.print(thumbVO3.getRcd_no() + ",");
//		System.out.print(thumbVO3.getMb_id() + "\n");
//		System.out.println("---------------------");

//		//查詢getall
//		List<ThumbVO> list = dao.getAll();
//		for (ThumbVO thumbVO : list) {
//			System.out.print(thumbVO.getRcd_no() + ",");
//			System.out.print(thumbVO.getMb_id());
//
//			System.out.println();
//		}

	}

}
