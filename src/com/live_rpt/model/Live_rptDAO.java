package com.live_rpt.model;

import java.util.List;
import java.util.Map;

public class Live_rptDAO implements Live_rptDAO_interface{
	
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String userid = "DA106G5";
//	String passwd = "DA106G5";
	
	private static final String INSERT_STMT = "INSERT INTO LIVE_RPT (LIVE_RPT_NO,RPT_reason,cmt_no,mb_id) values ('cmtr'||LPAD(to_char(CMT_RPT_SEQ.nextval), 5, '0'),?,?,?)";
	private static final String GET_ALL_STMT = "SELECT cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id FROM cmt_rpt ORDER BY cmt_rpt_no";
	private static final String GET_ONE_STMT = "SELECT cmt_rpt_no, rpt_reason, rpt_status, cmt_no, mb_id FROM cmt_rpt WHERE cmt_rpt_no = ?";
	private static final String DELETE = "DELETE FROM cmt_rpt where cmt_rpt_no = ?";
	private static final String UPDATE = "UPDATE cmt_rpt SET rpt_reason = ?, rpt_status = ? where cmt_rpt_no = ?";
	private static final String UPDATE_ALL_CMT_STATUS = "UPDATE cmt_rpt SET rpt_status = ? where cmt_no = ?";
	private static final String GET_RPTED_MB_ID = "SELECT commentt.mb_id FROM commentt JOIN cmt_rpt ON commentt.cmt_no = cmt_rpt.cmt_no WHERE cmt_rpt.cmt_no = ?";
	
	
	
	
	

	@Override
	public void insert(Live_rptVO Live_rptVO) {
		
		
	}

	@Override
	public void update(Live_rptVO Live_rptVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateByCmtNo(Live_rptVO Live_rptVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getRptedMb_id(String live_rpt_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String live_rpt_no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Live_rptVO findByPrimaryKey(String live_rpt_no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Live_rptVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Live_rptVO> getAllUWish(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
