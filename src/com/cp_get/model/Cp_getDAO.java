package com.cp_get.model;

import java.util.*;
import java.sql.*;

public class Cp_getDAO implements Cp_getDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	// 新增某會員的優惠卷。
	private static final String ADD_COUPON = "INSERT INTO cp_get (mb_id, cp_no) VALUES (?,?)";
	// 修改某會員持有的某張優惠卷狀態，例如使用優惠卷
	private static final String CHANGE_COUPON_STATUS = "update cp_get set cp_status = ? where mb_id = ? and cp_no = ?";
	// 列出某會員持有的全部優惠卷
	private static final String SEARCH_A_MEMBER_ALL_COUPON = "select cp_no, cp_status from cp_get where mb_id = ?";

	// 列出某會員處於該狀態的優惠券
	private static final String LIST_A_MEMBER_CP_GET_BY_STATUS = "select cp_no, cp_status from cp_get where mb_id = ? and cp_status = ?";

	// 刪除會員優惠卷使用狀態，有刪除所有優惠卷問題 目前用不到
	// private static final String DELETE = "DELETE FROM cp_get where mb_id = ?";

	@Override
	public int aMemberGetCoupon(Cp_getVO cp_getVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(ADD_COUPON);

			pstmt.setString(1, cp_getVO.getMb_id());
			pstmt.setString(2, cp_getVO.getCp_no());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public int aMemberUseCoupon(Cp_getVO cp_getVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateCount = 0;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHANGE_COUPON_STATUS);
			pstmt.setInt(1, cp_getVO.getCp_status());
			pstmt.setString(2, cp_getVO.getMb_id());
			pstmt.setString(3, cp_getVO.getCp_no());
			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return updateCount;

	}

	public List<Cp_getVO> searchMemberGetCoupon(String mb_id) {
		List<Cp_getVO> list = new ArrayList<Cp_getVO>();
		Cp_getVO cp_getVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SEARCH_A_MEMBER_ALL_COUPON);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			System.out.println("===============DAO顯示開始===============");
			System.out.println("");
			System.out.println("會員帳號：" + mb_id);
			System.out.println("");
			System.out.println("===============DAO顯示結束===============");
			while (rs.next()) {

				cp_getVO = new Cp_getVO();
				cp_getVO.setCp_no(rs.getString("cp_no"));
				cp_getVO.setCp_status(rs.getInt("cp_status"));
				list.add(cp_getVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public List<Cp_getVO> listAmemberCpGetStatus(Cp_getVO cp_getVO) {

		List<Cp_getVO> list = new ArrayList<Cp_getVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LIST_A_MEMBER_CP_GET_BY_STATUS);
			pstmt.setString(1, cp_getVO.getMb_id());
			pstmt.setInt(2, cp_getVO.getCp_status());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cp_getVO = new Cp_getVO();
				cp_getVO.setCp_no(rs.getString("cp_no"));
				cp_getVO.setCp_status(rs.getInt("cp_status"));
				list.add(cp_getVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	    
	
	
	public static void main(String[] args) {

		Cp_getDAO dao = new Cp_getDAO();

		// 新增持有優惠卷，某會員得到該優惠卷。
		Cp_getVO cp_getVO1 = new Cp_getVO();
		cp_getVO1.setMb_id("michael123");
		cp_getVO1.setCp_no("CPN00004");
		int updateCount_insert = dao.aMemberGetCoupon(cp_getVO1);
		System.out.println("會員" + cp_getVO1.getMb_id() + "新增" + updateCount_insert + "種優惠券");

//		// 會員使用優惠卷，更改優惠卷狀態
//		Cp_getVO cp_getVO2 = new Cp_getVO();
//		cp_getVO2.setMb_id("michael123");
//		cp_getVO2.setCp_no("CPN00001");
//		cp_getVO2.setCp_status(1);
//		int updateCount = dao.aMemberUseCoupon(cp_getVO2);
//
//		if (updateCount == 1 && cp_getVO2.getCp_status() == 2) {
//			System.out.println("會員" + cp_getVO2.getMb_id() + "使用" + cp_getVO2.getCp_no() + "優惠券");
//		}

		// 查詢開會員某狀態的優惠券，例如列出"可使用狀態"的優惠卷
//		Cp_getVO cp_getVO = new Cp_getVO();
//		cp_getVO.setMb_id("weijhih123");
//		cp_getVO.setCp_status(1);
//		List<Cp_getVO> list = dao.listAmemberCpGetStatus(cp_getVO);
//		System.out.println("會員：" + cp_getVO.getMb_id());
//
//		if (cp_getVO.getCp_status() == 1) {
//			for (Cp_getVO aCp_getVO : list) {
//				System.out.println("可使用的優惠券編號:" + aCp_getVO.getCp_no());
//
//			}
//		} else if (cp_getVO.getCp_status() == 2) {
//			for (Cp_getVO aCp_getVO : list) {
//				System.out.println("已使用的優惠券編號:" + aCp_getVO.getCp_no());
//
//			}
//		}

		// 查詢該會員所有的優惠卷
//		List<Cp_getVO> list = dao.searchMemberGetCoupon("weijhih123");
//	
//        for(Cp_getVO acp_getVO : list) {
//        	System.out.println("優惠卷號碼："+acp_getVO.getCp_no()+" "+"優惠卷狀態："+acp_getVO.getCp_status());
//        }

		 
		 
		 
		 
		 
	}

}