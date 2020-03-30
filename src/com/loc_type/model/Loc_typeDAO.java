package com.loc_type.model;

import static com.common.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Loc_typeDAO implements Loc_type_interface {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = "INSERT INTO Loc_type (Loc_typeNO, Loc_info) VALUES (?, ?)";
	private static final String UPDATE = "UPDATE Loc_type set Loc_info = ? where Loc_typeNO = ?";
	private static final String DELETE = "DELETE from Loc_type where Loc_typeNO = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM Loc_type where Loc_typeNO = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM Loc_type order by Loc_typeNO";

	@Override
	public void insert(Loc_typeVO loc_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, loc_typeVO.getLoc_typeno());
			pstmt.setString(2, loc_typeVO.getLoc_info());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void update(Loc_typeVO loc_typeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, loc_typeVO.getLoc_info());
			pstmt.setString(2, loc_typeVO.getLoc_typeno());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void delete(String loc_typeno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, loc_typeno);

			rs = pstmt.executeQuery();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	}

	@Override
	public Loc_typeVO findByPrimaryKey(String loc_typeno) {
		Loc_typeVO loc_typeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, loc_typeno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				loc_typeVO = new Loc_typeVO();
				loc_typeVO.setLoc_typeno(rs.getString("loc_typeno"));
				loc_typeVO.setLoc_info(rs.getString("loc_info"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
		return loc_typeVO;
	}

	@Override
	public List<Loc_typeVO> getAll() {
		List<Loc_typeVO> list = new ArrayList<Loc_typeVO>();
		Loc_typeVO loc_typeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER_CLASS);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				loc_typeVO = new Loc_typeVO();
				loc_typeVO.setLoc_typeno(rs.getString("loc_typeno"));
				loc_typeVO.setLoc_info(rs.getString("loc_info"));
				list.add(loc_typeVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	// 測試
	public static void main(String[] args) {
		Loc_typeDAO dao = new Loc_typeDAO();

		// 增
		Loc_typeVO loc_typeVO_insert = new Loc_typeVO();
		loc_typeVO_insert.setLoc_typeno("4");
		loc_typeVO_insert.setLoc_info("有7-11真好");
		dao.insert(loc_typeVO_insert);

		// 改
		Loc_typeVO loc_typeVO_update = new Loc_typeVO();
		loc_typeVO_update.setLoc_typeno("4");
		loc_typeVO_update.setLoc_info("全家就是你家");
		dao.update(loc_typeVO_update);

		// 用PK查詢
		Loc_typeVO loc_typeVO_getByPK = dao.findByPrimaryKey("4");
		System.out.println("新增並修改的PK是:"+loc_typeVO_getByPK.getLoc_typeno());
		System.out.println("修改後的值是:"+loc_typeVO_getByPK.getLoc_info());
		
		// 刪
		dao.delete("4");
		
		// 查
		List<Loc_typeVO> all = dao.getAll();
		System.out.println("\n刪掉剛剛新增的後剩:");
		for (Loc_typeVO loc_typeVO : all) {
			System.out.print(loc_typeVO.getLoc_typeno()+"：");
			System.out.println(loc_typeVO.getLoc_info());
		}

	}
}