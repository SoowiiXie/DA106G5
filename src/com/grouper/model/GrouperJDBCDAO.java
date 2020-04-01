package com.grouper.model;

import java.util.*;
import java.sql.*;

public class GrouperJDBCDAO implements GrouperDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO Grouper (GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW) VALUES (grp_no_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT GRP_NO,MB_ID,LOC_NO,to_char(GRP_APPLYSTART,'yyyy-mm-dd HH24:MI')GRP_APPLYSTART,to_char(GRP_APPLYEND,'yyyy-mm-dd HH24:MI')GRP_APPLYEND,to_char(GRP_START,'yyyy-mm-dd HH24:MI')GRP_START,to_char(GRP_END,'yyyy-mm-dd HH24:MI')GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW FROM Grouper order by GRP_NO";
	private static final String GET_ONE_STMT = 
		"SELECT GRP_NO,MB_ID,LOC_NO,to_char(GRP_APPLYSTART,'yyyy-mm-dd HH24:MI')GRP_APPLYSTART,to_char(GRP_APPLYEND,'yyyy-mm-dd HH24:MI')GRP_APPLYEND,to_char(GRP_START,'yyyy-mm-dd HH24:MI')GRP_START,to_char(GRP_END,'yyyy-mm-dd HH24:MI')GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW FROM Grouper where GRP_NO = ?";
	private static final String DELETE = 
		"DELETE FROM Grouper where GRP_NO = ?";
	private static final String UPDATE = 
		"UPDATE Grouper set MB_ID=?, LOC_NO=?, GRP_APPLYSTART=?, GRP_APPLYEND=?, GRP_START=?, GRP_END=?, GRP_NAME=?, GRP_CONTENT=?, GRP_PERSONMAX=?, GRP_PERSONMIN=?, GRP_PERSONCOUNT=?, GRP_STATUS=?, GRP_FOLLOW=? where GRP_NO = ?";

	@Override
	public void insert(GrouperVO grouperVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, grouperVO.getGrp_no());
			pstmt.setString(2, grouperVO.getMb_id());
			pstmt.setString(3, grouperVO.getLoc_no());
			pstmt.setDate(4, grouperVO.getGrp_applystart());
			pstmt.setDate(5, grouperVO.getGrp_applyend());
			pstmt.setDate(6, grouperVO.getGrp_start());
			pstmt.setDate(7, grouperVO.getGrp_end());
			pstmt.setString(8, grouperVO.getGrp_name());
			pstmt.setString(9, grouperVO.getGrp_content());
			pstmt.setInt(10, grouperVO.getGrp_personmax());
			pstmt.setInt(11, grouperVO.getGrp_personmin());
			pstmt.setInt(12, grouperVO.getGrp_personcount());
			pstmt.setInt(13, grouperVO.getGrp_status());
			pstmt.setInt(14, grouperVO.getGrp_follow());

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
	public void update(GrouperVO grouperVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, grouperVO.getGrp_no());
			pstmt.setString(2, grouperVO.getMb_id());
			pstmt.setString(3, grouperVO.getLoc_no());
			pstmt.setDate(4, grouperVO.getGrp_applystart());
			pstmt.setDate(5, grouperVO.getGrp_applyend());
			pstmt.setDate(6, grouperVO.getGrp_start());
			pstmt.setDate(7, grouperVO.getGrp_end());
			pstmt.setString(8, grouperVO.getGrp_name());
			pstmt.setString(9, grouperVO.getGrp_content());
			pstmt.setInt(10, grouperVO.getGrp_personmax());
			pstmt.setInt(11, grouperVO.getGrp_personmin());
			pstmt.setInt(12, grouperVO.getGrp_personcount());
			pstmt.setInt(13, grouperVO.getGrp_status());
			pstmt.setInt(14, grouperVO.getGrp_follow());

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
	public void delete(String Grp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, Grp_no);

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
	public GrouperVO findByPrimaryKey(String Grp_no) {

		GrouperVO grouperVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, Grp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				grouperVO = new GrouperVO();
				grouperVO.setGrp_no(rs.getString("Grp_no"));
				grouperVO.setMb_id(rs.getString("Mb_id"));
				grouperVO.setLoc_no(rs.getString("Loc_no"));
				grouperVO.setGrp_applystart(rs.getDate("Grp_applystart"));
				grouperVO.setGrp_applyend(rs.getDate("Grp_applyend"));
				grouperVO.setGrp_start(rs.getDate("Grp_start"));
				grouperVO.setGrp_end(rs.getDate("Grp_end"));
				grouperVO.setGrp_name(rs.getString("Grp_name"));
				grouperVO.setGrp_content(rs.getString("Grp_content"));
				grouperVO.setGrp_personmax(rs.getInt("Grp_personmax"));
				grouperVO.setGrp_personmin(rs.getInt("Grp_personmin"));
				grouperVO.setGrp_personcount(rs.getInt("Grp_personcount"));
				grouperVO.setGrp_status(rs.getInt("Grp_status"));
				grouperVO.setGrp_follow(rs.getInt("Grp_follow"));
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
		return grouperVO;
	}

	@Override
	public List<GrouperVO> getAll() {
		List<GrouperVO> list = new ArrayList<GrouperVO>();
		GrouperVO grouperVO = null;

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
				grouperVO = new GrouperVO();
				grouperVO.setGrp_no(rs.getString("Grp_no"));
				grouperVO.setMb_id(rs.getString("Mb_id"));
				grouperVO.setLoc_no(rs.getString("Loc_no"));
				grouperVO.setGrp_applystart(rs.getDate("Grp_applystart"));
				grouperVO.setGrp_applyend(rs.getDate("Grp_applyend"));
				grouperVO.setGrp_start(rs.getDate("Grp_start"));
				grouperVO.setGrp_end(rs.getDate("Grp_end"));
				grouperVO.setGrp_name(rs.getString("Grp_name"));
				grouperVO.setGrp_content(rs.getString("Grp_content"));
				grouperVO.setGrp_personmax(rs.getInt("Grp_personmax"));
				grouperVO.setGrp_personmin(rs.getInt("Grp_personmin"));
				grouperVO.setGrp_personcount(rs.getInt("Grp_personcount"));
				grouperVO.setGrp_status(rs.getInt("Grp_status"));
				grouperVO.setGrp_follow(rs.getInt("Grp_follow"));
				list.add(grouperVO); // Store the row in the list
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

		GrouperJDBCDAO dao = new GrouperJDBCDAO();

		// 新增
		GrouperVO grpVO1 = new GrouperVO();
		grpVO1.setGrp_no("吳永志1");
		grpVO1.setMb_id("MANAGER");
		grpVO1.setLoc_no("");
		grpVO1.setGrp_applystart(java.sql.Date.valueOf("2020-04-01 18:00"));
		grpVO1.setGrp_applyend(java.sql.Date.valueOf("2020-04-01 21:00"));
		grpVO1.setGrp_start(java.sql.Date.valueOf("2020-03-31 08:00"));
		grpVO1.setGrp_end(java.sql.Date.valueOf("2005-03-31 18:00"));
		grpVO1.setGrp_name("");
		grpVO1.setGrp_content("");
		grpVO1.setGrp_personmax(new Integer(30));
		grpVO1.setGrp_personmin(new Integer(3));
		grpVO1.setGrp_personcount(new Integer(25));
		grpVO1.setGrp_status(new Integer(1));
		grpVO1.setGrp_follow(new Integer(50));
		dao.insert(grpVO1);

		// 修改
		GrouperVO grpVO2 = new GrouperVO();
		grpVO2.setGrp_no("吳永志1");
		grpVO2.setMb_id("MANAGER");
		grpVO2.setLoc_no("");
		grpVO2.setGrp_applystart(java.sql.Date.valueOf("2020-04-01 18:00"));
		grpVO2.setGrp_applyend(java.sql.Date.valueOf("2020-04-01 21:00"));
		grpVO2.setGrp_start(java.sql.Date.valueOf("2020-03-31 08:00"));
		grpVO2.setGrp_end(java.sql.Date.valueOf("2005-03-31 18:00"));
		grpVO2.setGrp_name("");
		grpVO2.setGrp_content("");
		grpVO2.setGrp_personmax(new Integer(30));
		grpVO2.setGrp_personmin(new Integer(3));
		grpVO2.setGrp_personcount(new Integer(25));
		grpVO2.setGrp_status(new Integer(1));
		grpVO2.setGrp_follow(new Integer(50));
		dao.update(grpVO2);

		// 刪除
		dao.delete("7014");

		// 查詢
		GrouperVO grpVO3 = dao.findByPrimaryKey("7001");
		System.out.print(grpVO3.getGrp_no() + ",");
		System.out.print(grpVO3.getMb_id() + ",");
		System.out.print(grpVO3.getLoc_no() + ",");
		System.out.print(grpVO3.getGrp_applystart() + ",");
		System.out.print(grpVO3.getGrp_applyend() + ",");
		System.out.print(grpVO3.getGrp_start() + ",");
		System.out.println(grpVO3.getGrp_end());
		System.out.print(grpVO3.getGrp_name() + ",");
		System.out.print(grpVO3.getGrp_content() + ",");
		System.out.print(grpVO3.getGrp_personmax() + ",");
		System.out.print(grpVO3.getGrp_personmin() + ",");
		System.out.print(grpVO3.getGrp_personcount() + ",");
		System.out.print(grpVO3.getGrp_status() + ",");
		System.out.println(grpVO3.getGrp_follow());
		System.out.println("---------------------");

		// 查詢
		List<GrouperVO> list = dao.getAll();
		for (GrouperVO aGrp : list) {
			System.out.print(aGrp.getGrp_no() + ",");
			System.out.print(aGrp.getMb_id() + ",");
			System.out.print(aGrp.getLoc_no() + ",");
			System.out.print(aGrp.getGrp_applystart() + ",");
			System.out.print(aGrp.getGrp_applyend() + ",");
			System.out.print(aGrp.getGrp_start() + ",");
			System.out.print(aGrp.getGrp_end());
			System.out.print(aGrp.getGrp_name() + ",");
			System.out.print(aGrp.getGrp_content() + ",");
			System.out.print(aGrp.getGrp_personmax() + ",");
			System.out.print(aGrp.getGrp_personmin() + ",");
			System.out.print(aGrp.getGrp_personcount() + ",");
			System.out.print(aGrp.getGrp_status() + ",");
			System.out.print(aGrp.getGrp_follow());
			System.out.println();
		}
	}
}