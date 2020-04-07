package com.grp_detail.model;

import java.util.*;
import java.sql.*;

public class Grp_detailJDBCDAO implements Grp_detailDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "DA106";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO emp2 (empno,ename,job,hiredate,sal,comm,deptno) VALUES (emp2_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM emp2 order by empno";
	private static final String GET_ONE_STMT = 
		"SELECT empno,ename,job,to_char(hiredate,'yyyy-mm-dd') hiredate,sal,comm,deptno FROM emp2 where empno = ?";
	private static final String DELETE = 
		"DELETE FROM emp2 where empno = ?";
	private static final String UPDATE = 
		"UPDATE emp2 set ename=?, job=?, hiredate=?, sal=?, comm=?, deptno=? where empno = ?";

	@Override
	public void insert(Grp_detailVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEname());
			pstmt.setString(2, empVO.getJob());
			pstmt.setDate(3, empVO.getHiredate());
			pstmt.setDouble(4, empVO.getSal());
			pstmt.setDouble(5, empVO.getComm());
			pstmt.setInt(6, empVO.getDeptno());

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
	public void update(Grp_detailVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEname());
			pstmt.setString(2, empVO.getJob());
			pstmt.setDate(3, empVO.getHiredate());
			pstmt.setDouble(4, empVO.getSal());
			pstmt.setDouble(5, empVO.getComm());
			pstmt.setInt(6, empVO.getDeptno());
			pstmt.setInt(7, empVO.getEmpno());

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
	public void delete(Integer empno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empno);

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
	public Grp_detailVO findByPrimaryKey(Integer empno) {

		Grp_detailVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				empVO = new Grp_detailVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEname(rs.getString("ename"));
				empVO.setJob(rs.getString("job"));
				empVO.setHiredate(rs.getDate("hiredate"));
				empVO.setSal(rs.getDouble("sal"));
				empVO.setComm(rs.getDouble("comm"));
				empVO.setDeptno(rs.getInt("deptno"));
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
		return empVO;
	}

	@Override
	public List<Grp_detailVO> getAll() {
		List<Grp_detailVO> list = new ArrayList<Grp_detailVO>();
		Grp_detailVO empVO = null;

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
				empVO = new Grp_detailVO();
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEname(rs.getString("ename"));
				empVO.setJob(rs.getString("job"));
				empVO.setHiredate(rs.getDate("hiredate"));
				empVO.setSal(rs.getDouble("sal"));
				empVO.setComm(rs.getDouble("comm"));
				empVO.setDeptno(rs.getInt("deptno"));
				list.add(empVO); // Store the row in the list
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

		Grp_detailJDBCDAO dao = new Grp_detailJDBCDAO();

		// �s�W
		Grp_detailVO empVO1 = new Grp_detailVO();
		empVO1.setEname("�d�ç�1");
		empVO1.setJob("MANAGER");
		empVO1.setHiredate(java.sql.Date.valueOf("2005-01-01"));
		empVO1.setSal(new Double(50000));
		empVO1.setComm(new Double(500));
		empVO1.setDeptno(10);
		dao.insert(empVO1);

		// �ק�
		Grp_detailVO empVO2 = new Grp_detailVO();
		empVO2.setEmpno(7001);
		empVO2.setEname("�d�ç�2");
		empVO2.setJob("MANAGER2");
		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
		empVO2.setSal(new Double(20000));
		empVO2.setComm(new Double(200));
		empVO2.setDeptno(20);
		dao.update(empVO2);

		// �R��
		dao.delete(7014);

		// �d��
		Grp_detailVO empVO3 = dao.findByPrimaryKey(7001);
		System.out.print(empVO3.getEmpno() + ",");
		System.out.print(empVO3.getEname() + ",");
		System.out.print(empVO3.getJob() + ",");
		System.out.print(empVO3.getHiredate() + ",");
		System.out.print(empVO3.getSal() + ",");
		System.out.print(empVO3.getComm() + ",");
		System.out.println(empVO3.getDeptno());
		System.out.println("---------------------");

		// �d��
		List<Grp_detailVO> list = dao.getAll();
		for (Grp_detailVO aEmp : list) {
			System.out.print(aEmp.getEmpno() + ",");
			System.out.print(aEmp.getEname() + ",");
			System.out.print(aEmp.getJob() + ",");
			System.out.print(aEmp.getHiredate() + ",");
			System.out.print(aEmp.getSal() + ",");
			System.out.print(aEmp.getComm() + ",");
			System.out.print(aEmp.getDeptno());
			System.out.println();
		}
	}
}