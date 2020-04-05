package com.cmt_rpt.model;



public class Cmt_rptVO {
	private String cmt_rpt_no;
	private String rpt_reason;
	private Integer rpt_status;
	private String cmt_no;
	private String mb_id;
	
	public Cmt_rptVO(){
		super();
	}

	public String getCmt_rpt_no() {
		return cmt_rpt_no;
	}

	public void setCmt_rpt_no(String cmt_rpt_no) {
		this.cmt_rpt_no = cmt_rpt_no;
	}

	public String getRpt_reason() {
		return rpt_reason;
	}

	public void setRpt_reason(String rpt_reason) {
		this.rpt_reason = rpt_reason;
	}

	public Integer getRpt_status() {
		return rpt_status;
	}

	public void setRpt_status(Integer rpt_status) {
		this.rpt_status = rpt_status;
	}

	public String getCmt_no() {
		return cmt_no;
	}

	public void setCmt_no(String cmt_no) {
		this.cmt_no = cmt_no;
	}

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	
}
