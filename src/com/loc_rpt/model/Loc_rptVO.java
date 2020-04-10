package com.loc_rpt.model;



public class Loc_rptVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loc_rpt_no;
	private String rpt_reason;
	private Integer rpt_status;
	private String loc_no;
	private String mb_id;
	
	public Loc_rptVO(){
		super();
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

	public String getMb_id() {
		return mb_id;
	}

	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}

	public String getLoc_rpt_no() {
		return loc_rpt_no;
	}

	public void setLoc_rpt_no(String loc_rpt_no) {
		this.loc_rpt_no = loc_rpt_no;
	}

	public String getLoc_no() {
		return loc_no;
	}

	public void setLoc_no(String loc_no) {
		this.loc_no = loc_no;
	}
	
}
