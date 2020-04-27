package com.group_detail.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Grp_detailDAO implements Grp_detailDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
			"INSERT INTO grp_detail (mb_id,grp_no) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT mb_id,grp_no,grp_register FROM grp_detail order by mb_id";
	private static final String GET_ONE_STMT = 
			"SELECT mb_id,grp_no,grp_register FROM grp_detail where mb_id = ?";
	private static final String GET_ONE_STMTGRPNO = 
			"SELECT mb_id,grp_no,grp_register FROM grp_detail where grp_no = ?";
	private static final String DELETE = 
			"DELETE FROM grp_detail where mb_id = ?";
	private static final String UPDATE = 
			"UPDATE grp_detail set grp_no=?, grp_register=? where mb_id = ?";
	private static final String GET_PEOPLE_COUNT = 
			"select count(1) as count from grp_detail where grp_no = ?";
	private static final String GET_GROUP_COUNT = 
			"select count(1) as count from grp_detail where mb_id = ?";
	private static final String GET_SELECT_STMT = 
			"SELECT mb_id,grp_no,grp_register FROM grp_detail where mb_id";
	@Override
	public void insert(Grp_detailVO grp_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		System.out.println("dao");
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, grp_detailVO.getMb_id());
			pstmt.setString(2, grp_detailVO.getGrp_no());
//			pstmt.setInt(3, grp_detailVO.getGrp_register());

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
	public void update(Grp_detailVO grp_detailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(3, grp_detailVO.getMb_id());
			pstmt.setString(1, grp_detailVO.getGrp_no());
			pstmt.setInt(2, grp_detailVO.getGrp_register());

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
	public void delete(String mb_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mb_id);

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
	public List<Grp_detailVO> findByPrimaryKey(String mb_id) {

		Grp_detailVO grp_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Grp_detailVO> list = new ArrayList<Grp_detailVO>();

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				grp_detailVO = new Grp_detailVO();
				grp_detailVO.setMb_id(rs.getString("mb_id"));
				grp_detailVO.setGrp_no(rs.getString("grp_no"));
				grp_detailVO.setGrp_register(rs.getInt("grp_register"));
				list.add(grp_detailVO); // Store the row in the list
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
	
	public Grp_detailVO findByPrimaryKeyByGrp_no(String grp_no) {

		Grp_detailVO grp_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMTGRPNO);

			pstmt.setString(1, grp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				grp_detailVO = new Grp_detailVO();
				grp_detailVO.setMb_id(rs.getString("mb_id"));
				grp_detailVO.setGrp_no(rs.getString("grp_no"));
				grp_detailVO.setGrp_register(rs.getInt("grp_register"));
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				grp_detailVO = new Grp_detailVO();
				grp_detailVO.setMb_id(rs.getString("mb_id"));
				grp_detailVO.setGrp_no(rs.getString("grp_no"));
				grp_detailVO.setGrp_register(rs.getInt("grp_register"));
				list.add(grp_detailVO); // Store the row in the list
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
	public int totalPeople(String grp_no) {
		// TODO Auto-generated method stub			
				int PeopleCount = 0 ;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {					
					con = ds.getConnection();					
					pstmt = con.prepareStatement(GET_PEOPLE_COUNT);
					pstmt.setString(1, grp_no);
					rs = pstmt.executeQuery();
					rs.next();
					PeopleCount = rs.getInt("count");
		
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
				return PeopleCount ;
	}

	@Override
	public int totalGroup(String mb_id) {
		int groupcount = 0 ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {					
			con = ds.getConnection();					
			pstmt = con.prepareStatement(GET_GROUP_COUNT);
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			rs.next();
			groupcount = rs.getInt("count");
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
		return groupcount ;
	}

	@Override
	public List<Grp_detailVO> findByPrimaryKeyMbidGetAll(String mb_id) {
		
		List<Grp_detailVO> list2 = new ArrayList<Grp_detailVO>();
		Grp_detailVO grp_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
	public Grp_detailVO findByPrimaryKeyByMb_id(String mb_id) {
		// TODO Auto-generated method stub
		return null;
	}
}