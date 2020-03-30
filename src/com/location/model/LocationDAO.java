package com.location.model;

import static com.common.Common.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LocationDAO implements Location_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO Location (loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE Location set loc_typeno=?, longitude=?, latitude=?, loc_status=?, loc_address=?, loc_pic=? where loc_no = ?";
	private static final String DELETE = "DELETE from Location where loc_no = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM Location where loc_no = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM Location order by loc_no";
	private static final String GET_BY_LOC_TYPENO = "SELECT * FROM Location where loc_typeno = ? order by loc_no";

	@Override
	public void insert(LocationVO locationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, locationVO.getLoc_no());
			pstmt.setString(2, locationVO.getLoc_typeno());
			pstmt.setString(3, locationVO.getLongitude());
			pstmt.setString(4, locationVO.getLatitude());
			pstmt.setInt(5, locationVO.getLoc_status());
			pstmt.setString(6, locationVO.getLoc_address());
			pstmt.setBytes(7, locationVO.getLoc_pic());

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
	public void update(LocationVO locationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

	@Override
	public Set<LocationVO> getLocationByLoc_typeno(String loc_typeno) {
		Set<LocationVO> set = new HashSet<LocationVO>();
		LocationVO locationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
		return set;
	}

	// 測試
	public static void main(String[] args) {
		LocationDAO dao = new LocationDAO();

		// 增 loc_no, loc_typeno, longitude, latitude, loc_status, loc_address, loc_pic
		LocationVO locationVO_insert = new LocationVO();
		locationVO_insert.setLoc_no("loc00007");
		locationVO_insert.setLoc_typeno("3");
		locationVO_insert.setLongitude("123.456");
		locationVO_insert.setLatitude("78.90");
		locationVO_insert.setLoc_status(7);// 有給default
		locationVO_insert.setLoc_address("桃園");
		FileInputStream in;
		BufferedInputStream bf;
		try {
			in = new FileInputStream("fake_picture/loc" + String.format("%05d", 6) + ".jpg");
			bf = new BufferedInputStream(in);
			byte[] image = new byte[bf.available()];// 讀入的圖檔,暫存在記憶體
			bf.read(image);
			locationVO_insert.setLoc_pic(image);
			bf.close();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.insert(locationVO_insert);

		// 改
		LocationVO locationVO_update = new LocationVO();
		locationVO_update.setLoc_no("loc00007");
		locationVO_update.setLoc_typeno("3");
		locationVO_update.setLongitude("123.456");
		locationVO_update.setLatitude("78.90");
		locationVO_update.setLoc_status(7);// 有給default
		locationVO_update.setLoc_address("全家就是我家");
		try {
			in = new FileInputStream("fake_picture/loc" + String.format("%05d", 6) + ".jpg");
			bf = new BufferedInputStream(in);
			byte[] image = new byte[bf.available()];// 讀入的圖檔,暫存在記憶體
			bf.read(image);
			locationVO_update.setLoc_pic(image);
			bf.close();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.update(locationVO_update);

		// 用PK查詢
		LocationVO locationVO_getByPK = dao.findByPrimaryKey("loc00007");
		System.out.println("新增並修改的PK是:" + locationVO_getByPK.getLoc_no());
		System.out.print("修改後的值是:");
		System.out.print(locationVO_getByPK.getLoc_typeno() + ",");
		System.out.print(locationVO_getByPK.getLongitude() + ",");
		System.out.print(locationVO_getByPK.getLatitude() + ",");
		System.out.print(locationVO_getByPK.getLoc_status() + ",");
		System.out.print(locationVO_getByPK.getLoc_address() + ",");
		System.out.println(locationVO_getByPK.getLoc_pic());

		// 刪
		dao.delete("loc00007");

		// 查
		List<LocationVO> all = dao.getAll();
		System.out.println("\n刪掉剛剛新增的後剩:");
		for (LocationVO locationVO : all) {
			System.out.println(locationVO.getLoc_no() + "," + locationVO.getLoc_typeno() + ","
					+ locationVO.getLongitude() + "," + locationVO.getLatitude() + "," + locationVO.getLoc_status()
					+ "," + locationVO.getLoc_address() + "," + locationVO.getLoc_pic());
		}

		// 查
		Set<LocationVO> loc_typenoAll = dao.getLocationByLoc_typeno("1");
		System.out.println("\n取得所有Loc_typeno=1的地標:");
		for (LocationVO locationVO : loc_typenoAll) {
			System.out.println(locationVO.getLoc_no() + "," + locationVO.getLoc_typeno() + ","
					+ locationVO.getLongitude() + "," + locationVO.getLatitude() + "," + locationVO.getLoc_status()
					+ "," + locationVO.getLoc_address() + "," + locationVO.getLoc_pic());
		}

	}

}