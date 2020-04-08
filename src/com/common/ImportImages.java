package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportImages {

	public static void main(String[] args) {
		
		// 6個會員
		String[] mb_id = {"soowii123","xuan123","michael123","vain123","yiwen123","weijhih123"};
		
		Connection con = null;
		PreparedStatement pstmt = null;
		InputStream fin = null;
		int count = 0;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "DA106G5", "DA106G5");
			pstmt = con.prepareStatement("UPDATE MEMBER SET MB_PIC=? WHERE MB_ID = ?");
			// 6張照片
			for(int i = 0; i < 6; i++) {
				
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
