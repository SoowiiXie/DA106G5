package com.coupon.model;

import java.util.*;
import java.sql.*;

public class CouponDAO implements CouponDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
		"INSERT INTO coupon (cp_no, cp_name, cp_price, cp_detail) VALUES ('CPN'||LPAD(to_char(PRODUCT_SEQ.NEXTVAL), 5, '0'),?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT cp_no, cp_name, cp_price, cp_detail , cp_time FROM coupon order by cp_no";
	private static final String GET_ONE_STMT = 
		"SELECT cp_no,cp_name,cp_price FROM coupon where cp_no = ?";
	private static final String DELETE = 
		"DELETE FROM coupon where cp_no = ?";
	private static final String UPDATE = 
		"UPDATE coupon set cp_name = ?  where cp_no = ?";

	@Override
	public int addCoupon(CouponVO couponVo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);			
			String cols[] = {"cp_no"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			
			pstmt.setString(1, couponVo.getCp_name());
			pstmt.setInt(2, couponVo.getCp_price());
			pstmt.setString(3, couponVo.getCp_detail());
			
			

			updateCount = pstmt.executeUpdate();
			
			//掘取對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						String key = rs.getString(i);
						System.out.println("自增主鍵值(" + i + ") = " + key +"(剛新增成功的商品編號)");
					}
				} while (rs.next());
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			rs.close();

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
	public int updateCouponInformation(CouponVO couponVo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setString(1, couponVo.getCp_name());
			pstmt.setString(2, couponVo.getCp_no());


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
	public int deleteCoupon(String cp_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, cp_no);
			
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
	public CouponVO searchCoupon(String cp_no) {

		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, cp_no);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				couponVO = new CouponVO();
				couponVO.setCp_no(rs.getString("cp_no"));
				couponVO.setCp_name(rs.getString("cp_name"));
				couponVO.setCp_price(rs.getInt("cp_price"));
				
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
		return couponVO;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;

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
				couponVO = new CouponVO();
				couponVO.setCp_no(rs.getString("cp_no"));
				couponVO.setCp_name(rs.getString("cp_name"));
				couponVO.setCp_price(rs.getInt("cp_price"));
				couponVO.setCp_detail(rs.getString("cp_detail"));
				couponVO.setCp_time(rs.getTimestamp("cp_time"));
				Timestamp sqlTime = couponVO.getCp_time();
				String javaTime = sqlTime.toString();  //把用toString 把sqlTime改寫成字串
				couponVO.setCp_javaTime(javaTime.substring(0, 16));//取字串裡面索引值0(第一個)到第16(第17個)的字元
				list.add(couponVO); // Store the row in the vector
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

		CouponDAO dao = new CouponDAO();

		 //新增，新增優惠卷種類
//		CouponVO couponVO1 = new CouponVO();
//		couponVO1.setCp_name("測試用優惠卷");
//		couponVO1.setCp_price(77777);
//		couponVO1.setCp_detail("測試用備註");
//		 int updateCount_insert = dao.addCoupon(couponVO1);
//		 System.out.println("新增"+updateCount_insert+"筆優惠卷種類");
//		 	
//
////		 修改，修改優惠卷名稱
//		 CouponVO couponVO2 = new CouponVO();	
//		 couponVO2.setCp_no("CPN00007");
//		 couponVO2.setCp_name("優惠卷1000元"); //注意VARACHA2(20)
//		 int updateCount_update = dao.updateCouponInformation(couponVO2);
//		 System.out.println("修改"+updateCount_update+"筆優惠卷資料");
//				
//
//		 //刪除優惠卷資料
//		 int updateCount_delete = dao.deleteCoupon("CPN00009");
//		 System.out.println("刪除"+updateCount_delete+"筆優惠卷資料");
//
//		// 查詢，用優惠卷編號查詢優惠卷資料		
//		CouponVO couponVO3 = dao.searchCoupon("CPN00005");
//		System.out.println("輸入的優惠券編號："+couponVO3.getCp_no());
//		System.out.print(couponVO3.getCp_name() + ",");
//		System.out.print(couponVO3.getCp_price());
		
		// 查詢，列出所有優惠卷資料
		List<CouponVO> list = dao.getAll();
		for (CouponVO aCouponVO : list) {
			System.out.print(aCouponVO.getCp_no() + ",");
			System.out.print(aCouponVO.getCp_name() + ",");
			System.out.print(aCouponVO.getCp_price() + ",");
            System.out.println(aCouponVO.getCp_detail() + ",");
            System.out.println(aCouponVO.getCp_javaTime());
			System.out.println();
		}
	}
}