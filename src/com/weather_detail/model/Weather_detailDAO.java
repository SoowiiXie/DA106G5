package com.weather_detail.model;

import static com.common.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.Map.Entry;

public class Weather_detailDAO implements Weather_detail_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO Weather_detail (WEATHER_TIME, WEATHER_PLACE, WTH_STATUS, WTH_HIGH, WTH_LOW, WTH_COMFORT, WTH_RAIN_CHANCE) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE Weather_detail set WTH_STATUS=?, WTH_HIGH=?, WTH_LOW=?, WTH_COMFORT=?, WTH_RAIN_CHANCE=? where WEATHER_TIME=? and WEATHER_PLACE=?";
	private static final String DELETE = "DELETE from Weather_detail where WEATHER_TIME=? and WEATHER_PLACE=?";
	private static final String GET_ONE_STMT = "SELECT * FROM Weather_detail where WEATHER_TIME=? and WEATHER_PLACE=?";
	private static final String GET_ALL_STMT = "SELECT * FROM Weather_detail order by WEATHER_PLACE , WEATHER_TIME";

	@Override
	public void insert(Weather_detailVO weather_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			// WEATHER_TIME, WEATHER_PLACE, WTH_STATUS, WTH_HIGH, WTH_LOW, WTH_COMFORT,
			// WTH_RAIN_CHANCE
			pstmt.setTimestamp(1, weather_detailVO.getWeather_time());
			pstmt.setString(2, weather_detailVO.getWeather_place());
			pstmt.setString(3, weather_detailVO.getWth_status());
			pstmt.setInt(4, weather_detailVO.getWth_high());
			pstmt.setInt(5, weather_detailVO.getWth_low());
			pstmt.setString(6, weather_detailVO.getWth_comfort());
			pstmt.setInt(7, weather_detailVO.getWth_rain_chance());

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
	public void update(Weather_detailVO weather_detailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setTimestamp(6, weather_detailVO.getWeather_time());
			pstmt.setString(7, weather_detailVO.getWeather_place());
			pstmt.setString(1, weather_detailVO.getWth_status());
			pstmt.setInt(2, weather_detailVO.getWth_high());
			pstmt.setInt(3, weather_detailVO.getWth_low());
			pstmt.setString(4, weather_detailVO.getWth_comfort());
			pstmt.setInt(5, weather_detailVO.getWth_rain_chance());

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
	public void delete(Timestamp weather_time, String weather_place) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setTimestamp(1, weather_time);
			pstmt.setString(2, weather_place);

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
	public Weather_detailVO findByPrimaryKey(Timestamp weather_time, String weather_place) {
		Weather_detailVO weather_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setTimestamp(1, weather_time);
			pstmt.setString(2, weather_place);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				weather_detailVO = new Weather_detailVO();
				// WEATHER_TIME, WEATHER_PLACE, WTH_STATUS, WTH_HIGH, WTH_LOW, WTH_COMFORT,
				// WTH_RAIN_CHANCE
				weather_detailVO.setWeather_time(rs.getTimestamp("WEATHER_TIME"));
				weather_detailVO.setWeather_place(rs.getString("WEATHER_PLACE"));
				weather_detailVO.setWth_status(rs.getString("WTH_STATUS"));
				weather_detailVO.setWth_high(rs.getInt("WTH_HIGH"));
				weather_detailVO.setWth_low(rs.getInt("WTH_LOW"));
				weather_detailVO.setWth_comfort(rs.getString("WTH_COMFORT"));
				weather_detailVO.setWth_rain_chance(rs.getInt("WTH_RAIN_CHANCE"));
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
		return weather_detailVO;
	}

	@Override
	public List<Weather_detailVO> getAll() {
		List<Weather_detailVO> list = new ArrayList<Weather_detailVO>();
		Weather_detailVO weather_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				weather_detailVO = new Weather_detailVO();
				// WEATHER_TIME, WEATHER_PLACE, WTH_STATUS, WTH_HIGH, WTH_LOW, WTH_COMFORT,
				// WTH_RAIN_CHANCE
				weather_detailVO.setWeather_time(rs.getTimestamp("WEATHER_TIME"));
				weather_detailVO.setWeather_place(rs.getString("WEATHER_PLACE"));
				weather_detailVO.setWth_status(rs.getString("WTH_STATUS"));
				weather_detailVO.setWth_high(rs.getInt("WTH_HIGH"));
				weather_detailVO.setWth_low(rs.getInt("WTH_LOW"));
				weather_detailVO.setWth_comfort(rs.getString("WTH_COMFORT"));
				weather_detailVO.setWth_rain_chance(rs.getInt("WTH_RAIN_CHANCE"));

				list.add(weather_detailVO); // Store the row in the list
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
	public List<Weather_detailVO> getAllUWish(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();

		List<Weather_detailVO> list_map = new ArrayList<Weather_detailVO>();
		Weather_detailVO weather_detailVO_map = null;

		Connection con = null;
		PreparedStatement pstmt_map = null;
		ResultSet rs_map = null;

		sb.append("SELECT * FROM Weather_detail where ");
		for (Entry<String, String[]> entry : map.entrySet()) {
			sb.append("(");
			for (int i = 0; i < entry.getValue().length; i++) {
				if (i == 0) {
					sb.append(entry.getKey() + " = " + entry.getValue()[i]);
				} else {
					sb.append(" OR " + entry.getKey() + " = " + entry.getValue()[i]);
				}
			}
			sb.append(") and ");
		}
		sb.append(" 1=1 ");

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt_map = con.prepareStatement(sb.toString(),ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs_map = pstmt_map.executeQuery();
			while (rs_map.next()) {
				// empVO 也稱為 Domain objects
				weather_detailVO_map = new Weather_detailVO();
				// WEATHER_TIME, WEATHER_PLACE, WTH_STATUS, WTH_HIGH, WTH_LOW, WTH_COMFORT,
				// WTH_RAIN_CHANCE
				weather_detailVO_map.setWeather_time(rs_map.getTimestamp("WEATHER_TIME"));
				weather_detailVO_map.setWeather_place(rs_map.getString("WEATHER_PLACE"));
				weather_detailVO_map.setWth_status(rs_map.getString("WTH_STATUS"));
				weather_detailVO_map.setWth_high(rs_map.getInt("WTH_HIGH"));
				weather_detailVO_map.setWth_low(rs_map.getInt("WTH_LOW"));
				weather_detailVO_map.setWth_comfort(rs_map.getString("WTH_COMFORT"));
				weather_detailVO_map.setWth_rain_chance(rs_map.getInt("WTH_RAIN_CHANCE"));

				list_map.add(weather_detailVO_map); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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