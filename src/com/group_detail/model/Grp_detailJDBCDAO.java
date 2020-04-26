package com.group_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Grp_detailJDBCDAO implements Grp_detailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
			"INSERT INTO grp_detail (mb_id,grp_no,grp_register) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT mb_id,grp_no,grp_register FROM grp_detail order by mb_id";
	private static final String GET_ONE_STMT = 
			"SELECT mb_id,grp_no,grp_register FROM grp_detail where mb_id = ?";
	private static final String DELETE = 
			"DELETE FROM grp_detail where mb_id = ?";
	private static final String UPDATE = 
			"UPDATE grp_detail set grp_no=?, grp_register=? where mb_id = ?";
	private static final String GET_PEOPLE_COUNT = 
			"select count(1) as count from grp_detail where grp_no = ?";
	private static final String GET_GROUP_COUNT = 
			"select count(1) as count from grp_detail where mb_id = ?";	
	private static final String GET_SELECT_STMT = 
			"SELECT mb_id,grp_no,grp_register FROM grp_detail where mb_id = ?";

	@Override
	public void insert(Grp_detailVO grp_detaiVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, grp_detaiVO.getMb_id());
			pstmt.setString(2, grp_detaiVO.getGrp_no());
			pstmt.setInt(3, grp_detaiVO.getGrp_register());
		
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
	public void update(Grp_detailVO grp_detaiVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(3, grp_detaiVO.getMb_id());
			pstmt.setString(1, grp_detaiVO.getGrp_no());
			pstmt.setInt(2, grp_detaiVO.getGrp_register());
			
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
	public void delete(String mb_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mb_id);

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
	public Grp_detailVO findByPrimaryKey(String mb_id) {

		Grp_detailVO grp_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// grp_detailVO �]�٬� Domain objects
				grp_detailVO = new Grp_detailVO();
				grp_detailVO.setMb_id(rs.getString("mb_id"));
				grp_detailVO.setGrp_no(rs.getString("grp_no"));
				grp_detailVO.setGrp_register(rs.getInt("grp_register"));
				
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
		return grp_detailVO;
	}

	@Override
	public List<Grp_detailVO> getAll() {
		
		List<Grp_detailVO> list = new ArrayList<Grp_detailVO>();
		Grp_detailVO grp_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// grp_detailVO �]�٬� Domain objects
				grp_detailVO = new Grp_detailVO();				
				grp_detailVO.setMb_id(rs.getString("mb_id"));
				grp_detailVO.setGrp_no(rs.getString("grp_no"));
				grp_detailVO.setGrp_register(rs.getInt("grp_register"));
				list.add(grp_detailVO); // Store the row in the list
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
	public int totalPeople(String grp_no) {
		// TODO Auto-generated method stub		
		int getPeopleCount = 0 ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_PEOPLE_COUNT);					
			pstmt.setString(1, grp_no);			
			rs = pstmt.executeQuery();
			rs.next();
			getPeopleCount=rs.getInt("count");
			 
		 
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
		return  getPeopleCount;
	}
	
	@Override
	public int totalGroup(String mb_id) {
		// TODO Auto-generated method stub		
		int getGroupCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GROUP_COUNT);			
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			
			rs.next();
			getGroupCount = rs.getInt("count");

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
		return getGroupCount ;
	}
	@Override
	public List<Grp_detailVO> findByPrimaryKeyMbidGetAll(String mb_id) {
		
		List<Grp_detailVO> list2 = new ArrayList<Grp_detailVO>();
		Grp_detailVO grp_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_SELECT_STMT);

			pstmt.setString(1, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// grp_detailVO �]�٬� Domain objects
				grp_detailVO = new Grp_detailVO();
				grp_detailVO.setMb_id(rs.getString("mb_id"));
				grp_detailVO.setGrp_no(rs.getString("grp_no"));
				grp_detailVO.setGrp_register(rs.getInt("grp_register"));
				list2.add(grp_detailVO);
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
		return list2;
	}
	@Override
	public Grp_detailVO findByPrimaryKeyByGrp_no(String grp_no) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {

		Grp_detailJDBCDAO dao = new Grp_detailJDBCDAO();

		// 新增
//		Grp_detailVO grp_detailVO1 = new Grp_detailVO();				
//		grp_detailVO1.setMb_id("yiwen123");
//		grp_detailVO1.setGrp_no("grp00001");
//		grp_detailVO1.setGrp_register(1);
//		
//		dao.insert(grp_detailVO1);
//
//		// 修改
//		Grp_detailVO grp_detailVO2 = new Grp_detailVO();
//		grp_detailVO2.setMb_id("soowii123");
//		grp_detailVO2.setGrp_no("grp00002");
//		grp_detailVO2.setGrp_register(3);
//		dao.update(grp_detailVO2);
//
//		// 刪除
//		dao.delete("yiwen123");
//
//		// 查詢
//		Grp_detailVO grp_detailVO3 = dao.findByPrimaryKey("xuan123");
//		System.out.print(grp_detailVO3.getMb_id() + ",");
//		System.out.print(grp_detailVO3.getGrp_no() + ",");
//		System.out.println(grp_detailVO3.getGrp_register() + ",");
//		
//		System.out.println("---------------------");
////
		// 列表
//		List<Grp_detailVO> list = dao.getAll();
//		for (Grp_detailVO aEmp : list) {
//			System.out.print(aEmp.getMb_id() + ",");
//			System.out.print(aEmp.getGrp_no() + ",");
//			System.out.print(aEmp.getGrp_register() + ",");
//			System.out.println();
//		}	
//		//查詢該揪團之人數
//		int getPeopleCount = dao.totalPeople("grp00001");
//		System.out.print("getPeopleCount " + getPeopleCount);
//		//查詢該會員之揪團
//		int getGroupCount = dao.totalGroup("yiwen123");
//		System.out.print("getGroupCount " + getGroupCount);
		
		// 查詢多筆
		List<Grp_detailVO> list2 = dao.findByPrimaryKeyMbidGetAll("yiwen123");
		for (Grp_detailVO aEmp : list2) {
		System.out.print(aEmp.getMb_id() + ",");
		System.out.print(aEmp.getGrp_no() + ",");
		System.out.println(aEmp.getGrp_register() + ",");
		}
		System.out.println("---------------------");
	}


}