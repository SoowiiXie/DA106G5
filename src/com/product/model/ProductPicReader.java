package com.product.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductPicReader
 */

public class ProductPicReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");  // 接收相對應的編碼請求(有中文時)
		res.setContentType("image/gif");
		Connection con = null;
//		PreparedStatement pstmt = null;
		ServletOutputStream out = res.getOutputStream();  // 瀏覽器的輸出

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			Statement stmt = con.createStatement();
			String pd_no = req.getParameter("pd_no").trim();
			ResultSet rs = stmt.executeQuery(
				"SELECT pd_pic FROM PRODUCT WHERE pd_no = '" +pd_no + "'");
			
			if (rs.next()) {
				// 用高階水管
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("pd_pic"));
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = in.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				in.close();
			} else {
				// res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/null2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			// System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null2.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}



}
