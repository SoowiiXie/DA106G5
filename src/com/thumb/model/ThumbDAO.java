package com.thumb.model;

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

public class ThumbDAO implements Thumb_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO thumb (rcd_no,mb_id) values (?,?)";
//	private static final String GET_ALL_STMT = "SELECT rcd_no,mb_id FROM thumb ORDER BY mb_id";
	private static final String GET_ONE_STMT = "SELECT * FROM thumb WHERE rcd_no=?";
	private static final String COUNT_ALL = "SELECT COUNT ('a Thumb') AS COUNTTHUMBS FROM THUMB WHERE rcd_no = ?";
	private static final String COUNT_ONE = "SELECT COUNT ('a Thumb') AS COUNTTHUMB FROM THUMB WHERE rcd_no = ? AND mb_id = ?";
	private static final String DELETE = "DELETE FROM thumb where rcd_no = ? and mb_id = ?";
//	private static final String UPDATE = "UPDATE thumb SET rcd_no = ?, mb_id = ? where mb_id = ?";

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
	public void insert(ThumbVO thumbVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, thumbVO.getRcd_no());
			pstmt.setString(2, thumbVO.getMb_id());

			pstmt.executeUpdate();

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

//	@Override
//	public void update(ThumbVO thumbVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
////			Class.forName(DRIVER_CLASS);
////			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//
//			pstmt.setString(1, thumbVO.getRcd_no());
//			pstmt.setString(2, thumbVO.getMb_id());
//			pstmt.setString(3, thumbVO.getMb_id());
//
//			pstmt.executeUpdate();
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}

	@Override
	public void delete(String rcd_no, String mb_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, rcd_no);
			pstmt.setString(2, mb_id);

			pstmt.executeUpdate();

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

//	@Override
//	public ThumbVO findByPrimaryKey(String rcd_no, String mb_id) {
//		ThumbVO thumbVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
////			Class.forName(DRIVER_CLASS);
////			con = DriverManager.getConnection(URL, USER, PASSWORD);
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ONE_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//
//			pstmt.setString(1, rcd_no);
//			pstmt.setString(2, mb_id);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				thumbVO = new ThumbVO();
//				thumbVO.setRcd_no(rs.getString("rcd_no"));
//				thumbVO.setMb_id(rs.getString("mb_id"));
//			}
//
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return thumbVO;
//	}

	@Override
	public List<ThumbVO> getAllByRcd_no(String rcd_no) {
		List<ThumbVO> list = new ArrayList<ThumbVO>();
		ThumbVO thumbVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setString(1, rcd_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				thumbVO = new ThumbVO();
				thumbVO.setRcd_no(rs.getString("rcd_no"));
				thumbVO.setMb_id(rs.getString("mb_id"));
				list.add(thumbVO); // Store the row in the list
			}

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

	@Override
	public Integer countAllThumbs(String rcd_no) {
		// TODO Auto-generated method stub
		Integer thumbs = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT_ALL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, rcd_no);
			rs = pstmt.executeQuery();
			rs.next();
			thumbs = Integer.parseInt(rs.getString("COUNTTHUMBS"));

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
		return thumbs;
	}
	
	@Override
	public Integer countOneThumb(String rcd_no, String mb_id) {
		// TODO Auto-generated method stub
		Integer thumb = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT_ONE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, rcd_no);
			pstmt.setString(2, mb_id);
			rs = pstmt.executeQuery();
			rs.next();
			thumb = Integer.parseInt(rs.getString("COUNTTHUMB"));
			
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
		return thumb;
	}

}
