package com.mb_follow.model;

import java.sql.Connection;
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



public class Mb_followDAO implements Mb_followDAO_interface{
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";
	
	private static final String INSERT_STMT = "INSERT INTO MB_FOLLOW ( MB_ID, MB_ID_FOLLOWED) VALUES (?, ?)";
	private static final String DELETE = "DELETE FROM MB_FOLLOW WHERE MB_ID = ? AND MB_ID_FOLLOWED = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM MB_FOLLOW";
	private static final String GET_STMT = "SELECT * FROM MB_FOLLOW WHERE MB_ID = ?";
	private static final String HAS_FOLLOW = "SELECT * FROM MB_FOLLOW WHERE MB_ID = ? AND MB_ID_FOLLOWED = ?";
	
	
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
	public void insert(Mb_followVO Mb_followVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, Mb_followVO.getMb_id());
			pstmt.setString(2, Mb_followVO.getMb_id_followed());

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
	public void insertByString(String mb_id, String mb_id_followed) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mb_id);
			pstmt.setString(2, mb_id_followed);

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
	public void delete(Mb_followVO Mb_followVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, Mb_followVO.getMb_id());
			pstmt.setString(2, Mb_followVO.getMb_id_followed());

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
	public void deleteByString(String mb_id, String mb_id_followed) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mb_id);
			pstmt.setString(2, mb_id_followed);

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
	public List<Mb_followVO> findByMbId(String mb_id) {
		
		
		List<Mb_followVO> list = new ArrayList<Mb_followVO>();
		Mb_followVO mb_followVO = null;
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
				mb_followVO = new Mb_followVO();
				mb_followVO.setMb_id(rs.getString("MB_ID"));
				mb_followVO.setMb_id_followed(rs.getString("MB_ID_FOLLOWED"));
				list.add(mb_followVO); // Store the row in the list
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
	public List<Mb_followVO> getAll() {
		
		List<Mb_followVO> list = new ArrayList<Mb_followVO>();
		Mb_followVO mb_followVO = null;
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
				mb_followVO = new Mb_followVO();
				mb_followVO.setMb_id(rs.getString("MB_ID"));
				mb_followVO.setMb_id_followed(rs.getString("MB_ID_FOLLOWED"));
				list.add(mb_followVO); // Store the row in the list
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
		Mb_followDAO dao = new Mb_followDAO();
		
		// 增BYString
//		
//		
//		Mb_followVO mb_followVO = new Mb_followVO();
//		mb_followVO.setMb_id("soowii123");
//		mb_followVO.setMb_id_followed("yiwen123");
//		dao.insertByString("soowii123","yiwen123");
		
		
		
		// 增
//		
//		
//		Mb_followVO mb_followVO = new Mb_followVO();
//		mb_followVO.setMb_id("soowii123");
//		mb_followVO.setMb_id_followed("yiwen123");
//		dao.insert(mb_followVO);
		
		
		
		//刪BYSTRING
//		dao.deleteByString("soowii123","yiwen123");
		
		
		//刪
//		Mb_followVO mb_followVO = new Mb_followVO();
//		mb_followVO.setMb_id("soowii123");
//		mb_followVO.setMb_id_followed("yiwen123");
//		dao.delete(mb_followVO);
		
			
		
//		
		// 查
//		List<Mb_followVO> all = dao.getAll();
//		for(Mb_followVO Mb_followVO:all) {
//			System.out.println(Mb_followVO.getMb_id());	
//			System.out.println(Mb_followVO.getMb_id_followed());
//			System.out.println();
//				
//		}
		
		// 用PK查詢
//		List<Mb_followVO> all = dao.findByMbId("yiwen123");
//		for(Mb_followVO Mb_followVO:all) {
//		System.out.println(Mb_followVO.getMb_id());	
//		System.out.println(Mb_followVO.getMb_id_followed());
//		System.out.println();
//			
//		}
		
	}



	@Override  // 是否有追蹤
	public boolean hasFollow(String mb_id, String mb_id_followed) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(HAS_FOLLOW);
			
			pstmt.setString(1, mb_id);
			pstmt.setString(2, mb_id_followed);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = true;
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
		return result;
	}


}
