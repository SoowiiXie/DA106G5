package com.product.model;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class ProductPicReader2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
			System.out.println("第二個讀圖");
			
			 String action = req.getParameter("action");	
			
	 if(action.equals("pd_pic2")) {
			 
		try {
			Statement stmt = con.createStatement();
			String pd_no = req.getParameter("pd_no").trim();
			ResultSet rs = stmt.executeQuery(
					// "SELECT IMAGE FROM PICTURES WHERE PID = " + req.getParameter("PID"));
					"SELECT pd_pic2 FROM product WHERE pd_no = '" + pd_no + "' ");
			if (rs.next()) {
				byte[] pic =null;
				
				pic=rs.getBytes(1);
				
				out.write(pic);
				
//				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("loc_pic"));
//				byte[] buf = new byte[4 * 1024]; // 4K buffer
//				int len;
//				while ((len = in.read(buf)) != -1) {
//					out.write(buf, 0, len);
//				}
//				in.close();
				
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	 }
	 
	 if(action.equals("pd_pic3")) {
		 
			try {
				Statement stmt = con.createStatement();
				String pd_no = req.getParameter("pd_no").trim();
				ResultSet rs = stmt.executeQuery(
						// "SELECT IMAGE FROM PICTURES WHERE PID = " + req.getParameter("PID"));
						"SELECT pd_pic3 FROM product WHERE pd_no = '" + pd_no + "' ");
				if (rs.next()) {
					byte[] pic =null;
					
					pic=rs.getBytes(1);
					
					out.write(pic);
					
//					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("loc_pic"));
//					byte[] buf = new byte[4 * 1024]; // 4K buffer
//					int len;
//					while ((len = in.read(buf)) != -1) {
//						out.write(buf, 0, len);
//					}
//					in.close();
					
				} else {
//					res.sendError(HttpServletResponse.SC_NOT_FOUND);
					InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
//				System.out.println(e);
				InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
				 }
	 
	 if(action.equals("pd_pic4")) {
		 
			try {
				Statement stmt = con.createStatement();
				String pd_no = req.getParameter("pd_no").trim();
				ResultSet rs = stmt.executeQuery(
						// "SELECT IMAGE FROM PICTURES WHERE PID = " + req.getParameter("PID"));
						"SELECT pd_pic4 FROM product WHERE pd_no = '" + pd_no + "' ");
				if (rs.next()) {
					byte[] pic =null;
					
					pic=rs.getBytes(1);
					
					out.write(pic);
					
//					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("loc_pic"));
//					byte[] buf = new byte[4 * 1024]; // 4K buffer
//					int len;
//					while ((len = in.read(buf)) != -1) {
//						out.write(buf, 0, len);
//					}
//					in.close();
					
				} else {
//					res.sendError(HttpServletResponse.SC_NOT_FOUND);
					InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
//				System.out.println(e);
				InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
				 }
	 
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G5_DB");
			con = ds.getConnection();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}