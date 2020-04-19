package com.od_detail.model;

import java.util.*;

import java.sql.*;

public class Od_detailDAO implements Od_detailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO od_detail (od_no, pd_no, od_amount, od_price, pd_size) VALUES (?,?,?,?,?)";
	private static final String GET_ONE_OEDER_DETAIL_ALL_STMT = 
			"SELECT * FROM od_detail where od_no = ?";
	private static final String GET_ALL_STMT_FOR_OD_DETAIL = "SELECT * FROM od_detail ";
	private static final String DELETE_OD_DETAIL = "DELETE FROM od_detail where od_no = ?";

	@Override
	public Od_detailVO addOneOdDetail(Od_detailVO od_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, od_detailVO.getOd_no());
			pstmt.setString(2, od_detailVO.getPd_no());
			pstmt.setInt(3, od_detailVO.getOd_amount());
			pstmt.setInt(4, od_detailVO.getOd_price());
			pstmt.setString(5, od_detailVO.getPd_size());
			pstmt.executeUpdate();

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
		return od_detailVO;
	}

	@Override
	public int delete_od_no_odDetail(String od_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_OD_DETAIL);

			pstmt.setString(1, od_no);

			updateCount = pstmt.executeUpdate();
			System.out.println("訂單明細：" + od_no);
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

	@Override
	public List<Od_detailVO> search_one_oeder_detail(String od_no) {
		List<Od_detailVO> list = new ArrayList<Od_detailVO>();
		Od_detailVO od_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_OEDER_DETAIL_ALL_STMT);

			pstmt.setString(1, od_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				// empVo 也稱為 Domain objects
				od_detailVO = new Od_detailVO(); // 記得補陣列迴圈
				od_detailVO.setOd_no(rs.getString("od_no"));
				od_detailVO.setPd_no(rs.getString("pd_no"));
				od_detailVO.setOd_amount(rs.getInt("od_amount"));
				od_detailVO.setOd_price(rs.getInt("od_price"));
				od_detailVO.setPd_size(rs.getString("pd_size"));
				list.add(od_detailVO);

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<Od_detailVO> getAll() {
		List<Od_detailVO> list = new ArrayList<Od_detailVO>();
		Od_detailVO od_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT_FOR_OD_DETAIL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				od_detailVO = new Od_detailVO();
				od_detailVO.setOd_no(rs.getString("OD_NO"));
				od_detailVO.setPd_no(rs.getString("PD_NO"));
				od_detailVO.setOd_amount(rs.getInt("OD_AMOUNT"));
				od_detailVO.setOd_price(rs.getInt("OD_PRICE"));
				list.add(od_detailVO); // Store the row in the vector
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void addOd_detail2(Od_detailVO od_detailVO, java.sql.Connection con) {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, od_detailVO.getOd_no());
			pstmt.setString(2, od_detailVO.getPd_no());
			pstmt.setInt(3, od_detailVO.getOd_amount());
			pstmt.setInt(4, od_detailVO.getOd_price());
			pstmt.setString(5, od_detailVO.getPd_size());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Od_detail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}

	public static void main(String[] args) {

		Od_detailDAO dao = new Od_detailDAO();

		// 新增，新增一個訂單編號的訂單明細。
//		Od_detailVO od_detailVO1 = new Od_detailVO();
//		od_detailVO1.setOd_no("20200418-000019");
//		od_detailVO1.setPd_no("PDN00001");
//		od_detailVO1.setOd_amount(2);
//		od_detailVO1.setOd_price(650);
//		od_detailVO1.setPd_size("XXL");
//		dao.addOneOdDetail(od_detailVO1);
//		System.out.println("訂單編號：" + od_detailVO1.getOd_no());
//		System.out.println("新增：" + od_detailVO1.getPd_no() + "商品");
//		System.out.println("數量：" + od_detailVO1.getOd_amount());
//		System.out.println("價格" + od_detailVO1.getOd_price());

		// 刪除，刪除該訂單編號的所有訂單明細
//		 int updateCount_delete = dao.delete_od_no_odDetail("20200324-000005");
//	
//			 System.out.println("已刪除資料，共"+updateCount_delete+"筆");

		// 查詢，單一編號的訂單明細
//		List<Od_detailVO> list = dao.search_one_oeder_detail("20200418-000015");
//		for(Od_detailVO aOd_detailVO : list) {
//			System.out.print("訂單編號："+aOd_detailVO.getOd_no()+" ");
//			System.out.print("產品編號"+aOd_detailVO.getPd_no()+" ");
//			System.out.print("尺寸："+aOd_detailVO.getPd_size()+" ");
//			System.out.print("數量："+aOd_detailVO.getOd_amount()+" ");
//			System.out.print("價格"+aOd_detailVO.getOd_price());
//			
//			System.out.println("");
//				
//		}

		// 查詢，全部訂單編號的訂單明細
//		List<Od_detailVO> lists = dao.getAll();                       
//		for (Od_detailVO aOd_detailVO : lists) {
//			System.out.print(aOd_detailVO.getOd_no() + ",");
//			System.out.print(aOd_detailVO.getPd_no() + ",");
//			System.out.print(aOd_detailVO.getOd_amount() + ",");
//			System.out.print(aOd_detailVO.getOd_price()+ ",");
//		
//			System.out.println();
//		}
	}
}