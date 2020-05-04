package com.product.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ProductPicReader
 */

public class ProductPicReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:49161:xe";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	// 連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G5_DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 接收相對應的編碼請求(有中文時)
		res.setContentType("image/gif");
		Connection con = null;
//		PreparedStatement pstmt = null;
		ServletOutputStream out = res.getOutputStream(); // 瀏覽器的輸出

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			Statement stmt = con.createStatement();
			String pd_no = req.getParameter("pd_no").trim();
			System.out.println(pd_no);
			ResultSet rs = stmt.executeQuery("SELECT pd_pic FROM PRODUCT WHERE pd_no = '" + pd_no + "'");
             System.out.println(rs);
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
