package com.mb_rpt.model;

public class MB_RPTVO implements java.io.Serializable {
	private String mb_rpt_no, mb_id_1,mb_id_2,rpt_reason;
	private Integer rpt_status;
	
	public MB_RPTVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMb_rpt_no() {
		return mb_rpt_no;
	}

	public void setMb_rpt_no(String mb_rpt_no) {
		this.mb_rpt_no = mb_rpt_no;
	}

	public String getMb_id_1() {
		return mb_id_1;
	}

	public void setMb_id_1(String mb_id_1) {
		this.mb_id_1 = mb_id_1;
	}

	public String getMb_id_2() {
		return mb_id_2;
	}

	public void setMb_id_2(String mb_id_2) {
		this.mb_id_2 = mb_id_2;
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
	
}
