package com.location.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class LocationDAO2{

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