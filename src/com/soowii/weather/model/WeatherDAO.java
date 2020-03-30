package com.soowii.weather.model;

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

public class WeatherDAO implements Weather_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO Weather (wth_status, weather_pic) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE Weather set weather_pic=? where wth_status = ?";
	private static final String DELETE = "DELETE from Weather where wth_status = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM Weather where wth_status = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM Weather order by wth_status";

	@Override
	public void insert(WeatherVO weatherVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, weatherVO.getWth_status());
			pstmt.setBytes(2, weatherVO.getWeather_pic());

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
	public void update(WeatherVO weatherVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(2, weatherVO.getWth_status());
			pstmt.setBytes(1, weatherVO.getWeather_pic());


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
	public void delete(String wth_status) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, wth_status);


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
	public WeatherVO findByPrimaryKey(String wth_status) {
		WeatherVO weatherVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, wth_status);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				weatherVO = new WeatherVO();
				weatherVO.setWth_status(rs.getString("wth_status"));
				weatherVO.setWeather_pic(rs.getBytes("weather_pic"));
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
		return weatherVO;
	}

	@Override
	public List<WeatherVO> getAll() {
		List<WeatherVO> list = new ArrayList<WeatherVO>();
		WeatherVO weatherVO = null;

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
				weatherVO = new WeatherVO();
				weatherVO.setWth_status(rs.getString("wth_status"));
				weatherVO.setWeather_pic(rs.getBytes("weather_pic"));

				list.add(weatherVO); // Store the row in the list
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

	// 測試
	public static void main(String[] args) {
		WeatherDAO dao = new WeatherDAO();

		// 增
		WeatherVO weatherVO_insert = new WeatherVO();
		weatherVO_insert.setWth_status("愛情來的太快像龍捲風");
		FileInputStream in;
		BufferedInputStream bf;
		try {
			in = new FileInputStream("FakePicture/weather00003.jpg");
			bf = new BufferedInputStream(in);
			byte[] image = new byte[bf.available()];// 讀入的圖檔,暫存在記憶體
			bf.read(image);
			weatherVO_insert.setWeather_pic(image);
			bf.close();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.insert(weatherVO_insert);

		// 改
		WeatherVO weatherVO_update = new WeatherVO();
		weatherVO_update.setWth_status("愛情來的太快像龍捲風");
		try {
			in = new FileInputStream("FakePicture/weather00001.png");
			bf = new BufferedInputStream(in);
			byte[] image = new byte[bf.available()];// 讀入的圖檔,暫存在記憶體
			bf.read(image);
			weatherVO_update.setWeather_pic(image);
			bf.close();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.update(weatherVO_update);

		// 用PK查詢
		WeatherVO weatherVO_getByPK = dao.findByPrimaryKey("愛情來的太快像龍捲風");
		System.out.println("新增並修改的PK是:" + weatherVO_getByPK.getWth_status());
		System.out.print("修改後的值是:");
		System.out.println(weatherVO_getByPK.getWeather_pic());

		// 刪
		dao.delete("愛情來的太快像龍捲風");

		// 查
		List<WeatherVO> all = dao.getAll();
		System.out.println("\n刪掉剛剛新增的後剩:");
		for (WeatherVO weatherVO : all) {
			System.out.println(weatherVO.getWth_status()+","+weatherVO.getWeather_pic());
		}

	}

}