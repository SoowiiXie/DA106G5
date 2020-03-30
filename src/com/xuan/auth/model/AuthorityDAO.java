package com.xuan.auth.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorityDAO implements AuthorityDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";
	
	private static final String INSERT_STMT = 
			"INSERT INTO AUTHORITY (STAFF_ID,ABILITY_NO) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM AUTHORITY ORDER BY STAFF_ID";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM AUTHORITY WHERE STAFF_ID = ? AND ABILITY_NO = ?";
	private static final String DELETE = 
			"DELETE FROM AUTHORITY WHERE STAFF_ID = ? AND ABILITY_NO = ?";
	
	
	@Override
	public void insert(AuthorityVO authorityVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, authorityVO.getStaff_id());
			pstmt.setString(2, authorityVO.getAbility_no());
			
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
	public void delete(String staff_id, String ability_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, staff_id);
			pstmt.setString(2, ability_no);

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
	public AuthorityVO findByPrimaryKey(String staff_id, String ability_no) {
		AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, staff_id);
			pstmt.setString(2, ability_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				authorityVO = new AuthorityVO();
				authorityVO.setStaff_id(rs.getString("staff_id"));
				authorityVO.setAbility_no(rs.getString("ability_no"));
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
		return authorityVO;
	}

	@Override
	public List<AuthorityVO> getAll() {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				authorityVO = new AuthorityVO();
				authorityVO.setStaff_id(rs.getString("staff_id"));
				authorityVO.setAbility_no(rs.getString("ability_no"));
				list.add(authorityVO);
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
//		AuthorityDAO dao = new AuthorityDAO();
//		
//		// 查
//		List<AuthorityVO> all = dao.getAll();
//		for(AuthorityVO authorityVO:all) {
//			System.out.println(authorityVO.getStaff_id());	
//			System.out.println(authorityVO.getAbility_no());
//		}
//		
//		// 增
//		AuthorityVO authorityVO = new AuthorityVO();
//		authorityVO.setStaff_id("staff_soowii");
//		authorityVO.setAbility_no("01");
//		dao.insert(authorityVO);
//		
//		 // 刪
//		dao.delete("staff_soowii", "01");
//		
//		// 用PK查詢
//		AuthorityVO ability = dao.findByPrimaryKey("staff_xuan","03");
//		if(ability!=null) {
//			System.out.println(ability.getStaff_id());
//			System.out.println(ability.getAbility_no());
//		}else {
//			System.out.println("沒有找到資料");
//		}
//	}
}
