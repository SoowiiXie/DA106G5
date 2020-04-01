package com.que_rpt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Question_RPTDAO implements Question_RPTDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
		"INSERT INTO QUESTION_RPT (QUESTION_NO, MB_ID, QUESTION_CONTENT) VALUES ('QUR'||LPAD(to_char(QUESTION_NO_SEQ.NEXTVAL), 5, '0'), ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM QUESTION_RPT order by QUESTION_NO";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM QUESTION_RPT where QUESTION_NO = ?";
	private static final String UPDATE = 
		"UPDATE QUESTION_RPT set MB_ID=?, QUESTION_CONTENT=?, QUESTION_STATUS=? where QUESTION_NO = ?";
	
	@Override
	public void insert(Question_RPTVO qusetion_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, qusetion_rptVO.getMb_id());
			pstmt.setString(2, qusetion_rptVO.getQuestion_content());

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
	public void update(Question_RPTVO qusetion_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, qusetion_rptVO.getMb_id());
			pstmt.setString(2, qusetion_rptVO.getQuestion_content());
			pstmt.setInt(3, qusetion_rptVO.getQuestion_status());
			pstmt.setString(4, qusetion_rptVO.getQuestion_no());

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
	public Question_RPTVO findByPrimaryKey(String question_no) {
		Question_RPTVO question_RPTVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, question_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				question_RPTVO = new Question_RPTVO();
				question_RPTVO.setQuestion_no(rs.getString("question_no"));
				question_RPTVO.setMb_id(rs.getString("mb_id"));
				question_RPTVO.setQuestion_content(rs.getString("question_content"));
				question_RPTVO.setQuestion_status(rs.getInt("question_status"));
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
		return question_RPTVO;
	}

	@Override
	public List<Question_RPTVO> getAll() {
		List<Question_RPTVO> list = new ArrayList<Question_RPTVO>();
		Question_RPTVO question_RPTVO = null;

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
				question_RPTVO = new Question_RPTVO();
				question_RPTVO.setQuestion_no(rs.getString("question_no"));
				question_RPTVO.setMb_id(rs.getString("mb_id"));
				question_RPTVO.setQuestion_content(rs.getString("question_content"));
				question_RPTVO.setQuestion_status(rs.getInt("question_status"));
				list.add(question_RPTVO); // Store the row in the list
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
//		public static void main(String[] args) {
//			Question_RPTDAO dao = new Question_RPTDAO();
//			
//			// 查
//			List<Question_RPTVO> all = dao.getAll();
//			for(Question_RPTVO question_RPTVO:all) {
//				System.out.println(question_RPTVO.getQuestion_no());	
//				System.out.println(question_RPTVO.getMb_id());
//				System.out.println(question_RPTVO.getQuestion_content());
//				System.out.println(question_RPTVO.getQuestion_status());
//			}
//			
//			// 增
//			Question_RPTVO question_RPTVO = new Question_RPTVO();
//			question_RPTVO.setMb_id("weijhih123");
//			question_RPTVO.setQuestion_content("weijhih123+++++++++");
//			dao.insert(question_RPTVO);
//			
//			// 改
//			Question_RPTVO question_RPTVO = new Question_RPTVO();
//			question_RPTVO.setMb_id("soowii123");
//			question_RPTVO.setQuestion_content("OOIHOHI");
//			question_RPTVO.setQuestion_status(2);
//			question_RPTVO.setQuestion_no("QUR00006");
//			dao.update(question_RPTVO);
//			
//			// 用PK查詢
//			Question_RPTVO question_RPTVO = dao.findByPrimaryKey("QUR00006");
//			System.out.println(question_RPTVO.getQuestion_no());
//			System.out.println(question_RPTVO.getMb_id());
//			System.out.println(question_RPTVO.getQuestion_content());
//			System.out.println(question_RPTVO.getQuestion_status());
//		}


}
