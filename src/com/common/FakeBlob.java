package com.common;

import static com.common.Common.DRIVER_CLASS;
import static com.common.Common.PASSWORD;
import static com.common.Common.URL;
import static com.common.Common.USER;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class FakeBlob {
	public static void main(String[] args) {
		try {
			Class.forName(DRIVER_CLASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		// 存圖片到資料庫
		String sql_updateLoc = "UPDATE LOCATION SET LOC_PIC = ? WHERE LOC_NO = ?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_updateLoc);) {
			int rowCount = 0;
			FileInputStream in;
			BufferedInputStream bf;
			// 塞六張假圖
			for (int i = 1; i <= 6; i++) {
				// 共五位數字，不足補0
				in = new FileInputStream("WebContent/fake_picture/loc" + String.format("%05d", i) + ".jpg");
				bf = new BufferedInputStream(in);
				// 方法1.使用byte陣列setBytes
//			    byte[] image = new byte[bf.available()];//讀入的圖檔,暫存在記憶體
//			    bf.read(image);
//			    ps.setBytes(1, image);

				// 方法2.讀入圖檔後setBinaryStream到DB
				ps.setBinaryStream(1, bf, bf.available());// 如果不用顯示在使用者裝置直接寫入DB

				ps.setString(2, "loc" + String.format("%05d", i));
				rowCount += ps.executeUpdate();
				bf.close();
				in.close();
			}
			System.out.println(rowCount + " row(s) inserted!!");
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 存圖片到資料庫
		String sql_updateWth = "UPDATE Weather SET weather_pic = ? WHERE wth_status = ?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(sql_updateWth);) {
			int rowCount = 0;
			FileInputStream in;
			BufferedInputStream bf;
			// 不跑回圈塞圖
			// 第一張
			in = new FileInputStream("WebContent/fake_picture/weather00001.png");
			bf = new BufferedInputStream(in);
			ps.setBinaryStream(1, bf, bf.available());// 如果不用顯示在使用者裝置直接寫入DB
			ps.setString(2, "晴");
			rowCount += ps.executeUpdate();
			// 第二張
			in = new FileInputStream("WebContent/fake_picture/weather00002.jpg");
			bf = new BufferedInputStream(in);
			ps.setBinaryStream(1, bf, bf.available());// 如果不用顯示在使用者裝置直接寫入DB
			ps.setString(2, "雨");
			rowCount += ps.executeUpdate();
			// 第三張
			in = new FileInputStream("WebContent/fake_picture/weather00003.jpg");
			bf = new BufferedInputStream(in);
			ps.setBinaryStream(1, bf, bf.available());// 如果不用顯示在使用者裝置直接寫入DB
			ps.setString(2, "陰");
			rowCount += ps.executeUpdate();

			System.out.println(rowCount + " row(s) inserted!!");
			bf.close();
			in.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 8個會員
		String[] mb_id = {"soowii123","xuan123","michael123","vain123","yiwen123","weijhih123","androidlababy520","anjavababy520"};
		
		Connection con = null;
		PreparedStatement pstmt = null;
		InputStream fin = null;
		int count = 0;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "DA106G5", "DA106G5");
			pstmt = con.prepareStatement("UPDATE MEMBER SET MB_PIC=? WHERE MB_ID = ?");
			// 6張照片
			for(int i = 0; i < 8; i++) {
				
				fin = new FileInputStream(new File("WebContent/fake_picture", "mb" + (i+1) + ".jpg")); 
				
				pstmt.setBinaryStream(1, fin, fin.available());
				pstmt.setString(2, mb_id[i]);
				count += pstmt.executeUpdate();
				
			}
			System.out.println(count + " images inserted!!");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			// Clean up JDBC resources
		} finally {
			if(fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace(System.err);
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
}