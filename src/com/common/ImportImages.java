package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.xuan.mb.model.*;

public class ImportImages {

	public static void main(String[] args) {
		String[] mb_id = {"soowii123","xuan123","michael123","vain123","yiwen123"};
		String[] pics = {"1","2","3","4","5"};
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "DA106G5", "DA106G5");
			pstmt = con.prepareStatement("UPDATE MEMBER set MB_PIC=? where MB_ID = ?");
			
			for(int i = 0; i < mb_id.length; i++) {
				
				File pic = new File("images", pics[i] + ".jpg");
				
				byte[] picb = new byte[(int)pic.length()];
				
				InputStream fin = new FileInputStream(pic); 
				
				
				pstmt.setBytes(1, MemberVO.getMb_pic());
				pstmt.setString(2, MemberVO.getMb_id());

				pstmt.executeUpdate();
			}
			

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

}
