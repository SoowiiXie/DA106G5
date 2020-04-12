package com.live.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LiveDAO implements LiveDAO_interface {

//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO LIVE ( LIVE_NO, LIVE_CONTENT, LIVE_STATUS, LIVE_STARTTEASER ,LIVE_START, MB_ID) VALUES ( 'LIV'||LPAD(to_char(LIVE_NO_SEQ.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM LIVE ORDER BY LIVE_NO";
	private static final String GET_STMT = "SELECT * FROM LIVE WHERE MB_ID = ?";
	private static final String UPDATE = "UPDATE LIVE SET LIVE_CONTENT=?, LIVE_STATUS=?, LIVE_STARTTEASER=?, LIVE_START=?, LIVE_STORE=? WHERE LIVE_NO = ?";
	private static final String DELETE = "DELETE FROM LIVE WHERE LIVE_NO = ?";
	
	
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
	public void insert(LiveVO LiveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(, LiveVO.getLive_no());
			pstmt.setString(1, LiveVO.getLive_content());
			pstmt.setInt(2, LiveVO.getLive_status());
			pstmt.setDate(3, LiveVO.getLive_startteaser());
			pstmt.setDate(4, LiveVO.getLive_start());
			pstmt.setString(5, LiveVO.getMb_id());
//			pstmt.setBytes(7, LiveVO.getLive_store());
			

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(LiveVO LiveVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, LiveVO.getLive_content());
			pstmt.setInt(2, LiveVO.getLive_status());
			pstmt.setDate(3, LiveVO.getLive_startteaser());
			pstmt.setDate(4, LiveVO.getLive_start());
			pstmt.setBytes(5, LiveVO.getLive_store());
			pstmt.setString(6, LiveVO.getLive_no());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(String live_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, live_no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<LiveVO> findByMbId(String mb_id) {
		
		
		List<LiveVO> list = new ArrayList<LiveVO>();
		LiveVO liveVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_STMT);
			
			pstmt.setString(1, mb_id);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				liveVO = new LiveVO();
				liveVO.setLive_no(rs.getString("LIVE_NO"));
				liveVO.setLive_content(rs.getString("LIVE_CONTENT"));
				liveVO.setLive_status(rs.getInt("LIVE_STATUS"));
				liveVO.setLive_startteaser(rs.getDate("LIVE_STARTTEASER"));
				liveVO.setLive_start(rs.getDate("LIVE_START"));
				liveVO.setLive_store(rs.getBytes("LIVE_STORE"));
				liveVO.setMb_id(rs.getString("MB_ID"));
				list.add(liveVO); // Store the row in the list
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

	@Override
	public List<LiveVO> getAll() {
		List<LiveVO> list = new ArrayList<LiveVO>();
		LiveVO liveVO = null;

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
				liveVO = new LiveVO();
				liveVO.setLive_no(rs.getString("LIVE_NO"));
				liveVO.setLive_content(rs.getString("LIVE_CONTENT"));
				liveVO.setLive_status(rs.getInt("LIVE_STATUS"));
				liveVO.setLive_startteaser(rs.getDate("LIVE_STARTTEASER"));
				liveVO.setLive_start(rs.getDate("LIVE_START"));
				liveVO.setLive_store(rs.getBytes("LIVE_STORE"));
				liveVO.setMb_id(rs.getString("MB_ID"));
				list.add(liveVO); // Store the row in the list
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
	
	
	
	
	
	
	public static void main (String[] args) throws Exception {
		LiveDAO dao = new LiveDAO();
		
		// 增
//		
//		InputStream fin = new FileInputStream(new File("fake_picture", "mb1.jpg"));
//		byte[] pic = new byte[fin.available()];
//		fin.read(pic);
//		LiveVO liveVO = new LiveVO();
//		liveVO.setLive_no("LIV00007");
//		liveVO.setLive_content("TEST_LIVE");
//		liveVO.setLive_status(5);
//		liveVO.setLive_startteaser(new Date(89,05,25));
//		liveVO.setLive_start(new Date(89,05,25));
//		liveVO.setLive_store(pic);
//		liveVO.setMb_id("yiwen123");
//		dao.insert(liveVO);
		
		//刪
//		dao.delete("LIV00007");
		
		//改
//		LiveVO liveVO = new LiveVO();
//		liveVO.setLive_no("LIV00007");
//		liveVO.setLive_content("TEST_LIVE2");
//		liveVO.setLive_status(6);
//		liveVO.setLive_startteaser(new Date(90,05,25));
//		liveVO.setLive_start(new Date(90,05,25));
////		liveVO.setLive_store();
//		liveVO.setMb_id("xuan123");
//		dao.update(liveVO);
		
		
		
//		
		// 查
//		List<LiveVO> all = dao.getAll();
//		for(LiveVO LiveVO:all) {
//			System.out.println(LiveVO.getLive_no());	
//			System.out.println(LiveVO.getLive_content());
//			System.out.println(LiveVO.getLive_status());	
//			System.out.println(LiveVO.getLive_startteaser());
//			System.out.println(LiveVO.getLive_start());	
//			System.out.println(LiveVO.getMb_id());
//				
//		}
		
		// 用PK查詢
//		List<LiveVO> list = new ArrayList<LiveVO>();
//		list = dao.findByMbId("michael123");
//		
//		for(LiveVO LiveVO:list) {
//			System.out.println(LiveVO.getLive_no());	
//			System.out.println(LiveVO.getLive_content());
//			System.out.println(LiveVO.getLive_status());	
//			System.out.println(LiveVO.getLive_startteaser());
//			System.out.println(LiveVO.getLive_start());	
//			System.out.println(LiveVO.getMb_id());
//				
//		}
		
		
		
		
	}
	

}
