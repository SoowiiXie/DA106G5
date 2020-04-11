<<<<<<< HEAD
package com.group_rpt.model;

import java.util.*;
import java.sql.*;

public class Group_rptJDBCDAO implements Group_rptDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
			"INSERT INTO group_rpt (group_rpt_no,grp_no,mb_id,rpt_reason,rpt_status) VALUES ('grr'||LPAD(to_char(group_rpt_no_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT group_rpt_no,grp_no,mb_id,rpt_reason,rpt_status FROM group_rpt order by group_rpt_no";
		private static final String GET_ONE_STMT = 
			"SELECT group_rpt_no,grp_no,mb_id,rpt_reason,rpt_status FROM group_rpt where group_rpt_no = ?";
		private static final String DELETE = 
			"DELETE FROM group_rpt where group_rpt_no = ?";
		private static final String UPDATE = 
			"UPDATE group_rpt set grp_no=?, mb_id=?, rpt_reason=?, rpt_status=? where group_rpt_no = ?";

	@Override
	public void insert(Group_rptVO group_rptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			//pstmt.setString(1, group_rptVO.getGroup_rpt_no());
			pstmt.setString(1, group_rptVO.getGrp_no());			
			pstmt.setString(2, group_rptVO.getMb_id());
			pstmt.setString(3, group_rptVO.getRpt_reason());
			pstmt.setInt(4, group_rptVO.getRpt_status());;

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
	public void update(Group_rptVO group_rptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(5, group_rptVO.getGroup_rpt_no());
			pstmt.setString(1, group_rptVO.getGrp_no());			
			pstmt.setString(2, group_rptVO.getMb_id());
			pstmt.setString(3, group_rptVO.getRpt_reason());
			pstmt.setInt(4, group_rptVO.getRpt_status());

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
	public void delete(String group_rpt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, group_rpt_no);

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
	public Group_rptVO findByPrimaryKey(String group_rpt_no) {

		Group_rptVO group_rptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, group_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				group_rptVO = new Group_rptVO();
				group_rptVO.setGroup_rpt_no(rs.getString("group_rpt_no"));
				group_rptVO.setGrp_no(rs.getString("grp_no"));				
				group_rptVO.setMb_id(rs.getString("mb_id"));
				group_rptVO.setRpt_reason(rs.getString("rpt_reason"));
				group_rptVO.setRpt_status(rs.getInt("rpt_status"));
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
		return group_rptVO;
	}

	@Override
	public List<Group_rptVO> getAll() {
		List<Group_rptVO> list = new ArrayList<Group_rptVO>();
		Group_rptVO group_rptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				group_rptVO = new Group_rptVO();
				group_rptVO.setGroup_rpt_no(rs.getString("group_rpt_no"));
				group_rptVO.setGrp_no(rs.getString("grp_no"));
				group_rptVO.setMb_id(rs.getString("mb_id"));
				group_rptVO.setRpt_reason(rs.getString("rpt_reason"));				
				group_rptVO.setRpt_status(rs.getInt("rpt_status"));
				list.add(group_rptVO); // Store the row in the list
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

		Group_rptJDBCDAO dao = new Group_rptJDBCDAO();

//		// 增
//		Group_rptVO group_rptVO1 = new Group_rptVO();
////		group_rptVO1.setGroup_rpt_no(new Double(500));
//		group_rptVO1.setGrp_no("grp00002");
//		group_rptVO1.setMb_id("michael123");		
//		group_rptVO1.setRpt_reason("");
//		group_rptVO1.setRpt_status(1);
//		dao.insert(group_rptVO1);
//
//		// 改
//		Group_rptVO group_rptVO2 = new Group_rptVO();
//		group_rptVO2.setGroup_rpt_no("grr00009");
//		group_rptVO2.setGrp_no("grp00005");
//		group_rptVO2.setMb_id("vain123");
//		group_rptVO2.setRpt_reason("");		
//		group_rptVO2.setRpt_status(2);
//		dao.update(group_rptVO2);
//
//		// 刪
//		dao.delete("grr00007");
//
//		// 查
//		Group_rptVO group_rptVO3 = dao.findByPrimaryKey("grr00002");
//		System.out.print(group_rptVO3.getGroup_rpt_no() + ",");
//		System.out.print(group_rptVO3.getGrp_no() + ",");	
//		System.out.print(group_rptVO3.getMb_id() + ",");
//		System.out.print(group_rptVO3.getRpt_reason() + ",");
//		System.out.println(group_rptVO3.getRpt_status());
//		System.out.println("---------------------");
//
//		// 查全部
//		List<Group_rptVO> list = dao.getAll();
//		for (Group_rptVO aGroup_rpt : list) {
//			System.out.print(aGroup_rpt.getGroup_rpt_no() + ",");
//			System.out.print(aGroup_rpt.getGrp_no() + ",");			
//			System.out.print(aGroup_rpt.getMb_id() + ",");
//			System.out.print(aGroup_rpt.getRpt_reason() + ",");
//			System.out.print(aGroup_rpt.getRpt_status());
//			System.out.println();
//		}
	}
=======
package com.group_rpt.model;

import java.util.*;
import java.sql.*;

public class Group_rptJDBCDAO implements Group_rptDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106G5";
	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
			"INSERT INTO group_rpt (group_rpt_no,grp_no,mb_id,rpt_reason,rpt_status) VALUES ('grr'||LPAD(to_char(group_rpt_no_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT group_rpt_no,grp_no,mb_id,rpt_reason,rpt_status FROM group_rpt order by group_rpt_no";
		private static final String GET_ONE_STMT = 
			"SELECT group_rpt_no,grp_no,mb_id,rpt_reason,rpt_status FROM group_rpt where group_rpt_no = ?";
		private static final String DELETE = 
			"DELETE FROM group_rpt where group_rpt_no = ?";
		private static final String UPDATE = 
			"UPDATE group_rpt set grp_no=?, mb_id=?, rpt_reason=?, rpt_status=? where group_rpt_no = ?";

	@Override
	public void insert(Group_rptVO group_rptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			//pstmt.setString(1, group_rptVO.getGroup_rpt_no());
			pstmt.setString(1, group_rptVO.getGrp_no());			
			pstmt.setString(2, group_rptVO.getMb_id());
			pstmt.setString(3, group_rptVO.getRpt_reason());
			pstmt.setInt(4, group_rptVO.getRpt_status());;

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
	public void update(Group_rptVO group_rptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(5, group_rptVO.getGroup_rpt_no());
			pstmt.setString(1, group_rptVO.getGrp_no());			
			pstmt.setString(2, group_rptVO.getMb_id());
			pstmt.setString(3, group_rptVO.getRpt_reason());
			pstmt.setInt(4, group_rptVO.getRpt_status());

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
	public void delete(String group_rpt_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, group_rpt_no);

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
	public Group_rptVO findByPrimaryKey(String group_rpt_no) {

		Group_rptVO group_rptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, group_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				group_rptVO = new Group_rptVO();
				group_rptVO.setGroup_rpt_no(rs.getString("group_rpt_no"));
				group_rptVO.setGrp_no(rs.getString("grp_no"));				
				group_rptVO.setMb_id(rs.getString("mb_id"));
				group_rptVO.setRpt_reason(rs.getString("rpt_reason"));
				group_rptVO.setRpt_status(rs.getInt("rpt_status"));
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
		return group_rptVO;
	}

	@Override
	public List<Group_rptVO> getAll() {
		List<Group_rptVO> list = new ArrayList<Group_rptVO>();
		Group_rptVO group_rptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				group_rptVO = new Group_rptVO();
				group_rptVO.setGroup_rpt_no(rs.getString("group_rpt_no"));
				group_rptVO.setGrp_no(rs.getString("grp_no"));
				group_rptVO.setMb_id(rs.getString("mb_id"));
				group_rptVO.setRpt_reason(rs.getString("rpt_reason"));				
				group_rptVO.setRpt_status(rs.getInt("rpt_status"));
				list.add(group_rptVO); // Store the row in the list
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

		Group_rptJDBCDAO dao = new Group_rptJDBCDAO();

		// 增
//		Group_rptVO group_rptVO1 = new Group_rptVO();
////		group_rptVO1.setGroup_rpt_no(new Double(500));
//		group_rptVO1.setGrp_no("grp00002");
//		group_rptVO1.setMb_id("michael123");		
//		group_rptVO1.setRpt_reason("");
//		group_rptVO1.setRpt_status(1);
//		dao.insert(group_rptVO1);

//		// 改
//		Group_rptVO group_rptVO2 = new Group_rptVO();
//		group_rptVO2.setGroup_rpt_no("grr00007");
//		group_rptVO2.setGrp_no("grp00005");
//		group_rptVO2.setMb_id("vain123");
//		group_rptVO2.setRpt_reason("");		
//		group_rptVO2.setRpt_status(2);
//		dao.update(group_rptVO2);

//		// 刪
//		dao.delete("grr00006");
//
//		// 查
//		Group_rptVO group_rptVO3 = dao.findByPrimaryKey("grr00002");
//		System.out.print(group_rptVO3.getGroup_rpt_no() + ",");
//		System.out.print(group_rptVO3.getGrp_no() + ",");	
//		System.out.print(group_rptVO3.getMb_id() + ",");
//		System.out.print(group_rptVO3.getRpt_reason() + ",");
//		System.out.println(group_rptVO3.getRpt_status());
//		System.out.println("---------------------");
//
		// 查全部
		List<Group_rptVO> list = dao.getAll();
		for (Group_rptVO aGroup_rpt : list) {
			System.out.print(aGroup_rpt.getGroup_rpt_no() + ",");
			System.out.print(aGroup_rpt.getGrp_no() + ",");			
			System.out.print(aGroup_rpt.getMb_id() + ",");
			System.out.print(aGroup_rpt.getRpt_reason() + ",");
			System.out.print(aGroup_rpt.getRpt_status());
			System.out.println();
		}
	}
>>>>>>> branch 'master' of https://github.com/SoowiiXie/DA106G5.git
}