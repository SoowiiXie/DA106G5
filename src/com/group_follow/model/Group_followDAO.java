package com.group_follow.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.group_detail.model.Grp_detailVO;

public class Group_followDAO implements Group_followDAO_interface {

	//一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G5_DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		private static final String INSERT_STMT = 
			"INSERT INTO grp_follow (GRP_NO,MB_ID) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT GRP_NO,MB_ID FROM grp_follow order by GRP_NO";
		private static final String GET_ONE_STMT = 
			"SELECT GRP_NO,MB_ID FROM grp_follow where GRP_NO = ?";
		private static final String GET_ONE_STMTGRPNO = 
				"SELECT mb_id,grp_no FROM grp_follow where grp_no = ?";
		private static final String INSERT_TWO = 
			"INSERT INTO grp_follow (GRP_NO,MB_ID) VALUES (?, ?)";	
		private static final String DELETE_TWO = 
			"DELETE FROM grp_follow where GRP_NO=? and  MB_ID = ?";
		private static final String UPDATE = 
			"UPDATE grp_follow set MB_ID=? where GRP_NO = ?";
		private static final String GET_FOLLOWPEOPLE_COUNT = 
				"select count(1) as count from grp_follow where grp_no = ?";
		private static final String GET_FOLLOWGROUP_COUNT = 
				"select count(1) as count from grp_follow where mb_id = ?";
		private static final String GET_ONE_STMT_MB_ID = 
				"SELECT GRP_NO,MB_ID FROM grp_follow where MB_ID = ?";
		private static final String IS_FOLLOW = 
			"SELECT * FROM grp_follow WHERE GRP_NO = ? AND MB_ID = ?";

	@Override
	public void insert(Group_followVO group_followVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, group_followVO.getGrp_no());
			pstmt.setString(2, group_followVO.getMb_id());
			
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
	public void update(Group_followVO group_followVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, group_followVO.getMb_id());
			pstmt.setString(2, group_followVO.getGrp_no());

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
	public void delete(Group_followVO group_followVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_TWO);

			pstmt.setString(1, group_followVO.getMb_id());
			pstmt.setString(2, group_followVO.getGrp_no());

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
	public List<Group_followVO> findByPrimaryKey(String mb_id) {
		
		Group_followVO group_followVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Group_followVO> list = new ArrayList<Group_followVO>();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_MB_ID);

			pstmt.setString(1, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				group_followVO = new Group_followVO();
				group_followVO.setMb_id(rs.getString("mb_id"));
				group_followVO.setGrp_no(rs.getString("grp_no"));
				list.add(group_followVO); // Store the row in the list
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

	@Override
	public List<Group_followVO> getAll() {
		List<Group_followVO> list = new ArrayList<Group_followVO>();
		Group_followVO group_followVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// grouperVO �]�٬� Domain objects
				group_followVO = new Group_followVO();
				group_followVO.setGrp_no(rs.getString("grp_no"));
				group_followVO.setMb_id(rs.getString("mb_id"));
				list.add(group_followVO); // Store the row in the list
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
	@Override
	public int totalFollowPeople(String grp_no) {
		int totalFollowPeople = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();					
			pstmt = con.prepareStatement(GET_FOLLOWPEOPLE_COUNT);
			pstmt.setString(1, grp_no);
			rs = pstmt.executeQuery();
			rs.next();
			totalFollowPeople = rs.getInt("count");

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
		return totalFollowPeople ;
	}
	@Override
	public int totalFollowGroup(String mb_id) {
		// TODO Auto-generated method stub
		int totalFollowGroup = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();					
			pstmt = con.prepareStatement(GET_FOLLOWGROUP_COUNT);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			rs.next();
			totalFollowGroup = rs.getInt("count");

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
		return totalFollowGroup ;
	}
	@Override
	public Group_followVO findByPrimaryKeyByMb_id(String mb_id) {
		Group_followVO group_followVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMTGRPNO);

			pstmt.setString(1, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				group_followVO = new Group_followVO();
				group_followVO.setMb_id(rs.getString("mb_id"));
				group_followVO.setGrp_no(rs.getString("grp_no"));
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
		return group_followVO;
	}
	@Override
	public void insertByTwo(String grp_no, String mb_id) {
		// TODO Auto-generated method stub
		System.out.println("INI");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_TWO);

			pstmt.setString(1, grp_no);
			pstmt.setString(2, mb_id);

			pstmt.executeUpdate();
			System.out.println("endI");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void deleteByTwo(String grp_no, String mb_id) {
		// TODO Auto-generated method stub
		System.out.println("IND");
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_TWO);

			pstmt.setString(1, grp_no);
			pstmt.setString(2, mb_id);

			pstmt.executeUpdate();
			System.out.println("endD");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public boolean isFollow(String grp_no, String mb_id) {
		// TODO Auto-generated method stub

		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(IS_FOLLOW);
			
			pstmt.setString(1, grp_no);
			pstmt.setString(2, mb_id);
			
			rs = pstmt.executeQuery();
			System.out.println("OKA");
			if(rs.next()) {
				System.out.println("OKB");
				rs.getInt(1);
				result = true;
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
		System.out.println("finished");
		return result;
	
	}
}