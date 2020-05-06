package com.msg.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MessageDAO implements MessageDAO_interface{
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";

	private static final String INSERT_STMT = 
		"INSERT INTO MESSAGE (MSG_NO, MB_ID_1, MB_ID_2, MSG_CONTENT) VALUES ('MSN'||LPAD(to_char(msg_no_seq.NEXTVAL), 5, '0'), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM MESSAGE order by MSG_NO";
	private static final String GET_ALL_BY_MB_ID_2_STMT = 
		"SELECT * FROM (SELECT * FROM MESSAGE WHERE MB_ID_2 = ? order by MSG_NO DESC) where rownum between 1 and 4";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM MESSAGE where MSG_NO = ?";
	private static final String DELETE = 
		"DELETE FROM MESSAGE where MSG_NO = ?";
	private static final String UPDATE = 
		"UPDATE MESSAGE set MB_ID_1=?, MB_ID_2=?, MSG_CONTENT=?, MSG_STATUS=? where MSG_NO = ?";
	private static final String COUNT_ALL_NOTREADS = 
		"SELECT COUNT ('a notRead') AS NOTREADS FROM MESSAGE WHERE MB_ID_2 = ? AND MSG_STATUS = ?";
	
	// 連線池
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
	public void insert(MessageVO messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			
			pstmt.setString(1, messageVO.getMb_id_1());
			pstmt.setString(2, messageVO.getMb_id_2());
			pstmt.setString(3, messageVO.getMsg_content());

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
	public void update(MessageVO messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, messageVO.getMb_id_1());
			pstmt.setString(2, messageVO.getMb_id_2());
			pstmt.setString(3, messageVO.getMsg_content());
			pstmt.setInt(4, messageVO.getMsg_status());
			pstmt.setString(5, messageVO.getMsg_no());

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
	public void delete(String msg_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, msg_no);

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
	public MessageVO findByPrimaryKey(String msg_no) {
		MessageVO messageVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, msg_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				messageVO = new MessageVO();
				messageVO.setMsg_no(rs.getString("msg_no"));
				messageVO.setMb_id_1(rs.getString("mb_id_1"));
				messageVO.setMb_id_2(rs.getString("mb_id_2"));
				messageVO.setMsg_content(rs.getString("msg_content"));
				messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				messageVO.setMsg_status(rs.getInt("msg_status"));
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
		return messageVO;
	}

	@Override
	public List<MessageVO> getAllByMb_id_2(String mb_id_2) {
		// TODO Auto-generated method stub
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MB_ID_2_STMT);

			pstmt.setString(1, mb_id_2);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				messageVO = new MessageVO();
				messageVO.setMsg_no(rs.getString("msg_no"));
				messageVO.setMb_id_1(rs.getString("mb_id_1"));
				messageVO.setMb_id_2(rs.getString("mb_id_2"));
				messageVO.setMsg_content(rs.getString("msg_content"));
				messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				messageVO.setMsg_status(rs.getInt("msg_status"));
				list.add(messageVO); // Store the row in the list
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
	public List<MessageVO> getAll() {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				messageVO = new MessageVO();
				messageVO.setMsg_no(rs.getString("msg_no"));
				messageVO.setMb_id_1(rs.getString("mb_id_1"));
				messageVO.setMb_id_2(rs.getString("mb_id_2"));
				messageVO.setMsg_content(rs.getString("msg_content"));
				messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				messageVO.setMsg_status(rs.getInt("msg_status"));
				list.add(messageVO); // Store the row in the list
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
	
	public List<MessageVO> getAllPlu(Map<String,String> map){
		
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO = null;
		StringBuilder sb = new StringBuilder("SELECT * FROM MESSAGE WHERE ");
		
		Set set = map.keySet();
		int i = set.size();
		
		for (Object obj : set) {
			sb.append(obj.toString() + "=" + map.get(obj));
			if( i != 1 ) {
				sb.append(" AND ");
				i--;
			}
		}		

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			con = ds.getConnection();
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				messageVO = new MessageVO();
				messageVO.setMsg_no(rs.getString("msg_no"));
				messageVO.setMb_id_1(rs.getString("mb_id_1"));
				messageVO.setMb_id_2(rs.getString("mb_id_2"));
				messageVO.setMsg_content(rs.getString("msg_content"));
				messageVO.setMsg_time(rs.getTimestamp("msg_time"));
				messageVO.setMsg_status(rs.getInt("msg_status"));
				list.add(messageVO); // Store the row in the list
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
	
	
//	// 測試
//	public static void main(String[] args) {
//		MessageDAO dao = new MessageDAO();
//		
//		// 查
//		List<MessageVO> all = dao.getAll();
//		for(MessageVO messageVO:all) {
//			System.out.println(messageVO.getMsg_no());	
//			System.out.println(messageVO.getMb_id_1());
//			System.out.println(messageVO.getMb_id_2());
//			System.out.println(messageVO.getMsg_content());
//			System.out.println(messageVO.getMsg_time());
//			System.out.println(messageVO.getMsg_status());
//		}
//		
//		// 增
//		MessageVO messageVO = new MessageVO();
//		messageVO.setMb_id_1("soowii123");
//		messageVO.setMb_id_2("vain123");
//		messageVO.setMsg_content("HOHIOHOH");
//		dao.insert(messageVO);	
//		
//		// 改
//		MessageVO messageVO = new MessageVO();
//		messageVO.setMb_id_1("soowii123");
//		messageVO.setMb_id_2("vain123");
//		messageVO.setMsg_content("BBBBB");
//		messageVO.setMsg_status(2);
//		messageVO.setMsg_no("MSN00012");
//		dao.update(messageVO);
//		
//		 // 刪
//		dao.delete("MSN00013");
//		
//		 // 用PK查詢
//		MessageVO messageVO = dao.findByPrimaryKey("MSN00005");
//		System.out.println(messageVO.getMsg_no());	
//		System.out.println(messageVO.getMb_id_1());
//		System.out.println(messageVO.getMb_id_2());
//		System.out.println(messageVO.getMsg_content());
//		System.out.println(messageVO.getMsg_time());
//		System.out.println(messageVO.getMsg_status());
//		
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("MB_ID_1", "'vain123'");
//		map.put("MB_ID_2", "'michael123'");
//		List<MessageVO>  list = dao.getAllPlu(map);
//		for (MessageVO messageVO : list) {
//			System.out.println(messageVO.getMsg_no());	
//			System.out.println(messageVO.getMb_id_1());
//			System.out.println(messageVO.getMb_id_2());
//			System.out.println(messageVO.getMsg_content());
//			System.out.println(messageVO.getMsg_time());
//			System.out.println(messageVO.getMsg_status());
//		}
//	}

	@Override
	public Integer countNotReads(String mb_id_2) {
	// TODO Auto-generated method stub
			Integer notreads = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
//				Class.forName(driver);
//				con = DriverManager.getConnection(url, userid, passwd);
				con = ds.getConnection();
				pstmt = con.prepareStatement(COUNT_ALL_NOTREADS,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				pstmt.setString(1, mb_id_2);
				pstmt.setInt(2, 1);
				rs = pstmt.executeQuery();
				rs.next();
				notreads = Integer.parseInt(rs.getString("NOTREADS"));

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
			return notreads;
		}

}
