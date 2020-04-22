package com.mb.model;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class MemberPicReader extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");  // 接收相對應的編碼請求(有中文時)
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();  // 瀏覽器的輸出

		try {
//			Statement stmt = con.createStatement();
			String mb_id = req.getParameter("mb_id").trim();
//			ResultSet rs = stmt.executeQuery(
//				"SELECT MB_PIC FROM MEMBER WHERE MB_ID = '" + mb_id + "'");
			
			
//			if (rs.next()) {
//				// 用高階水管
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("mb_pic"));
//				byte[] buf = new byte[4 * 1024]; // 4K buffer
//				int len;
//				while ((len = in.read(buf)) != -1) {
//					out.write(buf, 0, len);
//				}
//				in.close();
//			}else {
			BufferedInputStream bf = null;
			Statement stmtLine = con.createStatement();
			ResultSet rsLine = stmtLine.executeQuery(
					"SELECT MB_PIC, MB_LINE_PIC FROM MEMBER WHERE MB_ID = '" + mb_id + "'");
			if (rsLine.next()) {
				// 用高階水管
				if(rsLine.getBinaryStream("mb_pic")!=null){
					bf = new BufferedInputStream(rsLine.getBinaryStream("mb_pic"));
				}else {
					bf = new BufferedInputStream(rsLine.getBinaryStream("mb_line_pic"));
				}
				byte[] buf = new byte[4 * 1024]; // 4K buffer
				int len;
				while ((len = bf.read(buf)) != -1) {
					out.write(buf, 0, len);
				}
				bf.close();
			}
//				else if (rsLine.next()) {
//					res.setContentType("text/plain");
//					res.setCharacterEncoding("UTF-8");
//					PrintWriter outLine = res.getWriter();
//					outLine.write(rsLine.getString("mb_line_pic"));
//				} 
//				else if (rsLine.next()) {
//					URL myURL = new URL(rsLine.getString("mb_line_pic"));
//					HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
//					InputStream is = conn.getInputStream();
//					BufferedInputStream in = new BufferedInputStream(is);
//					byte[] buf = new byte[4 * 1024]; // 4K buffer
//					int len;
//					while ((len = in.read(buf)) != -1) {
//						out.write(buf, 0, len);
//					}
//					in.close();
//				} 
			else {
				// res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/null2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
//			}
				
		} catch (Exception e) {
			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null2.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G5_DB");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
