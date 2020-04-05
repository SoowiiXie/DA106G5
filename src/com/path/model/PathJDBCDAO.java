package com.path.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PathJDBCDAO implements PathDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO PATH (PATH_NO,PATH_NAME,PATH_DIFFICULTY,PATH_START,PATH_END,PATH_DISTANCE,PATH_KML,PATH_LNG,PATH_LAT) values ('p'||LPAD(to_char(PATH_NO_SEQ.nextval), 5, '0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT path_no, path_name, path_difficulty, path_popular, path_start, path_end, path_distance, path_status, path_kml, path_lng, path_lat FROM path ORDER BY path_no";
	private static final String GET_ONE_STMT = "SELECT path_no, path_name, path_difficulty, path_popular, path_start, path_end, path_distance, path_status, path_kml, path_lng, path_lat FROM path WHERE path_no = ?";
	private static final String DELETE = "DELETE FROM path where path_no = ?";
	private static final String UPDATE = "UPDATE path SET path_name = ?, path_difficulty = ? where path_no = ?";

	@Override
	public void insert(PathVO pathVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, pathVO.getPath_name());
			pstmt.setInt(2, pathVO.getPath_difficulty());
			pstmt.setTimestamp(3, pathVO.getPath_start());
			pstmt.setTimestamp(4, pathVO.getPath_end());
			pstmt.setDouble(5, pathVO.getPath_distance());
			pstmt.setString(6, pathVO.getPath_kml());
			pstmt.setDouble(7, pathVO.getPath_lng());
			pstmt.setDouble(8, pathVO.getPath_lat());

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
	public void update(PathVO pathVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pathVO.getPath_name());
			pstmt.setInt(2, pathVO.getPath_difficulty());
			pstmt.setString(3, pathVO.getPath_no());

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
	public void delete(String path_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, path_no);

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
	public PathVO findByPrimaryKey(String path_no) {
		PathVO pathVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, path_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				pathVO = new PathVO();
				pathVO.setPath_no(rs.getString("path_no"));
				pathVO.setPath_name(rs.getString("path_name"));
				pathVO.setPath_difficulty(rs.getInt("path_difficulty"));
				pathVO.setPath_popular(rs.getInt("path_popular"));
				pathVO.setPath_start(rs.getTimestamp("path_start"));
				pathVO.setPath_end(rs.getTimestamp("path_end"));
				pathVO.setPath_distance(rs.getDouble("path_distance"));
				pathVO.setPath_status(rs.getInt("path_status"));
				pathVO.setPath_kml(rs.getString("path_kml"));
				pathVO.setPath_lng(rs.getDouble("path_lng"));
				pathVO.setPath_lat(rs.getDouble("path_lat"));
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
		return pathVO;
	}

	@Override
	public List<PathVO> getAll() {
		List<PathVO> list = new ArrayList<PathVO>();
		PathVO pathVO = null;

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
				pathVO = new PathVO();
				pathVO.setPath_no(rs.getString("path_no"));
				pathVO.setPath_name(rs.getString("path_name"));
				pathVO.setPath_difficulty(rs.getInt("path_difficulty"));
				pathVO.setPath_popular(rs.getInt("path_popular"));
				pathVO.setPath_start(rs.getTimestamp("path_start"));
				pathVO.setPath_end(rs.getTimestamp("path_end"));
				pathVO.setPath_distance(rs.getDouble("path_distance"));
				pathVO.setPath_status(rs.getInt("path_status"));
				pathVO.setPath_kml(rs.getString("path_kml"));
				pathVO.setPath_lng(rs.getDouble("path_lng"));
				pathVO.setPath_lat(rs.getDouble("path_lat"));
				list.add(pathVO); // Store the row in the list
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

		PathJDBCDAO dao = new PathJDBCDAO();

//		//新增
//		PathVO pathVO1 = new PathVO();
//		pathVO1.setPath_name("PATH6");
//		pathVO1.setPath_difficulty(2);
//		pathVO1.setPath_start(java.sql.Timestamp.valueOf("2005-01-01 22:22:22"));
//		pathVO1.setPath_end(java.sql.Timestamp.valueOf("2005-01-01 22:44:22"));
//		pathVO1.setPath_distance(10.22);
//		pathVO1.setPath_kml("{}");
//		pathVO1.setPath_lng(20.2323);
//		pathVO1.setPath_lat(105.2323);
//		dao.insert(pathVO1);

//		//修改
//		PathVO pathVO2 = new PathVO();
//		pathVO2.setPath_name("大操場");
//		pathVO2.setPath_difficulty(2);
//		pathVO2.setPath_no("p00006");
//		dao.update(pathVO2);

//		//刪除
//		dao.delete("p00006");

//		//查詢
//		PathVO pathVO3 = dao.findByPrimaryKey("p00001");
//		System.out.print(pathVO3.getPath_no() + ",");
//		System.out.print(pathVO3.getPath_name() + ",");
//		System.out.print(pathVO3.getPath_difficulty() + ",");
//		System.out.print(pathVO3.getPath_popular() + ",");
//		System.out.print(pathVO3.getPath_start() + ",");
//		System.out.print(pathVO3.getPath_end() + ",");
//		System.out.println(pathVO3.getPath_distance()+ ",");
//		System.out.println(pathVO3.getPath_status()+ ",");
//		System.out.println(pathVO3.getPath_kml()+ ",");
//		System.out.println(pathVO3.getPath_lng()+ ",");
//		System.out.println(pathVO3.getPath_lat()+ ",");
//		System.out.println("---------------------");

//		//查詢getall
//		List<PathVO> list = dao.getAll();
//		for (PathVO pathVO : list) {
//			System.out.print(pathVO.getPath_no() + ",");
//			System.out.print(pathVO.getPath_name() + ",");
//			System.out.print(pathVO.getPath_difficulty() + ",");
//			System.out.print(pathVO.getPath_popular() + ",");
//			System.out.print(pathVO.getPath_start() + ",");
//			System.out.print(pathVO.getPath_end() + ",");
//			System.out.print(pathVO.getPath_distance() + ",");
//			System.out.print(pathVO.getPath_status() + ",");
//			System.out.print(pathVO.getPath_kml() + ",");
//			System.out.print(pathVO.getPath_lng() + ",");
//			System.out.print(pathVO.getPath_lat());
//			System.out.println();
//		}
		
		
	}

}
