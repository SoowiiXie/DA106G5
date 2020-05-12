package android.com.mb.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mb.model.MemberVO;

public class MemberDAO extends com.mb.model.MemberDAO implements com.mb.model.MemberDAO_interface{

	private static final String INSERT_STMT = "INSERT INTO MEMBER ( MB_ID, MB_PWD, MB_LINE_ID, MB_NAME ,MB_GENDER, MB_BIRTHDAY, MB_PIC, MB_EMAIL) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM MEMBER order by MB_ID";
	private static final String GET_ONE_STMT = "SELECT * FROM MEMBER where MB_ID = ?";
	private static final String UPDATE = "UPDATE MEMBER set MB_PWD=?, MB_LINE_ID=?, MB_NAME=?, MB_GENDER=?, MB_BIRTHDAY=?, MB_LV=?, MB_PIC=?, MB_RPT_TIMES=?, MB_EMAIL=?, MB_STATUS=? where MB_ID = ?";
	private static final String FIND_BY_ID_PASWD = "SELECT * FROM member WHERE MB_ID = ? AND MB_PWD = ?";
	private static final String CHECK_ID_EXIST = "SELECT MB_ID FROM member WHERE MB_ID = ?";
	private static final String FIND_BY_ID = "SELECT * FROM member WHERE MB_ID = ?";
	private static final String GET_IMAGE = "SELECT mb_pic FROM member WHERE mb_id = ?";
	
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
	public void insert(MemberVO memberVO) {
		// TODO Auto-generated method stub
		super.insert(memberVO);
	}

	@Override
	public void update(MemberVO memberVO) {
		// TODO Auto-generated method stub
		super.update(memberVO);
	}

	@Override
	public MemberVO findByPrimaryKey(String mb_id) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				memberVO = new MemberVO();
				memberVO.setMb_id(rs.getString("mb_id"));
				memberVO.setMb_pwd(rs.getString("mb_pwd"));
				memberVO.setMb_name(rs.getString("mb_name"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		// TODO Auto-generated method stub
		return super.getAll();
	}

	@Override
	public String addOneToRptTime(String mb_id) {
		// TODO Auto-generated method stub
		return super.addOneToRptTime(mb_id);
	}

	public boolean isMember(String mb_id, String mb_pwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isMember = false;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(FIND_BY_ID_PASWD);
			ps.setString(1, mb_id);
			ps.setString(2, mb_pwd);
			ResultSet rs = ps.executeQuery();
			isMember = rs.next();	
			return isMember;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isMember;
	}

	public boolean isUserIdExist(String mb_id) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isUserIdExist = false;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(CHECK_ID_EXIST);
			ps.setString(1, mb_id);
			ResultSet rs = ps.executeQuery();
			isUserIdExist = rs.next();
			return isUserIdExist;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isUserIdExist;
	}

	public MemberVO findById(String mb_id) {
		MemberVO member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(FIND_BY_ID);

			pstmt.setString(1, mb_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				member = new MemberVO();
				member.setMb_id(rs.getString(1));
				member.setMb_pwd(rs.getString(2));
				member.setMb_name(rs.getString(3));
				member.setMb_gender(rs.getInt(4));
				member.setMb_line_id(rs.getString(5));
//				member.setMb_birthday(rs.getDate(6));
//				member.setMb_email(rs.getString(7));
//				member.setMb_lv(rs.getInt(9));
//				member.setMb_rpt_times(rs.getInt(10));
//				member.setMb_status(rs.getInt(11));

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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return member;
	}
	
	
	public byte[] getImage(String mb_id) {
		byte[] picture = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con .prepareStatement(GET_IMAGE);
			
			pstmt.setString(1, mb_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				picture = rs.getBytes(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured."+ e.getMessage());
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace(System.err);
				}
			}
		}
		return picture;
	}
	
	
	public boolean insertFromAndroid(MemberVO memberVO) {
		boolean succeedOrNot = false;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, memberVO.getMb_id());
			pstmt.setString(2, memberVO.getMb_pwd());
			pstmt.setString(3, memberVO.getMb_line_id());
			pstmt.setString(4, memberVO.getMb_name());
			pstmt.setInt(5, memberVO.getMb_gender());
			pstmt.setDate(6, memberVO.getMb_birthday());
			pstmt.setBytes(7, memberVO.getMb_pic());
			pstmt.setString(8, memberVO.getMb_email());
			
	
			int i = pstmt.executeUpdate();
			if(i==0) {
				
			}else {
				succeedOrNot=true;
			}
			

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
		return succeedOrNot;
	}
	

}
