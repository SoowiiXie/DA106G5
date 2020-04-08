package com.grouper.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class GrouperDAO implements GrouperDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G5_DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//GRP_NO,MB_ID,LOC_NO,to_char(GRP_APPLYSTART,'yyyy-mm-dd HH24:MI')GRP_APPLYSTART,to_char(GRP_APPLYEND,'yyyy-mm-dd HH24:MI')GRP_APPLYEND,to_char(GRP_START,'yyyy-mm-dd HH24:MI')GRP_START,to_char(GRP_END,'yyyy-mm-dd HH24:MI')GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW 
	private static final String INSERT_STMT = 
		"INSERT INTO Grouper (GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW) VALUES ('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM Grouper order by GRP_NO";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM Grouper where GRP_NO = ?";
	private static final String DELETE = 
		"DELETE FROM Grouper where GRP_NO = ?";
	private static final String UPDATE = 
		"UPDATE Grouper set MB_ID=?, LOC_NO=?, GRP_APPLYSTART=?, GRP_APPLYEND=?, GRP_START=?, GRP_END=?, GRP_NAME=?, GRP_CONTENT=?, GRP_PERSONMAX=?, GRP_PERSONMIN=?, GRP_PERSONCOUNT=?, GRP_STATUS=?, GRP_FOLLOW=? where GRP_NO = ?";

	@Override
	public void insert(GrouperVO grouperVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setString(1, grouperVO.getGrp_no());
			pstmt.setString(1, grouperVO.getMb_id());
			pstmt.setString(2, grouperVO.getLoc_no());
			pstmt.setTimestamp(3, grouperVO.getGrp_applystart());
			pstmt.setTimestamp(4, grouperVO.getGrp_applyend());
			pstmt.setTimestamp(5, grouperVO.getGrp_start());
			pstmt.setTimestamp(6, grouperVO.getGrp_end());
			pstmt.setString(7, grouperVO.getGrp_name());
			pstmt.setString(8, grouperVO.getGrp_content());
			pstmt.setInt(9, grouperVO.getGrp_personmax());
			pstmt.setInt(10, grouperVO.getGrp_personmin());
			pstmt.setInt(11, grouperVO.getGrp_personcount());
			pstmt.setInt(12, grouperVO.getGrp_status());
			pstmt.setInt(13, grouperVO.getGrp_follow());

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
	public void update(GrouperVO grouperVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, grouperVO.getMb_id());
			pstmt.setString(2, grouperVO.getLoc_no());
			pstmt.setTimestamp(3, grouperVO.getGrp_applystart());
			pstmt.setTimestamp(4, grouperVO.getGrp_applyend());
			pstmt.setTimestamp(5, grouperVO.getGrp_start());
			pstmt.setTimestamp(6, grouperVO.getGrp_end());
			pstmt.setString(7, grouperVO.getGrp_name());
			pstmt.setString(8, grouperVO.getGrp_content());
			pstmt.setInt(9, grouperVO.getGrp_personmax());
			pstmt.setInt(10, grouperVO.getGrp_personmin());
			pstmt.setInt(11, grouperVO.getGrp_personcount());
			pstmt.setInt(12, grouperVO.getGrp_status());
			pstmt.setInt(13, grouperVO.getGrp_follow());
			pstmt.setString(14, grouperVO.getGrp_no());

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
	public void delete(String Grp_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, Grp_no);

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
	public GrouperVO findByPrimaryKey(String grp_no) {

		GrouperVO grouperVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, grp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// grouperVO �]�٬� Domain objects
				grouperVO = new GrouperVO();
				grouperVO.setGrp_no(rs.getString("Grp_no"));
				grouperVO.setMb_id(rs.getString("Mb_id"));
				grouperVO.setLoc_no(rs.getString("Loc_no"));
				grouperVO.setGrp_applystart(rs.getTimestamp("Grp_applystart"));
				grouperVO.setGrp_applyend(rs.getTimestamp("Grp_applyend"));
				grouperVO.setGrp_start(rs.getTimestamp("Grp_start"));
				grouperVO.setGrp_end(rs.getTimestamp("Grp_end"));
				grouperVO.setGrp_name(rs.getString("Grp_name"));
				grouperVO.setGrp_content(rs.getString("Grp_content"));
				grouperVO.setGrp_personmax(rs.getInt("Grp_personmax"));
				grouperVO.setGrp_personmin(rs.getInt("Grp_personmin"));
				grouperVO.setGrp_personcount(rs.getInt("Grp_personcount"));
				grouperVO.setGrp_status(rs.getInt("Grp_status"));
				grouperVO.setGrp_follow(rs.getInt("Grp_follow"));
		
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// grouperVO �]�٬� Domain objects
				grouperVO = new GrouperVO();
				grouperVO.setGrp_no(rs.getString("grp_no"));
				grouperVO.setMb_id(rs.getString("mb_id"));
				grouperVO.setLoc_no(rs.getString("loc_no"));
				grouperVO.setGrp_applystart(rs.getTimestamp("grp_applystart"));
				grouperVO.setGrp_applyend(rs.getTimestamp("grp_applyend"));
				grouperVO.setGrp_start(rs.getTimestamp("grp_start"));
				grouperVO.setGrp_end(rs.getTimestamp("grp_end"));
				grouperVO.setGrp_name(rs.getString("grp_name"));
				grouperVO.setGrp_content(rs.getString("grp_content"));
				grouperVO.setGrp_personmax(rs.getInt("grp_personmax"));
				grouperVO.setGrp_personmin(rs.getInt("grp_personmin"));
				grouperVO.setGrp_personcount(rs.getInt("grp_personcount"));
				grouperVO.setGrp_status(rs.getInt("grp_status"));
				grouperVO.setGrp_follow(rs.getInt("grp_follow"));
				list.add(grouperVO); // Store the row in the list
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