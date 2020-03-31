package com.xuan.ntf.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NotifyDAO implements NotifyDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
		"INSERT INTO NOTIFY (NTF_NO, MB_ID, NTF_CONTENT) VALUES ('NTF'||LPAD(to_char(ntf_no_seq.NEXTVAL), 5, '0'), ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM NOTIFY order by NTF_NO";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM NOTIFY where NTF_NO = ?";
	private static final String UPDATE = 
		"UPDATE NOTIFY set MB_ID=?, NTF_CONTENT=?, NTF_STATUS=? where NTF_NO = ?";

	@Override
	public void insert(NotifyVO notifyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, notifyVO.getMb_id());
			pstmt.setString(2, notifyVO.getNtf_content());

			pstmt.executeUpdate();

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

	@Override
	public void update(NotifyVO notifyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, notifyVO.getMb_id());
			pstmt.setString(2, notifyVO.getNtf_content());
			pstmt.setInt(3, notifyVO.getNtf_status());
			pstmt.setString(4, notifyVO.getNtf_no());

			pstmt.executeUpdate();

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

	@Override
	public NotifyVO findByPrimaryKey(String ntf_no) {
		NotifyVO notifyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, ntf_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				notifyVO = new NotifyVO();
				notifyVO.setNtf_no(rs.getString("ntf_no"));
				notifyVO.setMb_id(rs.getString("mb_id"));
				notifyVO.setNtf_content(rs.getString("ntf_content"));
				notifyVO.setNtf_status(rs.getInt("ntf_status"));
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
		return notifyVO;
	}

	@Override
	public List<NotifyVO> getAll() {
		List<NotifyVO> list = new ArrayList<NotifyVO>();
		NotifyVO notifyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				notifyVO = new NotifyVO();
				notifyVO.setNtf_no(rs.getString("ntf_no"));
				notifyVO.setMb_id(rs.getString("mb_id"));
				notifyVO.setNtf_content(rs.getString("ntf_content"));
				notifyVO.setNtf_status(rs.getInt("ntf_status"));
				list.add(notifyVO); // Store the row in the list
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
	
//	// 測試
//	public static void main(String[] args) {
//		NotifyDAO dao = new NotifyDAO();
//		
//		// 查
//		List<NotifyVO> all = dao.getAll();
//		for(NotifyVO notifyVO:all) {
//			System.out.println(notifyVO.getNtf_no());	
//			System.out.println(notifyVO.getMb_id());
//			System.out.println(notifyVO.getNtf_content());
//			System.out.println(notifyVO.getNtf_status());
//		}
//		
//		// 增
//		NotifyVO notifyVO = new NotifyVO();
//		notifyVO.setMb_id("soowii123");
//		notifyVO.setNtf_content("OOIHOHI");
//		dao.insert(notifyVO);
//		
//		// 改
//		NotifyVO notifyVO = new NotifyVO();
//		notifyVO.setMb_id("soowii123");
//		notifyVO.setNtf_content("OOIHOHI");
//		notifyVO.setNtf_status(2);
//		notifyVO.setNtf_no("NTF00015");
//		dao.update(notifyVO);
//		
//		// 用PK查詢
//		NotifyVO notifyVO = dao.findByPrimaryKey("NTF00015");
//		System.out.println(notifyVO.getNtf_no());
//		System.out.println(notifyVO.getMb_id());
//		System.out.println(notifyVO.getNtf_content());
//		System.out.println(notifyVO.getNtf_status());
//	}

}
