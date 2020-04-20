package com.mb.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

//只使用findByPrimaryKey 其他都是亂寫的
public class MemberPGDAO implements MemberDAO_interface {
	// INSERT INTO TABLE_NAME (column1, column2, column3,...columnN) VALUES (value1,
	// value2, value3,...valueN);
	// "SELECT id, \"GRP_NO\", \"MB_ID\", \"GRP_PERSONCOUNT\", \"GRP_STATUS\",
	// \"GRP_FOLLOW\" FROM public.grouper";
	private static final String GET_ONE_STMT = "SELECT * FROM public.orders where \"MB_ID\" = ?";

	// 連線池
	private static DataSource pgds = null;
	static {
		try {
			Context ctx = new InitialContext();
			pgds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G5_PGDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MemberVO findByPrimaryKey(String mb_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(DRIVER_CLASS_PG);
//			con= DriverManager.getConnection(URL_PG, USER_PG, PASSWORD_PG);
			con = pgds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMb_id(rs.getString("mb_id"));
				memberVO.setMb_line_id(rs.getString("mb_line_id"));
				memberVO.setMb_line_display(rs.getString("mb_line_display"));
				memberVO.setMb_line_status(rs.getString("mb_line_status"));
				
				URL myURL = new URL(rs.getString("mb_line_pic"));
				HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				InputStream in = conn.getInputStream();
				BufferedInputStream bf = new BufferedInputStream(in);
				byte[] buffer = new byte[16*1024];
				int a;
				while ((a = bf.read(buffer)) != -1) {
					baos.write(buffer, 0, a);
				}
				memberVO.setMb_line_pic(baos.toByteArray());
				bf.close();
				in.close();
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return memberVO;
	}

	@Override
	public void insert(MemberVO memberVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MemberVO memberVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLine(MemberVO memberVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MemberVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addOneToRptTime(String mb_id) {
		// TODO Auto-generated method stub
		return null;
	}

//	// 測試
//	public static void main(String[] args) throws Exception {
//		MemberDAO dao = new MemberDAO();
//		
//		// 查
//		List<MemberVO> all = dao.getAll();
//		for(MemberVO memberVO:all) {
//			System.out.println(memberVO.getMb_id());	
//			System.out.println(memberVO.getMb_pwd());
//			System.out.println(memberVO.getMb_line());	
//			System.out.println(memberVO.getMb_name());
//			System.out.println(memberVO.getMb_gender());	
//			System.out.println(memberVO.getMb_birthday());
//			System.out.println(memberVO.getMb_lv());	
//			System.out.println(memberVO.getMb_pic());
//			System.out.println(memberVO.getMb_rpt_times());	
//			System.out.println(memberVO.getMb_email());
//			System.out.println(memberVO.getMb_status());	
//		}
//		
//		
//		// 增
//		
//		InputStream fin = new FileInputStream(new File("fake_picture", "mb1.jpg"));
//		byte[] pic = new byte[fin.available()];
//		fin.read(pic);
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMb_id("apple1234");
//		memberVO.setMb_pwd("65a4sd");
//		memberVO.setMb_line("apple_line");
//		memberVO.setMb_name("apple");
//		memberVO.setMb_gender(1);
//		memberVO.setMb_birthday(new Date(89,05,25));
//		memberVO.setMb_lv(2);
//		memberVO.setMb_pic(pic);
//		memberVO.setMb_rpt_times(5);
//		memberVO.setMb_email("apple@yahoo.com");
//		memberVO.setMb_status(1);
//		dao.insert(memberVO);
//		
//		// 改
//		MemberVO memberVO = new MemberVO();
//		memberVO.setMb_id("michael123");
//		memberVO.setMb_pwd("++++++++++");
//		memberVO.setMb_line("michael_line");
//		memberVO.setMb_name("Michael");
//		memberVO.setMb_gender(1);
//		memberVO.setMb_birthday(new Date(89,05,25));
//		memberVO.setMb_lv(2);
//		memberVO.setMb_pic(null);
//		memberVO.setMb_rpt_times(2);
//		memberVO.setMb_email("Michael123@yahoo.com");
//		memberVO.setMb_status(2);
//		dao.update(memberVO);
//		
//		// 用PK查詢
//		MemberVO memberVO = dao.findByPrimaryKey("michael123");
//		System.out.println(memberVO.getMb_id());	
//		System.out.println(memberVO.getMb_pwd());
//		System.out.println(memberVO.getMb_line());	
//		System.out.println(memberVO.getMb_name());
//		System.out.println(memberVO.getMb_gender());	
//		System.out.println(memberVO.getMb_birthday());
//		System.out.println(memberVO.getMb_lv());	
//		System.out.println(memberVO.getMb_pic());
//		System.out.println(memberVO.getMb_rpt_times());	
//		System.out.println(memberVO.getMb_email());
//		System.out.println(memberVO.getMb_status());	
//		
//		// 新增檢舉次數
//		System.out.println(dao.addOneToRptTime("michael123"));
//		
//	}
}