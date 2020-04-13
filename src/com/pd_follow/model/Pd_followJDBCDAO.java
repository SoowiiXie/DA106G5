package com.pd_follow.model;

import java.util.*;


import java.sql.*;

public class Pd_followJDBCDAO implements Pd_follow_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
		"INSERT INTO pd_follow (mb_id, pd_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mb_id, pd_no FROM pd_follow order by mb_id";
	private static final String GET_A＿MEMBER_ALL_PD_FOLLOW =        
		"SELECT mb_id, pd_no FROM pd_follow where mb_id = ?";
	private static final String DELETE_MEMBER_A_PRODUCTS = 
	    "delete FROM pd_follow where mb_id = ? and pd_no = ?";

	@Override
	    public int insertMemberOneProduct(Pd_followVO pd_followVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,pd_followVO.getMb_id());
			pstmt.setString(2,pd_followVO.getPd_no());

			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}


	@Override
	public int deleteMemberOneProduct(Pd_followVO pd_followVO) {
		
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_MEMBER_A_PRODUCTS);
			
			pstmt.setString(1, pd_followVO.getMb_id());
		    pstmt.setString(2, pd_followVO.getPd_no());	
			updateCount = pstmt.executeUpdate();

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
		return updateCount;
	}

	@Override
	public List<Pd_followVO> searchMemberAllPdFollow(String mb_id) {

		Pd_followVO pd_followVO = null;
		List<Pd_followVO> list = new ArrayList<Pd_followVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_A＿MEMBER_ALL_PD_FOLLOW);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pd_followVO = new Pd_followVO();
				// empVo 也稱為 Domain objects
				pd_followVO = new Pd_followVO();               //記得補陣列迴圈
				pd_followVO.setMb_id(rs.getString("mb_id"));
				pd_followVO.setPd_no(rs.getString("pd_no"));
				
				list.add(pd_followVO);
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

	@Override
	public List<Pd_followVO> getAll() {
		List<Pd_followVO> list = new ArrayList<Pd_followVO>();
		Pd_followVO pd_followVO = null;

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
				 pd_followVO = new Pd_followVO();
				 pd_followVO.setMb_id(rs.getString("mb_id"));
				 pd_followVO.setPd_no(rs.getString("pd_no"));
				list.add(pd_followVO); // Store the row in the vector
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

	public static void main(String[] args) {

		Pd_followJDBCDAO dao = new Pd_followJDBCDAO();

		 //新增，會員新增一個商品收藏
		Pd_followVO pd_followVO1 = new Pd_followVO();
		pd_followVO1.setMb_id("yiwen123");
		pd_followVO1.setPd_no("PDN00004");
		 int updateCount_insert = dao.insertMemberOneProduct(pd_followVO1);
		 
		 if (updateCount_insert == 1) {
			 System.out.println("新增會員收藏");		 
		 }
		 
		 System.out.println("會員編號："+pd_followVO1.getMb_id());
		 System.out.println("商品編號："+pd_followVO1.getPd_no());
		 

		
		 //刪除 刪除會員一個商品收藏
		Pd_followVO pd_followVO2 = new Pd_followVO(); 
		pd_followVO2.setMb_id("yiwen123");
		pd_followVO2.setPd_no("PDN00004");
		 int updateCount_delete = dao.deleteMemberOneProduct(pd_followVO2);
		 if (updateCount_delete == 1) {
			 System.out.println("會員編號："+pd_followVO2.getMb_id()+"收藏的商品"+pd_followVO2.getPd_no()+"已經刪除");
			 
		 }

//		// 查詢，查詢單一會員收藏
		List<Pd_followVO> list = dao.searchMemberAllPdFollow("yiwen123");
		System.out.println("查詢單一會員收藏");
		for(Pd_followVO aPd_followVO : list) {
		
			System.out.print("會員編號："+aPd_followVO.getMb_id()+" ");
			System.out.println("產品編號："+aPd_followVO.getPd_no());
			
		}
		
		
		// 查詢全部會員收藏
		List<Pd_followVO> lists = dao.getAll();
		System.out.println("列出所有會員收藏");
		for (Pd_followVO aPd_followVO : lists) {
			System.out.print("會員編號："+aPd_followVO.getMb_id() + ",");
			System.out.print("產品編號："+aPd_followVO.getPd_no());
		
			System.out.println();
		}
	}
}