package com.live_rpt.model;

public class Live_rptVO implements java.io.Serializable{
	private String live_rpt_no;
	private String live_no;
	private String rpt_reason;
	private String mb_id;
	private Integer rpt_status;
	
	
	
	public Live_rptVO() {
		super();
	}


	public Live_rptVO(String live_rpt_no, String live_no, String rpt_reason, String mb_id, Integer rpt_status) {
		super();
		this.live_rpt_no = live_rpt_no;
		this.live_no = live_no;
		this.rpt_reason = rpt_reason;
		this.mb_id = mb_id;
		this.rpt_status = rpt_status;
	}


	public String getLive_rpt_no() {
		return live_rpt_no;
	}


	public void setLive_rpt_no(String live_rpt_no) {
		this.live_rpt_no = live_rpt_no;
	}


	public String getLive_no() {
		return live_no;
	}


	public void setLive_no(String live_no) {
		this.live_no = live_no;
	}


	public String getRpt_reason() {
		return rpt_reason;
	}


	public void setRpt_reason(String rpt_reason) {
		this.rpt_reason = rpt_reason;
	}


	public String getMb_id() {
		return mb_id;
	}


	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}


	public Integer getRpt_status() {
		return rpt_status;
	}


	public void setRpt_status(Integer rpt_status) {
		this.rpt_status = rpt_status;
	}
	
	
	
	
	

}
