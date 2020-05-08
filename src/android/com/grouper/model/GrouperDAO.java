package android.com.grouper.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GrouperDAO implements GrouperDAO_interface {
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
	// GRP_NO,MB_ID,LOC_NO,to_char(GRP_APPLYSTART,'yyyy-mm-dd
	// HH24:MI')GRP_APPLYSTART,to_char(GRP_APPLYEND,'yyyy-mm-dd
	// HH24:MI')GRP_APPLYEND,to_char(GRP_START,'yyyy-mm-dd
	// HH24:MI')GRP_START,to_char(GRP_END,'yyyy-mm-dd
	// HH24:MI')GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW
	private static final String INSERT_STMT = "INSERT INTO Grouper (GRP_NO,MB_ID,LOC_NO,GRP_APPLYSTART,GRP_APPLYEND,GRP_START,GRP_END,GRP_NAME,GRP_CONTENT,GRP_PERSONMAX,GRP_PERSONMIN,GRP_PERSONCOUNT,GRP_STATUS,GRP_FOLLOW) VALUES ('grp'||LPAD(to_char(grp_no_seq.NEXTVAL), 5, '0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM Grouper order by GRP_NO";
	private static final String GET_ONE_STMT = "SELECT * FROM Grouper where GRP_NO = ?";
	private static final String DELETE = "DELETE FROM Grouper where GRP_NO = ?";
	private static final String UPDATE = "UPDATE Grouper set MB_ID=?, LOC_NO=?, GRP_APPLYSTART=?, GRP_APPLYEND=?, GRP_START=?, GRP_END=?, GRP_NAME=?, GRP_CONTENT=?, GRP_PERSONMAX=?, GRP_PERSONMIN=?, GRP_PERSONCOUNT=?, GRP_STATUS=?, GRP_FOLLOW=? where GRP_NO = ?";

	private static final String UPDATE_GRP_PERSONCOUNT_ONE = "UPDATE Grouper set GRP_PERSONCOUNT=? where GRP_NO = ?";

	private static final String ADD_TO_GRP_DETAIL_STMT = "INSERT INTO GRP_DETAIL (MB_ID,GRP_NO,GRP_REGISTER) VALUES (?, ?, ?)";
	private static final String DELETE_FROM_GRP_DETAIL_STMT = "DELETE FROM GRP_DETAIL WHERE MB_ID = ? AND GRP_NO = ?";
	private static final String UPDATE_GRP_DETAIL_STATUS_STMT = "UPDATE GRP_DETAIL SET GRP_REGISTER = 2 WHERE MB_ID = ? AND GRP_NO = ?";

	private static final String GET_FOLLOWED_FROM_GRP_FOLLOW_STMT = "SELECT * FROM GROUPER JOIN GRP_FOLLOW ON (GROUPER.GRP_NO = GRP_FOLLOW.GRP_NO) WHERE GRP_FOLLOW.MB_ID = ?";
	

	private static final String GET_HOSTED_STMT = "SELECT * FROM Grouper where MB_ID = ?";
	private static final String GET_JOINED_FROM_GRP_DETAIL_STMT = "SELECT * FROM GROUPER JOIN GRP_DETAIL ON (GROUPER.GRP_NO = GRP_DETAIL.GRP_NO) WHERE GRP_DETAIL.MB_ID = ?";
//	private static final String GET_FOLLOWED_FROM_GRP_FOLLOW_STMT = "SELECT * FROM GRP_FOLLOW WHERE MB_ID = ?";
	private static final String ADD_TO_GRP_FOLLOW_STMT = "INSERT INTO GRP_FOLLOW (GRP_NO,MB_ID) VALUES (?, ?)";
	private static final String DELETE_FROM_GRP_FOLLOW_STMT = "DELETE FROM GRP_FOLLOW WHERE GRP_NO = ? AND MB_ID = ?";
	
	private static final String UPDATE_GRP_FOLLOW_IN_GROUPER_ONE = "UPDATE GROUPER SET GRP_FOLLOW = ? WHERE GRP_NO = ?";
	
	private static final String GET_GRP_REGISTER_FROM_GRP_DETAIL = "SELECT MB_ID FROM GRP_DETAIL WHERE GRP_REGISTER = 2 AND GRP_NO = ?";
	private static final String GET_ALL_GRP_REGISTER_FROM_GRP_DETAIL = "SELECT MB_ID FROM GRP_DETAIL WHERE GRP_NO = ?";

	@Override
	public boolean insert(GrouperVO grouperVO) {
		boolean insertSucceedOrNot = false;

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
			pstmt.setInt(11, 0);//Grp_personcount
			pstmt.setInt(12, 1);//Grp_status
			pstmt.setInt(13, 0);//Grp_follow

			int i = 0;
			i = pstmt.executeUpdate();
			if (i == 1) {
				insertSucceedOrNot = true;
			}

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
		return insertSucceedOrNot;
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
	public boolean updatePlusOne(GrouperVO grouperVO) {
		boolean updateSuccessOrNot = false;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_GRP_PERSONCOUNT_ONE);

			pstmt.setInt(1, grouperVO.getGrp_personcount() + 1);
			pstmt.setString(2, grouperVO.getGrp_no());

			int i = 0;
			i = pstmt.executeUpdate();
			if (i == 1) {
				updateSuccessOrNot = true;
			}

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
		return updateSuccessOrNot;

	}

	@Override
	public boolean updateMinusOne(GrouperVO grouperVO) {
		boolean updateSuccessOrNot = false;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_GRP_PERSONCOUNT_ONE);

			pstmt.setInt(1, grouperVO.getGrp_personcount() - 1);
			pstmt.setString(2, grouperVO.getGrp_no());

			int i = 0;
			i = pstmt.executeUpdate();
			if (i == 1) {
				updateSuccessOrNot = true;
			}

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
		return updateSuccessOrNot;

	}

	@Override
	public boolean addToGrp_Detail(String mb_id, String grp_no) {
		boolean addToGrp_DetailSuccessOrNot = false;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_TO_GRP_DETAIL_STMT);

			pstmt.setString(1, mb_id);
			pstmt.setString(2, grp_no);
			pstmt.setInt(3, 1);

			int i = 0;
			i = pstmt.executeUpdate();

			if (i == 1) {
				addToGrp_DetailSuccessOrNot = true;
			}

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
		return addToGrp_DetailSuccessOrNot;

	}

	@Override
	public boolean deleteFromGrp_Detail(String mb_id, String grp_no) {
		boolean deleteFromGrp_DetailSuccessOrNot = false;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_FROM_GRP_DETAIL_STMT);

			pstmt.setString(1, mb_id);
			pstmt.setString(2, grp_no);

			int i = 0;
			i = pstmt.executeUpdate();
			if (i == 1) {
				deleteFromGrp_DetailSuccessOrNot = true;
			}

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
		return deleteFromGrp_DetailSuccessOrNot;

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
	public List<GrouperVO> getHosted(String mb_id) {

		List<GrouperVO> list = new ArrayList<GrouperVO>();
		GrouperVO grouperVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_HOSTED_STMT);

			pstmt.setString(1, mb_id);

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
	public List<GrouperVO> getFollow(String mb_id) {

		List<GrouperVO> list = new ArrayList<GrouperVO>();
		GrouperVO grouperVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_FOLLOWED_FROM_GRP_FOLLOW_STMT);

			pstmt.setString(1, mb_id);

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

		for (GrouperVO e : list) {

			System.out.println(e);
		}
		return list;

	}

	@Override
	public List<GrouperVO> getJoined(String mb_id) {

		List<GrouperVO> list = new ArrayList<GrouperVO>();
		GrouperVO grouperVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_JOINED_FROM_GRP_DETAIL_STMT);

			pstmt.setString(1, mb_id);

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
	public String joinGroup(String mb_id, String grp_no) {

		List<GrouperVO> joinedList = new ArrayList<GrouperVO>();

		joinedList = getJoined(mb_id);
		String joinStatus = "justCantHitMe";

		System.out.println("joinedList : " + joinedList);

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
			}

			// 判斷此揪團時間是否與已參加揪團時間重疊

			System.out.println(grouperVO);

			long tsJoinStart = grouperVO.getGrp_start().getTime();
			long tsJoinEnd = grouperVO.getGrp_end().getTime();

			System.out.println("grouperVO : " + grouperVO.getGrp_no());
			System.out.println("-------------------------------------------------");
			
			//目前沒揪團的話就直接加入
			if(joinedList.isEmpty()) {
				joinStatus = "canJoin";
			}
			
			
			
			
			// 每個已參加揪團與想參加揪團比對
			for (GrouperVO joinedVO : joinedList) {

				long tsJoinedStart = joinedVO.getGrp_start().getTime();
				long tsJoinedEnd = joinedVO.getGrp_end().getTime();

				// 中途觀察結果
				System.out.println("joinedVO : " + joinedVO.getGrp_no());

				// 判斷可否加入揪團
				if ((tsJoinedStart > tsJoinStart && tsJoinedStart >= tsJoinEnd)
						|| (tsJoinStart > tsJoinedStart && tsJoinStart >= tsJoinedEnd)) {
					joinStatus = "canJoin";
				} else if (grouperVO.getGrp_no().equals(joinedVO.getGrp_no())) {
					joinStatus = "joinedAlready";
					break;
				} else if (grouperVO.getGrp_personcount() == grouperVO.getGrp_personmax()) {
					joinStatus = "groupFullAlready";
					break;
				} else {
					joinStatus = "justCantHitMe";
					break;
				}
				System.out.println("HALF WAY: " + joinStatus);
				System.out.println("-------------------------------------------------");

			}

			// 執行加入揪團
			if (joinStatus.equals("canJoin")) {
				if (updatePlusOne(grouperVO) && addToGrp_Detail(mb_id, grouperVO.getGrp_no())) {
					joinStatus = "joinSuccess";
				} else {
					
				}
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

		System.out.println("FINAL: " + joinStatus);
		return joinStatus;

	}

	@Override
	public boolean cancelGroup(String mb_id, String grp_no) {

		boolean cancelStatus = false;
		GrouperVO grouperVO = null;

		grouperVO = findByPrimaryKey(grp_no);

		if (deleteFromGrp_Detail(mb_id, grp_no) && updateMinusOne(grouperVO)) {
			cancelStatus = true;
		} else {
			System.out.println("cancelGroup method has encountered an error!!!!!");
		}

		return cancelStatus;

	}

	@Override
	public boolean addFollowedGroup(String mb_id, String grp_no) {
		boolean addFollowedSucceedOrNot = false;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(ADD_TO_GRP_FOLLOW_STMT);

			pstmt.setString(1, grp_no);
			pstmt.setString(2, mb_id);

			int i = 0;
			i = pstmt.executeUpdate();

			if (i == 1 && updateGrp_FollowInGrouperPlusOne(grp_no)) {
				addFollowedSucceedOrNot = true;
			}

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

		return addFollowedSucceedOrNot;
	}

	@Override
	public boolean cancelFollowedGroup(String mb_id, String grp_no) {

		boolean cancelFollowedSucceedOrNot = false;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_FROM_GRP_FOLLOW_STMT);

			pstmt.setString(1, grp_no);
			pstmt.setString(2, mb_id);

			int i = 0;
			i = pstmt.executeUpdate();

			if (i == 1 && updateGrp_FollowInGrouperMinusOne(grp_no)) {
				cancelFollowedSucceedOrNot = true;
			}

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

		return cancelFollowedSucceedOrNot;

	}

	@Override
	public boolean updateGrp_DetailStatus(String mb_id, String grp_no) {
		boolean updateGrp_DetailStatusSuccessOrNot = false;
		
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_GRP_DETAIL_STATUS_STMT);

			pstmt.setString(1, mb_id);
			pstmt.setString(2, grp_no);

			int i = 0;
			i = pstmt.executeUpdate();

			if (i == 1) {
				updateGrp_DetailStatusSuccessOrNot = true;
			}

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
		
		
		
		
		return updateGrp_DetailStatusSuccessOrNot;
	}

	@Override
	public boolean updateGrp_FollowInGrouperPlusOne(String grp_no) {
		boolean updateGrp_FollowInGrouperPlusOneSuccessOrNot = false;
		GrouperVO grouperVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_GRP_FOLLOW_IN_GROUPER_ONE);
			
			grouperVO = findByPrimaryKey(grp_no);

			pstmt.setInt(1, grouperVO.getGrp_follow() + 1);
			pstmt.setString(2, grouperVO.getGrp_no());

			int i = 0;
			i = pstmt.executeUpdate();
			if (i == 1) {
				updateGrp_FollowInGrouperPlusOneSuccessOrNot = true;
			}

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
		return updateGrp_FollowInGrouperPlusOneSuccessOrNot;
	}

	@Override
	public boolean updateGrp_FollowInGrouperMinusOne(String grp_no) {
		boolean updateGrp_FollowInGrouperMinusOneSuccessOrNot = false;
		GrouperVO grouperVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_GRP_FOLLOW_IN_GROUPER_ONE);
			
			grouperVO = findByPrimaryKey(grp_no);

			pstmt.setInt(1, grouperVO.getGrp_follow() - 1);
			pstmt.setString(2, grouperVO.getGrp_no());

			int i = 0;
			i = pstmt.executeUpdate();
			if (i == 1) {
				updateGrp_FollowInGrouperMinusOneSuccessOrNot = true;
			}

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
		return updateGrp_FollowInGrouperMinusOneSuccessOrNot;
	}

	@Override
	public List<String> getGrp_registerFromGrp_detail(String grp_no) {
		
		List<String> list = new ArrayList<String>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_GRP_REGISTER_FROM_GRP_DETAIL);

			pstmt.setString(1, grp_no);

			rs = pstmt.executeQuery();
			
			int i = 1;
			while (rs.next()) {
				String str = rs.getString(i);
				i++;
				list.add(str); // Store the row in the list
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
	public List<String> getAllGrp_registerFromGrp_detail(String grp_no) {
		List<String> list = new ArrayList<String>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_GRP_REGISTER_FROM_GRP_DETAIL);

			pstmt.setString(1, grp_no);

			rs = pstmt.executeQuery();
			
			
			int i = 1;
			while (rs.next()) {
				String str = rs.getString(i);
				System.out.println(str);
				i++;
				list.add(str); // Store the row in the list
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
}