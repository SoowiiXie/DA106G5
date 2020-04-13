package com.pd_type.model;

import java.util.*;
import java.sql.*;

public class Pd_typeDAO implements Pd_typeDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:49161:xe";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
		"INSERT INTO PD_TYPE (PD_TYPENO, PD_TYPENAME) VALUES ('PTN'||LPAD(to_char(PD_TYPE_seq.NEXTVAL), 5, '0'), ?)";
	private static final String GET_ALL_STMT = 
		"SELECT PD_TYPENO, PD_TYPENAME FROM PD_TYPE order by PD_TYPENO";
	private static final String GET_ONE_STMT = 
		"SELECT PD_TYPENO, PD_TYPENAME FROM PD_TYPE where PD_TYPENO = ?";
	private static final String DELETE = 
		"DELETE FROM PD_TYPE where PD_TYPENO = ?";
	private static final String UPDATE = 
		"UPDATE PD_TYPE set PD_TYPENAME = ? where PD_TYPENO = ?";

	@Override
	public int addProductType(Pd_typeVO pd_typeVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);			
			String cols[] = {"PD_TYPENO"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			
			pstmt.setString(1, pd_typeVO.getPd_typeName());

			updateCount = pstmt.executeUpdate();
			
			//掘取對應的自增主鍵值
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						String key = rs.getString(i);
						System.out.println("自增主鍵值(" + i + ") = " + key +"(剛新增成功的商品類別)");
						System.out.println("");
						System.out.println("商品類別編號："+key);				
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
	public int updateTypeName(Pd_typeVO pd_typeVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
            
			pstmt.setString(1, pd_typeVO.getPd_typeName());
			pstmt.setString(2, pd_typeVO.getPd_typeNo());
			
			

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
	public int delete(String pd_typeNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, pd_typeNo);
			
			updateCount = pstmt.executeUpdate();
			
			System.out.println("已刪除"+pd_typeNo);

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
	public Pd_typeVO searchType(String pd_typeNo) {

		Pd_typeVO pd_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, pd_typeNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				pd_typeVO = new Pd_typeVO();
				pd_typeVO.setPd_typeNo(rs.getString("pd_typeNo"));
				pd_typeVO.setPd_typeName(rs.getString("pd_typeName"));

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
		return pd_typeVO;
	}

	@Override
	public List<Pd_typeVO> getAll() {
		List<Pd_typeVO> list = new ArrayList<Pd_typeVO>();
		Pd_typeVO pd_typeVO = null;

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
				pd_typeVO = new Pd_typeVO();
				pd_typeVO.setPd_typeNo(rs.getString("pd_typeno"));
				pd_typeVO.setPd_typeName(rs.getString("pd_typeName"));

				list.add(pd_typeVO); // Store the row in the vector
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

		Pd_typeDAO dao = new Pd_typeDAO();

		 //新增
//		Pd_typeVO pd_typeVO1 = new Pd_typeVO();
//		pd_typeVO1.setPd_typeName("測試用分類");
//		dao.addProductType(pd_typeVO1);
//		System.out.println("類別名稱："+pd_typeVO1.getPd_typeName());
	    
				

		 //-----------------類別名稱修改-----------------
		
//		 Pd_typeVO pd_typeVO2 = new Pd_typeVO();
//		 pd_typeVO2.setPd_typeNo("PTN00021");
//		 pd_typeVO2.setPd_typeName("TEST");
//		 int updateCount_update = dao.updateTypeName(pd_typeVO2);
//	   	 System.out.println("-----類別名稱修改-----");
//	     System.out.println("");
//	     System.out.println("輸入商品類別編號："+pd_typeVO2.getPd_typeNo());
//	     System.out.println("商品類別名稱："+pd_typeVO2.getPd_typeName());
//	     System.out.println("類別名稱修改"+updateCount_update+"次");
//		 System.out.println();		

		 //-----------------刪除類別修改-----------------
//		  System.out.println("-----刪除類別-----");
//		  dao.delete("PTN00010");
//		 

		// -----------------查詢單一類別-----------------
		
//		Pd_typeVO pd_typeVO3 = dao.searchType("PTN00003");
//		System.out.println("-----查詢商品類別-----");
//		System.out.println("");
//		System.out.println("輸入查詢商品類別編號："+pd_typeVO3.getPd_typeNo());
//		System.out.println("");
//		System.out.print("該商品類別編號的類別為："+pd_typeVO3.getPd_typeName());
		

	

		// 查詢所有類別
//		List<Pd_typeVO> list = dao.getAll();
//		for (Pd_typeVO aPd_typeVO : list) {
//			System.out.print(aPd_typeVO.getPd_typeNo() + ",");
//			System.out.print(aPd_typeVO.getPd_typeName());
//			
//			System.out.println("");
//			
//		}
	}
	}
