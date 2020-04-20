package com.mb.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO  implements MemberDAO_interface{

//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
		"INSERT INTO MEMBER ( MB_ID, MB_PWD, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_PIC, MB_EMAIL) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM MEMBER order by MB_ID";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM MEMBER where MB_ID = ?";
	private static final String UPDATE = 
		"UPDATE MEMBER set MB_PWD=?, MB_NAME=?, MB_GENDER=?, MB_BIRTHDAY=?, MB_LV=?, MB_PIC=?, MB_RPT_TIMES=?, MB_EMAIL=?, MB_STATUS=? where MB_ID = ?";
	private static final String UPDATE_LINE = 
		"UPDATE MEMBER set MB_LINE_ID=?, MB_LINE_PIC=?, MB_LINE_DISPLAY=?, MB_LINE_STATUS=? where MB_ID = ?";
	// 被檢舉次數+1
	private static final String GET_ONE_RPT_TIME = 
		"SELECT MB_RPT_TIMES FROM MEMBER where MB_ID = ?";
	private static final String ADD_ONE_TO_RPT_TIME =
		"UPDATE MEMBER set MB_RPT_TIMES=? where MB_ID = ?";
	
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
	
	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, memberVO.getMb_id());
			pstmt.setString(2, memberVO.getMb_pwd());
			pstmt.setString(3, memberVO.getMb_name());
			pstmt.setInt(4, memberVO.getMb_gender());
			pstmt.setDate(5, memberVO.getMb_birthday());
			pstmt.setBytes(6, memberVO.getMb_pic());
			pstmt.setString(7, memberVO.getMb_email());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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

	@Override
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, memberVO.getMb_pwd());
			pstmt.setString(2, memberVO.getMb_name());
			pstmt.setInt(3, memberVO.getMb_gender());
			pstmt.setDate(4, memberVO.getMb_birthday());
			pstmt.setInt(5, memberVO.getMb_lv());
			pstmt.setBytes(6, memberVO.getMb_pic());
			pstmt.setInt(7, memberVO.getMb_rpt_times());
			pstmt.setString(8, memberVO.getMb_email());
			pstmt.setInt(9, memberVO.getMb_status());
			pstmt.setString(10, memberVO.getMb_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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

	@Override
	public MemberVO findByPrimaryKey(String mb_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMb_id(rs.getString("mb_id"));
				memberVO.setMb_pwd(rs.getString("mb_pwd"));
				memberVO.setMb_name(rs.getString("mb_name"));
				memberVO.setMb_gender(rs.getInt("mb_gender"));
				memberVO.setMb_birthday(rs.getDate("mb_birthday"));
				memberVO.setMb_lv(rs.getInt("mb_lv"));
				memberVO.setMb_pic(rs.getBytes("mb_pic"));
				memberVO.setMb_rpt_times(rs.getInt("mb_rpt_times"));
				memberVO.setMb_email(rs.getString("mb_email"));
				memberVO.setMb_status(rs.getInt("mb_status"));
				memberVO.setMb_line_id(rs.getString("mb_line_id"));
				memberVO.setMb_line_pic(rs.getBytes("mb_line_pic"));
				memberVO.setMb_line_display(rs.getString("mb_line_display"));
				memberVO.setMb_line_status(rs.getString("mb_line_status"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMb_id(rs.getString("mb_id"));
				memberVO.setMb_pwd(rs.getString("mb_pwd"));
				memberVO.setMb_name(rs.getString("mb_name"));
				memberVO.setMb_gender(rs.getInt("mb_gender"));
				memberVO.setMb_birthday(rs.getDate("mb_birthday"));
				memberVO.setMb_lv(rs.getInt("mb_lv"));
				memberVO.setMb_pic(rs.getBytes("mb_pic"));
				memberVO.setMb_rpt_times(rs.getInt("mb_rpt_times"));
				memberVO.setMb_email(rs.getString("mb_email"));
				memberVO.setMb_status(rs.getInt("mb_status"));
				memberVO.setMb_line_id(rs.getString("mb_line_id"));
				memberVO.setMb_line_pic(rs.getBytes("mb_line_pic"));
				memberVO.setMb_line_display(rs.getString("mb_line_display"));
				memberVO.setMb_line_status(rs.getString("mb_line_status"));
				list.add(memberVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	@Override  // 被檢舉次數+1
	public String addOneToRptTime(String mb_id) {
		
		String str = null;
		ResultSet rs = null;
		// 改用try()方法，自動關閉連線
		try (Connection con = ds.getConnection();PreparedStatement pstmt = con.prepareStatement(GET_ONE_RPT_TIME);
				PreparedStatement add_pstmt = con.prepareStatement(ADD_ONE_TO_RPT_TIME);){
			
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();

			rs.next();
			// 取得會員被檢舉次數後+1
			Integer mb_rpt_times = rs.getInt("mb_rpt_times") + 1; 
			
			add_pstmt.setInt(1, mb_rpt_times);
			add_pstmt.setString(2, mb_id);
			add_pstmt.executeUpdate();
			
			str = mb_id + "被檢舉次數更新為" + mb_rpt_times;
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return str;
	}

	@Override
	public void updateLine(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_LINE);
			
			pstmt.setString(1, memberVO.getMb_line_id());
			pstmt.setBytes(2, memberVO.getMb_line_pic());
			pstmt.setString(3, memberVO.getMb_line_display());
			pstmt.setString(4, memberVO.getMb_line_status());
			pstmt.setString(5, memberVO.getMb_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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
