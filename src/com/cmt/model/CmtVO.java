package com.cmt.model;

import java.sql.Date;

public class CmtVO {
	private String cmt_no;
	private String cmt_content;
	private Date cmt_time;
	private Integer cmt_status;
	private String rcd_no;
	private String mb_id;
	
	public CmtVO(){
		super();
	}

	public String getCmt_no() {
		return cmt_no;
	}

	public void setCmt_no(String cmt_no) {
		this.cmt_no = cmt_no;
	}

	public String getCmt_content() {
		return cmt_content;
	}

	public void setCmt_content(String cmt_content) {
		this.cmt_content = cmt_content;
	}

	public Date getCmt_time() {
		return cmt_time;
	}

	public void setCmt_time(Date cmt_time) {
		this.cmt_time = cmt_time;
	}

	public Integer getCmt_status() {
		return cmt_status;
	}

	public void setCmt_status(Integer cmt_status) {
		this.cmt_status = cmt_status;
	}

	public String getRcd_no() {
		return rcd_no;
	}

	public void setRcd_no(String rcd_no) {
		this.rcd_no = rcd_no;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	
	
}
