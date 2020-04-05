package com.group_rpt.model;

public class Group_rptVO implements java.io.Serializable{
	private String group_rpt_no;
	private String grp_no;
	private String mb_id;
	private String rpt_reason;	
	private Integer rpt_status;
	
	public String getGroup_rpt_no() {
		return group_rpt_no;
	}
	public void setGroup_rpt_no(String group_rpt_no) {
		this.group_rpt_no = group_rpt_no;
	}
	public String getGrp_no() {
		return grp_no;
	}
	public void setGrp_no(String grp_no) {
		this.grp_no = grp_no;
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
