package com.group_rpt.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Group_rptDAO implements Group_rptDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO group_rpt (group_rpt_no,grp_no,mb_id,rpt_reason,rpt_status) VALUES ('grr'||LPAD(to_char(group_rpt_no_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT group_rpt_no,grp_no,mb_id,rpt_reason,rpt_status FROM group_rpt order by group_rpt_no";
	private static final String GET_ONE_STMT = 
		"SELECT group_rpt_no,grp_no,mb_id,rpt_reason,rpt_status FROM group_rpt FROM group_rpt where group_rpt_no = ?";
	private static final String DELETE = 
		"DELETE FROM group_rpt where group_rpt_no = ?";
	private static final String UPDATE = 
		"UPDATE group_rpt set grp_no=?, mb_id=?, rpt_reason=?, rpt_status=? where group_rpt_no = ?";

	@Override
	public void insert(Group_rptVO group_rptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			//pstmt.setString(1, group_rptVO.getGroup_rpt_no());
			pstmt.setString(1, group_rptVO.getGrp_no());			
			pstmt.setString(2, group_rptVO.getMb_id());
			pstmt.setString(3, group_rptVO.getRpt_reason());
			pstmt.setInt(4, group_rptVO.getRpt_status());;

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, group_rptVO.getGroup_rpt_no());
			pstmt.setString(2, group_rptVO.getGrp_no());			
			pstmt.setString(3, group_rptVO.getMb_id());
			pstmt.setString(4, group_rptVO.getRpt_reason());
			pstmt.setInt(5, group_rptVO.getRpt_status());

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, group_rpt_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
}