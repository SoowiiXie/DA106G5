package com.rcd_rpt.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class Rcd_rptJNDIDAO implements Rcd_rptDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO rcd_rpt (rcd_rpt_no,rpt_reason,rcd_no,mb_id) values ('rcdr'||LPAD(to_char(RCD_RPT_SEQ.nextval), 5, '0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT rcd_rpt_no, rpt_reason, rpt_status, rcd_no, mb_id FROM rcd_rpt ORDER BY rcd_rpt_no";
	private static final String GET_ONE_STMT = "SELECT rcd_rpt_no, rpt_reason, rpt_status, rcd_no, mb_id FROM rcd_rpt WHERE rcd_rpt_no = ?";
	private static final String DELETE = "DELETE FROM rcd_rpt where rcd_rpt_no = ?";
	private static final String UPDATE = "UPDATE rcd_rpt SET rpt_reason = ?, rpt_status = ? where rcd_rpt_no = ?";
	private static final String UPDATE_ALL_RPT_STATUS = "UPDATE rcd_rpt SET rpt_status = ? where rcd_no = ?";
	private static final String GET_RPTED_MB_ID = "SELECT record.mb_id FROM record JOIN rcd_rpt ON record.rcd_no = rcd_rpt.rcd_no WHERE rcd_rpt.rcd_no = ?";
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DA106G5_DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(Rcd_rptVO rcd_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, rcd_rptVO.getRpt_reason());
			pstmt.setString(2, rcd_rptVO.getRcd_no());
			pstmt.setString(3, rcd_rptVO.getMb_id());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	}



	@Override
	public void update(Rcd_rptVO rcd_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rcd_rptVO.getRpt_reason());
			pstmt.setInt(2, rcd_rptVO.getRpt_status());
			pstmt.setString(3, rcd_rptVO.getRcd_rpt_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	}



	@Override
	public void delete(String rcd_rpt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, rcd_rpt_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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

	}



	@Override
	public Rcd_rptVO findByPrimaryKey(String rcd_rpt_no) {
		Rcd_rptVO rcd_rpt = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rcd_rpt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rcd_rpt = new Rcd_rptVO();
				rcd_rpt.setRcd_rpt_no(rs.getString("rcd_rpt_no"));
				rcd_rpt.setRpt_reason(rs.getString("rpt_reason"));
				rcd_rpt.setRpt_status(rs.getInt("rpt_status"));
				rcd_rpt.setRcd_no(rs.getString("rcd_no"));
				rcd_rpt.setMb_id(rs.getString("mb_id"));
				rcd_rpt.setRcd_rpt_no(rs.getString("rcd_rpt_no"));
			}

			// Handle any driver errors
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
		return rcd_rpt;
	}



	@Override
	public List<Rcd_rptVO> getAll() {
		List<Rcd_rptVO> list = new ArrayList<Rcd_rptVO>();
		Rcd_rptVO rcd_rptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rcd_rptVO = new Rcd_rptVO();
				rcd_rptVO.setRcd_rpt_no(rs.getString("rcd_rpt_no"));
				rcd_rptVO.setRpt_reason(rs.getString("rpt_reason"));
				rcd_rptVO.setRpt_status(rs.getInt("rpt_status"));
				rcd_rptVO.setRcd_no(rs.getString("rcd_no"));
				rcd_rptVO.setMb_id(rs.getString("mb_id"));
				list.add(rcd_rptVO); // Store the row in the list
				
			}

			// Handle any driver errors
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
	public void updateByRcdNo(Rcd_rptVO rcd_rptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ALL_RPT_STATUS,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

			pstmt.setInt(1, rcd_rptVO.getRpt_status());
			pstmt.setString(2, rcd_rptVO.getRcd_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
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
		
	}
	
	
	@Override
	public String getRptedMb_id(String rcd_no) {
		String rpted_mb_id;//被檢舉會員編號
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_RPTED_MB_ID,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			pstmt.setString(1, rcd_no);
			
			rs = pstmt.executeQuery();

			rs.next();
			rpted_mb_id = rs.getString("mb_id");	

			// Handle any driver errors
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
		return rpted_mb_id;
	}
	

	




}
