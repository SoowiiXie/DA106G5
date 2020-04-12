package com.rcd_rpt.model;



public class Rcd_rptVO implements java.io.Serializable{
	private String rcd_rpt_no;
	private String rpt_reason;
	private Integer rpt_status;
	private String rcd_no;
	private String mb_id;
	
	public Rcd_rptVO(){
		super();
	}

	public String getRcd_rpt_no() {
		return rcd_rpt_no;
	}

	public void setRcd_rpt_no(String rcd_rpt_no) {
		this.rcd_rpt_no = rcd_rpt_no;
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
