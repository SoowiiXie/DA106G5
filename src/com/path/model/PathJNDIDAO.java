package com.path.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class PathJNDIDAO implements PathDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO PATH (path_no) values ('p'||LPAD(to_char(PATH_NO_SEQ.nextval), 5, '0'))";
	private static final String GET_ALL_STMT = "SELECT path_no, path_name, path_difficulty, path_popular, path_start, path_end, path_distance, path_status, path_kml, path_lng, path_lat FROM path ORDER BY path_no";
	private static final String GET_ONE_STMT = "SELECT path_no, path_name, path_difficulty, path_popular, path_start, path_end, path_distance, path_status, path_kml, path_lng, path_lat FROM path WHERE path_no = ?";
	private static final String DELETE = "DELETE FROM path where path_no = ?";
	private static final String UPDATE = "UPDATE path SET path_name = ?, path_difficulty = ?, path_end = ?, path_distance = ?, path_kml = ?, path_lng = ?, path_lat = ? , path_pic = ? where path_no = ?";
	private static final String GET_IMAGE = "SELECT path_pic FROM path WHERE path_no = ?";
	
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
	public PathVO insert(PathVO pathVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			
			String cols[] = {"path_no"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);
			pstmt.executeUpdate();
			
			String path_no = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				path_no = rs.getString(1);
System.out.println("path_no = "+path_no);
			}else {
				System.out.println("未取得path_no");
			}
			rs.close();
			
			pathVO.setPath_no(path_no);
//System.out.println("OK");
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
		return pathVO;
	}

	@Override
	public void update(PathVO pathVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, pathVO.getPath_name());
			pstmt.setInt(2, pathVO.getPath_difficulty());
			pstmt.setTimestamp(3, pathVO.getPath_end());
			pstmt.setDouble(4, pathVO.getPath_distance());
			pstmt.setString(5, pathVO.getPath_kml());
			pstmt.setDouble(6, pathVO.getPath_lng());
			pstmt.setDouble(7, pathVO.getPath_lat());
			pstmt.setBytes(8, pathVO.getPath_pic());
			pstmt.setString(9, pathVO.getPath_no());
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
	public void delete(String path_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, path_no);

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
	public PathVO findByPrimaryKey(String path_no) {
		PathVO pathVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
	public byte[] getImage(String path_no) {
		byte[] picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con .prepareStatement(GET_IMAGE);
			
			pstmt.setString(1, path_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				picture = rs.getBytes(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured."+ e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}
		return picture;
	}
	

	public static void main(String[] args) {

		PathJNDIDAO dao = new PathJNDIDAO();

		//新增
//		PathVO pathVO1 = new PathVO();
//		dao.insert(pathVO1);

////		//修改
//		PathVO pathVO2 = new PathVO();
//		pathVO2.setPath_name("大操場");
//		pathVO2.setPath_difficulty(2);
//		pathVO2.setPath_end(java.sql.Timestamp.valueOf("2020-04-13 22:22:22"));
//		pathVO2.setPath_distance(123.05);
//		pathVO2.setPath_kml("[{\"LNG\":\"121.199769\",\"LAT\":\"24.963707\"},{\"LNG\":\"121.199770\"}]");
//		pathVO2.setPath_lng(121.199769);
//		pathVO2.setPath_lat(24.963707);
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
