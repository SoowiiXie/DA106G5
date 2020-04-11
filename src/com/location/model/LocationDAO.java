package com.location.model;

import static com.common.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class LocationDAO implements Location_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO Location (loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic) VALUES ('loc'||LPAD(to_char(loc_no_seq.NEXTVAL), 5, '0'),?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE Location set loc_typeno=?, longitude=?, latitude=?, loc_status=?, loc_address=?, loc_pic=? where loc_no = ?";
	private static final String DELETE = "DELETE from Location where loc_no = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM Location where loc_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM Location order by loc_no";
	private static final String GET_BY_LOC_TYPENO = "SELECT * FROM Location where loc_typeno = ? order by loc_no";

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
	public void insert(LocationVO locationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String next_loc_no = null;

		try {
//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			String cols[] = { "loc_no" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

//			pstmt.setString(1, locationVO.getLoc_no());
			pstmt.setString(1, locationVO.getLoc_typeno());
			pstmt.setString(2, locationVO.getLongitude());
			pstmt.setString(3, locationVO.getLatitude());
			pstmt.setInt(4, locationVO.getLoc_status());
			pstmt.setString(5, locationVO.getLoc_address());
			pstmt.setBytes(6, locationVO.getLoc_pic());

			pstmt.executeUpdate();
			// 取得對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_loc_no = rs.getString(1);
				System.out.println("自增主鍵值= " + next_loc_no + "(剛新增成功的編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			// Handle any driver errors
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
	public void update(LocationVO locationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(7, locationVO.getLoc_no());
			pstmt.setString(1, locationVO.getLoc_typeno());
			pstmt.setString(2, locationVO.getLongitude());
			pstmt.setString(3, locationVO.getLatitude());
			pstmt.setInt(4, locationVO.getLoc_status());
			pstmt.setString(5, locationVO.getLoc_address());
			pstmt.setBytes(6, locationVO.getLoc_pic());

			pstmt.executeUpdate();
			// Handle any driver errors
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
	public void delete(String loc_typeno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, loc_typeno);

			rs = pstmt.executeQuery();

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
	}

	@Override
	public LocationVO findByPrimaryKey(String loc_no) {
		LocationVO locationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, loc_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				locationVO = new LocationVO();
				locationVO.setLoc_no(rs.getString("loc_no"));
				locationVO.setLoc_typeno(rs.getString("loc_typeno"));
				locationVO.setLongitude(rs.getString("longitude"));
				locationVO.setLatitude(rs.getString("latitude"));
				locationVO.setLoc_status(rs.getInt("loc_status"));
				locationVO.setLoc_address(rs.getString("loc_address"));
				locationVO.setLoc_pic(rs.getBytes("loc_pic"));
			}

			// Handle any driver errors
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
		return locationVO;
	}

	@Override
	public List<LocationVO> getAll() {
		List<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO locationVO = null;

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
				locationVO = new LocationVO();
				locationVO.setLoc_no(rs.getString("loc_no"));
				locationVO.setLoc_typeno(rs.getString("loc_typeno"));
				locationVO.setLongitude(rs.getString("longitude"));
				locationVO.setLatitude(rs.getString("latitude"));
				locationVO.setLoc_status(rs.getInt("loc_status"));
				locationVO.setLoc_address(rs.getString("loc_address"));
				locationVO.setLoc_pic(rs.getBytes("loc_pic"));

				list.add(locationVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public Set<LocationVO> getLocationByLoc_typeno(String loc_typeno) {
		Set<LocationVO> set = new HashSet<LocationVO>();
		LocationVO locationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_LOC_TYPENO);
			pstmt.setString(1, loc_typeno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				locationVO = new LocationVO();
				locationVO.setLoc_no(rs.getString("loc_no"));
				locationVO.setLoc_typeno(rs.getString("loc_typeno"));
				locationVO.setLongitude(rs.getString("longitude"));
				locationVO.setLatitude(rs.getString("latitude"));
				locationVO.setLoc_status(rs.getInt("loc_status"));
				locationVO.setLoc_address(rs.getString("loc_address"));
				locationVO.setLoc_pic(rs.getBytes("loc_pic"));

				set.add(locationVO); // Store the row in the list
			}

			// Handle any driver errors
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
		return set;
	}
	
	@Override
	public List<LocationVO> getAllUWish(Map<String, String[]> map, String orderBy) {
		StringBuilder sb = new StringBuilder();

		List<LocationVO> list_map = new ArrayList<LocationVO>();
		LocationVO locationVO_map = null;

		Connection con = null;
		PreparedStatement pstmt_map = null;
		ResultSet rs_map = null;

		sb.append("SELECT * FROM location where ");
		for (Entry<String, String[]> entry : map.entrySet()) {
			sb.append("(");
			for (int i = 0; i < entry.getValue().length; i++) {
				if (i == 0) {
					sb.append(entry.getKey() + " like '%" + entry.getValue()[i] + "%'");
				} else {
					sb.append(" OR " + entry.getKey() + " like '%" + entry.getValue()[i] + "%'");
				}
			}
			sb.append(") and ");
		}
		sb.append(" 1=1 order by " + orderBy);

		try {

//			Class.forName(DRIVER_CLASS);
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
			con = ds.getConnection();
			pstmt_map = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs_map = pstmt_map.executeQuery();
			while (rs_map.next()) {
				// empVO 也稱為 Domain objects
				locationVO_map = new LocationVO();
				locationVO_map.setLoc_no(rs_map.getString("loc_no"));
				locationVO_map.setLoc_typeno(rs_map.getString("loc_typeno"));
				locationVO_map.setLongitude(rs_map.getString("longitude"));
				locationVO_map.setLatitude(rs_map.getString("latitude"));
				locationVO_map.setLoc_status(rs_map.getInt("loc_status"));
				locationVO_map.setLoc_address(rs_map.getString("loc_address"));
				locationVO_map.setLoc_pic(rs_map.getBytes("loc_pic"));
				list_map.add(locationVO_map); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs_map != null) {
				try {
					rs_map.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt_map != null) {
				try {
					pstmt_map.close();
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
		return list_map;
	}

}